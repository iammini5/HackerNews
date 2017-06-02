package com.yzeng.hackernews.util;

import rx.Scheduler;


public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();

}
