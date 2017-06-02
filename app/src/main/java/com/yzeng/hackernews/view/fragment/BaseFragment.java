package com.yzeng.hackernews.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.yzeng.hackernews.HackerNewsApplication;
import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.view.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies(HackerNewsApplication.getComponent(), context);
        // can be used for general purpose in all Fragments of Application
    }

    protected abstract void injectDependencies(ApplicationComponent component, Context context);

    abstract BaseActivity getBaseActivity();

}