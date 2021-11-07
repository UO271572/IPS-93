package ips.business.categorias;

import java.util.List;

import ips.business.carreras.CarreraDisplayDTO;
import ips.util.Database;

public class CategoriaModel {

	private Database db=new Database();
	
	public static final String SQL_INSERT_CATEGORIAS_IDCARRERA = "INSERT INTO CATEGORIAS(nombre,edadinicio,edadfin,sexo,idcarrera) "
			+ "VALUES(?,?,?,?,?)";
	
	public void insertarCategorias(CategoriaDTO categoria) {
		
		db.executeUpdate(SQL_INSERT_CATEGORIAS_IDCARRERA, categoria.getNombre(), categoria.getEdadInicio(),
				categoria.getEdadFin(),categoria.getSexo(),categoria.getIdCarrera());
	}
	
}
