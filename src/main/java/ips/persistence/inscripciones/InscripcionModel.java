package ips.persistence.inscripciones;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import ips.business.inscripciones.InscripcionDTO;
import ips.util.Database;

public class InscripcionModel {

    private Database db = new Database();

    public static final String SQL_UPDATE_INSCRIPCION = "update inscripciones set tiempoinicio=? and tiempofin=? where dorsal=? and idcarrera=?";

    public static final String SQL_GET_INSCIPCIONES_BY_EMAIL = "select i.idcarrera, i.estadoinscripcion, i.fechainscripcion, i.dorsal, i.incidencia, i.tiempoinicio, i.tiempofin"
	    + " from inscripciones i, corredores c where i.dnicorredor=c.dnicorredor and c.email=?";

    public static final String SQL_CANCELAR_INSCRIPCION = "update inscripciones set estadoinscripcion='CANCELADA' and fechacancelacion=? where dnicorredor=? and idcarrera=?";

    public void updateInscripciones(List<InscripcionDTO> lista) {
	for (InscripcionDTO i : lista)
	    db.executeUpdate(SQL_UPDATE_INSCRIPCION, i.getTiempoinicio(), i.getTiempofin(), i.getDorsal(),
		    i.getIdcarrera());
    }

    public List<InscripcionDTO> getInscripcionesByEmail(String email) {
	return db.executeQueryPojo(InscripcionDTO.class, SQL_GET_INSCIPCIONES_BY_EMAIL, email);
    }

    public void calcelarInscripcion(String dnicorredor, int idcarrera) {
	db.executeUpdate(SQL_CANCELAR_INSCRIPCION, Date.valueOf(LocalDate.now()), dnicorredor, idcarrera);
    }

}
