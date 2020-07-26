package app.com.eLearningApp.adapters;

import android.content.Context;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.com.eLearningApp.R;
import app.com.eLearningApp.models.Snippet;


public class VideoPostAdapter extends RecyclerView.Adapter<VideoPostAdapter.YoutubePostHolder> {

    private List<Snippet> dataSet;
    private Context mContext = null;
    private ClickListner clickListner;


    public VideoPostAdapter(Context mContext, List<Snippet> dataSet) {
        this.dataSet = dataSet;
        this.mContext = mContext;

    }

    @Override
    public YoutubePostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_post_layout,parent,false);
        YoutubePostHolder postHolder = new YoutubePostHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(YoutubePostHolder holder, final int position) {
        //set the views here
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewDate = holder.textViewDate;
        ImageView ImageThumb = holder.ImageThumb;
        final Snippet object=dataSet.get(position);

        textViewTitle.setText(object.getTitle());
        textViewDate.setText(object.getPublishedAt());

         if (object.getThumbnails().getStandardThemnail()!=null)
             Glide.with(mContext).load(object.getThumbnails().getStandardThemnail().getUrl())
                     .into(holder.ImageThumb);
         else if (object.getThumbnails().getHighThemnail()!=null)
             Glide.with(mContext).load(object.getThumbnails().getHighThemnail().getUrl())
                     .into(holder.ImageThumb);
         else if (object.getThumbnails().getMediumThemnail()!=null)
             Glide.with(mContext).load(object.getThumbnails().getMediumThemnail().getUrl())
                     .into(holder.ImageThumb);
         else if (object.getThumbnails().getDefaultThemnail()!=null)
             Glide.with(mContext).load(object.getThumbnails().getDefaultThemnail().getUrl())
                     .into(holder.ImageThumb);
        holder.cardView.
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext, DetailsActivity.class);
//                        intent.putExtra("DATA", object.getTitle()+"%"+""+
//                                object.getDescription()+"%"+
//                                object.getPublishedAt()+"%"+
//                                object.getResourceId().getVideoId()+"%"+
//                                object.getPlaylistId());
//                        intent.putExtra("DATAOBJ",  object);
//                        mContext.startActivity(intent);
                        clickListner.onClick(position);

                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class YoutubePostHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDate;
        ImageView ImageThumb;
        CardView cardView;

        public YoutubePostHolder(View itemView) {
            super(itemView);
            this.textViewTitle =  itemView.findViewById(R.id.textViewTitle);
            this.textViewDate =  itemView.findViewById(R.id.textViewDate);
            this.ImageThumb = itemView.findViewById(R.id.ImageThumb);
            this.cardView=itemView.findViewById(R.id.cardView);

        }


    }

    public void setClickListner(ClickListner clickListner){
        this.clickListner=clickListner;

    }

    public interface ClickListner{
        public void onClick(int position);
    }
}

