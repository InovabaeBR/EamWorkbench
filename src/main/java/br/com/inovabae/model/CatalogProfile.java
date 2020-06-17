package br.com.inovabae.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * T352C
 */
public class CatalogProfile {
    private String catalogProfile;
    private CatalogEnum catalogEnum;
    private List<GroupCode> groupCodes;

    public CatalogProfile(String catalogProfile, CatalogEnum catalogEnum) {
        this(catalogProfile, catalogEnum, null);
    }

    public CatalogProfile(String catalogProfile, CatalogEnum catalogEnum,
                          List<GroupCode> groupCodes) {
        this.catalogProfile = catalogProfile;
        this.catalogEnum = catalogEnum;
        this.groupCodes = groupCodes;
    }

    public String getCatalogProfile() {
        return catalogProfile;
    }

    public void setCatalogProfile(String catalogProfile) {
        this.catalogProfile = catalogProfile;
    }

    public CatalogEnum getCatalogEnum() {
        return catalogEnum;
    }

    public void setCatalogEnum(CatalogEnum catalogEnum) {
        this.catalogEnum = catalogEnum;
    }

    public List<GroupCode> getGroupCodes() {
        return groupCodes;
    }

    public void setGroupCodes(List<GroupCode> groupCodes) {
        this.groupCodes = groupCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CatalogProfile that = (CatalogProfile) o;

        return new EqualsBuilder()
                .append(catalogProfile, that.catalogProfile)
                .append(catalogEnum, that.catalogEnum)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(catalogProfile)
                .toHashCode();
    }
}
