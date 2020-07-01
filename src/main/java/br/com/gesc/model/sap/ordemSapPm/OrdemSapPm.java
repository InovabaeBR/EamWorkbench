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
    public Long numero;

    /**
     * <b>Campo SAP:</b> AUART
     * <br>Tipo de ordem
     */
    public String tipo;

    /**
     * <b>Campo SAP: AUTYP</b><br>
     * Categoria de ordem<br>
     *      [20 = Ordem PM<br>
     *      30 = Diagrama de rede PS ]
      */
    public String categoria;

    /**
     *<b>Campo SAP: ERNAM</b><br>
     * Autor da ordem
     */
    public String criadoPor;

    /**
     * <b>Campo SAP: ERDAT</b><br>
     * Data de criação da ordem
     */
    public Date dataEntrada;

    /**
     * Operações da ordem PM
     */
    public List<OrdemSapPmOperacao> operacoes;

    /**
     * Componentes da ordem PM
     */
    public List<OrdemSapPmComponente> componentes;
}
