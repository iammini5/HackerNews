package com.yzeng.hackernews.test.dagger.di.module;

import com.yzeng.hackernews.di.module.ClientModule;
import com.yzeng.hackernews.test.dagger.utils.MockInterceptor;
import javax.inject.Named;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import static com.yzeng.hackernews.test.dagger.utils.MockInterceptor.RESPONSE_WITH_COMMENTS;


public class MockClientModule extends ClientModule{

    @Override
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                            int networkTimeoutInSeconds,
                                            boolean isDebug,
                                            Cache cache,
                                            Interceptor cacheInterceptor,
                                            Interceptor offlineCacheInterceptor) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(cacheInterceptor);
        return okHttpClient.build();
    }

    @Override
    public Interceptor provideCacheInterceptor(@Named("cacheMaxAge") int maxAgeMin) {
        return new MockInterceptor(RESPONSE_WITH_COMMENTS);
    }
}
