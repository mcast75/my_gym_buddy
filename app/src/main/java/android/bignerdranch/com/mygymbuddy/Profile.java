package android.bignerdranch.com.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    Button bProfHome;
    TextView tvProfName, tvProfHeightFt, tvProfHeightIn, tvProfWeight, tvProfBM, tvProfSM, tvProfDM, tvProfExp, tvProfGoals;
    UserLocalStore mUserLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvProfName = (TextView) findViewById(R.id.profName);
        tvProfHeightFt = (TextView) findViewById(R.id.profHeightFt);
        tvProfHeightIn = (TextView) findViewById(R.id.profHeightIn);
        tvProfWeight = (TextView) findViewById(R.id.profWeight);
        tvProfBM = (TextView) findViewById(R.id.profBM);
        tvProfSM = (TextView) findViewById(R.id.profSM);
        tvProfDM = (TextView) findViewById(R.id.profDM);
        tvProfExp = (TextView) findViewById(R.id.profExp);
        tvProfGoals = (TextView) findViewById(R.id.goals);



        bProfHome = (Button) findViewById(R.id.profHome);

        bProfHome.setOnClickListener(this);

        mUserLocalStore = new UserLocalStore(this);
    }


    @Override
    public void onStart() {
        super.onStart();


        User user = mUserLocalStore.getLoggedInUser();
        Log.d("PROFILE", "Value: \n" + user.name + user.heightFt);


        tvProfName.setText(user.name);
        tvProfHeightFt.setText("" + user.heightFt);
        tvProfHeightIn.setText("" + user.heightIn);
        tvProfWeight.setText("" + user.weight);

        if (user.benchMax == -1) {
            tvProfBM.setText("Not Given");
        } else{
            tvProfBM.setText("" + user.benchMax);
        }

        if (user.squatMax == -1) {
            tvProfSM.setText("Not Given");
        } else{
            tvProfSM.setText("" + user.squatMax);
        }

        if (user.deadMax == -1) {
            tvProfDM.setText("Not Given");
        } else{
            tvProfDM.setText("" + user.deadMax);
        }

        Log.d("PROFILE", "USER EXP: \n" + user.goals);

        if (user.experience == 1){
            tvProfExp.setText("Beginner");
        }else if(user.experience == 2){
            tvProfExp.setText("Experienced");
        }

        if (user.goals == 1){
            tvProfGoals.setText("General Fitness");
        }else if(user.experience == 2){
            tvProfGoals.setText("Strength");
        }else if(user.experience == 2) {
            tvProfGoals.setText("Weight Loss");
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profHome:
                startActivity(new Intent(Profile.this, Home.class));


                break;
        }

    }
}



