package com.example.question2.model;

import com.squareup.moshi.Json;

public class Global {

    @Json(name = "NewConfirmed")
    private Integer newConfirmed;
    @Json(name = "TotalConfirmed")
    private Integer totalConfirmed;
    @Json(name = "NewDeaths")
    private Integer newDeaths;
    @Json(name = "TotalDeaths")
    private Integer totalDeaths;
    @Json(name = "NewRecovered")
    private Integer newRecovered;
    @Json(name = "TotalRecovered")
    private Integer totalRecovered;

    public Integer getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(Integer newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(Integer totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public Integer getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Integer newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(Integer newRecovered) {
        this.newRecovered = newRecovered;
    }

    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

}