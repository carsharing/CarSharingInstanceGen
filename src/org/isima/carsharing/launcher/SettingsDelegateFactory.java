/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.launcher;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hicham
 */
public class SettingsDelegateFactory {

    public SettingsDelegate findSettingsdDelegate(String[] args) throws Exception {
        CommandLineSettingsDelegate settingsdDelegate = new CommandLineSettingsDelegate();
        for (String s : args) {
            if (s.contains(":")) {
                if (s.substring(0,s.indexOf(":")+1).equals("dC:")) {
                    int dC = Integer.parseInt(s.substring(s.indexOf(":")+1, s.length()));
                    settingsdDelegate.setDefaultCapacity(dC);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dD:")) {
                    int dD = Integer.parseInt(s.substring(s.indexOf(":")+1, s.length()));
                    settingsdDelegate.setDefaultDriverAvailable(dD);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dP:")) {
                    int dP = Integer.parseInt(s.substring(s.indexOf(":")+1, s.length()));
                    settingsdDelegate.setDefaultParkedCar(dP);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dT:")) {
                    String dT = s.substring(s.indexOf(":")+1, s.length());
                    settingsdDelegate.setDefaultTimeStamp(dT);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dNm:")) {
                    String dNm = s.substring(s.indexOf(":")+1, s.length());
                    settingsdDelegate.setDefaultName(dNm);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dNt:")) {
                    String dNt = s.substring(s.indexOf(":")+1, s.length());
                    settingsdDelegate.setDefaultNetwork(dNt);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dV:")) {
                    String dV = s.substring(s.indexOf(":")+1, s.length());
                    settingsdDelegate.setDefaultVersion(dV);
                } else if (s.substring(0,s.indexOf(":")+1).equals("Ldout:")) {
                    String LdoutPath = s.substring(s.indexOf(":")+1, s.length());
                    File Ldout = new File(LdoutPath);
                    if (Ldout.exists()) {
                        if (Ldout.isDirectory()) {
                            settingsdDelegate.setLogDirectory(Ldout);
                        } else {
                            //Error
                        }
                    } else {
                        boolean mkdirStatus = Ldout.mkdirs();
                        if (mkdirStatus == false) {
                            //error
                        } else {
                            settingsdDelegate.setLogDirectory(Ldout);
                        }
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("Llvl:")) {
                    String Llvl = s.substring(s.indexOf(":")+1, s.length());
                    switch (Llvl) {
                        case "a":
                            settingsdDelegate.setLogLevel(Level.ALL);
                            break;
                        case "i":
                            settingsdDelegate.setLogLevel(Level.INFO);
                            break;
                        case "w":
                            settingsdDelegate.setLogLevel(Level.WARNING);
                            break;
                        case "s":
                            settingsdDelegate.setLogLevel(Level.SEVERE);
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("in:")) {
                    String inPath = s.substring(s.indexOf(":")+1, s.length());
                    File in = new File(inPath);
                    if (in.exists()) {
                        if (in.isFile()) {
                            settingsdDelegate.setInputFile(in);
                        } else {                                         
                            //Error
                        }
                    } else {
                        //Error
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("out:")) {
                    String outPath = s.substring(s.indexOf(":")+1, s.length());
                    File out = new File(outPath);
                    if (out.exists()) {
                        if (out.isDirectory()) {
                            settingsdDelegate.setOutputDirectory(out);
                        } else {
                            //Error
                        }
                    } else {
                        boolean mkdirStatus = out.mkdirs();
                        if (mkdirStatus == false) {
                            //error
                        } else {
                            settingsdDelegate.setOutputDirectory(out);
                        }
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("conf:")) {
                    String confPath = s.substring(s.indexOf(":")+1, s.length());
                    File conf = new File(confPath);
                    if (conf.exists()) {
                        if (conf.isFile()) {
                            settingsdDelegate.setConfigFile(conf);
                        } else {                                         
                            //Error
                        }
                    } 
                } else if (s.substring(0,s.indexOf(":")+1).equals("serv:")) {
                    String serv = s.substring(s.indexOf(":")+1, s.length());
                    settingsdDelegate.setOtpServerURL(serv);
                } else if (s.substring(0,s.indexOf(":")+1).equals("oConf:")) {
                    String oConf = s.substring(s.indexOf(":")+1, s.length());
                    switch (oConf) {
                        case "y":
                            settingsdDelegate.setOverrideConfigFile(Boolean.TRUE);
                            break;
                        case "n":
                            settingsdDelegate.setOverrideConfigFile(Boolean.FALSE);
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("margin:")) {
                    Float margin = Float.parseFloat(s.substring(s.indexOf(":")+1, s.length()));
                    settingsdDelegate.setDistanceMargin(margin);
                } else if (s.substring(0,s.indexOf(":")+1).equals("defValues:")) {
                    String defValues = s.substring(s.indexOf(":")+1, s.length());
                    switch (defValues) {
                        case "y":
                            settingsdDelegate.setActivateDefaultValues(Boolean.TRUE);
                            break;
                        case "n":
                            settingsdDelegate.setActivateDefaultValues(Boolean.FALSE);
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("completeGraph:")) {
                    String completeGraph = s.substring(s.indexOf(":")+1, s.length());
                    switch (completeGraph) {
                        case "y":
                            settingsdDelegate.setGenerateCompleteGraph(Boolean.TRUE);
                            break;
                        case "n":
                            settingsdDelegate.setGenerateCompleteGraph(Boolean.FALSE);
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("incompleteGraph:")) {
                    String incompleteGraph = s.substring(s.indexOf(":")+1, s.length());
                    switch (incompleteGraph) {
                        case "y":
                            settingsdDelegate.setGenerateIncompleteGraph(Boolean.TRUE);
                            break;
                        case "n":
                            settingsdDelegate.setGenerateIncompleteGraph(Boolean.FALSE);
                            break;
                        default:
                            //error
                            break;
                    }
                }
            } else {
                //error
            }
        }

        updateSettingsConfigDelegateAndOverWriteConfigFile(settingsdDelegate);        
        return settingsdDelegate;
    }
    
    private void updateSettingsConfigDelegateAndOverWriteConfigFile(CommandLineSettingsDelegate settingsdDelegate){
        ConfigFileHandler configFileHandler = new ConfigFileHandler();
         if(settingsdDelegate.getConfigFile() != null){
            try {
                File configFile = settingsdDelegate.getConfigFile();
                Map<String, Object> readMap = configFileHandler.read(configFile);
                settingsdDelegate.updateFromConfigFile(readMap);
            } catch (IOException ex) {
                Logger.getLogger(SettingsDelegateFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         
         settingsdDelegate.updateFromSimpleConfig();
         
         if(settingsdDelegate.isOverrideConfigFile()){
             configFileHandler.overwrite(settingsdDelegate);
         }
    }
}
