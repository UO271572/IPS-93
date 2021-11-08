package ips.ui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class VentanaCrearPlazoView extends JDialog {
    private JDateChooser fechaInicio;
    private JDateChooser fechaFin;
    private JLabel lblEuros;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JSpinner spCuota;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel label;
    private JLabel label_1;

    public VentanaCrearPlazoView() {
	setResizable(false);
	setBounds(100, 100, 487, 286);
	getContentPane().setBackground(Color.WHITE);
	getContentPane().setLayout(null);
	getContentPane().add(getBtnGuardar());
	getContentPane().add(getBtnCancelar());
	getContentPane().add(getPanel());
	getContentPane().add(getPanel_1());
	getContentPane().add(getPanel_2());
	setTitle("Organizador: Introduce los datos del plazo de la carrera");
	this.setModal(true);
	setLocationRelativeTo(null);
    }

    public JDateChooser getFechaInicio() {
	if (fechaInicio == null) {
	    fechaInicio = new JDateChooser();
	}
	return fechaInicio;
    }

    public JDateChooser getFechaFin() {
	if (fechaFin == null) {
	    fechaFin = new JDateChooser();
	}
	return fechaFin;
    }

    public JLabel getLblEuros() {
	if (lblEuros == null) {
	    lblEuros = new JLabel("euros");
	}
	return lblEuros;
    }

    public JButton getBtnGuardar() {
	if (btnGuardar == null) {
	    btnGuardar = new JButton("Guardar");
	    btnGuardar.setBounds(284, 206, 85, 38);
	}
	return btnGuardar;
    }

    public JButton getBtnCancelar() {
	if (btnCancelar == null) {
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBounds(379, 206, 85, 38);
	}
	return btnCancelar;
    }

    public JSpinner getSpCuota() {
	if (spCuota == null) {
	    SpinnerModel model = new SpinnerNumberModel(0.0, 0.0, 100000.0, 0.1);
	    spCuota = new JSpinner(model);
	}
	return spCuota;
    }

    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setBorder(new TitledBorder(null, "Fecha inicial del plazo", TitledBorder.LEADING, TitledBorder.TOP,
		    null, null));
	    panel.setBounds(53, 27, 374, 41);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(getFechaInicio());
	}
	return panel;
    }

    private JPanel getPanel_1() {
	if (panel_1 == null) {
	    panel_1 = new JPanel();
	    panel_1.setBorder(new TitledBorder(null, "Fecha final del plazo", TitledBorder.LEADING, TitledBorder.TOP,
		    null, null));
	    panel_1.setBounds(53, 82, 374, 41);
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
	    panel_1.add(getFechaFin());
	}
	return panel_1;
    }

    private JPanel getPanel_2() {
	if (panel_2 == null) {
	    panel_2 = new JPanel();
	    panel_2.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cuota",
		    TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    panel_2.setBounds(53, 138, 374, 41);
	    panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
	    panel_2.add(getLabel());
	    panel_2.add(getLabel_1());
	    panel_2.add(getSpCuota());
	    panel_2.add(getLblEuros());
	}
	return panel_2;
    }

    private JLabel getLabel() {
	if (label == null) {
	    label = new JLabel("");
	}
	return label;
    }

    private JLabel getLabel_1() {
	if (label_1 == null) {
	    label_1 = new JLabel("");
	}
	return label_1;
    }
}
