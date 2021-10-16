/**
 * 
 */
package ips.persistence.pagos;

import java.util.List;

import ips.business.inscripciones.InscripcionDto;
import ips.util.Database;

/**
 * @author PC
 *
 */
public class PagoTarjetaModel {
	
	private Database db = new Database();
	private static final String SQL_PAGO_INSCRIPCION = "UPDATE INSCRIPCIONES SET estadoinscripcion = ? where dnicorredor = ? and idcarrera = ?";
	
	
	public List<InscripcionDto> updateInscription(String dni, int idCarrera) {
		return db.executeQueryPojo(InscripcionDto.class, SQL_PAGO_INSCRIPCION, dni,idCarrera);
	}
	

}
