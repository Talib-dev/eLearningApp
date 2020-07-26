package app.com.eLearningApp.models;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {
    @SerializedName("standard")
    private StandardThumbnail standardThemnail;
    @SerializedName("default")
    private StandardThumbnail defaultThemnail;
    @SerializedName("medium")
    private StandardThumbnail mediumThemnail;
    @SerializedName("high")
    private StandardThumbnail highThemnail;


    public Thumbnails(StandardThumbnail standardThemnail, StandardThumbnail defaultThemnail, StandardThumbnail mediumThemnail, StandardThumbnail highThemnail) {
        this.standardThemnail = standardThemnail;
        this.defaultThemnail = defaultThemnail;
        this.mediumThemnail = mediumThemnail;
        this.highThemnail = highThemnail;
    }

    public StandardThumbnail getDefaultThemnail() {
        return defaultThemnail;
    }

    public StandardThumbnail getMediumThemnail() {
        return mediumThemnail;
    }

    public StandardThumbnail getHighThemnail() {
        return highThemnail;
    }

    public StandardThumbnail getStandardThemnail() {
        return standardThemnail;
    }


}
