package com.yzeng.hackernews.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.hackernews.api.Item;
import com.yzeng.hackernews.presenter.MainPresenter;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.di.module.MainModule;
import com.yzeng.hackernews.view.MainView;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import javax.inject.Inject;
import butterknife.BindView;
import timber.log.Timber;


public class MainActivity extends BaseActivity implements MainView, NewsFragment.OnListFragmentInteractionListener {

    public static final String TAG_OFFERS_FRAGMENT = "offers_fragment";

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

    private NewsFragment mNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("Main Activity Created");
        setContentView(R.layout.activity_main);
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
        createFragment();
        attachFragment();
        Timber.d("Main Activity Resumed");
    }


    private void createFragment() {
        // change column count according fo screen orientation
        if(null == mNewsFragment)
        {
            mNewsFragment = NewsFragment.newInstance();
        }

    }

    private void attachFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.offers_fragment, mNewsFragment, TAG_OFFERS_FRAGMENT);
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
        Intent detailsIntent = DetailActivity.getCallingIntent(context,news);
        ActivityCompat.startActivity(this, detailsIntent,null);
    }

}
