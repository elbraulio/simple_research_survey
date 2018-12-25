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

	@Override
	public void execute(JobExecutionContext context) {
		try {
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
			Logger.getLogger(MailSurvey.class).error(
					"Unexpected exception", e
			);
			Thread.currentThread().interrupt();
		}
	}
}
