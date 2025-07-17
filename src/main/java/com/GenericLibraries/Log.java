package com.GenericLibraries;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class Log {

    private static Logger logger;

    public static Logger initializeLogger(Class<?> cls) {
        if (logger == null) {
            createLogDirectory();

            // Use a fixed log file name (same log file reused every run)
            String logFilePath = "logs/logs.log";

            // Set the system property so log4j can use it
            System.setProperty("logfile.name", logFilePath);

            // Configure log4j
            PropertyConfigurator.configure("src/test/resources/log4j.properties");

            // Initialize logger only once
            logger = Logger.getLogger(cls);
        }

        return logger;
    }

    private static void createLogDirectory() {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }

    // Helper methods to directly log messages
    public static void info(String message) {
        if (logger != null) logger.info(message);
    }

    public static void error(String message) {
        if (logger != null) logger.error(message);
    }

    public static void warn(String message) {
        if (logger != null) logger.warn(message);
    }

    public static void debug(String message) {
        if (logger != null) logger.debug(message);
    }
}
