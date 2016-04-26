package android.bignerdranch.com.mygymbuddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.provider.ContactsContract;
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

public class RegisterTrainer extends AppCompatActivity implements View.OnClickListener {

    Button bRegisterTrainer;
    EditText etNameTrainer, etEmail, etAgeTrainer, etExperienceTrainer, etPasswordTrainer;
    CheckBox cbBeginnerTrainer, cbGeneralFitnessTrainer, cbStrengthTrainer, cbWeightLossTrainer;
    int focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_trainer);

        etNameTrainer = (EditText) findViewById(R.id.etNameTrainer);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAgeTrainer = (EditText) findViewById(R.id.etAgeTrainer);
        etPasswordTrainer = (EditText) findViewById(R.id.etPasswordTrainer);
        etExperienceTrainer = (EditText) findViewById(R.id.etExperienceTrainer);

        cbBeginnerTrainer = (CheckBox) findViewById(R.id.cbBeginnerClient);
        cbGeneralFitnessTrainer= (CheckBox) findViewById(R.id.cbGeneralFitnessClient);
        cbStrengthTrainer = (CheckBox) findViewById(R.id.cbStrengthClient);
        cbWeightLossTrainer = (CheckBox) findViewById(R.id.cbWeightLossClient);


        bRegisterTrainer = (Button) findViewById(R.id.bRegisterTrainer);

        bRegisterTrainer.setOnClickListener(this);
    }


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.cbBeginnerClient:
                if (checked) {
                    cbWeightLossTrainer.setEnabled(false);
                    cbStrengthTrainer.setEnabled(false);
                    cbGeneralFitnessTrainer.setEnabled(false);
                    focus =0;
                }
                break;

            case R.id.cbGeneralFitnessClient:
                if (checked) {
                    cbWeightLossTrainer.setEnabled(false);
                    cbStrengthTrainer.setEnabled(false);
                    cbBeginnerTrainer.setEnabled(false);
                    focus = 1;
                }
                break;

            case R.id.cbStrengthClient:
                if (checked) {
                    cbWeightLossTrainer.setEnabled(false);
                    cbBeginnerTrainer.setEnabled(false);
                    cbGeneralFitnessTrainer.setEnabled(false);
                    focus = 2;
                }
                break;

            case R.id.cbWeightLossClient:
                if (checked) {
                    cbBeginnerTrainer.setEnabled(false);
                    cbStrengthTrainer.setEnabled(false);
                    cbGeneralFitnessTrainer.setEnabled(false);
                    focus = 3;
                }
                break;

        }

    }

    @Override
    public void onClick(View v) {

        if (etNameTrainer.getText().toString().matches("")) {
            showErrorMessage("Please Enter Your Name");

        } else if (etAgeTrainer.getText().toString().matches("")) {
            showErrorMessage("Please Fill In Both Height Fields");

        } else if (etExperienceTrainer.getText().toString().matches("")) {
            showErrorMessage("Please Fill In Your Experience");

        } else if (etEmail.getText().toString().matches("")) {
            showErrorMessage("Please Fill In Your Contact Email");

        } else if (etPasswordTrainer.getText().toString().matches("")) {
            showErrorMessage("Please Enter A Password");

        } else if (!cbBeginnerTrainer.isChecked() && !cbGeneralFitnessTrainer.isChecked() && !cbStrengthTrainer.isChecked() && !cbWeightLossTrainer.isChecked()) {
            showErrorMessage("Please Select an Specialty Focus");


        }else {


            switch (v.getId()) {
                case R.id.bRegisterTrainer:

                    String name = etNameTrainer.getText().toString();
                    String email = etEmail.getText().toString();
                    String password = etPasswordTrainer.getText().toString();
                    int age = Integer.parseInt(etAgeTrainer.getText().toString());
                    int experience = Integer.parseInt(etExperienceTrainer.getText().toString());
                    int focusSend = focus;
                    int numClients = 0;


                    Trainer trainer = new Trainer(name, email, password, age, experience, focusSend, numClients);


                    registerTrainer(trainer);


                    break;


            }
        }
    }



    private void showErrorMessage(String errorMsg){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(RegisterTrainer.this);
        dialogBuilder.setMessage(errorMsg);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void registerTrainer(Trainer trainer){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.storeTrainerDataInBackground(trainer, new GetTrainerCallback() {
            @Override
            public void done(Trainer returnedTrainer) {
                startActivity(new Intent(RegisterTrainer.this, LoginTrainer.class));
            }

        });
    }


}
