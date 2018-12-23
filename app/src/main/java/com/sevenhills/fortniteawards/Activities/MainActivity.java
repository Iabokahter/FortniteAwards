package com.sevenhills.fortniteawards.Activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.sevenhills.fortniteawards.Fragments.BetterList;
import com.sevenhills.fortniteawards.Fragments.Info_Fragment;
import com.sevenhills.fortniteawards.Fragments.SettingFargment;
import com.sevenhills.fortniteawards.Fragments.main_fragment;
import com.sevenhills.fortniteawards.R;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_camera);

        ImageView navigation = (ImageView) findViewById(R.id.navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

//        LinearLayout invite_friend = (LinearLayout) findViewById(R.id.invite_friend);
//        invite_friend.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), InviteFriendActivity.class);
//                startActivity(myIntent);
//            }
//        });

//        LinearLayout wallet = (LinearLayout) findViewById(R.id.wallet);
//        wallet.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {


//                Snackbar.make(view,"kjystkref",0).show();
//                Intent intent = new Intent(MainActivity.this, WithdrawActivity.class);
//                startActivity(intent);



        FragmentManager fragmentManager = getSupportFragmentManager();
        main_fragment fragment = new main_fragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        //drawer.openDrawer(GravityCompat.START);
    }

    private void rewardAds() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-4824494878097656/8403117409",//use this id for testing
                new AdRequest.Builder().build());

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_camera) {
            fragment = new main_fragment();

        } else if (id == R.id.nav_gallery) {
            fragment = new SettingFargment();
        } else if (id == R.id.nav_About) {
            fragment = new Info_Fragment();
        } else if (id == R.id.nav_topmonth) {
            fragment = new BetterList();
        }
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
