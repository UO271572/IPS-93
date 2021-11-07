package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ips.ui.MenuCorredorView;
import ips.ui.MenuOrganizadorView;
import ips.ui.MenuRolSelectionView;

public class MenuRolSelectionController {

	private MenuRolSelectionView view;
	
	
	public MenuRolSelectionController(MenuRolSelectionView view) {
		this.view = view;
	}
	
	public void initController() {
		view.addWindowListener(notCloseDirectly());
		view.getBtnCorredor().addActionListener(accionBotonCorredor());
		view.getBtnOrganizador().addActionListener(accionBotonOrganizador());
		//view.getBtInscribirse().addActionListener(actionBotonInscribirse());	
	}
	
	public WindowAdapter notCloseDirectly() {
		return new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	view.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		        int result = JOptionPane.showConfirmDialog(
		            view,
		            "¿Está seguro de que quiere cerrar la aplicación?",
		            "Exit Application",
		            JOptionPane.YES_NO_OPTION);
		 
		        if (result == JOptionPane.YES_OPTION)
		            view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    }
		};
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
				//controller.initController();
				frame.setVisible(true);
			}
		};
	}
	
	
	
//	private ActionListener actionBotonInscribirse() {
//		return new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MenuInscripcionView frame = new MenuInscripcionView();
//				MenuInscripcionController controller = new MenuInscripcionController(frame);
//				controller.initController();
//				frame.setVisible(true);
//			}
//		};
//	}
	
	
	
	
}
