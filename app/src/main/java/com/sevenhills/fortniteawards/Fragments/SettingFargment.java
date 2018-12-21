package com.sevenhills.fortniteawards.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sevenhills.fortniteawards.R;


public class SettingFargment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.setting_fargment, container, false);


        ImageView back = (ImageView) thisView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment=null;
                FragmentManager fragmentManager=getFragmentManager();
                fragment=new main_fragment();
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });
        return thisView;
    }

}
