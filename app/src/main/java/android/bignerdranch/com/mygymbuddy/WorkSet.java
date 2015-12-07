package android.bignerdranch.com.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkSet extends AppCompatActivity implements View.OnClickListener {

    TextView setExercise, workSets, workReps, currentSet;
    EditText currentWeight, currentReps;
    Button saveSet, nextExercise, bFinishWorkout;
    WorkoutLocalStore mWorkoutLocalStore;
    int exNumber = 1;

    WorkoutPlan workoutPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_set);

        setExercise = (TextView) findViewById(R.id.setExercise);
        workSets = (TextView) findViewById(R.id.workSets);
        workReps = (TextView) findViewById(R.id.workReps);
        currentSet = (TextView) findViewById(R.id.currentSet);

        currentWeight = (EditText) findViewById(R.id.currentWeight);
        currentReps = (EditText) findViewById(R.id.currentReps);

        saveSet = (Button) findViewById(R.id.saveSet);
        nextExercise = (Button) findViewById(R.id.nextExercise);
        bFinishWorkout = (Button) findViewById(R.id.bFinishWorkout);

        saveSet.setOnClickListener(this);
        nextExercise.setOnClickListener(this);
        bFinishWorkout.setOnClickListener(this);

        mWorkoutLocalStore = new WorkoutLocalStore(this);


        workoutPlan = mWorkoutLocalStore.getCurrentWorkout();

        Log.d("ADebugTag", "WORKOUT DETAILS: \n"+workoutPlan.planName);




    }

    @Override
    public void onStart(){
        super.onStart();
        WorkoutPlan workoutPlan = mWorkoutLocalStore.getCurrentWorkout();

        setExercise.setText(workoutPlan.ex1);
        workSets.setText(""+workoutPlan.numSets1);
        workReps.setText(""+workoutPlan.numReps1);

        ImageView pic = (ImageView) findViewById(R.id.exPic);
        int resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
        pic.setImageResource(resID);

        exNumber = 2;


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_work_set, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bFinishWorkout:
                startActivity(new Intent(WorkSet.this, Home.class));
                break;

            case R.id.nextExercise:
                switch (exNumber){

                    case 1:
                        setExercise.setText(workoutPlan.ex1);
                        workSets.setText(""+workoutPlan.numSets1);
                        workReps.setText(""+workoutPlan.numReps1);

                        ImageView pic = (ImageView) findViewById(R.id.exPic);
                        int resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber++;

                        break;

                    case 2:
                        setExercise.setText(workoutPlan.ex2);
                        workSets.setText(""+workoutPlan.numSets2);
                        workReps.setText(""+workoutPlan.numReps2);

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber =3;

                        break;

                    case 3:
                        setExercise.setText(workoutPlan.ex3);
                        workSets.setText(""+workoutPlan.numSets3);
                        workReps.setText(""+workoutPlan.numReps3);

                         pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 4;

                        break;

                    case 4:
                        setExercise.setText(workoutPlan.ex4);
                        workSets.setText(""+workoutPlan.numSets4);
                        workReps.setText(""+workoutPlan.numReps4);

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 5;

                        break;

                    case 5:
                        setExercise.setText(workoutPlan.ex5);
                        workSets.setText(""+workoutPlan.numSets5);
                        workReps.setText(""+workoutPlan.numReps5);

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 6;

                        break;

                    case 6:
                        setExercise.setText(workoutPlan.ex6);
                        workSets.setText(""+workoutPlan.numSets6);
                        workReps.setText(""+workoutPlan.numReps6);

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 7;

                        break;

                    case 7:
                        setExercise.setText(workoutPlan.ex7);
                        workSets.setText(""+workoutPlan.numSets7);
                        workReps.setText(""+workoutPlan.numReps7);

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 8;

                        break;

                    case 8:
                        setExercise.setText(workoutPlan.ex8);
                        workSets.setText(""+workoutPlan.numSets8);
                        workReps.setText(""+workoutPlan.numReps8);

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);


                        exNumber = 9;

                        break;

                }

                break;

        }

    }

    private String fixName(String ex){
        String temp = ex.toLowerCase();
        temp=temp.replaceAll("\\p{Z}","");
        return temp;


    }


}
