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
public class SettingsdDelegate {

    //Nodes default values
    private Integer defaultCapacity;// dC ok
    private Integer defaultDriverAvailable;// dD ok
    private Integer defaultParkedCar;// dP ok
    private String defaultTimeStamp;// dT ok
    private String defaultName;// dNm ok
    private String defaultNetwork;// dNt ok
    private String defaultVersion;// dV ok

    //Launch config
    private File logDirectory; // Ldout
    private Level logLevel; // Llvl (a:i:w:s)
    private File inputFile; // in
    private File outputDirectory; // out
    private File configFile; // conf 
    private String otpServerURL; // serv
    private Boolean overrideConfigFile; // oConf (y:n)

    //Graph config
    private Float distanceMargin;  // margin
    private Boolean activateDefaultValues; // defValues (y:n)
    private Boolean generateCompleteGraph; // completeGraph (y:n)
    private Boolean generateIncompleteGraph; // incompleteGraph (y:n)

    public Integer getDefaultCapacity() {
        return defaultCapacity;
    }

    public void setDefaultCapacity(Integer defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }

    public Integer getDefaultDriverAvailable() {
        return defaultDriverAvailable;
    }

    public void setDefaultDriverAvailable(Integer defaultDriverAvailable) {
        this.defaultDriverAvailable = defaultDriverAvailable;
    }

    public Integer getDefaultParkedCar() {
        return defaultParkedCar;
    }

    public void setDefaultParkedCar(Integer defaultParkedCar) {
        this.defaultParkedCar = defaultParkedCar;
    }

    public String getDefaultTimeStamp() {
        return defaultTimeStamp;
    }

    public void setDefaultTimeStamp(String defaultTimeStamp) {
        this.defaultTimeStamp = defaultTimeStamp;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getDefaultNetwork() {
        return defaultNetwork;
    }

    public void setDefaultNetwork(String defaultNetwork) {
        this.defaultNetwork = defaultNetwork;
    }

    public String getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    public File getLogDirectory() {
        return logDirectory;
    }

    public void setLogDirectory(File logDirectory) {
        this.logDirectory = logDirectory;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public String getOtpServerURL() {
        return otpServerURL;
    }

    public void setOtpServerURL(String otpServerURL) {
        this.otpServerURL = otpServerURL;
    }

    public Boolean isOverrideConfigFile() {
        return overrideConfigFile;
    }

    public void setOverrideConfigFile(Boolean overrideConfigFile) {
        this.overrideConfigFile = overrideConfigFile;
    }

    public Float getDistanceMargin() {
        return distanceMargin;
    }

    public void setDistanceMargin(Float distanceMargin) {
        this.distanceMargin = distanceMargin;
    }

    public Boolean isActivateDefaultValues() {
        return activateDefaultValues;
    }

    public void setActivateDefaultValues(Boolean activateDefaultValues) {
        this.activateDefaultValues = activateDefaultValues;
    }

    public Boolean isGenerateCompleteGraph() {
        return generateCompleteGraph;
    }

    public void setGenerateCompleteGraph(Boolean generateCompleteGraph) {
        this.generateCompleteGraph = generateCompleteGraph;
    }

    public Boolean isGenerateIncompleteGraph() {
        return generateIncompleteGraph;
    }

    public void setGenerateIncompleteGraph(Boolean generateIncompleteGraph) {
        this.generateIncompleteGraph = generateIncompleteGraph;
    }


}
