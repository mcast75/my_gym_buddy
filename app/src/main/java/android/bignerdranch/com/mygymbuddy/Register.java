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

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etHeightFt, etHeightIn, etWeight, etUsername, etPassword;
    CheckBox cbBeginner, cbExperienced, cbGeneralFitness, cbStrength, cbWeightLoss;
    int experience, goals;

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
        cbBeginner = (CheckBox) findViewById(R.id.cbBeginner);
        cbExperienced = (CheckBox) findViewById(R.id.cbExperienced);
        cbGeneralFitness = (CheckBox) findViewById(R.id.cbGeneralFitness);
        cbStrength = (CheckBox) findViewById(R.id.cbStrength);
        cbWeightLoss = (CheckBox) findViewById(R.id.cbWeightLoss);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.cbBeginner:
                if(checked)
                    experience = 1;
                break;

            case R.id.cbExperienced:
                if(checked)
                    experience = 2;
                break;
            case R.id.cbGeneralFitness:
                if(checked)
                    goals = 1;
                break;
            case R.id.cbStrength:
                if(checked)
                    goals = 2;
                break;
            case R.id.cbWeightLoss:
                if(checked)
                    goals = 3;
                break;
        }
    }

    @Override
    public void onClick(View v){


        switch(v.getId()){
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
                int weight = Integer.parseInt(etWeight.getText().toString());
                User user = new User(name, userName, password, heightFt, heightIn, weight, experience, goals);



                registerUser(user);


                break;




        }
    }



    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Register.this);
        dialogBuilder.setMessage("Please Fill In All Registration Fields");
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
