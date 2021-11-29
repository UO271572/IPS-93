package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.comparar.VentanaCompararController;
import ips.business.corredores.CorredorDTO;
import ips.business.corredores.CorredoresController;
import ips.business.inscripciones.EstadoInscripcionesController;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.corredores.CorredoresModel;
import ips.ui.MenuCorredorView;
import ips.ui.MenuInscripcionClubView;
import ips.ui.MenuInscripcionView;
import ips.ui.carreras.CarrerasView;
import ips.ui.comparar.VentanaCompararView;
import ips.ui.corredores.CorredoresView;
import ips.ui.inscripciones.EstadoInscripcionesView;
import ips.util.Printer;

public class MenuCorredorController {

    private MenuCorredorView view;
    private CarrerasController cc;
    private CorredoresController coc;

    private final static int CODIGO_ERROR_NO_SELECCION = -404;
    private List<CarreraDisplayDTO> listaCarreras;

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
	view.getBtnInscribirClub().addActionListener(accionBotonInscribirClub());
	view.getBtnComparar().addActionListener(accionAbrirVentanaComparacion());
	view.getBtnVerInscripciones().addActionListener(accionBtnVerInscripciones());
    }

    private ActionListener accionBtnVerInscripciones() {
	return new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		comprobarCorredor();
	    }
	};
    }

    private void comprobarCorredor() {
	CorredorDTO corredor = emailRegistrado();
	if (corredor != null)
	    abrirVentanaInscripciones(corredor);
	else {
	    if (!view.getPnFormulario().isVisible())
		view.getPnFormulario().setVisible(true);
	    else {
		corredor = recogidaDatos();
		if (corredor != null)
		    abrirVentanaInscripciones(corredor);
	    }
	}
    }

    private void abrirVentanaInscripciones(CorredorDTO corredor) {
	try {
	    EstadoInscripcionesView estado = new EstadoInscripcionesView();
	    estado.setCorredor(corredor);
	    new EstadoInscripcionesController(estado);
	    estado.setVisible(true);
	} catch (BusinessException e) {
	    Printer.printBusinessException(e);
	}
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

    public void inicializarTablaCarrerasSinFiltro() {
	vaciarTabla();
	// try {
	listaCarreras = cc.getListaCarreras();
	añadirListaCarrerasTabla(listaCarreras);

    }

    private void vaciarTabla() {
	view.getTableModel().setRowCount(0);
    }

    private void inicializarTablaCarrerasConFiltro() {
	vaciarTabla();
	try {
	    listaCarreras = cc.getListaCarrerasFiltradas();
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
	    int plazasDisponibles = listaCarreras.get(i).getPlazasRestantes();
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
		try {
		    abrirVentanaInscripcion();
		} catch (BusinessException e1) {
		    Printer.printBusinessException(e1);
		}
	    }
	};

    }

    private ActionListener accionBotonInscribirClub() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		// TODO se abre dos veces
		MenuInscripcionClubView clubView = new MenuInscripcionClubView();
		MenuInscripcionClubController clubController = new MenuInscripcionClubController(clubView);
		clubController.setMenuCorredor(returnThis());
		int idCarrera = getIdCarreraFromTable();
		if (idCarrera != CODIGO_ERROR_NO_SELECCION) {
		    if (checkCarreraAbierta(idCarrera)) {
			clubController.setIdCarrera(idCarrera);
			clubView.setVisible(true);
		    }
		} else {
		    JOptionPane.showMessageDialog(null, "Debes seleccionar una carrera abierta");
		}
	    }
	};

    }

    private MenuCorredorController returnThis() {
	return this;
    }

    private boolean checkCarreraAbierta(int idCarrera) {
	List<CarreraDisplayDTO> listaCarrerasAbiertas = cc.getListaCarreras();
	for (CarreraDisplayDTO carrera : listaCarrerasAbiertas) {
	    if (carrera.getIdCarrera() == idCarrera) {
		return true;
	    }

	}
	JOptionPane.showMessageDialog(null, "La carrera con ID " + idCarrera + " no está abierta");
	return false;
    }

    private int getIdCarreraFromTable() {
	int column = 0;
	int row = view.getTable().getSelectedRow();
	if (column >= 0 && row >= 0) {
	    return Integer.parseInt(view.getTableModel().getValueAt(row, column).toString());
	} else {
	    return CODIGO_ERROR_NO_SELECCION;
	}
    }

    private void abrirVentanaInscripcion() throws BusinessException {
	CarreraDisplayDTO carrera = getCarrera();
	if (carrera == null) {
	    JOptionPane.showMessageDialog(view, "Debe seleccionar una carrera");
	    return;
	}
	CorredorDTO corredor = emailRegistrado();
	if (corredor == null) {
	    registrarCorredor();
	    return;
	}
	if (corredor.getIdCarrera() == carrera.getIdCarrera()) {
	    JOptionPane.showMessageDialog(view, "Debe seleccionar una carrera en la que no esté ya inscrito", "ERROR",
		    JOptionPane.ERROR_MESSAGE);
	    return;
	} else {
	    inscripcion(corredor, carrera);
	    MenuInscripcionView inscripcion = new MenuInscripcionView();
	    MenuInscripcionController inscripcionCOntroller = new MenuInscripcionController(inscripcion);
	    inscripcion.setCorredor(corredor);
	    inscripcion.setCarrera(getCarrera());
	    inscripcion.setVisible(true);
	    new MenuInscripcionController(inscripcion);
	}
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
	    CarreraDisplayDTO carrera = getCarrera();
	    if (carrera == null) {
		JOptionPane.showMessageDialog(view, "Debe seleccionar una carrera");
		return;
	    }
	    if (corredor != null) {
		inscripcion(corredor, carrera);
		MenuInscripcionView inscripcion = new MenuInscripcionView();
		MenuInscripcionController inscripcionCOntroller = new MenuInscripcionController(inscripcion);
		inscripcion.setCorredor(corredor);
		inscripcion.setCarrera(getCarrera());
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

    private void inscripcion(CorredorDTO corredor, CarreraDisplayDTO carrera) {
	corredor.setIdCarrera(carrera.getIdCarrera());
	// Anteriormente plazasDisponibles
	carrera.setPlazasRestantes(carrera.getPlazasRestantes() - 1);
	corredor.setEstadoInscripcion("Pre-inscrito");
    }

    public CarreraDisplayDTO getCarrera() {
	CarreraDisplayDTO carrera = null;
	int i = view.getTable().getSelectedRow();
	if (i > -1)
	    carrera = listaCarreras.get(i);
	return carrera;
    }

}
