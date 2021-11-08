/**
 * 
 */
package ips.business.inscripciones;

import java.sql.Time;

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
	private Time tiempoInicio;
	private Time tiempoFin;
	
	
	
	
	public Time getTiempoInicio() {
		return tiempoInicio;
	}
	public void setTiempoInicio(Time tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public Time getTiempoFin() {
		return tiempoFin;
	}
	public void setTiempoFin(Time tiempoFin) {
		this.tiempoFin = tiempoFin;
	}
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
	
	
	
}
