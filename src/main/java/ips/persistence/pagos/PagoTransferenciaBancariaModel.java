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
	private static final String SQL_ANOTAR_INCIDENCIA = "UPDATE INSCRIPCIONES SET incidencia = ? where dnicorredor = ? and idcarrera = ?";
	
	private static final String SQL_VER_PAGO_ATLETA = "SELECT * FROM INSCRIPCIONES where idcarrera = ? and dnicorredor = ?";
	
	private static final String SQL_VER_PENDIENTES_DE_PAGO = "SELECT * FROM INSCRIPCIONES where idcarrera = ? and estadoinscripcion = 'PENDIENTE DE PAGO'";
	private static final String SQL_ACTUALIZAR_ESTADO_INSCRIPCION = "UPDATE INSCRIPCIONES SET estadoinscripcion = ? where dnicorredor = ? and idcarrera = ? and estadoinscripcion = ?";
	
	public List<InscripcionDTO> updateInscription(String dni, int idCarrera) {
		//Selecciona los cambios que se van a producir para devolver la lista
		List<InscripcionDTO> lista = db.executeQueryPojo(InscripcionDTO.class, SQL_VER_PAGO_INSCRIPCION, dni,idCarrera,"INSCRITO");
		//hace update de la base de datos
		db.executeUpdate(SQL_PAGO_INSCRIPCION, "PENDIENTE DE PAGO",dni,idCarrera,"INSCRITO");
		return lista;
	}
	
	public List<InscripcionDTO> verPagosCarrera(int idCarrera, String dniCorredor){
		return db.executeQueryPojo(InscripcionDTO.class, SQL_VER_PAGO_ATLETA, idCarrera, dniCorredor);
	}

	public void anotarIncidencia(String dnicorredor, int idCarrera, String mensaje) {
		db.executeUpdate(SQL_ANOTAR_INCIDENCIA, mensaje, dnicorredor,idCarrera);
	}
	
	public List<InscripcionDTO> updateInscriptionAnular(String dni, int idCarrera) {
		//Selecciona los cambios que se van a producir para devolver la lista
		List<InscripcionDTO> lista = db.executeQueryPojo(InscripcionDTO.class, SQL_VER_PAGO_INSCRIPCION, dni,idCarrera,"INSCRITO");
		//hace update de la base de datos
		db.executeUpdate(SQL_ACTUALIZAR_ESTADO_INSCRIPCION, "ANULADA",dni,idCarrera,"PENDIENTE DE PAGO");
		return lista;
	}
	
	public List<InscripcionDTO> verPendientesDePago(int idCarrera){
		return db.executeQueryPojo(InscripcionDTO.class, SQL_VER_PENDIENTES_DE_PAGO, idCarrera);
	}
	
	public List<InscripcionDTO> updateInscriptionInscrito(String dni, int idCarrera) {
		//Selecciona los cambios que se van a producir para devolver la lista
		List<InscripcionDTO> lista = db.executeQueryPojo(InscripcionDTO.class, SQL_VER_PAGO_INSCRIPCION, dni,idCarrera,"INSCRITO");
		//hace update de la base de datos
		db.executeUpdate(SQL_ACTUALIZAR_ESTADO_INSCRIPCION, "INSCRITO",dni,idCarrera,"PENDIENTE DE PAGO");
		return lista;
	}
	
	

}
