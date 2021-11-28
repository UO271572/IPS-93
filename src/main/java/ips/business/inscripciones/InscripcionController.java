package ips.business.inscripciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ips.business.BusinessCheck;
import ips.business.BusinessException;
import ips.business.plazos.PlazoDTO;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.inscripciones.InscripcionModel;
import ips.persistence.pagos.PagoTarjetaModel;
import ips.persistence.pagos.PagoTransferenciaBancariaModel;
import ips.ui.carreras.InscripcionView;

public class InscripcionController {

    private static final int PRECIO_POR_DEFECTO = 5;

    private PagoTarjetaModel model;
    private PagoTransferenciaBancariaModel modelBanco;
    private InscripcionModel imodel;
    private InscripcionView view;

    public InscripcionController(PagoTarjetaModel model) {// , InscripcionView view) {
	this.model = model;
	// this.view = view;
    }

    public InscripcionController(PagoTransferenciaBancariaModel model) {// , InscripcionView view) {
	this.modelBanco = model;
    }

    public InscripcionController() {
	imodel = new InscripcionModel();
    }

    public List<InscripcionDTO> actualizarPagoConTarjeta(String dni, int idCarrera) throws BusinessException {
	List<InscripcionDTO> inscripciones = model.updateInscription(dni, idCarrera);
	BusinessCheck.isTrue(!inscripciones.isEmpty(), "No existe alguna asociacion entre el corredor y la carrera");
	return inscripciones;
    }

    public List<InscripcionDTO> actualizarPagoBancario(String dni, int idCarrera) throws BusinessException {
	List<InscripcionDTO> inscripciones = modelBanco.updateInscription(dni, idCarrera);
	BusinessCheck.isTrue(!inscripciones.isEmpty(), "No existe alguna asociacion entre el corredor y la carrera");
	return inscripciones;
    }

    public int[] comprobarPagosDeCarrera(int idCarrera) {
	int[] resultado = new int[2];

	// Veo cuanto cuesta la inscripción en la carrera
	double precioCarrera; // = carrerasModel.getPrecioCarrera(idCarrera);

	// Leo el fichero de pagos de la carrera
	BufferedReader reader;
	try {
	    reader = new BufferedReader(new FileReader("pagos_carrera_" + Integer.toString(idCarrera) + ".csv"));

	    String linea = reader.readLine();
	    String[] campos;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

	    while (linea != null) {
		campos = linea.split(",");

		// Ahora mismo tengo la cantidad del pago que ha hecho y la fecha en la que se
		// ha realizado
		List<InscripcionDTO> inscripcion = modelBanco.verPagosCarrera(idCarrera, campos[0]);

		String fechaInscripcion = inscripcion.get(0).getFechainscripcion();
		LocalDate fechaLocalDate = LocalDate.parse(fechaInscripcion);

		precioCarrera = getPrecioCarrera(idCarrera, fechaLocalDate);

		// comprobar que la cantidad sea adecuada
		// si es mas anotar en incidencias que hay que devolver x euros
		if (Double.valueOf(campos[2]) > precioCarrera) {
		    double diferencia = Double.valueOf(campos[2]) - precioCarrera;
		    // System.out.println("pagaste mas" + inscripcion.get(0).getDnicorredor() +
		    // idCarrera + "Devolver " + diferencia + " euros");
		    modelBanco.anotarIncidencia(inscripcion.get(0).getDnicorredor(), idCarrera,
			    "Pagó más, devolver " + diferencia + " euros");

		    // si es menos la inscripcion sera anulada (update a anulada) y anotar en
		    // incidencias que hay que
		    // devolver x euros
		} else if (Double.valueOf(campos[2]) < precioCarrera) {
		    // System.out.println("pagaste menos" + inscripcion.get(0).getDnicorredor() +
		    // idCarrera + "Devolver " + campos[2] + " euros");
		    modelBanco.anotarIncidencia(inscripcion.get(0).getDnicorredor(), idCarrera,
			    "Pagó menos, devolver " + campos[2] + " euros");
		    // modelBanco.updateInscriptionAnular(inscripcion.get(0).getDnicorredor(),
		    // idCarrera);
		    resultado[1] = resultado[1] + 1;
		    linea = reader.readLine();
		    continue;
		}

		// si es igual pasamos a comprobar la fecha
		// comprobar que se haya hecho antes de 48h
		if (LocalDate.parse(campos[1])
			.compareTo(LocalDate.parse(inscripcion.get(0).getFechainscripcion()).plusDays(2)) > 0) { // Ha
														 // pagado
														 // más
														 // de
														 // 48h
														 // después
		    // System.out.println("El pago se ha hecho más de 48h después");
		    // System.out.println("" + inscripcion.get(0).getDnicorredor() + idCarrera +
		    // "Devolver " + campos[2] + " euros");
		    modelBanco.anotarIncidencia(inscripcion.get(0).getDnicorredor(), idCarrera,
			    "Pagó fuera de plazo, devolver " + campos[2] + " euros");
		    modelBanco.updateInscriptionAnular(inscripcion.get(0).getDnicorredor(), idCarrera);
		    resultado[1] = resultado[1] + 1;
		    linea = reader.readLine();
		    continue;

		}

		modelBanco.updateInscriptionInscrito(inscripcion.get(0).getDnicorredor(), idCarrera);
		resultado[0] = resultado[0] + 1;
		linea = reader.readLine();
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}

	// despues de comprobar los pagos, pillar todos los que esten en pendiente de
	// pago, verificar si han pasado mas de 48h desde que se han inscrito,
	// si es asi, anular inscripcion (plaza queda libre)
	List<InscripcionDTO> pendientesDePago = modelBanco.verPendientesDePago(idCarrera);

	for (InscripcionDTO pendiente : pendientesDePago) {
	    if (LocalDate.now().compareTo(LocalDate.parse(pendiente.getFechainscripcion()).plusDays(2)) > 0) { // No ha
													       // pagado
													       // en 48h
		// System.out.println("El atleta " + pendiente.getDnicorredor() + " inscrito en
		// la carrera " + idCarrera
		// + " no ha pagado en 48h, se le anula inscripcion");
		modelBanco.anotarIncidencia(pendiente.getDnicorredor(), pendiente.getIdcarrera(),
			"No ha hecho ningún pago en 48h");
		modelBanco.updateInscriptionAnular(pendiente.getDnicorredor(), idCarrera);

		resultado[1] = resultado[1] + 1;
	    }
	}

	return resultado;
    }

    public double getPrecioCarrera(int idCarrera, LocalDate fechaInscripcion) {

	CarrerasModel carrerasModel = new CarrerasModel();

	double resultado;

	List<PlazoDTO> plazos = carrerasModel.verPlazosCarrera(idCarrera);

	if (plazos.isEmpty()) { // La carrera no tiene plazos asociados
	    return PRECIO_POR_DEFECTO;
	}

	PlazoDTO plazoElegido = null;

	for (int i = 0; i < plazos.size(); i++) {
	    LocalDate fechaInicioPlazo = plazos.get(i).getFechaInicio().toLocalDate();
	    LocalDate fechaFinPlazo = plazos.get(i).getFechaFin().toLocalDate();

	    if (fechaInscripcion.isAfter(fechaInicioPlazo) && fechaInscripcion.isBefore(fechaFinPlazo)) {
		if (i != 0) {
		    plazoElegido = plazos.get(i);
		}
	    }
	}

	if (plazoElegido == null) { // La fecha de inscripción del atleta no está dentro de ningún plazo
	    plazoElegido = plazos.get(0);
	}

	resultado = plazoElegido.getCuota();

	return resultado;
    }

    public void updateInscripciones(List<InscripcionDTO> lista) throws BusinessException {
	imodel.updateInscripciones(lista);
    }

    /**
     * Ordena por tiempos mas rapidos a mas lentos los resultados de la carrera de
     * los corredores
     * 
     * @param idcarrera
     * @return
     */
    public List<InscripcionDTO> listInscripcionesByTime(int idcarrera) {
	return imodel.inscripcionesOrdenarPorTiempos(idcarrera);
    }

}
