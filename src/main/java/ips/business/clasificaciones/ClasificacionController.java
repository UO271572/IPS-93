/**
 * 
 */
package ips.business.clasificaciones;

import java.sql.SQLException;
import java.util.List;

import ips.business.BusinessCheck;
import ips.business.BusinessException;
import ips.business.categorias.CategoriaDTO;
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

    public List<CategoriaDTO> obtenerCategorias(int idcarrera) throws BusinessException, SQLException {
	List<CategoriaDTO> listaDto = model.getCategorias(idcarrera);
	BusinessCheck.isFalse(listaDto.isEmpty(), "No hay clasificacion disponible");
	return listaDto;
    }

    public List<ClasificacionDTO> obtenerCategoriasFiltrado(int idcarrera, String sexo, int edadinicio, int edadfin)
	    throws BusinessException, SQLException {
	List<ClasificacionDTO> listaDto = model.getClasificacion(idcarrera, sexo, edadfin, edadfin);
	BusinessCheck.isFalse(listaDto.isEmpty(), "No hay clasificacion disponible");
	return listaDto;
    }

//    public List<ClasificacionDTO> mostrarResultadosPorCategoria(int idcarrera) throws BusinessException, SQLException {
//	List<ClasificacionDTO> listaDto = model.getClasificacionByCategoria(idcarrera);
//	BusinessCheck.isFalse(listaDto.isEmpty(), "No hay clasificacion disponible");
//	return listaDto;
//    }
//
//    // Para el boton de clasificacion sin filtros
//    public List<ClasificacionDTO> mostrarResultados(int idCarrera) throws BusinessException, SQLException {
//	List<ClasificacionDTO> listaDto = model.getClasificacion(idCarrera); // sin filtros
//	BusinessCheck.isFalse(listaDto.isEmpty(), "No hay clasificacion disponible");
//	return listaDto;
//    }
}
