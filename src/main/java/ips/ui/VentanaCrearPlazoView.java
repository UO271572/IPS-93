package ips.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JDateChooser;

public class VentanaCrearPlazoView extends JDialog {
    private JDateChooser fechaInicio;
    private JDateChooser fechaFin;
    private JLabel lblCuotaDelPlazo;
    private JLabel lblEuros;
    private JLabel lblFechaInicioDel;
    private JLabel lblFechaFinalDel;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JSpinner spCuota;

    public VentanaCrearPlazoView() {
	setBounds(100, 100, 487, 286);
	getContentPane().setBackground(Color.WHITE);
	getContentPane().setLayout(null);
	getContentPane().add(getFechaInicio());
	getContentPane().add(getFechaFin());
	getContentPane().add(getLblCuotaDelPlazo());
	getContentPane().add(getLblEuros());
	getContentPane().add(getLblFechaInicioDel());
	getContentPane().add(getLblFechaFinalDel());
	getContentPane().add(getBtnGuardar());
	getContentPane().add(getBtnCancelar());
	getContentPane().add(getSpCuota());
	setTitle("Organizador: Introduce los datos del plazo de la carrera");
	this.setModal(true);
	setLocationRelativeTo(null);
    }

    public JDateChooser getFechaInicio() {
	if (fechaInicio == null) {
	    fechaInicio = new JDateChooser();
	    fechaInicio.setBounds(176, 35, 127, 19);
	}
	return fechaInicio;
    }

    public JDateChooser getFechaFin() {
	if (fechaFin == null) {
	    fechaFin = new JDateChooser();
	    fechaFin.setBounds(176, 96, 127, 19);
	}
	return fechaFin;
    }

    public JLabel getLblCuotaDelPlazo() {
	if (lblCuotaDelPlazo == null) {
	    lblCuotaDelPlazo = new JLabel("Cuota del plazo:");
	    lblCuotaDelPlazo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblCuotaDelPlazo.setBounds(10, 153, 132, 27);
	}
	return lblCuotaDelPlazo;
    }

    public JLabel getLblEuros() {
	if (lblEuros == null) {
	    lblEuros = new JLabel("euros");
	    lblEuros.setBounds(232, 162, 46, 13);
	}
	return lblEuros;
    }

    public JLabel getLblFechaInicioDel() {
	if (lblFechaInicioDel == null) {
	    lblFechaInicioDel = new JLabel("Fecha inicial del plazo:");
	    lblFechaInicioDel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblFechaInicioDel.setBounds(10, 32, 141, 27);
	}
	return lblFechaInicioDel;
    }

    public JLabel getLblFechaFinalDel() {
	if (lblFechaFinalDel == null) {
	    lblFechaFinalDel = new JLabel("Fecha final del plazo:");
	    lblFechaFinalDel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblFechaFinalDel.setBounds(10, 96, 186, 19);
	}
	return lblFechaFinalDel;
    }

    public JButton getBtnGuardar() {
	if (btnGuardar == null) {
	    btnGuardar = new JButton("Guardar");
	    btnGuardar.setBounds(284, 223, 85, 21);
	}
	return btnGuardar;
    }

    public JButton getBtnCancelar() {
	if (btnCancelar == null) {
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBounds(379, 223, 85, 21);
	}
	return btnCancelar;
    }

    public JSpinner getSpCuota() {
	if (spCuota == null) {
	    SpinnerModel model = new SpinnerNumberModel(0.0, 0.0, 100000.0, 0.1);
	    spCuota = new JSpinner(model);
	    spCuota.setBounds(176, 159, 46, 20);
	}
	return spCuota;
    }

    /*
     * private static class __Tmp { private static void __tmp() { javax.swing.JPanel
     * __wbp_panel = new javax.swing.JPanel(); } }
     */
}
