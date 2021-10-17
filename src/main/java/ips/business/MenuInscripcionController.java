/**
 * 
 */
package ips.business;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.inscripciones.InscripcionController;
import ips.business.inscripciones.InscripcionDTO;
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
				//DefaultListModel<InscripcionDto> model = new DefaultListModel<>();
				
				try {
					if(validarCampos()) {
						List<InscripcionDTO> listaActualizacion = controller.actualizarPagoConTarjeta(view.getTxCorredor().getText(), 
								Integer.parseInt(view.getTxIdentificadorCarrera().getText()));
						
						//model.addAll(listaActualizacion);
						InscripcionDTO[] inscripciones = arrayListToArray(listaActualizacion);
						view.getListUpdates().setModel(new DefaultComboBoxModel<InscripcionDTO>(inscripciones));//añadir al componente la lista de actualizaciones;
						//simular con jdialog la pasarela de pago
						JOptionPane.showMessageDialog(null, "Se esta tramitando el pago... Inscripcion realizada!");
					}
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(null, "No se puedo realizar la inscripcion");
					Printer.printBusinessException(e1);
				}
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
	
	
	private boolean validarCampos() {
		if(view.getTxCorredor().getText()==null || view.getTxIdentificadorCarrera().getText()==null)  {
			Printer.printBusinessException(new BusinessException());
			return false;
		}
		 if(view.getTxCorredor().getText().isEmpty() || view.getTxIdentificadorCarrera().getText().isEmpty()) {
			Printer.printBusinessException(new BusinessException());
			return false;
		}
		return true;
	}
	
}
