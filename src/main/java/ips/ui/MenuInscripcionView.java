package ips.ui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ips.business.corredores.CorredorDTO;

public class MenuInscripcionView extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JLabel lbNumero;
    private JTextField txNumeroTarjeta;
    private JLabel lbFecha;
    private JTextField txFecha;
    private JLabel lblCvc;
    private JTextField txCvc;
    private JLabel lbDni;
    private JTextField txCorredor;
    private JScrollPane scrollPane;
    private JButton btValidar;
    private JList listUpdates;
    private JLabel lbVistaResultado;
    private JRadioButton rdbtnTransferenciaBancaria;
    private JPanel panelTarjeta;
    private JRadioButton rbtnPagoTarjeta;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private CorredorDTO corredor;

    /**
     * Create the frame.
     */
    public MenuInscripcionView() {
	setTitle("Corredor: Inscripcion con tarjeta");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 809, 368);
	setLocationRelativeTo(null);
	setModal(true);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getScrollPane());
	contentPane.add(getBtValidar());
	contentPane.add(getLbVistaResultado());
	contentPane.add(getRdbtnTransferenciaBancaria());
	contentPane.add(getRbtnPagoTarjeta());
	contentPane.add(getPanelTarjeta());
    }

    private JLabel getLbNumero() {
	if (lbNumero == null) {
	    lbNumero = new JLabel("Numero tarjeta:");
	    lbNumero.setBounds(10, 22, 121, 20);
	}
	return lbNumero;
    }

    public JTextField getTxNumeroTarjeta() {
	if (txNumeroTarjeta == null) {
	    txNumeroTarjeta = new JTextField();
	    txNumeroTarjeta.setBounds(141, 22, 121, 20);
	    txNumeroTarjeta.setColumns(10);
	}
	return txNumeroTarjeta;
    }

    private JLabel getLbFecha() {
	if (lbFecha == null) {
	    lbFecha = new JLabel("Fecha caducidad:");
	    lbFecha.setBounds(10, 55, 121, 20);
	}
	return lbFecha;
    }

    public JTextField getTxFecha() {
	if (txFecha == null) {
	    txFecha = new JTextField();
	    txFecha.setColumns(10);
	    txFecha.setBounds(141, 55, 121, 20);
	}
	return txFecha;
    }

    private JLabel getLblCvc() {
	if (lblCvc == null) {
	    lblCvc = new JLabel("Cvc:");
	    lblCvc.setBounds(10, 86, 121, 20);
	}
	return lblCvc;
    }

    public JTextField getTxCvc() {
	if (txCvc == null) {
	    txCvc = new JTextField();
	    txCvc.setColumns(10);
	    txCvc.setBounds(141, 86, 121, 20);
	}
	return txCvc;
    }

    private JLabel getLbDni() {
	if (lbDni == null) {
	    lbDni = new JLabel("Dni:");
	    lbDni.setBounds(10, 117, 121, 20);
	}
	return lbDni;
    }

    public JTextField getTxCorredor() {
	if (txCorredor == null) {
	    txCorredor = new JTextField();
	    txCorredor.setBounds(141, 117, 121, 20);
	    txCorredor.setColumns(10);
	}
	return txCorredor;
    }

    private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(332, 92, 438, 142);
	    scrollPane.setViewportView(getListUpdates());
	}
	return scrollPane;
    }

    public JButton getBtValidar() {
	if (btValidar == null) {
	    btValidar = new JButton("Validar");
	    btValidar.setBounds(151, 272, 121, 33);
	    btValidar.setEnabled(false);
	}
	return btValidar;
    }

    public JList getListUpdates() {
	if (listUpdates == null) {
	    listUpdates = new JList();
	}
	return listUpdates;
    }

    private JLabel getLbVistaResultado() {
	if (lbVistaResultado == null) {
	    lbVistaResultado = new JLabel("Actualizacion de los pagos:");
	    lbVistaResultado.setBounds(351, 61, 199, 20);
	}
	return lbVistaResultado;
    }

    public JRadioButton getRdbtnTransferenciaBancaria() {
	if (rdbtnTransferenciaBancaria == null) {
	    rdbtnTransferenciaBancaria = new JRadioButton("Transferencia bancaria");
	    rdbtnTransferenciaBancaria.setSelected(true);
	    buttonGroup.add(rdbtnTransferenciaBancaria);
	    rdbtnTransferenciaBancaria.setBackground(Color.WHITE);
	    rdbtnTransferenciaBancaria.setBounds(151, 61, 161, 22);
	}
	return rdbtnTransferenciaBancaria;
    }

    public JPanel getPanelTarjeta() {
	if (panelTarjeta == null) {
	    panelTarjeta = new JPanel();
	    panelTarjeta.setVisible(false);
	    panelTarjeta.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	    panelTarjeta.setBackground(Color.WHITE);
	    panelTarjeta.setBounds(10, 90, 285, 165);
	    panelTarjeta.setLayout(null);
	    panelTarjeta.add(getLbNumero());
	    panelTarjeta.add(getTxNumeroTarjeta());
	    panelTarjeta.add(getLbFecha());
	    panelTarjeta.add(getTxFecha());
	    panelTarjeta.add(getLblCvc());
	    panelTarjeta.add(getTxCvc());
	    panelTarjeta.add(getLbDni());
	    panelTarjeta.add(getTxCorredor());
	}
	return panelTarjeta;
    }

    public JRadioButton getRbtnPagoTarjeta() {
	if (rbtnPagoTarjeta == null) {
	    rbtnPagoTarjeta = new JRadioButton("Pago con tarjeta");
	    rbtnPagoTarjeta.setBounds(24, 60, 109, 23);
	    buttonGroup.add(rbtnPagoTarjeta);
	    rbtnPagoTarjeta.setBackground(Color.WHITE);
	}
	return rbtnPagoTarjeta;
    }

    public void setCorredor(CorredorDTO corredor) {
	this.corredor = corredor;
    }
}
