package android.bignerdranch.com.mygymbuddy;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkSet extends AppCompatActivity implements View.OnClickListener {

    TextView setExercise, workSets, workReps, currentSet, record, here;
    TextView set1, set2, set3, set4, set5, currentWeight, currentReps, actualWeight1, actualWeight2, actualWeight3, actualWeight4, actualWeight5
            , actualReps1, actualReps2, actualReps3, actualReps4, actualReps5;

    TextView preWeight1, preWeight2, preWeight3, preWeight4, preWeight5, preRep1, preRep2, preRep3, preRep4, preRep5;

    String weightRef, repRef;

    LinearLayout row1, row2, row3, row4, row5;
    View line1, line2, line3, line4, line5;
    Button saveSet, nextExercise, bFinishWorkout;
    WorkoutLocalStore mWorkoutLocalStore;
    int exNumber = 1, i = 1, numSets;
    ArrayList<String> workoutData= new ArrayList<>();
    Context mContext;

    WorkoutPlan workoutPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_set);

        mContext = this;

        preWeight1 = (TextView) findViewById(R.id.preWeight1);
        preWeight2 = (TextView) findViewById(R.id.preWeight2);
        preWeight3 = (TextView) findViewById(R.id.preWeight3);
        preWeight4 = (TextView) findViewById(R.id.preWeight4);
        preWeight5 = (TextView) findViewById(R.id.preWeight5);

        preRep1 = (TextView) findViewById(R.id.preRep1);
        preRep2 = (TextView) findViewById(R.id.preRep2);
        preRep3 = (TextView) findViewById(R.id.preRep3);
        preRep4 = (TextView) findViewById(R.id.preRep4);
        preRep5 = (TextView) findViewById(R.id.preRep5);

        record = (TextView) findViewById(R.id.record);
        here = (TextView) findViewById(R.id.here);

        set1 = (TextView) findViewById(R.id.set1);
        set2 = (TextView) findViewById(R.id.set2);
        set3 = (TextView) findViewById(R.id.set3);
        set4 = (TextView) findViewById(R.id.set4);
        set5 = (TextView) findViewById(R.id.set5);


        actualWeight1 = (TextView) findViewById(R.id.actualWeight1);
        actualWeight2 = (TextView) findViewById(R.id.actualWeight2);
        actualWeight3 = (TextView) findViewById(R.id.actualWeight3);
        actualWeight4 = (TextView) findViewById(R.id.actualWeight4);
        actualWeight5 = (TextView) findViewById(R.id.actualWeight5);



        actualReps1 = (TextView) findViewById(R.id.actualReps1);
        actualReps2 = (TextView) findViewById(R.id.actualReps2);
        actualReps3 = (TextView) findViewById(R.id.actualReps3);
        actualReps4 = (TextView) findViewById(R.id.actualReps4);
        actualReps5 = (TextView) findViewById(R.id.actualReps5);


        row1 = (LinearLayout) findViewById(R.id.row1);
        row2 = (LinearLayout) findViewById(R.id.row2);
        row3 = (LinearLayout) findViewById(R.id.row3);
        row4 = (LinearLayout) findViewById(R.id.row4);
        row5 = (LinearLayout) findViewById(R.id.row5);


        //row1.setVisibility(View.INVISIBLE);
        row2.setVisibility(View.INVISIBLE);
        row3.setVisibility(View.INVISIBLE);
        row4.setVisibility(View.INVISIBLE);
        row5.setVisibility(View.INVISIBLE);

        line1 = (View) findViewById(R.id.line1);
        line2 = (View) findViewById(R.id.line2);
        line3 = (View) findViewById(R.id.line3);
        line4 = (View) findViewById(R.id.line4);
        line5 = (View) findViewById(R.id.line5);

        //line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        line5.setVisibility(View.INVISIBLE);


        setExercise = (TextView) findViewById(R.id.setExercise);
        workSets = (TextView) findViewById(R.id.workSets);
        workReps = (TextView) findViewById(R.id.workReps);
        currentSet = (TextView) findViewById(R.id.currentSet);
        currentSet.setText("1");

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
        numSets = workoutPlan.numSets1;

        weightRef = workoutPlan.weight1+"";
        repRef = workoutPlan.numReps1+"";

        preWeight1.setText(weightRef);
        preRep1.setText(repRef);
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
        set1.setText("1");


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

                Home.updateDay(Home.getDay()+1);


                startActivity(new Intent(WorkSet.this, Home.class));
                break;

            case R.id.nextExercise:
                saveSet.setVisibility(View.VISIBLE);
                currentSet.setText("1");
                currentReps.setVisibility(View.VISIBLE);
                currentWeight.setVisibility(View.VISIBLE);
                record.setVisibility(View.VISIBLE);
                here.setVisibility(View.VISIBLE);

                row1.setVisibility(View.VISIBLE);


                row2.setVisibility(View.INVISIBLE);
                row3.setVisibility(View.INVISIBLE);
                row4.setVisibility(View.INVISIBLE);
                row5.setVisibility(View.INVISIBLE);

                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.INVISIBLE);
                line4.setVisibility(View.INVISIBLE);
                line5.setVisibility(View.INVISIBLE);

                actualWeight1.setText("");
                actualReps1.setText("");
                actualWeight2.setText("");
                actualReps2.setText("");
                actualWeight3.setText("");
                actualReps3.setText("");
                actualWeight4.setText("");
                actualReps4.setText("");
                actualWeight5.setText("");
                actualReps5.setText("");


                switch (exNumber){

                    case 1:
                        setExercise.setText(workoutPlan.ex1);
                        workSets.setText(""+workoutPlan.numSets1);
                        workReps.setText(""+workoutPlan.numReps1);
                        numSets = workoutPlan.numSets1;
                        Log.d("ADebugTag", "NUM SETS: \n"+numSets);
                        Log.d("ADebugTag", "NUM SETS2: \n"+workoutPlan.numSets1);

                        ImageView pic = (ImageView) findViewById(R.id.exPic);
                        int resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber++;

                        break;

                    case 2:
                        setExercise.setText(workoutPlan.ex2);
                        workSets.setText(""+workoutPlan.numSets2);
                        workReps.setText(""+workoutPlan.numReps2);
                        numSets = workoutPlan.numSets2;

                        weightRef = workoutPlan.weight2+"";
                        repRef = workoutPlan.numReps2+"";
                        preWeight1.setText(weightRef);
                        preRep1.setText(repRef);

                        if(weightRef.equals("0")){
                            preWeight1.setText("BW");
                            currentWeight.setText("BW");
                            currentWeight.setEnabled(false);
                        }

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber =3;

                        break;

                    case 3:
                        setExercise.setText(workoutPlan.ex3);
                        workSets.setText(""+workoutPlan.numSets3);
                        workReps.setText(""+workoutPlan.numReps3);
                        numSets = workoutPlan.numSets3;

                        weightRef = workoutPlan.weight3+"";
                        repRef = workoutPlan.numReps3+"";
                        preWeight1.setText(weightRef);
                        preRep1.setText(repRef);

                        if(weightRef.equals("0")){
                            preWeight1.setText("BW");
                            currentWeight.setText("BW");
                            currentWeight.setEnabled(false);
                        }

                         pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 4;

                        break;

                    case 4:
                        setExercise.setText(workoutPlan.ex4);
                        workSets.setText(""+workoutPlan.numSets4);
                        workReps.setText(""+workoutPlan.numReps4);
                        numSets = workoutPlan.numSets4;

                        weightRef = workoutPlan.weight4+"";
                        repRef = workoutPlan.numReps4+"";
                        preWeight1.setText(weightRef);
                        preRep1.setText(repRef);

                        if(weightRef.equals("0")){
                            preWeight1.setText("BW");
                            currentWeight.setText("BW");
                            currentWeight.setEnabled(false);
                        }

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 5;

                        break;

                    case 5:
                        setExercise.setText(workoutPlan.ex5);
                        workSets.setText(""+workoutPlan.numSets5);
                        workReps.setText(""+workoutPlan.numReps5);
                        numSets = workoutPlan.numSets5;

                        weightRef = workoutPlan.weight5+"";
                        repRef = workoutPlan.numReps5+"";
                        preWeight1.setText(weightRef);
                        preRep1.setText(repRef);

                        if(weightRef.equals("0")){
                            preWeight1.setText("BW");
                            currentWeight.setText("BW");
                            currentWeight.setEnabled(false);
                        }

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 6;

                        break;

                    case 6:
                        setExercise.setText(workoutPlan.ex6);
                        workSets.setText(""+workoutPlan.numSets6);
                        workReps.setText(""+workoutPlan.numReps6);
                        numSets = workoutPlan.numSets6;

                        weightRef = workoutPlan.weight6+"";
                        repRef = workoutPlan.numReps6+"";
                        preWeight1.setText(weightRef);
                        preRep1.setText(repRef);

                        if(weightRef.equals("0")){
                            preWeight1.setText("BW");
                            currentWeight.setText("BW");
                            currentWeight.setEnabled(false);
                        }

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 7;

                        break;

                    case 7:
                        setExercise.setText(workoutPlan.ex7);
                        workSets.setText(""+workoutPlan.numSets7);
                        workReps.setText(""+workoutPlan.numReps7);
                        numSets = workoutPlan.numSets7;

                        weightRef = workoutPlan.weight7+"";
                        repRef = workoutPlan.numReps7+"";
                        preWeight1.setText(weightRef);
                        preRep1.setText(repRef);

                        if(weightRef.equals("0")){
                            preWeight1.setText("BW");
                            currentWeight.setText("BW");
                            currentWeight.setEnabled(false);
                        }

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);

                        exNumber = 8;

                        break;

                    case 8:
                        setExercise.setText(workoutPlan.ex8);
                        workSets.setText(""+workoutPlan.numSets8);
                        workReps.setText(""+workoutPlan.numReps8);
                        numSets = workoutPlan.numSets8;

                        weightRef = workoutPlan.weight8+"";
                        repRef = workoutPlan.numReps8+"";
                        preWeight1.setText(weightRef);
                        preRep1.setText(repRef);

                        if(weightRef.equals("0")){
                            preWeight1.setText("BW");
                            currentWeight.setText("BW");
                            currentWeight.setEnabled(false);
                        }

                        pic = (ImageView) findViewById(R.id.exPic);
                        resID = getResources().getIdentifier(fixName(setExercise.getText().toString()), "drawable", getPackageName());
                        pic.setImageResource(resID);


                        exNumber = 9;

                        break;

                    case 9:
                        LinearLayout ll = (LinearLayout) findViewById(R.id.screen);
                        ll.removeAllViews();
                        Button home = new Button(this);
                        home.setId(R.id.bFinishWorkout);
                        home.setOnClickListener(this);
//                        ViewGroup.LayoutParams lp = home.getLayoutParams();
//                        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        home.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                , ViewGroup.LayoutParams.WRAP_CONTENT));
                        home.setText("Return Home");
                        home.setTextColor(Color.YELLOW);
                        home.setBackgroundColor(Color.BLACK);
                        home.setTextSize(32);
                        ll.addView(home);

                        TextView col1 = new TextView(mContext);
                        TextView col2 = new TextView(mContext);
                        TextView col3 = new TextView(mContext);
                        TextView col4 = new TextView(mContext);
                        TextView col5 = new TextView(mContext);

                        col1.setText("");
                        col1.setTextSize(24);
                        col1.setTextColor(Color.WHITE);
                        col1.setGravity(Gravity.CENTER_HORIZONTAL);


                        col2.setText("Review Your Workout");
                        col2.setTextSize(28);
                        col2.setTextColor(Color.WHITE);


                        col3.setText("                      Prescribed                 Actual");
                        col3.setTextSize(28);
                        col3.setTextColor(Color.WHITE);
                        col3.setGravity(Gravity.CENTER_HORIZONTAL);


                        TextView line = new TextView(mContext);
                        line.setTextSize(4);
                        line.setTextColor(Color.WHITE);
                        line.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "__________________________________________________");
                        line.setPadding(0, 0, 0, 40);


                        ll.addView(col1);
                        ll.addView(col2);
                        ll.addView(line);
                        ll.addView(col3);


                        ScrollView scroll = new ScrollView(mContext);
                        home.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                , ViewGroup.LayoutParams.MATCH_PARENT));
                        ll.addView(scroll);


                        TableLayout table = new TableLayout(mContext);
                        table.setGravity(Gravity.CENTER);
                        scroll.addView(table);


                        TableRow row = new TableRow(mContext);
                        home.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                , ViewGroup.LayoutParams.WRAP_CONTENT));

                        TextView col1r2 = new TextView(mContext);
                        TextView col2r2 = new TextView(mContext);
                        TextView col3r2 = new TextView(mContext);
                        TextView col4r2 = new TextView(mContext);
                        TextView col5r2 = new TextView(mContext);

                        col1r2.setText("  Exercise(Set)    ");
                        col1r2.setTextSize(26);
                        col1r2.setTextColor(Color.WHITE);

                        col2r2.setText("Weight     ");
                        col2r2.setTextSize(26);
                        col2r2.setTextColor(Color.WHITE);


                        col3r2.setText("Reps     ");
                        col3r2.setTextSize(26);
                        col3r2.setTextColor(Color.WHITE);

                        col4r2.setText("Weight     ");
                        col4r2.setTextSize(26);
                        col4r2.setTextColor(Color.WHITE);

                        col5r2.setText("Reps");
                        col5r2.setTextSize(26);
                        col5r2.setTextColor(Color.WHITE);

                        TextView line2 = new TextView(mContext);
                        line2.setTextSize(4);
                        line2.setTextColor(Color.WHITE);
                        line2.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line2.setPadding(0, 0, 0, 20);

                        row.addView(col1r2);
                        row.addView(col2r2);
                        row.addView(col3r2);
                        row.addView(col4r2);
                        row.addView(col5r2);
                        table.addView(row);
                        table.addView(line2);


                        int count = 0;

                        for(int i = 1; i<=workoutPlan.numSets1; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setText(" " + workoutPlan.ex1 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            if(workoutPlan.weight1 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight1 + "");
                            }
                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            col3.setText(" "+workoutPlan.numReps1 + "");
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }
                        TextView line3 = new TextView(mContext);
                        line3.setTextSize(4);
                        line3.setTextColor(Color.WHITE);
                        line3.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line3.setPadding(0, 0, 0, 20);
                        table.addView(line3);

                        for(int i = 1; i<=workoutPlan.numSets2; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setText(" " + workoutPlan.ex2 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            if(workoutPlan.weight2 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight2 + "");
                            }                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            col3.setText(" " + workoutPlan.numReps2 + "");
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }
                        TextView line4 = new TextView(mContext);
                        line4.setTextSize(4);
                        line4.setTextColor(Color.WHITE);
                        line4.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line4.setPadding(0, 0, 0, 20);
                        table.addView(line4);

                        for(int i = 1; i<=workoutPlan.numSets3; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setText(" " + workoutPlan.ex3 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            if(workoutPlan.weight3 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight3 + "");
                            }
                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            col3.setText(" "+workoutPlan.numReps3 + "");
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }
                        TextView line5 = new TextView(mContext);
                        line5.setTextSize(4);
                        line5.setTextColor(Color.WHITE);
                        line5.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line5.setPadding(0, 0, 0, 20);
                        table.addView(line5);

                        for(int i = 1; i<workoutPlan.numSets4; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setWidth(30);
                            col1.setText(" " + workoutPlan.ex4 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            if(workoutPlan.weight4 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight4 + "");
                            }
                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            col3.setText(" "+workoutPlan.numReps4 + "");
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }
                        TextView line6 = new TextView(mContext);
                        line6.setTextSize(4);
                        line6.setTextColor(Color.WHITE);
                        line6.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line6.setPadding(0, 0, 0, 20);
                        table.addView(line6);

                        for(int i = 1; i<=workoutPlan.numSets5; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setText(" " + workoutPlan.ex5 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            if(workoutPlan.weight5 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight5 + "");
                            }
                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            col3.setText(" "+workoutPlan.numReps5 + "");
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }
                        TextView line7 = new TextView(mContext);
                        line7.setTextSize(4);
                        line7.setTextColor(Color.WHITE);
                        line7.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line7.setPadding(0, 0, 0, 20);
                        table.addView(line7);

                        for(int i = 1; i<=workoutPlan.numSets6; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setText(" " + workoutPlan.ex6 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            if(workoutPlan.weight6 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight6 + "");
                            }

                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            col3.setText(" "+workoutPlan.numReps6 + "");
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }
                        TextView line8 = new TextView(mContext);
                        line8.setTextSize(4);
                        line8.setTextColor(Color.WHITE);
                        line8.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line8.setPadding(0, 0, 0, 20);
                        table.addView(line8);

                        for(int i = 1; i<=workoutPlan.numSets7; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setText(" " + workoutPlan.ex7 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            col2.setText(" " + workoutPlan.weight7 + "");
                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            if(workoutPlan.weight7 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight7 + "");
                            }
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }
                        TextView line9 = new TextView(mContext);
                        line9.setTextSize(4);
                        line9.setTextColor(Color.WHITE);
                        line9.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "________________________________________________");
                        line9.setPadding(0, 0, 0, 20);
                        table.addView(line9);

                        for(int i = 1; i<=workoutPlan.numSets8; i++){

                            TableRow row2 = new TableRow(mContext);

                            col1 = new TextView(mContext);
                            col1.setText(" " + workoutPlan.ex8 + " (" + i + ")");
                            col1.setTextSize(24);

                            col2 = new TextView(mContext);
                            if(workoutPlan.weight8 == 0){
                                col2.setText(" "+"BW");
                            }else {
                                col2.setText(" " + workoutPlan.weight8 + "");
                            }                            col2.setTextSize(24);


                            col3 = new TextView(mContext);
                            Log.d("ADebugTag", "Weight Number: \n" + workoutPlan.weight1);
                            col3.setText(" "+workoutPlan.numReps8 + "");
                            col3.setTextSize(24);


                            col5 = new TextView(mContext);
                            col5.setText(" "+workoutData.get(count));
                            col5.setTextSize(24);

                            count++;

                            col4 = new TextView(mContext);
                            col4.setText(" "+workoutData.get(count));
                            col4.setTextSize(24);
                            count++;

                            row2.addView(col1);
                            row2.addView(col2);
                            row2.addView(col3);
                            row2.addView(col4);
                            row2.addView(col5);
                            table.addView(row2);

                        }


                        break;



                }

                break;

            case R.id.saveSet:
                switch (i) {
                    case 1:
                        Log.d("ADebugTag", "SET!1");
                        i++;
                        set2.setText("2");
                        currentSet.setText("2");
                        if(weightRef.equals("0")){
                            preWeight2.setText("BW");
                        }else {
                            preWeight2.setText(weightRef);
                        }
                        preRep2.setText(repRef);
                        actualWeight1.setText(currentWeight.getText());
                        actualReps1.setText(currentReps.getText());
                        workoutData.add(currentWeight.getText().toString());
                        workoutData.add(currentReps.getText().toString());
                        currentReps.setText("");
                        if(currentWeight.isEnabled()==true) {
                            currentWeight.setText("");
                        }
                        row2.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.VISIBLE);
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                        break;

                    case 2:
                        Log.d("ADebugTag", "SET!2");
                        i++;
                        set3.setText("3");
                        currentSet.setText("3");
                        actualWeight2.setText(currentWeight.getText());
                        actualReps2.setText(currentReps.getText());
                        workoutData.add(currentWeight.getText().toString());
                        workoutData.add(currentReps.getText().toString());

                        if(weightRef.equals("0")){
                            preWeight3.setText("BW");
                        }else {
                            preWeight3.setText(weightRef);
                        }

                        preRep3.setText(repRef);
                        currentReps.setText("");
                        if(currentWeight.isEnabled()==true) {
                            currentWeight.setText("");
                        }                        row3.setVisibility(View.VISIBLE);
                        line3.setVisibility(View.VISIBLE);
                        inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);



                        break;

                    case 3:
                        Log.d("ADebugTag", "SET!3");
                        i++;
                        set4.setText("4");
                        currentSet.setText("4");
                        actualWeight3.setText(currentWeight.getText());
                        actualReps3.setText(currentReps.getText());
                        workoutData.add(currentWeight.getText().toString());
                        workoutData.add(currentReps.getText().toString());
                        currentReps.setText("");
                        if(currentWeight.isEnabled()==true) {
                            currentWeight.setText("");
                        }
                        inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                        if (numSets < 4) {
                            currentReps.setVisibility(View.INVISIBLE);
                            currentWeight.setVisibility(View.INVISIBLE);
                            currentReps.setText("");
                            currentWeight.setText("");

                            saveSet.setVisibility(View.INVISIBLE);
                            currentSet.setText("Complete");
                            i=1;
                            record.setVisibility(View.INVISIBLE);
                            here.setVisibility(View.INVISIBLE);
                            currentWeight.setEnabled(true);

                        }else {


                            if(weightRef.equals("0")){
                                preWeight4.setText("BW");
                            }else {
                                preWeight4.setText(weightRef);
                            }

                            preRep4.setText(repRef);

                            row4.setVisibility(View.VISIBLE);
                            line4.setVisibility(View.VISIBLE);
                        }

                        break;
                    case 4:
                        Log.d("ADebugTag", "SET!4");
                        i++;

                        set5.setText("5");
                        currentSet.setText("5");
                        actualWeight4.setText(currentWeight.getText());
                        actualReps4.setText(currentReps.getText());
                        workoutData.add(currentWeight.getText().toString());
                        workoutData.add(currentReps.getText().toString());
                        currentReps.setText("");
                        if(currentWeight.isEnabled()==true) {
                            currentWeight.setText("");
                        }
                        inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                        if (numSets < 5) {
                            currentReps.setVisibility(View.INVISIBLE);
                            currentWeight.setVisibility(View.INVISIBLE);
                            currentReps.setText("");
                            currentWeight.setText("");



                            saveSet.setVisibility(View.INVISIBLE);
                            currentSet.setText("Complete");
                            record.setVisibility(View.INVISIBLE);
                            here.setVisibility(View.INVISIBLE);
                            currentWeight.setEnabled(true);


                            i=1;

                        }else {


                            if(weightRef.equals("0")){
                                preWeight5.setText("BW");
                            }else {
                                preWeight5.setText(weightRef);
                            }

                            preRep5.setText(repRef);

                            row5.setVisibility(View.VISIBLE);
                            line5.setVisibility(View.VISIBLE);
                        }


                        break;
                    case 5:
                        Log.d("ADebugTag", "SET!5");

                        set1.setText("1");
                        actualWeight5.setText(currentWeight.getText());
                        actualReps5.setText(currentReps.getText());
                        workoutData.add(currentWeight.getText().toString());
                        workoutData.add(currentReps.getText().toString());


                        inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                        currentReps.setText("");
                        if(currentWeight.isEnabled()==true) {
                            currentWeight.setText("");
                        }                        currentReps.setVisibility(View.INVISIBLE);
                        currentWeight.setVisibility(View.INVISIBLE);

                        saveSet.setVisibility(View.INVISIBLE);
                        currentSet.setText("Complete");

                        record.setVisibility(View.INVISIBLE);

                        here.setVisibility(View.INVISIBLE);

                        currentWeight.setEnabled(true);


                        saveSet.setVisibility(View.INVISIBLE);
                        i=1;

                        Log.d("ADebugTag", "ARRAY" + workoutData);

                        break;


                }



        }


    }

    private String fixName(String ex){
        String temp = ex.toLowerCase();
        temp=temp.replaceAll("\\p{Z}","");
        return temp;


    }


}
