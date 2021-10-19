/**
 * 
 */
package ips.business.clasificaciones;

import java.sql.Time;

import org.hsqldb.types.TimeData;

/**
 * @author PC
 *
 */
public class ClasificacionDTO {
	
	private int posicion;
	private String sexo;//tabla corredores
	private String nombre;//tabla corredores
	private Time time;
	private String dnicorredor;
//	private String idcarrera;


	
	
	
	public String getSexo() {
		return sexo;
	}
	/**
 * @param posicion
 * @param sexo
 * @param nombre
 * @param time
 * @param dnicorredor
 */
public ClasificacionDTO(int posicion, String sexo, String nombre, Time time, String dnicorredor) {
	super();
	this.posicion = posicion;
	this.sexo = sexo;
	this.nombre = nombre;
	this.time = time;
	this.dnicorredor = dnicorredor;
}
	public String getDnicorredor() {
		return dnicorredor;
	}
	public void setDnicorredor(String dnicorredor) {
		this.dnicorredor = dnicorredor;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
//	public String getIdcarrera() {
//		return idcarrera;
//	}
//	public void setIdcarrera(String idcarrera) {
//		this.idcarrera = idcarrera;
//	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	
	
}
