/**
 * 
 */
package ips.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ips.business.inscripciones.InscripcionDTO;
import ips.ui.MenuInscripcionView;
import ips.util.Printer;

/**
 * @author PC
 *
 */
public class MenuInscripcionController {

    private MenuInscripcionView view;

    public MenuInscripcionController(MenuInscripcionView view) {
	this.view = view;
	initController();
    }

    public void initController() {
	view.addWindowListener(notCloseDirectly());
	view.getBtValidar().addActionListener(actionValidar());
	view.getRdbtnTransferenciaBancaria().addActionListener(accionTransferencia());
	view.getRbtnPagoTarjeta().addActionListener(accionTarjeta());
    }

    private ActionListener accionTransferencia() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		view.getPanelTarjeta().setVisible(false);
		view.getBtValidar().setEnabled(false);
	    }
	};
    }

    private ActionListener accionTarjeta() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		view.getPanelTarjeta().setVisible(true);
		view.getBtValidar().setEnabled(true);
	    }
	};
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

    private ActionListener actionValidar() {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
//		InscripcionController controller = new InscripcionController(new PagoTarjetaModel());
//		try {
//		    if (validarTarjeta() && validarCamposCorredor()) {
////			List<InscripcionDTO> listaActualizacion = controller.actualizarPagoConTarjeta(
////				view.getTxCorredor().getText(),
////				view.getInscView().obtenerCarreraSeleccionada().getIdCarrera());
////			// model.addAll(listaActualizacion);
////			InscripcionDTO[] inscripciones = arrayListToArray(listaActualizacion);
////			view.getListUpdates().setModel(new DefaultComboBoxModel<InscripcionDTO>(inscripciones));// añadir al componente la lista de actualizaciones;
//			// simular con jdialog la pasarela de pago
//			JOptionPane.showMessageDialog(null, "Se esta tramitando el pago... Inscripcion realizada!");
//		    }
//		} catch (BusinessException | NumberFormatException e1) {
//		    JOptionPane.showMessageDialog(null, "No se puedo realizar la inscripcion");
//		    Printer.printBusinessException(e1);
//		}
	    }
	};
    }

    public MenuInscripcionView getMenuView() {
	return view;
    }

    private InscripcionDTO[] arrayListToArray(List<InscripcionDTO> listaInscripcion) {
	InscripcionDTO[] list = new InscripcionDTO[listaInscripcion.size()];
	for (int i = 0; i < listaInscripcion.size(); i++) {
	    list[i] = listaInscripcion.get(i);
	}
	return list;
    }

    /**
     * Comprueba que los datos de la tarjeta son validos
     * 
     * @return
     */
    private boolean validarTarjeta() {
	if (view.getTxNumeroTarjeta().getText().length() < 16) {
	    JOptionPane.showMessageDialog(null, "La longitud del numero de tarjeta debe ser 16");
	    return false;
	}
	Date fechaIntroducida = null;
	try {
	    fechaIntroducida = parseToDate(view.getTxFecha().getText());
	} catch (NumberFormatException e) {
	    JOptionPane.showMessageDialog(null, "Error al introducir los datos el formato no es el adecuado");
	    Printer.printBusinessException(new BusinessException(e));
	}
	if (fechaIntroducida.before(new Date())) {
	    JOptionPane.showMessageDialog(null, "La tarjeta esta caducada");
	    return false;
	}
	return true;
    }

    /**
     * 
     * @param text
     * @return
     */
    @SuppressWarnings("deprecation")
    private Date parseToDate(String text) throws NumberFormatException {
	String separador = Pattern.quote("-");
	String[] date = text.split(separador);
	return new Date(Integer.parseInt(date[0]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
    }

    /**
     * Comprueba que los campos del corredor no esten vacios
     * 
     * @return
     */
    private boolean validarCamposCorredor() {
	try {
	    String[] separador = view.getTxCorredor().getText().trim().split("");
	    if (separador.length != 7) {
		Printer.printBusinessException(new BusinessException(
			"Error al introducir el dni 6 digitos y una letra, debe estar registrado"));
		return false;
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null,
		    "Error al introducir el dni 6 digitos y una letra, debe estar registrado");
	    return false;
	}

	if (view.getTxCorredor().getText() == null || view.getTxCorredor().getText().isEmpty()) {
	    Printer.printBusinessException(new BusinessException());
	    return false;
	}
	return true;
    }

}
