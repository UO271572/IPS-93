package ips.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;

public class MenuInscripcionView extends JFrame {

	private JPanel contentPane;
	private JLabel lbNumero;
	private JTextField txNumeroTarjeta;
	private JLabel lbFecha;
	private JTextField txFecha;
	private JLabel lblCvc;
	private JTextField txCvc;
	private JLabel lbCorredor;
	private JTextField txCorredor;
	private JLabel lblIdentificadorcarrera;
	private JTextField txIdentificadorCarrera;
	private JScrollPane scrollPane;
	private JButton btValidar;
	private JList listUpdates;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuInscripcionView frame = new MenuInscripcionView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MenuInscripcionView() {
		setTitle("Inscripcion con tarjeta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbNumero());
		contentPane.add(getTxNumeroTarjeta());
		contentPane.add(getLbFecha());
		contentPane.add(getTxFecha());
		contentPane.add(getLblCvc());
		contentPane.add(getTxCvc());
		contentPane.add(getLbCorredor());
		contentPane.add(getTxCorredor());
		contentPane.add(getLblIdentificadorcarrera());
		contentPane.add(getTxIdentificadorCarrera());
		contentPane.add(getScrollPane());
		contentPane.add(getBtValidar());
	}

	private JLabel getLbNumero() {
		if (lbNumero == null) {
			lbNumero = new JLabel("Numero tarjeta:");
			lbNumero.setBounds(20, 34, 121, 20);
		}
		return lbNumero;
	}
	private JTextField getTxNumeroTarjeta() {
		if (txNumeroTarjeta == null) {
			txNumeroTarjeta = new JTextField();
			txNumeroTarjeta.setBounds(151, 34, 121, 20);
			txNumeroTarjeta.setColumns(10);
		}
		return txNumeroTarjeta;
	}
	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Fecha caducidad:");
			lbFecha.setBounds(20, 65, 121, 20);
		}
		return lbFecha;
	}
	private JTextField getTxFecha() {
		if (txFecha == null) {
			txFecha = new JTextField();
			txFecha.setColumns(10);
			txFecha.setBounds(151, 65, 121, 20);
		}
		return txFecha;
	}
	private JLabel getLblCvc() {
		if (lblCvc == null) {
			lblCvc = new JLabel("Cvc:");
			lblCvc.setBounds(20, 96, 121, 20);
		}
		return lblCvc;
	}
	private JTextField getTxCvc() {
		if (txCvc == null) {
			txCvc = new JTextField();
			txCvc.setColumns(10);
			txCvc.setBounds(151, 96, 121, 20);
		}
		return txCvc;
	}
	private JLabel getLbCorredor() {
		if (lbCorredor == null) {
			lbCorredor = new JLabel("Identificador:");
			lbCorredor.setBounds(20, 125, 121, 20);
		}
		return lbCorredor;
	}
	public JTextField getTxCorredor() {
		if (txCorredor == null) {
			txCorredor = new JTextField();
			txCorredor.setColumns(10);
			txCorredor.setBounds(151, 125, 121, 20);
		}
		return txCorredor;
	}
	private JLabel getLblIdentificadorcarrera() {
		if (lblIdentificadorcarrera == null) {
			lblIdentificadorcarrera = new JLabel("IdentificadorCarrera:");
			lblIdentificadorcarrera.setBounds(20, 156, 121, 20);
		}
		return lblIdentificadorcarrera;
	}
	public JTextField getTxIdentificadorCarrera() {
		if (txIdentificadorCarrera == null) {
			txIdentificadorCarrera = new JTextField();
			txIdentificadorCarrera.setColumns(10);
			txIdentificadorCarrera.setBounds(151, 156, 121, 20);
		}
		return txIdentificadorCarrera;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(336, 34, 433, 228);
			scrollPane.setViewportView(getListUpdates());
		}
		return scrollPane;
	}
	public JButton getBtValidar() {
		if (btValidar == null) {
			btValidar = new JButton("Validar");
			btValidar.setBounds(151, 200, 121, 33);
		}
		return btValidar;
	}
	public JList getListUpdates() {
		if (listUpdates == null) {
			listUpdates = new JList();
		}
		return listUpdates;
	}
}
