package android.bignerdranch.com.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterChoose extends AppCompatActivity implements View.OnClickListener{

    Button bRegisterAthlete, bRegisterTrainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_choose);


        bRegisterAthlete = (Button) findViewById(R.id.bRegisterAthlete);
        bRegisterTrainer = (Button) findViewById(R.id.bRegisterTrainer);

        bRegisterAthlete.setOnClickListener(this);
        bRegisterTrainer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bRegisterAthlete:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.bRegisterTrainer:
                startActivity(new Intent(this, RegisterTrainer.class));
                break;
        }
    }


}
