package com.sevenhills.fortniteawards.Activities;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;

        import com.sevenhills.fortniteawards.R;

public class ThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_layout);


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), SettingsActivity.class);
//                startActivity(myIntent);
            }
        });


    }
}
