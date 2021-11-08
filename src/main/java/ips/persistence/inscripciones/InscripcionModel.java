package ips.persistence.inscripciones;

import java.util.List;

import ips.business.inscripciones.InscripcionDTO;
import ips.util.Database;

public class InscripcionModel {

    private Database db = new Database();

    public static final String SQL_UPDATE_INSCRIPCION = "update inscripciones set tiempoinicio=? and tiempofin=? where dorsal=? and idcarrera=?";

    public void updateInscripciones(List<InscripcionDTO> lista) {
	for (InscripcionDTO i : lista)
	    db.executeUpdate(SQL_UPDATE_INSCRIPCION, i.getTiempoinicio(), i.getTiempofin(), i.getDorsal(),
		    i.getIdcarrera());
    }

}
