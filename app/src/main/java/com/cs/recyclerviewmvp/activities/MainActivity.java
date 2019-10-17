package com.cs.recyclerviewmvp.activities;

import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.cs.recyclerviewmvp.MyApplication;
import com.cs.recyclerviewmvp.R;
import com.cs.recyclerviewmvp.adapters.CountryFeedAdapter;
import com.cs.recyclerviewmvp.framework.presenter.CountryPresenter;
import com.cs.recyclerviewmvp.framework.view.OnRecyclerViewClickListener;
import com.cs.recyclerviewmvp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

/**
 * Created by Vijay Patel on 29/11/18.
 */

public class MainActivity extends AppCompatActivity implements OnRecyclerViewClickListener {

    @BindView(R.id.tb_main)
    Toolbar toolbar;

    @BindView(R.id.content_swipe_refresh_main)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.pb_main)
    ProgressBar mProgressBar;

    @BindView(R.id.recycler_main)
    RecyclerView mRecyclerView;

    @Inject
    Retrofit retrofit;

    CountryFeedAdapter mCountryFeedAdapter;
    CountryPresenter mCountryPresenter;
    boolean isLoading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getNetComponent().inject(this);

        toolbar.setTitle(getString(R.string.app_name));
        initializeRecyclerView();
        showProgress();
        initializeAdapter();

        mCountryPresenter = new CountryPresenter(this, retrofit);
        mCountryPresenter.loadPosts(false);
        initializeRefreshLayout();
    }

    public void setToolbarTitle(String title){
        toolbar.setTitle(title);
    }

    private void initializeRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        mRecyclerView.setHasFixedSize(false);
    }

    public void initializeAdapter() {
        mCountryFeedAdapter = new CountryFeedAdapter(this, new ArrayList<CountryModel>(), this);
        mRecyclerView.setAdapter(mCountryFeedAdapter);
    }

    public void initializeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCountryPresenter.loadPosts(true);
            }
        });
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setVisibility(View.INVISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    public void displayErrorSnackbar() {
        final View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                mCountryPresenter.loadPosts(false);
            }
        };
        final View coordinatorLayoutView = findViewById(R.id.coordinator_layout_main);
        Snackbar
                .make(coordinatorLayoutView, R.string.error_load_post_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snackbar_action_retry, clickListener)
                .show();
    }

    public void addPosts(List<CountryModel> posts) {
        mCountryFeedAdapter.addAllData(posts);
        mCountryFeedAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    public void refreshPosts(List<CountryModel> posts) {
        mCountryFeedAdapter.clearData();
        mCountryFeedAdapter.addAllData(posts);
        mCountryFeedAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
        isLoading = false;
    }

    @Override
    public void onRecyclerViewClick(Object clickedObject, View clickedImage) {
        //Recycler List Item Click
    }
}
