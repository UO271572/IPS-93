/**
 * 
 */
package ips.business.dorsales;

import java.util.List;

import ips.business.BusinessException;
import ips.business.inscripciones.InscripcionDTO;
import ips.persistence.dorsales.DorsalesModel;
import ips.ui.inscripciones.DorsalesView;

/**
 * @author PC
 *
 */
public class DorsalesController {
	
	private DorsalesModel dorsalModel;

	public DorsalesController (DorsalesModel dorsalModel) {
		this.dorsalModel =dorsalModel;
	}

	
	/**
	 * Obtiene las inscripciones para una determinada carrera
	 * @param idCarrera
	 * @return
	 */
	public List<InscripcionDTO> getInscripciones(int idCarrera){
		List<InscripcionDTO> lista = dorsalModel.getInscripciones(idCarrera);
		//checks
		return lista;
	}
	
	/**
	 * Asigna los dorsales para los corredores de una carrera
	 * @param idCarrera
	 * @throws BusinessException 
	 */
	public void asignarDorsales(int idCarrera) throws BusinessException {
		dorsalModel.asignarDorsales(idCarrera);
	}
	
	
	
	
}
