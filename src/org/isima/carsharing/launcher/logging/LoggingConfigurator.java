package org.isima.carsharing.launcher.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface LoggingConfigurator {
	
	public void setup(Logger logger,Level lvl);

}
