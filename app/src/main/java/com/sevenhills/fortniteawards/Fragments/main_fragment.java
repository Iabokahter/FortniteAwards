package com.sevenhills.fortniteawards.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sevenhills.fortniteawards.Activities.InviteFriendActivity;
import com.sevenhills.fortniteawards.Activities.WithdrawActivity;
import com.sevenhills.fortniteawards.R;
import com.sevenhills.fortniteawards.User;
import com.tapjoy.TJConnectListener;
import com.tapjoy.Tapjoy;

import java.util.Hashtable;

import static android.widget.Toast.LENGTH_SHORT;


public class main_fragment extends Fragment {

    private DatabaseReference mDatabase;
    SharedPreferences sp;
    static  boolean firstTime = true;
    TextView Points;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View thisView  = inflater.inflate(R.layout.main_fragment, container, false);


        final String SDKKey  = "ZbzFPWjAT82xnWp0t0HvQQEC1bAvhb53ruxs3N5O7AilJYH487e0sSTqScxe";

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

        if(mDatabase == null) {
            //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }
        sp = getActivity().getSharedPreferences("user",0);
        LinearLayout wallet = thisView.findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),WithdrawActivity.class));
            }
        });

        Points = thisView.findViewById(R.id.v_pointText);
        //Points.setText();
        Log.e("DB PATH = ",sp.getString("username","NULL") +  " ->" + "P_bucksAmount");
        Log.e("DB child count", mDatabase.child("users").child(sp.getString("username","NULL")).child("P_bucksAmount").getKey());
        mDatabase.child("users").child(sp.getString("userID","NULL")).child("P_bucksAmount").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //mDatabase.child(sp.getString("username","NULL"))
                //if(dataSnapshot.getValue() !)

                Log.e("BLAH BLAH BLAH", "User name: " + sp.getString("userID","FAAAACK")+ ", V-POints " + dataSnapshot.getValue());

                if(dataSnapshot.getValue() != null) {
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
        return thisView;
    }

}

