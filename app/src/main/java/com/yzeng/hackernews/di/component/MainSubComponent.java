package com.yzeng.hackernews.di.component;

import com.yzeng.hackernews.di.module.MainModule;
import com.yzeng.hackernews.di.scope.MainScope;
import com.yzeng.hackernews.view.activity.MainActivity;

import dagger.Subcomponent;

@MainScope
@Subcomponent(modules = {
        MainModule.class
})
public interface MainSubComponent {

    void inject(MainActivity activity);
}
