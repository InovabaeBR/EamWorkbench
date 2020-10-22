package br.com.gesc.model.sap.notaSapPm;

import org.apache.commons.lang3.StringUtils;

public class CatalogoNotaServico {
    private String TPCATALOGO;
    private String GRUPOCODE;
    private String CODE;
    private String DESCRICAO;
    private String STATUS;
    private String TIPONS;
    private String PRECO;
    private String CATALOGO;

    public CatalogoNotaServico(String TPCATALOGO, String GRUPOCODE, String CODE, String DESCRICAO, String STATUS,
                               String TIPONS, String PRECO, String CATALOGO) {
        this.TPCATALOGO = TPCATALOGO;
        this.GRUPOCODE = GRUPOCODE;
        this.CODE = CODE;
        this.DESCRICAO = DESCRICAO;
        this.STATUS = STATUS;
        this.TIPONS = TIPONS;
        this.PRECO = PRECO;
        this.CATALOGO = CATALOGO;
    }

    @Override
    public String toString() {
        return "CatalogoNotaServico{" +
                "TPCATALOGO='" + TPCATALOGO + '\'' +
                ", GRUPOCODE='" + GRUPOCODE + '\'' +
                ", CODE='" + CODE + '\'' +
                ", DESCRICAO='" + DESCRICAO + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", TIPONS='" + TIPONS + '\'' +
                ", PRECO='" + PRECO + '\'' +
                ", CATALOGO='" + CATALOGO + '\'' +
                '}';
    }

    public String getTPCATALOGO() {
        return TPCATALOGO;
    }

    public void setTPCATALOGO(String TPCATALOGO) {
        this.TPCATALOGO = TPCATALOGO;
    }

    public String getGRUPOCODE() {
        return GRUPOCODE;
    }

    public void setGRUPOCODE(String GRUPOCODE) {
        this.GRUPOCODE = GRUPOCODE;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public void setDESCRICAO(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTIPONS() {
        return TIPONS;
    }

    public void setTIPONS(String TIPONS) {
        this.TIPONS = TIPONS;
    }

    public String getPRECO() {
        return PRECO;
    }

    public void setPRECO(String PRECO) {
        this.PRECO = PRECO;
    }

    public String getCATALOGO() {
        return CATALOGO;
    }

    public void setCATALOGO(String CATALOGO) {
        this.CATALOGO = CATALOGO;
    }
}
