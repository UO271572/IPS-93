package ips.business.inscripciones;

import java.sql.Date;
import java.sql.Time;

import ips.util.Printer;

public class InscripcionDTO {

    private String dnicorredor;
    private int idcarrera;
    private String estadoinscripcion;
    private String fechainscripcion;
    private int dorsal;
    private String incidencia;
    private Time tiempoinicio;
    private Time tiempofin;
    // Tiempos intermedios posibles
    private Time ti_1;
    private Time ti_2;
    private Time ti_3;
    private Time ti_4;
    private Time ti_5;
    private Date fechacancelacion;

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

    public String getFechainscripcion() {
	return fechainscripcion;
    }

    public void setFechainscripcion(String fechainscripcion) {
	this.fechainscripcion = fechainscripcion;
    }

    public Time getTi_1() {
	return ti_1;
    }

    public void setTi_1(Time ti_1) {
	this.ti_1 = ti_1;
    }

    public Time getTi_2() {
	return ti_2;
    }

    public void setTi_2(Time ti_2) {
	this.ti_2 = ti_2;
    }

    public Time getTi_3() {
	return ti_3;
    }

    public void setTi_3(Time ti_3) {
	this.ti_3 = ti_3;
    }

    public Time getTi_4() {
	return ti_4;
    }

    public void setTi_4(Time ti_4) {
	this.ti_4 = ti_4;
    }

    public Time getTi_5() {
	return ti_5;
    }

    public void setTi_5(Time ti_5) {
	this.ti_5 = ti_5;
    }

    public Date getFechacancelacion() {
	return fechacancelacion;
    }

    public void setFechacancelacion(Date fechacancelacion) {
	this.fechacancelacion = fechacancelacion;
    }

    public String toString() {
	return Printer.print(this);
    }

}
