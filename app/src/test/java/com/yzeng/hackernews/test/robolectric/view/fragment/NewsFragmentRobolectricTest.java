package com.yzeng.hackernews.test.robolectric.view.fragment;

import android.view.View;

import com.yzeng.hackernews.BuildConfig;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.test.robolectric.support.ShadowSnackbar;
import com.yzeng.hackernews.view.activity.MainActivity;
import com.yzeng.hackernews.view.fragment.NewsFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.yzeng.hackernews.test.robolectric.support.Assert.assertSnackbarIsShown;
import static com.yzeng.hackernews.test.robolectric.support.Assert.assertViewIsNotVisible;
import static com.yzeng.hackernews.test.robolectric.support.Assert.assertViewIsVisible;
import static com.yzeng.hackernews.test.robolectric.support.ViewLocator.getView;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class NewsFragmentRobolectricTest {

    private MainActivity activity;
    private NewsFragment fragment;

    @Before
    public void setUp() throws Exception {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = (NewsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.offers_fragment);
        assertNotNull(fragment);

    }

    @Test
    public void testShowProgress() throws Exception {
        fragment.showProgress();

        View progress = getView(fragment, R.id.progress_more);
        assertViewIsVisible(progress);


        View noInternet = getView(fragment, R.id.no_internet);
        assertViewIsNotVisible(noInternet);

        View noContent = getView(fragment, R.id.no_content);
        assertViewIsNotVisible(noContent);

        View networkError = getView(fragment, R.id.network_error);
        assertViewIsNotVisible(networkError);
    }

    @Test
    public void testHideProgress() throws Exception {
        fragment.hideProgress();

        View progress = getView(fragment, R.id.progress_more);
        assertViewIsNotVisible(progress);
    }

    @Test
    public void testShowOfflineMessage() throws Exception {
        fragment.showOfflineMessage();
        assertSnackbarIsShown(R.string.offline_message);
        View noInternet = getView(fragment, R.id.no_internet);
        assertViewIsVisible(noInternet);
    }


    @Test
    public void testOnDestroy() throws Exception {
        fragment.onDetach();

        assertNull(fragment.presenter);
    }

}