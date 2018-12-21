package com.sevenhills.fortniteawards.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sevenhills.fortniteawards.R;

public class Sign_Up_Activity extends AppCompatActivity {



    private static final String TAG = "EmailPassword";
    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    private int revealX;
    private int revealY;
    View rootLayout;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up_layout);
        mAuth = FirebaseAuth.getInstance();
        final EditText name = findViewById(R.id.full_name);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText con_pass = findViewById(R.id.confirm_password);
        Button next = (Button) findViewById(R.id.continue_button);
        rootLayout = findViewById(R.id.signupLayout);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Have_A_Name(name) && Right_Password(password, con_pass) && Have_An_Email(email)) {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        getSharedPreferences("user",0).edit().putString("username",name.getText().toString()).apply();
                                        Log.e(TAG, "createUserWithEmail:done    ", task.getException());

                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Log.e(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                               Toast.LENGTH_SHORT).show();
                                    //    updateUI(null);
                                    }

                                    // ...
                                }
                            });
                    Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(myIntent);
                }
            }
        });


        TextView signin = (TextView) findViewById(R.id.sign_in);
        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view2) {
                Intent myIntent = new Intent(view2.getContext(), SignIn_Activity.class);
                startActivity(myIntent);
            }
        });


        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
            //intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
            //intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
                ) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = 360;// / 2// intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 40);
            revealY = 560;////intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 650);


            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }
    }

    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(800);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    protected void unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish();
        } else {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                    rootLayout, revealX, revealY, finalRadius, 0);

            circularReveal.setDuration(400);
            //circularReveal.start();
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    finish();
                }
            });


            circularReveal.start();
        }
    }


    boolean Have_A_Name(EditText text) {
        String name = text.getText().toString();
        if (name.isEmpty()) {
            text.setError("Name is required");
            return false;
        } else
            return true;
    }

    boolean Have_An_Email(EditText text) {
        String email = text.getText().toString();
        if (!((String) email).isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else {
            text.setError("Email is required");
            return false;
        }
    }

    boolean Right_Password(EditText text, EditText conText) {
        String password = text.getText().toString();
        String con_pass = conText.getText().toString();
        if (password.length() < 8) {
            text.setError("Must be at least 8 char");
            return false;
        }

        if (password.contains(con_pass))
            return true;
        else {
            conText.setError("Password not match");
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        unRevealActivity();
    }

}
