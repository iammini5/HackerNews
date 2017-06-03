package com.yzeng.hackernews.di.module;

import com.google.gson.Gson;
import com.yzeng.hackernews.client.HackerNewsApi;
import com.yzeng.hackernews.client.SplashBaseApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    @Provides
    @Singleton
    public HackerNewsApi provideNewsApiService(@Named("newsEndPoint")Retrofit retrofit) {
        return retrofit.create(HackerNewsApi.class);
    }

    @Provides
    @Singleton
    public SplashBaseApi providePicsApiService(@Named("picsEndPoint")Retrofit retrofit) {
        return retrofit.create(SplashBaseApi.class);
    }

    @Provides
    @Singleton
    @Named("newsEndPoint")
    public Retrofit provideNewsRetrofit( @Named("newsEndPoint") HttpUrl baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named("picsEndPoint")
    public Retrofit providePicsRetrofit( @Named("picsEndPoint") HttpUrl baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public Converter.Factory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


}
