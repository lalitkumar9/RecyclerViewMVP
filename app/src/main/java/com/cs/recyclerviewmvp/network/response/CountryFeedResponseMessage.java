package com.cs.recyclerviewmvp.network.response;

import com.cs.recyclerviewmvp.model.CountryModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Vijay Patel on 29/11/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryFeedResponseMessage {

    @JsonProperty("title")
    private String title;

    @JsonProperty("rows")
    private List<CountryModel> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CountryModel> getRows() {
        return rows;
    }

    public void setRows(List<CountryModel> rows) {
        this.rows = rows;
    }
}