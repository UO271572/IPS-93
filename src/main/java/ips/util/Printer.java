package ips.util;

import java.sql.Time;
import java.util.List;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.clasificaciones.ClasificacionDTO;
import ips.business.corredores.CorredorDTO;
import ips.business.inscripciones.InscripcionDTO;

public class Printer {

    public static String print(CarreraDisplayDTO carrera) {

	StringBuilder sb = new StringBuilder();
	sb.append("Id carrera: " + carrera.getIdCarrera());
	sb.append(" ");
	sb.append("Nombre: " + carrera.getNombre());
	sb.append(" ");
	sb.append("Tipo: " + carrera.getTipo());
	sb.append(" ");
	sb.append("Precio: " + carrera.getPrecio());
	sb.append(" ");
	sb.append("Fecha inicio: " + carrera.getFechaInicio());
	sb.append(" ");
	sb.append("Fecha fin: " + carrera.getFechaFin());
	sb.append(" ");
	sb.append("Fecha competicion: " + carrera.getFechaCompeticion());
	sb.append(" ");
	sb.append("Plazas disponibles: " + carrera.getPlazasDisponibles());
	return sb.toString();
    }

    public static String print(CorredorDTO corredor) {

	StringBuilder sb = new StringBuilder();
	sb.append("DNI: " + corredor.getDniCorredor());
	sb.append(" ");
	sb.append("Nombre: " + corredor.getNombre());
	sb.append(" ");
	sb.append("Categoria: " + corredor.getCategoria());
	sb.append(" ");
	sb.append("Fecha inscripcion: " + corredor.getFechaInscripcion());
	sb.append(" ");
	sb.append("Estado inscripcion: " + corredor.getEstadoInscripcion());

	return sb.toString();

    }

    public static String print(InscripcionDTO inscripcion) {

	StringBuilder sb = new StringBuilder();
	sb.append("DNI: " + inscripcion.getDnicorredor());
	sb.append(" ");
	sb.append("Nombre: " + inscripcion.getIdcarrera());
	sb.append(" ");
	sb.append("Estado inscripcion: " + inscripcion.getEstadoinscripcion());
	sb.append(" ");
	sb.append("Fecha inscripcion: " + inscripcion.getFechainscripcion());
	sb.append(" ");
	sb.append("Dorsal: " + inscripcion.getDorsal());
	sb.append(" ");
	if (inscripcion.getIncidencia() == null || inscripcion.getIncidencia().isEmpty()) {
	    sb.append("Incidendia: " + "---");
	} else {
	    sb.append("Incidendia: " + inscripcion.getIncidencia());
	}
	if (inscripcion.getTiempoinicio() == null) {
	    sb.append(" ");
	    sb.append("Tiempo inicio: " + "---");
	} else {
	    sb.append(" ");
	    sb.append("Tiempo inicio: " + inscripcion.getTiempoinicio());
	}
	if (inscripcion.getTiempofin() == null) {
	    sb.append(" ");
	    sb.append("Tiempo fin: " + "---");
	} else {
	    sb.append(" ");
	    sb.append("Tiempo fin: " + inscripcion.getTiempofin());
	}

	return sb.toString();

    }

    public static String print(ClasificacionDTO c) {

	StringBuilder sb = new StringBuilder();
	sb.append("Posicion: " + c.getPosicion());
	sb.append(" ");
	sb.append("Sexo: " + c.getSexo());
	sb.append(" ");
//		sb.append("Id carrera: " + c.getIdCarrera());
//		sb.append(" ");
	sb.append("Nombre: " + c.getNombre());
	sb.append(" ");

	if (c.getTime().equals(new Time(0, 0, 0))) {
	    sb.append("Time: ---");
	} else {
	    sb.append("Time: " + c.getTime());
	}
	return sb.toString();
    }

    // LISTAS
    public static void printCarreras(List<CarreraDisplayDTO> carreras) {
	for (CarreraDisplayDTO carrera : carreras) {
	    if (carrera != null) {
		print(carrera);
		printSaltoLinea();
	    }
	}
    }

    public static void printCorredores(List<CorredorDTO> corredores) {
	for (CorredorDTO corredor : corredores) {
	    if (corredor != null) {
		print(corredor);
		printSaltoLinea();
	    }
	}
    }

    public static void printClasificacion(List<ClasificacionDTO> clasi) {
	for (ClasificacionDTO c : clasi) {
	    if (c != null) {
		print(c);
		printSaltoLinea();
	    }
	}
    }

    // AUXILIARES
    private static void printSaltoLinea() {
	Console.println();
    }

    public static void printBusinessException(Exception e) {
	Console.println("Ha ocurrido un problema procesando su opcion:");
	Console.println("\t- " + e.getLocalizedMessage());
    }

}
