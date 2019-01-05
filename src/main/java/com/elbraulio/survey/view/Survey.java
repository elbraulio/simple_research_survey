package com.elbraulio.survey.view;

import com.elbraulio.survey.utils.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Survey extends HttpServlet {
    private final String VIEW_TEMPLATE_PATH = "survey.jsp";
    private final Logger logger = Logger.getLogger(Survey.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String ghUserId = req.getParameter("id");
        if (ghUserId == null) {
            logger.info("no id supplied");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            logger.info("requesting survey for id " + ghUserId);
            BProperties properties = new BProperties();
            try (Connection survey = new SqliteConnection(
                    properties.prop("sqlite.path")
            ).connection();
                 Connection rosgh = new SqliteConnection(
                         properties.prop("rosgh.db.path")
                 ).connection()) {
                List<Question> questions = new FetchQuestions(
                        ghUserId, survey, rosgh
                ).list();
                if (questions.isEmpty()) {
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                    return;
                } else {
                    req.setAttribute("questions", questions);
                }
                req.getRequestDispatcher(VIEW_TEMPLATE_PATH).forward(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                logger.error("sql", e);
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");
        String q3 = request.getParameter("q3");
        String feedback = request.getParameter("feedback");
        String aspirantId = request.getParameter("aspirant-id");
        String ghUserId = request.getParameter("ghuser-id");
        logger.info(
                "aspirantId:" + aspirantId + "\n" +
                        "ghUserId:" + ghUserId + "\n" +
                        "q1:" + q1 + "\n" +
                        "q2:" + q2 + "\n" +
                        "q3:" + q3 + "\n" +
                        "feedback:" + feedback + "\n"
        );
        BProperties properties = new BProperties();
        try (Connection survey = new SqliteConnection(
                properties.prop("sqlite.path")
        ).connection()) {
            new SaveAnswer(
                    q1, q2, q3, feedback, aspirantId, survey
            ).save();
            response.sendRedirect("/survey?id=" + ghUserId);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("sql", e);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
