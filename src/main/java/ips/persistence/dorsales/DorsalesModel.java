package ips.persistence.dorsales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import ips.business.BusinessException;
import ips.business.corredores.CorredorDTO;
import ips.business.inscripciones.InscripcionDTO;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.corredores.CorredoresModel;
import ips.util.Database;
import ips.util.Jdbc;
import ips.util.UnexpectedException;

public class DorsalesModel {
	private Database db = new Database();
	public final String SQL_FIND_ALL_BY_DORSAL = "select * from inscripciones where idcarrera = ? and estadoinscripcion = 'INSCRITO' order by dorsal";
	public final String SQL_FIND_CORREDORES_ORDER_BY_FECHA = "select c.dnicorredor, c.nombre "
			+ "from corredores c, inscripciones i "
			+ "where i.idcarrera = ? and i.estadoinscripcion = 'INSCRITO' and c.dnicorredor = i.dnicorredor "
			+ "order by i.fechainscripcion";
//	public final String SQL_INSERT_ALL = "INSERT INTO inscripciones (dnicorredor,idcarrera,estadoinscripcion,fechainscripcion,dorsal,incidencia) "
//			+ "VALUES (?,?,?,?,?,?)";
//	public final String SQL_INSERT ="update inscripciones set dorsal = ? where dnicorredor = ? and idcarrera = ?";
	public final String SQL_ASIGNAR_DORSAL = "UPDATE INSCRIPCIONES SET dorsal = ? where dnicorredor = ? and idcarrera = ? and estadoinscripcion = 'INSCRITO'";
	public final String SQL_SIGUIENTE_DORSAL = "select dorsal from inscripciones where idcarrera = ? order by dorsal";
	public static final String SQL_FIND_MAX_DORSAL = "select max(dorsal) from inscripciones where idcarrera = ?";
	/**
	 * Devuelve la lista de inscripciones de una carrera ordenados por el numero de
	 * dorsal
	 * 
	 * @param idcarrera
	 * @return
	 */
	public List<InscripcionDTO> getInscripciones(int idcarrera) {
		return db.executeQueryPojo(InscripcionDTO.class, SQL_FIND_ALL_BY_DORSAL, idcarrera);
	}

	/**
	 * Asigna dorsal a los corredores de una carrera
	 * @param idCarrera
	 * @throws BusinessException
	 */
	public void asignarDorsales(int idCarrera) throws BusinessException {
		//encontrar corredores para la carrera
		CorredoresModel corredor = new CorredoresModel();
		List<CorredorDTO> corredores = db.executeQueryPojo(CorredorDTO.class, SQL_FIND_CORREDORES_ORDER_BY_FECHA, idCarrera);
		
		//obtener el maximo numero de plazas (segun la carrera)
		CarrerasModel carrera = new CarrerasModel();
		int plazas = carrera.getPlazasDisponibles(idCarrera);
		
		//Obtener las plazas reservadas
		int reservadas = carrera.getPlazasReservadas(idCarrera);
		
		//comprobar que el numero de corredores no excede las plazas
		if(corredores.size() > (plazas-reservadas) ) {
			JOptionPane.showMessageDialog(null, "El numero de corredores inscritos supera al de las plazas que dispone la carrera");
			throw new BusinessException("El numero de corredores inscritos supera al de las plazas que dispone la carrera");
		}
		
		//obtener el numero de dorsal que le toca al corredor
		//recorrer los corredores y asignarles el dorsal
		for(CorredorDTO c : corredores) {
			String dni = c.getDniCorredor();
			int dorsal = encuentraDorsal(idCarrera,plazas,reservadas);
			db.executeUpdate(SQL_ASIGNAR_DORSAL, dorsal,dni,idCarrera);
		}
		
	}

	private int encuentraDorsal(int idcarrera,int plazas ,int reservadas) {
//		List<Integer> resultset = db.executeQueryPojo(Integer.class, SQL_SIGUIENTE_DORSAL, idcarrera);
//		int tamaño = resultset.size();
//		
//		for (int i = 0; i < tamaño; i++) {
//			int dorsalPrevio = resultset.get(i);
//			int dorsalPosterior = resultset.get(i + 1);
//			if(dorsalPosterior - dorsalPrevio > 1) {
//				return dorsalPrevio + 1;
//			}
//		}
//		return -1;
		
		Connection c = null;
		PreparedStatement pst = null;
		int dorsal = 0;
		try {
			c = Jdbc.createThreadConnection();
			
			pst = c.prepareStatement(SQL_FIND_MAX_DORSAL);		
			pst.setInt(1, idcarrera);
							
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				dorsal = rs.getInt(1);
			}
			rs.close();
			c.close();
		} catch (SQLException e) {
			throw new UnexpectedException(e);}
		finally {
			Jdbc.close(pst);
		}
		
		
		
//		int dorsal = db.executeQueryPojo(Integer.class, SQL_FIND_MAX_DORSAL, idcarrera).get(0);
		if(dorsal == 0 || dorsal <=reservadas) {
			dorsal = reservadas + 1;
			return dorsal;
		}
		return dorsal;
	}

}
