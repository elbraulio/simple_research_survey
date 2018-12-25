package com.elbraulio.survey.quartz;

import com.elbraulio.survey.utils.BProperties;
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

		} catch (Exception e) {
			Logger.getLogger(MailSurvey.class).error(
					"Unexpected exception", e
			);
			Thread.currentThread().interrupt();
		}
	}
}
