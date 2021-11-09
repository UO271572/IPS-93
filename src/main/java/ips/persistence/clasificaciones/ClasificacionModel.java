/**
 * 
 */
package ips.persistence.clasificaciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ips.business.categorias.CategoriaDTO;
import ips.business.clasificaciones.ClasificacionDTO;
import ips.util.Database;
import ips.util.Jdbc;

/**
 * @author PC
 *
 */
public class ClasificacionModel {
    Database db = new Database();
    // cl.posicion, c.sexo, c.nombre , cl.time
//	private Database db = new Database();
    public static final String SQL_CLASIFICACION_SEXO = "SELECT cl.posicion, c.sexo, c.nombre,c.dnicorredor,cl.idcarrera,cl.time from resultados cl,corredores c where(idcarrera = ? and c.dnicorredor = cl.dnicorredor) order by c.sexo,cl.time";

    public static final String SQL_CLASIFICACION_CATEGORIA = "SELECT cl.posicion, c.sexo, c.nombre,c.dnicorredor,cl.idcarrera,cl.time from resultados cl,corredores c where(idcarrera = ? and c.dnicorredor = cl.dnicorredor) order by c.categoria,cl.time";

    public static final String SQL_CLASIFICACION = "SELECT c.sexo, c.nombre,c.dnicorredor,cl.idcarrera from resultados cl,corredores c where(idcarrera = ? and c.dnicorredor = cl.dnicorredor)"; // quite
																								 // posicion
																								 // y
																								 // tiempo
																								 // temporalmente

    // -----AMPLIACION
    public static final String SQL_CLASIFICACION_POR_EDAD_Y_SEXO = "select distinct c.nombre,cat.nombre,i.dorsal,i.tiempoinicio,i.tiempofin \r\n"
	    + "from corredores as c \r\n" + "join inscripciones as i  on(c.dnicorredor = i.dnicorredor)\r\n"
	    + "join categorias as cat on (cat.idcarrera = i.idcarrera)\r\n"
	    + "where (cat.sexo = ? and i.idcarrera = ? and i.estadoinscripcion = 'INSCRITO' \r\n"
	    + "and ((YEAR(?)-YEAR(c.fechanacimiento)) between (cat.edadinicio = ?) and (cat.edadfin = ?)));";
    // SEXO SERA: Masculino Femenino
    public static final String SQL_CATEGORIAS = "select * from categorias where idcarrera = ?";

    /**
     * Obtiene las categorias de una carrera
     * 
     * @param idcarrera
     * @return
     */
    public List<CategoriaDTO> getCategorias(int idcarrera) {
	return db.executeQueryPojo(CategoriaDTO.class, SQL_CATEGORIAS, idcarrera);
    }

    /**
     * Obtiene las clasificaciones dadas por una carrera y fecha de inicio y fin de
     * la categoria
     * 
     * @param idcarrera
     * @param edadinicio
     * @param edadfin
     * @return
     * @throws SQLException
     */
    public List<ClasificacionDTO> getClasificacion(int idcarrera, String sexo, int edadinicio, int edadfin)
	    throws SQLException {
	List<ClasificacionDTO> list = new ArrayList();
	Connection conn = Jdbc.createThreadConnection();
	PreparedStatement ps = conn.prepareStatement(SQL_CLASIFICACION_POR_EDAD_Y_SEXO);
	ps.setString(1, sexo);
	ps.setInt(2, idcarrera);
	Date date = new Date(Calendar.getInstance().getTime().getTime());
	ps.setDate(3, date);
	ps.setInt(4, edadinicio);
	ps.setInt(5, edadfin);
	ResultSet rs = ps.executeQuery();
	ClasificacionDTO dto = new ClasificacionDTO();
	while (rs.next()) {
	    dto.setNombre(rs.getString(1));
	    dto.setCategoriaperteneciente(rs.getString(2));
	    dto.setDorsal(rs.getInt(3));
	    dto.setTimeinicial(rs.getTime(4));
	    dto.setTimefinal(rs.getTime(5));
	    list.add(dto);
	}
	return list;
    }

//    public List<ClasificacionDTO> getClasificacionBySex(int idcarrera) throws SQLException {
//	List<ClasificacionDTO> list = new ArrayList();
//	Connection conn = Jdbc.createThreadConnection();
//	PreparedStatement ps = conn.prepareStatement(SQL_CLASIFICACION_SEXO);
//	ps.setInt(1, idcarrera);
//	ResultSet rs = ps.executeQuery();
//	while (rs.next()) {
//	    list.add(new ClasificacionDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
//		    rs.getTime(6)));
//	}
//
//	return list;
//
//    }
//
//    public List<ClasificacionDTO> getClasificacionByCategoria(int idcarrera) throws SQLException {
//	// return db.executeQueryPojo(ClasificacionDTO.class,
//	// SQL_CLASIFICACION_CATEGORIA,idcarrera);
//	List<ClasificacionDTO> list = new ArrayList();
//	Connection conn = Jdbc.createThreadConnection();
//	PreparedStatement ps = conn.prepareStatement(SQL_CLASIFICACION_CATEGORIA);
//	ps.setInt(1, idcarrera);
//	ResultSet rs = ps.executeQuery();
//	while (rs.next()) {
//	    list.add(new ClasificacionDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
//		    rs.getTime(6)));
//	}
//
//	return list;
//
//    }
//
//    public List<ClasificacionDTO> getClasificacion(int idcarrera) throws SQLException {
//	// return db.executeQueryPojo(ClasificacionDTO.class,
//	// SQL_CLASIFICACION_CATEGORIA,idcarrera);
//	List<ClasificacionDTO> list = new ArrayList();
//	Connection conn = Jdbc.createThreadConnection();
//	PreparedStatement ps = conn.prepareStatement(SQL_CLASIFICACION);
//	ps.setInt(1, idcarrera);
//	ResultSet rs = ps.executeQuery();
//	while (rs.next()) {
//	    list.add(new ClasificacionDTO(0, rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
//		    new Time(0)));
//	}
//
//	return list;
//    }

}
