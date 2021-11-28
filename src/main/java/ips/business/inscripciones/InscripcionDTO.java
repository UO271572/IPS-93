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
    private Time t1;
    private Time t2;
    private Time t3;
    private Time t4;
    private Time t5;

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

    public Time getT1() {
	return t1;
    }

    public void setT1(Time t1) {
	this.t1 = t1;
    }

    public Time getT2() {
	return t2;
    }

    public void setT2(Time t2) {
	this.t2 = t2;
    }

    public Time getT3() {
	return t3;
    }

    public void setT3(Time t3) {
	this.t3 = t3;
    }

    public Time getT4() {
	return t4;
    }

    public void setT4(Time t4) {
	this.t4 = t4;
    }

    public Time getT5() {
	return t5;
    }

    public void setT5(Time t5) {
	this.t5 = t5;
    }

}
