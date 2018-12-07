package com.sevenhills.fortniteawards;


        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.Toast;

        import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    main_fragment mainFragment=new main_fragment();
    AwardsFragment awardsFragment=new AwardsFragment();
    SettingFargment settingFargment=new SettingFargment();
    BetterList betterList=new BetterList();
    FragmentManager fm =getFragmentManager();
    FragmentTransaction mainTrans=fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView setting = (ImageView) findViewById(R.id.setting_text);
        setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = getApplicationContext();
                Toast toast;
                toast = Toast.makeText(context, "setting", LENGTH_SHORT);
                toast.show();
                mainTrans.replace(R.id.fragment,settingFargment);
                mainTrans.commit();
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
                Context context = getApplicationContext();
                Toast toast;
                toast = Toast.makeText(context, "setting", LENGTH_SHORT);
                toast.show();
                mainTrans.add(R.id.left_frgament,betterList);
                mainTrans.commit();;
            }
        });

        LinearLayout wallet = (LinearLayout) findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = getApplicationContext();
                Toast toast;
                toast = Toast.makeText(context, "awards", LENGTH_SHORT);
                toast.show();
                mainTrans.replace(R.id.fragment,awardsFragment);
                mainTrans.commit();
            }
        });

    }

}
