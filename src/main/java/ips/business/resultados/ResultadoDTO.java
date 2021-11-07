package ips.business.resultados;

import java.sql.Time;

public class ResultadoDTO {

    private String dnicorredor;
    private int idcarrera;
    private Time tiempoinicio, tiempofin;

    public ResultadoDTO() {
    }

    public ResultadoDTO(String dnicorredor, int idcarrera, Time tiempoinicio, Time tiempofin) {
	this.dnicorredor = dnicorredor;
	this.idcarrera = idcarrera;
	this.tiempoinicio = tiempoinicio;
	this.tiempofin = tiempofin;
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

    public Time getTiempoinicio() {
	return tiempoinicio;
    }

    public void setTiempoinicio(Time tiempoinicio) {
	this.tiempoinicio = tiempoinicio;
    }

    public Time getTiempofin() {
	return tiempofin;
    }

    public void setTiempofin(Time tiempofin) {
	this.tiempofin = tiempofin;
    }

}
