package com.google.youtubechannel.Model.Entities;


public class ChannelItem {
    private String Title;
    private String Image;
    private String Id;

    public ChannelItem(String Title , String Image , String Id) {

        this.Image=Image;
        this.Id=Id;
        this.Title= Title;
    }

    public String getId() {
        return Id;
    }

    public ChannelItem setId(String id) {
        Id = id;
        return this;
    }

    public String getImage() {
        return Image;
    }

    public ChannelItem setImage(String image) {
        Image = image;
        return this;
    }

    public String getTitle() {
        return Title;
    }

    public ChannelItem setTitle(String title) {
        Title = title;
        return this;
    }
}
