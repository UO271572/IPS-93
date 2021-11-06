package ips.business.corredores;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ips.util.Printer;

public class CorredorDTO {

	private String dniCorredor;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String sexo;
	private String email;
	private String categoria;
	//ATRIBUTOS TABLA INSCRIPCIONES
	private int idCarrera;
	private String estadoInscripcion;
	private Date fechaInscripcion;
	
	public String getDniCorredor() {
		return dniCorredor;
	}
	public void setDniCorredor(String dniCorredor) {
		this.dniCorredor = dniCorredor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	public String getEstadoInscripcion() {
		return estadoInscripcion;
	}
	public void setEstadoInscripcion(String estadoInscripcion) {
		this.estadoInscripcion = estadoInscripcion;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public String getCategoria() { // [ADRI] Si la competicion tiene sus propias categorias se usa su método
		
		String res = "";
		String genero = sexo;
		Date edad = fechaNacimiento;
		
		if(genero.toLowerCase().equals("hombre")) {
			res += getCategoríaEdad(edad) + " - Masculino";
		} else if(genero.toLowerCase().equals("mujer")) {
			res += getCategoríaEdad(edad) + " - Femenino";
		} else {
			throw new RuntimeException("Error: género no válido");
		}
		
		this.categoria = res;
		
		return categoria;
	}

	private String getCategoríaEdad(Date edadDate) { // [ADRI] Método auxiliar lo dejé aquí, luego hacer clase inscripcion y 
		// esa tiene un método que recibe lo mismo que getCategoria y devuelve sus propias categorias?
		
		String res = "";
		
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(edadDate);
		
		int edad = Period.between(LocalDate.of(calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH)+1,calendar1.get(Calendar.DAY_OF_MONTH)),
				LocalDate.now()).getYears();
		
		if(edad>40) {
			res += "Adulto - Mayor de 40 años";
		} else if (edad >= 35) {
			res += "Adulto - Entre 35 y 39 años";
		} else if (edad >= 30) {
			res += "Mayor - Entre 30 y 34 años";
		} else if (edad >= 25) {
			res += "Mayor - Entre 25 y 29 años";
		} else if (edad >= 20) {
			res += "Juvenil - Entre 20 y 24 años";
		} else if (edad >= 15) {
			res += "Juvenil - Entre 15 y 19 años";
		} else if (edad >= 10) {
			res += "Infantil - Entre 10 y 14 años";
		} else {
			res += "Infantil - Menor de 10 años";
		}
			
		return res;
	}
	
	public String toString() {
		return Printer.print(this);
	}
	
}
