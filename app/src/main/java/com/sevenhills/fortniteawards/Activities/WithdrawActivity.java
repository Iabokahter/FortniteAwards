package com.sevenhills.fortniteawards.Activities;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.support.v4.app.ActivityOptionsCompat;
        import android.support.v4.view.ViewCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.CardView;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.sevenhills.fortniteawards.R;


public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener {
    VbucksCard V1,V2,V3,V4;
    public int []vbucksAmount =  {1000,2800,7500,13500};
    public int []pbucksAmount =  {10000,20000,40000,70000};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdraw_layout);
        V1 = new VbucksCard(R.id.card1,1);
        V2 = new VbucksCard(R.id.card2,2);
        V3= new VbucksCard(R.id.card3,3);
        V4 = new VbucksCard(R.id.card4,4);






    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CompleteWithdrawActivity.class);
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this, (View)ivProfile, "profile");
//        intent.putExtra(CompleteWithdrawActivity.EXTRA_CONTACT, contact);
//        startActivity(intent, options.toBundle());
    }

    class VbucksCard {

        View cardView;
        ImageView image;
        TextView vbucks,pbucks;

        @SuppressLint("SetTextI18n")
        VbucksCard (int id, int amount) {
            cardView = findViewById(id);
            image = cardView.findViewById(R.id.coin_image);
            vbucks = cardView.findViewById(R.id.vbucksAmount);
            pbucks = cardView.findViewById(R.id.pbucks);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CompleteWithdrawActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(WithdrawActivity.this,image,ViewCompat.getTransitionName(image));
                    startActivity(intent,options.toBundle());
                }
            });
            switch (amount){

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
