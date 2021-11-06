package ips.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import ips.business.MenuRolSelectionController;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de la aplicación.
 */
public class SwingMain {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					
					MenuRolSelectionView view = new MenuRolSelectionView();
					MenuRolSelectionController controller = new MenuRolSelectionController(view);
					controller.initController();
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //NOSONAR codigo autogenerado
				}
			}
		});
	}

}
