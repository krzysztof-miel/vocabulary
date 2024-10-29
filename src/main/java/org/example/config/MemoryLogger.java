package org.example.config;


import org.slf4j.LoggerFactory;


public class MemoryLogger {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MemoryLogger.class);


    public static void logMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        log.debug("Memory usage:");
        log.debug("Total memory: " + totalMemory / (1024 * 1024) + " MB");
        log.debug("Used memory: " + usedMemory / (1024 * 1024) + " MB");
        log.debug("Free memory: " + freeMemory / (1024 * 1024) + " MB");
    }
}
