package com.yzeng.hackernews.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.view.activity.BaseActivity;
import com.yzeng.hackernews.view.adapter.NewsRecyclerViewAdapter;
import com.yzeng.hackernews.view.fragment.NewsFragment;

import dagger.Module;
import dagger.Provides;


@Module
public class AppNewsModule extends NewsModule {
    private final Context context;
    private final NewsFragment.OnListFragmentInteractionListener listener;
    private final NewsFragment newsFragment;
    private final BaseActivity baseActivity;

    public AppNewsModule(Context context, NewsFragment fragment) {
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

        if (context instanceof NewsFragment.OnListFragmentInteractionListener) {
            listener = (NewsFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Provides
    @NewsScope
    public NewsFragment.OnListFragmentInteractionListener provideOnListFragmentInteractionListener() {
        return listener;
    }

    @Provides
    @NewsScope
    public BaseActivity provideBaseActivity() {
        return baseActivity;
    }

    @Provides
    @NewsScope
    public LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Provides
    @NewsScope
    public NewsRecyclerViewAdapter provideAdapter() {
        return new NewsRecyclerViewAdapter(newsFragment);
    }

}
