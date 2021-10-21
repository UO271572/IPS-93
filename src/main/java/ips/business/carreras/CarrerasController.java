package ips.business.carreras;

import java.util.List;

import ips.business.BusinessCheck;
import ips.business.BusinessException;
import ips.persistence.carreras.CarrerasModel;
import ips.ui.carreras.CarrerasView;

public class CarrerasController {

	private CarrerasModel model;
	private CarrerasView view;
	
	public CarrerasController(CarrerasModel m, CarrerasView v) {
		this.model = m;
		this.view = v;
	}
	
	public CarrerasController() {
		model = new CarrerasModel();
		view = new CarrerasView();
	}

	public  List<CarreraDisplayDTO> getListaCarreras() throws BusinessException {
		List<CarreraDisplayDTO> carreras=model.getListaCarreras();
		BusinessCheck.isTrue(!carreras.isEmpty(),"No hay carreras disponibles");
		return carreras;
	}

	public CarreraDisplayDTO findByIdCarrera(int idCarrera) throws BusinessException {
		List<CarreraDisplayDTO> carrera = model.getCarreraByIdCarrera(idCarrera);
		BusinessCheck.isTrue(!carrera.isEmpty(),"La carrera no existe");
		return carrera.get(0);
	}
	
	
	// [ADRI]
	
	public List<CarreraDisplayDTO> getCarrerasInscripcion() throws BusinessException {
		List<CarreraDisplayDTO> carreras = model.getCarrerasInscripcion();
		BusinessCheck.isTrue(!carreras.isEmpty(),"No hay carreras disponibles para la inscripción");
		return carreras;
	}
	
	public List<CarreraDisplayDTO> getCarrerasById(String idCarrera) throws BusinessException {
		List<CarreraDisplayDTO> carreras = model.getCarreraById(idCarrera);
		BusinessCheck.isTrue(!carreras.isEmpty(),"No hay carreras con dicho identificador");
		return carreras;
	}
	
	public List<CarreraDisplayDTO> getCompeticiones() throws BusinessException {
		CarrerasController carrerasController = new CarrerasController(new CarrerasModel(),new CarrerasView());
        return carrerasController.getListaCarreras();
	}
	
	public CarreraDisplayDTO[] getCompeticionesInscripcion() throws BusinessException {
		CarrerasController carrerasController = new CarrerasController(new CarrerasModel(),new CarrerasView());
        return carrerasController.getCarrerasInscripcion().toArray(new CarreraDisplayDTO[carrerasController.getCarrerasInscripcion().size()]);
	}
	
	
}
