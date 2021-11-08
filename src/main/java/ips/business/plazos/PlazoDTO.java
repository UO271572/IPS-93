package ips.business.plazos;

import java.sql.Date;

public class PlazoDTO {
    private int idCarrera;
    private Date fechaInicio;
    private Date fechaFin;
    private double cuota;

    public PlazoDTO() {

    }

    public PlazoDTO(int idCarrera, Date fechaInicio, Date fechaFin, double cuota) {
	this.idCarrera = idCarrera;
	this.fechaInicio = fechaInicio;
	this.fechaFin = fechaFin;
	this.cuota = cuota;
    }

    public int getIdCarrera() {
	return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
	this.idCarrera = idCarrera;
    }

    public Date getFechaInicio() {
	return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
	return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
    }

    public double getCuota() {
	return cuota;
    }

    public void setCuota(double cuota) {
	this.cuota = cuota;
    }
}
