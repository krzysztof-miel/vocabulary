package org.example;

import org.example.config.LoggerLevelConfig;
import org.example.mailModule.EmailScheduler;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vocabulary {

    private static final Logger log = LoggerFactory.getLogger(Vocabulary.class);

    public static void main(String[] args) throws IOException {

        String logLevel = System.getenv("LOG_LEVEL");
        System.out.println("Application started with log level: " + logLevel);

        LoggerLevelConfig.displayCurrentLogLevel();

        EmailScheduler.startScheduler();

    }
}