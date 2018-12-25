package com.elbraulio.survey.mail;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Survey {
    private final int id;
    private final int rosUserId;

    public Survey(int id, int rosUserId) {

        this.id = id;
        this.rosUserId = rosUserId;
    }

    public int id() {
        return id;
    }

    public int rosUserId() {
        return rosUserId;
    }
}
