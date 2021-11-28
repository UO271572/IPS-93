package ips.persistence.carreras;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.plazos.PlazoDTO;
import ips.util.Database;
import ips.util.Jdbc;
import ips.util.UnexpectedException;

public class CarrerasModel {

    private Database db = new Database();

    public static final String SQL_LISTA_CARRERAS = "select * from carreras order by fechacompeticion desc";

    public static final String SQL_LISTA_CARRERAS_ABIERTAS = "select * from carreras c where (select min(fechaFin) from plazos p2 where p2.idcarrera = c.idCarrera) <= ? "
	    + "and (select min(fechaFin) from plazos p2 where p2.idcarrera = c.idCarrera) <= ?";

    public static final String SQL_LISTA_CARRERAS_NO_COMPETIDAS = "select * from carreras where fechacompeticion >= ? order by fechacompeticion asc";

    public static final String SQL_CARRERA_BY_IDCARRERA = "SELECT * FROM CARRERAS WHERE IDCARRERA=?"; // ADRI CAMBIO
												      // TEMPORAL and
												      // fechafin>=? and
												      // fechaInicio<=?";

    public static final String SQL_CARRERAS_INSCRIPCION = "SELECT * FROM CARRERAS where fechacompeticion>=? and plazasDisponibles>0";

    public static final String SQL_CARRERA_BY_ID = "SELECT * FROM CARRERAS WHERE IDCARRERA=?";

    public static final String SQL_INSERT_CARRERA = "INSERT INTO carreras (idcarrera,nombre,fechacompeticion,tipo,distancia,plazasdisponibles,plazasreservadas,lugar) "
	    + "VALUES (?,?,?,?,?,?,?,?)";

    public static final String SQL_FIND_MAX_IDCARRERA = "select max(idcarrera) from carreras";

    public static final String SQL_FIND_PLAZAS = "select plazasdisponibles from carreras where idcarrera = ?";
    public static final String SQL_FIND_PLAZASRESERVADAS = "select plazasreservadas from carreras where idcarrera = ?";

    public static final String SQL_FIND_PRECIO_IDCARRERA = "select precio from carreras where idcarrera = ?";

    public static final String SQL_FIND_PLAZOS_IDCARRERA = "select * from plazos where idcarrera = ?";

    public static final String SQL_FIND_CARRERAS_COMPETIDAS_POR_EMAIL = "select * from carreras c, inscripciones i, corredores co\n"
	    + "where co.email = ? and co.dnicorredor = i.dnicorredor\n"
	    + "and i.idcarrera = c.idcarrera and fechacompeticion <= ?";

    public static final String SQL_GET_NUMERO_INSCRIPCIONES_VALIDA = "SELECT COUNT(*) FROM INSCRIPCIONES WHERE IDCARRERA=? AND ESTADOINSCRIPCION<>'ANULADA'";

    public static final String SQL_FIND_MAX_FECHAFIN = "select max(fechafin) from plazos where idcarrera = ? ";

    /**
     * Obtiene la ultima fecha de plazo de una carrera
     * 
     * @param idcarrera
     * @return
     */
    public Date getMaxFechaFin(int idcarrera) {
	// return db.executeQueryPojo(Date.class, SQL_FIND_MAX_FECHAFIN,
	// idcarrera).get(0);
	Connection c = null;
	PreparedStatement pst = null;
	Date date = null;
	try {
	    c = Jdbc.createThreadConnection();

	    pst = c.prepareStatement(SQL_FIND_MAX_FECHAFIN);
	    pst.setInt(1, idcarrera);

	    ResultSet rs = pst.executeQuery();
	    while (rs.next()) {
		date = rs.getDate(1);
	    }
	    rs.close();
	    c.close();
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);
	}
	return date;
    }

    public List<CarreraDisplayDTO> getListaCarreras() {
	// List<CarreraDisplayDTO> listCarreras = new ArrayList<CarreraDisplayDTO>();
	Date fecha = java.sql.Date.valueOf(LocalDate.now());
	return db.executeQueryPojo(CarreraDisplayDTO.class, SQL_LISTA_CARRERAS_ABIERTAS, fecha, fecha);
    }

    public List<CarreraDisplayDTO> getListaCarrerasTodas() {
	// List<CarreraDisplayDTO> listCarreras = new ArrayList<CarreraDisplayDTO>();
	Date fecha = java.sql.Date.valueOf(LocalDate.now());
	return db.executeQueryPojo(CarreraDisplayDTO.class, SQL_LISTA_CARRERAS);
    }

    public List<CarreraDisplayDTO> getListaCarrerasFiltradas() {
	Date fecha = java.sql.Date.valueOf(LocalDate.now());
	return db.executeQueryPojo(CarreraDisplayDTO.class, SQL_LISTA_CARRERAS_NO_COMPETIDAS, fecha);
    }

    public List<CarreraDisplayDTO> getCarreraByIdCarrera(int idCarrera) {
	// FOrmas de no hacer el cast? DUDA
	Date fecha = java.sql.Date.valueOf(LocalDate.now());
	List<CarreraDisplayDTO> carrera = db.executeQueryPojo(CarreraDisplayDTO.class, SQL_CARRERA_BY_IDCARRERA,
		idCarrera);// ,fecha,fecha);
	return carrera;
    }

    // [ADRI]

    public List<CarreraDisplayDTO> getCarrerasInscripcion() {
	Date fecha = java.sql.Date.valueOf(LocalDate.now());
	return db.executeQueryPojo(CarreraDisplayDTO.class, SQL_CARRERAS_INSCRIPCION, fecha);
    }

    public List<CarreraDisplayDTO> getCarreraById(String idCarrera) {
	List<CarreraDisplayDTO> carrera = db.executeQueryPojo(CarreraDisplayDTO.class, SQL_CARRERA_BY_ID, idCarrera);
	return carrera;
    }

    /// Distintas
    public void insertarCarrera(CarreraDisplayDTO carrera) {
	Connection c = null;
	PreparedStatement pst = null;

	try {
	    c = Jdbc.createThreadConnection();
	    pst = c.prepareStatement(SQL_INSERT_CARRERA);
	    pst.setInt(1, carrera.getIdCarrera());
	    pst.setString(2, carrera.getNombre());
	    pst.setDate(3, carrera.getFechaCompeticion());
	    pst.setString(4, carrera.getTipo());
	    pst.setDouble(5, carrera.getDistancia());
	    pst.setInt(6, carrera.getPlazasDisponibles());
	    pst.setInt(7, carrera.getPlazasReservadas());
	    pst.setString(8, carrera.getLugar());

	    pst.executeUpdate();
	    c.close();
	    pst.close();
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);

	}

    }

    public int getPlazasDisponibles(int idcarrera) {
	Connection c = null;
	PreparedStatement pst = null;
	int plazas = 0;
	try {
	    c = Jdbc.createThreadConnection();

	    pst = c.prepareStatement(SQL_FIND_PLAZAS);
	    pst.setInt(1, idcarrera);

	    ResultSet rs = pst.executeQuery();
	    while (rs.next()) {
		plazas = rs.getInt(1);
	    }
	    rs.close();
	    c.close();
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);
	}
	return plazas;
//		return db.executeQueryPojo(Integer.class, SQL_FIND_PLAZAS, idcarrera).get(0);
    }

    public int getPlazasReservadas(int idCarrera) {
	Connection c = null;
	PreparedStatement pst = null;
	int plazas = 0;
	try {
	    c = Jdbc.createThreadConnection();

	    pst = c.prepareStatement(SQL_FIND_PLAZASRESERVADAS);
	    pst.setInt(1, idCarrera);

	    ResultSet rs = pst.executeQuery();
	    while (rs.next()) {
		plazas = rs.getInt(1);
	    }
	    rs.close();
	    c.close();
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);
	}
	return plazas;
//		return db.executeQueryPojo(Integer.class, SQL_FIND_PLAZASRESERVADAS, idCarrera).get(0);
    }

    public int getMaxIdCarrera() {
	Connection c = null;
	PreparedStatement pst = null;

	try {
	    c = Jdbc.createThreadConnection();
	    int idCarrera = 0;
	    ResultSet rs = c.createStatement().executeQuery(SQL_FIND_MAX_IDCARRERA);
	    while (rs.next()) {
		idCarrera = rs.getInt(1);
	    }

	    c.close();
	    return idCarrera;
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);

	}
    }

    public List<PlazoDTO> verPlazosCarrera(int idCarrera) {
	return db.executeQueryPojo(PlazoDTO.class, SQL_FIND_PLAZOS_IDCARRERA, idCarrera);
    }

    public List<CarreraDisplayDTO> getListaCarrerasCompetidasPorEmailCorredor(String email) {
	Date fecha = new Date(System.currentTimeMillis());
	return db.executeQueryPojo(CarreraDisplayDTO.class, SQL_FIND_CARRERAS_COMPETIDAS_POR_EMAIL, email, fecha);
    }

    public int getInscritosCarrera(int idCarrera) {
	Connection c = null;
	PreparedStatement pst = null;

	int res = 0;

	try {
	    c = Jdbc.createThreadConnection();

	    pst = c.prepareStatement(SQL_GET_NUMERO_INSCRIPCIONES_VALIDA);

	    pst.setInt(1, idCarrera);

	    ResultSet rs = pst.executeQuery();

	    if (rs.next()) {
		res = rs.getInt(1);
	    }

	    c.close();
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);

	}

	return res;
    }

}
