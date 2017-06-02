package com.yzeng.hackernews.test.dagger.di.component;

import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.di.module.AndroidModule;
import com.yzeng.hackernews.di.module.ApiModule;
import com.yzeng.hackernews.di.module.ApplicationModule;
import com.yzeng.hackernews.di.module.ClientModule;
import com.yzeng.hackernews.test.dagger.DetailActivityDaggerTest;
import com.yzeng.hackernews.test.dagger.MainActivityDaggerTest;
import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        ClientModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(MainActivityDaggerTest test);

    void inject(DetailActivityDaggerTest test);

}