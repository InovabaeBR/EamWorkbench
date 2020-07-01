package br.com.gesc.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * @since 30/06/2020
 */
public class CatalogCode {
    private GroupCode groupCode;
    private String code;
    private String version;
    private String shortText;
    private Boolean hasLongText;
    private Date validSince;
    private UserAdmData userAdmData;
    private Boolean isInactive;
    private Boolean isUsed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CatalogCode that = (CatalogCode) o;

        return new EqualsBuilder()
                .append(groupCode, that.groupCode)
                .append(code, that.code)
                .append(version, that.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(groupCode)
                .append(code)
                .append(version)
                .toHashCode();
    }

    public GroupCode getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(GroupCode groupCode) {
        this.groupCode = groupCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public Boolean getHasLongText() {
        return hasLongText;
    }

    public void setHasLongText(Boolean hasLongText) {
        this.hasLongText = hasLongText;
    }

    public Date getValidSince() {
        return validSince;
    }

    public void setValidSince(Date validSince) {
        this.validSince = validSince;
    }

    public UserAdmData getUserAdmData() {
        return userAdmData;
    }

    public void setUserAdmData(UserAdmData userAdmData) {
        this.userAdmData = userAdmData;
    }

    public Boolean getInactive() {
        return isInactive;
    }

    public void setInactive(Boolean inactive) {
        isInactive = inactive;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}
