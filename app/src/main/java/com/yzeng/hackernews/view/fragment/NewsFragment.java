package com.yzeng.hackernews.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hackernews.api.Item;
import com.yzeng.hackernews.presenter.NewsListPresenter;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.di.component.ApplicationComponent;
import com.yzeng.hackernews.di.component.NewsSubComponent;
import com.yzeng.hackernews.di.module.AppNewsModule;
import com.yzeng.hackernews.util.AppConstants;
import com.yzeng.hackernews.util.ItemSpaceDecoration;
import com.yzeng.hackernews.view.NewsView;
import com.yzeng.hackernews.view.activity.BaseActivity;
import com.yzeng.hackernews.view.adapter.NewsRecyclerViewAdapter;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;


public class NewsFragment extends BaseFragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    public NewsListPresenter presenter;
    @Inject
    Context context;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    NewsRecyclerViewAdapter adapter;
    @Inject
    OnListFragmentInteractionListener listener;
    @Inject
    BaseActivity mainActivity;

    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.no_internet)
    View noInternet;
    @BindView(R.id.network_error)
    View networkError;
    @BindView(R.id.no_content)
    View noContent;
    @BindView(R.id.progress_more)
    View progressMore;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private NewsSubComponent newsSubComponent;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.no_internet)
    void onNoInternetClick() {
        loadOffersData();
    }

    @OnClick(R.id.no_content)
    void onNoContentClick() {
        loadOffersData();
    }

    @OnClick(R.id.network_error)
    void onNetworkErrorClick() {
        loadOffersData();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        subscriptions.add(
                adapter.asObservable()
                        .filter(offer -> null != listener)
                        .subscribe(listener::onListFragmentInteraction)
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        ButterKnife.bind(this, view);

        // add material margins to list items card view
        recyclerView.addItemDecoration(new ItemSpaceDecoration(AppConstants.RECYCLER_VIEW_ITEM_SPACE));

        // allow pull to refresh on list
        swipeRefresh.setOnRefreshListener(this);

        // load data for first run
        initRecyclerView();
        presenter.loadNewsListData();

        return view;
    }

    @Override
    protected void injectDependencies(ApplicationComponent component, Context context) {
        newsSubComponent = component
                .plus(new AppNewsModule(context, this));
        newsSubComponent.inject(this);
    }

    @Override
    BaseActivity getBaseActivity() {
        return mainActivity;
    }

    public NewsSubComponent getDependenciesModules()
    {
        return newsSubComponent;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        presenter.destroy();
        presenter = null;
        subscriptions.unsubscribe();
    }

    @Override
    public void showProgress() {
        progressMore.setVisibility(View.VISIBLE);
        noInternet.setVisibility(View.GONE);
        networkError.setVisibility(View.GONE);
        noContent.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
        progressMore.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        getBaseActivity().showMessage(message);
    }

    @Override
    public void showOfflineMessage() {
        getBaseActivity().showOfflineMessage();

        if (adapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }
    }

    private void loadOffersData() {
        adapter.clearNews();
        presenter.loadNewsListData();
    }

    @Override
    public void setNewsValue(String[] news) {
        Timber.d("Loaded Offers: %s", news.toString());

        if (adapter.getItemCount() == 0) {
            adapter.setNews(news);
            initRecyclerView();
        } else {
            adapter.addMoreNews(news);
            adapter.notifyDataSetChanged();
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        loadOffersData();
    }

    public interface OnListFragmentInteractionListener {

        void onListFragmentInteraction(Item offer);

    }
}
