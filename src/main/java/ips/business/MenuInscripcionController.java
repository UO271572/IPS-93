/**
 * 
 */
package ips.business;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;

import ips.business.inscripciones.InscripcionController;
import ips.business.inscripciones.InscripcionDto;
import ips.persistence.pagos.PagoTarjetaModel;
import ips.ui.MenuInscripcionView;
import ips.ui.inscripciones.InscripcionView;
import ips.util.Printer;

/**
 * @author PC
 *
 */
public class MenuInscripcionController {
	
	private MenuInscripcionView view;
	
	
	/**
	 * Constructor
	 * @param view
	 */
	public MenuInscripcionController(MenuInscripcionView view) {
		this.view = view;
	}
	
	
	public void initController() {
		iniciar();
	}
	
	private void iniciar() {
		view.getBtValidar().addActionListener(actionValidar());
	}


	private ActionListener actionValidar() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InscripcionController controller = new InscripcionController(new PagoTarjetaModel(), new InscripcionView());
				DefaultListModel<InscripcionDto> model = new DefaultListModel<>();
				
				try {
					if(validarCampos()) {
						List<InscripcionDto> listaActualizacion = controller.actualizarPagoConTarjeta(view.getTxCorredor().getSelectedText(), Integer.parseInt(view.getTxIdentificadorCarrera().getSelectedText()));
						model.addAll(listaActualizacion);
						view.getListUpdates().setModel(model);//añadir al componente la lista de actualizaciones;
					}
				} catch (BusinessException e1) {
					Printer.printBusinessException(e1);
				}
			}
		};
	}
	
	
	
	private boolean validarCampos() {
		if(view.getTxCorredor().getSelectedText().isEmpty() || view.getTxIdentificadorCarrera().getSelectedText().isEmpty()) {
			Printer.printBusinessException(new BusinessException());
			return false;
		}
		return true;
	}
	
	
//	btValidar.addActionListener(new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		if(validarCampos()) {
//			//hacer el update
//			//simular transaccion con Jdialig
//		}
//	}
//});
}
