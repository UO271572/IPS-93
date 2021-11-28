package ips.ui;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class MenuInscripcionClubView extends JDialog {
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JDateChooser dateChooser;
    private JLabel lblFechaDeNacimiento;
    private JLabel lblDni;
    private JLabel lblEmail;
    private JLabel lblSexo;
    private JTextField txNombre;
    private JTextField txApellidos;
    private JTextField txDNI;
    private JTextField txEmail;
    private JComboBox<String> cbSexo;
    private JButton btnInscribir;
    private JButton btnFinalizar;
    private DefaultTableModel modelCorredores;
    private JScrollPane scrollPaneCorredores;
    private JTable tableCorredores;
    private JTextField txClub;
    private JLabel lblClub;
    private JLabel lblCarrera;
    private JButton btnEligeUnFichero;

    public MenuInscripcionClubView() {
	getContentPane().setBackground(Color.WHITE);
	modelCorredores = new DefaultTableModel();
	getContentPane().setLayout(null);
	getContentPane().add(getLblNombre());
	getContentPane().add(getLblApellidos());
	getContentPane().add(getDateChooser());
	getContentPane().add(getLblFechaDeNacimiento());
	getContentPane().add(getLblDni());
	getContentPane().add(getLblSexo());
	getContentPane().add(getTxNombre());
	getContentPane().add(getTxApellidos());
	getContentPane().add(getTxDNI());
	getContentPane().add(getTxEmail());
	getContentPane().add(getCbSexo());
	getContentPane().add(getBtnInscribir());
	// getContentPane().add(getTableCorredores());
	getContentPane().add(getBtnFinalizar());
	getContentPane().add(getLblEmail());
	getContentPane().add(getScrollPaneCorredores());
	getContentPane().add(getTxClub());
	getContentPane().add(getLblClub());
	getContentPane().add(getLblCarrera());
	getContentPane().add(getBtnEligeUnFichero());
	setModal(true);
	setBounds(100, 100, 892, 478);
	setLocationRelativeTo(null);

    }

    public JLabel getLblNombre() {
	if (lblNombre == null) {
	    lblNombre = new JLabel("Nombre:");
	    lblNombre.setBounds(57, 153, 41, 13);
	}
	return lblNombre;
    }

    public JLabel getLblApellidos() {
	if (lblApellidos == null) {
	    lblApellidos = new JLabel("Apellidos:");
	    lblApellidos.setBounds(57, 176, 45, 13);
	}
	return lblApellidos;
    }

    public JDateChooser getDateChooser() {
	if (dateChooser == null) {
	    dateChooser = new JDateChooser();
	    dateChooser.setBounds(165, 208, 69, 19);
	}
	return dateChooser;
    }

    public JLabel getLblFechaDeNacimiento() {
	if (lblFechaDeNacimiento == null) {
	    lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
	    lblFechaDeNacimiento.setBounds(57, 214, 98, 13);
	}
	return lblFechaDeNacimiento;
    }

    public JLabel getLblDni() {
	if (lblDni == null) {
	    lblDni = new JLabel("DNI:");
	    lblDni.setBounds(57, 237, 22, 13);
	}
	return lblDni;
    }

    public JLabel getLblEmail() {
	if (lblEmail == null) {
	    lblEmail = new JLabel("Email:");
	    lblEmail.setBounds(57, 282, 29, 13);
	}
	return lblEmail;
    }

    public JLabel getLblSexo() {
	if (lblSexo == null) {
	    lblSexo = new JLabel("Sexo:");
	    lblSexo.setBounds(57, 308, 26, 13);
	}
	return lblSexo;
    }

    public JTextField getTxNombre() {
	if (txNombre == null) {
	    txNombre = new JTextField();
	    txNombre.setBounds(135, 150, 96, 19);
	    txNombre.setColumns(10);
	}
	return txNombre;
    }

    public JTextField getTxApellidos() {
	if (txApellidos == null) {
	    txApellidos = new JTextField();
	    txApellidos.setBounds(135, 173, 96, 19);
	    txApellidos.setColumns(10);
	}
	return txApellidos;
    }

    public JTextField getTxDNI() {
	if (txDNI == null) {
	    txDNI = new JTextField();
	    txDNI.setBounds(135, 234, 96, 19);
	    txDNI.setColumns(10);
	}
	return txDNI;
    }

    public JTextField getTxEmail() {
	if (txEmail == null) {
	    txEmail = new JTextField();
	    txEmail.setBounds(138, 279, 96, 19);
	    txEmail.setColumns(10);
	}
	return txEmail;
    }

    public JComboBox<String> getCbSexo() {
	if (cbSexo == null) {
	    cbSexo = new JComboBox<String>();
	    cbSexo.setBounds(135, 305, 155, 19);
	    cbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Hombre", "Mujer" }));
	}
	return cbSexo;
    }

    public JButton getBtnInscribir() {
	if (btnInscribir == null) {
	    btnInscribir = new JButton("Añadir");
	    btnInscribir.setBounds(559, 376, 63, 21);
	}
	return btnInscribir;
    }

    public JButton getBtnFinalizar() {
	if (btnFinalizar == null) {
	    btnFinalizar = new JButton("Finalizar");
	    btnFinalizar.setBounds(643, 376, 71, 21);
	}
	return btnFinalizar;
    }

    public DefaultTableModel getCorredoresModel() {
	return modelCorredores;
    }

    public JScrollPane getScrollPaneCorredores() {
	if (scrollPaneCorredores == null) {
	    scrollPaneCorredores = new JScrollPane();
	    scrollPaneCorredores.setBounds(352, 55, 516, 284);
	    scrollPaneCorredores.setViewportView(getTableCorredores());
	}
	return scrollPaneCorredores;
    }

    public JTable getTableCorredores() {
	if (tableCorredores == null) {
	    tableCorredores = new JTable(modelCorredores);
	    tableCorredores.setBackground(Color.WHITE);
	    tableCorredores.setBounds(873, 275, -434, -259);
	    modelCorredores.addColumn("Nombre");
	    modelCorredores.addColumn("Apellidos");
	    modelCorredores.addColumn("DNI");
	    modelCorredores.addColumn("Fecha de nacimiento");
	    modelCorredores.addColumn("Email");
	    modelCorredores.addColumn("Sexo");
	    tableCorredores.setDefaultEditor(Object.class, null);
	}
	return tableCorredores;
    }

    public JTextField getTxClub() {
	if (txClub == null) {
	    txClub = new JTextField();
	    txClub.setBounds(135, 121, 96, 19);
	    txClub.setColumns(10);
	}
	return txClub;
    }

    public JLabel getLblClub() {
	if (lblClub == null) {
	    lblClub = new JLabel("Club:");
	    lblClub.setBounds(57, 124, 46, 13);
	}
	return lblClub;
    }

    public JLabel getLblCarrera() {
	if (lblCarrera == null) {
	    lblCarrera = new JLabel("carrera");
	    lblCarrera.setBounds(60, 10, 46, 13);
	}
	return lblCarrera;
    }

    public JButton getBtnEligeUnFichero() {
	if (btnEligeUnFichero == null) {
	    btnEligeUnFichero = new JButton("Elige un fichero");
	    btnEligeUnFichero.setBounds(57, 85, 137, 21);
	}
	return btnEligeUnFichero;
    }
}
