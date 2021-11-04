package ips.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ips.business.BusinessException;
import ips.ui.carreras.EstadoInscripcionesView;
import ips.ui.carreras.InscripcionView;

public class MenuCorredorView extends JDialog{
	private JButton btnVerInscripciones;
	private JButton btnInscribirse;
	private JTable table;
	private JScrollPane scrollPane_1;
	private DefaultTableModel model;
	private JRadioButton rdbtnAbiertas;
	private JRadioButton rdbtnVerTodas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public MenuCorredorView() {
		setTitle("Corredor");
		setResizable(false);
		model = new DefaultTableModel();
		setBounds(100, 100, 892, 427);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnVerInscripciones());
		getContentPane().add(getBtnInscribirse());
		getContentPane().add(getScrollPane_1());
		getContentPane().add(getRdbtnAbiertas());
		getContentPane().add(getRdbtnVerTodas());
		this.setModal(true);
	}
	
	public DefaultTableModel getTableModel() {
		return model;
	}
	
	// [ADRI]
	private JButton getBtnVerInscripciones() {
		if (btnVerInscripciones == null) {
			btnVerInscripciones = new JButton("Ver inscripciones");
			btnVerInscripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EstadoInscripcionesView estado = null;
					try {
						estado = new EstadoInscripcionesView();
					} catch (BusinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    estado.setVisible(true);
				}
			});
			btnVerInscripciones.setBounds(629, 342, 134, 38);
		}
		return btnVerInscripciones;
	}
	private JButton getBtnInscribirse() {
		if (btnInscribirse == null) {
			btnInscribirse = new JButton("Inscribirse");
			btnInscribirse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InscripcionView inscripcion = null;
					try {
						inscripcion = new InscripcionView();
					} catch (BusinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    inscripcion.setVisible(true);
				}
			});
			btnInscribirse.setEnabled(true);
			btnInscribirse.setBounds(773, 342, 105, 38);
		}
		return btnInscribirse;
	}
	public JTable getTable() {
		if (table == null) {
			table = new JTable(model);
			model.addColumn("ID Carrera");
			model.addColumn("Nombre");
			model.addColumn("Fecha");
			model.addColumn("Tipo");
			model.addColumn("Lugar");
			model.addColumn("Distancia");
			model.addColumn("Plazas disponibles");
			table.setDefaultEditor(Object.class, null);
			
		}
		return table;
	}
	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 36, 639, 307);
			scrollPane_1.setViewportView(getTable());
		}
		return scrollPane_1;
	}
	public JRadioButton getRdbtnAbiertas() {
		if (rdbtnAbiertas == null) {
			rdbtnAbiertas = new JRadioButton("Abiertas");
			buttonGroup.add(rdbtnAbiertas);
			rdbtnAbiertas.setBounds(655, 107, 105, 21);
		}
		return rdbtnAbiertas;
	}
	public JRadioButton getRdbtnVerTodas() {
		if (rdbtnVerTodas == null) {
			rdbtnVerTodas = new JRadioButton("Ver todas");
			buttonGroup.add(rdbtnVerTodas);
			rdbtnVerTodas.setBounds(655, 70, 105, 21);
			rdbtnVerTodas.setSelected(true);
		}
		return rdbtnVerTodas;
	}
}
