package com.example.question2.model;

import java.util.List;
import com.squareup.moshi.Json;

public class Covid {

    @Json(name = "Global")
    private Global global;
    @Json(name = "Countries")
    private List<Country> countries = null;

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}