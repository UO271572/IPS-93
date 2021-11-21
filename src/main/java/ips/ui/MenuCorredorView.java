package ips.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ips.business.BusinessException;
import ips.ui.carreras.EstadoInscripcionesView;

public class MenuCorredorView extends JDialog {

    private static final long serialVersionUID = 1L;

    private JLabel lbCarreras;
    private JScrollPane spCarreras;
    private JTable table;
    private JRadioButton rdbtnVerTodas;
    private JRadioButton rdbtnAbiertas;
    private JLabel lbEmail;
    private JTextField tfEmail;
    private JButton btnVerInscripciones;
    private JButton btnInscribirse;
    private JPanel pnFormulario;
    private JLabel lbFormulario;
    private JLabel lbNombre;
    private JTextField tfNombre;
    private JLabel lbApellidos;
    private JTextField tfApellidos;
    private JLabel lbDni;
    private JTextField tfDni;
    private JLabel lbFecha;
    private JSpinner spDia;
    private JSpinner spMes;
    private JSpinner spAño;
    private JLabel lbSexo;
    private JComboBox<String> cbSexo;

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private DefaultTableModel model;

    public MenuCorredorView() {
	model = new DefaultTableModel();

	setTitle("Corredor");
	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setModal(true);
	setBounds(100, 100, 892, 700);
	setLocationRelativeTo(null);

	getContentPane().setBackground(Color.WHITE);
	getContentPane().setLayout(null);
	getContentPane().add(getLbCarreras());
	getContentPane().add(getSpCarreras());
	getContentPane().add(getRdbtnVerTodas());
	getContentPane().add(getRdbtnAbiertas());
	getContentPane().add(getLbEmail());
	getContentPane().add(getTfEmail());
	getContentPane().add(getBtnVerInscripciones());
	getContentPane().add(getBtnInscribirse());
	getContentPane().add(getPnFormulario());
    }

    public DefaultTableModel getTableModel() {
	return model;
    }

    private JLabel getLbCarreras() {
	if (lbCarreras == null) {
	    lbCarreras = new JLabel("Lista de carreras:");
	    lbCarreras.setDisplayedMnemonic('C');
	    lbCarreras.setLabelFor(getSpCarreras());
	    lbCarreras.setBackground(Color.WHITE);
	    lbCarreras.setBounds(37, 36, 129, 19);
	}
	return lbCarreras;
    }

    public JScrollPane getSpCarreras() {
	if (spCarreras == null) {
	    spCarreras = new JScrollPane();
	    spCarreras.setBounds(37, 72, 639, 307);
	    spCarreras.setViewportView(getTable());
	}
	return spCarreras;
    }

    public JTable getTable() {
	if (table == null) {
	    table = new JTable(model);
	    model.addColumn("ID Carrera");
	    model.addColumn("Nombre");
	    model.addColumn("Fecha");
	    model.addColumn("Tipo");
	    model.addColumn("Lugar");
	    model.addColumn("Distancia");
	    model.addColumn("Plazas disponibles");
	    model.addColumn("Lista de espera");
	    table.setDefaultEditor(Object.class, null);
	}
	return table;
    }

    public JRadioButton getRdbtnVerTodas() {
	if (rdbtnVerTodas == null) {
	    rdbtnVerTodas = new JRadioButton("Abiertas");
	    buttonGroup.add(rdbtnVerTodas);
	    rdbtnVerTodas.setBounds(727, 72, 105, 21);
	    rdbtnVerTodas.setSelected(true);
	}
	return rdbtnVerTodas;
    }

    public JRadioButton getRdbtnAbiertas() {
	if (rdbtnAbiertas == null) {
	    rdbtnAbiertas = new JRadioButton("No competidas");
	    buttonGroup.add(rdbtnAbiertas);
	    rdbtnAbiertas.setBounds(727, 109, 105, 21);
	}
	return rdbtnAbiertas;
    }

    private JLabel getLbEmail() {
	if (lbEmail == null) {
	    lbEmail = new JLabel("Introduzca su email:");
	    lbEmail.setDisplayedMnemonic('E');
	    lbEmail.setLabelFor(getTfEmail());
	    lbEmail.setBackground(Color.WHITE);
	    lbEmail.setBounds(37, 410, 129, 19);
	}
	return lbEmail;
    }

    public JTextField getTfEmail() {
	if (tfEmail == null) {
	    tfEmail = new JTextField();
	    tfEmail.setBackground(Color.WHITE);
	    tfEmail.setBounds(176, 407, 217, 25);
	    tfEmail.setColumns(10);
	}
	return tfEmail;
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
			e1.printStackTrace();
		    }
		    estado.setVisible(true);
		}
	    });
	    btnVerInscripciones.setBounds(581, 572, 134, 38);
	}
	return btnVerInscripciones;
    }

    public JButton getBtnInscribirse() {
	if (btnInscribirse == null) {
	    btnInscribirse = new JButton("Inscribirse");
	    btnInscribirse.setEnabled(true);
	    btnInscribirse.setBounds(744, 572, 105, 38);
	}
	return btnInscribirse;
    }

    public JPanel getPnFormulario() {
	if (pnFormulario == null) {
	    pnFormulario = new JPanel();
	    pnFormulario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	    pnFormulario.setBounds(37, 453, 436, 183);
	    pnFormulario.setLayout(null);
	    pnFormulario.setBackground(Color.white);
	    pnFormulario.setVisible(false);

	    pnFormulario.add(getLbFormulario());
	    pnFormulario.add(getLbNombre());
	    pnFormulario.add(getTfNombre());
	    pnFormulario.add(getLbApellidos());
	    pnFormulario.add(getTfApellidos());
	    pnFormulario.add(getLbDni());
	    pnFormulario.add(getTfDni());
	    pnFormulario.add(getLbFecha());
	    pnFormulario.add(getSpDia());
	    pnFormulario.add(getSpMes());
	    pnFormulario.add(getSpAño());
	    pnFormulario.add(getLbSexo());
	    pnFormulario.add(getCbSexo());
	}
	return pnFormulario;
    }

    private JLabel getLbFormulario() {
	if (lbFormulario == null) {
	    lbFormulario = new JLabel("Rellene el siguiente formulario:");
	    lbFormulario.setBounds(10, 11, 199, 25);
	    lbFormulario.setBackground(Color.WHITE);
	}
	return lbFormulario;
    }

    private JLabel getLbNombre() {
	if (lbNombre == null) {
	    lbNombre = new JLabel("Nombre:");
	    lbNombre.setDisplayedMnemonic('N');
	    lbNombre.setLabelFor(getTfNombre());
	    lbNombre.setBackground(Color.WHITE);
	    lbNombre.setBounds(10, 47, 65, 24);
	}
	return lbNombre;
    }

    public JTextField getTfNombre() {
	if (tfNombre == null) {
	    tfNombre = new JTextField();
	    tfNombre.setBackground(Color.WHITE);
	    tfNombre.setBounds(86, 48, 106, 22);
	    tfNombre.setColumns(10);
	}
	return tfNombre;
    }

    private JLabel getLbApellidos() {
	if (lbApellidos == null) {
	    lbApellidos = new JLabel("Apellidos:");
	    lbApellidos.setDisplayedMnemonic('A');
	    lbApellidos.setBackground(Color.WHITE);
	    lbApellidos.setBounds(211, 47, 65, 24);
	}
	return lbApellidos;
    }

    public JTextField getTfApellidos() {
	if (tfApellidos == null) {
	    tfApellidos = new JTextField();
	    tfApellidos.setBackground(Color.WHITE);
	    tfApellidos.setBounds(284, 48, 106, 22);
	    tfApellidos.setColumns(10);
	}
	return tfApellidos;
    }

    private JLabel getLbDni() {
	if (lbDni == null) {
	    lbDni = new JLabel("DNI:");
	    lbDni.setBounds(10, 82, 47, 24);
	    lbDni.setLabelFor(getTfDni());
	    lbDni.setDisplayedMnemonic('D');
	    lbDni.setBackground(Color.WHITE);
	}
	return lbDni;
    }

    public JTextField getTfDni() {
	if (tfDni == null) {
	    tfDni = new JTextField();
	    tfDni.setColumns(10);
	    tfDni.setBackground(Color.WHITE);
	    tfDni.setBounds(75, 82, 106, 22);
	}
	return tfDni;
    }

    private JLabel getLbFecha() {
	if (lbFecha == null) {
	    lbFecha = new JLabel("Fecha de nacimiento:");
	    lbFecha.setBounds(10, 117, 135, 20);
	    lbFecha.setBackground(Color.WHITE);
	}
	return lbFecha;
    }

    public JSpinner getSpDia() {
	if (spDia == null) {
	    spDia = new JSpinner();
	    spDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
	    spDia.setBounds(155, 117, 54, 20);
	}
	return spDia;
    }

    public JSpinner getSpMes() {
	if (spMes == null) {
	    spMes = new JSpinner();
	    spMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
	    spMes.setBounds(229, 118, 47, 20);
	}
	return spMes;
    }

    public JSpinner getSpAño() {
	if (spAño == null) {
	    spAño = new JSpinner();
	    spAño.setModel(new SpinnerNumberModel(1960, 1921, 2003, 1));
	    spAño.setBounds(302, 118, 65, 20);
	}
	return spAño;
    }

    private JLabel getLbSexo() {
	if (lbSexo == null) {
	    lbSexo = new JLabel("Sexo:");
	    lbSexo.setLabelFor(getCbSexo());
	    lbSexo.setDisplayedMnemonic('S');
	    lbSexo.setBounds(10, 148, 47, 24);
	}
	return lbSexo;
    }

    public JComboBox<String> getCbSexo() {
	if (cbSexo == null) {
	    cbSexo = new JComboBox<String>();
	    cbSexo.setBackground(Color.WHITE);
	    cbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Hombre", "Mujer" }));
	    cbSexo.setBounds(75, 149, 106, 22);
	}
	return cbSexo;
    }
}
