package com.elbraulio.survey.view;

import com.elbraulio.survey.utils.BProperties;
import com.elbraulio.survey.utils.FetchQuestions;
import com.elbraulio.survey.utils.Question;
import com.elbraulio.survey.utils.SqliteConnection;
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
        String rosUserId = req.getParameter("id");
        if (rosUserId == null) {
            logger.info("no id supplied");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            logger.info("requesting survey for id " + rosUserId);
            BProperties properties = new BProperties();
            try(Connection survey = new SqliteConnection(
                    properties.prop("sqlite.path")
            ).connection();
                Connection rosgh = new SqliteConnection(
                        properties.prop("rosgh.db.path")
                ).connection() ){
                List<Question> questions = new FetchQuestions(
                        rosUserId, survey, rosgh
                ).list();
                if(questions.isEmpty()) {
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
}
