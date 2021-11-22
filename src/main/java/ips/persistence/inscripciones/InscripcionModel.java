package ips.persistence.inscripciones;

import java.util.List;

import ips.business.inscripciones.InscripcionDTO;
import ips.util.Database;

public class InscripcionModel {

    private Database db = new Database();

    public static final String SQL_UPDATE_INSCRIPCION = "update inscripciones set tiempoinicio=? and tiempofin=? where dorsal=? and idcarrera=?";
    public static final String SQL_ORDENAR_POR_TIEMPO = "select * from inscripciones where (idcarrera = ?) order by (tiempofin - tiempoinicio)";

    public void updateInscripciones(List<InscripcionDTO> lista) {
	for (InscripcionDTO i : lista)
	    db.executeUpdate(SQL_UPDATE_INSCRIPCION, i.getTiempoinicio(), i.getTiempofin(), i.getDorsal(),
		    i.getIdcarrera());
    }
    
    
    HACER?
    public List<InscripcionDTO> inscripcionesOrdenarPorTiempos(){
	return db.executeQueryPojo(InscripcionDTO.class, SQL_ORDENAR_POR_TIEMPO, null);
    }

}
