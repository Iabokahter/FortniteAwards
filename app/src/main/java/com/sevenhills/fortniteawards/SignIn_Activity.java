package com.sevenhills.fortniteawards;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class SignIn_Activity extends AppCompatActivity {

    final EditText email =findViewById(R.id.email_signin);
    final EditText password = findViewById(R.id.password_signin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        Button next = (Button) findViewById(R.id.continue_signin);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Have_An_Email(email) && Right_Password(password)) {
                    Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(myIntent);
                }
            }
        });
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
        }
        else
            return true;
    }
}