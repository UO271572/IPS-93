package ips.ui.carreras;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ips.business.BusinessException;
import ips.business.corredores.CorredorDTO;

public class EstadoInscripcionesView extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JLabel lbCarreras;
    private JScrollPane spCarreras;
    private JTable table;
    private DefaultTableModel model;
    private JButton btFinalizar;
    private JButton btCancelarInscripcion;

    private CorredorDTO corredor;

    public EstadoInscripcionesView() throws BusinessException {
	model = new DefaultTableModel();

	setModal(true);
	setTitle("Estado inscripciones");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	setBounds(100, 100, 700, 451);
	setLocationRelativeTo(null);

	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(null);
	setContentPane(contentPane);
	contentPane.add(getLbCarreras());
	contentPane.add(getSpCarreras());
	contentPane.add(getBtFinalizar());
	contentPane.add(getBtCancelarInscripcion());
    }

    private JLabel getLbCarreras() throws BusinessException {
	if (lbCarreras == null) {
	    lbCarreras = new JLabel("Estado inscripciones:");
	    lbCarreras.setBounds(34, 31, 245, 28);
	    lbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 13));
	}
	return lbCarreras;
    }

    public JScrollPane getSpCarreras() {
	if (spCarreras == null) {
	    spCarreras = new JScrollPane();
	    spCarreras.setEnabled(false);
	    spCarreras.setBounds(34, 65, 621, 276);
	    spCarreras.setViewportView(getTable());
	}
	return spCarreras;
    }

    public JTable getTable() {
	if (table == null) {
	    table = new JTable(model);
	    model.addColumn("ID Carrera");
	    model.addColumn("Estado");
	    model.addColumn("Fecha");
	    model.addColumn("Dorsal");
	    model.addColumn("Incidencia");
	    model.addColumn("Tiempo inicio");
	    model.addColumn("Tiempo fin");
	    table.setDefaultEditor(Object.class, null);
	}
	return table;
    }

    public DefaultTableModel getTableModel() {
	return model;
    }

    public JButton getBtFinalizar() {
	if (btFinalizar == null) {
	    btFinalizar = new JButton("Finalizar");
	    btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btFinalizar.setMnemonic('F');
	    btFinalizar.setBackground(Color.RED);
	    btFinalizar.setForeground(Color.WHITE);
	    btFinalizar.setBounds(432, 368, 100, 30);
	}
	return btFinalizar;
    }

    public JButton getBtCancelarInscripcion() {
	if (btCancelarInscripcion == null) {
	    btCancelarInscripcion = new JButton("Cancelar inscipción");
	    btCancelarInscripcion.setMnemonic('C');
	    btCancelarInscripcion.setForeground(Color.WHITE);
	    btCancelarInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btCancelarInscripcion.setBackground(Color.RED);
	    btCancelarInscripcion.setBounds(115, 368, 201, 30);
	}
	return btCancelarInscripcion;
    }

    public CorredorDTO getCorredor() {
	return corredor;
    }

    public void setCorredor(CorredorDTO corredor) {
	this.corredor = corredor;
    }

}
