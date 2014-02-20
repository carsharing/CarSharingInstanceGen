/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.launcher;

import java.io.File;
import java.util.logging.Level;

/**
 *
 * @author Hicham
 */
public interface SettingsDelegate {

    public Integer getDefaultCapacity();

    public Integer getDefaultDriverAvailable();

    public Integer getDefaultParkedCar();

    public String getDefaultTimeStamp();

    public String getDefaultName();

    public String getDefaultNetwork();

    public String getDefaultVersion();

    public File getLogDirectory();

    public Level getLogLevel();

    public File getInputFile();

    public File getOutputDirectory();

    public File getConfigFile();

    public String getOtpServerURL();

    public Boolean isOverrideConfigFile();

    public Float getDistanceMargin();

    public Boolean isActivateDefaultValues();

    public Boolean isGenerateCompleteGraph();

    public Boolean isGenerateIncompleteGraph();
    
    public String usedConfigsToXMLComment();
}
