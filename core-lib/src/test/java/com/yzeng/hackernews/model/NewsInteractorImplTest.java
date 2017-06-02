package com.yzeng.hackernews.model;

import com.hackernews.api.Item;
import com.yzeng.hackernews.client.HackerNewsApi;
import com.yzeng.hackernews.util.ItemPair;
import com.yzeng.hackernews.util.SchedulerProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NewsInteractorImplTest {

    NewsInteractor interactor;
    HackerNewsApi api;
    SchedulerProvider scheduler;
    String[] expectedIndex;
    Item expectedResult;
    ItemPair<Item, List<Item>> expectNewsPair;

    @Before
    public void setup() {
        api = mock(HackerNewsApi.class);
        scheduler = mock(SchedulerProvider.class);

        // Set up the stub we want to return in the mock

        expectedIndex = new String[1];
        expectedIndex[0] = "1212";

        // put the test offer in a test api result
        expectedResult = new Item();
        expectedResult.setId(12);

        Integer[] array = {1212,1213,1214,1215};
        expectedResult.setKids(Arrays.asList(array) );

        List<Item> lists = new ArrayList<Item>();

        for(int i = 0; i<4; i++)
        {
            Item temp = new Item();
            temp.setId(13);
            temp.setBy("YI");
            temp.setText("Test" + i);
            temp.setKids(Arrays.asList(array));
            lists.add(expectedResult);
        }

        expectNewsPair = new ItemPair<>(expectedResult, lists);


        // mock scheduler to run immediately
        when(scheduler.mainThread())
                .thenReturn(Schedulers.immediate());
        when(scheduler.backgroundThread())
                .thenReturn(Schedulers.immediate());

        // mock api result with expected result
        when(api.getItem(any(String.class)))
                .thenReturn(Observable.just(expectedResult));

        // mock api result with expected result
        when(api.listItemIndex())
                .thenReturn(Observable.just(expectedIndex));

        // create a real new interactor using mocked api and scheduler
        interactor = new NewsInteractorImpl(api, scheduler,5,5);
    }

    @Test
    public void testLoadTopNews() throws Exception {
        TestSubscriber<String[]> testSubscriber = new TestSubscriber<>();

        // call interactor with some random params
        interactor.loadTopNews()
                .subscribe(testSubscriber);

        // it must return the expectedResult with no error
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expectedIndex));
    }

    @Test
    public void testLoadNewsItem() throws Exception {
        TestSubscriber<Item> testSubscriber = new TestSubscriber<>();

        // call interactor with some random params
        interactor.loadNews("1223")
                .subscribe(testSubscriber);

        // it must return the expectedResult with no error
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expectedResult));
    }

    @Test
    public void testLoadNewsAndComments() throws Exception {
        TestSubscriber<ItemPair<Item, List<Item>>> testSubscriber = new TestSubscriber<>();

        // call interactor with some random params
        interactor.loadNewsWithComments(expectedResult)
                .subscribe(testSubscriber);

        // it must return the expectedResult with no error
        testSubscriber.assertNoErrors();
        Assert.assertEquals(4, testSubscriber.getValueCount());

        Assert.assertEquals(expectedResult, testSubscriber.getOnNextEvents().get(0).getKey());
        Assert.assertEquals(expectedResult, testSubscriber.getOnNextEvents().get(1).getKey());
        Assert.assertEquals(expectedResult, testSubscriber.getOnNextEvents().get(2).getKey());
        Assert.assertEquals(expectedResult, testSubscriber.getOnNextEvents().get(3).getKey());
    }

}
