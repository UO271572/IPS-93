package ips.business.categorias;

public class CategoriaDTO {

	private String nombre;
	private int edadInicio;
	private int edadFin;
	private int idCarrera;
	private String sexo; // Absoluto, masculino, femenino
	
	
	public CategoriaDTO(String nombre, int edadInicio, int edadFin, String sexo) {
		super();
		this.nombre = nombre;
		this.edadInicio = edadInicio;
		this.edadFin = edadFin;
		this.setSexo(sexo);
	}

	@Override
	public String toString() {
		return nombre + " Edad inicio: " + edadInicio + " Edad fin: " + edadFin + " Sexo: "
				+ sexo;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdadInicio() {
		return edadInicio;
	}
	public void setEdadInicio(int edadInicio) {
		this.edadInicio = edadInicio;
	}
	public int getEdadFin() {
		return edadFin;
	}
	public void setEdadFin(int edadFin) {
		this.edadFin = edadFin;
	}
	public int getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
}
