package br.com.gesc.services.sap;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class SapConnect {
    protected JCoDestination getDestination() {
        // This will create a file called mySAPSystem.jcoDestination
        String DESTINATION_NAME1 = "ComgasDev";

        /**
         * "C:\Program Files (x86)\SAP\FrontEnd\SAPgui\SAPgui.exe" /H/177.67.63.18/S/3299/W/emergencial/M/172.31.0.173/S/3602/G/SPACE
         */

        /**
         * SAP ECC - DEV

        Properties conn = new Properties();
        conn.setProperty(DestinationDataProvider.JCO_ASHOST, "comgas364"); //host
        conn.setProperty(DestinationDataProvider.JCO_SAPROUTER, "/H/177.67.63.18/S/3299/W/emergencial");
        conn.setProperty("jco.server.saprouter", "/M/172.31.0.173/S/3602/G/SPACE");
        conn.setProperty(DestinationDataProvider.JCO_SYSNR, "02"); //system number
        conn.setProperty(DestinationDataProvider.JCO_CLIENT, "100"); //client number
        conn.setProperty(DestinationDataProvider.JCO_USER, "TR010375");
        conn.setProperty(DestinationDataProvider.JCO_PASSWD, "ecnv@85@");
        conn.setProperty(DestinationDataProvider.JCO_LANG, "pt");
         */


         //SAP CCQ
         Properties conn = new Properties();
         conn.setProperty(DestinationDataProvider.JCO_ASHOST, "comgas230.comgas.local"); //host
//         conn.setProperty(DestinationDataProvider.JCO_SAPROUTER, "/H/177.67.63.18/S/3299/W/emergencial");
//         conn.setProperty("jco.server.saprouter", "/M/172.31.0.173/S/3602/G/SPACE");
         conn.setProperty(DestinationDataProvider.JCO_SYSNR, "14"); //system number
         conn.setProperty(DestinationDataProvider.JCO_CLIENT, "200"); //client number
         conn.setProperty(DestinationDataProvider.JCO_USER, "TR010375");
         conn.setProperty(DestinationDataProvider.JCO_PASSWD, "ecnv@85@");
         conn.setProperty(DestinationDataProvider.JCO_LANG, "pt");


        createDataFile(DESTINATION_NAME1, "jcoDestination", conn);
        // This will use that destination file to connect to SAP
        JCoDestination destination = null;
        try {
            destination = JCoDestinationManager.getDestination(DESTINATION_NAME1);

            System.out.println("Attributes:");
            System.out.println(destination.getAttributes());
            System.out.println();
            destination.ping();

        } catch (JCoException e) {
            e.printStackTrace();
        }
        return destination;
    }

    /**
     * Create data file
     *
     * @param name
     * @param suffix
     * @param properties
     */
    static void createDataFile(String name, String suffix, Properties properties) {
        File cfg = new File(name + "." + suffix);
        if (cfg.exists()) {
            cfg.delete();
        }

        try {
            FileOutputStream fos = new FileOutputStream(cfg, false);
            properties.store(fos, "for tests only !");
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
        }

    }
}

