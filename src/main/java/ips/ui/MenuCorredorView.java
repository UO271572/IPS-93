package ips.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.ui.carreras.CarrerasView;
import ips.ui.carreras.EstadoInscripcionesView;
import ips.ui.carreras.InscripcionView;
import ips.util.Printer;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuCorredorView extends JFrame{
	private JComboBox cbCorredores;
	private JScrollPane scrollPane;
	private JList listCorredores;
	private JButton btnGo;
	private JButton btnVerInscripciones;
	private JButton btnInscribirse;
	
	public MenuCorredorView() {
		setResizable(false);

		setBounds(100, 100, 854, 427);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getCbCorredores());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnGo());
		getContentPane().add(getBtnVerInscripciones());
		getContentPane().add(getBtnInscribirse());
	}
	
	public JComboBox getCbCorredores() {
		if (cbCorredores == null) {
			cbCorredores = new JComboBox();
			cbCorredores.setBounds(57, 41, 316, 30);
		}
		return cbCorredores;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(57, 115, 357, 146);
			scrollPane.setViewportView(getListCorredores());
		}
		return scrollPane;
	}
	public JList getListCorredores() {
		if (listCorredores == null) {
			listCorredores = new JList();
		}
		return listCorredores;
	}
	public JButton getBtnGo() {
		if (btnGo == null) {
			btnGo = new JButton("Go");
			btnGo.setBounds(171, 81, 85, 21);
		}
		return btnGo;
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
			btnVerInscripciones.setBounds(572, 342, 134, 38);
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
			btnInscribirse.setBounds(725, 342, 105, 38);
		}
		return btnInscribirse;
	}
}
