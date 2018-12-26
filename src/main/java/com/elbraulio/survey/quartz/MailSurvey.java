package com.elbraulio.survey.quartz;

import com.elbraulio.survey.mail.DefaultSendMails;
import com.elbraulio.survey.utils.BProperties;
import com.elbraulio.survey.utils.SqliteConnection;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Scheduled Job
 */
public class MailSurvey extends HttpServlet {

    private final Logger logger = Logger.getLogger(MailSurvey.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
