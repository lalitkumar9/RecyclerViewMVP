package com.cs.recyclerviewmvp.network.api;

import com.cs.recyclerviewmvp.network.response.CountryFeedResponseMessage;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Vijay Patel on 29/11/18.
 */

public interface MiddlewareService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<CountryFeedResponseMessage>
    getCountryFeeds();
}
