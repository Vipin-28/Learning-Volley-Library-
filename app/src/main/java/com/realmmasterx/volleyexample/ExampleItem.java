package com.realmmasterx.volleyexample;

public class ExampleItem {
    private String ImageUrl;
    private String mCreator;
    private int mLikes;

    public ExampleItem(String imageUrl, String mCreator, int mLikes) {
        ImageUrl = imageUrl;
        this.mCreator = mCreator;
        this.mLikes = mLikes;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

    public int getLikes() {
        return mLikes;
    }
}
