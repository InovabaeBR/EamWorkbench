package br.com.gesc.services.sap;

import br.com.gesc.dao.OrdemPmDaoImpl;
import br.com.gesc.model.sap.ordemSapPm.OrdemSapPm;
import com.sap.conn.jco.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OrdemPMService extends SapConnectService {
    public static void main(String[] args) throws JCoException {
//        SapConnect conn = new SapConnect();
//        JCoDestination dest = conn.getDestination();

//        String[] ordens = new String[]{"000005084426", "000005084433"};
//
//        for (String ordem : ordens) {
//            getDetail(ordem);
//        }

//        String centro = "CG01";
//        OrdemPMService instance = new OrdemPMService();
//        System.out.println(instance.getDesvios(centro));

        OrdemPMService service = new OrdemPMService();
        OrdemSapPm ordem = new OrdemSapPm();
        ordem.setNumero(113123l);
        List<String> retorno = service.alterarStatus(ordem, "EXCO");
        retorno.forEach(System.out::println);
    }

    protected List<String> getDesvios(String centro) throws JCoException {
        OrdemPmDaoImpl dao = new OrdemPmDaoImpl();
        return dao.getDesvios(centro);
    }

    protected List<String> alterarStatus(OrdemSapPm ordem, String statusExterno) {
        OrdemPmDaoImpl dao = new OrdemPmDaoImpl();
        return dao.alterarStatus(ordem, statusExterno);
    }

    protected OrdemSapPm getDetail(String number) throws JCoException {
        HashMap<String, String> inputParams = new HashMap<String, String>();
        inputParams.put("NUMBER", number);

        JCoFunction function = setInputParameters("BAPI_ALM_ORDER_GET_DETAIL", inputParams);

        try {
            function.execute(dest);
            JCoStructure es_header = function.getExportParameterList().getStructure("ES_HEADER");
            System.out.println(es_header.getString("SHORT_TEXT"));

            JCoTable et_operations = function.getTableParameterList().getTable("ET_OPERATIONS");

            for (int i = 0; i < et_operations.getNumRows(); i++) {
                et_operations.setRow(i);
                System.out.println(et_operations.getValue("ACTIVITY"));
                System.out.println(et_operations.getValue("CONTROL_KEY"));
            }

        } catch (JCoException ex) {
            if (ex.getKey().equals("CARR_NOT_FOUND")) {
                System.out.println("Airline company not found with given id.");
            }
        }
        return null;
    }
}
