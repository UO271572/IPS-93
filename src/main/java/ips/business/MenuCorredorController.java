package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.persistence.carreras.CarrerasModel;
import ips.ui.MenuCorredorView;
import ips.ui.carreras.CarrerasView;
import ips.util.Printer;

public class MenuCorredorController {

	private MenuCorredorView view;
	private CarrerasController cc;
	
	public MenuCorredorController(MenuCorredorView view) {
		this.view = view;
		this.cc = new CarrerasController(new CarrerasModel(), new CarrerasView());
		initController();
	}

	public void initController() {
		view.addWindowListener(notCloseDirectly());
		inicializarTablaCarrerasSinFiltro();
		view.getRdbtnVerTodas().addActionListener(accionBotonVerTodas());
		view.getRdbtnAbiertas().addActionListener(accionBotonVerNoCompetidas());
	}
	
	public WindowAdapter notCloseDirectly() {
		return new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	view.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		    	/*int result = JOptionPane.showConfirmDialog(
	            view,
	            "¿Está seguro de que quiere cerrar la aplicación?",
	            "Exit Application",
	            JOptionPane.YES_NO_OPTION);
	 */
	        //if (result == JOptionPane.YES_OPTION)
	            view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    }
		};
	}
	
	


	/*// Acciones
	private ActionListener accionBotonGo(int index) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (index) {
				case 0:

					try {
						List<CarreraDisplayDTO> listaCarreras = cc.getListaCarreras(); // [ADRI] hacer un metodo getListaCarrerasPorCategoria
						/*
							[ADRI] Pone que las categorías son específicas para cada competición (cuando estas tengan categorías específicas, la clase
							Competición debería implementar un método que sea obtenerCategoría)
						 *//*
						añadirListaCarrerasTabla(listaCarreras);
						
					} catch (BusinessException e1) {
						Printer.printBusinessException(e1);
						break;
					}

				}
			}

			
		};
	}*/
	
	
	private ActionListener accionBotonVerNoCompetidas() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inicializarTablaCarrerasConFiltro();
			}
		};
	}

	private ActionListener accionBotonVerTodas() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inicializarTablaCarrerasSinFiltro();
			}
		};
	}

	private void inicializarTablaCarrerasSinFiltro() {
		vaciarTabla();
     	try {
				List<CarreraDisplayDTO> listaCarreras = cc.getListaCarreras(); // [ADRI] hacer un metodo getListaCarrerasPorCategoria
				/*
					[ADRI] Pone que las categorías son específicas para cada competición (cuando estas tengan categorías específicas, la clase
					Competición debería implementar un método que sea obtenerCategoría)
				 */
				añadirListaCarrerasTabla(listaCarreras);
				
			} catch (BusinessException e1) {
				Printer.printBusinessException(e1);
			}
     	
	}
	
	private void vaciarTabla() {
		view.getTableModel().setRowCount(0);
	}

	private void inicializarTablaCarrerasConFiltro() {
		vaciarTabla();
     	try {
				List<CarreraDisplayDTO> listaCarreras = cc.getListaCarrerasFiltradas(); // [ADRI] hacer un metodo getListaCarrerasPorCategoria
				/*
					[ADRI] Pone que las categorías son específicas para cada competición (cuando estas tengan categorías específicas, la clase
					Competición debería implementar un método que sea obtenerCategoría)
				 */
				añadirListaCarrerasTabla(listaCarreras);
				
			} catch (BusinessException e1) {
				Printer.printBusinessException(e1);
			}
	}

	private void añadirListaCarrerasTabla(List<CarreraDisplayDTO> listaCarreras) {
		for (int i = 0; i < listaCarreras.size(); i++) {
			int idCarerra = listaCarreras.get(i).getIdCarrera();
			String nombre = listaCarreras.get(i).getNombre();
			Date fecha = listaCarreras.get(i).getFechaCompeticion();
			String tipo = listaCarreras.get(i).getTipo();
			int plazasDisponibles = listaCarreras.get(i).getPlazasDisponibles();
			double distancia = listaCarreras.get(i).getDistancia();
			String lugar = listaCarreras.get(i).getLugar();
			Object[] data = { idCarerra, nombre, fecha, tipo,lugar, distancia ,plazasDisponibles };
			view.getTableModel().insertRow(0, data);

		}
	}
}
