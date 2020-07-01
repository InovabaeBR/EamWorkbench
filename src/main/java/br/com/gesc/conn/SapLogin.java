package br.com.gesc.conn;

import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoDestinationManager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class SapLogin {
    public static void main(String[] args) {
        // This will create a file called mySAPSystem.jcoDestination
        String DESTINATION_NAME1 = "mySAPSystem";
        Properties conn = new Properties();
        conn.setProperty(DestinationDataProvider.JCO_ASHOST, "10.129.19.151"); //host
        conn.setProperty(DestinationDataProvider.JCO_SYSNR,  "00"); //system number
        conn.setProperty(DestinationDataProvider.JCO_CLIENT, "442"); //client number
        conn.setProperty(DestinationDataProvider.JCO_USER,   "MPOSRFC");
        conn.setProperty(DestinationDataProvider.JCO_PASSWD, "123456");
        conn.setProperty(DestinationDataProvider.JCO_LANG,   "pt");

        createDataFile(DESTINATION_NAME1, "jcoDestination", conn);
        // This will use that destination file to connect to SAP
        try {
            JCoDestination destination = JCoDestinationManager.getDestination("mySAPSystem");

            System.out.println("Attributes:");
            System.out.println(destination.getAttributes());
            System.out.println();
            destination.ping();

        } catch (JCoException e){
            e.printStackTrace();
        }
    }

    /**
     * Create data file
     * @param name
     * @param suffix
     * @param properties
     */
    static void createDataFile(String name, String suffix, Properties properties)
    {
        File cfg = new File(name+"."+suffix);
        if(!cfg.exists())
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(cfg, false);
                properties.store(fos, "for tests only !");
                fos.close();
            }
            catch (Exception e)
            {
                throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
            }
        }
    }
    }

