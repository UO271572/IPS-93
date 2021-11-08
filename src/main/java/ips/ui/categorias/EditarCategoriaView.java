package ips.ui.categorias;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class EditarCategoriaView extends JDialog {

    private JPanel contentPane;
    private JPanel pn_Nombre;
    private JTextField txtNombre;
    private JPanel pn_EdadInicio;
    private JPanel pn_EdadFin;
    private JPanel pn_Sexo;
    private JButton btnTerminar;
    private JComboBox cb_sexo;
    private JSpinner spinner_edadInicio;
    private JSpinner spinner_edadFinal;
    private JList listaCategorias;

    /**
     * Launch the application.
     */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditarCategoriaView frame = new EditarCategoriaView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

    /**
     * Create the frame.
     * 
     * @param jList
     */
    public EditarCategoriaView(JList lista) {
	setModal(true);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 571, 310);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPanel_3());
	contentPane.add(getPn_EdadInicio());
	contentPane.add(getPn_EdadFin());
	contentPane.add(getPn_Sexo());
	contentPane.add(getBtnTerminar());
	this.listaCategorias = lista;

	setLocationRelativeTo(null);
    }

    private JPanel getPanel_3() {
	if (pn_Nombre == null) {
	    pn_Nombre = new JPanel();
	    pn_Nombre.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pn_Nombre.setBounds(91, 20, 303, 47);
	    pn_Nombre.setLayout(new GridLayout(0, 1, 0, 0));
	    pn_Nombre.add(getTxtNombre());
	}
	return pn_Nombre;
    }

    public JTextField getTxtNombre() {
	if (txtNombre == null) {
	    txtNombre = new JTextField();
	    txtNombre.setColumns(10);
	}
	return txtNombre;
    }

    private JPanel getPn_EdadInicio() {
	if (pn_EdadInicio == null) {
	    pn_EdadInicio = new JPanel();
	    pn_EdadInicio.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Edad inicio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    pn_EdadInicio.setBounds(91, 77, 303, 47);
	    pn_EdadInicio.setLayout(new GridLayout(0, 1, 0, 0));
	    pn_EdadInicio.add(getSpinner_edadInicio());
	}
	return pn_EdadInicio;
    }

    private JPanel getPn_EdadFin() {
	if (pn_EdadFin == null) {
	    pn_EdadFin = new JPanel();
	    pn_EdadFin.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Edad fin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    pn_EdadFin.setBounds(91, 134, 303, 47);
	    pn_EdadFin.setLayout(new GridLayout(0, 1, 0, 0));
	    pn_EdadFin.add(getSpinner_edadFinal());
	}
	return pn_EdadFin;
    }

    private JPanel getPn_Sexo() {
	if (pn_Sexo == null) {
	    pn_Sexo = new JPanel();
	    pn_Sexo.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sexo",
		    TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    pn_Sexo.setBounds(91, 191, 303, 47);
	    pn_Sexo.setLayout(new GridLayout(0, 1, 0, 0));
	    pn_Sexo.add(getCb_sexo());
	}
	return pn_Sexo;
    }

    public JButton getBtnTerminar() {
	if (btnTerminar == null) {
	    btnTerminar = new JButton("Terminar");
	    btnTerminar.setBounds(462, 242, 85, 21);
	}
	return btnTerminar;
    }

    public JComboBox getCb_sexo() {
	if (cb_sexo == null) {
	    cb_sexo = new JComboBox();

	    String[] sexos = new String[2];
	    sexos[0] = "Masculino";
	    sexos[1] = "Femenino";

	    cb_sexo.setModel(new DefaultComboBoxModel<String>(sexos));

	}
	return cb_sexo;
    }

    public JSpinner getSpinner_edadInicio() {
	if (spinner_edadInicio == null) {
	    spinner_edadInicio = new JSpinner();
	    spinner_edadInicio.setModel(new SpinnerNumberModel(18, 18, 130, 1));
	}
	return spinner_edadInicio;
    }

    public JSpinner getSpinner_edadFinal() {
	if (spinner_edadFinal == null) {
	    spinner_edadFinal = new JSpinner();
	    spinner_edadFinal.setModel(new SpinnerNumberModel(18, 18, 130, 1));
	}
	return spinner_edadFinal;
    }

    public JList getListaCategorias() {
	return listaCategorias;
    }
}
