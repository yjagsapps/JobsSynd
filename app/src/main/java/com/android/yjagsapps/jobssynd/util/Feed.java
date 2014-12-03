package com.android.yjagsapps.jobssynd.util;

/**
 * Created by Jags on 12/2/2014.
 */
public class Feed {
    private int Id;
    private String name;
    private String URL;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
