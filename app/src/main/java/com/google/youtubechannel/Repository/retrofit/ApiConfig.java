package com.google.youtubechannel.Repository.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class ApiConfig {

    private final static int CACHE_SIZE_BYTES = 1024 * 1024 * 5;
    private static ApiConfig instance;
    private final Retrofit retrofit;
    private ApiService service;

    private ApiConfig(final Context context) {
        Cache myCache = getCacheDir(context);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(myCache);

        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder();

                if (!isNetworkConnected(context)) {
                    requestBuilder.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7);
                } else {
                    requestBuilder.addHeader("Cache-Control", "public, max-stale=" + 60);
                }

                return chain.proceed(requestBuilder.build());
            }
        });

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        httpClient
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(ApiConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public synchronized static ApiConfig getInstance(Context context) {
        if (instance == null)
            instance = new ApiConfig(context);
        return instance;
    }

    /**
     * used to cache request response so it's better and faster
     * <p>
     * this used with E-TAG
     */

    private static Cache getCacheDir(Context context) {
        if (context == null) return null;
        return new Cache(context.getCacheDir(), CACHE_SIZE_BYTES);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }

    public ApiService getService() {
        if (service == null) {
            this.service = retrofit.create(ApiService.class);
        }
        return service;
    }
}
