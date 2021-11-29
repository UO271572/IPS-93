package ips.business.inscripciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.ui.carreras.JustificanteCancelacionView;
import ips.ui.inscripciones.EstadoInscripcionesView;
import ips.util.Printer;

public class EstadoInscripcionesController {

    private EstadoInscripcionesView view;
    private InscripcionController ic;

    private List<InscripcionDTO> inscripciones;

    public EstadoInscripcionesController(EstadoInscripcionesView view) {
	this.view = view;
	ic = new InscripcionController();
	inicializarTabla();
	view.getBtCancelarInscripcion().addActionListener(accionCancelarInscripcion());
	view.getBtFinalizar().addActionListener(accionFinalizar());
    }

    private void inicializarTabla() {
	view.getTableModel().setRowCount(0);
	try {
	    String email = view.getCorredor().getEmail();
	    inscripciones = ic.getInscripcionesByEmail(email);
	    rellenarTabla(inscripciones);
	    if (inscripciones.isEmpty()) {
		JOptionPane.showMessageDialog(view, "Aún no se ha inscrito en ninguna carrera");
		view.getBtCancelarInscripcion().setEnabled(false);
	    }
	} catch (BusinessException e1) {
	    Printer.printBusinessException(e1);
	}
    }

    private void rellenarTabla(List<InscripcionDTO> inscripciones) {
	for (InscripcionDTO i : inscripciones) {
	    int idcarrera = i.getIdcarrera();
	    String estadoinscripcion = i.getEstadoinscripcion();
	    Date fechainscripcion = i.getFechainscripcion();
	    int dorsal = i.getDorsal();
	    String incidencia = i.getIncidencia();
	    Time tiempoinicio = i.getTiempoinicio();
	    Time tiempofin = i.getTiempofin();
	    Object[] data = { idcarrera, estadoinscripcion, fechainscripcion, dorsal, incidencia, tiempoinicio,
		    tiempofin };
	    view.getTableModel().insertRow(0, data);
	}
    }

    private ActionListener accionCancelarInscripcion() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    comprobaciones();
		} catch (BusinessException e1) {
		    Printer.printBusinessException(e1);
		}
	    }
	};
    }

    private void comprobaciones() throws BusinessException {
	int i = view.getTable().getSelectedRow();
	if (i == -1) {
	    JOptionPane.showMessageDialog(view, "Debe seleccionar una carrera");
	    return;
	}
	InscripcionDTO inscripcion = inscripciones.get(i);
	if (inscripcion.getEstadoinscripcion() == "CANCELADA" | inscripcion.getEstadoinscripcion() == "ANULADA") {
	    JOptionPane.showMessageDialog(view, "La carrera no se encuentra en estado \"inscrito\"");
	    return;
	}
	CarreraDisplayDTO carrera = new CarrerasController().findByIdCarrera(inscripcion.getIdcarrera());
	if (carrera.getFechamaxcancelacion() == null | carrera.getPorcentajedevo() == -1) {
	    JOptionPane.showMessageDialog(view, "La carrera no admite cancelaciones");
	    return;
	}
	if (!carrera.getFechamaxcancelacion().before(Date.valueOf(LocalDate.now()))) {
	    JOptionPane.showMessageDialog(view, "No es posible cancelar la carrera, se cerró el plazo de cancelación");
	    return;
	}
	ic.calcelarInscripcion(inscripcion);
	cancelar(carrera);
    }

    private void cancelar(CarreraDisplayDTO carrera) {
	try {
	    new CarrerasController().actualizarPlazasCarrera(carrera);
	    JustificanteCancelacionView jv = new JustificanteCancelacionView(view);
	    jv.setImporte(carrera.getPrecio());
	    jv.setVisible(true);
	} catch (BusinessException e) {
	    Printer.printBusinessException(e);
	}
    }

    private ActionListener accionFinalizar() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		view.dispose();
	    }
	};
    }

}
