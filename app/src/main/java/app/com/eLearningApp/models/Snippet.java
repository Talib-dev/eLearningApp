package app.com.eLearningApp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Snippet implements Parcelable {
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("channelId")
    private String channelId;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("thumbnails")
    Thumbnails thumbnails;
    @SerializedName("channelTitle")
    private String string;
    @SerializedName("playlistId")
    private String playlistId;
    @SerializedName("position")
    private int position;
    @SerializedName("resourceId")
    private ResourceId resourceId;

    public Snippet(String publishedAt, String channelId, String title,
                   String description,Thumbnails thumbnails, String string, String playlistId,
                   int position, ResourceId resourceId) {
        this.publishedAt = publishedAt;
        this.channelId = channelId;
        this.title = title;
        this.description = description;
        this.thumbnails=thumbnails;
        this.string = string;
        this.playlistId = playlistId;
        this.position = position;
        this.resourceId = resourceId;
    }


    protected Snippet(Parcel in) {
        publishedAt = in.readString();
        channelId = in.readString();
        title = in.readString();
        description = in.readString();
        string = in.readString();
        playlistId = in.readString();
        position = in.readInt();
    }

    public static final Creator<Snippet> CREATOR = new Creator<Snippet>() {
        @Override
        public Snippet createFromParcel(Parcel in) {
            return new Snippet(in);
        }

        @Override
        public Snippet[] newArray(int size) {
            return new Snippet[size];
        }
    };

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ResourceId getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publishedAt);
        dest.writeString(channelId);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(string);
        dest.writeString(playlistId);
        dest.writeInt(position);
    }
}
