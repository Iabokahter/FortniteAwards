package com.sevenhills.fortniteawards;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        LinearLayout account = (LinearLayout) findViewById(R.id.account_activity);
        account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AccountActivity.class);
                startActivity(myIntent);
            }
        });

        LinearLayout theme = (LinearLayout) findViewById(R.id.theme_activity);
        theme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ThemeActivity.class);
                startActivity(myIntent);
            }
        });

        LinearLayout support = (LinearLayout) findViewById(R.id.support_activity);
        support.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SupportActivity.class);
                startActivity(myIntent);
            }
        });

        LinearLayout about = (LinearLayout) findViewById(R.id.about_activity);
        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AboutUs_Activity.class);
                startActivity(myIntent);
            }
        });


    }
}
