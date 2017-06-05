package com.yzeng.hackernews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackernews.api.Item;
import com.jakewharton.rxbinding.view.RxView;
import com.splashbase.api.Picture;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.di.module.AdapterModule;
import com.yzeng.hackernews.presenter.NewsBasePresenter;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import com.yzeng.hackernews.view.fragment.PicsFragment;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;


public class PicsRecyclerViewAdapter extends RecyclerView.Adapter<PicViewHolder> {

    private ArrayList<Picture> news = new ArrayList<>();

    private PublishSubject<Picture> notify = PublishSubject.create();

    private PicsFragment picsFragment;

    @Inject
    public NewsBasePresenter presenter;

    @Inject
    public PicsRecyclerViewAdapter(PicsFragment newsFragment) {
        this.picsFragment = newsFragment;
    }

    public void setNews(Picture[] news) {
        this.news = new ArrayList<Picture>(Arrays.asList(news));
        notifyDataSetChanged();
    }

    public void addMoreNews(Picture[] news) {
        this.news.addAll(new ArrayList<Picture>(Arrays.asList(news)));
    }

    @Override
    public PicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_pic, parent, false);

        PicViewHolder newViewHolder = new PicViewHolder(view );
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(final PicViewHolder holder, int position) {

        final Picture newIndex = news.get(position);
        holder.setValue(newIndex);
        RxView.clicks(holder.view)
                .map(aVoid -> holder.item)
                .subscribe(notify::onNext);
    }

    public Observable<Picture> asObservable() {
        return notify.asObservable();
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void clearNews() {
        news = new ArrayList<>();
        notifyDataSetChanged();
    }
}
