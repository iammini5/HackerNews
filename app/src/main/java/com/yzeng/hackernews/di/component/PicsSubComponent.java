package com.yzeng.hackernews.di.component;

import com.yzeng.hackernews.di.module.AdapterModule;
import com.yzeng.hackernews.di.module.AppNewsModule;
import com.yzeng.hackernews.di.module.AppPicsModule;
import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.di.scope.PicScope;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import com.yzeng.hackernews.view.fragment.PicsFragment;

import dagger.Subcomponent;


@PicScope
@Subcomponent(modules = {
        AppPicsModule.class,
})

public interface PicsSubComponent {
    void inject(PicsFragment fragment);
}
