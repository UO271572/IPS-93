package ips.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
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
    private JPanel panel;
    private JLabel label;

    public MenuInscripcionClubView() {
	setTitle("Menu corredor: Incripcion por lotes");
	setResizable(false);
	getContentPane().setBackground(new Color(255, 248, 220));
	modelCorredores = new DefaultTableModel();
	getContentPane().setLayout(null);
	getContentPane().add(getBtnInscribir());
	// getContentPane().add(getTableCorredores());
	getContentPane().add(getBtnFinalizar());
	getContentPane().add(getScrollPaneCorredores());
	getContentPane().add(getTxClub());
	getContentPane().add(getLblClub());
	getContentPane().add(getLblCarrera());
	getContentPane().add(getBtnEligeUnFichero());
	getContentPane().add(getPanel());
	getContentPane().add(getLabel());
	setModal(true);
	setBounds(100, 100, 999, 478);
	setLocationRelativeTo(null);

    }

    public JLabel getLblNombre() {
	if (lblNombre == null) {
	    lblNombre = new JLabel("Nombre:");
	    lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNombre.setBounds(13, 25, 55, 13);
	}
	return lblNombre;
    }

    public JLabel getLblApellidos() {
	if (lblApellidos == null) {
	    lblApellidos = new JLabel("Apellidos:");
	    lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblApellidos.setBounds(13, 48, 67, 21);
	}
	return lblApellidos;
    }

    public JDateChooser getDateChooser() {
	if (dateChooser == null) {
	    dateChooser = new JDateChooser();
	    dateChooser.setBounds(166, 89, 175, 19);
	}
	return dateChooser;
    }

    public JLabel getLblFechaDeNacimiento() {
	if (lblFechaDeNacimiento == null) {
	    lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
	    lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblFechaDeNacimiento.setBounds(12, 91, 162, 13);
	}
	return lblFechaDeNacimiento;
    }

    public JLabel getLblDni() {
	if (lblDni == null) {
	    lblDni = new JLabel("DNI:");
	    lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblDni.setBounds(14, 188, 77, 13);
	}
	return lblDni;
    }

    public JLabel getLblEmail() {
	if (lblEmail == null) {
	    lblEmail = new JLabel("Email:");
	    lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblEmail.setBounds(13, 140, 80, 13);
	}
	return lblEmail;
    }

    public JLabel getLblSexo() {
	if (lblSexo == null) {
	    lblSexo = new JLabel("Sexo:");
	    lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblSexo.setBounds(13, 231, 73, 13);
	}
	return lblSexo;
    }

    public JTextField getTxNombre() {
	if (txNombre == null) {
	    txNombre = new JTextField();
	    txNombre.setBounds(88, 20, 254, 19);
	    txNombre.setColumns(10);
	}
	return txNombre;
    }

    public JTextField getTxApellidos() {
	if (txApellidos == null) {
	    txApellidos = new JTextField();
	    txApellidos.setBounds(89, 51, 252, 19);
	    txApellidos.setColumns(10);
	}
	return txApellidos;
    }

    public JTextField getTxDNI() {
	if (txDNI == null) {
	    txDNI = new JTextField();
	    txDNI.setBounds(88, 185, 251, 19);
	    txDNI.setColumns(10);
	}
	return txDNI;
    }

    public JTextField getTxEmail() {
	if (txEmail == null) {
	    txEmail = new JTextField();
	    txEmail.setBounds(89, 137, 251, 19);
	    txEmail.setColumns(10);
	}
	return txEmail;
    }

    public JComboBox<String> getCbSexo() {
	if (cbSexo == null) {
	    cbSexo = new JComboBox<String>();
	    cbSexo.setBackground(new Color(255, 248, 220));
	    cbSexo.setBounds(88, 225, 250, 19);
	    cbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Hombre", "Mujer" }));
	}
	return cbSexo;
    }

    public JButton getBtnInscribir() {
	if (btnInscribir == null) {
	    btnInscribir = new JButton("Añadir");
	    btnInscribir.setBackground(new Color(255, 248, 220));
	    btnInscribir.setBounds(792, 415, 82, 21);
	}
	return btnInscribir;
    }

    public JButton getBtnFinalizar() {
	if (btnFinalizar == null) {
	    btnFinalizar = new JButton("Finalizar");
	    btnFinalizar.setBackground(new Color(255, 248, 220));
	    btnFinalizar.setBounds(880, 415, 82, 21);
	}
	return btnFinalizar;
    }

    public DefaultTableModel getCorredoresModel() {
	return modelCorredores;
    }

    public JScrollPane getScrollPaneCorredores() {
	if (scrollPaneCorredores == null) {
	    scrollPaneCorredores = new JScrollPane();
	    scrollPaneCorredores.setBounds(386, 48, 576, 355);
	    scrollPaneCorredores.setViewportView(getTableCorredores());
	}
	return scrollPaneCorredores;
    }

    public JTable getTableCorredores() {
	if (tableCorredores == null) {
	    tableCorredores = new JTable(modelCorredores);
	    tableCorredores.setForeground(new Color(0, 0, 0));
	    tableCorredores.setBackground(new Color(255, 248, 220));
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
	    txClub.setBounds(81, 56, 155, 19);
	    txClub.setColumns(10);
	}
	return txClub;
    }

    public JLabel getLblClub() {
	if (lblClub == null) {
	    lblClub = new JLabel("Club:");
	    lblClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblClub.setBounds(25, 59, 46, 13);
	}
	return lblClub;
    }

    public JLabel getLblCarrera() {
	if (lblCarrera == null) {
	    lblCarrera = new JLabel("Carrera:");
	    lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblCarrera.setBounds(25, 24, 71, 13);
	}
	return lblCarrera;
    }

    public JButton getBtnEligeUnFichero() {
	if (btnEligeUnFichero == null) {
	    btnEligeUnFichero = new JButton("Elige un fichero");
	    btnEligeUnFichero.setBackground(new Color(255, 248, 220));
	    btnEligeUnFichero.setBounds(22, 105, 164, 21);
	}
	return btnEligeUnFichero;
    }

    public JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setBackground(new Color(255, 248, 220));
	    panel.setBorder(new TitledBorder(null, "Formulario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel.setBounds(21, 146, 352, 259);
	    panel.setLayout(null);
	    panel.add(getTxNombre());
	    panel.add(getLblNombre());
	    panel.add(getLblEmail());
	    panel.add(getTxEmail());
	    panel.add(getLblApellidos());
	    panel.add(getTxApellidos());
	    panel.add(getLblFechaDeNacimiento());
	    panel.add(getDateChooser());
	    panel.add(getLblDni());
	    panel.add(getTxDNI());
	    panel.add(getLblSexo());
	    panel.add(getCbSexo());
	}
	return panel;
    }

    public JLabel getLabel() {
	if (label == null) {
	    label = new JLabel("");
	    label.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    label.setBounds(81, 24, 46, 15);
	}
	return label;
    }
}
