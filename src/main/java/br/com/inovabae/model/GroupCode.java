package br.com.inovabae.model;

import java.io.Serializable;
import java.util.Date;

public class GroupCode implements Serializable {
    private CatalogEnum catalogEnum;
    private String codeGroup;
    private String shortText;
    private UserAdmData userAdmData;
    private Boolean isUsed;
    private Boolean isInactive;
    private int status;

    public GroupCode(CatalogEnum catalogEnum, String codeGroup, String shortText,
                     UserAdmData userAdmData, Boolean isUsed, Boolean isInactive,
                     int status) {
        this.catalogEnum = catalogEnum;
        this.codeGroup = codeGroup;
        this.shortText = shortText;
        this.userAdmData = userAdmData;
        this.isUsed = isUsed;
        this.isInactive = isInactive;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupCode groupCode = (GroupCode) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(catalogEnum, groupCode.catalogEnum)
                .append(codeGroup, groupCode.codeGroup)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(catalogEnum)
                .append(codeGroup)
                .append(shortText)
                .append(userAdmData)
                .append(isUsed)
                .append(isInactive)
                .append(status)
                .append(createdBy)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "GroupCode{" +
                "catalogEnum=" + catalogEnum.getCatalog() +
                ", codeGroup='" + codeGroup + '\'' +
                ", shortText='" + shortText + '\'' +
                ", userAdmData=" + userAdmData +
                ", isUsed=" + isUsed +
                ", isInactive=" + isInactive +
                ", status=" + status +
                ", createdBy=" + createdBy +
                '}';
    }

    public Date getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Date createdBy) {
        this.createdBy = createdBy;
    }

    private Date createdBy;

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Boolean getInactive() {
        return isInactive;
    }

    public void setInactive(Boolean inactive) {
        isInactive = inactive;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CatalogEnum getCatalogEnum() {
        return catalogEnum;
    }

    public void setCatalogEnum(CatalogEnum catalogEnum) {
        this.catalogEnum = catalogEnum;
    }

    public UserAdmData getUserAdmData() {
        return userAdmData;
    }

    public void setUserAdmData(UserAdmData userAdmData) {
        this.userAdmData = userAdmData;
    }
}
