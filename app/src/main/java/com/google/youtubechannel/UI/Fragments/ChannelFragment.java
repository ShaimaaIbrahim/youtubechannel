package com.google.youtubechannel.UI.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.youtubechannel.Model.Entities.ChannelItem;
import com.google.youtubechannel.Model.ViewModels.ChannelsViewModel;
import com.google.youtubechannel.Model.ViewModels.SubscribeViewModel;
import com.google.youtubechannel.R;
import com.google.youtubechannel.UI.Activities.VideosActivity;
import com.google.youtubechannel.UI.Adapters.AllChannelsRecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ChannelFragment extends Fragment {

private CoordinatorLayout coordinatorLayout;
private AllChannelsRecyclerView allChannelsRecyclerView;
private SubscribeViewModel viewModel;
private ChannelsViewModel channelsViewModel;
private RecyclerView recyclerView;
public  List<ChannelItem> channelItems;


    public ChannelFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_channel, container, false);

        coordinatorLayout=view.findViewById(R.id.coordinator);
        channelItems=new ArrayList<>();

        viewModel = ViewModelProviders.of(this).get(SubscribeViewModel.class);
        channelsViewModel=ViewModelProviders.of(this).get(ChannelsViewModel.class);

        final List<ChannelItem> channelItemList = new ArrayList<>();
        allChannelsRecyclerView= new AllChannelsRecyclerView();


        recyclerView=view.findViewById(R.id.recycler_view);

         LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
         layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setAdapter(allChannelsRecyclerView);


        channelItemList.add(new ChannelItem("Coding With Nerds", "" , "UCnDAXfhnL5j-KhHc1KhvXHw" ));
        channelItemList.add(new ChannelItem("Edmet Dev", "" , "UCnDAXfhnL5j-KhHc1KhvXHw" ));
        channelItemList.add(new ChannelItem("Self Study Space", "" , "UCnDAXfhnL5j-KhHc1KhvXHw" ));
        channelItemList.add(new ChannelItem("Coding in Flow", "" , "UCnDAXfhnL5j-KhHc1KhvXHw" ));
        channelItemList.add(new ChannelItem("Coding with Mitch", "" , "UCnDAXfhnL5j-KhHc1KhvXHw" ));


               allChannelsRecyclerView.setAllVideos(channelItemList);


              allChannelsRecyclerView.setOnItemClickListner(new AllChannelsRecyclerView.OnItemClickListner() {
                  @Override
                  public void onClick(int position, View view) {

                      if (view.getId()==R.id.channel_img){

                          Intent intent = new Intent(getContext() , VideosActivity.class);
                          intent.putExtra("Channle_Id" , channelItemList.get(position).getId());
                          startActivity(intent);

                      }
                     else if (view.getId()==R.id.circleImageView){

                        viewModel.SubscibeNewChannel(channelItemList.get(position).getId());
                         CreateSnackBar();
                     }
                  }
              });

           channelsViewModel.Subscribe(channelItems);

           if (channelItems.size()!=0 && channelItems!=null) {
               Log.e(TAG + "Shaimaa", channelItems.get(0).getTitle());
           }
        return view;
    }


private void CreateSnackBar(){
    Snackbar snackbar = Snackbar
            .make(coordinatorLayout, "Subscribed Successfully", Snackbar.LENGTH_LONG);
    snackbar.show();
}


}
