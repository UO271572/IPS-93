package ips.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;

import com.toedter.calendar.JDateChooser;

public class MenuCrearCarreraView extends JDialog{
	private JTextField txNombre;
	private JLabel lblNombre;
	private JLabel lblLugar;
	private JTextField txLugar;
	private JLabel lblTipo;
	private JRadioButton rdbtnCiudad;
	private JRadioButton rdbtnMontaa;
	private JLabel lblDistancia;
	private JSpinner spDistancia;
	private JLabel lblKm;
	private JLabel lblPlazasTotales;
	private JSpinner spPlazas;
	private JDatePicker datePicker;
	private JButton btnGuardar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser fechaCompeticion;
	private JButton btnCancelar;
	private JLabel lblFechaDeCompeticion;
	private JScrollPane scrollPanePlazos;
	private JTable tablePlazos;
	private DefaultTableModel modelPlazos;
	private JButton btnAnadir;
	private JButton btnBorrar;
	private JButton btnModificar;
	public MenuCrearCarreraView() {
		setTitle("Organizador: Creacion de carrera");
		setBounds(100, 100, 892, 427);
		setLocationRelativeTo(null);
		modelPlazos = new DefaultTableModel();
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getTxNombre());
		getContentPane().add(getLblNombre());
		getContentPane().add(getLblLugar());
		getContentPane().add(getTxLugar());
		getContentPane().add(getLblTipo());
		getContentPane().add(getRdbtnCiudad());
		getContentPane().add(getRdbtnMontana());
		getContentPane().add(getLblDistancia());
		getContentPane().add(getSpDistancia());
		getContentPane().add(getLblKm());
		getContentPane().add(getLblPlazasTotales());
		getContentPane().add(getSpPlazas());
		getContentPane().add(getBtnGuardar());
		getContentPane().add(getFechaCompeticion());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getLblFechaDeCompeticion());
		getContentPane().add(getScrollPanePlazos());
		getContentPane().add(getBtnAnadir());
		getContentPane().add(getBtnBorrar());
		getContentPane().add(getBtnModificar());
		this.setModal(true);
	}
	public JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setBounds(69, 55, 148, 19);
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	public JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNombre.setBounds(13, 58, 72, 13);
		}
		return lblNombre;
	}
	public JLabel getLblLugar() {
		if (lblLugar == null) {
			lblLugar = new JLabel("Lugar:");
			lblLugar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblLugar.setBounds(264, 49, 46, 26);
		}
		return lblLugar;
	}
	public JTextField getTxLugar() {
		if (txLugar == null) {
			txLugar = new JTextField();
			txLugar.setBounds(320, 55, 138, 19);
			txLugar.setColumns(10);
		}
		return txLugar;
	}
	public JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblTipo.setBounds(492, 54, 61, 17);
		}
		return lblTipo;
	}
	public JRadioButton getRdbtnCiudad() {
		if (rdbtnCiudad == null) {
			rdbtnCiudad = new JRadioButton("Ciudad");
			buttonGroup.add(rdbtnCiudad);
			rdbtnCiudad.setBackground(Color.WHITE);
			rdbtnCiudad.setBounds(596, 32, 105, 21);
			rdbtnCiudad.setSelected(true);
		}
		return rdbtnCiudad;
	}
	public JRadioButton getRdbtnMontana() {
		if (rdbtnMontaa == null) {
			rdbtnMontaa = new JRadioButton("Montaña");
			buttonGroup.add(rdbtnMontaa);
			rdbtnMontaa.setBackground(Color.WHITE);
			rdbtnMontaa.setBounds(596, 67, 105, 21);
		}
		return rdbtnMontaa;
	}
	public JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia:");
			lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDistancia.setBounds(13, 120, 72, 13);
		}
		return lblDistancia;
	}
	public JSpinner getSpDistancia() {
		if (spDistancia == null) {
			spDistancia = new JSpinner();
			spDistancia.setBounds(79, 119, 138, 20);
		}
		return spDistancia;
	}
	public JLabel getLblKm() {
		if (lblKm == null) {
			lblKm = new JLabel("km");
			lblKm.setBounds(225, 122, 46, 13);
		}
		return lblKm;
	}
	public JLabel getLblPlazasTotales() {
		if (lblPlazasTotales == null) {
			lblPlazasTotales = new JLabel("Plazas totales:");
			lblPlazasTotales.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPlazasTotales.setBounds(264, 120, 123, 13);
		}
		return lblPlazasTotales;
	}
	public JSpinner getSpPlazas() {
		if (spPlazas == null) {
			spPlazas = new JSpinner();
			spPlazas.setBounds(357, 119, 101, 20);
		}
		return spPlazas;
	}
	public JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnGuardar.setBounds(560, 305, 85, 21);
		}
		return btnGuardar;
	}
	public JDateChooser getFechaCompeticion() {
		if (fechaCompeticion == null) {
			fechaCompeticion = new JDateChooser();
			fechaCompeticion.setBounds(642, 120, 101, 19);
		}
		return fechaCompeticion;
	}
	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(658, 305, 85, 21);
		}
		return btnCancelar;
	}
	public JLabel getLblFechaDeCompeticion() {
		if (lblFechaDeCompeticion == null) {
			lblFechaDeCompeticion = new JLabel("Fecha de competicion:");
			lblFechaDeCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFechaDeCompeticion.setBounds(492, 120, 148, 13);
		}
		return lblFechaDeCompeticion;
	}
	public JScrollPane getScrollPanePlazos() {
		if (scrollPanePlazos == null) {
			scrollPanePlazos = new JScrollPane();
			scrollPanePlazos.setBounds(492, 186, 258, 103);
			scrollPanePlazos.setViewportView(getTablePlazos());
		}
		return scrollPanePlazos;
	}
	public JTable getTablePlazos() {
		if (tablePlazos == null) {
			tablePlazos = new JTable(modelPlazos);
			modelPlazos.addColumn("Fecha inicio");
			modelPlazos.addColumn("Fecha fin");
			modelPlazos.addColumn("Cuota");
			tablePlazos.setDefaultEditor(Object.class, null);
			
		}
		return tablePlazos;
	}
	public DefaultTableModel getTablePlazosModel() {
		return modelPlazos;
	}
	public JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBounds(397, 186, 85, 21);
		}
		return btnAnadir;
	}
	public JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar");
			btnBorrar.setBounds(397, 268, 85, 21);
		}
		return btnBorrar;
	}
	public JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setBounds(397, 228, 85, 21);
		}
		return btnModificar;
	}
}
