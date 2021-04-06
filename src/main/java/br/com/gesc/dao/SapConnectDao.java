package br.com.gesc.dao;

import br.com.gesc.services.sap.SapConnect;
import com.sap.conn.jco.*;
import com.sap.conn.jco.ext.DestinationDataProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class SapConnectDao {
    protected static SapConnectDao conn = new SapConnectDao();
    protected static JCoDestination dest = conn.getDestination();

    /**
     * Execução sem parâmetros
     *
     * @param functionName
     * @param outputTable
     * @return
     */
    protected List<String> execute(String functionName, HashMap<String, List<String>> outputTable) {
        HashMap<String, String> inputParameters = new HashMap<String, String>();
        return this.execute(functionName, inputParameters, outputTable);
    }

    protected List<String> execute(String functionName, HashMap<String, String> inputParameters,
                                   HashMap<String, List<String>> outputTable) {
        JCoFunction function = setInputParameters(functionName, inputParameters);
        return getTableResult(outputTable, function);
    }

    protected List<String> execute(String functionName, HashMap<String, String> inputParameters,
                                   HashMap<String, HashMap<String, String>> inputStructure,
                                   HashMap<String, List<String>> outputTable) {
        JCoFunction function = setInputParameters(functionName, inputParameters, inputStructure);
        return getTableResult(outputTable, function);
    }

    protected JCoFunction setInputParameters(String functionName, HashMap<String, String> inputParameters) {
        JCoFunction function = this.setFunctionName(functionName);

        for (String chave : inputParameters.keySet()) {
            String valor = inputParameters.get(chave);
            function.getImportParameterList().setValue(chave, valor);
        }
        return function;
    }

    protected JCoFunction setInputParameters(String functionName, HashMap<String, String> inputParameters,
                                             HashMap<String, HashMap<String, String>> inputStructure) {

        JCoFunction function = this.setFunctionName(functionName);

        /**
         * Parâmetros simples
         */
        for (String chave : inputParameters.keySet()) {
            String valor = inputParameters.get(chave);
            function.getImportParameterList().setValue(chave, valor);
        }

        /**
         * Estrutura
         */
        for (String chave : inputStructure.keySet()) {
            HashMap<String, String> valor = inputStructure.get(chave);
            for (String chaveInner : valor.keySet()) {
                String valorInner = valor.get(chaveInner);
                function.getImportParameterList().getStructure(chave).setValue(chaveInner, valorInner);
            }
        }

        return function;
    }

    private JCoFunction setFunctionName(String name) {
        JCoFunction function = null;
        try {
            function = dest.getRepository().getFunction(name);

        } catch (JCoException e) {
            e.printStackTrace();
        }
        return function;
    }

    private List<String> getTableResult(HashMap<String, List<String>> outputTable,
                                        JCoFunction function) {
        String result = "";
        List<String> results = new ArrayList<String>();
        try {
            function.execute(dest);

            for (String table : outputTable.keySet()) {
                List<String> outputList = outputTable.get(table);
                JCoTable tableName = function.getExportParameterList().getTable(table);

                for (int i = 0; i < tableName.getNumRows(); i++) {
                    tableName.setRow(i);
                    result = null;
                    for (String outputParam : outputList) {
                        result = StringUtils.join(result, tableName.getValue(outputParam), "|");
                    }
                    results.add(result);
                }
            }

        } catch (JCoException e) {
            e.printStackTrace();
        }
        return results;
    }

    private JCoDestination getDestination() {
        // This will create a file called mySAPSystem.jcoDestination
        String DESTINATION_NAME1 = "ComgasDev";

        /**
         * "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\SAPgui.exe" /H/177.67.63.18/S/3299/W/emergencial/M/172.31.0.173/S/3602/G/SPACE
         */
        //SAP ECC - DEV

        Properties conn = new Properties();
        conn.setProperty(DestinationDataProvider.JCO_ASHOST, "comgas364"); //host
        conn.setProperty(DestinationDataProvider.JCO_SAPROUTER, "/H/177.67.63.18/S/3299/W/emergencial");
        conn.setProperty("jco.server.saprouter", "/M/172.31.0.173/S/3602/G/SPACE");
        conn.setProperty(DestinationDataProvider.JCO_SYSNR, "02"); //system number
        conn.setProperty(DestinationDataProvider.JCO_CLIENT, "100"); //client number
        conn.setProperty(DestinationDataProvider.JCO_USER, "TR010375");
        conn.setProperty(DestinationDataProvider.JCO_PASSWD, "ecnv@85@");
        conn.setProperty(DestinationDataProvider.JCO_LANG, "pt");

//         //SAP CCQ
//         Properties conn = new Properties();
//         conn.setProperty(DestinationDataProvider.JCO_ASHOST, "comgas230.comgas.local"); //host
////         conn.setProperty(DestinationDataProvider.JCO_SAPROUTER, "/H/177.67.63.18/S/3299/W/emergencial");
////         conn.setProperty("jco.server.saprouter", "/M/172.31.0.173/S/3602/G/SPACE");
//         conn.setProperty(DestinationDataProvider.JCO_SYSNR, "14"); //system number
//         conn.setProperty(DestinationDataProvider.JCO_CLIENT, "200"); //client number
//         conn.setProperty(DestinationDataProvider.JCO_USER, "TR010375");
//         conn.setProperty(DestinationDataProvider.JCO_PASSWD, "ecnv@86@");
//         conn.setProperty(DestinationDataProvider.JCO_LANG, "pt");


        createDataFile(DESTINATION_NAME1, "jcoDestination", conn);
        // This will use that destination file to connect to SAP
        JCoDestination destination = null;
        try {
            destination = JCoDestinationManager.getDestination(DESTINATION_NAME1);

            System.out.println("Attributes:");
            System.out.println(destination.getAttributes());
            System.out.println();
            destination.ping();

        } catch (JCoException e) {
            e.printStackTrace();
        }
        return destination;
    }

    /**
     * Create data file
     *
     * @param name
     * @param suffix
     * @param properties
     */
    static void createDataFile(String name, String suffix, Properties properties) {
        File cfg = new File(name + "." + suffix);
        if (cfg.exists()) {
            cfg.delete();
        }

        try {
            FileOutputStream fos = new FileOutputStream(cfg, false);
            properties.store(fos, "for tests only !");
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
        }

    }
}
