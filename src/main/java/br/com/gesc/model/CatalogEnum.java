package br.com.gesc.model;

public enum CatalogEnum {


    /**
     * CODING = Codificação
     * TASK = Medidas
     * CAUSE = Causas
     * ACTIVITY = Ações
     * OBJECT_PARTS = Partes do Objeto
     * DAMAGE = Sintomas
     */
    CODING("D"),
    TASK("2"),
    CAUSE("5"),
    ACTIVITY("A"),
    OBJECT_PARTS("B"),
    DAMAGE("C");

    public final String catalog;

    CatalogEnum(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalog() {
        return catalog;
    }

    public static CatalogEnum fromCatalog(String catalog) {
        for (CatalogEnum catalogEnum : CatalogEnum.values()) {
            if (catalogEnum.catalog.equals(catalog)) {
                return catalogEnum;
            }
        }
        return null;
    }
}
