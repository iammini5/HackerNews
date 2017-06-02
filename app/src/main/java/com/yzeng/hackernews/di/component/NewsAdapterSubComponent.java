package com.yzeng.hackernews.di.component;

import com.yzeng.hackernews.di.module.AdapterModule;
import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.view.adapter.NewViewHolder;

import dagger.Subcomponent;


@NewsScope
@Subcomponent(modules = {
        AdapterModule.class
})
public interface NewsAdapterSubComponent {

    void inject(NewViewHolder adapter);

}
