package android.bignerdranch.com.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener{

    Button bProfile, bWorkout, bHowIFeel, bLogout;
    UserLocalStore mUserLocalStore;
    TextView name, dayNum;
    static int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bProfile = (Button) findViewById(R.id.bProfile);
        bWorkout = (Button) findViewById(R.id.bWorkout);
        bHowIFeel = (Button) findViewById(R.id.bHowIFeel);
        bLogout = (Button) findViewById(R.id.bLogout);
        bWorkout = (Button) findViewById(R.id.bWorkout);
        name = (TextView) findViewById(R.id.name);
        dayNum = (TextView) findViewById(R.id.dayNum);

        bProfile.setOnClickListener(this);
        bLogout.setOnClickListener(this);
        bWorkout.setOnClickListener(this);
        bHowIFeel.setOnClickListener(this);

        mUserLocalStore = new UserLocalStore(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bProfile:
                startActivity(new Intent(this, Profile.class));

                break;

            case R.id.bWorkout:
                startActivity(new Intent(this, Workout.class));
                break;

            case R.id.bLogout:

                mUserLocalStore.clearUserData();
                mUserLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, GymActivity.class));
                break;

            case R.id.bHowIFeel:
                startActivity(new Intent(this, HowIFeel.class));
                break;

        }
    }

    @Override
    public void onStart(){
        super.onStart();
        if(day == 0)
            day = 1;

        name.setText(mUserLocalStore.getLoggedInUser().name);
        dayNum.setText(day+"");

    }

    public static void updateDay(int i){
        day = i;
    }

    public static int getDay(){
        return day;
    }
}
