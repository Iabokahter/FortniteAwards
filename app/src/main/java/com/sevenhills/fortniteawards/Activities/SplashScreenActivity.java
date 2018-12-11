package com.sevenhills.fortniteawards.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.sevenhills.fortniteawards.R;

public class SplashScreenActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView logo;
    Animation up2down,down2up;
    private Button logIn,SignUp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        logo = findViewById(R.id.splashscreenLogo);
        logIn = findViewById(R.id.sign_in_Button);
        SignUp = findViewById(R.id.sign_up_button);
        //logIn.setOn
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentActivity(view);
            }
        });
        up2down = AnimationUtils.loadAnimation(this,R.anim.up_2_down);
        down2up = AnimationUtils.loadAnimation(this,R.anim.down_2_up);
        logo.setAnimation(up2down);
        logo.animate();



    }

    @Override
    public void onClick(View view) {

        presentActivity(view);

    }

    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (logIn.getTranslationX() + view.getWidth() / 2);
        int revealY = (int) (logIn.getTranslationY()+ view.getHeight() / 2);

        Intent intent = new Intent(this, SignIn_Activity.class);
        intent.putExtra(SignIn_Activity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(SignIn_Activity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
       // intent.putExtra(SignIn_Activity.EXTRA_C)
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
