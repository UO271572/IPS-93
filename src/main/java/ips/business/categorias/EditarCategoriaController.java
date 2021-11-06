package ips.business.categorias;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import ips.ui.categorias.EditarCategoriaView;

public class EditarCategoriaController {
	
	private EditarCategoriaView view;
	private CategoriaDTO categoria;
	private int indice;
	
	private TerminarAñadirCategoriaAction terminarAñadirCategoria = new TerminarAñadirCategoriaAction();
	private TerminarModificarCategoriaAction terminarModificarCategoriaAction = new TerminarModificarCategoriaAction();
	
	public EditarCategoriaController(EditarCategoriaView view) {
		this.view = view;
		view.getBtnTerminar().addActionListener(terminarAñadirCategoria);
	}

	public EditarCategoriaController(EditarCategoriaView editarCategoria, CategoriaDTO categoria, int indice) {
		this.view = editarCategoria;
		this.categoria = categoria;
		this.indice = indice;
		view.getTxtNombre().setText(categoria.getNombre());
		view.getSpinner_edadInicio().setValue(categoria.getEdadInicio());
		view.getSpinner_edadFinal().setValue(categoria.getEdadFin());
		view.getCb_sexo().setSelectedItem(categoria.getSexo());
		view.getBtnTerminar().addActionListener(terminarModificarCategoriaAction);
	}
	
	class TerminarAñadirCategoriaAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String nombre = view.getTxtNombre().getText();
			int edadInicio = (int) view.getSpinner_edadInicio().getValue();
			int edadFinal = (int) view.getSpinner_edadFinal().getValue();
			
			// comprobar que edad inicio sea menor que edad final
			if(edadInicio > edadFinal) {
			
				JOptionPane.showMessageDialog(view, "La edad de inicio debe ser menor o igual que la de fin. Por favor, cámbiela.");
				
			} else {
			
				if(!comprobarCategoriaValida(new CategoriaDTO(nombre, edadInicio, edadFinal, (String) view.getCb_sexo().getModel().getSelectedItem(), CategoriaDTO.NO_CARRERA))) {
					JOptionPane.showMessageDialog(view, "Las categorías no son válidas. Por favor, revíselas. No puede haber huecos en los rangos");
				} else {
				
					CategoriaDTO cat = new CategoriaDTO(nombre, edadInicio, edadFinal, (String) view.getCb_sexo().getModel().getSelectedItem(), CategoriaDTO.NO_CARRERA);
					
					DefaultListModel modelo = (DefaultListModel) view.getListaCategorias().getModel();
					modelo.addElement(cat);
					view.getListaCategorias().setModel(modelo);
					view.dispose();
				
				}
			}
			
			
			
		}
		
	}
	
	class TerminarModificarCategoriaAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String nombre = view.getTxtNombre().getText();
			int edadInicio = (int) view.getSpinner_edadInicio().getValue();
			int edadFinal = (int) view.getSpinner_edadFinal().getValue();
			
			// comprobar que edad inicio sea menor que edad final
			if(edadInicio > edadFinal) {
				
				JOptionPane.showMessageDialog(view, "La edad de inicio debe ser menor o igual que la de fin. Por favor, cámbiela.");
			} else {
			
				if(!comprobarCategoriaValida(new CategoriaDTO(nombre, edadInicio, edadFinal, (String) view.getCb_sexo().getModel().getSelectedItem(), CategoriaDTO.NO_CARRERA))) {
					JOptionPane.showMessageDialog(view, "Las categorías no son válidas. Por favor, revíselas. No puede haber huecos en los rangos");
				} else {
					
						CategoriaDTO cat = new CategoriaDTO(nombre, edadInicio, edadFinal, (String) view.getCb_sexo().getModel().getSelectedItem(), CategoriaDTO.NO_CARRERA);
						
						DefaultListModel modelo = (DefaultListModel) view.getListaCategorias().getModel();
						modelo.setElementAt(cat, indice);
						view.getListaCategorias().setModel(modelo);
						view.dispose();
					}
				}
				
			}
			
			
		}
	
	private boolean comprobarCategoriaValida(CategoriaDTO categoria) {
		
		int minAbsoluto = Integer.MAX_VALUE;
		
		DefaultListModel<CategoriaDTO> modelo = (DefaultListModel<CategoriaDTO>) view.getListaCategorias().getModel();
		
		if(modelo.size() == 0) {
			return true;
		}
		
		int numberoCategoriasSexo = 0;
		
		for(int i = 0; i < modelo.getSize(); i++) {
			if(modelo.get(i).getSexo().equals((String) view.getCb_sexo().getModel().getSelectedItem())){
				numberoCategoriasSexo++;
			}
		}
		
		if(numberoCategoriasSexo == 0) {
			return true;
		}
		
		for(int i = 0; i < modelo.getSize(); i++) {  
			if(modelo.get(i).getSexo().equals((String) view.getCb_sexo().getModel().getSelectedItem())) {
				if(modelo.get(i).getEdadInicio() < minAbsoluto) {
					minAbsoluto = modelo.get(i).getEdadInicio();
				}
			}
		}
		
		int maxAbsoluto = -1;
		
		for(int i = 0; i < modelo.getSize(); i++) {  
			if(modelo.get(i).getSexo().equals((String) view.getCb_sexo().getModel().getSelectedItem())) {
				if(modelo.get(i).getEdadFin() > maxAbsoluto) {
					maxAbsoluto = modelo.get(i).getEdadFin();
				}
			}
		}
		
		boolean res = false;
		
		if(categoria.getEdadFin() == (minAbsoluto - 1)) { // añado un rango por debajo
			return true;
		} else if(categoria.getEdadInicio() == (maxAbsoluto + 1)) {
			return true;
		}
		return res;
	}
	
}
