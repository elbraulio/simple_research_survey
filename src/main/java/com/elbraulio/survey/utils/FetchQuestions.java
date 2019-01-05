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
    private final String ghUserId;
    private final Connection survey;
    private final Connection rosgh;

    public FetchQuestions(String ghUserId, Connection survey, Connection rosgh) {
        this.ghUserId = ghUserId;
        this.survey = survey;
        this.rosgh = rosgh;
    }

    public List<Question> list() throws SQLException {
        String sql = "select * from aspirant where gh_user_id=" + ghUserId +
                " and aspirant.id not in (select aspirant_id from answers);";
        List<Aspirant> aspirants = new LinkedList<>();
        try(ResultSet rs = survey.createStatement().executeQuery(sql)){
            while(rs.next()){
                aspirants.add(new Aspirant(
                        rs.getInt("id"),
                        rs.getInt("gh_user_id"),
                        rs.getInt("ros_question_id")

                ));
            }
        }
        List<Question> questions = new LinkedList<>();
        for(Aspirant aspirant : aspirants){
            String content = "select title, summary, url from ros_question " +
                    "where " +
                    "id=" + aspirant.ros_question_id + ";";
            try(ResultSet rs = rosgh.createStatement().executeQuery(content)){
                if (rs.next()){
                    questions.add(
                            new Question(
                                    aspirant.id,
                                    rs.getString("summary"),
                                    rs.getString("url"),
                                    rs.getString("title"),
                                    aspirant.gh_user_id + ""
                            )
                    );
                }
            }
        }
        return questions;
    }

    private class Aspirant {

        private final int id;
        private final int gh_user_id;
        private final int ros_question_id;

        public Aspirant(int id, int gh_user_id, int ros_question_id) {

            this.id = id;
            this.gh_user_id = gh_user_id;
            this.ros_question_id = ros_question_id;
        }
    }
}
