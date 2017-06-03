package com.yzeng.hackernews.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.di.scope.PicScope;
import com.yzeng.hackernews.view.activity.AbstractActivity;
import com.yzeng.hackernews.view.activity.BaseActivity;
import com.yzeng.hackernews.view.activity.MainActivity;
import com.yzeng.hackernews.view.adapter.NewsRecyclerViewAdapter;
import com.yzeng.hackernews.view.adapter.PicsRecyclerViewAdapter;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import com.yzeng.hackernews.view.fragment.PicsFragment;

import dagger.Module;
import dagger.Provides;


@Module
public class AppPicsModule extends PicsModule {
    private final Context context;
    private final PicsFragment.OnListFragmentInteractionListener listener;
    private final PicsFragment newsFragment;
    private final MainActivity baseActivity;

    public AppPicsModule(Context context, PicsFragment fragment) {
        super(fragment);

        this.newsFragment = fragment;
        this.context = context;

        if(context instanceof MainActivity)
        {
            this.baseActivity = (MainActivity) context;
        }else
        {
            throw new RuntimeException(context.toString()
                    + " must implement baseActivity");
        }

        if (context instanceof PicsFragment.OnListFragmentInteractionListener) {
            listener = (PicsFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Provides
    @PicScope
    public PicsFragment.OnListFragmentInteractionListener provideOnListFragmentInteractionListener() {
        return listener;
    }

    @Provides
    @PicScope
    public MainActivity provideBaseActivity() {
        return baseActivity;
    }

    @Provides
    @PicScope
    public LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Provides
    @PicScope
    public PicsRecyclerViewAdapter provideAdapter() {
        return new PicsRecyclerViewAdapter(newsFragment);
    }

}
