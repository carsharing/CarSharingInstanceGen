/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.launcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hicham
 */
public class SettingsDelegateFactory {
    private static final Logger logger = Logger.getLogger("CarSharingInstanceGen");

    public SettingsDelegate findSettingsdDelegate(String[] args) throws Exception {
        CommandLineSettingsDelegate settingsdDelegate = new CommandLineSettingsDelegate();
        for (String s : args) {
            if (s.contains(":")) {
                if (s.substring(0,s.indexOf(":")+1).equals("dC:")) {
                    int dC = Integer.parseInt(s.substring(s.indexOf(":")+1, s.length()));
                    logger.log(Level.FINE,"[{0}"+"]"+" Found default capacity : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),dC});
                    settingsdDelegate.setDefaultCapacity(dC);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dD:")) {
                    int dD = Integer.parseInt(s.substring(s.indexOf(":")+1, s.length()));
                    logger.log(Level.FINE,"[{0}"+"]"+" Found default driver available : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),dD});
                    settingsdDelegate.setDefaultDriverAvailable(dD);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dP:")) {
                    int dP = Integer.parseInt(s.substring(s.indexOf(":")+1, s.length()));
                    logger.log(Level.FINE,"[{0}"+"]"+" Found default parked car : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),dP});
                    settingsdDelegate.setDefaultParkedCar(dP);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dT:")) {
                    String dT = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found default node time stamp : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),dT});
                    settingsdDelegate.setDefaultTimeStamp(dT);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dNm:")) {
                    String dNm = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found default node name : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),dNm});
                    settingsdDelegate.setDefaultName(dNm);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dNt:")) {
                    String dNt = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found default node network : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),dNt});
                    settingsdDelegate.setDefaultNetwork(dNt);
                } else if (s.substring(0,s.indexOf(":")+1).equals("dV:")) {
                    String dV = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found default node version : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),dV});
                    settingsdDelegate.setDefaultVersion(dV);
                } else if (s.substring(0,s.indexOf(":")+1).equals("Ldout:")) {
                    String LdoutPath = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found log dir : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),LdoutPath});
                    File Ldout = new File(LdoutPath);
                    if (Files.exists(Paths.get(Ldout.toURI()))) {
                        if (Ldout.isDirectory()) {
                            logger.log(Level.INFO,"[{0}"+"]"+" Log dir set to : \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),LdoutPath});
                            settingsdDelegate.setLogDirectory(Ldout);
                        } else {
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Specified log dir is an already existing file", SettingsDelegateFactory.class.getName());
                        }
                    } else {
                        logger.log(Level.SEVERE,"[{0}"+"]"+" Error while opening log dir \"{1}\"", new Object[]{SettingsDelegateFactory.class.getName(),LdoutPath});
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("Llvl:")) {
                    String Llvl = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found log level : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),Llvl});
                    switch (Llvl) {
                        case "a":
                            logger.log(Level.INFO,"[{0}"+"]"+" Log level set to: \"ALL\" ", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setLogLevel(Level.ALL);
                            break;
                        case "i":
                            logger.log(Level.INFO,"[{0}"+"]"+" Log level set to: \"INFO\"  ", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setLogLevel(Level.INFO);
                            break;
                        case "w":
                            logger.log(Level.INFO,"[{0}"+"]"+" Log level set to: \"WARN\"  ", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setLogLevel(Level.WARNING);
                            break;
                        case "s":
                            logger.log(Level.INFO,"[{0}"+"]"+" Log level set to: \"SEVER\"  ", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setLogLevel(Level.SEVERE);
                            break;
                        default:
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Specified log level unknown \"{1}\"", new Object[]{SettingsDelegateFactory.class.getName(),Llvl});
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("in:")) {
                    String inPath = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found input file path : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),inPath});
                    File in = new File(inPath);
                    if (Files.exists(Paths.get(in.toURI()))) {
                        if (in.isFile()) {
                            logger.log(Level.INFO,"[{0}"+"]"+" Input file set to : \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),inPath});
                            settingsdDelegate.setInputFile(in);
                        } else {                    
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Specified input file is a directory", SettingsDelegateFactory.class.getName());
                        }
                    } else {
                        logger.log(Level.SEVERE,"[{0}"+"]"+" Error while opening input file \"{1}\" The file does not exist", new Object[]{SettingsDelegateFactory.class.getName(),inPath});
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("out:")) {
                    String outPath = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found output dir : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),outPath});
                    File out = new File(outPath);
                    if (Files.exists(Paths.get(out.toURI()))) {
                        if (out.isDirectory()) {
                            logger.log(Level.INFO,"[{0}"+"]"+" Putput dir set to : \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),outPath});
                            settingsdDelegate.setOutputDirectory(out);
                        } else {
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Specified output dir is an already existing file", SettingsDelegateFactory.class.getName());
                        }
                    } else {
                        logger.log(Level.SEVERE,"[{0}"+"]"+" Error while opening output dir \"{1}\"", new Object[]{SettingsDelegateFactory.class.getName(),outPath});
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("conf:")) {
                    String confPath = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found config file path : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),confPath});
                    File conf = new File(confPath);
                    if (Files.exists(Paths.get(conf.toURI()))) {
                        if (conf.isFile()) {
                            logger.log(Level.INFO,"[{0}"+"]"+" Config file set to : \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),confPath});
                            settingsdDelegate.setConfigFile(conf);
                        } else {                                         
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Specified config file is a directory", SettingsDelegateFactory.class.getName());
                        }
                    }else{
                        logger.log(Level.SEVERE,"[{0}"+"]"+" Error while opening config file \"{1}\" The file does not exist", new Object[]{SettingsDelegateFactory.class.getName(),confPath});
                    } 
                } else if (s.substring(0,s.indexOf(":")+1).equals("serv:")) {
                    String serv = s.substring(s.indexOf(":")+1, s.length());
                    logger.log(Level.FINE,"[{0}"+"]"+" Found OTP server URL : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),serv});
                    settingsdDelegate.setOtpServerURL(serv);
                } else if (s.substring(0,s.indexOf(":")+1).equals("oConf:")) {
                    String oConf = s.substring(s.indexOf(":")+1, s.length());
                    switch (oConf) {
                        case "y":
                            logger.log(Level.WARNING,"[{0}"+"]"+" Settings are being overwriten", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setOverrideConfigFile(Boolean.TRUE);
                            break;
                        case "n":
                            logger.log(Level.WARNING,"[{0}"+"]"+" Settings are not being overwriten", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setOverrideConfigFile(Boolean.FALSE);
                            break;
                        default:
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Setting error : Expected \'y\' or \'n\' found \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),oConf});
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("margin:")) {
                    Float margin = Float.parseFloat(s.substring(s.indexOf(":")+1, s.length()));
                    logger.log(Level.FINE,"[{0}"+"]"+" Found distance margin : \"{1}\" value in command line", new Object[]{SettingsDelegateFactory.class.getName(),margin});
                    settingsdDelegate.setDistanceMargin(margin);
                } else if (s.substring(0,s.indexOf(":")+1).equals("defValues:")) {
                    String defValues = s.substring(s.indexOf(":")+1, s.length());
                    switch (defValues) {
                        case "y":
                            logger.log(Level.WARNING,"[{0}"+"]"+" Default values are being used for incomplete nodes", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setActivateDefaultValues(Boolean.TRUE);
                            break;
                        case "n":
                            logger.log(Level.WARNING,"[{0}"+"]"+" Default values are NOT being used for incomplete nodes", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setActivateDefaultValues(Boolean.FALSE);
                            break;
                        default:
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Setting error : Expected \'y\' or \'n\' found \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),defValues});
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("completeGraph:")) {
                    String completeGraph = s.substring(s.indexOf(":")+1, s.length());
                    switch (completeGraph) {
                        case "y":
                            logger.log(Level.WARNING,"[{0}"+"]"+" Complete graph is being generated", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setGenerateCompleteGraph(Boolean.TRUE);
                            break;
                        case "n":
                            logger.log(Level.WARNING,"[{0}"+"]"+" Complete graph is NOT being generated", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setGenerateCompleteGraph(Boolean.FALSE);
                            break;
                        default:
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Setting error : Expected \'y\' or \'n\' found \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),completeGraph});
                            break;
                    }
                } else if (s.substring(0,s.indexOf(":")+1).equals("incompleteGraph:")) {
                    String incompleteGraph = s.substring(s.indexOf(":")+1, s.length());
                    switch (incompleteGraph) {
                        case "y":
                            logger.log(Level.WARNING,"[{0}"+"]"+"  Inomplete graph is being generated", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setGenerateIncompleteGraph(Boolean.TRUE);
                            break;
                        case "n":
                            logger.log(Level.WARNING,"[{0}"+"]"+" Inomplete graph is NOT being generated", SettingsDelegateFactory.class.getName());
                            settingsdDelegate.setGenerateIncompleteGraph(Boolean.FALSE);
                            break;
                        default:
                            logger.log(Level.SEVERE,"[{0}"+"]"+" Setting error : Expected \'y\' or \'n\' found \"{1}\" ", new Object[]{SettingsDelegateFactory.class.getName(),incompleteGraph});
                            break;
                    }
                }
            } else {
                logger.log(Level.SEVERE,"[{0}"+"]"+" Setting error : Expected command line setting according to this regular expression : (([A-Za-z0-9]*):([A-Za-z0-9]*) )+ ", SettingsDelegateFactory.class.getName());
            }
        }
        logger.log(Level.INFO,"[{0}"+"]"+" Done reading command line settings, now getting update from setting file and default settings", SettingsDelegateFactory.class.getName());
        updateSettingsConfigDelegateAndOverWriteConfigFile(settingsdDelegate);        
        return settingsdDelegate;
    }
    
    private void updateSettingsConfigDelegateAndOverWriteConfigFile(CommandLineSettingsDelegate settingsdDelegate){
        ConfigFileHandler configFileHandler = new ConfigFileHandler();
         if(settingsdDelegate.getConfigFile() != null){
            try {
                File configFile = settingsdDelegate.getConfigFile();
                logger.log(Level.INFO,"[{0}"+"]"+" Reading configuration file {1}", new Object[]{SettingsDelegateFactory.class.getName(),settingsdDelegate.getConfigFile().getAbsoluteFile()});
                Map<String, Object> readMap = configFileHandler.read(configFile);
                if(readMap != null){
                    settingsdDelegate.updateFromConfigFile(readMap);                    
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE,"[{0}"+"]"+" Failed reading configuration file {1}", new Object[]{SettingsDelegateFactory.class.getName(),settingsdDelegate.getConfigFile().getAbsoluteFile()});
            }
         }
         
         logger.log(Level.INFO,"[{0}"+"]"+" Gettings default values for missing settings", SettingsDelegateFactory.class.getName());
         settingsdDelegate.updateFromSimpleConfig();
         
         if(settingsdDelegate.isOverrideConfigFile()){
             logger.log(Level.WARNING,"[{0}"+"]"+" Overwriting the configuration file {1}", new Object[]{SettingsDelegateFactory.class.getName(),settingsdDelegate.getConfigFile()});
             configFileHandler.overwrite(settingsdDelegate);
         }
         
         logger.log(Level.INFO,"[{0}"+"]"+" Used config: "+settingsdDelegate.toString(), SettingsDelegateFactory.class.getName());
    }
}
