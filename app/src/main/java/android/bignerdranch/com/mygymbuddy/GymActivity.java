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

public class GymActivity extends AppCompatActivity implements View.OnClickListener{

    Button bRegister, bLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);


        bRegister = (Button) findViewById(R.id.bRegister);
        bLogin = (Button) findViewById(R.id.bLogin);

        bRegister.setOnClickListener(this);
        bLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bLogin:
                startActivity(new Intent(this, LoginChoose.class));
                break;

            case R.id.bRegister:
                startActivity(new Intent(this, RegisterChoose.class));
                break;
        }
    }


}
