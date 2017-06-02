package com.yzeng.hackernews.view.activity;

import android.content.Context;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.hackernews.api.Item;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.view.fragment.DetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Activity that shows details of a certain user.
 */
public class DetailActivity extends BaseActivity {

    private static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";
    public static final String TAG_DETAIL_FRAGMENT = "detail_fragment";

    private Fragment mDetailFragment;

    public static Intent getCallingIntent(Context context, Item userId) {
        Intent callingIntent = new Intent(context, DetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
        return callingIntent;
    }

    private Item userId;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializeActivity(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }


    @Override
    protected void injectDependencies(ApplicationComponent component) {

    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putSerializable(INSTANCE_STATE_PARAM_USER_ID, this.userId);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.userId = (Item) getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_USER_ID) ;
            mDetailFragment = DetailFragment.forUser(userId);
        } else {
            this.userId =(Item) savedInstanceState.getSerializable(INSTANCE_STATE_PARAM_USER_ID);
            mDetailFragment = findFragment(TAG_DETAIL_FRAGMENT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        attachFragment();
        Timber.d("Detail Activity Resumed");
    }

    private void attachFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, mDetailFragment, TAG_DETAIL_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }
}