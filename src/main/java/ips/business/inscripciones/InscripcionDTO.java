/**
 * 
 */
package ips.business.inscripciones;

import java.sql.Date;
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
    private Date fechainscripcion;
    private int dorsal;
    private String incidencia;
    private String club;
    private Time tiempoinicio;
    private Time tiempofin;

//	public Time getTiempoInicio() {
//		return tiempoInicio;
//	}
//	public void setTiempoInicio(Time tiempoInicio) {
//		this.tiempoInicio = tiempoInicio;
//	}
//	public Time getTiempoFin() {
//		return tiempoFin;
//	}
//	public void setTiempoFin(Time tiempoFin) {
//		this.tiempoFin = tiempoFin;
//	}

    public Time getTiempofin() {
	return tiempofin;
    }

    public void setTiempofin(Time tiempofin) {
	this.tiempofin = tiempofin;
    }

    public Time getTiempoinicio() {
	return tiempoinicio;
    }

    public void setTiempoinicio(Time tiempoinicio) {
	this.tiempoinicio = tiempoinicio;
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

    public Date getFechainscripcion() {
	return fechainscripcion;
    }

    public void setFechainscripcion(Date fechainscripcion) {
	this.fechainscripcion = fechainscripcion;
    }

    public String getClub() {
	return club;
    }

    public void setClub(String club) {
	this.club = club;
    }

    public String toString() {
	return Printer.print(this);
    }

}
