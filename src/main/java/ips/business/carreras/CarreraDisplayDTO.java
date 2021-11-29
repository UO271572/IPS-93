package ips.business.carreras;

import java.sql.Date;

import ips.util.Printer;

public class CarreraDisplayDTO {

    private int idCarrera;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaCompeticion;
    private double precio;// arreglar a double en la base de datos
    private double distancia;
    private String tipo;
    private int plazasTotales;
    private int plazasReservadas;
    private int plazasRestantes;
    private String lugar;
    private String estadoCarrera;
    private String listaDeEspera;// = "NO";
    // Tiempos intermedios posibles (nombre)
    private String t1;
    private String t2;
    private String t3;
    private String t4;
    private String t5;
    private Date fechamaxcancelacion = null;
    private double porcentajedevo = -1;

    public CarreraDisplayDTO() {
    }

    public CarreraDisplayDTO(int id, String nombre, Date fechaI, Date fechaF, int precio, double distancia, Date fechaC,
	    String tipo) {
	this.idCarrera = id;
	this.nombre = nombre;
	this.fechaInicio = fechaI;
	this.fechaFin = fechaF;
	this.precio = precio;
	this.distancia = distancia;
	this.fechaCompeticion = fechaC;
	this.tipo = tipo;
    }

    public int getIdCarrera() {
	return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
	this.idCarrera = idCarrera;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
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

    public Date getFechaCompeticion() {
	return fechaCompeticion;
    }

    public void setFechaCompeticion(Date fechaCompeticion) {
	this.fechaCompeticion = fechaCompeticion;
    }

    public double getPrecio() {
	return precio;
    }

    public void setPrecio(double precio) {
	this.precio = precio;
    }

    public double getDistancia() {
	return distancia;
    }

    public void setDistancia(double distancia) {
	this.distancia = distancia;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public int getPlazasTotales() {
	return plazasTotales;
    }

    public void setPlazasTotales(int plazasTotales) {
	this.plazasTotales = plazasTotales;
    }

    public int getPlazasReservadas() {
	return plazasReservadas;
    }

    public void setPlazasReservadas(int plazasReservadas) {
	this.plazasReservadas = plazasReservadas;
    }

    public int getPlazasRestantes() {
	return plazasRestantes;
    }

    public void setPlazasRestantes(int plazasRestantes) {
	this.plazasRestantes = plazasRestantes;
    }

    public String getLugar() {
	return lugar;
    }

    public void setLugar(String lugar) {
	this.lugar = lugar;
    }

    public String getEstadoCarrera() {
	return estadoCarrera;
    }

    public void setEstadoCarrera(String estadoCarrera) {
	this.estadoCarrera = estadoCarrera;
    }

    public String getListaDeEspera() {
	return listaDeEspera;
    }

    public void setListaDeEspera(String listaDeEspera) {
	this.listaDeEspera = listaDeEspera;
    }

    public String getT1() {
	return t1;
    }

    public void setT1(String t1) {
	this.t1 = t1;
    }

    public String getT2() {
	return t2;
    }

    public void setT2(String t2) {
	this.t2 = t2;
    }

    public String getT3() {
	return t3;
    }

    public void setT3(String t3) {
	this.t3 = t3;
    }

    public String getT4() {
	return t4;
    }

    public void setT4(String t4) {
	this.t4 = t4;
    }

    public String getT5() {
	return t5;
    }

    public void setT5(String t5) {
	this.t5 = t5;
    }

    public Date getFechamaxcancelacion() {
	return fechamaxcancelacion;
    }

    public void setFechamaxcancelacion(Date fechamaxcancelacion) {
	this.fechamaxcancelacion = fechamaxcancelacion;
    }

    public double getPorcentajedevo() {
	return porcentajedevo;
    }

    public void setPorcentajedevo(double porcentajedevo) {
	this.porcentajedevo = porcentajedevo;
    }

    public String toString() {
	return Printer.print(this);
    }

}
