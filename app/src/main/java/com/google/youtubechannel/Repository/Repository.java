package com.google.youtubechannel.Repository;

import android.content.Context;


import com.google.youtubechannel.Model.Entities.ChannelVideosItem;
import com.google.youtubechannel.R;
import com.google.youtubechannel.Repository.retrofit.ApiConfig;
import com.google.youtubechannel.Repository.retrofit.ApiConstants;
import com.google.youtubechannel.Repository.retrofit.ApiService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class Repository {

    private static Repository instance;
    private static ApiService service;
    private Context context;

    private Repository(Context context) {
        this.context = context;
        service = ApiConfig.getInstance(context).getService();

    }

    public static Repository getInstance(Context context) {
        if (instance == null)
            instance = new Repository(context);
        return instance;
    }

    public Observable<ChannelVideosItem> getChannels(String Channel_Id) {

        Map<String , Object > queryMap = new HashMap<>();
        queryMap.put(ApiConstants.PART ,context.getString(R.string.part) );
        queryMap.put(ApiConstants.CHANNEL_ID , Channel_Id );
        queryMap.put(ApiConstants.MAX_RESULTS ,context.getString(R.string.maxResult) );
        queryMap.put(ApiConstants.KEY, context.getString(R.string.key));

        return service
                .getChannelVideos(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<String> SubscribeNewChannel(String Channel_Id) {


        String part= context.getString(R.string.part_resourse);
        String key=  context.getString(R.string.key);


        return service
                .SubscribeChannel(part , Channel_Id  ,key )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }




}
