package ips.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import ips.business.BusinessException;
import ips.business.corredores.CorredorDTO;

public abstract class FileUtil {

    public static void loadFile(File nombreFicheroEntrada, List<CorredorDTO> listaCatalogo) throws BusinessException {

	String linea;
	String[] datos = null;

	try {
	    BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
	    while (fichero.ready()) {
		linea = fichero.readLine();
		datos = linea.split("#");
		CorredorDTO corredor = new CorredorDTO();
		corredor.setNombre(datos[0]);
		corredor.setApellidos(datos[1]);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = format.parse(datos[2]);
		java.sql.Date fechaNac = new java.sql.Date(parsed.getTime());
		corredor.setFechaNacimiento(fechaNac);
		corredor.setDniCorredor(datos[3]);
		corredor.setEmail(datos[4]);
		corredor.setSexo(datos[5]);
		listaCatalogo.add(corredor);
	    }
	    fichero.close();
	} catch (FileNotFoundException fnfe) {
	    System.out.println("El archivo no se ha encontrado.");
	} catch (IOException ioe) {
	    new RuntimeException("Error de entrada/salida.");
	} catch (ParseException e) {
	    throw new BusinessException("Ha habido un error de formato");
	}
    }

}
