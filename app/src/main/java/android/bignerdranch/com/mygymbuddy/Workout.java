package android.bignerdranch.com.mygymbuddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class Workout extends AppCompatActivity implements View.OnClickListener {
    Button bStartWorkout, bFinishWorkout;
    TextView weekNum, dayNum, ex1, weight1, set1, rep1, ex2, weight2, set2, rep2, ex3, weight3, set3, rep3,
            ex4, weight4, set4, rep4, ex5, weight5, set5, rep5, ex6, weight6, set6, rep6,
            ex7, weight7, set7, rep7, ex8, weight8, set8, rep8;

    WorkoutLocalStore mWorkoutLocalStore;
    UserLocalStore mUserLocalStore;

    WorkoutPlan mWorkoutPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        bStartWorkout = (Button) findViewById(R.id.bStartWorkout);
        bFinishWorkout = (Button) findViewById(R.id.bFinishWorkout);

        dayNum = (TextView) findViewById(R.id.dayNumber);
        weekNum = (TextView) findViewById(R.id.weekNumber);

        ex1 = (TextView) findViewById(R.id.ex1);
        weight1 = (TextView) findViewById(R.id.weight1);
        set1 = (TextView) findViewById(R.id.set1);
        rep1 = (TextView) findViewById(R.id.rep1);

        ex2 = (TextView) findViewById(R.id.ex2);
        weight2 = (TextView) findViewById(R.id.weight2);
        set2 = (TextView) findViewById(R.id.set2);
        rep2 = (TextView) findViewById(R.id.rep2);

        ex3 = (TextView) findViewById(R.id.ex3);
        weight3 = (TextView) findViewById(R.id.weight3);
        set3 = (TextView) findViewById(R.id.set3);
        rep3 = (TextView) findViewById(R.id.rep3);

        ex4 = (TextView) findViewById(R.id.ex4);
        weight4 = (TextView) findViewById(R.id.weight4);
        set4 = (TextView) findViewById(R.id.set4);
        rep4 = (TextView) findViewById(R.id.rep4);

        ex5 = (TextView) findViewById(R.id.ex5);
        weight5 = (TextView) findViewById(R.id.weight5);
        set5 = (TextView) findViewById(R.id.set5);
        rep5 = (TextView) findViewById(R.id.rep5);

        ex6 = (TextView) findViewById(R.id.ex6);
        weight6 = (TextView) findViewById(R.id.weight6);
        set6 = (TextView) findViewById(R.id.set6);
        rep6 = (TextView) findViewById(R.id.rep6);

        ex7 = (TextView) findViewById(R.id.ex7);
        weight7 = (TextView) findViewById(R.id.weight7);
        set7 = (TextView) findViewById(R.id.set7);
        rep7 = (TextView) findViewById(R.id.rep7);

        ex8 = (TextView) findViewById(R.id.ex8);
        weight8 = (TextView) findViewById(R.id.weight8);
        set8 = (TextView) findViewById(R.id.set8);
        rep8 = (TextView) findViewById(R.id.rep8);

        bStartWorkout.setOnClickListener(this);
        bFinishWorkout.setOnClickListener(this);

        mUserLocalStore = new UserLocalStore(this);
        User user = mUserLocalStore.getLoggedInUser();

        String nextWorkout = "";

        String[] beginnerWorkouts = new String[] {"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9"};
        String[] strengthWorkouts = new String[] {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9"};
        String[] weightLoss = new String[] {"w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9"};
        String[] generalFitness = new String[] {"g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8", "g9"};

        if (user.experience == 1) {
            nextWorkout = beginnerWorkouts[user.numWorkouts];
        }else if(user.experience == 2){
            if(user.goals == 1){
                nextWorkout = generalFitness[user.numWorkouts];
            }else if(user.goals == 2){
                nextWorkout = strengthWorkouts[user.numWorkouts];
            }else if(user.goals == 3){
                nextWorkout = weightLoss[user.numWorkouts];
            }
        }

        //mWorkoutPlan = new WorkoutPlan(nextWorkout);

        mWorkoutPlan = new WorkoutPlan("t1");

        mWorkoutLocalStore = new WorkoutLocalStore(this);




    }


    @Override
    public void onStart() {
        super.onStart();
        getWorkout(mWorkoutPlan);
        Log.d("ADebugTag2", "Excercisegtergrtgrtbgrtsbrbrb 2: \n" + mWorkoutPlan.ex1);

    }


    public void getWorkout(WorkoutPlan workoutPlan){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchWorkoutDataInBackground(workoutPlan, new GetWorkoutCallback() {
            @Override
            public void done(WorkoutPlan returnedWorkoutPlan) {
                if (returnedWorkoutPlan == null) {
                    showErrorMessage();
                } else {
                    dayNum.setText("" + returnedWorkoutPlan.day);
                    weekNum.setText("" + returnedWorkoutPlan.week);


                    ex1.setText(returnedWorkoutPlan.ex1);

                    ImageView pic = (ImageView) findViewById(R.id.image1);
                    int resID = getResources().getIdentifier(fixName(ex1.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);


                    if (returnedWorkoutPlan.weight1 == 0) {
                        weight1.setText("BW");
                    } else {
                        weight1.setText("" + returnedWorkoutPlan.weight1);
                    }
                    set1.setText("" + returnedWorkoutPlan.numSets1);
                    rep1.setText("" + returnedWorkoutPlan.numReps1);


                    Log.d("ADebugTag2", "Excercise 2: \n" + returnedWorkoutPlan.ex2);

                    ex2.setText(returnedWorkoutPlan.ex2);

                    pic = (ImageView) findViewById(R.id.image2);
                    resID = getResources().getIdentifier(fixName(ex2.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);

                    if (returnedWorkoutPlan.weight2 == 0) {
                        weight2.setText("BW");
                    } else {
                        weight2.setText("" + returnedWorkoutPlan.weight2);
                    }
                    set2.setText("" + returnedWorkoutPlan.numSets2);
                    rep2.setText("" + returnedWorkoutPlan.numReps2);


                    ex3.setText(returnedWorkoutPlan.ex3);

                    pic = (ImageView) findViewById(R.id.image3);
                    resID = getResources().getIdentifier(fixName(ex3.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);

                    if (returnedWorkoutPlan.weight3 == 0) {
                        weight3.setText("BW");
                    } else {
                        weight3.setText("" + returnedWorkoutPlan.weight3);
                    }
                    set3.setText("" + returnedWorkoutPlan.numSets3);
                    rep3.setText("" + returnedWorkoutPlan.numReps3);


                    ex4.setText(returnedWorkoutPlan.ex4);

                    pic = (ImageView) findViewById(R.id.image4);
                    resID = getResources().getIdentifier(fixName(ex4.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);

                    if (returnedWorkoutPlan.weight4 == 0) {
                        weight4.setText("BW");
                    } else {
                        weight4.setText("" + returnedWorkoutPlan.weight4);
                    }
                    set4.setText("" + returnedWorkoutPlan.numSets4);
                    rep4.setText("" + returnedWorkoutPlan.numReps4);


                    ex5.setText(returnedWorkoutPlan.ex5);

                    pic = (ImageView) findViewById(R.id.image5);
                     resID = getResources().getIdentifier(fixName(ex5.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);

                    if (returnedWorkoutPlan.weight5 == 0) {
                        weight5.setText("BW");
                    } else {
                        weight5.setText("" + returnedWorkoutPlan.weight5);
                    }
                    set5.setText("" + returnedWorkoutPlan.numSets5);
                    rep5.setText("" + returnedWorkoutPlan.numReps5);


                    ex6.setText(returnedWorkoutPlan.ex6);

                    pic = (ImageView) findViewById(R.id.image6);
                    resID = getResources().getIdentifier(fixName(ex6.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);

                    if (returnedWorkoutPlan.weight6 == 0) {
                        weight6.setText("BW");
                    } else {
                        weight6.setText("" + returnedWorkoutPlan.weight6);
                    }
                    set6.setText("" + returnedWorkoutPlan.numSets6);
                    rep6.setText("" + returnedWorkoutPlan.numReps6);


                    ex7.setText(returnedWorkoutPlan.ex7);

                    pic = (ImageView) findViewById(R.id.image7);
                    resID = getResources().getIdentifier(fixName(ex7.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);

                    if (returnedWorkoutPlan.weight7 == 0) {
                        weight7.setText("BW");
                    } else {
                        weight7.setText("" + returnedWorkoutPlan.weight7);
                    }
                    set7.setText("" + returnedWorkoutPlan.numSets7);
                    rep7.setText("" + returnedWorkoutPlan.numReps7);


                    ex8.setText(returnedWorkoutPlan.ex8);

                    pic = (ImageView) findViewById(R.id.image8);
                    resID = getResources().getIdentifier(fixName(ex8.getText().toString()), "drawable", getPackageName());
                    pic.setImageResource(resID);

                    if (returnedWorkoutPlan.weight8 == 0) {
                        weight8.setText("BW");
                    } else {
                        weight8.setText("" + returnedWorkoutPlan.weight8);
                    }
                    set8.setText("" + returnedWorkoutPlan.numSets8);
                    rep8.setText("" + returnedWorkoutPlan.numReps8);

                }

                mWorkoutLocalStore.storeWorkoutData(returnedWorkoutPlan);
                Log.d("ADebugTag2", "Excercisegtergrtgrtbgrtsbrbrb 2: \n" + mWorkoutLocalStore.getCurrentWorkout().ex1);
            }
        });


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bFinishWorkout:
                startActivity(new Intent(Workout.this, Home.class));
                 break;

            case R.id.bStartWorkout:
                startActivity(new Intent(Workout.this, WorkSet.class));
        }

    }



    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Workout.this);
        dialogBuilder.setMessage("Error Loading Workout");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private String fixName(String ex){
        String temp = ex.toLowerCase();
        temp=temp.replaceAll("\\p{Z}","");
        return temp;


    }

}

