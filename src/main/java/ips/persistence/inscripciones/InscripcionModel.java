package ips.persistence.inscripciones;

import java.util.ArrayList;
import java.util.List;

import ips.util.Database;

public class InscripcionModel {

    private Database db = new Database();

    private static final String SQL_GET_DNICORREDOR = "SELECT DNICORREDOR FROM INSCRIPCIONES WHERE IDCARRERA=1 AND DORSAL=?";

    public List<String> getDniCorredoresByDorsales(List<Integer> dorsales) {
	List<String> dnis = new ArrayList<String>();
	for (Integer dorsal : dorsales)
	    dnis.add(db.executeQueryPojo(String.class, SQL_GET_DNICORREDOR, dorsal).get(1));
	return dnis;
    }

}
