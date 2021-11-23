package ips.ui.carreras;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.corredores.CorredorDTO;

public class InscripcionView extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JLabel lbInscripcion;
    private JButton btSiguiente;
    private JButton btCancelar;

    CarrerasController controller;
    private JRadioButton rdbtnTransferenciaBancaria;
    private JRadioButton rbtnPagoTarjeta;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private CorredorDTO corredor;

    public InscripcionView() throws BusinessException {
	controller = new CarrerasController();

	setTitle("Inscripción");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	setBounds(100, 100, 433, 361);
	setLocationRelativeTo(null);
	setModal(true);

	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(null);
	setContentPane(contentPane);
	contentPane.add(getLbInscripcion());
	contentPane.add(getBtSiguiente());
	contentPane.add(getBtCancelar());
	contentPane.add(getRdbtnTransferenciaBancaria());
	contentPane.add(getRbtnPagoTarjeta());
    }

    private JLabel getLbInscripcion() {
	if (lbInscripcion == null) {
	    lbInscripcion = new JLabel("Inscripción");
	    lbInscripcion.setBackground(Color.WHITE);
	    lbInscripcion.setFont(new Font("Arial Black", Font.PLAIN, 35));
	    lbInscripcion.setBounds(42, 41, 270, 42);
	}
	return lbInscripcion;
    }

    public CarreraDisplayDTO[] getCarreras() throws BusinessException {
	CarrerasView view = new CarrerasView();
	return view.getCompeticionesInscripcion();
    }

    private JButton getBtSiguiente() {
	if (btSiguiente == null) {
	    btSiguiente = new JButton("Siguiente");
	    btSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    // btSiguiente.setEnabled(false); [ADRI] Era false al principio
	    btSiguiente.setEnabled(true);
	    btSiguiente.setMnemonic('S');
	    btSiguiente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    try {

			if (getRdbtnTransferenciaBancaria().isSelected()) { // [ADRI]
			    mostrarVentanaJustificante();
			} else {
			    mostrarMenuInscripcion();
			}

		    } catch (BusinessException e1) {
			e1.printStackTrace();
		    }

		}
	    });
	    btSiguiente.setForeground(Color.WHITE);
	    btSiguiente.setBackground(new Color(0, 128, 0));
	    btSiguiente.setBounds(308, 260, 90, 30);
	}
	return btSiguiente;
    }

    /**
     * Crea la nueva ventana para poder inscribirse
     * 
     * @throws BusinessException
     */
    private void mostrarMenuInscripcion() throws BusinessException {
//	MenuInscripcionController menInscripcion = new MenuInscripcionController(new MenuInscripcionView(this));
	// menInscripcion.initController();
//	menInscripcion.getMenuView().setVisible(true);
    }

    /**
     * 
     * @throws BusinessException
     */
    private void mostrarVentanaJustificante() throws BusinessException {
	CorredorDTO corredor = getCorredor();
	CarreraDisplayDTO carrera = obtenerCarreraSeleccionada();
	if (comprobaciones(corredor, carrera)) {
	    corredor.setIdCarrera(carrera.getIdCarrera());
	    carrera.setPlazasDisponibles(carrera.getPlazasDisponibles() - 1);
	    corredor.setEstadoInscripcion("Pre-inscrito");

	    JustificanteView jv = new JustificanteView(this);
	    jv.setVisible(true);
	}
    }

    public CarreraDisplayDTO obtenerCarreraSeleccionada() throws BusinessException {
	return null;
//	return (CarreraDisplayDTO) getCbCarreras().getSelectedItem();
    }

    private boolean comprobaciones(CorredorDTO corredor, CarreraDisplayDTO carrera) throws BusinessException {
	if (corredor.getIdCarrera() == carrera.getIdCarrera()) {
	    JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera en la que no esté ya inscrito", "ERROR",
		    JOptionPane.ERROR_MESSAGE);
//	    getCbCarreras().grabFocus();
	    return false;
	}
	return true;
    }

    private JButton getBtCancelar() {
	if (btCancelar == null) {
	    btCancelar = new JButton("Cancelar");
	    btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btCancelar.setMnemonic('C');
	    btCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	    btCancelar.setBackground(Color.RED);
	    btCancelar.setForeground(Color.WHITE);
	    btCancelar.setBounds(197, 260, 90, 30);
	}
	return btCancelar;
    }

    private JRadioButton getRdbtnTransferenciaBancaria() {
	if (rdbtnTransferenciaBancaria == null) {
	    rdbtnTransferenciaBancaria = new JRadioButton("Transferencia bancaria");
	    rdbtnTransferenciaBancaria.setSelected(true);
	    buttonGroup.add(rdbtnTransferenciaBancaria);
	    rdbtnTransferenciaBancaria.setBackground(Color.WHITE);
	    rdbtnTransferenciaBancaria.setBounds(16, 243, 161, 22);
	}
	return rdbtnTransferenciaBancaria;
    }

    private JRadioButton getRbtnPagoTarjeta() {
	if (rbtnPagoTarjeta == null) {
	    rbtnPagoTarjeta = new JRadioButton("Pago con tarjeta");
	    buttonGroup.add(rbtnPagoTarjeta);
	    rbtnPagoTarjeta.setBackground(Color.WHITE);
	    rbtnPagoTarjeta.setBounds(16, 268, 161, 22);
	}
	return rbtnPagoTarjeta;
    }

    public CorredorDTO getCorredor() {
	return corredor;
    }

    public void setCorredor(CorredorDTO corredor) {
	this.corredor = corredor;
    }
}
