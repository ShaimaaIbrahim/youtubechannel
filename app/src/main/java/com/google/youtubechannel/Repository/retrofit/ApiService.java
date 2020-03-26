package com.google.youtubechannel.Repository.retrofit;


import com.google.youtubechannel.Model.Entities.ChannelVideosItem;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface ApiService {

    @GET(ApiConstants.END_POINT_SEARCH)
    Observable<ChannelVideosItem> getChannelVideos(
            @QueryMap Map<String, Object> queryMap
    );


    //TODO  -- I do not know what Subscription class is

    @POST(ApiConstants.END_POINT_SUBSCRIBE)
    @FormUrlEncoded
    Observable<String> SubscribeChannel(
            @Field(ApiConstants.PART) String part , @Field(ApiConstants.CHANNEL_ID) String Channel_Id ,
            @Field(ApiConstants.KEY) String Key
    );


}
