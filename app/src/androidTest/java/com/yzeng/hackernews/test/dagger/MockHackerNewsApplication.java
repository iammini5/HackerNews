package com.yzeng.hackernews.test.dagger;

import com.yzeng.hackernews.HackerNewsApplicationImpl;
import com.yzeng.hackernews.di.module.AndroidModule;
import com.yzeng.hackernews.di.module.ApiModule;
import com.yzeng.hackernews.test.dagger.di.component.DaggerTestApplicationComponent;
import com.yzeng.hackernews.test.dagger.di.component.TestApplicationComponent;
import com.yzeng.hackernews.test.dagger.di.module.MockApplicationModule;
import com.yzeng.hackernews.test.dagger.di.module.MockClientModule;


public class MockHackerNewsApplication extends HackerNewsApplicationImpl {

    @Override
    public TestApplicationComponent createComponent() {
        return DaggerTestApplicationComponent
                .builder()
                .androidModule(new AndroidModule(this))
                .applicationModule(new MockApplicationModule())
                .apiModule(new ApiModule())
                .clientModule(new MockClientModule())
                .build();
    }

}
