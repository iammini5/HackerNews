package com.yzeng.hackernews.test.robolectric.support;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

public class ViewLocator {
    public static View getView(Activity activity, @IdRes int id) {
        return activity.findViewById(id);
    }

    public static View getView(Fragment fragment, @IdRes int id) {
        return fragment.getView().findViewById(id);
    }

    public static TextView getTextView(Activity activity, @IdRes int id) {
        return (TextView) getView(activity, id);
    }

    public static TextView getTextView(Fragment fragment, @IdRes int id) {
        return (TextView) getView(fragment, id);
    }
}
