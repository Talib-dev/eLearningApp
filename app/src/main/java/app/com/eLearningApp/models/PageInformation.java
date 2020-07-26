package app.com.eLearningApp.models;

import com.google.gson.annotations.SerializedName;

public class PageInformation {
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("resultsPerPage")
    private int resultsPerPage;

    public PageInformation(int totalResults, int resultsPerPage) {
        this.totalResults = totalResults;
        this.resultsPerPage = resultsPerPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
