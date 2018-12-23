package com.sevenhills.fortniteawards.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.SupportActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sevenhills.fortniteawards.Activities.AccountActivity;
import com.sevenhills.fortniteawards.R;


public class SettingFargment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View thisView = inflater.inflate(R.layout.setting_fargment, container, false);


        ImageView back = (ImageView) thisView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment=null;
                FragmentManager fragmentManager=getFragmentManager();
                fragment=new main_fragment();
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });

        LinearLayout account = (LinearLayout) thisView.findViewById(R.id.account_activity);
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(thisView.getContext(),AccountActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout support = (LinearLayout) thisView.findViewById(R.id.support_activity);
        support.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(thisView.getContext(), com.sevenhills.fortniteawards.Activities.SupportActivity.class);
                startActivity(intent);
            }
        });

        return thisView;
    }

}
