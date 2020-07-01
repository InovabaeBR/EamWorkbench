package br.com.gesc.model;

import java.util.List;

/**
 * View V_TQ80_C
 */
public class NotificationCatalog {
    private Notification notification;
    private List<CatalogProfile> catalogProfiles;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public List<CatalogProfile> getCatalogProfiles() {
        return catalogProfiles;
    }

    public void setCatalogProfiles(List<CatalogProfile> catalogProfiles) {
        this.catalogProfiles = catalogProfiles;
    }
}
