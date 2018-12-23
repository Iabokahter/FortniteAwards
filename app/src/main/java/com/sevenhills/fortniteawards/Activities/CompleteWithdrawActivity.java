package com.sevenhills.fortniteawards.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sevenhills.fortniteawards.R;

public class CompleteWithdrawActivity extends AppCompatActivity {

//    WithdrawActivity withdrawActivity=new WithdrawActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_withdraw_layout);


    }


//    public void setTransactionItems(View view, ImageView img, TextView text, int index) {
//        img = view.findViewById(R.id.coins_image_final);
//        text = view.findViewById(R.id.vbucksAmount_final);
//        switch (index) {
//            case 0:
//                img.setImageDrawable(getDrawable(R.drawable.coin1));
//                text.setText(withdrawActivity.vbucksAmount[index]);
//                break;
//            case 1:
//                img.setImageDrawable(getDrawable(R.drawable.coin2));
//                text.setText(withdrawActivity.vbucksAmount[index]);
//                break;
//            case 2:
//                img.setImageDrawable(getDrawable(R.drawable.coin3));
//                text.setText(withdrawActivity.vbucksAmount[index]);
//                break;
//            case 3:
//                img.setImageDrawable(getDrawable(R.drawable.coin4));
//                text.setText(withdrawActivity.vbucksAmount[index]);
//                break;
//
//            default:
//                img.setImageDrawable(getDrawable(R.drawable.logo));
//                break;
//        }
//    }

}
