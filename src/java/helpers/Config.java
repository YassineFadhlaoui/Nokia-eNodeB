/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author yassine
 */
public class Config {

    private static final String CONFIG_FILE = "/etc/BTSManager.conf";

    @SuppressWarnings("UseOfObsoleteCollectionType")
    public static Vector<String> getProperties(Vector<String> parameters) {
        Vector<String> params = new Vector();
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(CONFIG_FILE);
            prop.load(input);

            for (String parameter : parameters) {
                params.add(prop.getProperty(parameter));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return params;
    }
}
