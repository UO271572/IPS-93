package ips.persistence.resultados;

import java.util.List;

import ips.business.resultados.ResultadoDTO;
import ips.util.Database;

public class ResultadoModel {

    private Database db = new Database();

    public static final String SQL_UPDATE_RESULTADO = "update resultados set tiempoinicio=? and tiempofin=? where dnicorredor=?";

    public void updateResultados(List<ResultadoDTO> lista) {
	for (ResultadoDTO resultadoDTO : lista)
	    db.executeUpdate(SQL_UPDATE_RESULTADO, resultadoDTO.getTiempoinicio(), resultadoDTO.getTiempofin(),
		    resultadoDTO.getDnicorredor());
    }

}
