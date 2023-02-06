package com.gospace.assesment.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    static InputStream inputStream;
    static String value = "";
    public static String getProperty(String key) {
        try {
            Properties prop = new Properties();
            String propFileName = "env.conf";
            inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            value = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }
}
