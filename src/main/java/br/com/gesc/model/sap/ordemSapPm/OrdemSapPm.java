package br.com.gesc.model.sap.ordemSapPm;

import java.util.Date;
import java.util.List;

/**
 *  <b>Tabela SAP: AUFK</b>
 */
public class OrdemSapPm {
    /**
     * <b>Campo SAP: AUFNR</b><br>
     * Número da ordem PM
     */
    private Long numero;

    /**
     * <b>Campo SAP:</b> AUART
     * <br>Tipo de ordem
     */
    private String tipo;

    /**
     * <b>Campo SAP: AUTYP</b><br>
     * Categoria de ordem<br>
     *      [20 = Ordem PM<br>
     *      30 = Diagrama de rede PS ]
      */
    private String categoria;

    /**
     *<b>Campo SAP: ERNAM</b><br>
     * Autor da ordem
     */
    private String criadoPor;

    /**
     * <b>Campo SAP: ERDAT</b><br>
     * Data de criação da ordem
     */
    private Date dataEntrada;

    /**
     * Atividades a serem executadas na ordem PM
     */
    private List<OrdemSapPmOperacao> operacoes;

    /**
     * Materiais planejados a serem utilizados na ordem PM<br>
     * Serão considerados somente materiais de estoque (não materiais de compra direta)
     */
    private List<OrdemSapPmComponente> componentes;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public List<OrdemSapPmOperacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<OrdemSapPmOperacao> operacoes) {
        this.operacoes = operacoes;
    }

    public List<OrdemSapPmComponente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<OrdemSapPmComponente> componentes) {
        this.componentes = componentes;
    }
}
