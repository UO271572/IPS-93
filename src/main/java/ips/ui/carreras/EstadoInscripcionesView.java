package ips.ui.carreras;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.corredores.CorredorDTO;
import ips.ui.corredores.CorredoresView;

public class EstadoInscripcionesView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lbInscripcion;
	private JLabel lbEmail;
	private JTextField txtEmail;
	private JLabel lbCarreras;
	private JScrollPane spCarreras;
	private JTextArea taCarreras;
	private JButton btFinalizar;

	public EstadoInscripcionesView() throws BusinessException {
		setTitle("Estado inscripciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 487, 487);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(getLbInscripcion());
		contentPane.add(getLbEmail());
		contentPane.add(getTxtEmail());
		contentPane.add(getLbCarreras());
		contentPane.add(getSpCarreras());
		contentPane.add(getBtFinalizar());
	}

	private JLabel getLbInscripcion() {
		if (lbInscripcion == null) {
			lbInscripcion = new JLabel("Estado inscripciones");
			lbInscripcion.setBackground(Color.WHITE);
			lbInscripcion.setFont(new Font("Arial Black", Font.PLAIN, 35));
			lbInscripcion.setBounds(42, 41, 434, 42);
		}
		return lbInscripcion;
	}
	
	private JLabel getLbEmail() {
		if (lbEmail == null) {
			lbEmail = new JLabel("Email:");
			lbEmail.setLabelFor(getTxtEmail());
			lbEmail.setDisplayedMnemonic('e');
			lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbEmail.setBounds(42, 102, 144, 22);
		}
		return lbEmail;
	}
	
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtEmail.setEditable(true);
			txtEmail.setBackground(SystemColor.WHITE);
			txtEmail.setBounds(42, 135, 310, 42);
		}
		return txtEmail;
	}
	
	private void obtenerInfoInscripciones() throws BusinessException {
		CorredoresView view = new CorredoresView();
		List<CorredorDTO> corredores = view.getCorredorByEmail(getTxtEmail().getText());
		
		CarrerasView carrerasView = new CarrerasView();
		String infoInscripciones = "";
		for (CorredorDTO corredor : corredores)
			infoInscripciones += carrerasView.getCarrerasById("" + corredor.getIdCarrera()).get(0).getNombre() + " - " + corredor.getEstadoInscripcion() + " - " + corredor.getFechaInscripcion() + "\n";
		getTaCarreras().setText(infoInscripciones);
	}
	
	private JLabel getLbCarreras() throws BusinessException {
		if (lbCarreras == null) {
			lbCarreras = new JLabel("Carreras:");
			lbCarreras.setBounds(42, 188, 87, 28);
			lbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lbCarreras;
	}
	
	private JScrollPane getSpCarreras() {
		if (spCarreras == null) {
			spCarreras = new JScrollPane();
			spCarreras.setBounds(34, 231, 414, 164);
			spCarreras.setViewportView(getTaCarreras());
			spCarreras.setColumnHeaderView(getTaCarreras());
		}
		return spCarreras;
	}
	
	private JTextArea getTaCarreras() {
		if (taCarreras == null) {
			taCarreras = new JTextArea();
			taCarreras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			taCarreras.setBackground(Color.WHITE);
			taCarreras.setEditable(false);
		}
		return taCarreras;
	}
	
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btFinalizar.setMnemonic('F');
			btFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btFinalizar.setBackground(Color.RED);
			btFinalizar.setForeground(Color.WHITE);
			btFinalizar.setBounds(196, 406, 90, 30);
		}
		return btFinalizar;
	}
	
}
