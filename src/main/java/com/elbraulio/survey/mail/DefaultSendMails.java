package com.elbraulio.survey.mail;

import com.elbraulio.survey.utils.SurveyLock;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class DefaultSendMails implements SendMails {
    private final Connection surveyConn;
    private final Connection rosghConn;
    private final Logger logger = Logger.getLogger(DefaultSendMails.class);

    public DefaultSendMails(Connection surveyConn, Connection rosghConn) {

        this.surveyConn = surveyConn;
        this.rosghConn = rosghConn;
    }

    @Override
    public void send() {
        if (SurveyLock.isLocked()) {
            return;
        } else {
            SurveyLock.lock();
        }
        try {
            List<Survey> surveys = new FetchSurveys(this.surveyConn).list();
            for (Survey survey : surveys) {
                new DeleteSurvey(survey.id(), this.surveyConn).delete();

                new Mail(
                        new BuildMailContent(survey.rosUserId(),
                                this.rosghConn).content()
                ).send();

            }
        } catch (IOException e) {
            this.logger.error("Error", e);
        }
        SurveyLock.unlock();
    }
}
