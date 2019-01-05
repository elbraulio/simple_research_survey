package com.elbraulio.survey.mail;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class BuildMailContent {
    private final int ghUserId;
    private final Connection rosghConn;

    public BuildMailContent(int ghUserId, Connection rosghConn) {
        this.ghUserId = ghUserId;
        this.rosghConn = rosghConn;
    }

    public MailContent content() throws IOException {
        String mail = new FetchMail(this.ghUserId, this.rosghConn).mail();
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream("mail_description");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(stream,
                             StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String content = sb.toString();
        content = content.replace("{survey-link}",
                "http://survey.elbraulio.com/survey?id=" + this.ghUserId);
        content = content.replace("{survey-description-link}", "http://survey" +
                ".elbraulio.com");
        return new MailContent(
                mail,
                "research survey",
                content
        );
    }
}
