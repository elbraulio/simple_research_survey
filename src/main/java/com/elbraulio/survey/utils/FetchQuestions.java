package com.elbraulio.survey.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class FetchQuestions {
    private final String rosUserId;
    private final Connection survey;
    private final Connection rosgh;

    public FetchQuestions(String rosUserId, Connection survey, Connection rosgh) {
        this.rosUserId = rosUserId;
        this.survey = survey;
        this.rosgh = rosgh;
    }

    public List<Question> list() throws SQLException {
        String sql = "select * from aspirant where ros_user_id=" + rosUserId+
                ";";
        List<Aspirant> aspirants = new LinkedList<>();
        try(ResultSet rs = survey.createStatement().executeQuery(sql)){
            while(rs.next()){
                aspirants.add(new Aspirant(
                        rs.getInt("id"),
                        rs.getInt("ros_user_id"),
                        rs.getInt("ros_question_id")

                ));
            }
        }
        List<Question> questions = new LinkedList<>();
        for(Aspirant aspirant : aspirants){
            String content = "select title, summary, url form ros_question " +
                    "where " +
                    "id=" + aspirant.ros_question_id + ";";
            try(ResultSet rs = rosgh.createStatement().executeQuery(content)){
                if (rs.next()){
                    questions.add(
                            new Question(
                                    aspirant.id,
                                    rs.getString("summary"),
                                    rs.getString("url"),
                                    rs.getString("title")
                            )
                    );
                }
            }
        }
        return questions;
    }

    private class Aspirant {

        private final int id;
        private final int ros_user_id;
        private final int ros_question_id;

        public Aspirant(int id, int ros_user_id, int ros_question_id) {

            this.id = id;
            this.ros_user_id = ros_user_id;
            this.ros_question_id = ros_question_id;
        }
    }
}
