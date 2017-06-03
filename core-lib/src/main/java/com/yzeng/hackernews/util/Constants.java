package com.yzeng.hackernews.util;

public abstract class Constants {

    public static final String BASE_URL_NEWS = "https://hacker-news.firebaseio.com/v0/";
    public static final String BASE_URL_PICS = "http://www.splashbase.co/api/v1/images/";
    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB

    public static final int CACHE_MAX_AGE = 2; // 2 min
    public static final int CACHE_MAX_STALE = 0; // 7 day

    public static final int MAX_REFRESH_TIMEOUT = 2; // 2 second
    public static final int MAX_REFRESH_COUNT = 5;

}
