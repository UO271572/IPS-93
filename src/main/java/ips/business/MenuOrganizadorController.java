package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.categorias.CategoriaDTO;
import ips.business.clasificaciones.ClasificacionController;
import ips.business.corredores.CorredorDTO;
import ips.business.corredores.CorredoresController;
import ips.business.inscripciones.InscripcionController;
import ips.business.inscripciones.InscripcionDTO;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.clasificaciones.ClasificacionModel;
import ips.persistence.corredores.CorredoresModel;
import ips.persistence.pagos.PagoTransferenciaBancariaModel;
import ips.ui.MenuCrearCarreraView;
import ips.ui.MenuOrganizadorView;
import ips.ui.carreras.CarrerasView;
import ips.ui.corredores.CorredoresView;
import ips.ui.inscripciones.DorsalesView;
import ips.util.Printer;

public class MenuOrganizadorController {

    private MenuOrganizadorView view;
    private CarrerasController cc = new CarrerasController(new CarrerasModel(), new CarrerasView());

    public MenuOrganizadorController(MenuOrganizadorView view) {
	this.view = view;

	initController();
    }

    public void initController() {
	view.addWindowListener(notCloseDirectly());

	inicializarTablaCarreras();
	inicializarComboBox();
	// view.getBtnOrganizador().addActionListener(accionBotonOrganizador());
	view.getBtnProcesarPagos().addActionListener(accionProcesaPagosCarrera());
	// CREA LAS CLASIFICACIONES
	view.getBtnGenerarClasificacion().addActionListener(accionGenerarClasificaciones());
//	view.getBtMostrarClasificacionSinFiltro().addActionListener(accionBotonClasificaSinFiltro());
	view.getBtnCargarDatos().addActionListener(accionCargarDatos());
	// cargarCarrerasEnTabla();

    }

    /**
     * Filtrado
     * 
     * @return
     */
    private ActionListener accionGenerarClasificaciones() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		// obtener las categorias de la carrera
		ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
		try {

		    int fila = view.getTablaCarreras().getSelectedRow();
		    int idCarrera = (int) view.getTablaCarreras().getModel().getValueAt(fila, 0);

		    List<CategoriaDTO> listaCat = controller.obtenerCategorias(idCarrera);

		    // en un for
		    // crear en ejecucion los filtros y asignarles a la misma vez sus listas
		    // filtradas

		    for (int i = 0; i < listaCat.size(); i++) {
			view.iniciarBotonesFiltros(i, listaCat.get(i).getNombre());
//			view.getTabbedPane().addTab(listaCat.get(i).getNombre(), null, view.crearScrollPane(), null);

//			// añadir el boton a un panel con scrollpane y un jlist
//			DefaultListModel<ClasificacionDTO> dm = new DefaultListModel<ClasificacionDTO>();
//			dm.addAll(controller.obtenerCategoriasFiltrado(listaCat.get(i).getIdCarrera(),
//				listaCat.get(i).getSexo(), listaCat.get(i).getEdadInicio(),
//				listaCat.get(i).getEdadFin()));
//			view.getListCorredores().setModel(dm);

		    }
		    // si decido hacerlo con botones crear para cada boton el listener para que
		    // segun el id del boton realizar el filtro necesario
		    // si es un Jlist vaciarla cada vez que se cambia de pestaña

		    // AL ACABAR CAMBIAR LOS DTO Q SE VAN A MOSTRAR SI CIERTAS COLUMNAS SON NULL
		} catch (BusinessException e1) {
		    e1.printStackTrace();
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}

	    }
	};
    }

    private ActionListener accionCargarDatos() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    cargarDatos();
		    JOptionPane.showMessageDialog(view, "¡Datos cargados con éxito!");
		} catch (BusinessException e1) {
		    e1.printStackTrace();
		} catch (ParseException e1) {
		    e1.printStackTrace();
		}
	    }
	};
    }

    private void cargarDatos() throws BusinessException, ParseException {
	// INICIALMENTE PARA LA CARRERA CON ID = 238
	String fileName = "tiempos_238.txt";
	File fichero = new File(fileName);
	int idCarrera = Integer.valueOf(fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf(".")));
	BufferedReader br;
	List<InscripcionDTO> inscripciones = new ArrayList<InscripcionDTO>();
	try {
	    br = new BufferedReader(new FileReader(fichero));
	    String linea;
	    while ((linea = br.readLine()) != null) {
		InscripcionDTO inscripcion = new InscripcionDTO();

		inscripcion.setIdcarrera(idCarrera);

		int i = linea.indexOf(";");
		inscripcion.setDorsal(Integer.valueOf(linea.substring(0, i)));

		int j = linea.indexOf("-");
		String s = linea.substring(i + 1, j);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		long ms = sdf.parse(s).getTime();
		Time t = new Time(ms);
		inscripcion.setTiempofin(t);

		i = linea.indexOf("%");
		s = linea.substring(j + 1, i);
		sdf = new SimpleDateFormat("hh:mm:ss");
		ms = sdf.parse(s).getTime();
		t = new Time(ms);
		inscripcion.setTiempofin(t);

		inscripciones.add(inscripcion);
	    }
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	InscripcionController ic = new InscripcionController();
	ic.updateInscripciones(inscripciones);
    }

    // Borrar cuando tablas funcionen
//    private void cargarCarrerasEnTabla() {
//	
//	CarrerasController carreraController = new CarrerasController(new CarrerasModel(), new CarrerasView());
//	List<CarreraDisplayDTO> listaCarreras = null;
//	try {
//	    listaCarreras = carreraController.getListaCarrerasTodas();
//	} catch (BusinessException e) {
//
//	    e.printStackTrace();
//	}
//
//	DefaultListModel modelo = new DefaultListModel();
//
//	for (CarreraDisplayDTO carrera : listaCarreras) {
//	    modelo.addElement(carrera);
//	}
//
//	view.getListCarreras().setModel(modelo);
//    }

    public WindowAdapter notCloseDirectly() {
	return new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		view.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    }
	};
    }

    private void inicializarComboBox() {
//		view.getCbOpciones().addItem("Ver estado de la competicion"); 
//		view.getCbOpciones().addItem("Ver estado de la clasificacion por sexo");  
//		view.getCbOpciones().addItem("Ver estado de la clasificacion por categoria");  
	// view.getBtnGo().addActionListener(accionBotonGo(view.getCbOpciones().getSelectedIndex()));
	view.getBtnBuscarCorredores().addActionListener(accionBotonBuscarCorredores());
//	view.getBtMostrarClasificacionCategoria().addActionListener(accionBotonClasificaPorCategoria());
//	view.getBtMostrarClasificacionSexo().addActionListener(accionBotonClasificaPorSexo());
	view.getBtnCrearCarrera().addActionListener(cambiarAVentanaCrearCarrera());
	view.getBtnAsignarDorsales().addActionListener(accionAsignarDorsales());
    }

    /**
     * Asigna a los corredores de cierta carrera que este en estado cerrado, los
     * dorsales a los inscritos en ella
     * 
     * @return
     */
    private ActionListener accionAsignarDorsales() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		// Al crear el constructor ya se realizan los 2 metodos de asignar dorsales y
		// mostrarlos
		DorsalesView dorsalview = new DorsalesView();
		MenuDorsalesController mdc = new MenuDorsalesController(dorsalview, view);

		dorsalview.setVisible(true);
	    }
	};
    }

//    // Acciones
//    private ActionListener accionBotonClasificaPorSexo() {
//	return new ActionListener() {
//
//	    @Override
//	    public void actionPerformed(ActionEvent e) {
//		ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
//		DefaultListModel<ClasificacionDTO> dlm = new DefaultListModel<ClasificacionDTO>();
//		try {
//		    // dlm.addAll(controller.mostrarResultadosPorSexo(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera()));
//		    // ADRI
//		    dlm.addAll(controller.mostrarResultadosPorSexo(
//			    ((CarreraDisplayDTO) view.getListCarreras().getSelectedValue()).getIdCarrera()));
//		    view.getListCorredores().setModel(dlm);
//		} catch (NumberFormatException e1) {
//		    e1.printStackTrace();
//		} catch (BusinessException e1) {
//		    e1.printStackTrace();
//		} catch (SQLException e1) {
//		    e1.printStackTrace();
//		}
//	    }
//	};
//
//    }
//
//    private ActionListener accionBotonClasificaPorCategoria() {
//	return new ActionListener() {
//
//	    @Override
//	    public void actionPerformed(ActionEvent e) {
//		ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
//		DefaultListModel<ClasificacionDTO> dlm = new DefaultListModel<ClasificacionDTO>();
//		try {
//		    // int index = view.getCbCarreras().getSelectedItem()
//		    // dlm.addAll(controller.mostrarResultadosPorCategoria(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera()));
//		    dlm.addAll(controller.mostrarResultadosPorCategoria(
//			    ((CarreraDisplayDTO) view.getListCarreras().getSelectedValue()).getIdCarrera()));
//		    view.getListCorredores().setModel(dlm);
//
//		} catch (NumberFormatException e1) {
//		    e1.printStackTrace();
//		} catch (BusinessException e1) {
//		    e1.printStackTrace();
//		} catch (SQLException e1) {
//		    e1.printStackTrace();
//		}
//	    }
//	};
//
//    }
//
//    private ActionListener accionBotonClasificaSinFiltro() {
//	return new ActionListener() {
//
//	    @Override
//	    public void actionPerformed(ActionEvent e) {
//		ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
//		DefaultListModel<ClasificacionDTO> dlm = new DefaultListModel<ClasificacionDTO>();
//		try {
//		    if (view.getListCarreras().getSelectedValue() != null) {
//			// int index = view.getCbCarreras().getSelectedItem()
//			// dlm.addAll(controller.mostrarResultadosPorCategoria(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera()));
//			dlm.addAll(controller.mostrarResultados(
//				((CarreraDisplayDTO) view.getListCarreras().getSelectedValue()).getIdCarrera()));
//			view.getListCorredores().setModel(dlm);
//
//			for (Component c : view.getPn_FiltrosClasificacion().getComponents()) {
//
//			    JButton boton = (JButton) c;
//
//			    boton.setEnabled(true);
//			}
//		    } else {
//			JOptionPane.showMessageDialog(null, "Debes seleccionar una carrera");
//		    }
//		} catch (NumberFormatException e1) {
//		    e1.printStackTrace();
//		} catch (BusinessException e1) {
//		    e1.printStackTrace();
//		} catch (SQLException e1) {
//		    e1.printStackTrace();
//		}
//	    }
//	};
//
//    }

    private ActionListener accionBotonBuscarCorredores() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		CorredoresController carreraController = new CorredoresController(new CorredoresModel(),
			new CorredoresView());
		view.getTableModelCorredor().setRowCount(0);
		int fila = view.getTablaCarreras().getSelectedRow();

		try {
		    if (fila != -1) { //
			int idCarrera = (int) view.getTablaCarreras().getModel().getValueAt(fila, 0);

			List<CorredorDTO> corredores = carreraController.getCorredoresByIdCarrera(idCarrera);

			for (int i = 0; i < corredores.size(); i++) {
			    String DNI = corredores.get(i).getDniCorredor();
			    String nombre = corredores.get(i).getNombre();
			    String categoria = corredores.get(i).getCategoria();
			    Date fecha_inscripcion = corredores.get(i).getFechaInscripcion();
			    String estado_inscripcion = corredores.get(i).getEstadoInscripcion();
			    Object[] data = { DNI, nombre, categoria, fecha_inscripcion, estado_inscripcion };
			    view.getTableModelCorredor().insertRow(0, data);
			}

			// DefaultListModel<CorredorDTO> dlm = new DefaultListModel<CorredorDTO>();
			// dlm.addAll(carreraController.getCorredoresByIdCarrera(((CarreraDisplayDTO)(view.getCbCarreras().getSelectedItem())).getIdCarrera()));
			// dlm.addAll(carreraController.getCorredoresByIdCarrera(idCarrera));
			// view.getListCorredores().setModel(dlm);
		    } else {
			JOptionPane.showMessageDialog(null, "Debes seleccionar una carrera");
		    }
		} catch (BusinessException e1) {
		    Printer.printBusinessException(e1);
		}
	    }
	};
    }

    private ActionListener accionProcesaPagosCarrera() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		InscripcionController inscripcionController = new InscripcionController(
			new PagoTransferenciaBancariaModel());

		int fila = view.getTablaCarreras().getSelectedRow();

		if (fila != -1) {
		    int idCarrera = (int) view.getTablaCarreras().getModel().getValueAt(fila, 0);
		    int[] res = inscripcionController.comprobarPagosDeCarrera(idCarrera);

		    String resultado = "Inscripciones procesadas: " + (res[0] + res[1]) + "\n" + "Válidas : " + res[0]
			    + "\n" + "No válidas: " + res[1];

		    JOptionPane.showMessageDialog(view, resultado);
		} else {
		    JOptionPane.showMessageDialog(view, "Hay que seleccionar una carrera");
		}
	    }
	};
    }

    private ActionListener cambiarAVentanaCrearCarrera() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuCrearCarreraView crearCarrera = new MenuCrearCarreraView();
		MenuCrearCarreraController crearCarreraController = new MenuCrearCarreraController(crearCarrera);
		crearCarrera.setVisible(true);
	    }
	};
    }

    private void inicializarTablaCarreras() {
	vaciarTabla(view.getTableModelCarreras());
	try {
	    List<CarreraDisplayDTO> listaCarreras = cc.getListaCarrerasTodas();
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
	    int plazasDisponibles = listaCarreras.get(i).getPlazasTotales();
	    double distancia = listaCarreras.get(i).getDistancia();
	    String lugar = listaCarreras.get(i).getLugar();
	    Object[] data = { idCarerra, nombre, fecha, tipo, lugar, distancia, plazasDisponibles };
	    view.getTableModelCarreras().insertRow(0, data);
	}

    }

    private void vaciarTabla(DefaultTableModel tableModelCarreras) {
	tableModelCarreras.setRowCount(0);
    }

}
