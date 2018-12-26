package com.elbraulio.survey.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class SaveAnswer {
    private final String q1;
    private final String q2;
    private final String q3;
    private final String feedback;
    private final String aspirantId;
    private final Connection survey;

    public SaveAnswer(String q1, String q2, String q3, String feedback, String aspirantId, Connection survey) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.feedback = feedback;
        this.aspirantId = aspirantId;
        this.survey = survey;
    }

    public void save() throws SQLException {
        String sql = "insert into answers (q1, q2, q3, feedback, aspirant_id)" +
                " values ('" + q1 + "', '" + q2 + "', '" + q3 + "', '" + feedback + "', " + aspirantId +
                ");";
        try (PreparedStatement ps = survey.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
}
