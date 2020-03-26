package com.google.youtubechannel.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.youtubechannel.Model.Entities.VideoItem;
import com.google.youtubechannel.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class AllVideosRecyclerView extends RecyclerView.Adapter<AllVideosRecyclerView.recyclerView>{

    private List<VideoItem > videoItems;
    public static onClickListner listner;

    public AllVideosRecyclerView() {

    }
public void setAllVideos(List<VideoItem > videoItems){
        this.videoItems=videoItems;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public AllVideosRecyclerView.recyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item , parent , false);

      return new AllVideosRecyclerView.recyclerView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AllVideosRecyclerView.recyclerView holder, int position) {

      holder.textView.setText(videoItems.get(position).getTitle());
      Picasso.get().load(videoItems.get(position).getUrl()).into(holder.imageView);
      holder.textView1.setText(videoItems.get(position).getPublishedAt());

    }

    @Override
    public int getItemCount() {
        return videoItems == null ? 0 : videoItems.size();
    }

    public void setOnClickListner(AllVideosRecyclerView.onClickListner listner){
     AllVideosRecyclerView.listner = listner;
    }

    public interface onClickListner{
        void onClick(int position);
    }
    class recyclerView extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AppCompatTextView textView;
        private AppCompatImageView imageView;
        private AppCompatTextView textView1;


        public recyclerView(@NonNull View itemView) {
            super(itemView);
             textView=itemView.findViewById(R.id.video_title);
             imageView=itemView.findViewById(R.id.video_img);
             textView1=itemView.findViewById(R.id.published_at);

              itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
       listner.onClick(getAdapterPosition());
        }
    }
}