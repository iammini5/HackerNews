package com.yzeng.hackernews.test.robolectric.view.fragment;

import android.content.Intent;
import android.view.View;

import com.hackernews.api.Item;
import com.yzeng.hackernews.BuildConfig;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.test.robolectric.support.ShadowSnackbar;
import com.yzeng.hackernews.view.activity.DetailActivity;
import com.yzeng.hackernews.view.fragment.DetailFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import java.util.Arrays;
import java.util.List;
import static com.yzeng.hackernews.test.robolectric.support.Assert.assertViewIsNotVisible;
import static com.yzeng.hackernews.test.robolectric.support.Assert.assertViewIsVisible;
import static com.yzeng.hackernews.test.robolectric.support.ViewLocator.getView;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class DetailFragmentRobolectricTest {
    public static final String TEST_NEW_TITLE = "this is a example for test";

    private DetailActivity activity;
    private DetailFragment fragment;
    private Item expectedResult;

    @Before
    public void setUp() throws Exception {

        // put the test offer in a test api result

        Integer[] kidArray = {121212,121213,121214,121215};

        List<Integer> kids = Arrays.asList(kidArray);
        expectedResult = new Item();
        expectedResult.setBy("yi");
        expectedResult.setId(121212);
        expectedResult.setKids(kids);
        expectedResult.setTitle(TEST_NEW_TITLE);
        // setup activity
        // Launch the Detail activity
        Intent launchIntent = DetailActivity.getCallingIntent(RuntimeEnvironment.application,
                expectedResult);

        activity = Robolectric.buildActivity(DetailActivity.class)
                .withIntent(launchIntent).setup().get();

        assertNotNull(activity);

        // setup fragment
        fragment = (DetailFragment) activity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(fragment);

    }

    @Test
    public void testShowProgress() throws Exception {
        fragment.showProgress();

        View progress = getView(fragment, R.id.information_layout_loading_bar);
        assertViewIsVisible(progress);

        View noContent = getView(fragment, R.id.information_layout_error_layout);
        assertViewIsNotVisible(noContent);
    }

    @Test
    public void testHideProgress() throws Exception {
        fragment.hideProgress();

        View progress = getView(fragment, R.id.information_layout_loading_bar);
        assertViewIsNotVisible(progress);
    }

    @Test
    public void testShowOfflineMessage() throws Exception {
        fragment.showOfflineMessage();
        View errorContainer = getView(fragment, R.id.information_layout_error_layout);
        assertViewIsVisible(errorContainer);
        View noInternet = getView(fragment, R.id.information_layout_tv_error);
        assertViewIsVisible(noInternet);
    }


    @Test
    public void testOnDestroy() throws Exception {
        fragment.onDetach();
        assertNull(fragment.userDetailsPresenter);
    }

}