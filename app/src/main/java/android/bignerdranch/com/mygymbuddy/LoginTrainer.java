package android.bignerdranch.com.mygymbuddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Mike on 3/30/16.
 */
public class LoginTrainer extends AppCompatActivity implements View.OnClickListener {

        Button bLogin;
        EditText etEmail;
        EditText etPassword;
        TrainerLocalStore mTrainerLocalStore;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_trainer);

            etEmail = (EditText) findViewById(R.id.etEmailTrainer);
            etPassword = (EditText) findViewById(R.id.etPasswordTrainer);
            bLogin = (Button) findViewById(R.id.bLoginSucTrainer);

            bLogin.setOnClickListener(this);
            mTrainerLocalStore = new TrainerLocalStore(this);


        }


        @Override
        protected void onStart(){
            super.onStart();
/*
        if (authenticated() == true){
            displayUserDetails();
        }else{
            startActivity(new Intent(Login.this, Login.class));
        }
*/
        }

        private boolean authenticated(){
            return mTrainerLocalStore.getUserLoggedIn();
        }

        private void displayUserDetails(){
            Trainer trainer = mTrainerLocalStore.getLoggedInTrainer();

            etEmail.setText(trainer.email);
            etPassword.setText(trainer.password);
        }

        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.bLoginSucTrainer:

                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();



                    Trainer trainer = new Trainer(email,password);
                    authenticate(trainer);
                    break;

            }
        }

        private void authenticate(Trainer trainer){
            ServerRequests serverRequests = new ServerRequests(this);
            serverRequests.fetchTrainerDataInBackgorund(trainer, new GetTrainerCallback() {
                @Override
                public void done(Trainer returnedTrainer) {
                    if (returnedTrainer == null) {
                        showErrorMessage();
                    } else {
                        logUserIn(returnedTrainer);

                    }

                }
            });
        }

        private void showErrorMessage(){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginTrainer.this);
            dialogBuilder.setMessage("Incorrect user details");
            dialogBuilder.setPositiveButton("Ok", null);
            dialogBuilder.show();
        }

        private void logUserIn(Trainer returnedTrainer){
            mTrainerLocalStore.storeTrainerData(returnedTrainer);
            mTrainerLocalStore.setTrainerLoggedIn(true);

            startActivity(new Intent(this, HomeTrainer.class));

        }


    }
