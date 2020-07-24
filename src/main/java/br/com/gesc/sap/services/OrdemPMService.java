package br.com.gesc.sap.services;

import com.sap.conn.jco.*;

public class OrdemPMService {
    static SapConnect conn = new SapConnect();
    static JCoDestination dest = conn.getDestination();

    public static void main(String[] args) throws JCoException {
        SapConnect conn = new SapConnect();
        JCoDestination dest = conn.getDestination();

        String[] ordens = new String[]{"000005084426", "000005084433"};

        for (String ordem : ordens) {
            getDetail(ordem);
        }
    }

    protected static void getDetail(String number) throws JCoException {
        JCoFunction function = dest.getRepository().getFunction("BAPI_ALM_ORDER_GET_DETAIL");

        function.getImportParameterList().setValue("NUMBER", number);
        try {
            function.execute(dest);
            JCoStructure es_header = function.getExportParameterList().getStructure("ES_HEADER");
            System.out.println(es_header.getString("SHORT_TEXT"));

            JCoTable et_operations = function.getTableParameterList().getTable("ET_OPERATIONS");

            for (int i=0 ; i < et_operations.getNumRows(); i++) {
                et_operations.setRow(i);
                System.out.println(et_operations.getValue("ACTIVITY"));
                System.out.println(et_operations.getValue("CONTROL_KEY"));
            }

        } catch (JCoException ex) {
            if (ex.getKey().equals("CARR_NOT_FOUND")) {
                System.out.println("Airline company not found with given id.");
            }
        }
        System.out.println();
    }
}
