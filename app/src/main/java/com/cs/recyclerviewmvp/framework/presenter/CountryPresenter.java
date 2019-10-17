package com.cs.recyclerviewmvp.framework.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.cs.recyclerviewmvp.activities.MainActivity;
import com.cs.recyclerviewmvp.framework.components.DaggerSharedPrerenceComponent;
import com.cs.recyclerviewmvp.model.CountryModel;
import com.cs.recyclerviewmvp.modules.SharedPreferenceModule;
import com.cs.recyclerviewmvp.network.api.MiddlewareService;
import com.cs.recyclerviewmvp.network.response.CountryFeedResponseMessage;
import com.cs.recyclerviewmvp.utils.Constants;
import com.cs.recyclerviewmvp.utils.LocalUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vijay Patel on 29/11/18.
 */

public class CountryPresenter {
    private MainActivity mView;
    private Retrofit retrofit;
    private SharedPreferences mSharedPreferences;

    public CountryPresenter(MainActivity view, Retrofit retrofit) {
        this.mView = view;
        this.retrofit = retrofit;
        // Dagger%COMPONENT_NAME%
        mSharedPreferences = DaggerSharedPrerenceComponent.builder()
                .sharedPreferenceModule(new SharedPreferenceModule(mView.getApplication()))
                .build().getSharedPreference();
    }

    public void loadPosts(final boolean isRefresh) {
        if(LocalUtils.isConnected(mView)){
            retrofit.create(MiddlewareService.class).getCountryFeeds().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CountryFeedResponseMessage>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(CountryPresenter.class.getName(), e.toString());
                            mView.displayErrorSnackbar();
                        }

                        @Override
                        public void onNext(CountryFeedResponseMessage data) {
                            saveFeedToSharePreference(data.getRows());
                            loadFeeds(isRefresh, data.getRows());
                            loadToolbarTitle(data.getTitle());
                        }
                    });
        }else{
            List<CountryModel> events = getDataFromSharedPreferences();
            if(events == null){
                LocalUtils.buildNoNetworkDialog(mView).show();
                mView.displayErrorSnackbar();
                mView.hideProgress();
            }else{
                loadFeeds(isRefresh, events);
            }
        }
    }

    private void loadToolbarTitle(String title) {
        mView.setToolbarTitle(title);
    }

    public void loadFeeds(boolean isRefresh, List<CountryModel> data){
        mView.hideProgress();
        if (isRefresh) {
            mView.refreshPosts(data);
        } else {
            mView.addPosts(data);
        }
    }

    public void saveFeedToSharePreference(List<CountryModel> data){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();

        String json = gson.toJson(data);

        editor.putString(Constants.FEED_KEY, json);
        editor.apply();
    }

    private List<CountryModel> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(Constants.FEED_KEY, null);
        Type type = new TypeToken<ArrayList<CountryModel>>() {}.getType();
        ArrayList<CountryModel> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
}