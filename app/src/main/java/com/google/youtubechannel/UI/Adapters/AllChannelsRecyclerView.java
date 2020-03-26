package com.google.youtubechannel.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.youtubechannel.Model.Entities.ChannelItem;
import com.google.youtubechannel.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AllChannelsRecyclerView extends RecyclerView.Adapter<AllChannelsRecyclerView.viewHolder> {


    List<ChannelItem> videoListItems;
    public static OnItemClickListner listner;


    public void setAllVideos(List<ChannelItem> videoListItems) {
        this.videoListItems = videoListItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllChannelsRecyclerView.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.channel_list_item, viewGroup, false);
        return new AllChannelsRecyclerView.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllChannelsRecyclerView.viewHolder recyclerHolder, int i) {

        ChannelItem item = videoListItems.get(i);
        recyclerHolder.textView.setText(item.getTitle());

        if (i==4){
            recyclerHolder.imageView.setImageResource(R.drawable.mitch);
        }if (i==0){
            recyclerHolder.imageView.setImageResource(R.drawable.nerds);
        }if (i==1){
            recyclerHolder.imageView.setImageResource(R.drawable.edmt);
        }
        if (i==2){
            recyclerHolder.imageView.setImageResource(R.drawable.space);
        }
        if (i==3){
            recyclerHolder.imageView.setImageResource(R.drawable.coding_in_flow);
        }

    }

    @Override
    public int getItemCount() {

        return videoListItems==null ? 0 : videoListItems.size();
    }

    public void setOnItemClickListner(OnItemClickListner listner) {
        AllChannelsRecyclerView.listner = listner;
    }

    public interface OnItemClickListner {

        void onClick(int position , View view);

    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

   private TextView textView;
   private CircleImageView imageView;
   private CircleImageView circleImageView;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

           textView=itemView.findViewById(R.id.channel_title);
           imageView=itemView.findViewById(R.id.channel_img);
           circleImageView=itemView.findViewById(R.id.circleImageView);

           textView.setOnClickListener(this);
           imageView.setOnClickListener(this);
           circleImageView.setOnClickListener(this);

           itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listner.onClick(getAdapterPosition() , v);
        }
    }
}