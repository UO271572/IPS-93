package ips.business.plazos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ips.ui.VentanaCrearPlazoView;

//TODO lo de los double
public class VentanaCrearPlazoController {

    private final static long MILLISECONDS_IN_A_DAY = (24 * 60 * 60 * 1000);

    private VentanaCrearPlazoView view;
    private Date fechaCompeticion;
    private List<PlazoDTO> plazos;
    private int idCarrera;
    private DefaultTableModel tablePlazosModel;

    public VentanaCrearPlazoController(int idCarrera) {
	/*
	 * this.view = plazoView; this.fechaCompeticion = date;
	 */
	this.plazos = new ArrayList<PlazoDTO>();
	this.idCarrera = idCarrera;
    }

    private void initController() {
	view.getBtnGuardar().addActionListener(cambiarAVentanaCrearCarrera());
	view.getBtnCancelar().addActionListener(cancelar());
    }

    public void setTablePlazosModel(DefaultTableModel tablePlazosModel) {
	this.tablePlazosModel = tablePlazosModel;
    }

    public void setView(VentanaCrearPlazoView view) {
	this.view = view;
    }

    public void setFechaCompeticion(Date fechaCompeticion) {
	this.fechaCompeticion = fechaCompeticion;
	initController();
    }

    public void setListPlazos(List<PlazoDTO> plazos) {
	this.plazos = plazos;
    }

    private ActionListener cambiarAVentanaCrearCarrera() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String comprobacion = checkCamposPlazo();
		if (!comprobacion.isEmpty()) {
		    JOptionPane.showMessageDialog(null, comprobacion);
		    return;
		}
		if (plazos.size() > 0 && !checkPlazosConsecutivos()) {
		    JOptionPane.showMessageDialog(null, "Los plazos han de ir consecutivos");
		    return;
		}
		PlazoDTO plazo = new PlazoDTO(idCarrera, new java.sql.Date(view.getFechaInicio().getDate().getTime()),
			new java.sql.Date(view.getFechaFin().getDate().getTime()),
			(double) (view.getSpCuota().getValue()) * 1.0);
		plazos.add(plazo);
		JOptionPane.showMessageDialog(null,
			"Se ha añadido el siguiente plazo\n\t-Inicio del plazo: " + plazo.getFechaInicio()
				+ "\n\t-Fin del plazo: " + plazo.getFechaFin() + "\n\tCuota: " + plazo.getCuota());
		PlazosController plazos = new PlazosController();
		// plazos.insertPlazo(plazo);
		añadirPlazoPlazoTabla(plazo);

		view.dispose();
	    }

	};
    }

    private ActionListener cancelar() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		int respuesta = JOptionPane.showConfirmDialog(view, "¿Estas seguro de que quieres cancelar?");
		if (respuesta == JOptionPane.YES_OPTION) {
		    view.dispose();
		}

	    }

	};
    }

    private void añadirPlazoPlazoTabla(PlazoDTO plazo) {
	Date fechaInicio = plazo.getFechaInicio();
	Date fechaFin = plazo.getFechaFin();
	double cuota = plazo.getCuota();
	Object[] data = { fechaInicio, fechaFin, cuota };
	tablePlazosModel.insertRow(tablePlazosModel.getRowCount(), data);

    }

    private String checkCamposPlazo() {
	if (view.getFechaFin().getDate().compareTo(view.getFechaInicio().getDate()) == 0)
	    return "La fecha fin es igual a la fecha inicio. Porfavor, corríjalo.";
	if (view.getFechaFin().getDate().before(view.getFechaInicio().getDate()))
	    return "La fecha fin es anterior a la fecha inicio. Porfavor, corríjalo.";
	if (fechaCompeticion.before(view.getFechaFin().getDate()))
	    return "La fecha de competicion ha de ser mas lejana que la de fin de plazo.";
	if ((double) (view.getSpCuota().getValue()) * 1.0 < 0) {
	    return "El valor de la cuota no puede ser negativo";
	}
	return "";
    }

    private boolean checkPlazosConsecutivos() {
	Date date = plazos.get(plazos.size() - 1).getFechaFin();
	long nextDayMilliSeconds = date.getTime() + MILLISECONDS_IN_A_DAY;
	Date nextDate = new Date(nextDayMilliSeconds);
	if (nextDate.toString().equals(new java.sql.Date(view.getFechaInicio().getDate().getTime()).toString())) {
	    return true;
	}
	return false;
    }

}
