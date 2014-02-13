package org.isima.carsharing.launcher.logging;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingConfiguratorFactory {
	
	private Level level;
	private Logger logger;
        private File rootDirectory;
	
	public LoggingConfiguratorFactory(Logger log,Level lvl,File rootDirectory){			
	    this.logger = log;
	    log.setLevel(lvl);
	    this.logger.setLevel(lvl);
            this.level = lvl;
            this.rootDirectory = rootDirectory;
                
            deleteRecusiveDirectory(this.rootDirectory );

            File level11 = new File(this.rootDirectory.getAbsolutePath()+File.separator+"xml");
            if(!level11.exists()){
                    level11.mkdirs();
            }

            File level10 = new File(this.rootDirectory .getAbsolutePath()+File.separator+"txt");
            if(!level10.exists()){
                    level10.mkdirs();
            }
		
	}
	
	public XMLLoggingConfig applyXMLLoggingConfig(){
		XMLLoggingConfig xmlLoggingConfig = new XMLLoggingConfig();
		xmlLoggingConfig.setup(logger,level,this.rootDirectory);
		return xmlLoggingConfig;
	}
	
	public SimpleLoggingConfig applySimpleLoggingConfig(){
		SimpleLoggingConfig simpleLoggingConfig = new SimpleLoggingConfig();
		simpleLoggingConfig.setup(logger,level,this.rootDirectory);
		return simpleLoggingConfig;
	}
	
	private void deleteRecusiveDirectory(File directory){
		if(directory.isDirectory()){
			if(directory.listFiles().length != 0){
				for(File file : directory.listFiles()){
					if(file.isDirectory()){
						deleteRecusiveDirectory(file);
					}else{
						file.delete();
					}
				}
			}else{
				directory.delete();
			}			
		}
	}
}
