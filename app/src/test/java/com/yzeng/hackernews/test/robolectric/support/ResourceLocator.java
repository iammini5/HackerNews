package com.yzeng.hackernews.test.robolectric.support;

import android.support.annotation.StringRes;

import org.robolectric.RuntimeEnvironment;

public class ResourceLocator {
    public static String getString(@StringRes int id) {
        return RuntimeEnvironment.application.getString(id);
    }
}
