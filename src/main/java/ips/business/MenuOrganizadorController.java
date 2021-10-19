package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.clasificaciones.ClasificacionController;
import ips.business.clasificaciones.ClasificacionDTO;
import ips.business.corredores.CorredorDTO;
import ips.business.corredores.CorredoresController;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.clasificaciones.ClasificacionModel;
import ips.persistence.corredores.CorredoresModel;
import ips.ui.MenuOrganizadorView;
import ips.ui.carreras.CarrerasView;
import ips.ui.corredores.CorredoresView;
import ips.util.Printer;

public class MenuOrganizadorController {

	private MenuOrganizadorView view;
	
	public MenuOrganizadorController(MenuOrganizadorView view) {
		this.view = view;
	}


	public void initController() {
		inicializarComboBox();
		//view.getBtnOrganizador().addActionListener(accionBotonOrganizador());
		
	}
	

	private void inicializarComboBox() {
		view.getCbOpciones().addItem("Ver estado de la competicion"); 
//		view.getCbOpciones().addItem("Ver estado de la clasificacion por sexo");  
//		view.getCbOpciones().addItem("Ver estado de la clasificacion por categoria");  
		view.getBtnGo().addActionListener(accionBotonGo(view.getCbOpciones().getSelectedIndex()));
		view.getBtnBuscarCorredores().addActionListener(accionBotonBuscarCorredores(view.getCbCarreras().getSelectedIndex()));
		view.getBtMostrarClasificacionCategoria().addActionListener(accionBotonClasificaPorCategoria());
		view.getBtMostrarClasificacionSexo().addActionListener(accionBotonClasificaPorSexo());
	}


	//Acciones
	private ActionListener accionBotonClasificaPorSexo() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
				DefaultListModel<ClasificacionDTO> dlm = new DefaultListModel<ClasificacionDTO>();
				try {
					dlm.addAll(controller.mostrarResultadosPorSexo(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera()));
					view.getListCorredores().setModel(dlm);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BusinessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		};
		
	}
	
	
	private ActionListener accionBotonClasificaPorCategoria() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
				DefaultListModel<ClasificacionDTO> dlm = new DefaultListModel<ClasificacionDTO>();
				try {
					//int index = view.getCbCarreras().getSelectedItem()
					dlm.addAll(controller.mostrarResultadosPorCategoria(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera()));
					view.getListCorredores().setModel(dlm);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BusinessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		};
		
	}
	
	
	
	
	private ActionListener accionBotonBuscarCorredores(Object index) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CorredoresController carreraController = new CorredoresController(new CorredoresModel(),
						new CorredoresView());
				try {
					DefaultListModel<CorredorDTO> dlm = new DefaultListModel<CorredorDTO>();
					dlm.addAll(carreraController.getCorredoresByIdCarrera(((CarreraDisplayDTO)(view.getCbCarreras().getSelectedItem())).getIdCarrera()));
					view.getListCorredores().setModel(dlm);
				} catch (BusinessException e1) {
					Printer.printBusinessException(e1);
				}
			}
		};
	}
	
	private ActionListener accionBotonGo(int index) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch(index) {
					case 0:
						CarrerasController carreraController = new CarrerasController(new CarrerasModel(),new CarrerasView());
					try {
						List<CarreraDisplayDTO> listaCarreras = carreraController.getListaCarreras();
//						DefaultListModel dlm = new DefaultListModel();
//						dlm.addAll(listaCarreras);
						CarreraDisplayDTO[] carreras = arrayListToArray(listaCarreras);
						view.getCbCarreras().setModel(new DefaultComboBoxModel<CarreraDisplayDTO>(carreras));
						view.getBtnBuscarCorredores().setEnabled(true);
						view.getListCorredores().setEnabled(true);
						view.getBtMostrarClasificacionSexo().setEnabled(true);
						view.getBtMostrarClasificacionCategoria().setEnabled(true);
					} catch (BusinessException e1) {
						Printer.printBusinessException(e1);
						break;
					}
					
					case 1:
//						ClasificacionController clasController = new ClasificacionController(new ClasificacionModel());
//						List<ClasificacionDTO> listaClas = clasController.mostrarResultadosPorSexo(index);
//						//ClasificacionDTO[] clasificaciones = arrayClassificacionListToArray(clasController);
//						view.getCbCarreras().setModel(new DefaultComboBoxModel<ClasificacionDTO>(clasificaciones));
//						
						break;
					case 2:
						
						break;
				}
			}

			private ClasificacionDTO[] arrayClassificacionListToArray(List<ClasificacionDTO> listaCarreras) {
				ClasificacionDTO[] list = new ClasificacionDTO[listaCarreras.size()];
				for(int i = 0; i<listaCarreras.size();i++) {
					list[i] = listaCarreras.get(i);
				}
				return list;
			}
			private CarreraDisplayDTO[] arrayListToArray(List<CarreraDisplayDTO> listaCarreras) {
				CarreraDisplayDTO[] list = new CarreraDisplayDTO[listaCarreras.size()];
				for(int i = 0; i<listaCarreras.size();i++) {
					list[i] = listaCarreras.get(i);
				}
				return list;
			}
		};
	}
	
	
}
