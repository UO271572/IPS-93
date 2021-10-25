/**
 * 
 */
package ips.business.inscripciones;

import java.util.List;

import ips.business.BusinessCheck;
import ips.business.BusinessException;
import ips.persistence.pagos.PagoTarjetaModel;
import ips.persistence.pagos.PagoTransferenciaBancariaModel;
import ips.ui.carreras.InscripcionView;

/**
 * @author PC
 *
 */
public class InscripcionController {
	private PagoTarjetaModel model;
	private PagoTransferenciaBancariaModel modelBanco;
	private InscripcionView view;

	public InscripcionController(PagoTarjetaModel model){//, InscripcionView view) {
		this.model = model;
//		this.view = view;
	}
	
	public InscripcionController(PagoTransferenciaBancariaModel model){//, InscripcionView view) {
		this.modelBanco = model;
	}
	
	public List<InscripcionDTO> actualizarPagoConTarjeta(String dni, int idCarrera) throws BusinessException{
		List<InscripcionDTO> inscripciones = model.updateInscription(dni, idCarrera);
		BusinessCheck.isTrue(!inscripciones.isEmpty(),"No existe alguna asociacion entre el corredor y la carrera");
		return inscripciones;
	}
	
	public List<InscripcionDTO> actualizarPagoBancario(String dni, int idCarrera) throws BusinessException{
		List<InscripcionDTO> inscripciones = modelBanco.updateInscription(dni, idCarrera);
		BusinessCheck.isTrue(!inscripciones.isEmpty(),"No existe alguna asociacion entre el corredor y la carrera");
		return inscripciones;
	}
	
}
