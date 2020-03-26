package com.google.youtubechannel.Model.ViewModels;

import android.app.Application;

import com.google.youtubechannel.Repository.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BaseViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    public static Repository repository ;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<Boolean> isLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.setValue(isLoading);
    }

public static Repository getReposity(){
      return repository;
}

}
