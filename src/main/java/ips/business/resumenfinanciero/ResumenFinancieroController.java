package ips.business.resumenfinanciero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ips.persistence.resumenfinanciero.ResumenFinancieroModel;
import ips.ui.resumenfinanciero.ResumenFinancieroView;

public class ResumenFinancieroController {

    private ResumenFinancieroView view;
    private ResumenFinancieroModel model;
    private int idCarrera;

    // private CerrarVentanaAction cerrarVentana = new CerrarVentanaAction();

    public ResumenFinancieroController(ResumenFinancieroView view, int idCarrera, String nombreCarrera) {

	model = new ResumenFinancieroModel();
	this.view = view;
	this.idCarrera = idCarrera;

	view.getBtnCerrar().addActionListener(new CerrarVentanaAction());
	view.getLblInscAnN().setText(getNumeroInscripcionesAnuladas());
	view.getLblInscConfN().setText(getNumeroInscripcionesConfirmadas());
	view.getLblTotalDevolverN().setText(getTotalADevolver());
	view.getLblTotalIngresadoN().setText(getTotalIngresado());
	view.getLblNombreCarrera().setText(idCarrera + "");
	view.getLblTotalN()
		.setText((model.getTotalIngresado(idCarrera) - model.getTotalADevolver(idCarrera)) + " euros");
    }

    private String getTotalIngresado() {
	return model.getTotalIngresado(idCarrera) + "";
    }

    private String getTotalADevolver() {
	return model.getTotalADevolver(idCarrera) + "";
    }

    private String getNumeroInscripcionesConfirmadas() {
	return model.getInscripcionesConfirmadas(idCarrera) + "";
    }

    private String getNumeroInscripcionesAnuladas() {
	return model.getInscripcionesAnuladas(idCarrera) + "";
    }

    class CerrarVentanaAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    view.dispose();
	}

    }

}
