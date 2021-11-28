package ips.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MenuOrganizadorView extends JDialog {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JButton btnBuscarCorredores;
    private JButton btnCrearCarrera;
    private JPanel pn_OpcionesOrganizador;
    private JButton btnCargarDatos;
    private JButton btnProcesarPagos;
    private JButton btnAsignarDorsales;
    private JPanel pn_Carreras;
    private JScrollPane scrollPaneCarreras;
    private JPanel pn_BotonesCarreras;
    private JTable tablaCarreras;

    private DefaultTableModel modelTablaCarreras = new DefaultTableModel();
    private DefaultTableModel modelTablaCorredores = new DefaultTableModel();
    private DefaultTableModel modelTablaCategorias = new DefaultTableModel();
    private DefaultTableModel modelTablaClub = new DefaultTableModel();
    private DefaultTableModel modelTablaRitmo = new DefaultTableModel();
    private DefaultTableModel modelTablaDiferencia = new DefaultTableModel();
    private DefaultTableModel modelTablaDorsal = new DefaultTableModel();
    private DefaultTableModel modelTablaParciales = new DefaultTableModel();

    private JTable tableCorredores;
    private JTabbedPane tabbedPane;
    private JScrollPane scCorredores;
    private JScrollPane scCategorias;
    private JScrollPane scClub;
    private JScrollPane scRitmo;
    private JTable tRitmo;
    private JTable tableCategorias;
    private JTable tClub;
    private JPanel panel;
    private JPanel pnsur;
    private JPanel pnCentro;
    private JScrollPane scDiferencia;
    private JScrollPane scDorsal;
    private JTable tDiferencia;
    private JTable tDorsal;
    private JScrollPane scParciales;
    private JTable tParciales;
    private JTable table_2;
    private JTable table_3;
    private JButton btnResumenFinanciero;

    public MenuOrganizadorView() {
	setResizable(false);
	new DefaultTableModel();
	setTitle("Menú: Organizador");
	setBounds(100, 100, 1288, 666);
	setLocationRelativeTo(null);
	getContentPane().setBackground(Color.WHITE);
//	getContentPane().add(getPn_OpcionesOrganizador());
//	getContentPane().add(getPn_BotonesCarreras());
//	getContentPane().add(getPn_Carreras());
	// getContentPane().add(getTabbedPane());
	iniciar();

	this.setModal(true);
    }

    private void iniciar() {
	getContentPane().setLayout(new BorderLayout(0, 0));
//	getContentPane().add(getPnFiltros());
//	getPnFiltros().setLayout(new BorderLayout(0, 0));
	// getContentPane().add(getPn_listaCorredores());
	getContentPane().add(getPanel());
//	getPnFiltros().add(getTabbedPane());
    }

    // ------------------------------------------------------------------------

//    public JList getListCorredores() {
//	if (listCorredores == null) {
//	    listCorredores = new JList();
//	    listCorredores.setBackground(Color.WHITE);
//	}
//	return listCorredores;
//    }

    public JButton getBtnBuscarCorredores() {
	if (btnBuscarCorredores == null) {
	    btnBuscarCorredores = new JButton("Ver inscripciones");
	}
	return btnBuscarCorredores;
    }

    public JButton getBtnCrearCarrera() {
	if (btnCrearCarrera == null) {
	    btnCrearCarrera = new JButton("Crear carrera");
	}
	return btnCrearCarrera;
    }

    private JPanel getPn_OpcionesOrganizador() {
	if (pn_OpcionesOrganizador == null) {
	    pn_OpcionesOrganizador = new JPanel();
	    pn_OpcionesOrganizador.setBounds(639, 551, 625, 68);
	    pn_OpcionesOrganizador.setLayout(new GridLayout(1, 0, 0, 0));
	    pn_OpcionesOrganizador.add(getBtnResumenFinanciero());
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
	    pn_Carreras.setAlignmentX(Component.RIGHT_ALIGNMENT);
	    pn_Carreras.setBackground(Color.WHITE);
	    pn_Carreras
		    .setBorder(new TitledBorder(null, "Carreras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pn_Carreras.setBounds(10, 10, 619, 243);
	    pn_Carreras.setLayout(new BoxLayout(pn_Carreras, BoxLayout.X_AXIS));
	    pn_Carreras.add(getScrollPaneCarreras());
	    // pn_Carreras.add(getListCarreras());
	}
	return pn_Carreras;
    }

    private JScrollPane getScrollPaneCarreras() {
	if (scrollPaneCarreras == null) {
	    scrollPaneCarreras = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//	    scrollPaneCarreras = new JScrollPane();
	    scrollPaneCarreras.setViewportView(getTablaCarreras());
	    scrollPaneCarreras.setAlignmentX(Component.RIGHT_ALIGNMENT);

	}
	return scrollPaneCarreras;
    }

    private JPanel getPn_BotonesCarreras() {
	if (pn_BotonesCarreras == null) {
	    pn_BotonesCarreras = new JPanel();
	    pn_BotonesCarreras.setBackground(Color.WHITE);
	    pn_BotonesCarreras.setBounds(10, 551, 619, 68);
	    pn_BotonesCarreras.setLayout(new BorderLayout(0, 0));
	    pn_BotonesCarreras.add(getBtnBuscarCorredores());
//	    pn_BotonesCarreras.add(getBtnVerClasificacion());
	}
	return pn_BotonesCarreras;
    }

//    public JButton getBtnVerClasificacion() {
//	if (btnVerClasificacion == null) {
//	    btnVerClasificacion = new JButton("Ver clasificaciones");
//	}
//	return btnVerClasificacion;
//    }

//    private JPanel getPn_listaCorredores() {
//	if (pn_listaCorredores == null) {
//	    pn_listaCorredores = new JPanel();
//	    pn_listaCorredores.setBackground(Color.WHITE);
//	    pn_listaCorredores.setBorder(
//		    new TitledBorder(null, "Datos de corredores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//	    pn_listaCorredores.setBounds(639, 179, 625, 361);
//	    pn_listaCorredores.setLayout(new GridLayout(0, 1, 0, 0));
//	    pn_listaCorredores.add(getScrollPaneCorre());
//	}
//	return pn_listaCorredores;
//    }

    /*
     * private JPanel getPn_lista() { if (pn_lista == null) { pn_lista = new
     * JPanel(); pn_lista.setLayout(new BorderLayout(0, 0));
     * pn_lista.add(getScrollPaneCorredores()); } return pn_lista; }
     */

    public JTable getTablaCarreras() {
	if (tablaCarreras == null) {

	    tablaCarreras = new JTable(modelTablaCarreras);
	    tablaCarreras.setFillsViewportHeight(true);

	    // PARA CENTRAR
	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablaCarreras.getTableHeader()
		    .getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    // PARA CENTRAR

	    modelTablaCarreras.addColumn("ID Carrera");
	    modelTablaCarreras.addColumn("Nombre");
	    modelTablaCarreras.addColumn("Fecha");
	    modelTablaCarreras.addColumn("Tipo");
	    modelTablaCarreras.addColumn("Lugar");
	    modelTablaCarreras.addColumn("Distancia");

	    String nombre = "Plazas disponibles";
	    modelTablaCarreras.addColumn(nombre);

	    tablaCarreras.getColumn(nombre).setPreferredWidth(nombre.length() * 7);
	    tablaCarreras.getColumn("Fecha").setPreferredWidth("Fecha".length() * 18);
	    tablaCarreras.getColumn("Nombre").setPreferredWidth("Nombre".length() * 20);

	    tablaCarreras.setDefaultEditor(Object.class, null);

//	    tablaCarreras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//	    tablaCarreras.getColumn(0).setPreferredWidth(50);
//	    tablaCarreras.getColumn(1).setPreferredWidth(50);
//	    tablaCarreras.getColumn(2).setPreferredWidth(50);
//	    tablaCarreras.getColumn(3).setPreferredWidth(50);
//	    tablaCarreras.getColumn(4).setPreferredWidth(50);
//	    tablaCarreras.getColumn(5).setPreferredWidth(50);
//	    tablaCarreras.setAutoscrolls(true);
	}
	return tablaCarreras;
    }

    public DefaultTableModel getTableModelCarreras() {
	return modelTablaCarreras;
    }

    public DefaultTableModel getTableModelCorredor() {
	return modelTablaCorredores;
    }

    public JTable getTableCorredores() {
	if (tableCorredores == null) {
	    tableCorredores = new JTable(modelTablaCorredores);
	    tableCorredores.setFillsViewportHeight(true);

	    // PARA CENTRAR
	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableCorredores.getTableHeader()
		    .getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    // PARA CENTRAR

	    modelTablaCorredores.addColumn("DNI");
	    modelTablaCorredores.addColumn("Nombre");
	    modelTablaCorredores.addColumn("Sexo");
	    modelTablaCorredores.addColumn("Categoria");
	    modelTablaCorredores.addColumn("Fecha Insc");
	    modelTablaCorredores.addColumn("Estado");
	    modelTablaCorredores.addColumn("Club");
	    modelTablaCorredores.addColumn("Ritmo");
	    modelTablaCorredores.addColumn("Diferencia");
	    modelTablaCorredores.addColumn("Parcial 1");
	    modelTablaCorredores.addColumn("Parcial 2");
	    modelTablaCorredores.addColumn("Parcial 3");
	    modelTablaCorredores.addColumn("Parcial 4");
	    modelTablaCorredores.addColumn("Parcial 5");

	    tableCorredores.setDefaultEditor(Object.class, null);
	    tableCorredores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	return tableCorredores;
    }

//    private JScrollPane getScrollPaneCorre() {
//	if (scrollPaneCorre == null) {
//	    scrollPaneCorre = new JScrollPane();
//	    scrollPaneCorre.setViewportView(getTableCorredores());
//	}
//	return scrollPaneCorre;
//    }

    private JTabbedPane getTabbedPane() {
	if (tabbedPane == null) {
	    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	    tabbedPane.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Contenidos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    tabbedPane.setBounds(639, 11, 625, 529);
	    tabbedPane.addTab("Corredores", null, getScCorredores(), null);
	    tabbedPane.addTab("Categorias", null, getScCategorias(), null);
	    tabbedPane.addTab("Club", null, getScClub(), null);
	    tabbedPane.addTab("Ritmo", null, getScRitmo(), null);
	    tabbedPane.addTab("Diferencia", null, getScDiferencia(), null);
	    tabbedPane.addTab("Dorsal", null, getScDorsal(), null);
	    tabbedPane.addTab("Tiempos parciales", null, getScParciales(), null);

	}
	return tabbedPane;
    }

    private JScrollPane getScCorredores() {
	if (scCorredores == null) {
//	    scCorredores = new JScrollPane();
	    scCorredores = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	    scCorredores.setViewportView(getTableCorredores());
	    scCorredores.setAlignmentX(Component.RIGHT_ALIGNMENT);
//	    scCorredores.setViewportView(getTableCorredores());
	}
	return scCorredores;
    }

    private JScrollPane getScCategorias() {
	if (scCategorias == null) {
	    scCategorias = new JScrollPane();
	    scCategorias.setViewportView(getTableCategorias());
	}
	return scCategorias;
    }

    private JScrollPane getScClub() {
	if (scClub == null) {
	    scClub = new JScrollPane();
	    scClub.setViewportView(getTClub());
	}
	return scClub;
    }

    private JScrollPane getScRitmo() {
	if (scRitmo == null) {
	    scRitmo = new JScrollPane();
	    scRitmo.setViewportView(getTRitmo());
	}
	return scRitmo;
    }

    private JTable getTRitmo() {
	if (tRitmo == null) {
	    tRitmo = new JTable(modelTablaRitmo);
	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tRitmo.getTableHeader().getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    modelTablaRitmo.addColumn("Identificador");
	    modelTablaRitmo.addColumn("Corredor");
	    modelTablaRitmo.addColumn("Ritmo km/h");
	}
	return tRitmo;
    }

    /**
     * Tabla para las categorias
     * 
     * @return
     */
    public JTable getTableCategorias() {
	if (tableCategorias == null) {
	    tableCategorias = new JTable(modelTablaCategorias);
	    tableCategorias.setFillsViewportHeight(true);

	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableCategorias.getTableHeader()
		    .getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);

	    modelTablaCategorias.addColumn("Nombre");
	    modelTablaCategorias.addColumn("Sexo");
	    modelTablaCategorias.addColumn("Edad Inicio");
	    modelTablaCategorias.addColumn("Edad Fin");

	}
	return tableCategorias;
    }

    private JTable getTClub() {
	if (tClub == null) {
	    tClub = new JTable(modelTablaClub);

	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tClub.getTableHeader().getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    modelTablaClub.addColumn("Identificador");
	    modelTablaClub.addColumn("Corredor");
	    modelTablaClub.addColumn("Club perteneciente");
	}
	return tClub;
    }

    public DefaultTableModel getModelTablaCategorias() {
	return modelTablaCategorias;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public DefaultTableModel getModelTablaCarreras() {
	return modelTablaCarreras;
    }

    public DefaultTableModel getModelTablaCorredores() {
	return modelTablaCorredores;
    }

    public DefaultTableModel getModelTablaClub() {
	return modelTablaClub;
    }

    public DefaultTableModel getModelTablaRitmo() {
	return modelTablaRitmo;
    }

    public DefaultTableModel getModelTablaDiferencia() {
	return modelTablaDiferencia;
    }

    public DefaultTableModel getModelTablaDorsal() {
	return modelTablaDorsal;
    }

    public DefaultTableModel getModelTablaParciales() {
	return modelTablaParciales;
    }

    public JTable gettRitmo() {
	return tRitmo;
    }

    public JTable gettClub() {
	return tClub;
    }

    public JTable gettDiferencia() {
	return tDiferencia;
    }

    public JTable gettDorsal() {
	return tDorsal;
    }

    public JTable gettParciales() {
	return tParciales;
    }

    public void setModelTablaCategorias(DefaultTableModel modelTablaCategorias) {
	this.modelTablaCategorias = modelTablaCategorias;
    }

   
    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setLayout(new BorderLayout(0, 0));
	    panel.add(getPnsur(), BorderLayout.SOUTH);
	    panel.add(getPnCentro(), BorderLayout.CENTER);
	}
	return panel;
    }

    private JPanel getPnsur() {
	if (pnsur == null) {
	    pnsur = new JPanel();
	    pnsur.setLayout(new GridLayout(1, 0, 0, 0));
	    pnsur.add(getPn_BotonesCarreras());
	    pnsur.add(getPn_OpcionesOrganizador());
	}
	return pnsur;
    }

    private JPanel getPnCentro() {
	if (pnCentro == null) {
	    pnCentro = new JPanel();
	    pnCentro.setLayout(new GridLayout(1, 0, 0, 0));
	    pnCentro.add(getPn_Carreras());
	    pnCentro.add(getTabbedPane());
	}
	return pnCentro;
    }

    private JScrollPane getScDiferencia() {
	if (scDiferencia == null) {
	    scDiferencia = new JScrollPane();
	    scDiferencia.setViewportView(getTableDiferencia());
	}
	return scDiferencia;
    }

    private JScrollPane getScDorsal() {
	if (scDorsal == null) {
	    scDorsal = new JScrollPane();
	    scDorsal.setViewportView(getTableDorsal());
	}
	return scDorsal;
    }

    private JTable getTableDiferencia() {
	if (tDiferencia == null) {
	    tDiferencia = new JTable(modelTablaDiferencia);
	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tDiferencia.getTableHeader()
		    .getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    modelTablaDiferencia.addColumn("Identificador");
	    modelTablaDiferencia.addColumn("Corredor");
	    modelTablaDiferencia.addColumn("Diferencia");

	}
	return tDiferencia;
    }

    private JTable getTableDorsal() {
	if (tDorsal == null) {
	    tDorsal = new JTable(modelTablaDorsal);
	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tDorsal.getTableHeader()
		    .getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    modelTablaDorsal.addColumn("Identificador");
	    modelTablaDorsal.addColumn("Corredor");
	    modelTablaDorsal.addColumn("Dorsal");
	}
	return tDorsal;
    }

    private JScrollPane getScParciales() {
	if (scParciales == null) {
	    scParciales = new JScrollPane();
	    scParciales.setViewportView(getTParciales());
	}
	return scParciales;
    }

    private JTable getTParciales() {
	if (tParciales == null) {
	    tParciales = new JTable(modelTablaParciales);
	    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tParciales.getTableHeader()
		    .getDefaultRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    modelTablaParciales.addColumn("Identificador");
	    modelTablaParciales.addColumn("Corredor");
	    modelTablaParciales.addColumn("Parcial 1");
	    modelTablaParciales.addColumn("Parcial 2");
	    modelTablaParciales.addColumn("Parcial 3");
	    modelTablaParciales.addColumn("Parcial 4");
	    modelTablaParciales.addColumn("Parcial 5");
	}
	return tParciales;
    }

    public JButton getBtnResumenFinanciero() {
	if (btnResumenFinanciero == null) {
	    btnResumenFinanciero = new JButton("Resumen financiero");
	    btnResumenFinanciero.setFont(new Font("Tahoma", Font.PLAIN, 10));
	}
	return btnResumenFinanciero;
    }
}
