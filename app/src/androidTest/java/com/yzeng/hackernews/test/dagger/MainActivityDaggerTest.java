package com.yzeng.hackernews.test.dagger;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hackernews.api.Item;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.client.HackerNewsApi;
import com.yzeng.hackernews.test.dagger.di.component.TestApplicationComponent;
import com.yzeng.hackernews.test.dagger.utils.RecyclerViewMatcher;
import com.yzeng.hackernews.test.dagger.utils.Utils;
import com.yzeng.hackernews.view.activity.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityDaggerTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false);   // do not launch the activity immediately

    @Inject
    HackerNewsApi api;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MockHackerNewsApplication app = (MockHackerNewsApplication) instrumentation.getTargetContext().getApplicationContext();
        TestApplicationComponent component = (TestApplicationComponent) app.getComponent();
        component.inject(this);

    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void shouldBeAbleToSeeNews() {
        // Launch the activity
        mainActivity.launchActivity(new Intent());
        onView(withRecyclerView(R.id.list).atPosition(0))
                .check(matches(hasDescendant(withText(Utils.getItemStoryWithComments().getTitle()))));

        onView(withRecyclerView(R.id.list).atPosition(0))
                .check(matches(hasDescendant(withText(Item.getDescription(Utils.getItemStoryWithComments())))));

        onView(withRecyclerView(R.id.list).atPosition(1))
                .check(matches(hasDescendant(withText(Utils.getItemStoryWithComments().getTitle()))));

        onView(withRecyclerView(R.id.list).atPosition(1))
                .check(matches(hasDescendant(withText(Item.getDescription(Utils.getItemStoryWithComments())))));

        onView(withRecyclerView(R.id.list).atPosition(2))
                .check(matches(hasDescendant(withText(Utils.getItemStoryWithComments().getTitle()))));

        onView(withRecyclerView(R.id.list).atPosition(2))
                .check(matches(hasDescendant(withText(Item.getDescription(Utils.getItemStoryWithComments())))));

        onView(withRecyclerView(R.id.list).atPosition(3))
                .check(matches(hasDescendant(withText(Utils.getItemStoryWithComments().getTitle()))));

        onView(withRecyclerView(R.id.list).atPosition(3))
                .check(matches(hasDescendant(withText(Item.getDescription(Utils.getItemStoryWithComments())))));
    }

    @Test
    public void shouldBeAbleToLaunchCommentsFromNews() {
        mainActivity.launchActivity(new Intent());
        onView(withRecyclerView(R.id.list).atPosition(0)).perform(click());
        onView(withId(R.id.information_layout_tv_msg)).check(matches(withText(Utils.getItemStoryWithComments().getTitle())));

    }
}

