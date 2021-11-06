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
				CategoriaDTO cat = new CategoriaDTO(nombre, edadInicio, edadFinal, (String) view.getCb_sexo().getModel().getSelectedItem());
				
				DefaultListModel modelo = (DefaultListModel) view.getListaCategorias().getModel();
				modelo.addElement(cat);
				view.getListaCategorias().setModel(modelo);
				view.dispose();
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
				CategoriaDTO cat = new CategoriaDTO(nombre, edadInicio, edadFinal, (String) view.getCb_sexo().getModel().getSelectedItem());
				
				DefaultListModel modelo = (DefaultListModel) view.getListaCategorias().getModel();
				modelo.setElementAt(cat, indice);
				view.getListaCategorias().setModel(modelo);
				view.dispose();
			}
			
			
		}
		
	}
	
}
