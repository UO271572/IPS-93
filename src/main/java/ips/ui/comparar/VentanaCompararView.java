package ips.ui.comparar;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaCompararView extends JDialog {
    private JPanel pnCarrerasCompetidas;
    private JTable tablaCarrerasCompetidas;
    private JButton btnGenerarClasificacion;
    private JPanel pnClasificacion;
    private JTable tablaClasificacion;
    private JButton btnCompararAtleta;
    private JPanel pnComparacion;
    private JTable tablaComparacion;
    private JButton btnCerrar;

    private DefaultTableModel modeloCarrerasCompetidas;
    private DefaultTableModel modeloClasificacion;
    private JScrollPane spCarrerasCompetidas;
    private JScrollPane spClasificacion;
    private JScrollPane spComparacion;

    /**
     * Create the dialog.
     */
    public VentanaCompararView() {
	modeloCarrerasCompetidas = new DefaultTableModel();
	modeloClasificacion = new DefaultTableModel();

	setModal(true);
	setTitle("Menú Corredor: Comparación");
	setBounds(100, 100, 1057, 522);
	getContentPane().setLayout(new BorderLayout());
	{
	    JPanel pnCentro = new JPanel();
	    getContentPane().add(pnCentro, BorderLayout.CENTER);
	    pnCentro.setLayout(null);
	    pnCentro.add(getPnCarrerasCompetidas());
	    pnCentro.add(getPnClasificacion());
	    pnCentro.add(getPnComparacion());
	    pnCentro.add(getBtnCerrar());
	}
    }

    private JPanel getPnCarrerasCompetidas() {
	if (pnCarrerasCompetidas == null) {
	    pnCarrerasCompetidas = new JPanel();
	    pnCarrerasCompetidas.setBounds(36, 36, 446, 243);
	    pnCarrerasCompetidas.setLayout(new BorderLayout(0, 0));
	    pnCarrerasCompetidas.add(getBtnGenerarClasificacion(), BorderLayout.SOUTH);
	    pnCarrerasCompetidas.add(getSpCarrerasCompetidas(), BorderLayout.CENTER);
	}
	return pnCarrerasCompetidas;
    }

    private JTable getTablaCarrerasCompetidas() {
	if (tablaCarrerasCompetidas == null) {
	    tablaCarrerasCompetidas = new JTable(modeloCarrerasCompetidas);
	    modeloCarrerasCompetidas.addColumn("ID Carrera");
	    modeloCarrerasCompetidas.addColumn("Nombre");
	    modeloCarrerasCompetidas.addColumn("Fecha");
	    modeloCarrerasCompetidas.addColumn("Tipo");
	    modeloCarrerasCompetidas.addColumn("Lugar");
	    modeloCarrerasCompetidas.addColumn("Distancia");
	    modeloCarrerasCompetidas.addColumn("Plazas disponibles");
	    tablaCarrerasCompetidas.setDefaultEditor(Object.class, null);
	}
	return tablaCarrerasCompetidas;
    }

    public JButton getBtnGenerarClasificacion() {
	if (btnGenerarClasificacion == null) {
	    btnGenerarClasificacion = new JButton("Generar clasificación");
	}
	return btnGenerarClasificacion;
    }

    private JPanel getPnClasificacion() {
	if (pnClasificacion == null) {
	    pnClasificacion = new JPanel();
	    pnClasificacion.setBounds(561, 36, 446, 243);
	    pnClasificacion.setLayout(new BorderLayout(0, 0));
	    pnClasificacion.add(getBtnCompararAtleta(), BorderLayout.SOUTH);
	    pnClasificacion.add(getSpClasificacion(), BorderLayout.CENTER);
	}
	return pnClasificacion;
    }

    public JTable getTablaClasificacion() {
	if (tablaClasificacion == null) {
	    tablaClasificacion = new JTable(modeloClasificacion);
	}
	return tablaClasificacion;
    }

    private JButton getBtnCompararAtleta() {
	if (btnCompararAtleta == null) {
	    btnCompararAtleta = new JButton("Comparar con Atleta");
	}
	return btnCompararAtleta;
    }

    private JPanel getPnComparacion() {
	if (pnComparacion == null) {
	    pnComparacion = new JPanel();
	    pnComparacion.setBounds(36, 310, 972, 103);
	    pnComparacion.setLayout(new BorderLayout(0, 0));
	    pnComparacion.add(getSpComparacion(), BorderLayout.CENTER);
	}
	return pnComparacion;
    }

    private JTable getTablaComparacion() {
	if (tablaComparacion == null) {
	    tablaComparacion = new JTable();
	}
	return tablaComparacion;
    }

    private JButton getBtnCerrar() {
	if (btnCerrar == null) {
	    btnCerrar = new JButton("Cerrar");
	    btnCerrar.setBounds(922, 454, 85, 21);
	}
	return btnCerrar;
    }

    public DefaultTableModel getModeloCarrerasCompetidas() {
	return modeloCarrerasCompetidas;
    }

    private JScrollPane getSpCarrerasCompetidas() {
	if (spCarrerasCompetidas == null) {
	    spCarrerasCompetidas = new JScrollPane();
	    spCarrerasCompetidas.setViewportView(getTablaCarrerasCompetidas());
	}
	return spCarrerasCompetidas;
    }

    private JScrollPane getSpClasificacion() {
	if (spClasificacion == null) {
	    spClasificacion = new JScrollPane();
	    spClasificacion.setViewportView(getTablaClasificacion());
	}
	return spClasificacion;
    }

    private JScrollPane getSpComparacion() {
	if (spComparacion == null) {
	    spComparacion = new JScrollPane();
	    spComparacion.setViewportView(getTablaComparacion());
	}
	return spComparacion;
    }
}
