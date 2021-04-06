package br.com.gesc.dao;

import br.com.gesc.model.ApontamentoHoraOrdem;
import br.com.gesc.model.sap.ordemSapPm.OrdemSapPm;
import br.com.gesc.model.sap.ordemSapPm.OrdemSapPmOperacao;

import java.util.List;

public interface IOrdemPmDao {
    public void criar(OrdemSapPm ordem);
    public OrdemSapPm exibir(long numeroOrdem);
    public List<OrdemSapPm> exibir();
    public void atualizar(OrdemSapPm ordem);
    public void apontarDesvios(List<ApontamentoHoraOrdem> apontamentos);
    public void apontarAtividades(List<ApontamentoHoraOrdem> apontamentos);
    public void anexar(OrdemSapPm ordem, List<String> hyperlinks);
    public void atribuirPessoa(List<Long> numeroPessoas, OrdemSapPmOperacao operacao);
    public void encerrarTecnicamente(OrdemSapPm ordem);
    public List<String> alterarStatus(OrdemSapPm ordem, String statusExterno);
    public List<String> getDesvios(String centro);
}
