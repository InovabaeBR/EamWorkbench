package br.com.gesc.sap.services;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;

public class OrdemPMService {
    static SapConnect conn = new SapConnect();
    static JCoDestination dest = conn.getDestination();

    public static void main(String[] args) throws JCoException {
        SapConnect conn = new SapConnect();
        JCoDestination dest = conn.getDestination();

        getDetail("000005084426");
    }

    protected static void getDetail(String number) throws JCoException {
        JCoFunction function = dest.getRepository().getFunction("BAPI_ALM_ORDER_GET_DETAIL");

        function.getImportParameterList().setValue("NUMBER", number);
        try {
            function.execute(dest);
            //System.out.println(function.getExportParameterList().getString("ES_HEADER"));
            //JCoTable es_header = function.getExportParameterList().getTable("ES_HEADER");]
            JCoStructure es_header = function.getExportParameterList().getStructure("ES_HEADER");

            System.out.println(es_header.getString("SHORT_TEXT"));

            // Para tabelas
//            for (int i = 0; i < es_header.getNumRows(); i++) {
//                es_header.setRow(i);
//                System.out.println(es_header.getValue("SHORT_TEXT"));
//            }

        } catch (JCoException ex) {
            if (ex.getKey().equals("CARR_NOT_FOUND")) {
                System.out.println("Airline company not found with given id.");
            }
        }
        System.out.println();
    }

}
