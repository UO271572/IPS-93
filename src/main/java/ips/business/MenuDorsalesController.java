package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.List;

import javax.swing.JOptionPane;

import ips.business.carreras.CarreraDisplayDTO;
import ips.business.dorsales.DorsalesController;
import ips.business.inscripciones.InscripcionDTO;
import ips.persistence.dorsales.DorsalesModel;
import ips.ui.MenuOrganizadorView;
import ips.ui.inscripciones.DorsalesView;

public class MenuDorsalesController {
    private DorsalesView dorsalView;
    private DorsalesController dorsalController;

    //
    private MenuOrganizadorView menOrgView;

    public MenuDorsalesController(DorsalesView dv, MenuOrganizadorView menOrgView) {
	this.menOrgView = menOrgView;
	this.dorsalView = dv;
	this.dorsalController = new DorsalesController(new DorsalesModel());
	iniciarController();
    }

    /**
     * Iniciamos el controlador
     */
    private void iniciarController() {
	dorsalView.getBtOk().addActionListener(accionCerrarVentana());
	CarreraDisplayDTO dto = ((CarreraDisplayDTO) menOrgView.getListCarreras().getSelectedValue());
	// comprobamos que las carreras esten en estado cerrado
	if (isCarreraCerrada(dto)) {
	    asignarDorsalesACorredores(dto);
	    mostrarDorsales(dto);
	} else {
	    JOptionPane.showMessageDialog(null, "La carrera esta en estado ABIERTO\nNo se puede asignar los dorsales");
	}
    }

    /**
     * Cmprueba que la carrera esta en estado cerrado
     * 
     * @param dto
     * @return
     */
    private boolean isCarreraCerrada(CarreraDisplayDTO dto) {
	return (dto.getEstadoCarrera() == null || dto.getEstadoCarrera().equals("ABIERTO")) ? false : true;
    }

    private void asignarDorsalesACorredores(CarreraDisplayDTO dto) {
	try {
	    dorsalController.asignarDorsales(dto.getIdCarrera());
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
    }

    private void mostrarDorsales(CarreraDisplayDTO dto) {
	List<InscripcionDTO> listaActualizacion = dorsalController.getInscripciones(dto.getIdCarrera());
	vaciarTabla();
	añadirListaDorsalesTabla(listaActualizacion);
//		InscripcionDTO[] inscripciones = arrayListToArray(listaActualizacion);
//		dorsalView.getListInscripcionDorsal().setModel(new DefaultComboBoxModel<InscripcionDTO>(inscripciones));
    }

    private void vaciarTabla() {
	dorsalView.getModel().setRowCount(0);
    }

    private void añadirListaDorsalesTabla(List<InscripcionDTO> listaActualizacion) {
	for (int i = 0; i < listaActualizacion.size(); i++) {
	    String dnicorredor = listaActualizacion.get(i).getDnicorredor();
	    int idcarrera = listaActualizacion.get(i).getIdcarrera();
	    String estadoinscripcion = listaActualizacion.get(i).getEstadoinscripcion();
	    String fechainscripcion = listaActualizacion.get(i).getFechainscripcion();
	    int dorsal = listaActualizacion.get(i).getDorsal();
	    String incidencia = listaActualizacion.get(i).getIncidencia();
	    Time tiempoInicio = listaActualizacion.get(i).getTiempoinicio();
	    Time tiempoFin = listaActualizacion.get(i).getTiempofin();
	    Object[] data = { dnicorredor, idcarrera, estadoinscripcion, fechainscripcion, dorsal, incidencia,
		    tiempoInicio, tiempoFin };
	    dorsalView.getModel().insertRow(0, data);
	}
    }

    private ActionListener accionCerrarVentana() {
	return new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		dorsalView.dispose();
	    }
	};
    }

    private InscripcionDTO[] arrayListToArray(List<InscripcionDTO> listaInscripcion) {
	InscripcionDTO[] list = new InscripcionDTO[listaInscripcion.size()];
	for (int i = 0; i < listaInscripcion.size(); i++) {
	    list[i] = listaInscripcion.get(i);
	}
	return list;
    }
}
