package ips.persistence.inscripciones;

import java.util.ArrayList;
import java.util.List;

import ips.business.inscripciones.InscripcionDTO;
import ips.util.Database;

public class InscripcionModel {

    private Database db = new Database();

    public static final String SQL_UPDATE_INSCRIPCION = "update inscripciones set tiempoinicio=? and tiempofin=? where dorsal=? and idcarrera=?";
    public static final String SQL_ORDENAR_POR_TIEMPO = "select * from inscripciones where (idcarrera = ? ) order by (tiempofin - tiempoinicio)";

    /**
     * Actualiza las inscripciones
     * 
     * @param lista
     */
    public void updateInscripciones(List<InscripcionDTO> lista) {
	for (InscripcionDTO i : lista)
	    db.executeUpdate(SQL_UPDATE_INSCRIPCION, i.getTiempoinicio(), i.getTiempofin(), i.getDorsal(),
		    i.getIdcarrera());
    }

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

}
