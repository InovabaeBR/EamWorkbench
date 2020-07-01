package br.com.gesc.model;

public class Notification {
    private String notifType;
    private String name;

    public Notification(String notifType, String name) {
        this.notifType = notifType;
        this.name = name;
    }

    public String getNotifType() {
        return notifType;
    }

    public void setNotifType(String notifType) {
        this.notifType = notifType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogProfile() {
        return catalogProfile;
    }

    public void setCatalogProfile(String catalogProfile) {
        this.catalogProfile = catalogProfile;
    }

    private String catalogProfile;

}
