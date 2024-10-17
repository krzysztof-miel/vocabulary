package org.example;

import org.example.gptClient.GptClient;
import org.example.gptClient.Prompt;
import org.example.mailModule.EmailScheduler;
import org.example.mailModule.EmailSender;
import org.example.config.EnvLoader;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vocabulary {

    private static final Logger log = LoggerFactory.getLogger(Vocabulary.class);

    public static void main(String[] args) throws IOException {

        log.info("Application started.");

        EmailScheduler.startScheduler();

    }
}