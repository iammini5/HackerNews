package com.yzeng.hackernews.di.component;

import com.yzeng.hackernews.di.module.AdapterModule;
import com.yzeng.hackernews.di.module.AppNewsModule;
import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.view.fragment.NewsFragment;

import dagger.Subcomponent;


@NewsScope
@Subcomponent(modules = {
        AppNewsModule.class,
})
public interface NewsSubComponent {
    void inject(NewsFragment fragment);
    NewsAdapterSubComponent plus(AdapterModule module);
}
