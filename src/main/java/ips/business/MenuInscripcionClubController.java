package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.corredores.CorredorDTO;
import ips.business.corredores.CorredoresController;
import ips.business.inscripciones.InscripcionController;
import ips.business.inscripciones.InscripcionDTO;
import ips.ui.MenuInscripcionClubView;
import ips.util.FileUtil;

public class MenuInscripcionClubController {

    private final static int LIMITE_INFERIOR_EDAD = 18;

    private MenuInscripcionClubView view;

    private CorredoresController corredoresController;

    private InscripcionController inscripcionesController;

    private List<CorredorDTO> corredores = new ArrayList<CorredorDTO>();

    private int idCarrera;

    private int inscripcionesNuevas;

    private int plazasRestantes = 0;

    private MenuCorredorController corredorController;

    public MenuInscripcionClubController(MenuInscripcionClubView view) {
	this.view = view;
	inscripcionesNuevas = 0;
	corredoresController = new CorredoresController();
	inscripcionesController = new InscripcionController();
	initController();
    }

    public void initController() {
	view.getBtnInscribir().addActionListener(accionIncribirCorredor());
	view.getBtnFinalizar().addActionListener(accionFinalizar());
	view.getBtnEligeUnFichero().addActionListener(accionFichero());

    }

    public void setIdCarrera(int idCarrera) {
	this.idCarrera = idCarrera;
	view.getLabel().setText(idCarrera + "");
    }

    private ActionListener accionIncribirCorredor() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (validarDatosFormulario()) {
		    CorredorDTO corredor = new CorredorDTO();
		    String nombre = view.getTxNombre().getText();
		    String apellidos = view.getTxApellidos().getText();
		    String dni = view.getTxDNI().getText();
		    Date fechaNacimiento = new java.sql.Date(view.getDateChooser().getDate().getTime());
		    String email = view.getTxEmail().getText();
		    String sexo = (String) view.getCbSexo().getSelectedItem();
		    corredor.setNombre(nombre);
		    corredor.setApellidos(apellidos);
		    corredor.setDniCorredor(dni);
		    corredor.setFechaNacimiento(fechaNacimiento);
		    // TODO fechas y estado inscripcion
		    corredor.setEmail(email);
		    corredor.setSexo(sexo);
		    corredores.add(corredor);
		    Object[] data = { nombre, apellidos, dni, fechaNacimiento, email, sexo };
		    view.getCorredoresModel().insertRow(view.getTableCorredores().getRowCount(), data);
		    initCamposFormulario();
		}
	    }
	};
    }

    private ActionListener accionFichero() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File file = fc.getSelectedFile();
		    List<CorredorDTO> corredoresProvisionales = new ArrayList<CorredorDTO>();
		    try {
			FileUtil.loadFile(file, corredoresProvisionales);
			// COmparaciones
			int total = corredoresProvisionales.size();
			int annadidos = 0;
			for (CorredorDTO corredor : corredoresProvisionales) {
			    if (validarDatosFichero(corredor)) {
				corredores.add(corredor);
				Object[] data = { corredor.getNombre(), corredor.getApellidos(),
					corredor.getDniCorredor(), corredor.getFechaNacimiento(), corredor.getEmail(),
					corredor.getSexo() };
				view.getCorredoresModel().insertRow(view.getTableCorredores().getRowCount(), data);
				initCamposFormulario();
				annadidos++;
			    }
			}
			// corredores
			JOptionPane.showMessageDialog(null,
				"Se han añadido " + annadidos + " de " + total + "corredores.");
		    } catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Ha habido un error de formato");
		    }

		} else {
		    // log.append("Open command cancelled by user." + newline);
		}
	    }

	};
    }

    private ActionListener accionFinalizar() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		// previamente hay que mirar que la carrera en la que se pincha esta abierta
		// HECHO
		// debe de haber algun corredor
		if (corredores.size() > 0) {
		    // buscar plazas disponibles de la carrera en cuestion
		    CarrerasController carrerasController = new CarrerasController();
		    CarreraDisplayDTO carrera = carrerasController.getCarrerasById(idCarrera).get(0);
		    plazasRestantes = carrera.getPlazasRestantes();
		    if (plazasRestantes > 0) {
			// borramos los que no se pueda. los que no se pueden ni llegan a corredores
			if (plazasRestantes < corredores.size()) {
			    borrarCorredoresNecesarios(corredores.size() - plazasRestantes);
			}
			// Si entran todos saltamos aqui directamente

			// buscar por dni en corredores, si no esta se mete, si esta se hace update de
			// email por si acaso
			for (CorredorDTO corredor : corredores) {
			    checkCorredorRegistrado(corredor);
			    inscribirCorredor(corredor);
			}
			// Actualizamos las plazas
			carrera.setPlazasRestantes(plazasRestantes - inscripcionesNuevas);
			carrerasController.updatePlazasRestantesCarrera(carrera);
			JOptionPane.showMessageDialog(null,
				"Se han introducido con exito a " + inscripcionesNuevas
					+ " corredores.\nSe han actualizado" + " las inscripciones de "
					+ (corredores.size() - inscripcionesNuevas) + " corredores");
			corredorController.inicializarTablaCarrerasSinFiltro();
			view.dispose();

		    } else if (plazasRestantes <= 0 && isAlgunCorredorYaInscrito()) {
			int reinscritos = 0;
			for (CorredorDTO corredor : corredores) {
			    List<CorredorDTO> corredorEnBD = corredoresController
				    .getCorredorByDNI(corredor.getDniCorredor());
			    if (corredorEnBD.size() > 0) {
				if (!corredorEnBD.get(0).getEmail().equals(corredor.getEmail())) {
				    corredoresController.updateCorredor(corredor);

				}
				reinscritos += reinscribirCorredor(corredor);
			    }
			}
			JOptionPane.showMessageDialog(null,
				"Se han actualizado" + "las inscripciones de " + (reinscritos) + " corredores");
			corredorController.inicializarTablaCarrerasSinFiltro();
			view.dispose();
		    } else {
			JOptionPane.showMessageDialog(null,
				"No se ha podido inscribir a ningun corredor," + " la carrera esta llena");
			corredorController.inicializarTablaCarrerasSinFiltro();
			view.dispose();

		    }

		} else {
		    JOptionPane.showMessageDialog(null, "Introduce como mínimo un corredor");
		}
	    }
	};

    }

    private void checkCorredorRegistrado(CorredorDTO corredor) {
	List<CorredorDTO> corredorEnBD = corredoresController.getCorredorByDNI(corredor.getDniCorredor());
	if (corredorEnBD.size() <= 0) {
	    corredoresController.addCorredor(corredor);
	    // inscripcionesNuevas++;
	} else {
	    if (corredorEnBD.get(0).getEmail().equals(corredor.getEmail())) {
		// inscripcionesNuevas++;
		return;
	    } else {
		// Posibilidad de cambiar todo menos dni
		corredoresController.updateCorredor(corredor);

	    }
	}
    }

    private void inscribirCorredor(CorredorDTO corredor) {
	// Primero buscar por el dni y el id carrera si existe alguna
	// Si existe cambiamos: estado a Inscrito, fecha a la fecha de hoy
	// Incidencia devolver dinero y club
	List<InscripcionDTO> inscripcionBD = checkCorredorInscritoBD(corredor);
	InscripcionDTO inscripcionNueva = crearInscripcion(corredor.getDniCorredor());
	if (inscripcionBD.size() == 0) {
	    // Creamos la inscripcion
	    inscripcionesController.addInscripcion(inscripcionNueva);
	    inscripcionesNuevas++;
	} else {
	    // Update de la inscripcion
	    inscripcionNueva.setIncidencia("CAMBIO A CLUB " + inscripcionNueva.getClub());
	    inscripcionesController.updateInscripcion(inscripcionNueva);
	}
	// Si no existe se crea
    }

    private int reinscribirCorredor(CorredorDTO corredor) {
	// Primero buscar por el dni y el id carrera si existe alguna
	// Si existe cambiamos: estado a Inscrito, fecha a la fecha de hoy
	// Incidencia devolver dinero y club
	int reinscritos = 0;
	List<InscripcionDTO> inscripcionBD = checkCorredorInscritoBD(corredor);
	InscripcionDTO inscripcionNueva = crearInscripcion(corredor.getDniCorredor());
	if (inscripcionBD.size() > 0) {
	    // Update de la inscripcion
	    inscripcionNueva.setIncidencia("CAMBIO A CLUB " + inscripcionNueva.getClub());
	    inscripcionesController.updateInscripcion(inscripcionNueva);
	    reinscritos++;
	}
	return reinscritos;
    }

    private List<InscripcionDTO> checkCorredorInscritoBD(CorredorDTO corredor) {
	return inscripcionesController.findInscripcionByDNIAndIDCarrera(corredor.getDniCorredor(), idCarrera);

    }

    private boolean isAlgunCorredorYaInscrito() {
	for (CorredorDTO corredor : corredores) {
	    if (checkCorredorInscritoBD(corredor).size() > 0) {
		return true;
	    }
	}
	return false;
    }

    private InscripcionDTO crearInscripcion(String dniCorredor) {
	InscripcionDTO inscripcion = new InscripcionDTO();
	inscripcion.setEstadoinscripcion("INSCRITO");
	inscripcion.setDnicorredor(dniCorredor);
	inscripcion.setFechainscripcion(java.sql.Date.valueOf(LocalDate.now()));
	inscripcion.setIdcarrera(idCarrera);
	inscripcion.setClub(view.getTxClub().getText());
	// inscripcion.setIncidencia("DEVOLVER DINERO.CAMBIO A CLUB");
	return inscripcion;
    }

    private void borrarCorredoresNecesarios(int corredoresBorrar) {
	int i = 0;
	while (i < corredoresBorrar) {
	    corredores.remove(corredores.size() - 1);
	    i++;
	}

    }

    private void initCamposFormulario() {
	view.getTxNombre().setText("");
	view.getTxApellidos().setText("");
	view.getTxDNI().setText("");
	view.getTxEmail().setText("");
	view.getDateChooser().setDate(null);
    }

    private boolean validarDatosFormulario() {
	if (view.getTxClub().getText().isBlank()) {
	    view.getTxNombre().grabFocus();
	    return false;
	} else if (view.getTxNombre().getText().isBlank()) {
	    view.getTxNombre().grabFocus();
	    return false;
	} else if (view.getTxApellidos().getText().isBlank()) {
	    view.getTxApellidos().grabFocus();
	    return false;
	} else if (view.getTxEmail().getText().isBlank()) {
	    view.getTxEmail().grabFocus();
	    return false;
	} else if (view.getTxDNI().getText().isBlank()) {
	    view.getTxDNI().grabFocus();
	    return false;
	} else if (view.getDateChooser().getDate() == null) {
	    view.getDateChooser().grabFocus();
	    return false;
	} else if (isMenorDeEdad(new java.sql.Date(view.getDateChooser().getDate().getTime()))) {
	    JOptionPane.showMessageDialog(null, "El corredor es menor de edad");
	    return false;
	} else if (checkDNIAndEmailInTable(view.getTxEmail().getText(), view.getTxDNI().getText())) {
	    return false;
	} else
	    return true;
    }

    private boolean validarDatosFichero(CorredorDTO corredor) {
	if (view.getTxClub().getText().isBlank()) {
	    return false;
	} else if (corredor.getNombre().isBlank()) {
	    return false;
	} else if (corredor.getApellidos().isBlank()) {
	    return false;
	} else if (corredor.getEmail().isBlank()) {
	    return false;
	} else if (corredor.getDniCorredor().isBlank()) {
	    return false;
	} else if (corredor.getFechaNacimiento() == null) {
	    return false;
	} else if (isMenorDeEdad(corredor.getFechaNacimiento())) {
	    // JOptionPane.showMessageDialog(null, "El corredor es menor de edad");
	    return false;
	} else if (!corredor.getSexo().equals("Hombre") && !corredor.getSexo().equals("Mujer")) {
	    return false;
	} else if (checkDNIAndEmailInTable(corredor.getEmail(), corredor.getDniCorredor())) {
	    return false;
	} else
	    return true;
    }

    private boolean checkDNIAndEmailInTable(String email, String dni) {
	for (CorredorDTO corredor : corredores) {
	    if (corredor.getEmail().equals(email)) {
		JOptionPane.showMessageDialog(null, "Ya existe un corredor con ese Email");
		return true;
	    } else if (corredor.getDniCorredor().equals(dni)) {
		JOptionPane.showMessageDialog(null, "Ya existe un corredor con ese DNI");

		return true;
	    }

	}
	return false;
    }

    private boolean isMenorDeEdad(Date fechaNac) {

	LocalDate ahora = LocalDate.now();

	Period periodo = Period.between(fechaNac.toLocalDate(), ahora);
	if (periodo.getYears() < LIMITE_INFERIOR_EDAD) {
	    return true;
	} else {
	    return false;
	}
    }

    public void setMenuCorredor(MenuCorredorController menuCorredorController) {
	this.corredorController = menuCorredorController;
    }

}
