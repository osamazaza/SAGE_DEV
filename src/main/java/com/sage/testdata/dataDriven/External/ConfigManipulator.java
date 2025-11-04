package com.sage.testdata.dataDriven.External;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManipulator {
    private static Properties properties = new Properties();
    private static final String filePath = "C:\\Users\\user\\eclipse-workspace\\com.sdb\\resources\\SquadGlobalVariable.properties";

    static {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // READ a property
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // WRITE or UPDATE a property
    public static void setProperty(String key, String value) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.setProperty(key, value);
            properties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
