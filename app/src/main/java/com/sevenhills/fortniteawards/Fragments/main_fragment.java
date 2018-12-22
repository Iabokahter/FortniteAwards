package com.sevenhills.fortniteawards.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sevenhills.fortniteawards.Activities.InviteFriendActivity;
import com.sevenhills.fortniteawards.Activities.WithdrawActivity;
import com.sevenhills.fortniteawards.R;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tapjoy.TJConnectListener;
import com.tapjoy.Tapjoy;

import java.util.Hashtable;
import com.sevenhills.fortniteawards.User;

import static android.widget.Toast.LENGTH_SHORT;


public class main_fragment extends Fragment implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;

    View thisView;

    private DatabaseReference mDatabase;
    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("user", 0);
        thisView = inflater.inflate(R.layout.main_fragment, container, false);

        boolean firstTime = true;
        final TextView Points;

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        final String SDKKey = "ZbzFPWjAT82xnWp0t0HvQQEC1bAvhb53ruxs3N5O7AilJYH487e0sSTqScxe";

        Tapjoy.connect(getContext(), SDKKey, new Hashtable(), new TJConnectListener() {
            @Override
            public void onConnectSuccess() {
                Log.e("TAPJOY", "succ");
            }

            @Override
            public void onConnectFailure() {
                Log.e("TAPJOY", "fail");
            }
        });
        Tapjoy.setDebugEnabled(true);

        if (mDatabase == null) {
            //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }
        sp = getActivity().getSharedPreferences("user", 0);


        Points = thisView.findViewById(R.id.v_pointText);
        //Points.setText();
        Log.e("DB PATH = ", sp.getString("username", "NULL") + " ->" + "P_bucksAmount");
        Log.e("DB child count", mDatabase.child("users").child(sp.getString("username", "NULL")).child("P_bucksAmount").getKey());
        mDatabase.child("users").child(sp.getString("userID", "NULL")).child("P_bucksAmount").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //mDatabase.child(sp.getString("username","NULL"))
                //if(dataSnapshot.getValue() !)

                Log.e("BLAH BLAH BLAH", "User name: " + sp.getString("userID", "FAAAACK") + ", V-POints " + dataSnapshot.getValue());

                if (dataSnapshot.getValue() != null) {
                    int points = Math.toIntExact((long) dataSnapshot.getValue());

                    Points.setText(Integer.toString(points));

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("BLAH BLAH BLAH", "Failed to read value.", error.toException());
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
//        LinearLayout invite_friend = (LinearLayout) thisView.findViewById(R.id.invite_friend);
//        invite_friend.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                //Intent myIntent = new Intent(view.getContext(), InviteFriendActivity.class);
//                //startActivity(myIntent);;
//
//
//                DatabaseReference mRef = mDatabase.getRef();
//                mRef.setValue("SEND NUKES TO MOTHERLAND");
//
//                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
//
//// Creating new user node, which returns the unique key value
//// new user node would be /users/$userid/
//                String userId = mDatabase.push().getKey();
//
////// creating user object
////
////                User user = new User(sp.getString("username","BLAH BLAH"),
////                        sp.getString("email","SEND_NUKE@north.korea.com"),
////                        sp.getString("key","password"),
////                        Integer.toString(0));
//
////                mDatabase.child(userId).setValue(user);
//            }
//        });

        LinearLayout wallet = thisView.findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WithdrawActivity.class));
            }
        });

        //Ads setup
        // our app AdMob ID: ca-app-pub-1698795253663723~5657821522

        MobileAds.initialize(thisView.getContext(), "ca-app-pub-1698795253663723~5657821522");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(thisView.getContext());
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
//        if (mRewardedVideoAd.isLoaded()) {
//            mRewardedVideoAd.show();
//        }
//        rewardAds();
        LinearLayout watch_video = thisView.findViewById(R.id.watch_video);
        watch_video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mRewardedVideoAd.isLoaded())
                    mRewardedVideoAd.show();
            }
        });


        return thisView;
    }


    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
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

