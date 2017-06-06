package com.yzeng.hackernews.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.hackernews.api.Item;
import com.splashbase.api.Picture;
import com.yzeng.hackernews.presenter.MainPresenter;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.di.module.MainModule;
import com.yzeng.hackernews.view.MainView;
import com.yzeng.hackernews.view.fragment.AbstractFragment;
import com.yzeng.hackernews.view.fragment.BaseFragment;
import com.yzeng.hackernews.view.fragment.BestStoryFragment;
import com.yzeng.hackernews.view.fragment.NewStoryFragment;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import com.yzeng.hackernews.view.fragment.PicsFragment;
import com.yzeng.hackernews.view.fragment.TopStoryFragment;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import timber.log.Timber;

public class MainActivity extends AbstractActivity implements MainView, NewsFragment.OnListFragmentInteractionListener, BottomNavigationView.OnNavigationItemSelectedListener,PicsFragment.OnListFragmentInteractionListener {

    public static final String TAG_OFFERS_FRAGMENT = "offers_fragment";
    private static final int TOP_STORY = 0;
    private static final int NEW_STORY = 1;
    private static final int BEST_STORY = 2;
    private static final int PIC = 3;

    // injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    Resources resources;
    @Inject
    MainPresenter presenter;

    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private NewsFragment mNewsFragment;
    private NewsFragment mBestStoryFragment;
    private NewsFragment mLattestStoryFragment;
    private PicsFragment mPicsFragment;

    private AbstractFragment[] fragments;

    private int focusFragmentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("Main Activity Created");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
//            replaceLoadRootFragment(R.id.offers_fragment,mNewsFragment,false);
//            loadRootFragment(R.id.offers_fragment, mNewsFragment);
            mNewsFragment = TopStoryFragment.newInstance(TOP_STORY);
            mBestStoryFragment = BestStoryFragment.newInstance(BEST_STORY);
            mLattestStoryFragment = NewStoryFragment.newInstance(NEW_STORY);
            mPicsFragment = PicsFragment.newInstance(PIC);
            loadMultipleRootFragment(R.id.offers_fragment,0,mNewsFragment,mBestStoryFragment,mLattestStoryFragment,mPicsFragment);
        }else
        {
            mNewsFragment = findFragment(TopStoryFragment.class);
            mBestStoryFragment = findFragment(BestStoryFragment.class);
            mLattestStoryFragment = findFragment(NewStoryFragment.class);
            mPicsFragment = findFragment(PicsFragment.class);
        }

        fragments = new AbstractFragment[4];
        fragments[0] = mNewsFragment;
        fragments[1] = mBestStoryFragment;
        fragments[2] = mLattestStoryFragment;
        fragments[3] = mPicsFragment;
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        attachFragment(createFragment(focusFragmentIndex));

        Timber.d("Main Activity Resumed");
    }


//    private AbstractFragment createFragment(int type) {
//        // change column count according fo screen orientation
//        switch (type)
//        {
//            case TOP_STORY:
//                if (null == mNewsFragment)
//                {
//                    mNewsFragment = NewsFragment.newInstance(type);
//                }
//                return mNewsFragment;
//            case NEW_STORY:
//                if (null == mLattestStoryFragment)
//                {
//                    mLattestStoryFragment = NewsFragment.newInstance(type);
//                }
//                return mLattestStoryFragment;
//            case BEST_STORY:
//                if (null == mBestStoryFragment)
//                {
//                    mBestStoryFragment = NewsFragment.newInstance(type);
//                }
//                return mBestStoryFragment;
//            case PIC:
//                {
//                    mPicsFragment = PicsFragment.newInstance(0);
//                }
//                return mPicsFragment;
//
//            default:
//                return null;
//        }
//
//
//    }

    private void attachFragment(AbstractFragment newsFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.offers_fragment, newsFragment, TAG_OFFERS_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListFragmentInteraction(Item news) {
        Intent detailsIntent = DetailActivity.getCallingIntent(context, news);
        ActivityCompat.startActivity(this, detailsIntent, null);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
//                attachFragment(createFragment(focusFragmentIndex));
                showHideFragment(fragments[TOP_STORY], fragments[focusFragmentIndex]);
                focusFragmentIndex = TOP_STORY;
                break;
            case R.id.action_settings:
                showHideFragment(fragments[NEW_STORY],fragments[focusFragmentIndex]);

                focusFragmentIndex = NEW_STORY;
//                attachFragment(createFragment(focusFragmentIndex));


                break;
            case R.id.action_navigation:
                showHideFragment(fragments[BEST_STORY],fragments[focusFragmentIndex]);

                focusFragmentIndex = BEST_STORY;
//                attachFragment(createFragment(focusFragmentIndex));

                break;
            case R.id.action_picture:
                showHideFragment(fragments[PIC],fragments[focusFragmentIndex]);
                focusFragmentIndex = PIC;
//                attachFragment(createFragment(focusFragmentIndex));

                break;
        }
        return true;
    }

    @Override
    public void onListFragmentInteraction(Picture offer) {
//        Intent detailsIntent = DetailActivity.getCallingIntent(context, news);
//        ActivityCompat.startActivity(this, detailsIntent, null);
    }

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, MainActivity.class);
        return callingIntent;
    }
}
