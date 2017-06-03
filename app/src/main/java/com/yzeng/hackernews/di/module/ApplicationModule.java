package com.yzeng.hackernews.di.module;

import android.content.Context;

import com.yzeng.hackernews.BuildConfig;
import com.yzeng.hackernews.util.AppSchedulerProvider;
import com.yzeng.hackernews.util.Constants;
import com.yzeng.hackernews.util.SchedulerProvider;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;


@Module
public class ApplicationModule {
    @Provides
    @Singleton
    @Named("isDebug")
    public boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    public int provideNetworkTimeoutInSeconds() {
        return Constants.NETWORK_CONNECTION_TIMEOUT;
    }

    @Provides
    @Singleton
    @Named("newsEndPoint")
    public HttpUrl provideNewsEndpoint() {
        return HttpUrl.parse(Constants.BASE_URL_NEWS);
    }

    @Provides
    @Singleton
    @Named("picsEndPoint")
    public HttpUrl providePicsEndpoint() {
        return HttpUrl.parse(Constants.BASE_URL_PICS);
    }

    @Provides
    @Singleton
    public SchedulerProvider provideAppScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    public long provideCacheSize() {
        return Constants.CACHE_SIZE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    public int provideCacheMaxAgeMinutes() {
        return Constants.CACHE_MAX_AGE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    public int provideCacheMaxStaleDays() {
        return Constants.CACHE_MAX_STALE;
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    public File provideCacheDir(Context context) {
        return context.getCacheDir();
    }

    @Provides
    @Singleton
    @Named("maxTimeForRefresh")
    public int provideMaxRefreshTimeout() {
        return Constants.MAX_REFRESH_TIMEOUT;
    }

    @Provides
    @Singleton
    @Named("maxCountForRefresh")
    public int provideMaxRefreshCount() {
        return Constants.MAX_REFRESH_COUNT;
    }


}
