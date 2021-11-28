package ips.persistence.corredores;

import java.util.List;

import ips.business.corredores.CorredorDTO;
import ips.util.Database;

public class CorredoresModel {

    private Database db = new Database();

    private final static String SQL_LISTA_CORREDORES_BY_IDCARRERA = "select * from corredores c,inscripciones i where idcarrera = ? and c.dnicorredor = i.dnicorredor order by"
	    + " fechainscripcion, estadoinscripcion";

    private final static String SQL_CORREDOR_BY_EMAIL = "select * from corredores c, inscripciones i where c.dnicorredor = i.dnicorredor and c.email = ? order by fechainscripcion desc";

    private final static String SQL_EMAIL_CORREDORES = "select email from corredores";

    private final static String SQL_ADD_CORREDOR = "insert into corredores(dnicorredor, nombre, apellidos, fechanacimiento, sexo, email) values (?, ?, ?, ?, ?, ?)";

    private final static String SQL_CORREDOR_BY_DNI = "select * from corredores where dnicorredor = ?";

    private final static String SQL_UPDATE_CORREDOR_EMAIL = "UPDATE corredores SET email = ? WHERE dnicorredor = ?";

    public List<CorredorDTO> getListaCorredoresByIdCarrera(int idCarrera) {
	return db.executeQueryPojo(CorredorDTO.class, SQL_LISTA_CORREDORES_BY_IDCARRERA, idCarrera);
    }

    // [ADRI]
    public List<CorredorDTO> getCorredorByEmail(String email) {
	// Ordenado por fecha de inscripción (descendente)
	return db.executeQueryPojo(CorredorDTO.class, SQL_CORREDOR_BY_EMAIL, email);
    }

    public List<CorredorDTO> getEmailCorredores() {
	return db.executeQueryPojo(CorredorDTO.class, SQL_EMAIL_CORREDORES);
    }

    public void addCorredor(CorredorDTO corredor) {
	db.executeUpdate(SQL_ADD_CORREDOR, corredor.getDniCorredor(), corredor.getNombre(), corredor.getApellidos(),
		corredor.getFechaNacimiento(), corredor.getSexo(), corredor.getEmail());
    }

    public List<CorredorDTO> findCorredorByDNI(String dniCorredor) {
	return db.executeQueryPojo(CorredorDTO.class, SQL_CORREDOR_BY_DNI, dniCorredor);
    }

    public void updateCorredor(CorredorDTO corredor) {
	// Igual cambiar
	db.executeUpdate(SQL_UPDATE_CORREDOR_EMAIL, corredor.getEmail(), corredor.getDniCorredor());
    }
}
