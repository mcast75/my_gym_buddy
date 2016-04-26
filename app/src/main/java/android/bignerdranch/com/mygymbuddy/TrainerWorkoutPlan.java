package android.bignerdranch.com.mygymbuddy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TrainerWorkoutPlan extends AppCompatActivity implements View.OnClickListener {

    Button bLogout, bNext;

    Spinner ex1spinner, ex2spinner, ex3spinner, ex4spinner, ex5spinner, ex6spinner, ex7spinner, ex8spinner,
            set1spinner, set2spinner, set3spinner, set4spinner, set5spinner, set6spinner, set7spinner, set8spinner,
            rep1spinner, rep2spinner, rep3spinner, rep4spinner, rep5spinner, rep6spinner, rep7spinner, rep8spinner,
            wt1Spinner, wt2Spinner, wt3Spinner, wt4Spinner, wt5Spinner, wt6Spinner, wt7Spinner, wt8Spinner, clients;

    EditText etPlanName;

    String ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8;
    int s1,s2,s3,s4,s5,s6,s7,s8, r1,r2,r3,r4,r5,r6,r7,r8, w1,w2,w3,w4,w5,w6,w7,w8;

    WorkoutPlan plan;
    String[] clientArray = new String[6];
    TrainerLocalStore mTrainerLocalStore;



    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_workout_plan);

        clients = (Spinner) findViewById(R.id.clients);

        etPlanName = (EditText) findViewById(R.id.etPlanName);

        ex1spinner = (Spinner) findViewById(R.id.exSpinner1);
        set1spinner = (Spinner) findViewById(R.id.setSpinner1);
        rep1spinner = (Spinner) findViewById(R.id.repSpinner1);

        ex2spinner = (Spinner) findViewById(R.id.exSpinner2);
        set2spinner = (Spinner) findViewById(R.id.setSpinner2);
        rep2spinner = (Spinner) findViewById(R.id.repSpinner2);

        ex3spinner = (Spinner) findViewById(R.id.exSpinner3);
        set3spinner = (Spinner) findViewById(R.id.setSpinner3);
        rep3spinner = (Spinner) findViewById(R.id.repSpinner3);

        ex4spinner = (Spinner) findViewById(R.id.exSpinner4);
        set4spinner = (Spinner) findViewById(R.id.setSpinner4);
        rep4spinner = (Spinner) findViewById(R.id.repSpinner4);

        ex5spinner = (Spinner) findViewById(R.id.exSpinner5);
        set5spinner = (Spinner) findViewById(R.id.setSpinner5);
        rep5spinner = (Spinner) findViewById(R.id.repSpinner5);

        ex6spinner = (Spinner) findViewById(R.id.exSpinner6);
        set6spinner = (Spinner) findViewById(R.id.setSpinner6);
        rep6spinner = (Spinner) findViewById(R.id.repSpinner6);

        ex7spinner = (Spinner) findViewById(R.id.exSpinner7);
        set7spinner = (Spinner) findViewById(R.id.setSpinner7);
        rep7spinner = (Spinner) findViewById(R.id.repSpinner7);

        ex8spinner = (Spinner) findViewById(R.id.exSpinner8);
        set8spinner = (Spinner) findViewById(R.id.setSpinner8);
        rep8spinner = (Spinner) findViewById(R.id.repSpinner8);

        wt1Spinner = (Spinner) findViewById(R.id.wtSpinner1);
        wt2Spinner = (Spinner) findViewById(R.id.wtSpinner2);
        wt3Spinner = (Spinner) findViewById(R.id.wtSpinner3);
        wt4Spinner = (Spinner) findViewById(R.id.wtSpinner4);
        wt5Spinner = (Spinner) findViewById(R.id.wtSpinner5);
        wt6Spinner = (Spinner) findViewById(R.id.wtSpinner6);
        wt7Spinner = (Spinner) findViewById(R.id.wtSpinner7);
        wt8Spinner = (Spinner) findViewById(R.id.wtSpinner8);


        String[] exercises = new String[]{"Exercise 1", "Bench Press", "Squat", "Deadlift", "Shoulder Press", "Incline Press", "DB Row", "DB Lunges", "DB Curls", "Push Ups", "DB Flies", "Hanging Leg Raises", "Med Ball Drop", "Overhead Press", "Hamstring Curls", "Chin Ups", "Box Jumps"};
        ArrayAdapter<String> exAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, exercises);
        ex1spinner.setAdapter(exAdapter);
        ex2spinner.setAdapter(exAdapter);
        ex3spinner.setAdapter(exAdapter);
        ex4spinner.setAdapter(exAdapter);
        ex5spinner.setAdapter(exAdapter);
        ex6spinner.setAdapter(exAdapter);
        ex7spinner.setAdapter(exAdapter);
        ex8spinner.setAdapter(exAdapter);


        Integer[] sets = new Integer[]{1,2,3,4,5,6};
        ArrayAdapter<Integer> setAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, sets);
        set1spinner.setAdapter(setAdapter);
        set2spinner.setAdapter(setAdapter);
        set3spinner.setAdapter(setAdapter);
        set4spinner.setAdapter(setAdapter);
        set5spinner.setAdapter(setAdapter);
        set6spinner.setAdapter(setAdapter);
        set7spinner.setAdapter(setAdapter);
        set8spinner.setAdapter(setAdapter);


        Integer[] reps = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        ArrayAdapter<Integer> repAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, reps);
        rep1spinner.setAdapter(repAdapter);
        rep2spinner.setAdapter(repAdapter);
        rep3spinner.setAdapter(repAdapter);
        rep4spinner.setAdapter(repAdapter);
        rep5spinner.setAdapter(repAdapter);
        rep6spinner.setAdapter(repAdapter);
        rep7spinner.setAdapter(repAdapter);
        rep8spinner.setAdapter(repAdapter);

        Integer[] weights = new Integer[]{0, 40, 50, 55,60, 65, 70, 75, 80, 85, 90, 95, 100, 105};
        ArrayAdapter<Integer> wAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, weights);
        wt1Spinner.setAdapter(wAdapter);
        wt2Spinner.setAdapter(wAdapter);
        wt3Spinner.setAdapter(wAdapter);
        wt4Spinner.setAdapter(wAdapter);
        wt5Spinner.setAdapter(wAdapter);
        wt6Spinner.setAdapter(wAdapter);
        wt7Spinner.setAdapter(wAdapter);
        wt8Spinner.setAdapter(wAdapter);





        bLogout = (Button) findViewById(R.id.bLogout2);
        bLogout.setOnClickListener(this);

        bNext = (Button) findViewById(R.id.bNext);
        bNext.setOnClickListener(this);

        mTrainerLocalStore = new TrainerLocalStore(this);

        mContext = this;



    }


    @Override
    protected void onStart(){
        super.onStart();
        getClientList(mTrainerLocalStore.getLoggedInTrainer());

    }


    private void getClientList(Trainer trainer){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.fetchClientListInBackground(trainer, new GetClientListCallback() {
            @Override
            public void done(ClientForum returnedForum) {
                ArrayList<User> temp;
                clientArray[0] = "Select A Client";
                int i = 0;
                temp = returnedForum.getAllClients();


                for (i = 1; i <= temp.size(); i++) {
                    clientArray[i] = temp.get(i-1).name;
                    Log.d("ADebugTag", "Client: \n" + i+" "+clientArray[i]);

                }

                ArrayAdapter<String> clientAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, clientArray);

                clients.setAdapter(clientAdapter);
            }


        });
    }



    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bNext:

                ex1 = ex1spinner.getSelectedItem().toString();
                ex2 = ex2spinner.getSelectedItem().toString();
                ex3 = ex3spinner.getSelectedItem().toString();
                ex4 = ex4spinner.getSelectedItem().toString();
                ex5 = ex5spinner.getSelectedItem().toString();
                ex6 = ex6spinner.getSelectedItem().toString();
                ex7 = ex7spinner.getSelectedItem().toString();
                ex8 = ex8spinner.getSelectedItem().toString();

                s1 = (Integer) set1spinner.getSelectedItem();
                s2 = (Integer) set2spinner.getSelectedItem();
                s3 = (Integer) set3spinner.getSelectedItem();
                s4 = (Integer) set4spinner.getSelectedItem();
                s5 = (Integer) set5spinner.getSelectedItem();
                s6 = (Integer) set6spinner.getSelectedItem();
                s7 = (Integer) set7spinner.getSelectedItem();
                s8 = (Integer) set8spinner.getSelectedItem();

                r1 = (Integer) rep1spinner.getSelectedItem();
                r2 = (Integer) rep2spinner.getSelectedItem();
                r3 = (Integer) rep3spinner.getSelectedItem();
                r4 = (Integer) rep4spinner.getSelectedItem();
                r5 = (Integer) rep5spinner.getSelectedItem();
                r6 = (Integer) rep6spinner.getSelectedItem();
                r7 = (Integer) rep7spinner.getSelectedItem();
                r8 = (Integer) rep8spinner.getSelectedItem();

                w1 = (Integer) wt1Spinner.getSelectedItem();
                w2 = (Integer) wt2Spinner.getSelectedItem();
                w3 = (Integer) wt3Spinner.getSelectedItem();
                w4 = (Integer) wt4Spinner.getSelectedItem();
                w5 = (Integer) wt5Spinner.getSelectedItem();
                w6 = (Integer) wt6Spinner.getSelectedItem();
                w7 = (Integer) wt7Spinner.getSelectedItem();
                w8 = (Integer) wt8Spinner.getSelectedItem();


                plan = new WorkoutPlan("b1", etPlanName.getText().toString(), 1, 1, ex1, s1, r1, w1, ex2, s2, r2,w2, ex3, s3, r3, w3,
                        ex4, s4, r4, w4, ex5, s5, r5, w5, ex6, s6, r6, w6, ex7, s7, r7, w7, ex8, s8, r8, w8);


                publishWorkout(plan);

                break;




            case R.id.bLogout2:

                startActivity(new Intent(TrainerWorkoutPlan.this, HomeTrainer.class));
                break;

        }



    }


    private void publishWorkout(WorkoutPlan workoutPlan){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.storeWorkoutDataInBackground(workoutPlan, new GetWorkoutCallback() {
            @Override
            public void done(WorkoutPlan plan) {
                startActivity(new Intent(TrainerWorkoutPlan.this, HomeTrainer.class));
            }

        });
    }




}





