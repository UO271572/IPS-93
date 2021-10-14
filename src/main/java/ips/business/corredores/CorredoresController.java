package ips.business.corredores;

import java.util.List;

import ips.business.BusinessCheck;
import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
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

	public List<CorredorDTO> getCorredoresByIdCarrera(int idCarrera) throws BusinessException {
		CarrerasController carrerasController = new CarrerasController(new CarrerasModel(),new CarrerasView());
		//Si da nulo es que no existe y no hay que hacer nada mas
		CarreraDisplayDTO carrera = carrerasController.findByIdCarrera(idCarrera);
		List<CorredorDTO> corredores=model.getListaCorredoresByIdCarrera(idCarrera);
		return corredores;
	}
	
	public String getCategoría(String genero, int edad) {
		
		String res = "";
		
		if(genero.toLowerCase().equals("hombre")) {
			res += getCategoríaEdad(edad) + " - Masculino";
		} else if(genero.toLowerCase().equals("mujer")) {
			res += getCategoríaEdad(edad) + " - Femenino";
		} else {
			throw new RuntimeException("Error: género no válido");
		}
		
		return res;
	}

	private String getCategoríaEdad(int edad) {
		
		String res = "";
		
		if(edad>40) {
			res += "Adulto - Mayor de 40 años";
		} else if (edad >= 35) {
			res += "Adulto - Entre 35 y 39 años";
		} else if (edad >= 30) {
			res += "Mayor - Entre 30 y 34 años";
		} else if (edad >= 25) {
			res += "Mayor - Entre 25 y 29 años";
		} else if (edad >= 20) {
			res += "Juvenil - Entre 20 y 24 años";
		} else if (edad >= 15) {
			res += "Juvenil - Entre 15 y 19 años";
		} else if (edad >= 10) {
			res += "Infantil - Entre 10 y 14 años";
		}
			
		return res;
	}

}
