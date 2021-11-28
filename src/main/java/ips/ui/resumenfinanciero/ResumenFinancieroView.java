package ips.ui.resumenfinanciero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ResumenFinancieroView extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblNombreCarrera;
    private JPanel pnIngresos;
    private JPanel pnPerdidas;
    private JLabel lblInscripcionesConfirmadas;
    private JLabel lblTotalIngresado;
    private JLabel lblTotalDevolver;
    private JLabel lblInscripcionesAnuladas;
    private JLabel lblInscConfN;
    private JLabel lblInscAnN;
    private JLabel lblTotalIngresadoN;
    private JLabel lblTotalDevolverN;
    private JButton btnCerrar;
    private JLabel lblTotal;
    private JLabel lblTotalN;

    /**
     * Create the dialog.
     * 
     * @param idCarrera
     */
    public ResumenFinancieroView() {
	setModal(true);
	setResizable(false);
	setTitle("Menu Organizador: Resumen financiero");
	setBounds(100, 100, 660, 335);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	contentPanel.setLayout(null);
	{
	    JLabel lblTitulo = new JLabel("Resumen financiero de la carrera con nombre");
	    lblTitulo.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 23));
	    lblTitulo.setBounds(46, 11, 462, 40);
	    contentPanel.add(lblTitulo);
	}
	contentPanel.add(getLblNombreCarrera());
	contentPanel.add(getPnIngresos());
	contentPanel.add(getPnPerdidas());
	contentPanel.add(getBtnCerrar());
	contentPanel.add(getLblTotal());
	contentPanel.add(getLblTotalN());
	setLocationRelativeTo(null);
    }

    public JLabel getLblNombreCarrera() {
	if (lblNombreCarrera == null) {
	    lblNombreCarrera = new JLabel("");
	    lblNombreCarrera.setHorizontalTextPosition(SwingConstants.CENTER);
	    lblNombreCarrera.setHorizontalAlignment(SwingConstants.LEFT);
	    lblNombreCarrera.setBorder(null);
	    lblNombreCarrera.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 19));
	    lblNombreCarrera.setBounds(46, 46, 558, 31);
	}
	return lblNombreCarrera;
    }

    private JPanel getPnIngresos() {
	if (pnIngresos == null) {
	    pnIngresos = new JPanel();
	    pnIngresos
		    .setBorder(new TitledBorder(null, "Ingresos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pnIngresos.setBounds(46, 85, 274, 150);
	    pnIngresos.setLayout(null);
	    pnIngresos.add(getLblInscripcionesConfirmadas());
	    pnIngresos.add(getLblTotalIngresado());
	    pnIngresos.add(getLblInscConfN());
	    pnIngresos.add(getLblTotalIngresadoN());
	}
	return pnIngresos;
    }

    private JPanel getPnPerdidas() {
	if (pnPerdidas == null) {
	    pnPerdidas = new JPanel();
	    pnPerdidas.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		    "P\u00E9rdidas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    pnPerdidas.setBounds(330, 85, 274, 150);
	    pnPerdidas.setLayout(null);
	    pnPerdidas.add(getLblTotalDevolver());
	    pnPerdidas.add(getLblInscripcionesAnuladas());
	    pnPerdidas.add(getLblInscAnN());
	    pnPerdidas.add(getLblTotalDevolverN());
	}
	return pnPerdidas;
    }

    private JLabel getLblInscripcionesConfirmadas() {
	if (lblInscripcionesConfirmadas == null) {
	    lblInscripcionesConfirmadas = new JLabel("Inscripciones confirmadas:");
	    lblInscripcionesConfirmadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblInscripcionesConfirmadas.setBounds(22, 27, 167, 44);
	}
	return lblInscripcionesConfirmadas;
    }

    private JLabel getLblTotalIngresado() {
	if (lblTotalIngresado == null) {
	    lblTotalIngresado = new JLabel("Total ingresado:");
	    lblTotalIngresado.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblTotalIngresado.setBounds(22, 81, 102, 44);
	}
	return lblTotalIngresado;
    }

    private JLabel getLblTotalDevolver() {
	if (lblTotalDevolver == null) {
	    lblTotalDevolver = new JLabel("Total a devolver:");
	    lblTotalDevolver.setBounds(35, 81, 106, 44);
	    lblTotalDevolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	return lblTotalDevolver;
    }

    private JLabel getLblInscripcionesAnuladas() {
	if (lblInscripcionesAnuladas == null) {
	    lblInscripcionesAnuladas = new JLabel("Inscripciones anuladas:");
	    lblInscripcionesAnuladas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblInscripcionesAnuladas.setBounds(35, 27, 146, 44);
	}
	return lblInscripcionesAnuladas;
    }

    public JLabel getLblInscConfN() {
	if (lblInscConfN == null) {
	    lblInscConfN = new JLabel("");
	    lblInscConfN.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblInscConfN.setBounds(199, 27, 45, 44);
	}
	return lblInscConfN;
    }

    public JLabel getLblInscAnN() {
	if (lblInscAnN == null) {
	    lblInscAnN = new JLabel("");
	    lblInscAnN.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblInscAnN.setBounds(191, 27, 45, 44);
	}
	return lblInscAnN;
    }

    public JLabel getLblTotalIngresadoN() {
	if (lblTotalIngresadoN == null) {
	    lblTotalIngresadoN = new JLabel("");
	    lblTotalIngresadoN.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblTotalIngresadoN.setBounds(134, 81, 115, 44);
	}
	return lblTotalIngresadoN;
    }

    public JLabel getLblTotalDevolverN() {
	if (lblTotalDevolverN == null) {
	    lblTotalDevolverN = new JLabel("");
	    lblTotalDevolverN.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblTotalDevolverN.setBounds(149, 81, 115, 44);
	}
	return lblTotalDevolverN;
    }

    public JButton getBtnCerrar() {
	if (btnCerrar == null) {
	    btnCerrar = new JButton("Cerrar");
	    btnCerrar.setBackground(Color.WHITE);
	    btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnCerrar.setBounds(488, 245, 116, 43);
	}
	return btnCerrar;
    }

    private JLabel getLblTotal() {
	if (lblTotal == null) {
	    lblTotal = new JLabel("SUBTOTAL:");
	    lblTotal.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 23));
	    lblTotal.setBounds(46, 246, 126, 42);
	}
	return lblTotal;
    }

    public JLabel getLblTotalN() {
	if (lblTotalN == null) {
	    lblTotalN = new JLabel("");
	    lblTotalN.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 23));
	    lblTotalN.setBounds(170, 246, 150, 42);
	}
	return lblTotalN;
    }
}
