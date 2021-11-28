package ips.persistence.inscripciones;

import java.util.ArrayList;
import java.util.List;

import ips.business.inscripciones.InscripcionDTO;
import ips.util.Database;

public class InscripcionModel {

    private Database db = new Database();

    public static final String SQL_UPDATE_INSCRIPCION = "update inscripciones set tiempoinicio=? and tiempofin=? where dorsal=? and idcarrera=?";
    private static final String SQL_INSCRIPCION_DNI_ID = "select * from inscripciones where dnicorredor = ? and idcarrera = ?";
    private static final String SQL_ADD_INSCRIPCION = "insert into inscripciones(dnicorredor, idcarrera,ESTADOINSCRIPCION , FECHAINSCRIPCION, incidencia, club) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_INSCRIPCION_CLUB = "UPDATE inscripciones SET estadoinscripcion = ?, incidencia = ?, fechainscripcion = ?, club = ? WHERE dnicorredor = ? and idcarrera = ?";
    public static final String SQL_ORDENAR_POR_TIEMPO = "select * from inscripciones where (idcarrera = ? ) order by (tiempofin - tiempoinicio)";

    /**
     * Ordena por tiempos mas rapidos a mas lentos los resultados de la carrera de
     * los corredores
     * 
     * @param idcarrera
     * @return
     */
    public List<InscripcionDTO> inscripcionesOrdenarPorTiempos(int idcarrera) {
	List<InscripcionDTO> todo = db.executeQueryPojo(InscripcionDTO.class, SQL_ORDENAR_POR_TIEMPO, idcarrera);
	List<InscripcionDTO> añadirAlfinal = new ArrayList<InscripcionDTO>();
	for (InscripcionDTO inscripcion : todo) {
	    if (inscripcion.getTiempofin() == null || inscripcion.getTiempoinicio() == null) {
		añadirAlfinal.add(inscripcion);
	    }
	}
	for (InscripcionDTO inscripcion : añadirAlfinal) {
	    todo.remove(inscripcion);
	}
	todo.addAll(todo.size(), añadirAlfinal);
	return todo;
    }

    public void updateInscripciones(List<InscripcionDTO> lista) {
	for (InscripcionDTO i : lista)
	    db.executeUpdate(SQL_UPDATE_INSCRIPCION, i.getTiempoinicio(), i.getTiempofin(), i.getDorsal(),
		    i.getIdcarrera());
    }

    public List<InscripcionDTO> findInscripcionByDNIAndIDCarrera(String dniCorredor, int idCarrera) {
	return db.executeQueryPojo(InscripcionDTO.class, SQL_INSCRIPCION_DNI_ID, dniCorredor, idCarrera);
    }

    public void addInscripcion(InscripcionDTO inscripcionNueva) {
	db.executeUpdate(SQL_ADD_INSCRIPCION, inscripcionNueva.getDnicorredor(), inscripcionNueva.getIdcarrera(),
		inscripcionNueva.getEstadoinscripcion(), inscripcionNueva.getFechainscripcion(),
		inscripcionNueva.getIncidencia(), inscripcionNueva.getClub());
    }

    public void updateInscripcion(InscripcionDTO i) {
	db.executeUpdate(SQL_UPDATE_INSCRIPCION_CLUB, i.getEstadoinscripcion(), i.getIncidencia(),
		i.getFechainscripcion(), i.getClub(), i.getDnicorredor(), i.getIdcarrera());
    }

}
