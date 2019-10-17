package com.cs.recyclerviewmvp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vijay Patel on 29/11/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryModel {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageHref")
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
