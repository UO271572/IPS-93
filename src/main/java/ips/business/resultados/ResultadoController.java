package ips.business.resultados;

import java.util.List;

import ips.business.BusinessException;
import ips.persistence.resultados.ResultadoModel;

public class ResultadoController {

    private ResultadoModel model;

    public ResultadoController(ResultadoModel m) {
	this.model = m;
    }

    public ResultadoController() {
	model = new ResultadoModel();
    }

    public void updateResultados(List<ResultadoDTO> lista) throws BusinessException {
	model.updateResultados(lista);
    }

}
