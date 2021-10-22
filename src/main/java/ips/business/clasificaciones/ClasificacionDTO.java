/**
 * 
 */
package ips.business.clasificaciones;

import java.sql.Time;

import ips.util.Printer;

/**
 * @author PC
 *
 */
public class ClasificacionDTO {
	
	private int posicion;
	private String sexo;//tabla corredores
	private String nombre;//tabla corredores
	private Time time;
	private String dniCorredor;
	private int idCarrera;


	
	
	
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
public ClasificacionDTO(int posicion, String sexo, String nombre, String dnicorredor, int idCarrera,Time time) {
	this.posicion = posicion;
	this.sexo = sexo;
	this.nombre = nombre;
	this.time = time;
	this.dniCorredor = dnicorredor;
	this.idCarrera = idCarrera;
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
	
	/*public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	*/
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public String getDniCorredor() {
		return dniCorredor;
	}
	public void setDniCorredor(String dniCorredor) {
		this.dniCorredor = dniCorredor;
	}
	public int getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  Printer.print(this);
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
}
