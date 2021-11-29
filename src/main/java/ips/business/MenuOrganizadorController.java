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
import ips.business.resumenfinanciero.ResumenFinancieroController;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.clasificaciones.ClasificacionModel;
import ips.persistence.corredores.CorredoresModel;
import ips.persistence.pagos.PagoTransferenciaBancariaModel;
import ips.ui.MenuCrearCarreraView;
import ips.ui.MenuOrganizadorView;
import ips.ui.carreras.CarrerasView;
import ips.ui.corredores.CorredoresView;
import ips.ui.inscripciones.DorsalesView;
import ips.ui.resumenfinanciero.ResumenFinancieroView;
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
	// view.getBtnGenerarClasificacion().addActionListener(accionGenerarClasificaciones());
//	view.getBtMostrarClasificacionSinFiltro().addActionListener(accionBotonClasificaSinFiltro());
	view.getBtnCargarDatos().addActionListener(accionCargarDatos());
	view.getBtnResumenFinanciero().addActionListener(accionAbrirResumenFinanciero());
	// cargarCarrerasEnTabla();

    }

    /**
     * Filtrado
     * 
     * @return
     *//*
        * private ActionListener accionGenerarClasificaciones() { return new
        * ActionListener() {
        * 
        * @Override public void actionPerformed(ActionEvent e) { // obtener las
        * categorias de la carrera ClasificacionController controller = new
        * ClasificacionController(new ClasificacionModel()); try {
        * 
        * int fila = view.getTablaCarreras().getSelectedRow(); int idCarrera = (int)
        * view.getTablaCarreras().getModel().getValueAt(fila, 0);
        * 
        * List<CategoriaDTO> listaCat = controller.obtenerCategorias(idCarrera);
        * 
        * // en un for // crear en ejecucion los filtros y asignarles a la misma vez
        * sus listas // filtradas
        * 
        * for (int i = 0; i < listaCat.size(); i++) { view.iniciarBotonesFiltros(i,
        * listaCat.get(i).getNombre()); //
        * view.getTabbedPane().addTab(listaCat.get(i).getNombre(), null,
        * view.crearScrollPane(), null);
        * 
        * // // añadir el boton a un panel con scrollpane y un jlist //
        * DefaultListModel<ClasificacionDTO> dm = new
        * DefaultListModel<ClasificacionDTO>(); //
        * dm.addAll(controller.obtenerCategoriasFiltrado(listaCat.get(i).getIdCarrera()
        * , // listaCat.get(i).getSexo(), listaCat.get(i).getEdadInicio(), //
        * listaCat.get(i).getEdadFin())); // view.getListCorredores().setModel(dm);
        * 
        * } // si decido hacerlo con botones crear para cada boton el listener para que
        * // segun el id del boton realizar el filtro necesario // si es un Jlist
        * vaciarla cada vez que se cambia de pestaña
        * 
        * // AL ACABAR CAMBIAR LOS DTO Q SE VAN A MOSTRAR SI CIERTAS COLUMNAS SON NULL
        * } catch (BusinessException e1) { e1.printStackTrace(); } catch (SQLException
        * e1) { e1.printStackTrace(); }
        * 
        * } }; }
        */

    private ActionListener accionCargarDatos() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    cargarDatos();
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

		i++;
		String s = linea.substring(i, i + 8);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		long ms = sdf.parse(s).getTime();
		Time t = new Time(ms);
		inscripcion.setTiempoinicio(t);
		i += 9;

		int count = 1;
		while (true) {
		    s = linea.substring(i, i + 8);
		    sdf = new SimpleDateFormat("hh:mm:ss");
		    ms = sdf.parse(s).getTime();
		    t = new Time(ms);
		    if (linea.indexOf("%") == i + 8) {
			inscripcion.setTiempofin(t);
			break;
		    }
		    if (count == 1)
			inscripcion.setT1(t);
		    if (count == 2)
			inscripcion.setT2(t);
		    if (count == 3)
			inscripcion.setT3(t);
		    if (count == 4)
			inscripcion.setT4(t);
		    if (count == 5)
			inscripcion.setT5(t);
		    count++;
		    i += 9;
		}

		inscripciones.add(inscripcion);
	    }
	    br.close();
	} catch (IOException e) {
	    Printer.printBusinessException(e);
	}

	String mensaje = "¡Tiempos de la carrera con id=" + idCarrera + " cargados con éxito!";
	JOptionPane.showMessageDialog(view, mensaje);

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
		// quitar los datos de todas las tablas por si hubiese alguno
		view.getModelTablaClub().setRowCount(0);
		view.getModelTablaRitmo().setRowCount(0);
		view.getModelTablaDiferencia().setRowCount(0);
		view.getModelTablaDorsal().setRowCount(0);
		view.getModelTablaParciales().setRowCount(0);
		// generar las clasificaciones que tuviese la carrera
		view.getModelTablaCategorias().setRowCount(0);
		mostrarClasificaciones();

		// generar datos de los corredores
		CorredoresController corredoresController = new CorredoresController(new CorredoresModel(),
			new CorredoresView());
		InscripcionController inscController = new InscripcionController();

		view.getTableModelCorredor().setRowCount(0);
		int fila = view.getTablaCarreras().getSelectedRow();

		try {
		    if (fila != -1) { //
			int idCarrera = (int) view.getTablaCarreras().getModel().getValueAt(fila, 0);

			List<CorredorDTO> corredores = corredoresController.getCorredoresByIdCarrera(idCarrera);
			List<InscripcionDTO> inscripciones = inscController.listInscripcionesByTime(idCarrera);

			int posicion = 0;
			for (InscripcionDTO inscripcion : inscripciones) {
			    CorredorDTO corredordto = null;
			    for (CorredorDTO corredor : corredores) {
				if (inscripcion.getDnicorredor().equals(corredor.getDniCorredor())) {
				    corredordto = corredor;
				    break;
				}
			    }
			    String DNI = corredordto.getDniCorredor();
			    String nombre = corredordto.getNombre();
			    String sexo = corredordto.getSexo();
			    String categoria = corredordto.getCategoria();

			    Date fecha_inscripcion = inscripcion.getFechainscripcion();
			    String estado_inscripcion = inscripcion.getEstadoinscripcion();
			    String club = "----";
			    if (inscripcion.getClub() != null) {
				club = inscripcion.getClub();
			    }

			    // obtenemos la carrera seleccionada para obtener su distancia
			    CarrerasController carrerasController = new CarrerasController();
			    CarreraDisplayDTO carrera = carrerasController.getCarrerasById(idCarrera).get(0);

			    String ritmo = calcularRitmo(inscripcion, carrera.getDistancia());
			    String diferencial = "-----";
			    if (inscripciones.get(0).equals(inscripcion)) {
				diferencial = "+00:00:00";
			    } else {
				diferencial = calcularDiferenciaTiempo(inscripciones, inscripcion);
			    }

			    String t1 = "----";
			    String t2 = "----";
			    String t3 = "----";
			    String t4 = "----";
			    String t5 = "----";
			    if (inscripcion.getT1() != null) {
				t1 = inscripcion.getT1().toString();
			    }
			    if (inscripcion.getT2() != null) {
				t2 = inscripcion.getT2().toString();
			    }
			    if (inscripcion.getT3() != null) {
				t3 = inscripcion.getT3().toString();
			    }
			    if (inscripcion.getT4() != null) {
				t4 = inscripcion.getT4().toString();
			    }
			    if (inscripcion.getT5() != null) {
				t5 = inscripcion.getT5().toString();
			    }

			    Object[] data = { DNI, nombre, sexo, categoria, fecha_inscripcion, estado_inscripcion, club,
				    ritmo, diferencial, t1, t2, t3, t4, t5 };
			    // aÃ±adimos los datos a cada filtro
			    dataFiltroCorredores(posicion, data);
			    dataFiltroClub(posicion, data);
			    dataFiltroRitmo(posicion, data, calcularRitmo(inscripcion, carrera.getDistancia()));
			    dataFiltroDiferencia(posicion, data, calcularDiferenciaTiempo(inscripciones, inscripcion));
			    dataFiltroDorsal(posicion, data, inscripcion.getDorsal());
			    dataFiltroTiemposParciales(posicion, data);
			    posicion++;
			}
		    } else {
			JOptionPane.showMessageDialog(null, "Debes seleccionar una carrera");
		    }
		} catch (BusinessException e1) {
		    Printer.printBusinessException(e1);
		}
	    }
	};
    }

    /**
     * Muestra las clasificaciones de la carrera
     */
    private void mostrarClasificaciones() {
	ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
	try {
	    int fila = view.getTablaCarreras().getSelectedRow();
	    int idCarrera = (int) view.getTablaCarreras().getModel().getValueAt(fila, 0);

	    List<CategoriaDTO> listaCat = controller.obtenerCategorias(idCarrera);

	    for (CategoriaDTO categoria : listaCat) {
		String nombre = categoria.getNombre();
		int inicio = categoria.getEdadInicio();
		int fin = categoria.getEdadFin();
		String sexo = categoria.getSexo();
		Object[] data = { nombre, sexo, inicio, fin };
		view.getModelTablaCategorias().insertRow(0, data);
	    }
	} catch (BusinessException e1) {
	    e1.printStackTrace();
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
    }

    /**
     * Inserta en el modelo de datos los datos or parametro para el filtro
     * 
     * @param posicion
     * @param data
     */
    private void dataFiltroCorredores(int posicion, Object[] data) {
	view.getTableModelCorredor().insertRow(posicion, data);
    }

    /**
     * Inserta en el modelo de datos los datos or parametro para el filtro
     * 
     * @param posicion
     * @param data
     */
    private void dataFiltroClub(int posicion, Object[] data) {
	Object[] data2 = { data[0], data[1], data[6] };
	view.getModelTablaClub().insertRow(posicion, data2);
    }

    /**
     * Inserta en el modelo de datos los datos or parametro para el filtro
     * 
     * @param posicion
     * @param data
     */
    private void dataFiltroTiemposParciales(int posicion, Object[] data) {
	Object[] data2 = { data[0], data[1], data[9], data[10], data[11], data[12], data[13] };
	view.getModelTablaParciales().insertRow(posicion, data2);
    }

    /**
     * Inserta en el modelo de datos los datos or parametro para el filtro
     * 
     * @param posicion
     * @param data
     */
    private void dataFiltroDorsal(int posicion, Object[] data, int dorsal) {
	Object[] data2 = { data[0], data[1], dorsal };
	view.getModelTablaDorsal().insertRow(posicion, data2);
    }

    /**
     * Inserta en el modelo de datos los datos or parametro para el filtro
     * 
     * @param posicion
     * @param data
     */
    private void dataFiltroDiferencia(int posicion, Object[] data, String diferencia) {
	Object[] data2 = { data[0], data[1], diferencia };
	view.getModelTablaDiferencia().insertRow(posicion, data2);
    }

    /**
     * Inserta en el modelo de datos los datos or parametro para el filtro
     * 
     * @param posicion
     * @param data
     */
    private void dataFiltroRitmo(int posicion, Object[] data, String ritmo) {
	Object[] data2 = { data[0], data[1], ritmo };
	view.getModelTablaRitmo().insertRow(posicion, data2);
    }

    /**
     * Calcula el ritmo medio al que corrio el participante
     * 
     * @param inscripcion
     * @param distancia
     * @return
     */
    @SuppressWarnings("deprecation")
    private String calcularRitmo(InscripcionDTO inscripcion, double distancia) {
	if (inscripcion.getTiempofin() == null || inscripcion.getTiempoinicio() == null) {
	    return "----";
	}
	Time cronometraje = restarTiempo(inscripcion.getTiempofin(), inscripcion.getTiempoinicio());
	double h = cronometraje.getHours();
	double m = cronometraje.getMinutes() / 60.0;
	double s = (cronometraje.getSeconds() / 60.0) / 60.0;
//	double horas = h + (m / 60) + ((s / 60) / 60);
	double horas = h + m + s;
//	return String.valueOf(distancia / horas);
	return String.format("%.2f", (distancia / horas)) + " km/h";
    }

    /**
     * Calcula la diferencia de tiempos entre las dos inscripciones
     * 
     * @param inscripciones
     * @param inscripcion
     * @return
     */
    private String calcularDiferenciaTiempo(List<InscripcionDTO> inscripciones, InscripcionDTO inscripcion) {
	if (inscripcion.getTiempofin() == null || inscripcion.getTiempoinicio() == null
		|| inscripciones.get(0).getTiempofin() == null || inscripciones.get(0).getTiempoinicio() == null) {
	    return "----";
	}
	StringBuilder str = new StringBuilder();
	Time primerofin = inscripciones.get(0).getTiempofin();
	Time primeroinic = inscripciones.get(0).getTiempoinicio();
	Time t1 = restarTiempo(primerofin, primeroinic);

	Time segundofin = inscripcion.getTiempofin();
	Time segundoinic = inscripcion.getTiempoinicio();
	Time t2 = restarTiempo(segundofin, segundoinic);

	str.append(restarTiempo(t2, t1));
	return "+" + str.toString();
    }

    /**
     * Resta dos tiempos y lo devuelve en un nuevo objeto Time
     * 
     * @param primerofin
     * @param primeroinic
     * @return
     */
    @SuppressWarnings("deprecation")
    private Time restarTiempo(Time primerofin, Time primeroinic) {
	int h = primerofin.getHours() - primeroinic.getHours();
	int m = primerofin.getMinutes() - primeroinic.getMinutes();
	int s = primerofin.getSeconds() - primeroinic.getSeconds();
	return new Time(h, m, s);
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
	    int plazasDisponibles = listaCarreras.get(i).getPlazasRestantes();
	    double distancia = listaCarreras.get(i).getDistancia();
	    String lugar = listaCarreras.get(i).getLugar();
	    Object[] data = { idCarerra, nombre, fecha, tipo, lugar, distancia, plazasDisponibles };
	    view.getTableModelCarreras().insertRow(0, data);
	}

    }

    private void vaciarTabla(DefaultTableModel tableModelCarreras) {
	tableModelCarreras.setRowCount(0);
    }

    private ActionListener accionAbrirResumenFinanciero() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		int fila = view.getTablaCarreras().getSelectedRow();

		if (fila != -1) {
		    int idCarrera = (int) view.getTablaCarreras().getModel().getValueAt(fila, 0);
		    String nombreCarrera = (String) view.getTablaCarreras().getModel().getValueAt(fila, 1);

		    ResumenFinancieroView resumenView = new ResumenFinancieroView();
		    ResumenFinancieroController resumenFinancieroController = new ResumenFinancieroController(
			    resumenView, idCarrera, nombreCarrera);
		    resumenView.setVisible(true);
		} else {
		    JOptionPane.showMessageDialog(view, "Hay que seleccionar una carrera");
		}
	    }
	};
    }

}
