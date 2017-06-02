package com.yzeng.hackernews.di.component;

import com.yzeng.hackernews.di.module.NewDetailModule;
import com.yzeng.hackernews.di.scope.ItemScope;
import com.yzeng.hackernews.view.fragment.DetailFragment;

import dagger.Subcomponent;


@ItemScope
@Subcomponent(modules = {
        NewDetailModule.class,
})
public interface NewsDetailComponent {
    void inject(DetailFragment fragment);
}
