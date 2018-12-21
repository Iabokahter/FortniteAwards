package com.sevenhills.fortniteawards.Activities;

        import android.animation.Animator;
        import android.animation.AnimatorListenerAdapter;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.util.Patterns;
        import android.util.Size;
        import android.view.View;
        import android.view.ViewAnimationUtils;
        import android.view.ViewTreeObserver;
        import android.view.animation.AccelerateInterpolator;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.facebook.CallbackManager;
        import com.facebook.FacebookCallback;
        import com.facebook.FacebookException;
        import com.facebook.login.LoginResult;
        import com.facebook.login.widget.LoginButton;
        import com.google.android.gms.auth.api.Auth;
        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
        import com.google.android.gms.auth.api.signin.GoogleSignInApi;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.api.ApiException;
        import com.google.android.gms.common.api.GoogleApiActivity;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthCredential;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.auth.GoogleAuthProvider;
        import com.sevenhills.fortniteawards.R;
        import com.facebook.FacebookSdk;
        import com.facebook.appevents.AppEventsLogger;

        import java.util.Arrays;
        import java.util.Collections;

public class SignIn_Activity extends AppCompatActivity {
    SharedPreferences sp;
    private static final int RC_SIGN_IN = 2;
    String TAG = "BLAH BLAH BLAH";
    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    private int revealX;
    private int revealY;
    View rootLayout;
    Button google;//,facebook;
    FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleApiClient googleApiClient;
    private CallbackManager callbackManager;

    private static final String EMAIL = "email";
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("user",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        callbackManager = CallbackManager.Factory.create();



        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Collections.singletonList(EMAIL));

            // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                    Log.e("FACEBOOK","succed");
                    Toast.makeText(getApplicationContext(),"succ",Toast.LENGTH_SHORT).show();

                    GotoMainActivity();


                }

                @Override
                public void onCancel() {
                    // App code
                    Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_SHORT).show();
                    Log.e("FACEBOOK","cancel");
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    Log.e("FACEBOOK","error");
                }
            });
            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                .build();
            Intent intent = getIntent();
            mAuth = FirebaseAuth.getInstance();
            rootLayout = findViewById(R.id.root_view_signin);
            google = findViewById(R.id.google_signin);
            //facebook = findViewById(R.id.fb_signin);
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);
            final EditText email = findViewById(R.id.email_signin);
            final EditText password = findViewById(R.id.password_signin);
            Button next = (Button) findViewById(R.id.continue_signin);
        google.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"uytefk0",Toast.LENGTH_SHORT).show();
                signIn();
            }
        });

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }

        }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();//end google api init




        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Right_Password(password) && Have_An_Email(email)) {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        //sp.edit().putString("username",user.getDisplayName()).apply();
                                        sp.edit().putString("username",user.getDisplayName())
                                                .putString("key","GOOGLE")
                                                .putString("email",user.getEmail())
                                                .putString("userID","BLAH").apply();
                                        GotoMainActivity();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(SignIn_Activity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });

                }
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
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);



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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
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


    boolean Have_An_Email(EditText text) {
        String email = text.getText().toString();
        if (!((String) email).isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else {
            text.setError("Email is required");
            return false;
        }
    }

    boolean Right_Password(EditText text) {
        String password = text.getText().toString();
        if (password.length() < 8) {
            text.setError("Must be at least 8 char");
            return false;
        } else
            return true;
    }
    @Override
    public void onBackPressed() {
        unRevealActivity();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            GotoMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make((rootLayout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    void GotoMainActivity () {
        startActivity(new Intent(SignIn_Activity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));


    }
}
