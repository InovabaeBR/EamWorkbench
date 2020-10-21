package br.com.gesc.services.sap;

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

        String centro = "CG01";
        OrdemPMService instance = new OrdemPMService();
        System.out.println(instance.getDesvios(centro));
    }

    protected List<String> getDesvios(String centro) throws JCoException {
        HashMap<String, String> inputParameters = new HashMap<String, String>();
        inputParameters.put("CENTRO", centro);
        inputParameters.put("IDIOMA", "PT");


        HashMap<String, List<String>> outputTable = new HashMap<String, List<String>>();
        List<String> values = Arrays.asList("CODIGO", "DESCRICAO");
        outputTable.put("DESVIOS", values);
        return execute("/SIGGASM2/PMFED_DESVIO", inputParameters, outputTable);
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
