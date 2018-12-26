package com.elbraulio.survey.quartz;

import com.elbraulio.survey.mail.DefaultSendMails;
import com.elbraulio.survey.utils.BProperties;
import com.elbraulio.survey.utils.SqliteConnection;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.Connection;

/**
 * Scheduled Job
 */
public class MailSurvey implements Job {

    private final Logger logger = Logger.getLogger(MailSurvey.class);

    @Override
    public void execute(JobExecutionContext context) {
        BProperties properties = new BProperties();
        try (Connection survey = new SqliteConnection(
                properties.prop("sqlite.path")
        ).connection();
             Connection rosgh = new SqliteConnection(
                     properties.prop("rosgh.db.path")
             ).connection()
        ) {
            logger.info("starting job mail");
            new DefaultSendMails(
                    survey,
                    rosgh
            ).send();
        } catch (Exception e) {
            logger.error(
                    "Unexpected exception", e
            );
            Thread.currentThread().interrupt();
        }
    }
}
