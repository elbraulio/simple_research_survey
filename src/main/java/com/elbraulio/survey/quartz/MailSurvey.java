package com.elbraulio.survey.quartz;

import com.elbraulio.survey.mail.DefaultSendMails;
import com.elbraulio.survey.utils.BProperties;
import com.elbraulio.survey.utils.SqliteConnection;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 
 * Scheduled Job
 *
 */
public class MailSurvey implements Job {

    private final Logger logger = Logger.getLogger(MailSurvey.class);

	@Override
	public void execute(JobExecutionContext context) {
		try {
		    logger.info("starting job mail");
			BProperties properties = new BProperties();
			new DefaultSendMails(
                new SqliteConnection(
                        properties.prop("sqlite.path")
                ).connection(),
                    new SqliteConnection(
                            properties.prop("rosgh.db.path")
                    ).connection()
			).send();
		} catch (Exception e) {
			logger.error(
					"Unexpected exception", e
			);
			Thread.currentThread().interrupt();
		}
	}
}
