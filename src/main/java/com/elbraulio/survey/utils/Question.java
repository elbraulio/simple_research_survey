package com.elbraulio.survey.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Question {
    private final int aspirantId;
    private final String summary;
    private final String url;
    private final String title;
    private final String rosUserId;

    public Question(int aspirantId, String summary, String url, String title, String rosUserId) {

        this.aspirantId = aspirantId;
        this.summary = summary;
        this.url = url;
        this.title = title;
        this.rosUserId = rosUserId;
    }

    public int getAspirantId() {
        return aspirantId;
    }

    public String getSummary() {
        return summary;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getRosUserId() {
        return rosUserId;
    }
}
