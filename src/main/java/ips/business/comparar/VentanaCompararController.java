package ips.business.comparar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.clasificaciones.ClasificacionController;
import ips.business.inscripciones.InscripcionDTO;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.clasificaciones.ClasificacionModel;
import ips.ui.carreras.CarrerasView;
import ips.ui.comparar.VentanaCompararView;
import ips.util.Printer;

public class VentanaCompararController {

    private CarrerasController cc;
    private VentanaCompararView view;
    private String email;

    public VentanaCompararController(String email, VentanaCompararView view) {
	this.email = email;
	this.view = view;

	this.cc = new CarrerasController(new CarrerasModel(), new CarrerasView());

	initController();
    }

    private void initController() {

	// cargar tabla conc arreras en las que ha competido
	cargarTablaCarreras();

	// añadir listener al boton de la tabla de la izquierda, ese cogerá el id de la
	// carrera seleccionada
	// y cargará la tabla de la derecha
	view.getBtnGenerarClasificacion().addActionListener(accionGenerarClasificacion());

	// aladir listener al botón de la tabla de la derecha, ese cogerá el atleta
	// seleccionado y el id de la carrera
	// y cargará los datos del atleta usuario de la aplicación para dicha carrera y
	// los del atleta seleccionado
	view.getBtnCompararAtleta().addActionListener(accionCompararAtleta());

	// añadir listener al botón cerrar que haga dispose
	view.getBtnCerrar().addActionListener(accionCerrarVentana());
    }

    private void cargarTablaCarreras() {
	// TODO Auto-generated method stub
	try {

	    List<CarreraDisplayDTO> listaCarreras = cc.getListaCarrerasCompetidasPorEmailCorredor(email);
	    añadirListaCarrerasTabla(listaCarreras);
	} catch (BusinessException e) {
	    Printer.printBusinessException(e);
	}

    }

    private void añadirListaCarrerasTabla(List<CarreraDisplayDTO> listaCarreras) {
	view.getModeloCarrerasCompetidas().setRowCount(0);

	for (int i = 0; i < listaCarreras.size(); i++) {
	    int idCarerra = listaCarreras.get(i).getIdCarrera();
	    String nombre = listaCarreras.get(i).getNombre();
	    Date fecha = listaCarreras.get(i).getFechaCompeticion();
	    String tipo = listaCarreras.get(i).getTipo();
	    int plazasDisponibles = listaCarreras.get(i).getPlazasTotales();// CAmbio
	    double distancia = listaCarreras.get(i).getDistancia();
	    String lugar = listaCarreras.get(i).getLugar();
	    Object[] data = { idCarerra, nombre, fecha, tipo, lugar, distancia, plazasDisponibles };
	    view.getModeloCarrerasCompetidas().insertRow(0, data);
	}
    }

    private ActionListener accionGenerarClasificacion() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		ClasificacionController controller = new ClasificacionController(new ClasificacionModel());

		int fila = view.getTablaCarrerasCompetidas().getSelectedRow();
		int idCarrera = (int) view.getTablaCarrerasCompetidas().getModel().getValueAt(fila, 0);
		List<InscripcionDTO> corredores = controller.obtenerClasificacionAbsoluta(idCarrera);

		añadirListaCorredoresTabla(corredores);
	    }
	};
    }

    private void añadirListaCorredoresTabla(List<InscripcionDTO> corredores) {

	view.getModeloClasificacion().setRowCount(0);

	for (int i = 0; i < corredores.size(); i++) {

	    String dni = corredores.get(i).getDnicorredor();
	    int dorsal = corredores.get(i).getDorsal();
	    int posicion = i + 1;

	    // double ritmoPorKm

	    Time tiempoFin = corredores.get(i).getTiempofin();

	    // tiempos parciales

	    Object[] data = { dni, dorsal, posicion, tiempoFin };
	    view.getModeloClasificacion().insertRow(i, data);
	}
    }

    private ActionListener accionCompararAtleta() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		DefaultTableModel modelo = view.getModeloComparacion();

		modelo.setRowCount(0);

		ClasificacionController controller = new ClasificacionController(new ClasificacionModel());

		int fila = view.getTablaCarrerasCompetidas().getSelectedRow();
		int idCarrera = (int) view.getTablaCarrerasCompetidas().getModel().getValueAt(fila, 0);
		List<InscripcionDTO> corredores = controller.obtenerClasificacionAbsolutaCorredor(idCarrera, email);

		String dni = corredores.get(0).getDnicorredor();
		int dorsal = corredores.get(0).getDorsal();
		int posicion = 0;

		// double ritmoPorKm

		Time tiempoFin = corredores.get(0).getTiempofin();
		// tiempos parciales

		Object[] data = { dni, dorsal, posicion, tiempoFin };
		modelo.insertRow(0, data);

		int indice = view.getTablaClasificacion().getSelectedRow();

		modelo.addRow(view.getModeloClasificacion().getDataVector().get(indice));
	    }
	};
    }

    private ActionListener accionCerrarVentana() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		view.dispose();
	    }
	};
    }

}
