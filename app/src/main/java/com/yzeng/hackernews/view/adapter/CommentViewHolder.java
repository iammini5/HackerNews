package com.yzeng.hackernews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackernews.api.Item;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CommentViewHolder extends RecyclerView.ViewHolder{

    Item comment;
    public final View view;

    @BindView(R.id.layout_item_title)
    TextView commentTitle;
    @BindView(R.id.layout_item_description)
    TextView commentDescription;

    @BindView(R.id.layout_comment_item_divider)
    View divider;

    public CommentViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        this.view = view;
    }


    public void setValue(Item newItem) {
        String title = Item.getCommentTitle(newItem);
        Spanned description = Utils.getTextCommentHtml(newItem);
        comment = newItem;
        commentTitle.setText(title);
        commentDescription.setText(description);
        if(newItem.getLevel()!=1)
        {
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(80,
                    view.getContext().getResources().getDimensionPixelSize(R.dimen._5dp),
                    view.getContext().getResources().getDimensionPixelSize(R.dimen._5dp),
                    view.getContext().getResources().getDimensionPixelSize(R.dimen._5dp));
            view.setLayoutParams(buttonLayoutParams);
        }else
        {
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(view.getContext().getResources().getDimensionPixelSize(R.dimen._5dp) ,
                    view.getContext().getResources().getDimensionPixelSize(R.dimen._5dp),
                    view.getContext().getResources().getDimensionPixelSize(R.dimen._5dp),
                    view.getContext().getResources().getDimensionPixelSize(R.dimen._5dp));
        }
    }

}
