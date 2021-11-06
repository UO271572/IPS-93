package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.dorsales.DorsalesController;
import ips.business.inscripciones.InscripcionDTO;
import ips.persistence.dorsales.DorsalesModel;
import ips.ui.inscripciones.DorsalesView;

public class MenuDorsalesController {
	private DorsalesView dorsalView;
	private DorsalesController dorsalController;
	
	public MenuDorsalesController(DorsalesView dv) {
		this.dorsalView = dv;
		this.dorsalController = new DorsalesController(new DorsalesModel());
		iniciarController();
	}
	
	
	
	
	//NO HAY ACTIONS LISTENER NI MAS BOTONES PARA REALIZAR ACCIONES POR AHORA
	
	
	
	
	/**
	 * Iniciamos el controlador
	 */
	private void iniciarController() {
		dorsalView.getBtOk().addActionListener(accionCerrarVentana());
		asignarDorsalesACorredores();
		mostrarDorsales();
	}

	
	private void asignarDorsalesACorredores() {
		try {
			dorsalController.asignarDorsales(((CarreraDisplayDTO)dorsalView.getMenOrgView().getListCorredores().getSelectedValue()).getIdCarrera());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private void mostrarDorsales() {
		List<InscripcionDTO> listaActualizacion = dorsalController.getInscripciones(((CarreraDisplayDTO)dorsalView.getMenOrgView().getListCorredores().getSelectedValue()).getIdCarrera());
		InscripcionDTO[] inscripciones = arrayListToArray(listaActualizacion);
		dorsalView.getListInscripcionDorsal().setModel(new DefaultComboBoxModel<InscripcionDTO>(inscripciones));
	}




	private ActionListener accionCerrarVentana() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dorsalView.dispose();
			}
		};
	}
	
	private InscripcionDTO[] arrayListToArray(List<InscripcionDTO> listaInscripcion) {
		InscripcionDTO[] list = new InscripcionDTO[listaInscripcion.size()];
		for(int i = 0; i<listaInscripcion.size();i++) {
			list[i] = listaInscripcion.get(i);
		}
		return list;
	}
}
