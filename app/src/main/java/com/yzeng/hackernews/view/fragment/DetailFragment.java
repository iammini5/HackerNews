package com.yzeng.hackernews.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hackernews.api.Item;
import com.yzeng.hackernews.presenter.NewsItemPresenter;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.di.module.NewDetailModule;
import com.yzeng.hackernews.view.ItemView;
import com.yzeng.hackernews.view.activity.BaseActivity;
import com.yzeng.hackernews.view.adapter.CommentsRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.internal.Preconditions;

public class DetailFragment extends BaseFragment implements ItemView {
    private static final String PARAM_USER_ID = "param_user_id";

    @Inject
    public NewsItemPresenter userDetailsPresenter;
    @Inject
    CommentsRecyclerViewAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    BaseActivity DetailActivity;

    @BindView(R.id.information_layout_error_layout)
    LinearLayout errorContainer;
    @BindView(R.id.comment_list)
    RecyclerView recyclerView;
    @BindView(R.id.information_layout_tv_msg)
    TextView message;
    @BindView(R.id.information_layout_loading_bar)
    ProgressBar loadingBar;

    public static DetailFragment forUser(Item userId) {
        final DetailFragment userDetailsFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PARAM_USER_ID,userId);
        userDetailsFragment.setArguments(bundle);
        return userDetailsFragment;
    }

    public DetailFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    protected void injectDependencies(ApplicationComponent component, Context context) {
        component.plus(new NewDetailModule(context,this))
                .inject(this);
    }

    @Override
    BaseActivity getBaseActivity() {
        return DetailActivity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.view_information, container, false);
        ButterKnife.bind(this, fragmentView);


        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            this.loadUserDetails();
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        this.userDetailsPresenter.destroy();
        this.userDetailsPresenter = null;
    }

    public Context context() {
        return getActivity().getApplicationContext();
    }

    /**
     * Load user details.
     */
    private void loadUserDetails() {
        message.setText(currentUserId().getTitle());
        initRecyclerView();
        if (this.userDetailsPresenter != null) {
            userDetailsPresenter.loadNewsCommentsData(currentUserId());
        }
    }

    /**
     * Get current new item from fragments arguments.
     */
    private Item currentUserId() {
        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
        return (Item) arguments.getSerializable(PARAM_USER_ID);
    }

    @Override
    public void showProgress() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showComments(Item parent, List<Item> comments) {
        adapter.addNewsAndComments(parent, comments);
        adapter.notifyDataSetChanged();

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        getBaseActivity().showMessage(message);
    }

    @Override
    public void showOfflineMessage() {
        errorContainer.setVisibility(View.VISIBLE);
    }
}
