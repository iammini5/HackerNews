package com.yzeng.hackernews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hackernews.api.Item;
import com.yzeng.hackernews.R;

import java.util.ArrayList;
import java.util.List;


public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentViewHolder>{

    private List<Item> news = new ArrayList<>();

    public CommentsRecyclerViewAdapter() {
    }

    public synchronized void addNewsAndComments(Item parent, List<Item> comments)
    {
        parent.setLevel(1);
        this.news.add(parent);
        this.news.addAll(comments);
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_comment, parent, false);

        CommentViewHolder newViewHolder = new CommentViewHolder(view);
        newViewHolder.setIsRecyclable(false);

        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, int position) {
        final Item comment = news.get(position);
        holder.setValue(comment);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void clearNews() {
        news = new ArrayList<>();
    }
}
