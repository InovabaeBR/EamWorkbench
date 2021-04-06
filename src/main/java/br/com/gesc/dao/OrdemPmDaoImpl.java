package br.com.gesc.dao;

import br.com.gesc.model.ApontamentoHoraOrdem;
import br.com.gesc.model.sap.ordemSapPm.OrdemSapPm;
import br.com.gesc.model.sap.ordemSapPm.OrdemSapPmOperacao;
import com.sap.conn.jco.JCoStructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OrdemPmDaoImpl implements IOrdemPmDao {
    private static final SapConnectDao jcoConn = new SapConnectDao();

    @Override
    public List<String> alterarStatus(OrdemSapPm ordem, String statusExterno) {
        HashMap<String, String> inputParameters = new HashMap<String, String>();
        inputParameters.put("I_APLIC", "GESC");
        inputParameters.put("I_UNAME_APLIC", "TR010375");

        /**
         * Chave = Nome da estrutura
         * Valor = Nome do campo/valor
         */
        HashMap<String, HashMap<String, String>> inputStructure = new HashMap<String, HashMap<String, String>>();
        HashMap<String, String> content = new HashMap<String, String>();
        content.put("STATUS_EXTERNO", statusExterno);
        content.put("ORDEM", String.valueOf(ordem.getNumero()));
        inputStructure.put("I_STATUS_USUARIO", content);

        HashMap<String, List<String>> outputTable = new HashMap<String, List<String>>();
        List<String> values = Arrays.asList("FLAG_SUCESSO");
        outputTable.put("ET_RETORNO", values);

        return jcoConn.execute("Y_APM_SAP_GESC_ATZ_ST_USR_ORD", inputParameters, inputStructure, outputTable);
    }

    @Override
    public List<String> getDesvios(String centro) {
        HashMap<String, String> inputParameters = new HashMap<String, String>();
        inputParameters.put("CENTRO", centro);
        inputParameters.put("IDIOMA", "PT");

        HashMap<String, List<String>> outputTable = new HashMap<String, List<String>>();
        List<String> values = Arrays.asList("CODIGO", "DESCRICAO");
        outputTable.put("DESVIOS", values);
        return jcoConn.execute("/SIGGASM2/PMFED_DESVIO", inputParameters, outputTable);
    }

    @Override
    public void criar(OrdemSapPm ordem) {

    }

    @Override
    public OrdemSapPm exibir(long numeroOrdem) {
        return null;
    }

    @Override
    public List<OrdemSapPm> exibir() {
        return null;
    }

    @Override
    public void atualizar(OrdemSapPm ordem) {

    }

    @Override
    public void apontarDesvios(List<ApontamentoHoraOrdem> apontamentos) {

    }

    @Override
    public void apontarAtividades(List<ApontamentoHoraOrdem> apontamentos) {

    }

    @Override
    public void anexar(OrdemSapPm ordem, List<String> hyperlinks) {

    }

    @Override
    public void atribuirPessoa(List<Long> numeroPessoas, OrdemSapPmOperacao operacao) {

    }

    @Override
    public void encerrarTecnicamente(OrdemSapPm ordem) {

    }


}
