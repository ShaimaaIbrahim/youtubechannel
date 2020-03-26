package com.google.youtubechannel.UI.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.youtubechannel.Model.Entities.ChannelItem;
import com.google.youtubechannel.Model.ViewModels.ChannelsViewModel;
import com.google.youtubechannel.R;
import com.google.youtubechannel.UI.Adapters.AllChannelsRecyclerView;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class SubscribtionFragment extends Fragment {
private AllChannelsRecyclerView allChannelsRecyclerView;
private RecyclerView recyclerView;
private ChannelsViewModel channelsViewModel;


    public SubscribtionFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_subscribtion, container, false);

        allChannelsRecyclerView= new AllChannelsRecyclerView();

        channelsViewModel= ViewModelProviders.of(this).get(ChannelsViewModel.class);


        recyclerView=view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(allChannelsRecyclerView);

        channelsViewModel.SubscribedChannelsVideosMutable.observe(this, new Observer<List<ChannelItem>>() {
            @Override
            public void onChanged(List<ChannelItem> items) {

                allChannelsRecyclerView.setAllVideos(items);

                for (int i=0 ;i < items.size(); i++){
                    Log.e(TAG + "Aly" , items.get(i).getTitle());
                }
            }
        });




        return view;
    }


}
