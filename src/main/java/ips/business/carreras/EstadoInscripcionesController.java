package ips.business.carreras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;

import ips.business.BusinessException;
import ips.business.inscripciones.InscripcionController;
import ips.business.inscripciones.InscripcionDTO;
import ips.ui.carreras.EstadoInscripcionesView;
import ips.ui.carreras.JustificanteCancelacionView;
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
	    String fechainscripcion = i.getFechainscripcion();
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
		comprobaciones();
	    }
	};
    }

    private void comprobaciones() {
	int i = view.getTable().getSelectedRow();
	if (i == -1) {
	    JOptionPane.showMessageDialog(view, "Debe seleccionar una carrera");
	    return;
	}
	InscripcionDTO inscripcion = inscripciones.get(i);
	if (inscripcion.getEstadoinscripcion() == "CANCELADA") {
	    JOptionPane.showMessageDialog(view, "La carrera ya se encuentra cancelada actualmente");
	    return;
	}
	if (inscripcion.getEstadoinscripcion() == "ANULADA") {
	    JOptionPane.showMessageDialog(view, "La carrera se encuentra anulada actualmente");
	    return;
	}
	/*
	 * Si la carrera seleccionada -> su fecha tope de cancelación es anterior a la
	 * fecha actual o es null o el porcentaje a devolver es null =) mensaje error.
	 * Sino, procesar cancelación y mostrar justificante. Guardar en bbdd la fecha
	 * cancelación.
	 */
	if (Time.valueOf(LocalTime.now()).after(inscripcion.getTiempoinicio()))
	    JOptionPane.showMessageDialog(view, "No puede cancelar la carrera, se cerró el plazo de cancelación");
	ic.calcelarInscripcion(inscripcion);
	cancelar(inscripcion.getIdcarrera());
    }

    private void cancelar(int id) {
	try {
	    CarreraDisplayDTO carrera = new CarrerasController().findByIdCarrera(id);
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
