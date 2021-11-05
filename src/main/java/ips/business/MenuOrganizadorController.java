package ips.business;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.DrbgParameters.Reseed;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.business.clasificaciones.ClasificacionController;
import ips.business.clasificaciones.ClasificacionDTO;
import ips.business.corredores.CorredorDTO;
import ips.business.corredores.CorredoresController;
import ips.business.inscripciones.InscripcionController;
import ips.persistence.carreras.CarrerasModel;
import ips.persistence.clasificaciones.ClasificacionModel;
import ips.persistence.corredores.CorredoresModel;
import ips.persistence.pagos.PagoTransferenciaBancariaModel;
import ips.ui.MenuCrearCarreraView;
import ips.ui.MenuOrganizadorView;
import ips.ui.carreras.CarrerasView;
import ips.ui.corredores.CorredoresView;
import ips.util.Printer;

public class MenuOrganizadorController {

	private MenuOrganizadorView view;
	
	public MenuOrganizadorController(MenuOrganizadorView view) {
		this.view = view;
		
		initController();
	}


	public void initController() {
		view.addWindowListener(notCloseDirectly());
		inicializarComboBox();
		//view.getBtnOrganizador().addActionListener(accionBotonOrganizador());
		view.getBtnProcesarPagos().addActionListener(accionProcesaPagosCarrera());
		view.getBtnGenerarClasificacion().addActionListener(accionBotonClasificaSinFiltro());
		view.getBtMostrarClasificacionSinFiltro().addActionListener(accionBotonClasificaSinFiltro());
		cargarCarreras();
		
	}


	private void cargarCarreras() {
		CarrerasController carreraController = new CarrerasController(new CarrerasModel(),new CarrerasView());
		List<CarreraDisplayDTO> listaCarreras = null;
		try {
			listaCarreras = carreraController.getListaCarreras();
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}
		
		DefaultListModel modelo = new DefaultListModel();
		
		for(CarreraDisplayDTO carrera: listaCarreras) {
			modelo.addElement(carrera);
		}
		
		view.getListCarreras().setModel(modelo);
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
	

	private void inicializarComboBox() {
//		view.getCbOpciones().addItem("Ver estado de la competicion"); 
//		view.getCbOpciones().addItem("Ver estado de la clasificacion por sexo");  
//		view.getCbOpciones().addItem("Ver estado de la clasificacion por categoria");  
		// view.getBtnGo().addActionListener(accionBotonGo(view.getCbOpciones().getSelectedIndex()));
		view.getBtnBuscarCorredores().addActionListener(accionBotonBuscarCorredores(view.getListCarreras().getSelectedIndex()));
		view.getBtMostrarClasificacionCategoria().addActionListener(accionBotonClasificaPorCategoria());
		view.getBtMostrarClasificacionSexo().addActionListener(accionBotonClasificaPorSexo());
		view.getBtnCrearCarrera().addActionListener(cambiarAVentanaCrearCarrera());
	}


	//Acciones
	private ActionListener accionBotonClasificaPorSexo() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
				DefaultListModel<ClasificacionDTO> dlm = new DefaultListModel<ClasificacionDTO>();
				try {
					//dlm.addAll(controller.mostrarResultadosPorSexo(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera())); ADRI
					dlm.addAll(controller.mostrarResultadosPorSexo(((CarreraDisplayDTO)view.getListCarreras().getSelectedValue()).getIdCarrera()));
					view.getListCorredores().setModel(dlm);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BusinessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
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
					// dlm.addAll(controller.mostrarResultadosPorCategoria(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera()));
					dlm.addAll(controller.mostrarResultadosPorCategoria(((CarreraDisplayDTO)view.getListCarreras().getSelectedValue()).getIdCarrera()));
					view.getListCorredores().setModel(dlm);
					
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BusinessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		};
		
	}
	
	private ActionListener accionBotonClasificaSinFiltro() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClasificacionController controller = new ClasificacionController(new ClasificacionModel());
				DefaultListModel<ClasificacionDTO> dlm = new DefaultListModel<ClasificacionDTO>();
				try {
					//int index = view.getCbCarreras().getSelectedItem()
					// dlm.addAll(controller.mostrarResultadosPorCategoria(((CarreraDisplayDTO)view.getCbCarreras().getSelectedItem()).getIdCarrera()));
					dlm.addAll(controller.mostrarResultados(((CarreraDisplayDTO)view.getListCarreras().getSelectedValue()).getIdCarrera()));
					view.getListCorredores().setModel(dlm);
					

					for(Component c: view.getPn_FiltrosClasificacion().getComponents()) {
						
						JButton boton = (JButton) c;
						
						boton.setEnabled(true);
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BusinessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
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
					//dlm.addAll(carreraController.getCorredoresByIdCarrera(((CarreraDisplayDTO)(view.getCbCarreras().getSelectedItem())).getIdCarrera()));
					dlm.addAll(carreraController.getCorredoresByIdCarrera(((CarreraDisplayDTO)(view.getListCarreras().getSelectedValue())).getIdCarrera()));
					view.getListCorredores().setModel(dlm);
				} catch (BusinessException e1) {
					Printer.printBusinessException(e1);
				}
			}
		};
	}
	
	private ActionListener accionProcesaPagosCarrera() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InscripcionController inscripcionController = new InscripcionController(new PagoTransferenciaBancariaModel());
				int[] res = inscripcionController.comprobarPagosDeCarrera(((CarreraDisplayDTO)(view.getListCarreras().getSelectedValue())).getIdCarrera());
				
				// queda enseñar el dialogo con el resultado
				String resultado = "Inscripciones procesadas: " + (res[0] + res[1]) + "\n" +
									"Válidas : " + res[0] + "\n" +
									"No válidas: "+ res[1];
				
				JOptionPane.showMessageDialog(view, resultado);
			}
		};
	}
	
	private ActionListener cambiarAVentanaCrearCarrera() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuCrearCarreraView crearCarrera = new MenuCrearCarreraView();
				MenuCrearCarreraController controllerCrearCarrera = new MenuCrearCarreraController(crearCarrera);
				//MenuCorredorController controller = new MenuCorredorController(frame);
				//controller.initController();
				crearCarrera.setVisible(true);
			}
		};
	}
	
	}
	
	

