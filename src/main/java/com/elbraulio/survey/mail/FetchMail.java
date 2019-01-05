package com.elbraulio.survey.mail;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class FetchMail {
    private final int ghUserId;
    private final Connection rosghConn;
    private final Logger logger = Logger.getLogger(FetchMail.class);

    public FetchMail(int ghUserId, Connection rosghConn) {
        this.ghUserId = ghUserId;
        this.rosghConn = rosghConn;
    }

    public String mail() {
        String sql = "select gu.email " +
                "from gh_user gu " +
                "       where gu.id="
                + this.ghUserId + ";";
        String mail = "";
        try (ResultSet rs = rosghConn.createStatement().executeQuery(sql)) {
            if(rs.next()){
                mail = rs.getString("email");
            } else {
                logger.info("mail not found for gh_user_id " + ghUserId);
            }
        } catch (SQLException e) {
            this.logger.error("sql", e);
        }
        return mail;
    }
}
