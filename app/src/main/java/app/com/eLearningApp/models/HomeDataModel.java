package app.com.eLearningApp.models;

public class HomeDataModel {
    private String playlistName;
    private String imageURL;
    private String playListId;



    public HomeDataModel(String playlistName, String imageURL, String playListId) {
        this.playlistName = playlistName;
        this.imageURL = imageURL;
        this.playListId=playListId;
    }

    public HomeDataModel() {

    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPlayListId() {
        return playListId;
    }

    public void setPlayListId(String playListId) {
        this.playListId = playListId;
    }
}

