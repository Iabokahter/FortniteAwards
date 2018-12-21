package com.sevenhills.fortniteawards.Fragments;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sevenhills.fortniteawards.Activities.InviteFriendActivity;
import com.sevenhills.fortniteawards.Activities.MainActivity;
import com.sevenhills.fortniteawards.Activities.WithdrawActivity;
import com.sevenhills.fortniteawards.R;


public class main_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MainActivity mainActivity=new MainActivity();

        final View thisView  = inflater.inflate(R.layout.main_fragment, container, false);

        final DrawerLayout drawer = (DrawerLayout) thisView.findViewById(R.id.drawer_layout);

        /*ImageView navigation = (ImageView) thisView.findViewById(R.id.navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainActivity.OpenDrawer();
//                drawer.openDrawer(GravityCompat.START);
            }
        });*/

//        LinearLayout invite_friend = (LinearLayout) thisView.findViewById(R.id.invite_friend);
//        invite_friend.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), InviteFriendActivity.class);
//                startActivity(myIntent);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("message");
//                myRef.setValue("Hello, World!");
//                Snackbar.make(thisView,"kjystkref",0).show();
//            }
//        });



        LinearLayout wallet = (LinearLayout) thisView.findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),WithdrawActivity.class));
            }
        });
        return thisView;
    }

}

