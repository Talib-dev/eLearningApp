package app.com.eLearningApp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("kind")
    private String kind;
    @SerializedName("etag")
    private String etag;
    @SerializedName("nextPageToken")
    private String nextPageToken;
    @SerializedName("items")
    private List<Video> items;
    @SerializedName("pageInfo")
    private PageInformation pageInfo;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Video> getItems() {
        return items;
    }

    public void setItems(List<Video> items) {
        this.items = items;
    }

    public PageInformation getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInformation pageInfo) {
        this.pageInfo = pageInfo;
    }
}
