package com.example.android.quakereport;

/**
 * Created by Tsultrim on 5/19/18.
 */

public class Earthquake {

    private Double mag ;
    private String location;
    private Long date;
    private String url;


    public Earthquake(Double mag, String location, Long date, String url) {
        this.mag = mag;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    public Double getMag() {
        return mag;
    }

    public String getLocation() {
        return location;
    }

    public Long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
