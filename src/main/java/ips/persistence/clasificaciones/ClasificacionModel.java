/**
 * 
 */
package ips.persistence.clasificaciones;

import java.util.List;

import ips.business.clasificaciones.ClasificacionDTO;
import ips.util.Database;

/**
 * @author PC
 *
 */
public class ClasificacionModel {
	private Database db = new Database();
	private static final String SQL_CLASIFICACION_SEXO ="SELECT cl.posicion, c.sexo, c.nombre , cl.time from resultados cl,corredores c "
			+ "where(idcarrera = ?) order by c.sexo , time";
	private static final String SQL_CLASIFICACION_CATEGORIA ="SELECT cl.posicion, c.sexo, c.nombre , cl.time from resultados cl,corredores c "
			+ "where(idcarrera = ?) order by c.categoria, time";
	
	public List<ClasificacionDTO> getClasificacionBySex(int idcarrera){
		return db.executeQueryPojo(ClasificacionDTO.class, SQL_CLASIFICACION_SEXO,idcarrera);
	}
	
	public List<ClasificacionDTO> getClasificacionByCategoria(int idcarrera){
		return db.executeQueryPojo(ClasificacionDTO.class, SQL_CLASIFICACION_CATEGORIA,idcarrera);
	}
	
}
