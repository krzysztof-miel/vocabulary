package org.example.job;


import org.example.config.EnvLoader;
import org.example.config.MemoryLogger;
import org.example.gptClient.GptClient;
import org.example.gptClient.Prompt;
import org.example.mailModule.EmailSender;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(EmailJob.class);


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("Start email sending task.");
        String recipient = EnvLoader.getRecipient();
        String subject = "English vocabulary";
        String response;
        try {
            GptClient client = new GptClient();
            log.debug("Sending request to GPT API with prompt: {}", Prompt.prompt);
            response = client.getResponse(Prompt.prompt);
            log.debug("Received response from GPT API: \n {}", response);
            EmailSender.sendEmail(recipient, subject, response);
            log.info("Next email scheduled to be sent at: {}", context.getTrigger().getNextFireTime());
            MemoryLogger.logMemoryUsage();
        } catch (IOException e) {
            log.error("Error while sending email: {}", e.getMessage(), e);
        }


    }
}
