package com.example.muheda.loadandshare.model;

/**
 * Created by 13660 on 2018/11/7.
 */

public class ShareContent {
    private String url;
    private String title;
    private String description;
    private int drawId;

    public String getTitle() {
        return title;
    }

    public ShareContent setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShareContent setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getDrawId() {
        return drawId;
    }

    public ShareContent setDrawId(int drawId) {
        this.drawId = drawId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ShareContent setUrl(String url) {
        this.url = url;
        return this;
    }
}
