package com.sevenhills.fortniteawards.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sevenhills.fortniteawards.Activities.InviteFriendActivity;
import com.sevenhills.fortniteawards.Activities.WithdrawActivity;
import com.sevenhills.fortniteawards.R;
import com.sevenhills.fortniteawards.User;

import static android.widget.Toast.LENGTH_SHORT;


public class main_fragment extends Fragment {

    private DatabaseReference mDatabase;
    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("user",0);
        final View thisView  = inflater.inflate(R.layout.main_fragment, container, false);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        LinearLayout invite_friend = (LinearLayout) thisView.findViewById(R.id.invite_friend);
        invite_friend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent myIntent = new Intent(view.getContext(), InviteFriendActivity.class);
                //startActivity(myIntent);;


                DatabaseReference mRef = mDatabase.getRef();
                mRef.setValue("SEND NUKES TO MOTHERLAND");

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

// Creating new user node, which returns the unique key value
// new user node would be /users/$userid/
                String userId = mDatabase.push().getKey();

// creating user object

                User user = new User(sp.getString("username","BLAH BLAH"),
                        sp.getString("email","SEND_NUKE@north.korea.com"),
                        sp.getString("key","password"),
                        Integer.toString(0));

                mDatabase.child(userId).setValue(user);
            }
        });

        LinearLayout wallet = thisView.findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),WithdrawActivity.class));
            }
        });
        return thisView;
    }

}

