package br.com.gesc.model.sap.masterData;


/**
 *  <b>Tabela SAP: CRHD</b><br>
 *  Centro de trabalho - equipes de Manutenção
 */
public class CentroTrabalho {

    /**
     * <b>Campo SAP: ARBPL</b><br>
     * Código do Centro de Trabalho
     */
    public String codigo;

    /**
     * <b>Campo SAP: WERKS</b><br>
     * Centro (Planta) do Centro de Trabalho
     */
    public String centro;

    /**
     * <b>Tabela SAP: CRTX</b><br>
     * <b>Campo SAP: KTEXT</b><br>
     * <b>Ligação: CRHD-OBJTY = CRTX-OBJTY e CRHD-OBJID = CRTX-OBJID</b><br>
     * Denominação do Centro de Trabalho
     */
    public String denominacao;
}
    