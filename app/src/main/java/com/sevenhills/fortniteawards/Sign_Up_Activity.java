package com.sevenhills.fortniteawards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sign_Up_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        final EditText name = findViewById(R.id.full_name);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText con_pass = findViewById(R.id.confirm_password);


        Button next = (Button) findViewById(R.id.continue_button);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Have_A_Name(name) && Have_An_Email(email) && Right_Password(password, con_pass)) {
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
        /*if (password.length() < 8) {
            text.setError("Must be at least 8 char");
            return false;
        }

        if (password == con_pass)
            return true;
        else {
            conText.setError("Password not match");
            return false;
        }*/
        return  true;
    }

}
