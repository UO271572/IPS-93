package ips.business.comparar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.persistence.carreras.CarrerasModel;
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

	// añadir listener al botón cerrar que haga dispose
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
	for (int i = 0; i < listaCarreras.size(); i++) {
	    int idCarerra = listaCarreras.get(i).getIdCarrera();
	    String nombre = listaCarreras.get(i).getNombre();
	    Date fecha = listaCarreras.get(i).getFechaCompeticion();
	    String tipo = listaCarreras.get(i).getTipo();
	    int plazasDisponibles = listaCarreras.get(i).getPlazasDisponibles();
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

	    }
	};
    }

}
