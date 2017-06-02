package com.yzeng.hackernews.test.robolectric.view.activity;

import com.yzeng.hackernews.BuildConfig;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.test.robolectric.support.ShadowSnackbar;
import com.yzeng.hackernews.view.activity.MainActivity;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import static com.yzeng.hackernews.test.robolectric.support.Assert.assertSnackbarIsShown;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class MainActivityRobolectricTest {

    final static String TEST_TEXT = "This is a test text.";
    private MainActivity activity;

    NewsFragment homeFragment;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);

        assertNotNull(activity);

        homeFragment = (NewsFragment)activity
                .getSupportFragmentManager().findFragmentById(R.id.offers_fragment);

        Assert.assertNotNull(homeFragment);

    }

    @Test
    public void testShowToastMessage() throws Exception {
        activity.showMessage(TEST_TEXT);

        assertThat(TEST_TEXT, equalTo(ShadowToast.getTextOfLatestToast()));
    }

    @Test
    public void testShowInternetConnectionError() throws Exception {
        activity.showOfflineMessage();
        assertSnackbarIsShown(R.string.offline_message);
    }
}
