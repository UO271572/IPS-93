package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.plazos.PlazoDTO;
import ips.business.plazos.PlazosController;
import ips.business.plazos.VentanaCrearPlazoController;
import ips.persistence.carreras.CarrerasModel;
import ips.ui.MenuCrearCarreraView;
import ips.ui.VentanaCrearPlazoView;
import ips.ui.carreras.CarrerasView;

public class MenuCrearCarreraController {

	public final static int MAX_NUM_PLAZOS = 5;

	private MenuCrearCarreraView view;
	private CarrerasController cc;
	private VentanaCrearPlazoController crearPlazosController;
	private List<PlazoDTO> plazos = new ArrayList<PlazoDTO>();
	private PlazosController plazoController;

	int idCarrera;

	public MenuCrearCarreraController(MenuCrearCarreraView view) {
		this.view = view;
		cc = new CarrerasController(new CarrerasModel(), new CarrerasView());
		initController();
	}

	public void initController() {

		idCarrera = cc.getMaxIdCarrera() + 1;
		this.crearPlazosController = new VentanaCrearPlazoController(idCarrera);
		view.addWindowListener(notCloseDirectly());
		view.getBtnGuardar().addActionListener(crearCarrera());
		view.getBtnCancelar().addActionListener(cancelar());
		view.getBtnAnadir().addActionListener(añadirPlazo());
		view.getBtnBorrar().addActionListener(borrarPlazo());
		view.getBtnModificar().addActionListener(modificarPlazo());
		plazoController = new PlazosController();

	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setPlazos(List<PlazoDTO> plazos) {
		this.plazos = plazos;
	}

	public WindowAdapter notCloseDirectly() {
		return new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				view.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				/*
				 * int result = JOptionPane.showConfirmDialog( view,
				 * "¿Está seguro de que quiere cerrar la aplicación?", "Exit Application",
				 * JOptionPane.YES_NO_OPTION);
				 */
				// if (result == JOptionPane.YES_OPTION)
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

					plazoController.insertPlazos(plazos);
					JOptionPane.showMessageDialog(null,
							"Se ha creado la carrera con los siguientes datos:\n" + "\n\t-Id: " + carrera.getIdCarrera()
									+ "\n\t-Nombre: " + carrera.getNombre() + "\n\t-Lugar: " + carrera.getLugar()
									+ "\n\t-Tipo: " + carrera.getTipo() + "\n\t-Distancia: " + carrera.getDistancia()
									+ "km\n\t-Plazas totales: " + carrera.getPlazasDisponibles()
									+ "\n\t-Fecha de competicion: " + carrera.getFechaCompeticion());
					view.dispose();
				} else {
					JOptionPane.showMessageDialog(view, "Datos incorrectos. Por favor, reviselos.");

				}
			}

		};
	}

	private boolean checkCamposCrearCarrera() {
		Date fechaActual = java.sql.Date.valueOf(LocalDate.now());
		if (view.getTxNombre().getText().isBlank() || view.getTxLugar().getText().isBlank()
				|| (int) (view.getSpDistancia().getValue()) <= 0 || (int) (view.getSpPlazas().getValue()) <= 0
				|| view.getFechaCompeticion().getDate() == null
				|| !view.getFechaCompeticion().getDate().after(fechaActual) || isPlazosNoListo())
			return false;
		return true;
	}

	private boolean isPlazosNoListo() {
		if (view.getTablePlazos().getRowCount() <= 0 || view.getTablePlazos().getRowCount() > 5) {
			return true;
		}
		return false;
	}

	private CarreraDisplayDTO getCamposCarrera() {
		CarreraDisplayDTO carrera = new CarreraDisplayDTO();
		carrera.setDistancia((int) (view.getSpDistancia().getValue()));
		java.sql.Date date = new java.sql.Date(view.getFechaCompeticion().getDate().getTime());
		carrera.setFechaCompeticion(date);
		carrera.setIdCarrera(idCarrera);
		carrera.setNombre(view.getTxNombre().getText());
		carrera.setPlazasDisponibles((int) view.getSpPlazas().getValue());
		carrera.setLugar(view.getTxLugar().getText());
		if (view.getRdbtnMontana().isSelected()) {
			carrera.setTipo(view.getRdbtnMontana().getText());
		} else {
			carrera.setTipo(view.getRdbtnCiudad().getText());
		}
		return carrera;
	}

	private ActionListener cancelar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(view, "¿Estas seguro de que quieres cancelar?");
				if (respuesta == JOptionPane.YES_OPTION) {
					view.dispose();
				}

			}

		};
	}

	private ActionListener añadirPlazo() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.Date fechaCompeticion = view.getFechaCompeticion().getDate();
				if (plazos.size() < 5) {
					if (view.getFechaCompeticion().getDate() != null) {
						VentanaCrearPlazoView plazoView = new VentanaCrearPlazoView();
						crearPlazosController.setView(plazoView);
						crearPlazosController.setFechaCompeticion(new java.sql.Date(fechaCompeticion.getTime()));
						crearPlazosController.setTablePlazosModel(view.getTablePlazosModel());
						crearPlazosController.setListPlazos(plazos);
						plazoView.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null,
								"Asegurese de que introduce la fecha de competicion antes que las de los plazos");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No es posible tener mas de " + MAX_NUM_PLAZOS + " plazos");
				}
			}

		};
	}

	private ActionListener borrarPlazo() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (plazos.size() > 0) {
					int column = 0;
					int row = view.getTablePlazos().getSelectedRow();
					Date fechaInicio = (Date) view.getTablePlazos().getModel().getValueAt(row, column);
					borrarPlazoPorFechaDeInicio(fechaInicio);
				}
			}

		};

	}

	private ActionListener modificarPlazo() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (plazos.size() > 0) {
					int column = 0;
					int row = view.getTablePlazos().getSelectedRow();
					Date fechaInicio = (Date) view.getTablePlazos().getModel().getValueAt(row, column);
					modificarPlazoPorFechaDeInicio(fechaInicio);
				}
			}

		};

	}

	private void borrarPlazoPorFechaDeInicio(Date fechaInicio) {
		for (int i = 0; i < plazos.size(); i++) {
			if (plazos.get(i).getFechaInicio().toString().equals(fechaInicio.toString())) {
				if (i != 0 && i != plazos.size() - 1) {
					plazos.get(i + 1).setFechaInicio(plazos.get(i).getFechaInicio());
					PlazoDTO plazoACambiar = plazos.get(i + 1);
					Object[] data = { plazoACambiar.getFechaInicio(), plazoACambiar.getFechaFin(),
							plazoACambiar.getCuota() };
					view.getTablePlazosModel().removeRow(i);
					view.getTablePlazosModel().removeRow(i);
					view.getTablePlazosModel().insertRow(i, data);

				}else {
					view.getTablePlazosModel().removeRow(i);
				}
				plazos.remove(i);
				
				break;
			}
		}
	}
	
	private void modificarPlazoPorFechaDeInicio(Date fechaInicio) {
		for (int i = 0; i < plazos.size(); i++) {
			if (plazos.get(i).getFechaInicio().toString().equals(fechaInicio.toString())) {
				if (i != 0 && i != plazos.size() - 1) {
					plazos.get(i + 1).setFechaInicio(plazos.get(i).getFechaInicio());
					PlazoDTO plazoACambiar = plazos.get(i + 1);
					Object[] data = { plazoACambiar.getFechaInicio(), plazoACambiar.getFechaFin(),
  							plazoACambiar.getCuota() };
					view.getTablePlazosModel().removeRow(i);
					view.getTablePlazosModel().removeRow(i);
					view.getTablePlazosModel().insertRow(i, data);

				}
				plazos.remove(i);
				view.getTablePlazosModel().removeRow(i);
				return;
			}
		}
	}

}
