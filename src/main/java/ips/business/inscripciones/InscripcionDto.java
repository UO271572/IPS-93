/**
 * 
 */
package ips.business.inscripciones;

/**
 * @author PC
 *
 */
public class InscripcionDto {
	private String idCarrera;
	private String fechaInscripcion;
	private String estadoInscripcion;
	private String dniCorredor;
	
	
	/**
	 * @param idCarrera
	 * @param fechaInscripcion
	 * @param estadoInscripcion
	 * @param dniCorredor
	 */
	public InscripcionDto(String idCarrera, String fechaInscripcion, String estadoInscripcion, String dniCorredor) {
		super();
		this.idCarrera = idCarrera;
		this.fechaInscripcion = fechaInscripcion;
		this.estadoInscripcion = estadoInscripcion;
		this.dniCorredor = dniCorredor;
	}
	
	public String getIdCarrera() {
		return idCarrera;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public String getEstadoInscripcion() {
		return estadoInscripcion;
	}
	public String getDniCorredor() {
		return dniCorredor;
	}
	
	
	
}
