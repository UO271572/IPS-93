/**
 * 
 */
package ips.persistence.clasificaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ips.business.clasificaciones.ClasificacionDTO;
import ips.util.Database;
import ips.util.Jdbc;

/**
 * @author PC
 *
 */
public class ClasificacionModel {
	//cl.posicion, c.sexo, c.nombre , cl.time
	private Database db = new Database();
	public static final String SQL_CLASIFICACION_SEXO ="SELECT cl.posicion, c.sexo, c.nombre,c.dnicorredor,cl.idcarrera,cl.time from resultados cl,corredores c where(idcarrera = ? and c.dnicorredor = cl.dnicorredor) order by c.sexo,cl.time";
	
	public static final String SQL_CLASIFICACION_CATEGORIA ="SELECT cl.posicion, c.sexo, c.nombre,c.dnicorredor,cl.idcarrera,cl.time from resultados cl,corredores c where(idcarrera = ? and c.dnicorredor = cl.dnicorredor) order by c.categoria,cl.time";
	
	public List<ClasificacionDTO> getClasificacionBySex(int idcarrera) throws SQLException{
		List<ClasificacionDTO> list = new ArrayList();
		Connection conn =Jdbc.createThreadConnection();
		PreparedStatement ps = conn.prepareStatement(SQL_CLASIFICACION_SEXO);
		ps.setInt(1, idcarrera);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			list.add(new ClasificacionDTO(rs.getInt(1),rs.getString(2),rs.getString(3) ,rs.getString(4) , rs.getInt(5)));
		}
		
		return list;
		
	}
	
	public List<ClasificacionDTO> getClasificacionByCategoria(int idcarrera) throws SQLException{
		//return db.executeQueryPojo(ClasificacionDTO.class, SQL_CLASIFICACION_CATEGORIA,idcarrera);
		List<ClasificacionDTO> list = new ArrayList();
		Connection conn =Jdbc.createThreadConnection();
		PreparedStatement ps = conn.prepareStatement(SQL_CLASIFICACION_CATEGORIA);
		ps.setInt(1, idcarrera);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			list.add(new ClasificacionDTO(rs.getInt(1),rs.getString(2),rs.getString(3) ,rs.getString(4) , rs.getInt(5)));
		}
		
		return list;
		
	}
	
}
