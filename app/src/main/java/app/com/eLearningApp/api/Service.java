package app.com.eLearningApp.api;
import app.com.eLearningApp.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("playlistItems")
    Call<Response> getResponse(@Query("part") String part, @Query("playlistId") String playlistId,
                               @Query("maxResults") int maxResults, @Query("key") String key);

}
