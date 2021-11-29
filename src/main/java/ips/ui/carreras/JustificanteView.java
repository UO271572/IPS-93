package ips.ui.carreras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.corredores.CorredorDTO;
import ips.business.inscripciones.InscripcionController;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.pagos.PagoTransferenciaBancariaModel;
import ips.ui.MenuInscripcionView;

public class JustificanteView extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel pnContenido;
    private JButton btFinalizar;

    private MenuInscripcionView miv;
    private JTextArea txtMensaje;

    public JustificanteView(MenuInscripcionView miv) throws BusinessException {
	this.miv = miv;

	setTitle("Inscripción: Justificante");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setModal(true);
	setBounds(100, 100, 578, 366);
	setLocationRelativeTo(null);

	pnContenido = new JPanel();
	pnContenido.setBackground(Color.WHITE);
	setContentPane(pnContenido);
	pnContenido.setLayout(new BorderLayout(0, 0));
	pnContenido.add(getBtFinalizar(), BorderLayout.SOUTH);
	pnContenido.add(getTxtMensaje());
    }

    private String justificante() throws BusinessException {
	CorredorDTO corredor = miv.getCorredor();
	CarreraDisplayDTO carrera = miv.getCarrera();

	return "Justificante de inscripción - Nombre corredor: " + corredor.getNombre() + "\n" + "Competición: "
		+ corredor.getIdCarrera() + "\n" + "Categoría: " + corredor.getCategoria() + "\n"
		+ "Fecha inscripción: " + corredor.getFechaInscripcion() + "\n" + "Cantidad a abonar: "
		+ carrera.getPrecio() + "€";
    }

    /**
     * Emite el justificante
     * 
     * @throws BusinessException
     */
    protected String emitirJustificante() throws BusinessException { // [ADRI]
	String datos = "";

	try {
	    CorredorDTO corredor = miv.getCorredor();
	    FileWriter myWriter = new FileWriter(
		    "justificante" + corredor.getEmail() + corredor.getIdCarrera() + ".txt");

	    datos = "Datos de la cuenta a abonar \n" + "Titular: Carreras INC.\n" + "Número de cuenta: 123456789\n"
		    + "IBAN: 123456 123456 123456 123456\n" + "El corredor " + corredor.getNombre() + " "
		    + corredor.getApellidos() + " que se dispone a correr en la carrera " + corredor.getIdCarrera()
		    + " debe abonar " + miv.getCarrera().getPrecio() + "€ a dicha cuenta";

	    // COMPROBAR LISTA DE ESPERA
	    CarrerasModel model = new CarrerasModel();

	    if (model.getInscritosCarrera(corredor.getIdCarrera()) >= model
		    .getPlazasDisponibles(corredor.getIdCarrera())) {
		datos += "\nAVISO: se le ha añadido a una lista de espera para esta carrera";
	    }
	    // COMPROBAR LISTA DE ESPERA

	    myWriter.write(datos);

	    myWriter.close();

	    InscripcionController controller = new InscripcionController(new PagoTransferenciaBancariaModel());
	    corredor.setEstadoInscripcion("Inscrito");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return datos;
    }

    private JButton getBtFinalizar() {
	if (btFinalizar == null) {
	    btFinalizar = new JButton("Finalizar");
	    btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btFinalizar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    miv.dispose();
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
	    txtMensaje.setText(justificante() + "\n" + emitirJustificante());
	}
	return txtMensaje;
    }

}
