package br.com.gesc.services.sap;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SapConnectService {
    protected static SapConnect conn = new SapConnect();
    protected static JCoDestination dest = conn.getDestination();

    /**
     * Execução sem parâmetros
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

    protected JCoFunction setInputParameters(String functionName, HashMap<String, String> inputParameters) {
        JCoFunction function = this.setFunctionName(functionName);

        for (String chave : inputParameters.keySet()) {
            String valor = inputParameters.get(chave);
            function.getImportParameterList().setValue(chave, valor);
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
                        result = StringUtils.join(result, tableName.getValue(outputParam), ";");
                    }
                    results.add(result);
                }
            }

        } catch (JCoException e) {
            e.printStackTrace();
        }
        return results;

    }


}
