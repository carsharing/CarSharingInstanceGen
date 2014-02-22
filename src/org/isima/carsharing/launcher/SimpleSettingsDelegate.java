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
public class SimpleSettingsDelegate implements SettingsDelegate {

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

    @Override
    public File getInputFile() {
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
    public File getConfigFile() {
        try {
            File in = new File("conf.properties");
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
        return false;
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

    @Override
    public String usedConfigsToXMLComment() {
        String result = "Used config :\n";

        result += "\tNodes default values\n";
        result += "\t\tdefaultParkedCar = " + this.getDefaultParkedCar() + "\n";
        result += "\t\tdefaultCapacity = " + this.getDefaultCapacity() + "\n";
        result += "\t\tdefaultNetwork = " + this.getDefaultNetwork() + "\n";
        result += "\t\tdefaultTimeStamp = " + this.getDefaultTimeStamp() + "\n";
        result += "\t\tdefaultName = " + this.getDefaultName() + "\n";
        result += "\t\tdefaultDriverAvailable = " + this.getDefaultDriverAvailable() + "\n";
        result += "\t\tdefaultVersion = " + this.getDefaultVersion() + "\n";

        result += "\tLaunch config\n";
        result += "\t\tlogDirectory = " + this.getLogDirectory().getPath() + "\n";
        result += "\t\tlogLevel = " + this.getLogLevel().toString() + "\n";
        result += "\t\tinputFile = " + this.getInputFile().getAbsolutePath() + "\n";
        result += "\t\toutputDirectory = " + this.getOutputDirectory().getPath() + "\n";
        result += "\t\tconfigFile = " + this.getConfigFile().getAbsolutePath() + "\n";
        result += "\t\totpServerURL = " + this.getOtpServerURL() + "\n";
        result += "\t\toverrideConfigFile = " + this.isOverrideConfigFile() + "\n";

        result += "\tGraph config\n";
        result += "\t\tdistanceMargin = " + this.getDistanceMargin() + "\n";
        result += "\t\tactivateDefaultValues = " + this.isActivateDefaultValues() + "\n";
        result += "\t\tgenerateCompleteGraph = " + this.isGenerateCompleteGraph() + "\n";
        result += "\t\tgenerateIncompleteGraph = " + this.isGenerateIncompleteGraph() + "\n";

        return result;
    }

    @Override
    public String toString() {
        return "SimpleSettingsDelegate{" + "defaultCapacity=" + this.getDefaultCapacity() + ", defaultDriverAvailable=" + this.getDefaultDriverAvailable() + ", defaultParkedCar=" + this.getDefaultParkedCar() + ", defaultTimeStamp=" + this.getDefaultTimeStamp() + ", defaultName=" + this.getDefaultName() + ", defaultNetwork=" + this.getDefaultNetwork() + ", defaultVersion=" + this.getDefaultVersion() + ", logDirectory=" + this.getLogDirectory() + ", logLevel=" + this.getLogLevel() + ", inputFile=" + this.getInputFile() + ", outputDirectory=" + this.getOutputDirectory() + ", configFile=" + this.getConfigFile() + ", otpServerURL=" + this.getOtpServerURL() + ", overrideConfigFile=" + this.isOverrideConfigFile() + ", distanceMargin=" + this.getDistanceMargin() + ", activateDefaultValues=" + this.isActivateDefaultValues() + ", generateCompleteGraph=" + this.isGenerateCompleteGraph() + ", generateIncompleteGraph=" + this.isGenerateIncompleteGraph() + '}';
    }

}
