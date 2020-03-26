package com.google.youtubechannel.Model.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SubscribeViewModel extends BaseViewModel {


    public SubscribeViewModel(@NonNull Application application) {
        super(application);
    }

    public void SubscibeNewChannel(String Channel_Id){

      getReposity().SubscribeNewChannel(Channel_Id).subscribe(new Observer<String>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(String s) {
              Log.e("Success" , s);
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
      });
    }

    //TODO ./....
    public void CheckSubscription(){

    }
}
