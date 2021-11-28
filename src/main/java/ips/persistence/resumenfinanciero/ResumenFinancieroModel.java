package ips.persistence.resumenfinanciero;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ips.util.Jdbc;
import ips.util.UnexpectedException;

public class ResumenFinancieroModel {

    public static final String SQL_GET_CUOTA = "SELECT cuota from plazos p where p.idcarrera=? and ?>=p.fechainicio and ?<=p.fechafin";

    public static final String SQL_GET_ANULADOS = "SELECT fechainscripcion FROM INSCRIPCIONES where ESTADOINSCRIPCION='ANULADA' and idcarrera=?";

    public static final String SQL_GET_INSCRITOS = "SELECT fechainscripcion FROM INSCRIPCIONES where ESTADOINSCRIPCION='INSCRITO' and idcarrera=?";

    public static final String SQL_GET_INSCRIPCIONES_CONFIRMADAS = "SELECT COUNT(*) FROM INSCRIPCIONES WHERE IDCARRERA=? AND ESTADOINSCRIPCION='INSCRITO'";

    public static final String SQL_GET_INSCRIPCIONES_ANULADAS = "SELECT COUNT(*) FROM INSCRIPCIONES WHERE IDCARRERA=? AND ESTADOINSCRIPCION='ANULADA'";

    public double getTotalIngresado(int idCarrera) {

	Connection c = null;
	PreparedStatement pst = null;
	PreparedStatement pstAn = null;

	double res = 0;

	try {
	    c = Jdbc.createThreadConnection();
	    res = 0;

	    pstAn = c.prepareStatement(SQL_GET_INSCRITOS);

	    pstAn.setInt(1, idCarrera);

	    ResultSet rsAn = pstAn.executeQuery();

	    ResultSet rs;

	    Date d;
	    while (rsAn.next()) {
		d = rsAn.getDate(1);

		pst = c.prepareStatement(SQL_GET_CUOTA);
		pst.setInt(1, idCarrera);
		pst.setDate(2, d);
		pst.setDate(3, d);

		rs = pst.executeQuery();

		while (rs.next()) {
		    res += rs.getDouble(1);
		}
	    }

	    c.close();
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);

	}

	return res;
    }

    public double getTotalADevolver(int idCarrera) {

	Connection c = null;
	PreparedStatement pst = null;
	PreparedStatement pstAn = null;

	double res = 0;

	try {
	    c = Jdbc.createThreadConnection();
	    res = 0;

	    pstAn = c.prepareStatement(SQL_GET_ANULADOS);

	    pstAn.setInt(1, idCarrera);

	    ResultSet rsAn = pstAn.executeQuery();

	    ResultSet rs;

	    Date d;
	    while (rsAn.next()) {
		d = rsAn.getDate(1);

		pst = c.prepareStatement(SQL_GET_CUOTA);
		pst.setInt(1, idCarrera);
		pst.setDate(2, d);
		pst.setDate(3, d);

		rs = pst.executeQuery();

		while (rs.next()) {
		    res += rs.getDouble(1);
		}
	    }

	    c.close();
	} catch (SQLException e) {
	    throw new UnexpectedException(e);
	} finally {
	    Jdbc.close(pst);

	}

	return res;
    }

    public int getInscripcionesConfirmadas(int idCarrera) {

	Connection c = null;
	PreparedStatement pst = null;
	int res = 0;
	try {
	    c = Jdbc.createThreadConnection();
	    res = 0;

	    pst = c.prepareStatement(SQL_GET_INSCRIPCIONES_CONFIRMADAS);

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

    public int getInscripcionesAnuladas(int idCarrera) {

	Connection c = null;
	PreparedStatement pst = null;
	int res = 0;
	try {
	    c = Jdbc.createThreadConnection();
	    res = 0;

	    pst = c.prepareStatement(SQL_GET_INSCRIPCIONES_ANULADAS);

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
