package app.com.eLearningApp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import app.com.eLearningApp.R;
import app.com.eLearningApp.adapters.HomeAdapter;
import app.com.eLearningApp.adapters.SliderAdapter;
import app.com.eLearningApp.classes.PlaylistList;
import app.com.eLearningApp.models.HomeDataModel;

public class HomeFragment extends Fragment {

    private SliderView sliderView;
    private SliderAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapter(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);   }
        });
        renewItems();
        RecyclerView recyclerViewDS=view.findViewById(R.id.recycler_view_DS);
        RecyclerView recyclerViewJava=view.findViewById(R.id.recycler_view_java);
        RecyclerView recyclerViewSQL=view.findViewById(R.id.recycler_view_sql);
        RecyclerView recyclerViewC=view.findViewById(R.id.recycler_view_c);
        RecyclerView recyclerViewPython=view.findViewById(R.id.recycler_view_python);


        setIteams(recyclerViewDS,PlaylistList.getDSPlaylistList());
        setIteams(recyclerViewJava,PlaylistList.getJavaPlaylist());
        setIteams(recyclerViewSQL,PlaylistList.getSQLPlaylistList());
        setIteams(recyclerViewC,PlaylistList.getCPlaylistList());
        setIteams(recyclerViewPython,PlaylistList.getPythonPlaylistList());



        return view;
    }

    private void setIteams(RecyclerView recyclerView,List<HomeDataModel> homeDataModels){
        HomeAdapter homeAdapter;
        homeAdapter=new HomeAdapter(getContext(), homeDataModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

    }

     private  void renewItems() {
        List<HomeDataModel> sliderItemList = PlaylistList.getSliderPlaylistList();
                adapter.renewItems(sliderItemList);

        //dummy data
//        for (int i = 0; i < 5; i++) {
//            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item "+ i);
//            if (i % 2 == 0) {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//            } else {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//            }
//            sliderItemList.add(sliderItem);
//        }



    }
}
