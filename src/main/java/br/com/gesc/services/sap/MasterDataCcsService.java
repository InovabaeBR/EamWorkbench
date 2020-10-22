package br.com.gesc.services.sap;

import br.com.gesc.model.sap.notaSapPm.CatalogoNotaServico;
import br.com.gesc.services.UtilsService;
import com.sap.conn.jco.JCoException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MasterDataCcsService extends SapConnectService {
    public static void main(String[] args) throws JCoException, IOException, ParseException {
        MasterDataCcsService instance = new MasterDataCcsService();
        UtilsService utils = new UtilsService();

        List<String> headers = Arrays.asList("ARBPL", "WERKS", "LVORM", "KTEXT");
//        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_CEN_TRAB_RESP", headers), "Centros_trab");
//
//        headers = Arrays.asList("WERKS", "VERAN", "KTEXT");
//        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_GRP_PLANEJ", headers), "Grupo_Plan");

//        headers = Arrays.asList("CODE", "PROCE", "DESCR");
//        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_PROCEDENCIA", headers), "Procedencia");
//
//        headers = Arrays.asList("CODE", "PROBJ", "DESCR");
//        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_PARTE_OBJ", headers), "Parte_Objeto");
//
//        headers = Arrays.asList("CODE", "SINTM", "DESCR");
//        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_SINTOMA", headers), "Sintoma");
//
//        headers = Arrays.asList("CODE", "CATEGORIA", "DESCR");
//        utils.createCSVFile(headers, instance.getDadosNotaServico("ET_CATEG_EMERG", headers), "Categoria_Emergencia");
//
//        headers = Arrays.asList("CAT_MED", "CAT_SERV", "QUANT");
//        utils.createCSVFile(headers, instance.getListaTarefa("ET_LISTA_TAREFA", headers), "Lista_Tarefas");

        headers = Arrays.asList("TPCATALOGO", "GRUPOCODE", "CODE", "DESCRICAO", "STATUS", "TIPONS", "PRECO", "CATALOGO");
        utils.exportToJson(instance.getCatalogosNotaServico(), "Catalogos_Notas");


    }

    protected List<String> getCatalogoMedidas() throws JCoException {
        //return filtrarCatalogo("2");
        return null;
    }

    protected List<CatalogoNotaServico> getCatalogosNotaServico() throws JCoException {
        List<String> headers = Arrays.asList("TPCATALOGO", "GRUPOCODE", "CODE", "DESCRICAO", "STATUS", "TIPONS", "PRECO", "CATALOGO");
        List<String> rfcResults = getTableWithoutInput("IT_CATALOGOS", headers, "Z83WMF_CATALOGO_NS");

        List<CatalogoNotaServico> results = new ArrayList<CatalogoNotaServico>();

        for (String result : rfcResults) {
            String[] splitted = result.split("\\|");

            DecimalFormat df = new DecimalFormat("0.00");
            String value = "";

            try {
                value = df.format(df.parse(splitted[6].trim()));
            } catch (ParseException e) {
                value = "";
            }

            CatalogoNotaServico catalogo = new CatalogoNotaServico(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4],
                    splitted[5], value,
                    splitted[7]);
            results.add(catalogo);
        }
        return results;
    }

    protected List<String> getDadosNotaServico(String tableName, List<String> headers) throws JCoException {
        return getTableWithoutInput(tableName, headers, "Z83WMF_INTERF_DADOS_NS");
    }

    protected List<String> getListaTarefa(String tableName, List<String> headers) throws JCoException {
        return getTableWithoutInput(tableName, headers, "Z83WMF_LISTA_TAREFA");
    }

    private List<String> getTableWithoutInput(String tableName, List<String> headers, String rfcName) {
        HashMap<String, List<String>> outputTable = new HashMap<String, List<String>>();
        outputTable.put(tableName, headers);
        return execute(rfcName, outputTable);
    }


//    private List<String> filtrarCatalogo(String tipoCatalogo) throws JCoException {
//        List<String> catalogos = getCatalogosNotaServico();
//        return catalogos.stream().filter(record -> {
//            String[] fields = record.split(";");
//            return tipoCatalogo.equalsIgnoreCase(fields[0]);
//        }).collect(Collectors.toList());
//    }
}
