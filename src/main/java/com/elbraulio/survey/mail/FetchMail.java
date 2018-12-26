package com.elbraulio.survey.mail;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class FetchMail {
    private final int rosUserId;
    private final Connection rosghConn;
    private final Logger logger = Logger.getLogger(FetchMail.class);

    public FetchMail(int rosUserId, Connection rosghConn) {
        this.rosUserId = rosUserId;
        this.rosghConn = rosghConn;
    }

    public String mail() {
        String sql = "select gu.email " +
                "from gh_user gu " +
                "       join linked_users lu on gu.id = lu.gh_user_id " +
                "join ros_user ru on lu.ros_user_id = ru.id where ru.id="
                + this.rosUserId + ";";
        String mail = null;
        try (ResultSet rs = rosghConn.createStatement().executeQuery(sql)) {
            if(rs.next()){
                mail = rs.getString("email");
            }
        } catch (SQLException e) {
            this.logger.error("sql", e);
        }
        return mail;
    }
}
