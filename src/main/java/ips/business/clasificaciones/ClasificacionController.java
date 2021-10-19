/**
 * 
 */
package ips.business.clasificaciones;

import java.util.Collection;
import java.util.List;

import ips.business.BusinessCheck;
import ips.business.BusinessException;
import ips.persistence.clasificaciones.ClasificacionModel;

/**
 * @author PC
 *
 */
public class ClasificacionController {
	private ClasificacionModel model;
//	private ClasificacionView view;
	
	public ClasificacionController(ClasificacionModel model) {
		this.model = model;
	}
	
	
	public List<ClasificacionDTO> mostrarResultadosPorSexo(int idcarrera) throws BusinessException{
		List<ClasificacionDTO> listaDto = model.getClasificacionBySex(idcarrera);
		BusinessCheck.isFalse(listaDto.isEmpty(),"No hay clasificacion disponible");
		return listaDto;
	}


	public List<ClasificacionDTO> mostrarResultadosPorCategoria(int idcarrera) throws BusinessException {
		List<ClasificacionDTO> listaDto = model.getClasificacionByCategoria(idcarrera);
		BusinessCheck.isFalse(listaDto.isEmpty(),"No hay clasificacion disponible");
		return listaDto;
	}
}
