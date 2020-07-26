package app.com.eLearningApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.com.eLearningApp.R;
import app.com.eLearningApp.fragments.PlayListFragment;
import app.com.eLearningApp.models.HomeDataModel;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context mContext;
    private List<HomeDataModel> mList;

    public HomeAdapter(Context mContext, List<HomeDataModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_iteam,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.name.setText(mList.get(position).getPlaylistName());
            Glide.with(mContext).load(mList.get(position).getImageURL())
                .into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlayListFragment fragment1 = new PlayListFragment(mList.get(position).getPlayListId());
                    FragmentTransaction fragmentTransaction1 = ((AppCompatActivity) mContext).
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame_layout, fragment1, "Home");
                    fragmentTransaction1.addToBackStack(null);
                    fragmentTransaction1.commit();

                }
            });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_home_item);
            image =itemView.findViewById(R.id.iv_home_item);

        }
    }
}
