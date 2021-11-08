package ips.ui.inscripciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import ips.ui.MenuOrganizadorView;
import javax.swing.JTable;

public class DorsalesView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JButton btOk;
	private JTable listInscripcionesDorsal;
	private DefaultTableModel model;

	
	
	/**
	 * Create the dialog.
	 */
	public DorsalesView() {
		setModal(true);
		model = new DefaultTableModel();
//		this.menOrgView = menOrgView;
		setTitle("Asignacion de dorsales");
		setBounds(100, 100, 614, 433);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getPanel());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.add(getBtOk());
		}
		
//		this.setVisible(true);
//		this.setModal(true);
	}
	
	
	
//	public MenuOrganizadorView getMenOrgView() {
//		return menOrgView;
//	}

	public DefaultTableModel getModel() {
		return model;
	}



	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(24, 21, 549, 315);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
//			scrollPane.setViewportView(getTable());
			scrollPane.setViewportView(getListInscripcionDorsal());
		}
		return scrollPane;
	}
	public JButton getBtOk() {
		if (btOk == null) {
			btOk = new JButton("OK");
		}
		return btOk;
	}
	public JTable getListInscripcionDorsal() {
		if (listInscripcionesDorsal == null) {
			listInscripcionesDorsal = new JTable(model);
			model.addColumn("DNI");
			model.addColumn("IdCarrera");
			model.addColumn("Estado insc.");
			model.addColumn("Fecha insc.");
			model.addColumn("Dorsal");
			model.addColumn("Incidencia");
			model.addColumn("T-Inicio");
			model.addColumn("T-Fin");
			listInscripcionesDorsal.setDefaultEditor(Object.class, null);
		}
		return listInscripcionesDorsal;
	}
}
