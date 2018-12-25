package com.elbraulio.survey.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class SurveyLock {

    private static boolean lock = false;

    public static boolean isLocked() {
        return lock;
    }

    public static void lock() {
        lock = true;
    }

    public static void unlock() {
        lock = false;
    }
}
