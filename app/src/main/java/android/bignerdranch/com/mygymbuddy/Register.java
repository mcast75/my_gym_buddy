package android.bignerdranch.com.mygymbuddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    LinearLayout lLGoalsLayout, maxNumbers;
    TextView tvGoalsText, maxText;
    EditText etName, etHeightFt, etHeightIn, etWeight, etUsername, etPassword, etBenchMax, etSquatMax, etDeadMax;
    CheckBox cbBeginner, cbExperienced, cbGeneralFitness, cbStrength, cbWeightLoss;
    int experience, goals, flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etHeightFt = (EditText) findViewById(R.id.etHeightFt);
        etHeightIn = (EditText) findViewById(R.id.etHeightIn);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etBenchMax = (EditText) findViewById(R.id.etBenchMax);
        etSquatMax = (EditText) findViewById(R.id.etSquatMax);
        etDeadMax = (EditText) findViewById(R.id.etDeadMax);

        cbBeginner = (CheckBox) findViewById(R.id.cbBeginner);
        cbExperienced = (CheckBox) findViewById(R.id.cbExperienced);
        cbGeneralFitness = (CheckBox) findViewById(R.id.cbGeneralFitness);
        cbStrength = (CheckBox) findViewById(R.id.cbStrength);
        cbWeightLoss = (CheckBox) findViewById(R.id.cbWeightLoss);
        bRegister = (Button) findViewById(R.id.bRegister);

        lLGoalsLayout = (LinearLayout) findViewById(R.id.goalsLayout);
        tvGoalsText = (TextView) findViewById(R.id.goalsText);

        maxNumbers = (LinearLayout) findViewById(R.id.maxNumbers);
        maxText = (TextView) findViewById(R.id.maxText);


        lLGoalsLayout.setVisibility(View.INVISIBLE);
        tvGoalsText.setVisibility(View.INVISIBLE);

        maxText.setVisibility(View.INVISIBLE);
        maxNumbers.setVisibility(View.INVISIBLE);

        bRegister.setOnClickListener(this);
    }


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.cbBeginner:
                if(checked) {
                    experience = 1;
                    cbExperienced.setEnabled(false);
                    lLGoalsLayout.setVisibility(View.INVISIBLE);
                    tvGoalsText.setVisibility(View.INVISIBLE);

                }else if(!checked){
                    cbBeginner.setEnabled(true);
                    cbExperienced.setEnabled(true);
                }
                break;

            case R.id.cbExperienced:
                if(checked) {
                    experience = 2;
                    cbBeginner.setEnabled(false);
                    lLGoalsLayout.setVisibility(View.VISIBLE);
                    tvGoalsText.setVisibility(View.VISIBLE);
                    maxText.setVisibility(View.VISIBLE);
                    maxNumbers.setVisibility(View.VISIBLE);
                }else if(!checked){
                    cbExperienced.setEnabled(true);
                    cbBeginner.setEnabled(true);
                    lLGoalsLayout.setVisibility(View.INVISIBLE);
                    tvGoalsText.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.cbGeneralFitness:
                if(checked) {
                    goals = 1;
                    cbStrength.setEnabled(false);
                    cbWeightLoss.setEnabled(false);
                }else if(!checked) {
                    cbStrength.setEnabled(true);
                    cbWeightLoss.setEnabled(true);
                    cbGeneralFitness.setEnabled(true);
                }
                break;
            case R.id.cbStrength:
                if(checked) {
                    goals = 2;
                    cbWeightLoss.setEnabled(false);
                    cbGeneralFitness.setEnabled(false);
                }else if(!checked) {
                    cbStrength.setEnabled(true);
                    cbWeightLoss.setEnabled(true);
                    cbGeneralFitness.setEnabled(true);
                }
                break;
            case R.id.cbWeightLoss:
                if(checked) {
                    goals = 3;
                    cbStrength.setEnabled(false);
                    cbGeneralFitness.setEnabled(false);
                }else if(!checked) {
                    cbStrength.setEnabled(true);
                    cbWeightLoss.setEnabled(true);
                    cbGeneralFitness.setEnabled(true);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {

        if (etName.getText().toString().matches("")) {
            showErrorMessage("Please Enter Your Name");

        } else if (etHeightFt.getText().toString().matches("")) {
            showErrorMessage("Please Fill In Both Height Fields");

        } else if (etHeightIn.getText().toString().matches("")) {
            showErrorMessage("Please Fill In Both Height Fields");

        } else if (!cbBeginner.isChecked() && !cbExperienced.isChecked()) {
            showErrorMessage("Please Select an Experience Level");

        }else if(cbExperienced.isChecked() && !(cbWeightLoss.isChecked() || cbGeneralFitness.isChecked() || cbStrength.isChecked())) {
            showErrorMessage("Please Select a Fitness Goal");
            
        }else if(cbExperienced.isChecked() && (etBenchMax.getText().toString().matches("") || etSquatMax.getText().toString().matches("") || etDeadMax.getText().toString().matches(""))){
            showErrorMessage("You Have Selected Experienced. Please Fill In The Required 1 Rep Maxes");

        } else if (etUsername.getText().toString().matches("")) {
            showErrorMessage("Please Enter A Username Name");

        } else if (etPassword.getText().toString().matches("")) {
            showErrorMessage("Please Enter A Password");


        }else {


            switch (v.getId()) {
                case R.id.bRegister:

                    Log.d("Register", "Value: \n\n\n\n\n" + etName.getText().toString() + " " + etUsername.getText().toString() + " " + etPassword.getText().toString() +
                            " " + etHeightFt.getText().toString() + " " + etHeightIn.getText().toString() + " " + etWeight.getText().toString());

                    Log.d("Register", "Value: \n\n\n\n\n" + etName.getText().toString() + " " + etUsername.getText().toString() + " " + etPassword.getText().toString() +
                            " " + etHeightFt.getText().toString() + " " + etHeightIn.getText().toString() + " " + etWeight.getText().toString());
                    String name = etName.getText().toString();
                    String userName = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    int heightFt = Integer.parseInt(etHeightFt.getText().toString());
                    int heightIn = Integer.parseInt(etHeightIn.getText().toString());
                    int weight = -1;
                    int benchmax = -1;
                    int squatmax = -1;
                    int deadmax = -1;

                    if (!etWeight.getText().toString().matches("")) {
                        weight = Integer.parseInt(etWeight.getText().toString());
                    }

                    if (!etBenchMax.getText().toString().matches("")) {
                        benchmax = Integer.parseInt(etBenchMax.getText().toString());
                    }
                    if (!etSquatMax.getText().toString().matches("")) {
                        squatmax = Integer.parseInt(etSquatMax.getText().toString());
                    }
                    if (!etDeadMax.getText().toString().matches("")) {
                        deadmax = Integer.parseInt(etDeadMax.getText().toString());
                    }


                    int numWorkouts = 0;
                    int trainer = 0;


                    User user = new User(name, userName, password, heightFt, heightIn, weight, benchmax, squatmax, deadmax, experience, goals, numWorkouts, trainer);


                    registerUser(user);


                    break;


            }
        }
    }



    private void showErrorMessage(String errorMsg){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Register.this);
        dialogBuilder.setMessage(errorMsg);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void registerUser(User user){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.storeUserDataInBackground(user, new GetUserCallback(){
            @Override
        public void done(User returnedUser){
               startActivity(new Intent(Register.this, Login.class));
            }

        });
    }


}
