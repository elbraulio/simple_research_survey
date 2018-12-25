package com.elbraulio.survey.mail;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class DeleteSurvey {
    private final int id;
    private final Connection connection;
    private final Logger logger = Logger.getLogger(DeleteSurvey.class);

    public DeleteSurvey(int id, Connection connection) {
        this.id = id;
        this.connection = connection;
    }

    public void delete() {
        String sql = "delete from survey where id=" + this.id + ";";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("sql", e);
        }
    }
}
