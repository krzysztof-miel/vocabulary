package org.example;

import org.example.gptClient.GptClient;
import org.example.gptClient.Prompt;
import org.example.mailModule.EmailSender;
import org.example.config.EnvLoader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

import static org.example.mailModule.EmailScheduler.calculateNextRun;
import static org.example.mailModule.EmailScheduler.scheduleDailyEmail;

@Slf4j
public class Vocabulary {
    public static void main(String[] args) throws IOException {

        log.info("Application started.");

        LocalTime scheduledTime = LocalTime.of(13, 30);


        Runnable emailTask = () -> {
            log.info("Starting email sending task.");
            String recipient = EnvLoader.getRecipient();
            String subject = "English vocabulary";
            String response;
            try {
                GptClient client = new GptClient();
                log.info("Before gpt - in try");
                response = client.getResponse(Prompt.prompt);
                log.info("Afrer gpt - in try");
            } catch (IOException e) {
                log.info("Problem - in catch" + e);
                throw new RuntimeException(e);
            }

            EmailSender.sendEmail(recipient, subject, response);
            log.info("Email sent successfully.");

            LocalDateTime nextRun = calculateNextRun(scheduledTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            log.info("Next email scheduled for: " + nextRun.format(formatter));
        };

        scheduleDailyEmail(emailTask, scheduledTime);


    }
}