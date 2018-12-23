package com.sevenhills.fortniteawards.Activities;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v4.app.ActivityOptionsCompat;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v4.util.Pair;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.sevenhills.fortniteawards.Fragments.main_fragment;
        import com.sevenhills.fortniteawards.R;


public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener {
    VbucksCard V1, V2, V3, V4;
    public int[] vbucksAmount = {1000, 2800, 7500, 13500};
    public int[] pbucksAmount = {10000, 20000, 40000, 70000};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdraw_layout);
        V1 = new VbucksCard(R.id.card1, 1);
        V2 = new VbucksCard(R.id.card2, 2);
        V3 = new VbucksCard(R.id.card3, 3);
        V4 = new VbucksCard(R.id.card4, 4);


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CompleteWithdrawActivity.class);
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this, (View)ivProfile, "profile");
//        intent.putExtra(CompleteWithdrawActivity.EXTRA_CONTACT, contact);
//        startActivity(intent, options.toBundle());
    }


    //CompleteWithdrawActivity completeWithdrawActivity=new CompleteWithdrawActivity();


    SharedPreferences pref = this.getSharedPreferences("PREF_NAME", this.MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();


    class VbucksCard {



        View cardView;
        ImageView image;
        TextView vbucks;
        TextView pbucks;

        @SuppressLint("SetTextI18n")
        VbucksCard(int id, final int amount) {

            cardView = findViewById(id);
            image = cardView.findViewById(R.id.coin_image);
            vbucks = cardView.findViewById(R.id.vbucksAmount);
            pbucks = cardView.findViewById(R.id.pbucks);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CompleteWithdrawActivity.class);
                    Pair<View, String> p1 = Pair.create((View) image, "coin_image");
                    Pair<View, String> p2 = Pair.create((View) vbucks, "coin_text");

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(WithdrawActivity.this, p1, p2);
                    startActivity(intent, options.toBundle());
                    //completeWithdrawActivity.setTransactionItems(cardView,image,vbucks,amount-1);
                }
            });
            switch (amount) {

                case 1:
                    image.setImageDrawable(getDrawable(R.drawable.coin1));
                    vbucks.setText(String.valueOf(vbucksAmount[0]) + " V-Bucks");
                    pbucks.setText(String.valueOf(pbucksAmount[0]));

                    break;
                case 2:
                    image.setImageDrawable(getDrawable(R.drawable.coin2));
                    vbucks.setText(String.valueOf(vbucksAmount[1]) + " V-Bucks");
                    pbucks.setText(String.valueOf(pbucksAmount[1]));

                    break;
                case 3:
                    image.setImageDrawable(getDrawable(R.drawable.coin3));
                    vbucks.setText(String.valueOf(vbucksAmount[2]) + " V-Bucks");
                    pbucks.setText(String.valueOf(pbucksAmount[2]));

                    break;
                case 4:
                    image.setImageDrawable(getDrawable(R.drawable.coin4));
                    vbucks.setText(String.valueOf(vbucksAmount[3]) + " V-Bucks");
                    pbucks.setText(String.valueOf(pbucksAmount[3]));

                    break;
            }

        }


    }
}
