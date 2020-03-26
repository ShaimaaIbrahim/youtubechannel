package com.google.youtubechannel.Model.ViewModels;

import android.app.Application;
import android.util.Log;

import com.google.youtubechannel.Model.Entities.ChannelItem;
import com.google.youtubechannel.Model.Entities.ChannelVideosItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import static android.content.ContentValues.TAG;



public class ChannelsViewModel extends BaseViewModel {

    public MutableLiveData<List<ChannelVideosItem.Item>> ChannelsVideosMutable;
    public MutableLiveData<List<ChannelItem>> SubscribedChannelsVideosMutable;

    public ChannelsViewModel(@NonNull Application application) {
        super(application);

        ChannelsVideosMutable = new MutableLiveData<>();
        SubscribedChannelsVideosMutable=new MutableLiveData<List<ChannelItem>>();
    }

    public LiveData<List<ChannelVideosItem.Item>> getChannelVideos(String Channel_Id) {

        getReposity().getChannels(Channel_Id).subscribe(new Observer<ChannelVideosItem>() {
            @Override
            public void onSubscribe(Disposable d) {
                setIsLoading(true);
            }

            @Override
            public void onNext(ChannelVideosItem response) {
                if ( response.getItems()!= null && response.getItems().size()!=0) {
                    ChannelsVideosMutable.postValue(response.getItems());
                    Log.e(TAG+"Shaimaa" ,  response.getItems().size() + " ");
                }else {
                    Log.e(TAG+"Shaimaa" , "Erroor");
                } }

            @Override
            public void onError(Throwable e) {
                Log.e("Shaimaa" , e.getMessage());
                setIsLoading(false);
            }


            @Override
            public void onComplete() {
                Log.e("Shaimaa" , "Completed");
            }

        });
        return ChannelsVideosMutable;
    }

   public LiveData<List<ChannelItem>>Subscribe(List<ChannelItem> items){

        SubscribedChannelsVideosMutable.postValue(items);

        return SubscribedChannelsVideosMutable;
   }

}
