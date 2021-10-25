/**
 * 
 */
package ips.persistence.pagos;

import java.util.List;

import ips.business.inscripciones.InscripcionDTO;
import ips.util.Database;

/**
 * @author PC
 *
 */
public class PagoTransferenciaBancariaModel {
	
	private Database db = new Database();
	private static final String SQL_PAGO_INSCRIPCION = "UPDATE INSCRIPCIONES SET estadoinscripcion = ? where dnicorredor = ? and idcarrera = ? and estadoinscripcion <> ?";
	private static final String SQL_VER_PAGO_INSCRIPCION = "SELECT * FROM INSCRIPCIONES where dnicorredor = ? and idcarrera = ? and estadoinscripcion <> ?";
	
	
	public List<InscripcionDTO> updateInscription(String dni, int idCarrera) {
		//Selecciona los cambios que se van a producir para devolver la lista
		List<InscripcionDTO> lista = db.executeQueryPojo(InscripcionDTO.class, SQL_VER_PAGO_INSCRIPCION, dni,idCarrera,"INSCRITO");
		//hace update de la base de datos
		db.executeUpdate(SQL_PAGO_INSCRIPCION, "PENDIENTE DE PAGO",dni,idCarrera,"INSCRITO");
		return lista;
	}
	

}
