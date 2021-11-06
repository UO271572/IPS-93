package ips.ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.persistence.carreras.CarrerasModel;
import ips.ui.carreras.CarrerasView;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

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

	public MenuOrganizadorView() {
		setTitle("Menú: Organizador");
		setResizable(false);
		setBounds(100, 100, 953, 504);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPaneCorredores());
//		getContentPane().add(getCbCarreras());
		getContentPane().add(getPn_FiltrosClasificacion());
		getContentPane().add(getPn_OpcionesOrganizador());
		getContentPane().add(getPn_Carreras());
		getContentPane().add(getPn_BotonesCarreras());
		
		
		
		this.setModal(true);
	}

	private JScrollPane getScrollPaneCorredores() {
		if (scrollPaneCorredores == null) {
			scrollPaneCorredores = new JScrollPane();
			scrollPaneCorredores.setBounds(386, 120, 543, 259);
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
			pn_FiltrosClasificacion.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pn_FiltrosClasificacion.setBackground(Color.WHITE);
			pn_FiltrosClasificacion.setBounds(386, 10, 543, 100);
			pn_FiltrosClasificacion.setLayout(new GridLayout(0, 3, 0, 0));
			pn_FiltrosClasificacion.add(getBtMostrarClasificacionSinFiltro());
			pn_FiltrosClasificacion.add(getBtMostrarClasificacionSexo());
			pn_FiltrosClasificacion.add(getBtMostrarClasificacionCategoria());
		}
		return pn_FiltrosClasificacion;
	}
	private JPanel getPn_OpcionesOrganizador() {
		if (pn_OpcionesOrganizador == null) {
			pn_OpcionesOrganizador = new JPanel();
			pn_OpcionesOrganizador.setBounds(386, 389, 543, 68);
			pn_OpcionesOrganizador.setLayout(new GridLayout(1, 0, 0, 0));
			pn_OpcionesOrganizador.add(getBtnAsignarDorsales());
			pn_OpcionesOrganizador.add(getBtnProcesarPagos());
			pn_OpcionesOrganizador.add(getBtnCargarDatos());
			pn_OpcionesOrganizador.add(getBtnCrearCarrera());
		}
		return pn_OpcionesOrganizador;
	}
	private JButton getBtnCargarDatos() {
		if (btnCargarDatos == null) {
			btnCargarDatos = new JButton("Cargar datos");
		}
		return btnCargarDatos;
	}
	private JButton getBtnProcesarPagos() {
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
			pn_Carreras.setBorder(new TitledBorder(null, "Carreras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pn_Carreras.setBounds(10, 10, 353, 372);
			pn_Carreras.setLayout(new GridLayout(0, 1, 0, 0));
			pn_Carreras.add(getScrollPaneCarreras());
			//pn_Carreras.add(getListCarreras());
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
}

