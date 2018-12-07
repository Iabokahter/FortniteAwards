package com.sevenhills.fortniteawards;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

public class MotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView setting = (ImageView) findViewById(R.id.setting_text);
        setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SettingsActivity.class);
                startActivity(myIntent);
            }
        });

        LinearLayout invite_friend = (LinearLayout) findViewById(R.id.invite_friend);
        invite_friend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), InviteFriendActivity.class);
                startActivity(myIntent);
            }
        });

        ImageView best_One = (ImageView) findViewById(R.id.best_one);
        best_One.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), TopOneActivity.class);
                startActivity(myIntent);
            }
        });

        LinearLayout wallet = (LinearLayout) findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), WalletActivity.class);
                startActivity(myIntent);
            }
        });

    }


}
