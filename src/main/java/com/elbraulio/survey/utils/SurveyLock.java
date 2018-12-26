package com.elbraulio.survey.utils;

import org.apache.log4j.Logger;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class SurveyLock {
    private static final Logger logger = Logger.getLogger(SurveyLock.class);

    private static boolean lock = false;

    public static boolean isLocked() {
        return lock;
    }

    public static void lock() {
        logger.info("locking");
        lock = true;
    }

    public static void unlock() {
        logger.info("unlocking");
        lock = false;
    }
}
