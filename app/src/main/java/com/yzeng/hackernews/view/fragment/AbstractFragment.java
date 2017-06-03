package com.yzeng.hackernews.view.fragment;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yzeng.hackernews.HackerNewsApplication;
import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.view.activity.AbstractActivity;
import com.yzeng.hackernews.view.activity.BaseActivity;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public abstract class AbstractFragment extends SupportFragment {

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        FragmentAnimator fragmentAnimator = _mActivity.getFragmentAnimator();
        fragmentAnimator.setEnter(0);
        fragmentAnimator.setExit(0);
        return fragmentAnimator;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies(HackerNewsApplication.getComponent(), context);
    }

    protected abstract void injectDependencies(ApplicationComponent component, Context context);

    abstract AbstractActivity getBaseActivity();

}
