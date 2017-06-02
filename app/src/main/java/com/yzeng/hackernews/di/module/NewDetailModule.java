package com.yzeng.hackernews.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.yzeng.hackernews.di.scope.ItemScope;
import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.view.activity.BaseActivity;
import com.yzeng.hackernews.view.adapter.CommentsRecyclerViewAdapter;
import com.yzeng.hackernews.view.adapter.NewsRecyclerViewAdapter;
import com.yzeng.hackernews.view.fragment.DetailFragment;
import com.yzeng.hackernews.view.fragment.NewsFragment;

import dagger.Module;
import dagger.Provides;


@Module
public class NewDetailModule extends ItemModule {
    private final Context context;
    private final DetailFragment newsFragment;
    private final BaseActivity baseActivity;


    public NewDetailModule(Context context, DetailFragment fragment) {
        super(fragment);
        this.newsFragment = fragment;
        this.context = context;

        if(context instanceof BaseActivity)
        {
            this.baseActivity = (BaseActivity) context;
        }else
        {
            throw new RuntimeException(context.toString()
                    + " must implement baseActivity");
        }

    }

    @Provides
    @ItemScope
    public BaseActivity provideBaseActivity() {
        return baseActivity;
    }

    @Provides
    @ItemScope
    public LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Provides
    @ItemScope
    public CommentsRecyclerViewAdapter provideAdapter() {
        return new CommentsRecyclerViewAdapter();
    }

}
