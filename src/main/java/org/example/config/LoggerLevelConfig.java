package org.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class LoggerLevelConfig {

    private static final Logger log = LoggerFactory.getLogger(LoggerLevelConfig.class);



    public static void displayCurrentLogLevel() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Level logLevel = loggerContext.getLogger("root").getLevel();
        log.info("Default log level: {}", logLevel);
    }






//    private String debugLevel;
//
//    public LoggerLevelConfig(String[] args) {
//        if (args.length > 0) {
//            debugLevel = args[0];
//        } else {
//            debugLevel = System.getenv("DEBUG_LEVEL");
//        }
//
//        if (debugLevel != null) {
//            System.setProperty("DEBUG_LOG_LEVEL", debugLevel);
//        } else {
//            System.setProperty("DEBUG_LOG_LEVEL", "ERROR");
//        }
//    }
//
//    public String getDebugLevel() {
//        return debugLevel;
//    }
}
