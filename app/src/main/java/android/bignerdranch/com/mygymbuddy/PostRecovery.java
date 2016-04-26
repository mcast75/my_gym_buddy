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

public class PostRecovery extends AppCompatActivity implements View.OnClickListener {

    TextView name, label1, label2, label3, furtherIns;

    RecoveryEx recovery;
    RecoveryLocalStore mRecoveryLocalStore;

    Button bHome, bBack;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_recovery);

        mContext = this;
        name = (TextView)findViewById(R.id.name);
        label1 = (TextView)findViewById(R.id.label1);
        label2 = (TextView)findViewById(R.id.label2);
        label3 = (TextView)findViewById(R.id.label3);
        furtherIns = (TextView)findViewById(R.id.furtherIns);

        bHome = (Button) findViewById(R.id.bHome);
        bBack = (Button) findViewById(R.id.bBack);

        bHome.setOnClickListener(this);
        bBack.setOnClickListener(this);

        mRecoveryLocalStore = new RecoveryLocalStore(this);


    }

    @Override
    public void onStart(){
        super.onStart();

        recovery = mRecoveryLocalStore.getRecoveryEx();
        getRecovery(recovery);



    }

    public void getRecovery(RecoveryEx recovery){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchRecoveryDataInBackground(recovery, new GetRecoveryCallback() {
            @Override
            public void done(RecoveryEx returnedEx) {
                name.setText(returnedEx.name);

                ImageView pic = (ImageView) findViewById(R.id.recPic);
                int resID = getResources().getIdentifier(fixName(name.getText().toString()), "drawable", getPackageName());
                Log.d("ADebugTag", "IMG NAME: \n" + fixName(name.getText().toString()));

                label1.setText(returnedEx.ins1);
                label2.setText(returnedEx.ins2);
                label3.setText(returnedEx.ins3);
                furtherIns.setText(returnedEx.further);
                pic.setImageResource(resID);

            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bHome:

                startActivity(new Intent(PostRecovery.this, Home.class));
                break;


            case R.id.bBack:
                startActivity(new Intent(PostRecovery.this, HowIFeel.class));
                break;




        }


    }

    private String fixName(String ex){
        String temp = ex.toLowerCase();
        temp=temp.replaceAll("\\p{Z}","");
        return temp;


    }


}
