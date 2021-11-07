/**
 * 
 */
package ips.business.inscripciones;

import ips.util.Printer;

/**
 * @author PC
 *
 */
public class InscripcionDTO {
	private String dnicorredor;
	private int idcarrera;
	private String estadoinscripcion;
	private String fechainscripcion;
	private int dorsal;
	private String incidencia;
	
	
	
	
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public String getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}
	public String getDnicorredor() {
		return dnicorredor;
	}
	public void setDnicorredor(String dnicorredor) {
		this.dnicorredor = dnicorredor;
	}
	public int getIdcarrera() {
		return idcarrera;
	}
	public void setIdcarrera(int idcarrera) {
		this.idcarrera = idcarrera;
	}
	public String getEstadoinscripcion() {
		return estadoinscripcion;
	}
	public void setEstadoinscripcion(String estadoinscripcion) {
		this.estadoinscripcion = estadoinscripcion;
	}
	public String getFechainscripcion() {
		return fechainscripcion;
	}
	public void setFechainscripcion(String fechainscripcion) {
		this.fechainscripcion = fechainscripcion;
	}
	
	public String toString() {
		return Printer.print(this);
	}
	
	
//	/**
//	 * @param idCarrera
//	 * @param fechaInscripcion
//	 * @param estadoInscripcion
//	 * @param dniCorredor
//	 */
//	public InscripcionDto(String dniCorredor,int idCarrera, String estadoInscripcion,String fechaInscripcion) {
//		super();
//		this.idcarrera = idCarrera;
//		this.fechainscripcion = fechaInscripcion;
//		this.estadoinscripcion = estadoInscripcion;
//		this.dnicorredor = dniCorredor;
//	}
	
	
	
	
	
}
