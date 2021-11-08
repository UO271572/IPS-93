package ips.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;

import com.toedter.calendar.JDateChooser;

import ips.business.categorias.CategoriaDTO;

public class MenuCrearCarreraView extends JDialog {
    private JTextField txNombre;
    private JLabel lblNombre;
    private JLabel lblLugar;
    private JTextField txLugar;
    private JLabel lblTipo;
    private JRadioButton rdbtnCiudad;
    private JRadioButton rdbtnMontaa;
    private JLabel lblDistancia;
    private JSpinner spDistancia;
    private JLabel lblKm;
    private JLabel lblPlazasTotales;
    private JSpinner spPlazas;
    private JDatePicker datePicker;
    private JButton btnGuardar;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JDateChooser fechaCompeticion;
    private JButton btnCancelar;
    private JLabel lblFechaDeCompeticion;
    private JLabel lbPlazasReservadas;
    private JSpinner spPlazasReservadas;
    private JScrollPane scrollPanePlazos;
    private JTable tablePlazos;
    private DefaultTableModel modelPlazos;
    private JButton btnAnadir;
    private JButton btnBorrar;
    private JPanel pnCrearCategorias;
    private JButton btnAñadir;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JScrollPane scrollPane;
    private JScrollPane panel_Categorias;
    private JList lista_Categorias;
    private JPanel pnCrearCategorias_1;

    public MenuCrearCarreraView() {
	setResizable(false);
	setTitle("Organizador: Creacion de carrera");
	setBounds(100, 100, 892, 437);
	setLocationRelativeTo(null);
	modelPlazos = new DefaultTableModel();
	getContentPane().setBackground(Color.WHITE);
	getContentPane().setLayout(null);
	getContentPane().add(getTxNombre());
	getContentPane().add(getLblNombre());
	getContentPane().add(getLblLugar());
	getContentPane().add(getTxLugar());
	getContentPane().add(getLblTipo());
	getContentPane().add(getRdbtnCiudad());
	getContentPane().add(getRdbtnMontana());
	getContentPane().add(getLblDistancia());
	getContentPane().add(getSpDistancia());
	getContentPane().add(getLblKm());
	getContentPane().add(getLblPlazasTotales());
	getContentPane().add(getSpPlazas());
	getContentPane().add(getBtnGuardar());
	getContentPane().add(getFechaCompeticion());
	getContentPane().add(getBtnCancelar());
	getContentPane().add(getLblFechaDeCompeticion());
	getContentPane().add(getLbPlazasReservadas());
	getContentPane().add(getSpPlazasReservadas());
	// getContentPane().add(getBtnModificar());
	getContentPane().add(getPnCrearCategorias());
	getContentPane().add(getPnCrearCategorias_1());
	this.setModal(true);
    }

    public JTextField getTxNombre() {
	if (txNombre == null) {
	    txNombre = new JTextField();
	    txNombre.setBounds(69, 55, 148, 19);
	    txNombre.setColumns(10);
	}
	return txNombre;
    }

    public JLabel getLblNombre() {
	if (lblNombre == null) {
	    lblNombre = new JLabel("Nombre:");
	    lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNombre.setBounds(13, 58, 72, 13);
	}
	return lblNombre;
    }

    public JLabel getLblLugar() {
	if (lblLugar == null) {
	    lblLugar = new JLabel("Lugar:");
	    lblLugar.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblLugar.setBounds(264, 49, 46, 26);
	}
	return lblLugar;
    }

    public JTextField getTxLugar() {
	if (txLugar == null) {
	    txLugar = new JTextField();
	    txLugar.setBounds(320, 55, 138, 19);
	    txLugar.setColumns(10);
	}
	return txLugar;
    }

    public JLabel getLblTipo() {
	if (lblTipo == null) {
	    lblTipo = new JLabel("Tipo:");
	    lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblTipo.setBounds(492, 54, 61, 17);
	}
	return lblTipo;
    }

    public JRadioButton getRdbtnCiudad() {
	if (rdbtnCiudad == null) {
	    rdbtnCiudad = new JRadioButton("Ciudad");
	    buttonGroup.add(rdbtnCiudad);
	    rdbtnCiudad.setBackground(Color.WHITE);
	    rdbtnCiudad.setBounds(596, 32, 105, 21);
	    rdbtnCiudad.setSelected(true);
	}
	return rdbtnCiudad;
    }

    public JRadioButton getRdbtnMontana() {
	if (rdbtnMontaa == null) {
	    rdbtnMontaa = new JRadioButton("Montaña");
	    buttonGroup.add(rdbtnMontaa);
	    rdbtnMontaa.setBackground(Color.WHITE);
	    rdbtnMontaa.setBounds(596, 67, 105, 21);
	}
	return rdbtnMontaa;
    }

    public JLabel getLblDistancia() {
	if (lblDistancia == null) {
	    lblDistancia = new JLabel("Distancia:");
	    lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblDistancia.setBounds(13, 120, 72, 13);
	}
	return lblDistancia;
    }

    public JSpinner getSpDistancia() {
	if (spDistancia == null) {
	    SpinnerModel model = new SpinnerNumberModel(1.0, 0.1, 100000.0, 0.1);
	    spDistancia = new JSpinner(model);
	    spDistancia.setBounds(79, 119, 138, 20);
	}
	return spDistancia;
    }

    public JLabel getLblKm() {
	if (lblKm == null) {
	    lblKm = new JLabel("km");
	    lblKm.setBounds(225, 122, 46, 13);
	}
	return lblKm;
    }

    public JLabel getLblPlazasTotales() {
	if (lblPlazasTotales == null) {
	    lblPlazasTotales = new JLabel("Plazas totales:");
	    lblPlazasTotales.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblPlazasTotales.setBounds(264, 120, 123, 13);
	}
	return lblPlazasTotales;
    }

    public JSpinner getSpPlazas() {
	if (spPlazas == null) {
	    SpinnerModel model = new SpinnerNumberModel(0, 0, 100000, 1);
	    spPlazas = new JSpinner(model);
	    spPlazas.setBounds(357, 119, 101, 20);

	}
	return spPlazas;
    }

    public JButton getBtnGuardar() {
	if (btnGuardar == null) {
	    btnGuardar = new JButton("Guardar");
	    btnGuardar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	    });
	    btnGuardar.setBounds(692, 377, 85, 21);
	}
	return btnGuardar;
    }

    public JDateChooser getFechaCompeticion() {
	if (fechaCompeticion == null) {
	    fechaCompeticion = new JDateChooser();
	    fechaCompeticion.setBounds(642, 120, 101, 19);
	}
	return fechaCompeticion;
    }

    public JButton getBtnCancelar() {
	if (btnCancelar == null) {
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBounds(793, 377, 85, 21);
	}
	return btnCancelar;
    }

    public JLabel getLblFechaDeCompeticion() {
	if (lblFechaDeCompeticion == null) {
	    lblFechaDeCompeticion = new JLabel("Fecha de competicion:");
	    lblFechaDeCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblFechaDeCompeticion.setBounds(492, 120, 148, 13);
	}
	return lblFechaDeCompeticion;
    }

    private JLabel getLbPlazasReservadas() {
	if (lbPlazasReservadas == null) {
	    lbPlazasReservadas = new JLabel("Plazas reservadas:");
	    lbPlazasReservadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lbPlazasReservadas.setBounds(264, 168, 118, 19);
	}
	return lbPlazasReservadas;
    }

    public JSpinner getSpPlazasReservadas() {
	if (spPlazasReservadas == null) {
	    SpinnerModel model = new SpinnerNumberModel(0, 0, 100000, 1);
	    spPlazasReservadas = new JSpinner(model);
	    spPlazasReservadas.setBounds(379, 169, 79, 19);
	}
	return spPlazasReservadas;
    }

    private JPanel getPnCrearCategorias() {
	if (pnCrearCategorias == null) {
	    pnCrearCategorias = new JPanel();
	    pnCrearCategorias.setBackground(Color.WHITE);
	    pnCrearCategorias.setBorder(
		    new TitledBorder(null, "Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pnCrearCategorias.setBounds(13, 198, 422, 169);
	    pnCrearCategorias.setLayout(null);
	    pnCrearCategorias.add(getScrollPane());
	    pnCrearCategorias.add(getBtnAñadir());
	    pnCrearCategorias.add(getBtnModificar());
	    pnCrearCategorias.add(getBtnEliminar());
	    pnCrearCategorias.add(getPanel_Categorias());
	}
	return pnCrearCategorias;
    }

    public JButton getBtnAñadir() {
	if (btnAñadir == null) {
	    btnAñadir = new JButton("Añadir");
	    btnAñadir.setBounds(332, 23, 80, 40);

	}
	return btnAñadir;
    }

    public JScrollPane getScrollPanePlazos() {
	if (scrollPanePlazos == null) {
	    scrollPanePlazos = new JScrollPane();
	    scrollPanePlazos.setBounds(10, 23, 279, 136);
	    scrollPanePlazos.setViewportView(getTablePlazos());
	}
	return scrollPanePlazos;
    }

    public JTable getTablePlazos() {
	if (tablePlazos == null) {
	    tablePlazos = new JTable(modelPlazos);
	    modelPlazos.addColumn("Fecha inicio");
	    modelPlazos.addColumn("Fecha fin");
	    modelPlazos.addColumn("Cuota");
	    tablePlazos.setDefaultEditor(Object.class, null);

	}
	return tablePlazos;
    }

    public DefaultTableModel getTablePlazosModel() {
	return modelPlazos;
    }

    public JButton getBtnAnadir() {
	if (btnAnadir == null) {
	    btnAnadir = new JButton("Añadir");
	    btnAnadir.setBounds(299, 39, 85, 38);
	}
	return btnAnadir;
    }

    public JButton getBtnBorrar() {
	if (btnBorrar == null) {
	    btnBorrar = new JButton("Eliminar");
	    btnBorrar.setBounds(299, 106, 85, 38);
	}
	return btnBorrar;
    }

    public JButton getBtnModificar() {
	if (btnModificar == null) {
	    btnModificar = new JButton("Modificar");
	    btnModificar.setBounds(332, 70, 80, 40);

	    // esto abre un jdialog con las mismas opciones que el de arriba pero
	    // autocompletado con lo que ya hay
	}
	return btnModificar;
    }

    public JButton getBtnEliminar() {
	if (btnEliminar == null) {
	    btnEliminar = new JButton("Eliminar");
	    btnEliminar.setBounds(332, 116, 80, 40);
	}
	return btnEliminar;
    }

    private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(0, 0, 2, 2);
	}
	return scrollPane;
    }

    private JScrollPane getPanel_Categorias() {
	if (panel_Categorias == null) {
	    panel_Categorias = new JScrollPane();
	    panel_Categorias.setBounds(10, 23, 311, 133);
	    panel_Categorias.setViewportView(getLista_Categorias());

	    // aqui tendria que hacer un select all de la tabla categorias y rellenarla
	}
	return panel_Categorias;
    }

    public JList getLista_Categorias() {
	if (lista_Categorias == null) {
	    lista_Categorias = new JList<CategoriaDTO>();
	}
	return lista_Categorias;
    }

    private JPanel getPnCrearCategorias_1() {
	if (pnCrearCategorias_1 == null) {
	    pnCrearCategorias_1 = new JPanel();
	    pnCrearCategorias_1.setLayout(null);
	    pnCrearCategorias_1.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Plazos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    pnCrearCategorias_1.setBackground(Color.WHITE);
	    pnCrearCategorias_1.setBounds(446, 198, 422, 169);
	    pnCrearCategorias_1.add(getScrollPanePlazos());
	    pnCrearCategorias_1.add(getBtnAnadir());
	    pnCrearCategorias_1.add(getBtnBorrar());
	}
	return pnCrearCategorias_1;
    }
}
