package ips.ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import ips.business.carreras.CarreraDisplayDTO;

public class MenuOrganizadorView extends JDialog {
	private JComboBox<String> cbOpciones;
	private JButton btnGo;
	private JScrollPane scrollPaneCorredores;
	private JList listCorredores;
	private JButton btnBuscarCorredores;
	private JComboBox<CarreraDisplayDTO> cbCarreras;
	private JButton btMostrarClasificacionSexo;
	private JButton btMostrarClasificacionCategoria;
	private JButton btnCrearCarrera;

	public MenuOrganizadorView() {
		setTitle("Organizador");
		setResizable(false);
		setBounds(100, 100, 854, 427);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getCbOpciones());
		getContentPane().add(getBtnGo());
		getContentPane().add(getScrollPaneCorredores());
		getContentPane().add(getBtnBuscarCorredores());
		getContentPane().add(getCbCarreras());
		getContentPane().add(getBtMostrarClasificacionSexo());
		getContentPane().add(getBtMostrarClasificacionCategoria());
		getContentPane().add(getBtnCrearCarrera());
		this.setModal(true);
	}

	public JComboBox<String> getCbOpciones() {
		if (cbOpciones == null) {
			cbOpciones = new JComboBox<String>();
			cbOpciones.setBounds(10, 66, 339, 30);
		}
		return cbOpciones;
	}

	public JButton getBtnGo() {
		if (btnGo == null) {
			btnGo = new JButton("Go");
			btnGo.setBounds(99, 106, 85, 21);
		}
		return btnGo;
	}

	private JScrollPane getScrollPaneCorredores() {
		if (scrollPaneCorredores == null) {
			scrollPaneCorredores = new JScrollPane();
			scrollPaneCorredores.setBounds(222, 236, 409, 123);
			scrollPaneCorredores.setViewportView(getListCorredores());
		}
		return scrollPaneCorredores;
	}

	public JList getListCorredores() {
		if (listCorredores == null) {
			listCorredores = new JList();
			listCorredores.setEnabled(false);
			listCorredores.setBackground(Color.WHITE);
		}
		return listCorredores;
	}

	public JButton getBtnBuscarCorredores() {
		if (btnBuscarCorredores == null) {
			btnBuscarCorredores = new JButton("Buscar corredores");
			btnBuscarCorredores.setEnabled(false);
			btnBuscarCorredores.setBounds(386, 161, 186, 21);
		}
		return btnBuscarCorredores;
	}

	public JComboBox<CarreraDisplayDTO> getCbCarreras() {
		if (cbCarreras == null) {
			cbCarreras = new JComboBox<CarreraDisplayDTO>();
			cbCarreras.setBounds(399, 66, 249, 26);
		}
		return cbCarreras;
	}

	public JButton getBtMostrarClasificacionSexo() {
		if (btMostrarClasificacionSexo == null) {
			btMostrarClasificacionSexo = new JButton("Clasificacion por sexo");
			btMostrarClasificacionSexo.setEnabled(false);
			btMostrarClasificacionSexo.setBounds(596, 160, 167, 23);
		}
		return btMostrarClasificacionSexo;
	}
	public JButton getBtMostrarClasificacionCategoria() {
		if (btMostrarClasificacionCategoria == null) {
			btMostrarClasificacionCategoria = new JButton("Clasificacion por categoria");
			btMostrarClasificacionCategoria.setEnabled(false);
			btMostrarClasificacionCategoria.setBounds(596, 194, 167, 23);
		}
		return btMostrarClasificacionCategoria;
	}
	public JButton getBtnCrearCarrera() {
		if (btnCrearCarrera == null) {
			btnCrearCarrera = new JButton("Crear carrera");
			btnCrearCarrera.setBounds(641, 264, 109, 21);
		}
		return btnCrearCarrera;
	}
}

