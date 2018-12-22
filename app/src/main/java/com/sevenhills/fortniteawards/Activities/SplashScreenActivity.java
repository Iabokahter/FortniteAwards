package com.sevenhills.fortniteawards.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sevenhills.fortniteawards.R;

public class SplashScreenActivity extends AppCompatActivity {


    ImageView logo;

    enum gotoActivity {signIn, signUp}

    Animation up2down, down2up;
    private Button logIn;
    private FirebaseAuth mAuth;
    Button signUp ;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.splash_screen);
        logo = findViewById(R.id.splashscreenLogo);
        logIn = findViewById(R.id.sign_in_Button);
        //logIn.setOn
        signUp = findViewById(R.id.sign_up_button);
        SharedPreferences sp = getSharedPreferences("user", 0);
        if (sp.getBoolean("has_been_opened", false)) {
            //sp.edit().putBoolean("has_been_opened", true).apply();
            ShowButtons();


        } else {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {

                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            } else {
                ShowButtons();

            }

        }


    }

    void ShowButtons () {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentActivity(view, gotoActivity.signUp);

            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentActivity(view, gotoActivity.signIn);
            }
        });
        up2down = AnimationUtils.loadAnimation(this, R.anim.up_2_down);
        down2up = AnimationUtils.loadAnimation(this, R.anim.down_2_up);
        logo.setAnimation(up2down);
        logo.animate();

    }

    public void presentActivity(View view, gotoActivity activity) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (logIn.getTranslationX() + view.getWidth() / 2);
        int revealY = (int) (logIn.getTranslationY() + view.getHeight() / 2);

        Intent intent = new Intent(this, activity == gotoActivity.signIn ? SignIn_Activity.class : Sign_Up_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra(SignIn_Activity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(SignIn_Activity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        // intent.putExtra(SignIn_Activity.EXTRA_C)
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
