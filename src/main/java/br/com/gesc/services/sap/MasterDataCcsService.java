package br.com.gesc.services.sap;

import br.com.gesc.services.UtilsService;
import com.sap.conn.jco.JCoException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MasterDataCcsService extends SapConnectService {
    public static void main(String[] args) throws JCoException, IOException {
        MasterDataCcsService instance = new MasterDataCcsService();
        UtilsService utils = new UtilsService();

        List<String> headers = Arrays.asList("ARBPL", "WERKS", "LVORM", "KTEXT");
        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_CEN_TRAB_RESP", headers), "Centros_trab");


        headers = Arrays.asList("WERKS", "VERAN", "KTEXT");
        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_GRP_PLANEJ", headers), "Grupo_Plan");

    }

    protected List<String> getDadosNotaServico(String tableName, List<String> headers) throws JCoException {
        HashMap<String, List<String>> outputTable = new HashMap<String, List<String>>();
        outputTable.put(tableName, headers);
        return execute("Z83WMF_INTERF_DADOS_NS", outputTable);
    }
}
