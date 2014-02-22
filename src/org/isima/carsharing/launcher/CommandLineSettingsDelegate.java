/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.launcher;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Hicham
 */
public class CommandLineSettingsDelegate  implements SettingsDelegate{

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

    @Override
    public Integer getDefaultCapacity() {
        return defaultCapacity;
    }

    public void setDefaultCapacity(Integer defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }

    @Override
    public Integer getDefaultDriverAvailable() {
        return defaultDriverAvailable;
    }

    public void setDefaultDriverAvailable(Integer defaultDriverAvailable) {
        this.defaultDriverAvailable = defaultDriverAvailable;
    }

    @Override
    public Integer getDefaultParkedCar() {
        return defaultParkedCar;
    }

    public void setDefaultParkedCar(Integer defaultParkedCar) {
        this.defaultParkedCar = defaultParkedCar;
    }

    @Override
    public String getDefaultTimeStamp() {
        return defaultTimeStamp;
    }

    public void setDefaultTimeStamp(String defaultTimeStamp) {
        this.defaultTimeStamp = defaultTimeStamp;
    }

    @Override
    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @Override
    public String getDefaultNetwork() {
        return defaultNetwork;
    }

    public void setDefaultNetwork(String defaultNetwork) {
        this.defaultNetwork = defaultNetwork;
    }

    @Override
    public String getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    @Override
    public File getLogDirectory() {
        return logDirectory;
    }

    public void setLogDirectory(File logDirectory) {
        this.logDirectory = logDirectory;
    }

    @Override
    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    @Override
    public String getOtpServerURL() {
        return otpServerURL;
    }

    public void setOtpServerURL(String otpServerURL) {
        this.otpServerURL = otpServerURL;
    }

    @Override
    public Boolean isOverrideConfigFile() {
        return overrideConfigFile;
    }

    public void setOverrideConfigFile(Boolean overrideConfigFile) {
        this.overrideConfigFile = overrideConfigFile;
    }

    @Override
    public Float getDistanceMargin() {
        return distanceMargin;
    }

    public void setDistanceMargin(Float distanceMargin) {
        this.distanceMargin = distanceMargin;
    }

    @Override
    public Boolean isActivateDefaultValues() {
        return activateDefaultValues;
    }

    public void setActivateDefaultValues(Boolean activateDefaultValues) {
        this.activateDefaultValues = activateDefaultValues;
    }

    @Override
    public Boolean isGenerateCompleteGraph() {
        return generateCompleteGraph;
    }

    public void setGenerateCompleteGraph(Boolean generateCompleteGraph) {
        this.generateCompleteGraph = generateCompleteGraph;
    }

    @Override
    public Boolean isGenerateIncompleteGraph() {
        return generateIncompleteGraph;
    }

    public void setGenerateIncompleteGraph(Boolean generateIncompleteGraph) {
        this.generateIncompleteGraph = generateIncompleteGraph;
    }
    
    public void updateFromConfigFile(Map<String,Object> properties){
        if(this.defaultCapacity==null){
            this.defaultCapacity = (Integer) properties.get("defaultCapacity");
        }
        
        if(this.defaultDriverAvailable==null){
            this.defaultDriverAvailable = (Integer) properties.get("defaultDriverAvailable");
        }
        
        if(this.defaultParkedCar ==null){
            this.defaultParkedCar = (Integer) properties.get("defaultParkedCar");
        }
        
        if(this.defaultTimeStamp ==null){
            this.defaultTimeStamp = (String) properties.get("defaultTimeStamp");
        }
        
        if(this.defaultName ==null){
            this.defaultName = (String) properties.get("defaultName");
        }
        
        if(this.defaultNetwork ==null){
            this.defaultNetwork = (String) properties.get("defaultNetwork");
        }
        
        if(this.defaultVersion ==null){
            this.defaultVersion = (String) properties.get("defaultVersion");
        }
        
        if(this.logDirectory ==null){
            this.logDirectory = (File) properties.get("logDirectory");
        }
        
        if(this.logLevel ==null){
            this.logLevel = (Level) properties.get("logLevel");
        }
        
        if(this.inputFile ==null){
            this.inputFile = (File) properties.get("inputFile");
        }
        
        if(this.outputDirectory ==null){
            this.outputDirectory = (File) properties.get("outputDirectory");
        }
        
        if(this.configFile ==null){
            this.configFile = (File) properties.get("configFile");
        }
        
        if(this.otpServerURL ==null){
            this.otpServerURL = (String) properties.get("otpServerURL");
        }
        
        if(this.overrideConfigFile ==null){
            this.overrideConfigFile = (Boolean) properties.get("overrideConfigFile");
        }
        
        if(this.distanceMargin ==null){
            this.distanceMargin = (Float) properties.get("distanceMargin");
        }
        
        if(this.activateDefaultValues ==null){
            this.activateDefaultValues = (Boolean) properties.get("activateDefaultValues");
        }
        
        if(this.generateCompleteGraph ==null){
            this.generateCompleteGraph = (Boolean) properties.get("generateCompleteGraph");
        }
        
        if(this.generateIncompleteGraph ==null){
            this.generateIncompleteGraph = (Boolean) properties.get("generateIncompleteGraph");
        }
    }
    
    public void updateFromSimpleConfig(){
        SimpleSettingsDelegate settingsDelegate = new SimpleSettingsDelegate();
        
        if(this.defaultCapacity ==null ){
            this.defaultCapacity = settingsDelegate.getDefaultCapacity();
        }
        
        if(this.defaultDriverAvailable ==null ){
            this.defaultDriverAvailable = settingsDelegate.getDefaultDriverAvailable();
        }
        
        if(this.defaultParkedCar  ==null ){
            this.defaultParkedCar = settingsDelegate.getDefaultParkedCar();
        }
        
        if(this.defaultTimeStamp  ==null ){
            this.defaultTimeStamp = settingsDelegate.getDefaultTimeStamp();
        }
        
        if(this.defaultName ==null ){
            this.defaultName = settingsDelegate.getDefaultName();
        }
        
        if(this.defaultNetwork ==null){
            this.defaultNetwork = settingsDelegate.getDefaultNetwork();
        }
        
        if(this.defaultVersion ==null){
            this.defaultVersion = settingsDelegate.getDefaultVersion();
        }
        
        if(this.logDirectory ==null){
            this.logDirectory = settingsDelegate.getLogDirectory();
        }
        
        if(this.logLevel ==null){
            this.logLevel = settingsDelegate.getLogLevel();
        }
        
        if(this.inputFile ==null){
            this.inputFile = settingsDelegate.getInputFile();
        }
        
        if(this.outputDirectory ==null){
            this.outputDirectory = settingsDelegate.getOutputDirectory();
        }
        
        if(this.configFile ==null){
            this.configFile = settingsDelegate.getConfigFile();
        }
        
        if(this.otpServerURL ==null){
            this.otpServerURL = settingsDelegate.getOtpServerURL();
        }
        
        if(this.overrideConfigFile ==null){
            this.overrideConfigFile = settingsDelegate.isOverrideConfigFile();
        }
        
        if(this.distanceMargin ==null){
            this.distanceMargin = settingsDelegate.getDistanceMargin();
        }
        
        if(this.activateDefaultValues ==null){
            this.activateDefaultValues = settingsDelegate.isActivateDefaultValues();
        }
        
        if(this.generateCompleteGraph ==null){
            this.generateCompleteGraph = settingsDelegate.isGenerateCompleteGraph();
        }
        
        if(this.generateIncompleteGraph ==null){
            this.generateIncompleteGraph = settingsDelegate.isGenerateIncompleteGraph();
        }
    }    

    @Override
    public String usedConfigsToXMLComment() {
        String result = "Used config :\n";
        
        result += "\tNodes default values\n";
        result += "\t\tdefaultParkedCar = "+defaultParkedCar+"\n";
        result += "\t\tdefaultCapacity = "+defaultCapacity+"\n";
        result += "\t\tdefaultNetwork = "+defaultNetwork+"\n";
        result += "\t\tdefaultTimeStamp = "+defaultTimeStamp+"\n";
        result += "\t\tdefaultName = "+defaultName+"\n";
        result += "\t\tdefaultDriverAvailable = "+defaultDriverAvailable+"\n";
        result += "\t\tdefaultVersion = "+defaultVersion+"\n";
        
        result += "\tLaunch config\n";
        result += "\t\tlogDirectory = "+logDirectory.getPath()+"\n";
        result += "\t\tlogLevel = "+logLevel.toString()+"\n";
        result += "\t\tinputFile = "+inputFile.getAbsolutePath()+"\n";
        result += "\t\toutputDirectory = "+outputDirectory.getParent()+"\n";
        result += "\t\tconfigFile = "+configFile.getAbsolutePath()+"\n";
        result += "\t\totpServerURL = "+otpServerURL+"\n";
        result += "\t\toverrideConfigFile = "+overrideConfigFile+"\n";
        
        result += "\tGraph config\n";
        result += "\t\tdistanceMargin = "+distanceMargin+"\n";
        result += "\t\tactivateDefaultValues = "+activateDefaultValues+"\n";
        result += "\t\tgenerateCompleteGraph = "+generateCompleteGraph+"\n";
        result += "\t\tgenerateIncompleteGraph = "+generateIncompleteGraph+"\n";
        
        return result;
    }

    @Override
    public String toString() {
        return "CommandLineSettingsDelegate{" + "defaultCapacity=" + defaultCapacity + ", defaultDriverAvailable=" + defaultDriverAvailable + ", defaultParkedCar=" + defaultParkedCar + ", defaultTimeStamp=" + defaultTimeStamp + ", defaultName=" + defaultName + ", defaultNetwork=" + defaultNetwork + ", defaultVersion=" + defaultVersion + ", logDirectory=" + logDirectory + ", logLevel=" + logLevel + ", inputFile=" + inputFile + ", outputDirectory=" + outputDirectory + ", configFile=" + configFile + ", otpServerURL=" + otpServerURL + ", overrideConfigFile=" + overrideConfigFile + ", distanceMargin=" + distanceMargin + ", activateDefaultValues=" + activateDefaultValues + ", generateCompleteGraph=" + generateCompleteGraph + ", generateIncompleteGraph=" + generateIncompleteGraph + '}';
    }
    
}
