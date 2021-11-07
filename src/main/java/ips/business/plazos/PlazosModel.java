package ips.business.plazos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ips.util.Jdbc;
import ips.util.UnexpectedException;

public class PlazosModel {

	private static final String SQL_INSERT_CARRERA = "INSERT INTO plazos (idcarrera,fechainicio,fechafin,cuota) "
			+ "VALUES (?,?,?,?)";
	
	public void insertPlazo(PlazoDTO plazo) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.createThreadConnection();
			
			pst = c.prepareStatement(SQL_INSERT_CARRERA);		
			pst.setInt(1, plazo.getIdCarrera());
			pst.setDate(2, plazo.getFechaInicio());
			pst.setDate(3, plazo.getFechaFin());
			pst.setDouble(4,plazo.getCuota());
			
							
			pst.executeUpdate();
			c.close();
			pst.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e);}
		finally {
			Jdbc.close(pst);
			
		}
	}
	
}
