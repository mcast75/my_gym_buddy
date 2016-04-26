package android.bignerdranch.com.mygymbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mike on 3/30/16.
 */
public class LoginChoose extends AppCompatActivity implements View.OnClickListener{

        Button bLoginAthlete, bLoginTrainer;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_choose);


            bLoginAthlete = (Button) findViewById(R.id.bLoginAthlete);
            bLoginTrainer = (Button) findViewById(R.id.bLoginTrainer);

            bLoginAthlete.setOnClickListener(this);
            bLoginTrainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.bLoginAthlete:
                    startActivity(new Intent(this, Login.class));
                    break;

                case R.id.bLoginTrainer:
                    startActivity(new Intent(this, LoginTrainer.class));
                    break;
            }
        }


    }
