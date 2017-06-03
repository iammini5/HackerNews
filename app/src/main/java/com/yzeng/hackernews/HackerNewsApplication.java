package com.yzeng.hackernews;

import android.app.Application;

import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.di.component.DaggerApplicationComponent;
import com.yzeng.hackernews.di.module.AndroidModule;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;


public abstract class HackerNewsApplication extends Application {

    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();

        component = createComponent();
    }

    public ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public abstract void initApplication();
}
