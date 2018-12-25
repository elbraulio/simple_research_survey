package com.elbraulio.survey.mail;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class MailContent {
    private final String to;
    private final String subject;
    private final String content;

    public MailContent(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String subject() {
        return this.subject;
    }

    public String to() {
        return this.to;
    }

    public String body() {
        return this.content;
    }
}
