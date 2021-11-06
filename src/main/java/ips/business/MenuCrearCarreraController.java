package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.categorias.CategoriaDTO;
import ips.business.categorias.CategoriaModel;
import ips.business.categorias.EditarCategoriaController;
import ips.persistence.carreras.CarrerasModel;
import ips.ui.MenuCrearCarreraView;
import ips.ui.carreras.CarrerasView;
import ips.ui.categorias.EditarCategoriaView;

public class MenuCrearCarreraController {
	private MenuCrearCarreraView view;
	private CarrerasController cc = new CarrerasController();
	
	private AñadirCategoriaAction añadirCategoria = new AñadirCategoriaAction();
	private EliminarCategoriaAction eliminarCategoria = new EliminarCategoriaAction();
	private ModificarCategoriaAction modificarCategoria = new ModificarCategoriaAction();
	
	private int minEdadAbsoluta, maxEdadAbsoluta, minEdadMasculina, maxEdadMasculina, minEdadFemenina, maxEdadFemenina;

	public MenuCrearCarreraController(MenuCrearCarreraView view) {
		this.view = view;
	}

	public void initController() {
			view.addWindowListener(notCloseDirectly());
			view.getBtnGuardar().addActionListener(crearCarrera());
			view.getBtnCancelar().addActionListener(cancelar());
			
			view.getBtnAñadir().addActionListener(añadirCategoria);
			view.getBtnEliminar().addActionListener(eliminarCategoria);
			view.getBtnModificar().addActionListener(modificarCategoria);
			
			DefaultListModel modelo = new DefaultListModel();
			
			modelo.addAll(generarCategoriasPorDefecto());
			
			view.getLista_Categorias().setModel(modelo);
	}
	
	private List<CategoriaDTO> generarCategoriasPorDefecto() {
		
		List<CategoriaDTO> res = new ArrayList<CategoriaDTO>();
		CategoriaDTO cat1 = new CategoriaDTO("Absoluto - De 18 a 24", 18, 24, "Absoluto", CategoriaDTO.NO_CARRERA);
		CategoriaDTO cat2 = new CategoriaDTO("Masculino - De 18 a 24", 18, 24, "Masculino", CategoriaDTO.NO_CARRERA);
		CategoriaDTO cat3 = new CategoriaDTO("Femenino - De 18 a 24", 18, 24, "Femenino", CategoriaDTO.NO_CARRERA);
		
		res.add(cat1);
		res.add(cat2);
		res.add(cat3);
		
		minEdadAbsoluta = 18;
		maxEdadAbsoluta = 24;
		
		minEdadMasculina = 18;
		maxEdadMasculina = 24;
		
		minEdadFemenina = 18;
		maxEdadFemenina = 24;
		
		return res;
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

	class AñadirCategoriaAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			EditarCategoriaView editarCategoria = new EditarCategoriaView(view.getLista_Categorias());
			EditarCategoriaController editarCategoriaController = new EditarCategoriaController(editarCategoria);
			
			//editarCategoriaController.initController();
			editarCategoria.setVisible(true);
		}
		
	}
	
	class ModificarCategoriaAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			EditarCategoriaView editarCategoria = new EditarCategoriaView(view.getLista_Categorias());
			
			CategoriaDTO categoria = (CategoriaDTO) view.getLista_Categorias().getSelectedValue();//new CategoriaDTO(()view.getLista_Categorias().getSelectedValue(), 0, 0, null)
			
			int indice = view.getLista_Categorias().getSelectedIndex();
			
			new EditarCategoriaController(editarCategoria, categoria, indice);
			
			editarCategoria.setVisible(true);
		}
		
	}
	
	class EliminarCategoriaAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultListModel modelo = (DefaultListModel) view.getLista_Categorias().getModel();
			modelo.remove(view.getLista_Categorias().getSelectedIndex());
			view.getLista_Categorias().setModel(modelo);
		}
		
	}
	
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
				DefaultListModel<CategoriaDTO> modelo = (DefaultListModel<CategoriaDTO>) view.getLista_Categorias().getModel();
				
				for(int i = 0; i < modelo.getSize(); i++) {  
					modelo.get(i).setIdCarrera(carrera.getIdCarrera());
					categoriaModel.insertarCategorias(modelo.get(i));
				}
				//
				
				JOptionPane.showMessageDialog(null, "Se ha creado la carrera con los siguientes datos:\n"
						+ "\n\t-Id: "+carrera.getIdCarrera() + "\n\t-Nombre: " + carrera.getNombre() +
						"\n\t-Lugar: " + carrera.getLugar() + "\n\t-Tipo: "+carrera.getTipo()+"\n\t-Distancia: "+carrera.getDistancia()+
						"km\n\t-Plazas totales: " + carrera.getPlazasDisponibles() +"\n\t-Fecha de competicion: " + carrera.getFechaCompeticion());
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
				view.getDateChooser().getDate() == null || !view.getDateChooser().getDate().after(fechaActual))
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
