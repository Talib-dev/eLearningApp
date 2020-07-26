package app.com.eLearningApp.models;

import com.google.gson.annotations.SerializedName;

public class StandardThumbnail {
        @SerializedName("url")
        private String url;

        public StandardThumbnail(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

}
