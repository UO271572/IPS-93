package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JDialog;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.comparar.VentanaCompararController;
import ips.business.corredores.CorredorDTO;
import ips.business.corredores.CorredoresController;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.corredores.CorredoresModel;
import ips.ui.MenuCorredorView;
import ips.ui.carreras.CarrerasView;
import ips.ui.carreras.EstadoInscripcionesView;
import ips.ui.carreras.InscripcionView;
import ips.ui.comparar.VentanaCompararView;
import ips.ui.corredores.CorredoresView;
import ips.util.Printer;

public class MenuCorredorController {

    private MenuCorredorView view;
    private CarrerasController cc;
    private CorredoresController coc;

    public MenuCorredorController(MenuCorredorView view) {
	this.view = view;
	this.cc = new CarrerasController(new CarrerasModel(), new CarrerasView());
	this.coc = new CorredoresController(new CorredoresModel(), new CorredoresView());
	initController();
    }

    public void initController() {
	view.addWindowListener(notCloseDirectly());
	inicializarTablaCarrerasSinFiltro();
	view.getRdbtnVerTodas().addActionListener(accionBotonVerTodas());
	view.getRdbtnAbiertas().addActionListener(accionBotonVerNoCompetidas());
	view.getBtnInscribirse().addActionListener(accionBtnInscribirse());
	view.getBtnVerInscripciones().addActionListener(accionVerInscripciones());
	view.getBtnComparar().addActionListener(accionAbrirVentanaComparacion());
    }

    public WindowAdapter notCloseDirectly() {
	return new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		view.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    }
	};
    }

    private ActionListener accionBotonVerNoCompetidas() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		inicializarTablaCarrerasConFiltro();
	    }
	};
    }

    private ActionListener accionBotonVerTodas() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		inicializarTablaCarrerasSinFiltro();
	    }
	};
    }

    private void inicializarTablaCarrerasSinFiltro() {
	vaciarTabla();
	try {
	    List<CarreraDisplayDTO> listaCarreras = cc.getListaCarreras();
	    añadirListaCarrerasTabla(listaCarreras);
	} catch (BusinessException e1) {
	    Printer.printBusinessException(e1);
	}
    }

    private void vaciarTabla() {
	view.getTableModel().setRowCount(0);
    }

    private void inicializarTablaCarrerasConFiltro() {
	vaciarTabla();
	try {
	    List<CarreraDisplayDTO> listaCarreras = cc.getListaCarrerasFiltradas();
	    añadirListaCarrerasTabla(listaCarreras);
	} catch (BusinessException e1) {
	    Printer.printBusinessException(e1);
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
	    String espera = listaCarreras.get(i).getListaDeEspera();
	    Object[] data = { idCarerra, nombre, fecha, tipo, lugar, distancia, plazasDisponibles, espera };
	    view.getTableModel().insertRow(0, data);
	}
    }

    private ActionListener accionBtnInscribirse() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		inicializarTablaCarrerasSinFiltro();
		try {
		    abrirVentanaInscripcion();
		} catch (BusinessException e1) {
		    e1.printStackTrace();
		}
	    }
	};

    }

    private void abrirVentanaInscripcion() throws BusinessException {
	CorredorDTO corredor = emailRegistrado();
	if (corredor != null) {
	    InscripcionView inscripcion = new InscripcionView();
	    inscripcion.setCorredor(corredor);
	    inscripcion.setVisible(true);
	} else
	    registrarCorredor();
    }

    private CorredorDTO emailRegistrado() {
	String email = view.getTfEmail().getText();
	CorredorDTO corredor = coc.getCorredorRegistradoByEmail(email);
	return corredor;
    }

    private void registrarCorredor() throws BusinessException {
	if (!view.getPnFormulario().isVisible())
	    view.getPnFormulario().setVisible(true);
	else {
	    CorredorDTO corredor = recogidaDatos();
	    if (corredor != null) {
		InscripcionView inscripcion = new InscripcionView();
		inscripcion.setCorredor(corredor);
		inscripcion.setVisible(true);
	    }
	}
    }

    private CorredorDTO recogidaDatos() {
	String email = view.getTfEmail().getText();
	String nombre = view.getTfNombre().getText();
	String apellidos = view.getTfApellidos().getText();
	String dni = view.getTfDni().getText();
	int dia = (int) view.getSpDia().getValue();
	int mes = (int) view.getSpMes().getValue();
	int año = (int) view.getSpAño().getValue();
	String sexo = (String) view.getCbSexo().getSelectedItem();

	if (!validarDatos(email, nombre, apellidos, dni))
	    return null;

	CorredorDTO corredor = new CorredorDTO();
	corredor.setEmail(email);
	corredor.setNombre(nombre);
	corredor.setApellidos(apellidos);
	corredor.setDniCorredor(dni);
	corredor.setFechaNacimiento(new Date(año, mes, dia));
	corredor.setSexo(sexo);

	coc.addCorredor(corredor);
	return corredor;
    }

    private boolean validarDatos(String email, String nombre, String apellidos, String dni) {
	if (email.isEmpty()) {
	    view.getTfEmail().grabFocus();
	    return false;
	}
	if (nombre.isEmpty()) {
	    view.getTfNombre().grabFocus();
	    return false;
	}
	if (apellidos.isEmpty()) {
	    view.getTfApellidos().grabFocus();
	    return false;
	}
	if (dni.isEmpty()) {
	    view.getTfDni().grabFocus();
	    return false;
	} else
	    return true;
    }

    private ActionListener accionAbrirVentanaComparacion() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		VentanaCompararView compararView = new VentanaCompararView();
		VentanaCompararController controller = new VentanaCompararController(view.getTfEmail().getText(),
			compararView);
		compararView.setVisible(true);
	    }
	};
    }

    private ActionListener accionVerInscripciones() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		EstadoInscripcionesView estado = null;
		try {
		    estado = new EstadoInscripcionesView();
		} catch (BusinessException e1) {
		    e1.printStackTrace();
		}
		estado.setVisible(true);
	    }
	};

    }

}
