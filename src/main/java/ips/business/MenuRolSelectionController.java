package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ips.business.inscripciones.InscripcionController;
import ips.ui.MenuCorredorView;
import ips.ui.MenuInscripcionView;
import ips.ui.MenuOrganizadorView;
import ips.ui.MenuRolSelectionView;
import ips.ui.inscripciones.InscripcionView;

public class MenuRolSelectionController {

	private MenuRolSelectionView view;
	
	
	public MenuRolSelectionController(MenuRolSelectionView view) {
		this.view = view;
	}
	
	public void initController() {
		view.getBtnCorredor().addActionListener(accionBotonCorredor());
		view.getBtnOrganizador().addActionListener(accionBotonOrganizador());
		view.getBtInscribirse().addActionListener(actionBotonInscribirse());
		
	}
	
	private ActionListener accionBotonCorredor() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuCorredorView frame = new MenuCorredorView();
				MenuCorredorController controller = new MenuCorredorController(frame);
				controller.initController();
				frame.setVisible(true);
			}
		};
	}
	
	private ActionListener accionBotonOrganizador() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuOrganizadorView frame = new MenuOrganizadorView();
				MenuOrganizadorController controller = new MenuOrganizadorController(frame);
				controller.initController();
				frame.setVisible(true);
			}
		};
	}
	
	
	
	private ActionListener actionBotonInscribirse() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuInscripcionView frame = new MenuInscripcionView();
				MenuInscripcionController controller = new MenuInscripcionController(frame);
				controller.initController();
				frame.setVisible(true);
			}
		};
	}
	
	
	
	
}
