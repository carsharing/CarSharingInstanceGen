/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.launcher;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hicham
 */
public class SimpleSettingsDelegate implements SettingsDelegate{

    @Override
    public Integer getDefaultCapacity() {
        return 0;
    }

    @Override
    public Integer getDefaultDriverAvailable() {
        return 0;
    }

    @Override
    public Integer getDefaultParkedCar() {
        return 0;
    }

    @Override
    public String getDefaultTimeStamp() {
        return "n/a";
    }

    @Override
    public String getDefaultName() {
        return "unknown";
    }

    @Override
    public String getDefaultNetwork() {
        return "unknown";
    }

    @Override
    public String getDefaultVersion() {
        return "unknown";
    }

    @Override
    public File getLogDirectory() {
        File dir = new File("DefaultLogDir");
        dir.mkdirs();
        return dir;
    }

    @Override
    public Level getLogLevel() {
        return Level.ALL;
    }

    public File getInputFile(){
        try {
            File in = new File("input.xml");
            in.createNewFile();
            return in;
        } catch (IOException ex) {
            Logger.getLogger(SimpleSettingsDelegate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public File getOutputDirectory() {
        File dir = new File("DefaultOutputDir");
        dir.mkdirs();
        return dir;
    }

    @Override
    public File getConfigFile(){
        try {
            File in = new File("config.xml");
            in.createNewFile();
            return in;
        } catch (IOException ex) {
            Logger.getLogger(SimpleSettingsDelegate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getOtpServerURL() {
        return "localhost:8080";
    }

    @Override
    public Boolean isOverrideConfigFile() {
        return true;
    }

    @Override
    public Float getDistanceMargin() {
        return 0f;
    }

    @Override
    public Boolean isActivateDefaultValues() {
        return true;
    }

    @Override
    public Boolean isGenerateCompleteGraph() {
        return true;
    }

    @Override
    public Boolean isGenerateIncompleteGraph() {
        return true;
    }

}
