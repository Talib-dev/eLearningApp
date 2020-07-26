package app.com.eLearningApp.fragments;



import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.eLearningApp.DetailsActivity;
import app.com.eLearningApp.R;
import app.com.eLearningApp.adapters.VideoPostAdapter;
import app.com.eLearningApp.api.Client;
import app.com.eLearningApp.api.Service;
import app.com.eLearningApp.models.Response;
import app.com.eLearningApp.models.Snippet;
import app.com.eLearningApp.models.Video;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayListFragment extends Fragment {

//    private static String GOOGLE_YOUTUBE_API_KEY = "AIzaSyD9PI6uN74xfk6sfEll0q6VHihno0_UWrE";// key from devid18799 account
    private  String PLAYLIST_ID;//here you should use your playlist id for testing purpose you can use this app.com.youtubeapiv3.api also
    private RecyclerView mList_videos = null;
    private VideoPostAdapter adapter = null;
    Call<Response> responseCall;
    //    private ArrayList<YoutubeDataModel> mListData = new ArrayList<>();
    private List<Snippet> mList;
    public PlayListFragment(String PLAYLIST_ID) {
        this.PLAYLIST_ID=PLAYLIST_ID;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_play_list, container, false);
        mList_videos =  view.findViewById(R.id.mList_videos);
        mList=new ArrayList<>();
        initList();
        loadJSON();
        adapter.setClickListner(new VideoPostAdapter.ClickListner() {
            @Override
            public void onClick(int position) {
                Snippet object=mList.get(position);
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra("DATA", object.getTitle()+"%"+""+
                                object.getDescription()+"%"+
                                object.getPublishedAt()+"%"+
                                object.getResourceId().getVideoId()+"%"+
                                object.getPlaylistId());
                        intent.putExtra("DATAOBJ",  object);
                        view.getContext().startActivity(intent);
            }
        });
        return view;
    }
    private void loadJSON() {
        Service apiService = Client.getClient().create(Service.class);
        responseCall=apiService.getResponse("snippet",
                PLAYLIST_ID,20,
                "AIzaSyD9PI6uN74xfk6sfEll0q6VHihno0_UWrE");
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (!response.isSuccessful()) {
                    responseCall = call.clone();
                    responseCall.enqueue(this);
                    return;
                }
                if (response.body() == null) return;
                if (response.body().getItems() == null) return;
                for (Video tvShowBrief : response.body().getItems()) {
                    if (tvShowBrief.getSnippet()!=null){
                        Log.d("Tag",tvShowBrief.getSnippet().getTitle());
                        mList.add(tvShowBrief.getSnippet());
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void initList() {
        mList_videos.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new VideoPostAdapter(getActivity(), mList);
        mList_videos.setAdapter(adapter);
    }
//    //create an asynctask to get all the data from youtube
//    private class RequestYoutubeAPI extends AsyncTask<Void, String, String> {
//        private  String CHANNLE_GET_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="
//                + PLAYLIST_ID + "&maxResults=20&key=" + GOOGLE_YOUTUBE_API_KEY + "";
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpGet httpGet = new HttpGet(CHANNLE_GET_URL);
//            Log.e("URL", CHANNLE_GET_URL);
//            try {
//                HttpResponse response = httpClient.execute(httpGet);
//                HttpEntity httpEntity = response.getEntity();
//                String json = EntityUtils.toString(httpEntity);
//                return json;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String response) {
//            super.onPostExecute(response);
//            if (response != null) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.e("response", jsonObject.toString());
//                    mListData = parseVideoListFromResponse(jsonObject);
//                    initList(mListData);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public ArrayList<YoutubeDataModel> parseVideoListFromResponse(JSONObject jsonObject) {
//        ArrayList<YoutubeDataModel> mList = new ArrayList<>();
//
//        if (jsonObject.has("items")) {
//            try {
//                JSONArray jsonArray = jsonObject.getJSONArray("items");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject json = jsonArray.getJSONObject(i);
//                    if (json.has("kind")) {
//                        if (json.getString("kind").equals("youtube#playlistItem")) {
//                            YoutubeDataModel youtubeObject = new YoutubeDataModel();
//                            JSONObject jsonSnippet = json.getJSONObject("snippet");
//                            String vedio_id = "";
//                            if (jsonSnippet.has("resourceId")) {
//                                JSONObject jsonResource = jsonSnippet.getJSONObject("resourceId");
//                                vedio_id = jsonResource.getString("videoId");
//
//                            }
//                            String title = jsonSnippet.getString("title");
//                            String description = jsonSnippet.getString("description");
//                            String publishedAt = jsonSnippet.getString("publishedAt");
//                            String thumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");
//
//                            youtubeObject.setTitle(title);
//                            youtubeObject.setDescription(description);
//                            youtubeObject.setPublishedAt(publishedAt);
//                            youtubeObject.setThumbnail(thumbnail);
//                            youtubeObject.setVideo_id(vedio_id);
//                            mList.add(youtubeObject);
//
//                        }
//                    }
//
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return mList;
//
//    }
}
