package com.yzeng.hackernews.di.component;

import com.yzeng.hackernews.di.module.AndroidModule;
import com.yzeng.hackernews.di.module.ApiModule;
import com.yzeng.hackernews.di.module.AppNewsModule;
import com.yzeng.hackernews.di.module.AppPicsModule;
import com.yzeng.hackernews.di.module.ApplicationModule;
import com.yzeng.hackernews.di.module.ClientModule;
import com.yzeng.hackernews.di.module.MainModule;
import com.yzeng.hackernews.di.module.NewDetailModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        ClientModule.class,
})
public interface ApplicationComponent {

    MainSubComponent plus(MainModule module);

    NewsSubComponent plus(AppNewsModule module);

    NewsDetailComponent plus(NewDetailModule module);

    PicsSubComponent plus(AppPicsModule module);

}