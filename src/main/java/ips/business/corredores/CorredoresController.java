package ips.business.corredores;

import java.util.List;

import ips.business.BusinessException;
import ips.business.carreras.CarrerasController;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.corredores.CorredoresModel;
import ips.ui.carreras.CarrerasView;
import ips.ui.corredores.CorredoresView;

public class CorredoresController {

    private CorredoresModel model;
    private CorredoresView view;

    public CorredoresController(CorredoresModel model, CorredoresView view) {
	this.model = model;
	this.view = view;
    }

    public CorredoresController() {
	this.model = new CorredoresModel();
	this.view = new CorredoresView();
    }

    public List<CorredorDTO> getCorredoresByIdCarrera(int idCarrera) throws BusinessException {
	CarrerasController carrerasController = new CarrerasController(new CarrerasModel(), new CarrerasView());
	// Si da nulo es que no existe y no hay que hacer nada mas
	// CarreraDisplayDTO carrera = carrerasController.findByIdCarrera(idCarrera);
	List<CorredorDTO> corredores = model.getListaCorredoresByIdCarrera(idCarrera);
	return corredores;
    }

    // [ADRI]
    public List<CorredorDTO> getCorredorByEmail(String email) throws BusinessException {
	List<CorredorDTO> corredores = model.getCorredorByEmail(email);
	return corredores;
    }

    public CorredorDTO getCorredorRegistradoByEmail(String email) {
	List<CorredorDTO> corredores = model.getEmailCorredores();
	for (CorredorDTO corredor : corredores) {
	    if (corredor.getEmail().equals(email))
		return corredor;
	}
	return null;
    }

    public void addCorredor(CorredorDTO corredor) {
	model.addCorredor(corredor);
    }

    public List<CorredorDTO> getCorredorByDNI(String dniCorredor) {
	return model.findCorredorByDNI(dniCorredor);
    }

    public void updateCorredor(CorredorDTO corredor) {
	model.updateCorredor(corredor);
    }

}
