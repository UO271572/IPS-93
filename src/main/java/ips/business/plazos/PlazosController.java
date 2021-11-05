package ips.business.plazos;

import java.util.List;

public class PlazosController {

	private PlazosModel model;
	
	public PlazosController() {
		this.model = new PlazosModel();
	}

	public void insertPlazos(PlazoDTO plazo) {
		model.insertPlazo(plazo);
	}

	public void insertPlazos(List<PlazoDTO> plazos) {
		for(PlazoDTO plazo: plazos) {
			model.insertPlazo(plazo);
		}
	}
	
}
