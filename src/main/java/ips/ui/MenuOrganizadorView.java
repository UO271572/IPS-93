package ips.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ips.business.carreras.CarreraDisplayDTO;

public class MenuOrganizadorView extends JDialog {
    private JScrollPane scrollPaneCorredores;
    private JList listCorredores;
    private JButton btnBuscarCorredores;
    private JComboBox<CarreraDisplayDTO> cbCarreras;
    private JButton btMostrarClasificacionSexo;
    private JButton btMostrarClasificacionCategoria;
    private JButton btnCrearCarrera;
    private JPanel pn_FiltrosClasificacion;
    private JPanel pn_OpcionesOrganizador;
    private JButton btnCargarDatos;
    private JButton btnProcesarPagos;
    private JButton btnAsignarDorsales;
    private JPanel pn_Carreras;
    private JScrollPane scrollPaneCarreras;
    private JList listCarreras;
    private JPanel pn_BotonesCarreras;
    private JButton btnGenerarClasificacion;
    private JButton btMostrarClasificacionSinFiltro;

    // ---
    private DefaultTableModel tablemodel;
    private JTable table;
    private JTabbedPane tabbedPane;
    private JTabbedPane tabbedPane_1;
    private JPanel pnFiltros;
    private JScrollPane scrollPane;
    private ProcesaAccion pa = new ProcesaAccion();
    private JPanel pn_listaCorredores;
    private JPanel pn_lista;

    public MenuOrganizadorView() {
	tablemodel = new DefaultTableModel();
	setTitle("Menú: Organizador");
	setResizable(false);
	setBounds(100, 100, 1063, 510);
	setLocationRelativeTo(null);
	getContentPane().setBackground(Color.WHITE);
	getContentPane().setLayout(null);
//	getContentPane().add(getScrollPaneCorredores());
//		getContentPane().add(getCbCarreras());
	getContentPane().add(getPn_FiltrosClasificacion());
	getContentPane().add(getPn_OpcionesOrganizador());
	getContentPane().add(getPn_Carreras());
	getContentPane().add(getPn_BotonesCarreras());

	iniciar();

	this.setModal(true);
    }

    private void iniciar() {
//	getContentPane().add(getPnFiltros());
//	getPnFiltros().setLayout(new BorderLayout(0, 0));
	getContentPane().add(getPn_listaCorredores());
//	getPnFiltros().add(getTabbedPane());
    }

    class ProcesaAccion implements ActionListener {

	public void actionPerformed(ActionEvent a) {
	    filtrar(a.getActionCommand());
	}

	private void filtrar(String actionCommand) {
	    // mostrar el panel y scroll y lista asociados al boton
	    getPn_listaCorredores().show();arreglar a jtabbed
	}

    }

    public void iniciarBotonesFiltros(int i, String nombre) {
	// getListCorredores().removeAll();
	getPn_FiltrosClasificacion().add(crearBotonesFiltro(i, nombre));
	getPn_listaCorredores().add(crearPanelesScroll(nombre));
    }

    public JButton crearBotonesFiltro(int i, String nombre) {
	JButton btBoton = new JButton();
	btBoton = new JButton(nombre);
	btBoton.setActionCommand(Integer.toString(i));
	btBoton.addActionListener(pa);
	btBoton.setEnabled(true);
	return btBoton;
    }

    private JPanel crearPanelesScroll(String nombre) {
	JPanel panel = new JPanel();
	panel.setName("pn" + nombre);
	panel.setLayout(new BorderLayout(0, 0));
	panel.add(crearScrollPane(nombre));
	return panel;
    }

    private Component crearScrollPane(String nombre) {
	JScrollPane scpanel = new JScrollPane();
	scpanel.setName("sc" + nombre);
	scpanel.setBounds(386, 254, 661, 125);
	scpanel.setViewportView(crearList(nombre));

	return scpanel;
    }

    private JList crearList(String nombre) {
	JList jlist = new JList();
	jlist.setName("jlist" + nombre);
	jlist.setBackground(Color.WHITE);
	return jlist;
    }

//    /**
//     * Añadir la tabla al panel que se necesite
//     * 
//     * @return
//     */
//    public JTable getTable() {
//	if (table == null) {
//	    table = new JTable(tablemodel);
//	    tablemodel.addColumn("Id Carrera");
//	    tablemodel.addColumn("Nombre");
//	    tablemodel.addColumn("Dorsal");
//	    tablemodel.addColumn("Categoria");
//	    tablemodel.addColumn("Tiempo");
//	    table.setDefaultEditor(Object.class, null);
//	    // table.setVisible(true);
//	}
//	return table;
//    }
//
//    /**
//     * Creacion dinamica de scrollpane
//     * 
//     * @return
//     */
//    public JScrollPane crearScrollPane() {
//	JScrollPane scp = new JScrollPane(getTabbedPane());
//	scp.setViewportView(getTable());
//	// scp.setVisible(true);
//	return scp;
//    }

//    /**
//     * tABED PANE SOBRE EL QUE SE PONDRAN LOS FILTROS
//     * 
//     * @return
//     */
//    public JTabbedPane getTabbedPane() {
//	if (tabbedPane == null) {
//	    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//	    tabbedPane.setBounds(386, 56, 543, 169);
//	    tabbedPane.addTab("New tab", null, crearScrollPane(), null);
////	    añadir();
////	tabbedPane.addTab("NOMBRE DEL FILTRO CATEGORIA ", null, crearScrollPane(), null);
////	    AÑADIR TABS CUANDO SEPAMOS CUALOS SON LOS FILTROS
//	}
//
//	return tabbedPane;
//    }

//    private void añadir() {
//	tabbedPane.addTab("New tab", null, crearScrollPane(), null);
//    }

    // ------------------------------------------------------------------------

    private JScrollPane getScrollPaneCorredores() {
	if (scrollPaneCorredores == null) {
	    scrollPaneCorredores = new JScrollPane();
	    scrollPaneCorredores.setBounds(386, 254, 661, 125);
	    scrollPaneCorredores.setViewportView(getListCorredores());
	}
	return scrollPaneCorredores;
    }

    public JList getListCorredores() {
	if (listCorredores == null) {
	    listCorredores = new JList();
	    listCorredores.setBackground(Color.WHITE);
	}
	return listCorredores;
    }

    public JButton getBtnBuscarCorredores() {
	if (btnBuscarCorredores == null) {
	    btnBuscarCorredores = new JButton("Ver inscripciones");
	}
	return btnBuscarCorredores;
    }

//	public JComboBox<CarreraDisplayDTO> getCbCarreras() {
//		if (cbCarreras == null) {
//			cbCarreras = new JComboBox<CarreraDisplayDTO>();
//			cbCarreras.setBounds(30, 20, 249, 26);
//		}
//		return cbCarreras;
//	}

    public JButton getBtMostrarClasificacionSexo() {
	if (btMostrarClasificacionSexo == null) {
	    btMostrarClasificacionSexo = new JButton("Por sexo");
	    btMostrarClasificacionSexo.setEnabled(false);
	}
	return btMostrarClasificacionSexo;
    }

    public JButton getBtMostrarClasificacionCategoria() {
	if (btMostrarClasificacionCategoria == null) {
	    btMostrarClasificacionCategoria = new JButton("Por categoría");
	    btMostrarClasificacionCategoria.setEnabled(false);
	}
	return btMostrarClasificacionCategoria;
    }

    public JButton getBtnCrearCarrera() {
	if (btnCrearCarrera == null) {
	    btnCrearCarrera = new JButton("Crear carrera");
	}
	return btnCrearCarrera;
    }

    public JPanel getPn_FiltrosClasificacion() {
	if (pn_FiltrosClasificacion == null) {
	    pn_FiltrosClasificacion = new JPanel();
	    pn_FiltrosClasificacion
		    .setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pn_FiltrosClasificacion.setBackground(Color.WHITE);
	    pn_FiltrosClasificacion.setBounds(386, 10, 661, 83);
	    pn_FiltrosClasificacion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    pn_FiltrosClasificacion.add(getBtMostrarClasificacionSinFiltro());
	    pn_FiltrosClasificacion.add(getBtMostrarClasificacionSexo());
	    pn_FiltrosClasificacion.add(getBtMostrarClasificacionCategoria());
	}
	return pn_FiltrosClasificacion;
    }

    private JPanel getPn_OpcionesOrganizador() {
	if (pn_OpcionesOrganizador == null) {
	    pn_OpcionesOrganizador = new JPanel();
	    pn_OpcionesOrganizador.setBounds(386, 389, 661, 68);
	    pn_OpcionesOrganizador.setLayout(new GridLayout(1, 0, 0, 0));
	    pn_OpcionesOrganizador.add(getBtnAsignarDorsales());
	    pn_OpcionesOrganizador.add(getBtnProcesarPagos());
	    pn_OpcionesOrganizador.add(getBtnCargarDatos());
	    pn_OpcionesOrganizador.add(getBtnCrearCarrera());
	}
	return pn_OpcionesOrganizador;
    }

    public JButton getBtnCargarDatos() {
	if (btnCargarDatos == null) {
	    btnCargarDatos = new JButton("Cargar datos");
	}
	return btnCargarDatos;
    }

    public JButton getBtnProcesarPagos() {
	if (btnProcesarPagos == null) {
	    btnProcesarPagos = new JButton("Procesar pagos");
	}
	return btnProcesarPagos;
    }

    public JButton getBtnAsignarDorsales() {
	if (btnAsignarDorsales == null) {
	    btnAsignarDorsales = new JButton("Asignar dorsales");
	}
	return btnAsignarDorsales;
    }

    private JPanel getPn_Carreras() {
	if (pn_Carreras == null) {
	    pn_Carreras = new JPanel();
	    pn_Carreras.setBackground(Color.WHITE);
	    pn_Carreras
		    .setBorder(new TitledBorder(null, "Carreras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pn_Carreras.setBounds(10, 10, 353, 372);
	    pn_Carreras.setLayout(new GridLayout(0, 1, 0, 0));
	    pn_Carreras.add(getScrollPaneCarreras());
	    // pn_Carreras.add(getListCarreras());
	}
	return pn_Carreras;
    }

    public JList<CarreraDisplayDTO> getListCarreras() {
	if (listCarreras == null) {
	    listCarreras = new JList<CarreraDisplayDTO>();
	}
	return listCarreras;
    }

    private JScrollPane getScrollPaneCarreras() {
	if (scrollPaneCarreras == null) {
	    scrollPaneCarreras = new JScrollPane();
	    scrollPaneCarreras.setViewportView(getListCarreras());
	}
	return scrollPaneCarreras;
    }

    private JPanel getPn_BotonesCarreras() {
	if (pn_BotonesCarreras == null) {
	    pn_BotonesCarreras = new JPanel();
	    pn_BotonesCarreras.setBackground(Color.WHITE);
	    pn_BotonesCarreras.setBounds(10, 389, 353, 68);
	    pn_BotonesCarreras.setLayout(new GridLayout(0, 2, 0, 0));
	    pn_BotonesCarreras.add(getBtnBuscarCorredores());
	    pn_BotonesCarreras.add(getBtnGenerarClasificacion());
	}
	return pn_BotonesCarreras;
    }

    public JButton getBtnGenerarClasificacion() {
	if (btnGenerarClasificacion == null) {
	    btnGenerarClasificacion = new JButton("Generar clasificaciones");
	}
	return btnGenerarClasificacion;
    }

    public JButton getBtMostrarClasificacionSinFiltro() {
	if (btMostrarClasificacionSinFiltro == null) {
	    btMostrarClasificacionSinFiltro = new JButton("Sin filtros");
	    btMostrarClasificacionSinFiltro.setEnabled(false);
	}
	return btMostrarClasificacionSinFiltro;
    }

    private JPanel getPnFiltros() {
	if (pnFiltros == null) {
	    pnFiltros = new JPanel();
	    pnFiltros.setBorder(new TitledBorder(null, "Filtrado por categorias", TitledBorder.LEADING,
		    TitledBorder.TOP, null, null));
	    pnFiltros.setBackground(Color.WHITE);
	    pnFiltros.setBounds(384, 78, 663, 57);
	}
	return pnFiltros;
    }

    private JPanel getPn_listaCorredores() {
	if (pn_listaCorredores == null) {
	    pn_listaCorredores = new JPanel();
	    pn_listaCorredores.setBounds(382, 161, 665, 217);
	    pn_listaCorredores.setLayout(new BorderLayout(0, 0));

	    pn_listaCorredores.add(getPn_lista());
	}
	return pn_listaCorredores;
    }

    private JPanel getPn_lista() {
	if (pn_lista == null) {
	    pn_lista = new JPanel();
	    pn_lista.setLayout(new BorderLayout(0, 0));
	    pn_lista.add(getScrollPaneCorredores());
	}
	return pn_lista;
    }
}
