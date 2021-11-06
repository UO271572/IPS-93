package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.persistence.carreras.CarrerasModel;
import ips.ui.MenuCrearCarreraView;
import ips.ui.carreras.CarrerasView;

public class MenuCrearCarreraController {
	private MenuCrearCarreraView view;
	private CarrerasController cc;

	public MenuCrearCarreraController(MenuCrearCarreraView view) {
		this.view = view;
		cc = new CarrerasController(new CarrerasModel(),new CarrerasView());
		initController();
	}

	public void initController() {
			view.addWindowListener(notCloseDirectly());
			view.getBtnGuardar().addActionListener(crearCarrera());
			view.getBtnCancelar().addActionListener(cancelar());
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

	// AUXILIARES
	private ActionListener crearCarrera() {
			return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (checkCamposCrearCarrera()) {
				CarreraDisplayDTO carrera = getCamposCarrera();
				cc.insertarCarrera(carrera);
				JOptionPane.showMessageDialog(null, "Se ha creado la carrera con los siguientes datos:\n"
						+ "\n\t-Id: "+carrera.getIdCarrera() + "\n\t-Nombre: " + carrera.getNombre() +
						"\n\t-Lugar: " + carrera.getLugar() + "\n\t-Tipo: "+carrera.getTipo()+"\n\t-Distancia: "+carrera.getDistancia()+
						"km\n\t-Plazas totales: " + carrera.getPlazasDisponibles() +"\n\t-Fecha de competicion: " + carrera.getFechaCompeticion()
						+"\n\t-Plazas reservadas: " + carrera.getPlazasReservadas());
				view.dispose();
				}
				else {
					JOptionPane.showMessageDialog(view, "Datos incorrectos. Por favor, reviselos.");
					
				}
			}

	};
	}
	
	private boolean checkCamposCrearCarrera() {
		Date fechaActual = java.sql.Date.valueOf(LocalDate.now());
		if(view.getTxNombre().getText().isBlank() || view.getTxLugar().getText().isBlank() || 
				(int)(view.getSpDistancia().getValue()) <=0 || (int)(view.getSpPlazas().getValue()) <=0 ||
				view.getDateChooser().getDate() == null || !view.getDateChooser().getDate().after(fechaActual)
				|| ((int)view.getSpPlazas().getValue() - (int)view.getSpPlazasReservadas().getValue() < 0))
		return false;
			return true;
	}
	
	private CarreraDisplayDTO getCamposCarrera() {
		CarreraDisplayDTO carrera = new CarreraDisplayDTO();
		carrera.setDistancia((int)(view.getSpDistancia().getValue()));
		java.sql.Date date = new java.sql.Date(view.getDateChooser().getDate().getTime());
		carrera.setFechaCompeticion(date);
		carrera.setIdCarrera(cc.getMaxIdCarrera()+1);
		carrera.setNombre(view.getTxNombre().getText());
		carrera.setPlazasDisponibles((int) view.getSpPlazas().getValue());
		carrera.setPlazasReservadas((int)view.getSpPlazasReservadas().getValue());
		carrera.setLugar(view.getTxLugar().getText());
		if(view.getRdbtnMontana().isSelected()) {
		carrera.setTipo(view.getRdbtnMontana().getText());
		}
		else {
		carrera.setTipo(view.getRdbtnCiudad().getText());
		}
		return carrera;
	}
	
	private ActionListener cancelar() {
			return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(view, "¿Estas seguro de que quieres cancelar?");
				if(respuesta == JOptionPane.YES_OPTION) {
					view.dispose();
				}
				
			}
			
	};
}
	
}
