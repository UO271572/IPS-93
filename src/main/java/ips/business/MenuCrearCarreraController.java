package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.categorias.CategoriaDTO;
import ips.business.categorias.CategoriaModel;
import ips.business.categorias.EditarCategoriaController;
import ips.business.plazos.PlazoDTO;
import ips.business.plazos.PlazosController;
import ips.business.plazos.VentanaCrearPlazoController;
import ips.ui.MenuCrearCarreraView;
import ips.ui.VentanaCrearPlazoView;
import ips.ui.categorias.EditarCategoriaView;

public class MenuCrearCarreraController {

    public final static int MAX_NUM_PLAZOS = 5;

    private MenuCrearCarreraView view;
    private CarrerasController cc = new CarrerasController();

    private AñadirCategoriaAction añadirCategoria = new AñadirCategoriaAction();
    private EliminarCategoriaAction eliminarCategoria = new EliminarCategoriaAction();
    private ModificarCategoriaAction modificarCategoria = new ModificarCategoriaAction();

    private int minEdadAbsoluta, maxEdadAbsoluta, minEdadMasculina, maxEdadMasculina, minEdadFemenina, maxEdadFemenina;
    private VentanaCrearPlazoController crearPlazosController;
    private List<PlazoDTO> plazos = new ArrayList<PlazoDTO>();
    private PlazosController plazoController;

    int idCarrera;

    public MenuCrearCarreraController(MenuCrearCarreraView view) {
	this.view = view;
	// cc = new CarrerasController(new CarrerasModel(), new CarrerasView());
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
	plazoController = new PlazosController();

	view.getBtnAñadir().addActionListener(añadirCategoria);
	view.getBtnEliminar().addActionListener(eliminarCategoria);
	view.getBtnModificar().addActionListener(modificarCategoria);

	DefaultListModel modelo = new DefaultListModel();

	modelo.addAll(generarCategoriasPorDefecto());

	view.getLista_Categorias().setModel(modelo);

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

    private List<CategoriaDTO> generarCategoriasPorDefecto() {

	List<CategoriaDTO> res = new ArrayList<CategoriaDTO>();
	CategoriaDTO cat1 = new CategoriaDTO("Juvenil M", 18, 34, "Masculino", CategoriaDTO.NO_CARRERA);
	CategoriaDTO cat2 = new CategoriaDTO("Juvenil F", 18, 34, "Femenino", CategoriaDTO.NO_CARRERA);

	res.add(cat1);
	res.add(cat2);

	minEdadMasculina = 18;
	maxEdadMasculina = 24;

	minEdadFemenina = 18;
	maxEdadFemenina = 24;

	return res;
    }

    class AñadirCategoriaAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    EditarCategoriaView editarCategoria = new EditarCategoriaView(view.getLista_Categorias());
	    EditarCategoriaController editarCategoriaController = new EditarCategoriaController(editarCategoria);

	    // editarCategoriaController.initController();
	    editarCategoria.setVisible(true);
	}

    }

    class ModificarCategoriaAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    EditarCategoriaView editarCategoria = new EditarCategoriaView(view.getLista_Categorias());

	    CategoriaDTO categoria = (CategoriaDTO) view.getLista_Categorias().getSelectedValue();// new
												  // CategoriaDTO(()view.getLista_Categorias().getSelectedValue(),
												  // 0, 0, null)

	    int indice = view.getLista_Categorias().getSelectedIndex();

	    new EditarCategoriaController(editarCategoria, categoria, indice);

	    editarCategoria.setVisible(true);
	}

    }

    class EliminarCategoriaAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

	    if (comprobarCambioSeguro((CategoriaDTO) view.getLista_Categorias().getSelectedValue())) {
		DefaultListModel modelo = (DefaultListModel) view.getLista_Categorias().getModel();
		modelo.remove(view.getLista_Categorias().getSelectedIndex());
		view.getLista_Categorias().setModel(modelo);
	    } else {
		JOptionPane.showMessageDialog(view, "Este cambio va a generar huecos.");
	    }
	}

	public boolean comprobarCambioSeguro(CategoriaDTO viejo) {
	    int minSexo = Integer.MAX_VALUE;
	    int maxSexo = -1;

	    DefaultListModel<CategoriaDTO> modelo = (DefaultListModel<CategoriaDTO>) view.getLista_Categorias()
		    .getModel();

	    for (int i = 0; i < modelo.getSize(); i++) {
		if (modelo.get(i).getSexo().equals(viejo.getSexo())) {
		    if (modelo.get(i).getEdadInicio() < minSexo) {
			minSexo = modelo.get(i).getEdadInicio();
		    }

		    if (modelo.get(i).getEdadFin() > maxSexo) {
			maxSexo = modelo.get(i).getEdadFin();
		    }
		}
	    }

	    if (viejo.getEdadInicio() == minSexo || viejo.getEdadFin() == maxSexo) {
		return true;
	    } else {
		return false;
	    }
	}

    }

    // AUXILIARES
//	private ActionListener crearCarrera() {
//		return new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				CategoriaModel categoriaModel = new CategoriaModel();
//
//				if (checkCamposCrearCarrera()) {
//					CarreraDisplayDTO carrera = getCamposCarrera();
//					cc.insertarCarrera(carrera);
//					
//					//
//					DefaultListModel<CategoriaDTO> modelo = (DefaultListModel<CategoriaDTO>) view.getLista_Categorias().getModel();
//					
//					for(int i = 0; i < modelo.getSize(); i++) {  
//						modelo.get(i).setIdCarrera(carrera.getIdCarrera());
//						categoriaModel.insertarCategorias(modelo.get(i));
//					}
//					//
//
//					plazoController.insertPlazos(plazos);
//					JOptionPane.showMessageDialog(null,
//							"Se ha creado la carrera con los siguientes datos:\n" + "\n\t-Id: " + carrera.getIdCarrera()
//									+ "\n\t-Nombre: " + carrera.getNombre() + "\n\t-Lugar: " + carrera.getLugar()
//									+ "\n\t-Tipo: " + carrera.getTipo() + "\n\t-Distancia: " + carrera.getDistancia()
//									+ "km\n\t-Plazas totales: " + carrera.getPlazasDisponibles()
//									+ "\n\t-Fecha de competicion: " + carrera.getFechaCompeticion());
//					view.dispose();
//				} else {
//					JOptionPane.showMessageDialog(view, "Datos incorrectos. Por favor, reviselos.");
//
//				}
//			}
//
//		};
//	}

    // AUXILIARES
    private ActionListener crearCarrera() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		CategoriaModel categoriaModel = new CategoriaModel();

		if (checkCamposCrearCarrera()) {
		    CarreraDisplayDTO carrera = getCamposCarrera();
		    cc.insertarCarrera(carrera);

		    //
		    DefaultListModel<CategoriaDTO> modelo = (DefaultListModel<CategoriaDTO>) view.getLista_Categorias()
			    .getModel();

		    for (int i = 0; i < modelo.getSize(); i++) {
			modelo.get(i).setIdCarrera(carrera.getIdCarrera());
			categoriaModel.insertarCategorias(modelo.get(i));
		    }
		    //

		    plazoController.insertPlazos(plazos);
		    // double scale = Math.pow(10, -2);
		    JOptionPane.showMessageDialog(null,
			    "Se ha creado la carrera con los siguientes datos:\n" + "\n\t-Id: " + carrera.getIdCarrera()
				    + "\n\t-Nombre: " + carrera.getNombre() + "\n\t-Lugar: " + carrera.getLugar()
				    + "\n\t-Tipo: " + carrera.getTipo() + "\n\t-Distancia: " + carrera.getDistancia()
				    + "km\n\t-Plazas totales: " + carrera.getPlazasDisponibles()
				    + "\n\t-Fecha de competicion: " + carrera.getFechaCompeticion()
				    + "\n\t-Plazas reservadas: " + carrera.getPlazasReservadas());
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
		|| (double) (view.getSpDistancia().getValue()) <= 0 || (int) (view.getSpPlazas().getValue()) <= 0
		|| view.getFechaCompeticion().getDate() == null
		|| !view.getFechaCompeticion().getDate().after(fechaActual)
		|| ((int) view.getSpPlazas().getValue() - (int) view.getSpPlazasReservadas().getValue() < 0)
		|| isPlazosNoListo())
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
	carrera.setDistancia((double) (view.getSpDistancia().getValue()));
	java.sql.Date date = new java.sql.Date(view.getFechaCompeticion().getDate().getTime());
	carrera.setFechaCompeticion(date);
	carrera.setIdCarrera(idCarrera);
	carrera.setNombre(view.getTxNombre().getText());
	carrera.setPlazasDisponibles((int) view.getSpPlazas().getValue());
	carrera.setPlazasReservadas((int) view.getSpPlazasReservadas().getValue());
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

		} else {
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
