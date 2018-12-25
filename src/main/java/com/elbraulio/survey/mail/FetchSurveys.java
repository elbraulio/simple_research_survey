package com.elbraulio.survey.mail;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class FetchSurveys {
    private final Connection connection;
    private final Logger logger = Logger.getLogger(FetchSurveys.class);

    public FetchSurveys(Connection connection) {
        this.connection = connection;
    }

    public List<Survey> list() {
        String sql = "select * from survey;";
        List<Survey> surveys = new LinkedList<>();
        try(ResultSet rs = connection.createStatement().executeQuery(sql)){
            while (rs.next()){
                surveys.add(new Survey(
                        rs.getInt("id"),
                        rs.getInt("ros_user_id")
                ));
            }
        } catch (SQLException e) {
            logger.error("sql", e);
        }
        return surveys;
    }
}
