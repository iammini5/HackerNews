package com.yzeng.hackernews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackernews.api.Item;
import com.jakewharton.rxbinding.view.RxView;
import com.yzeng.hackernews.presenter.NewsBasePresenter;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;
import rx.Observable;
import rx.subjects.PublishSubject;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewViewHolder> {

    private ArrayList<String> news = new ArrayList<>();

    private PublishSubject<Item> notify = PublishSubject.create();

    private NewsFragment newsFragment;

    @Inject
    public NewsBasePresenter presenter;

    @Inject
    public NewsRecyclerViewAdapter(NewsFragment newsFragment) {
        this.newsFragment = newsFragment;
    }

    public void setNews(String[] news) {
        this.news = new ArrayList<>(Arrays.asList(news));
    }

    public void addMoreNews(String[] news) {
        this.news.addAll(new ArrayList<>(Arrays.asList(news)));
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);

        NewViewHolder newViewHolder = new NewViewHolder(view, newsFragment );


        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(final NewViewHolder holder, int position) {

        final String newIndex = news.get(position);
        holder.presenter.fetchData(newIndex);
        RxView.clicks(holder.view)
                .map(aVoid -> holder.item)
                .subscribe(notify::onNext);
    }

    public Observable<Item> asObservable() {
        return notify.asObservable();
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void clearNews() {
        news = new ArrayList<>();
    }
}
