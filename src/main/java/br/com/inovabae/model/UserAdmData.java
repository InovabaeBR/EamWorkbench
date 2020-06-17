package br.com.inovabae.model;

import java.io.Serializable;
import java.util.Date;

public class UserAdmData implements Serializable {
    private Date createdOn;
    private String createdBy;
    private String changedBy;
    private Date changedOn;

    public UserAdmData(Date createdOn, String createdBy, String changedBy, Date changedOn) {
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.changedBy = createdBy;
        this.changedOn = changedOn;
    }

    //TODO Corrigir a formatação das datas
    @Override
    public String toString() {
        return "UserAdmData{" +
                "createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", changedBy='" + changedBy + '\'' +
                ", changedOn=" + changedOn +
                '}';
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }
}
