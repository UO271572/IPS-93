package ips.ui.carreras;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.corredores.CorredorDTO;

public class JustificanteView extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel pnContenido;
	private JButton btFinalizar;
	
	private InscripcionView iiu;
	private JTextArea txtMensaje;
	
	public JustificanteView(InscripcionView iiu) throws BusinessException {
		this.iiu = iiu;
		
		setTitle("Inscripción: Justificante");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 442, 300);
		setLocationRelativeTo(null);
		
		pnContenido = new JPanel();
		pnContenido.setBackground(Color.WHITE);
		pnContenido.setLayout(null);
		setContentPane(pnContenido);
		pnContenido.add(getBtFinalizar());
		pnContenido.add(getTxtMensaje());
	}
	
	private String justificante() throws BusinessException {
		CorredorDTO corredor = iiu.obtenerCorredor();
		CarreraDisplayDTO carrera = iiu.obtenerCarreraSeleccionada();
		
		return "Justificante de inscripción - Nombre corredor: " + corredor.getNombre() + 
				"\n" + "Competición: " + corredor.getIdCarrera() + 
				"\n" + "Categoría: " + corredor.getCategoria() +
				"\n" + "Fecha inscripción: " + corredor.getFechaInscripcion() +
				"\n" + "Cantidad a abonar: " + carrera.getPrecio() + "€";
	}
	
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iiu.dispose();
					dispose();
				}
			});
			btFinalizar.setMnemonic('F');
			btFinalizar.setBackground(new Color(0, 128, 0));
			btFinalizar.setForeground(Color.WHITE);
			btFinalizar.setBounds(168, 212, 89, 26);
		}
		return btFinalizar;
	}
	private JTextArea getTxtMensaje() throws BusinessException {
		if (txtMensaje == null) {
			txtMensaje = new JTextArea();
			txtMensaje.setFont(new Font("Tahoma", Font.BOLD, 13));
			txtMensaje.setBounds(10, 22, 408, 176);
			txtMensaje.setText(justificante());
		}
		return txtMensaje;
	}
}
