package com.yzeng.hackernews.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;

import java.lang.ref.WeakReference;

public class LaunchActivity extends Activity {
    private static final String TAG = LaunchActivity.class.getSimpleName();
    private SwitchHandler mHandler = new SwitchHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //activity fad in and fad out
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onCreate(savedInstanceState);
        mHandler.sendEmptyMessageDelayed(1, 1000);

    }

    private static class SwitchHandler extends Handler {
        private WeakReference<LaunchActivity> mWeakReference;

        SwitchHandler(LaunchActivity activity) {
            mWeakReference = new WeakReference<LaunchActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LaunchActivity activity = mWeakReference.get();
            if (activity != null) {
                Intent mainIntent = MainActivity.getCallingIntent(activity);
                ActivityCompat.startActivity(activity, mainIntent, null);
                activity.finish();
            }
        }
    }
}