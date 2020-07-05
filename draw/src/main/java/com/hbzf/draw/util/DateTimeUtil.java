package com.hbzf.draw.util;

/**
 * @author luosai
 * @date 2020/5/17
 */
public class DateTimeUtil {

    private static final long ONE_SECOND = 1000;

    public static long currentTimeSeconds() {
        return System.currentTimeMillis() / ONE_SECOND;
    }

    public static long getTimeAgo(long gapTime) {
        return System.currentTimeMillis() - gapTime;
    }
}
