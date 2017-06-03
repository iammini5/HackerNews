package com.splashbase.api;


import com.google.gson.annotations.SerializedName;

public class PictureSet {

    @SerializedName("images")
    public Picture[] images;

    public PictureSet() {
    }

    public Picture[] getImages() {
        return images;
    }

    public void setImages(Picture[] images) {
        this.images = images;
    }


}
