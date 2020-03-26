package com.google.youtubechannel.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.youtubechannel.Model.Entities.VideoItem;
import com.google.youtubechannel.R;
import com.google.youtubechannel.Repository.retrofit.ApiConstants;

public class PlayVideActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {

    private VideoItem videoItem;
    private YouTubePlayerView playerView;

    private static final int RECOVERY_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_vide);

        playerView=findViewById(R.id.player_view);



        playerView.initialize(ApiConstants.YOUTUBE_API_KEY , this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Intent intent = getIntent();
        videoItem = (VideoItem) intent.getSerializableExtra(PlayVideActivity.class.getSimpleName());
        youTubePlayer.loadVideo(videoItem.getUrl());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {

            Toast.makeText(this, "Error Intializing Youtube Player", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(ApiConstants.YOUTUBE_API_KEY, this);
        }
    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }
}
