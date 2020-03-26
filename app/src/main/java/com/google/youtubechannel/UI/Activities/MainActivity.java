package com.google.youtubechannel.UI.Activities;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.youtubechannel.Model.ViewModels.ChannelsViewModel;
import com.google.youtubechannel.R;
import com.google.youtubechannel.UI.Adapters.ViewPagerAdapter;
import com.google.youtubechannel.UI.Fragments.ChannelFragment;
import com.google.youtubechannel.UI.Fragments.SubscribtionFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;



public class MainActivity extends AppCompatActivity {

    ChannelsViewModel viewModel;
    private ViewPagerAdapter viewPagerAdapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        AddFragments(viewPagerAdapter);
        setUpTabIcons();


    }

    private void AddFragments(ViewPagerAdapter viewPagerAdapter) {

        viewPagerAdapter.addFragments(new ChannelFragment());
        viewPagerAdapter.addFragments(new SubscribtionFragment());


     viewPager.setAdapter(viewPagerAdapter);
     tabLayout.setupWithViewPager(viewPager);


    } private void setUpTabIcons() {

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_video_library_black_24dp);
      //  imageView.setColorFilter(Color.parseColor("#5717AD"), PorterDuff.Mode.SRC_IN);
       tabLayout.getTabAt(0).setCustomView(imageView);


        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.ic_subscriptions_black_24dp);
      //  imageView1.setColorFilter(Color.parseColor("#5717AD"), PorterDuff.Mode.SRC_IN);
      tabLayout.getTabAt(1).setCustomView(imageView1);



    }

}
