package ips.ui.carreras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ips.business.BusinessException;
import ips.ui.inscripciones.EstadoInscripcionesView;

public class JustificanteCancelacionView extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel pnContenido;
    private JTextArea txtMensaje;
    private JButton btFinalizar;

    private EstadoInscripcionesView view;

    private double importe;

    public JustificanteCancelacionView(EstadoInscripcionesView view) throws BusinessException {
	this.view = view;

	setTitle("Cancelación inscripción: Justificante");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setModal(true);
	setBounds(100, 100, 578, 366);
	setLocationRelativeTo(null);

	pnContenido = new JPanel();
	pnContenido.setBackground(Color.WHITE);
	setContentPane(pnContenido);
	pnContenido.setLayout(new BorderLayout(0, 0));
	pnContenido.add(getTxtMensaje());
	pnContenido.add(getBtFinalizar(), BorderLayout.SOUTH);
    }

    private JButton getBtFinalizar() {
	if (btFinalizar == null) {
	    btFinalizar = new JButton("Finalizar");
	    btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btFinalizar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    view.dispose();
		    dispose();
		}
	    });
	    btFinalizar.setMnemonic('F');
	    btFinalizar.setBackground(new Color(0, 128, 0));
	    btFinalizar.setForeground(Color.WHITE);
	}
	return btFinalizar;
    }

    private JTextArea getTxtMensaje() throws BusinessException {
	if (txtMensaje == null) {
	    txtMensaje = new JTextArea();
	    txtMensaje.setFont(new Font("Tahoma", Font.BOLD, 13));
	    String justificante = "Justificante de inscripción:\nNombre corredor: " + view.getCorredor().getNombre()
		    + "\nFecha cancelación: " + Time.valueOf(LocalTime.now()) + "\nImporte a devolver: " + importe
		    + "€";
	    txtMensaje.setText(justificante);
	}
	return txtMensaje;
    }

    public void setImporte(double precio) {
	this.importe = precio;
    }

}
