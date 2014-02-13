/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hicham
 */
public class ConfigFileHandler {

    public Map<String, Object> read(File file) throws IOException {
        FileInputStream fileInputStream = null;
        Properties properties = new Properties();

        try {
            fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ConfigFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contructMap(properties);
    }

    public void overwrite(SettingsDelegate settingsDelegate) {

    }

    private Map<String, Object> contructMap(Properties source) {
        Map<String, Object> result = new HashMap<>();
        Integer defaultCapacity = Integer.parseInt(source.getProperty("defaultCapacity")); 
        Integer defaultDriverAvailable = Integer.parseInt(source.getProperty("defaultDriverAvailable")); 
        Integer defaultParkedCar = Integer.parseInt(source.getProperty("defaultParkedCar"));
        String defaultTimeStamp = source.getProperty("defaultTimeStamp");
        String defaultName = source.getProperty("defaultName");
        String defaultNetwork = source.getProperty("defaultNetwork");
        String defaultVersion = source.getProperty("defaultVersion");
        File logDirectory = getDirectoryFromString(source.getProperty("logDirectory"));
        Level logLevel = getLvlFromString(source.getProperty("logLevel"));
        File inputFile = getFileFromString(source.getProperty("inputFile"));
        File outputDirectory = getDirectoryFromString(source.getProperty("outputDirectory"));
        File configFile = getFileFromString(source.getProperty("configFile"));
        String otpServerURL = source.getProperty("otpServerURL");
        Boolean overrideConfigFile = Boolean.parseBoolean(source.getProperty("overrideConfigFile"));
        Float distanceMargin = Float.parseFloat(source.getProperty("distanceMargin"));
        Boolean activateDefaultValues = Boolean.parseBoolean(source.getProperty("activateDefaultValues"));
        Boolean generateCompleteGraph = Boolean.parseBoolean(source.getProperty("generateCompleteGraph"));
        Boolean generateIncompleteGraph = Boolean.parseBoolean(source.getProperty("generateIncompleteGraph"));
        
        result.put("defaultCapacity", defaultCapacity);
        result.put("defaultDriverAvailable", defaultDriverAvailable);
        result.put("defaultParkedCar", defaultParkedCar);
        result.put("defaultTimeStamp", defaultTimeStamp);
        result.put("defaultName", defaultName);
        result.put("defaultNetwork", defaultNetwork);
        result.put("defaultVersion", defaultVersion);
        result.put("logDirectory", logDirectory);
        result.put("logLevel", logLevel);
        result.put("inputFile", inputFile);
        result.put("outputDirectory", outputDirectory);
        result.put("configFile", configFile);
        result.put("otpServerURL", otpServerURL);
        result.put("overrideConfigFile", overrideConfigFile);
        result.put("distanceMargin", distanceMargin);
        result.put("activateDefaultValues", activateDefaultValues);
        result.put("generateCompleteGraph", generateCompleteGraph);
        result.put("generateIncompleteGraph", generateIncompleteGraph);
        return result;
    }

    private File getDirectoryFromString(String path) {
        File out = new File(path);
        if (out.exists()) {
            if (out.isDirectory()) {
                return out;
            } else {
                //TODO: Handle existing file with same name error
                return null;
            }
        } else {
            boolean mkdirStatus = out.mkdirs();
            if (mkdirStatus == false) {
                //TODO: Handle mkdir error
                return null;
            } else {
                return out;
            }
        }

    }

    private File getFileFromString(String path) {
        File out = new File(path);
        if (out.exists()) {
            if (out.isFile()) {
                return out;
            } else {
                //TODO: Handle existing directory with same name error
                return null;
            }
        } else {
            //TODO: Handle not exisiting file
            return null;
        }

    }

    private Level getLvlFromString(String Llvl) {
        switch (Llvl) {
            case "all":
                return Level.ALL;
            case "info":
                return Level.INFO;
            case "warning":
                return Level.WARNING;
            case "sever":
                return Level.SEVERE;
            default:
                return null;
        }
    }

}
