package ips.persistence.corredores;

import java.util.List;

import ips.business.corredores.CorredorDTO;
import ips.util.Database;

public class CorredoresModel {

	private Database db=new Database();
	private final static String SQL_LISTA_CORREDORES_BY_IDCARRERA = 
	"select * from corredores c,inscripciones i where idcarrera = ? and c.dnicorredor = i.dnicorredor order by"
	+ " fechainscripcion, estadoinscripcion";
	
	private final static String SQL_CORREDOR_BY_EMAIL = "select * from corredores c, inscripciones i where c.dnicorredor = i.dnicorredor and c.email = ? order by fechainscripcion desc"; 
	
	public List<CorredorDTO> getListaCorredoresByIdCarrera(int idCarrera) {
		return db.executeQueryPojo(CorredorDTO.class,SQL_LISTA_CORREDORES_BY_IDCARRERA,idCarrera);
	}
	
	// [ADRI]
	public List<CorredorDTO> getCorredorByEmail(String email) {
		// Ordenado por fecha de inscripción (descendente)
		return db.executeQueryPojo(CorredorDTO.class, SQL_CORREDOR_BY_EMAIL, email);
	}

}
