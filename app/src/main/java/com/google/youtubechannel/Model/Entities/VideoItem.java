package com.google.youtubechannel.Model.Entities;

import java.io.Serializable;

public class VideoItem implements Serializable {

    private String VideoId;
    private String Title;
    private String PublishedAt;
    private String Url;


    public VideoItem(String videoId , String Title , String url , String PublishedAt) {
        this.VideoId=videoId;
        this.PublishedAt=PublishedAt;
        this.Url=url;
        this.Title=Title;

    }

    public String getVideoId() {
        return VideoId;
    }

    public VideoItem setVideoId(String videoId) {
        VideoId = videoId;
        return this;
    }

    public String getPublishedAt() {
        return PublishedAt;
    }

    public String getUrl() {
        return Url;
    }

    public VideoItem setUrl(String url) {
        Url = url;
        return this;
    }

    public VideoItem setPublishedAt(String publishedAt) {
        PublishedAt = publishedAt;
        return this;
    }

    public String getTitle() {
        return Title;
    }

    public VideoItem setTitle(String title) {
        Title = title;
        return this;
    }
}
