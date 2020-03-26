package com.google.youtubechannel.UI.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.youtubechannel.Model.Entities.ChannelVideosItem;
import com.google.youtubechannel.Model.Entities.VideoItem;
import com.google.youtubechannel.Model.ViewModels.ChannelsViewModel;
import com.google.youtubechannel.R;
import com.google.youtubechannel.UI.Adapters.AllVideosRecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class VideosActivity extends AppCompatActivity {

    private ChannelsViewModel viewModel;
    private RecyclerView recyclerView;
    private AllVideosRecyclerView allVideosRecyclerView;
    private List<VideoItem> videoItemList=new ArrayList<>();
    private static final String TAG = "VideosActivity";
    private ProgressDialog dialog;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


        recyclerView=findViewById(R.id.recycler_view);

        allVideosRecyclerView=new AllVideosRecyclerView();
        dialog=new ProgressDialog(this);



        final Intent intent = getIntent();



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(allVideosRecyclerView);


        viewModel= ViewModelProviders.of(this).get(ChannelsViewModel.class);

        viewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                showLoading(aBoolean);
                }
            }
        });
         viewModel.getChannelVideos(intent.getStringExtra("Channle_Id")).observe(this,

                 new Observer<List<ChannelVideosItem.Item>>() {
             @Override
             public void onChanged(List<ChannelVideosItem.Item> items) {
                 assert items!=null;
                  for (int i =0 ; i < items.size() ; i++){
               if (items.get(i).getId().getVideoId()!=null){

                      Log.e(TAG+"Shaimaa " , items.get(i).getId()+ "");


                      videoItemList.add(new VideoItem(items.get(i).getId().getVideoId(),
                              items.get(i).getSnippet().getTitle() ,
                              items.get(i).getSnippet().getThumbnails().getHigh().getUrl() ,
                              items.get(i).getSnippet().getPublishedAt()));
                  }
                }
            }
         });

         allVideosRecyclerView.setAllVideos(videoItemList);

         allVideosRecyclerView.setOnClickListner(new AllVideosRecyclerView.onClickListner() {
             @Override
             public void onClick(int position) {

                 Intent intent1 = new Intent(VideosActivity.this , PlayVideActivity.class);
                 intent1.putExtra(PlayVideActivity.class.getSimpleName() , videoItemList.get(position));
                 startActivity(intent1);

             }
         });

    }

    private void showLoading(Boolean aBoolean) {
        if (aBoolean){
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
