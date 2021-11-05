package ips.business.plazos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JOptionPane;

import ips.ui.VentanaCrearPlazoView;

public class VentanaCrearPlazoController {

	private VentanaCrearPlazoView view;
	private Date fechaCompeticion;
	
	public VentanaCrearPlazoController(VentanaCrearPlazoView plazoView, java.sql.Date date) {
		this.view = plazoView;
		this.fechaCompeticion = date;
		initController();
	}
	
	private void initController() {
		view.getBtnGuardar().addActionListener(cambiarAVentanaCrearCarrera());
	}

	
	private ActionListener cambiarAVentanaCrearCarrera() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String comprobacion = checkCamposPlazo();
				if(!comprobacion.isEmpty())
					JOptionPane.showMessageDialog(null, comprobacion);
			}
		};
	}
	
	private String checkCamposPlazo() {
		if(view.getFechaFin().getDate().compareTo(view.getFechaInicio().getDate())==0)
			return "La fecha fin es igual a la fecha inicio. Porfavor, corríjalo.";
		if(view.getFechaFin().getDate().before(view.getFechaInicio().getDate()))
			return "La fecha fin es anterior a la fecha inicio. Porfavor, corríjalo.";
		if(fechaCompeticion.before(view.getFechaFin().getDate()))
			return "La fecha de competicion ha de ser mas lejana que la de fin de plazo.";
		return "";
	}
	
}
