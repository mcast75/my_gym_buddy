package android.bignerdranch.com.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class HowIFeel extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    Button headFront, headBack, shoulderFront1, chest, shoudlerFront2, upperBack, bicep1, stomach,bicep2
            ,tricep1, lowerBack, tricep2, wrist1, hips, wrist2, wrist3, gluts, wrist4, quad1, quad2
            , hamstring1, hamstring2, knee1, knee2, shin1, shin2, calve1, calve2, foot1, foot2, foot3
            , foot4, bhome, bShow;

    RecoveryEx ex;
    RecoveryLocalStore mRecoveryLocalStore;

    TextView mes1, bodyPart, mes2, painLevel;

    SeekBar painBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_ifeel);

        ex = new RecoveryEx();
        mRecoveryLocalStore = new RecoveryLocalStore(this);

        painLevel = (TextView) findViewById(R.id.painLevel);

        painBar = (SeekBar) findViewById(R.id.painBar);
        painBar.setMax(10);

        bShow = (Button) findViewById(R.id.bShow);

        painBar.setOnSeekBarChangeListener(this);

        headFront = (Button) findViewById(R.id.headFront);
        headBack = (Button) findViewById(R.id.headBack);

        shoulderFront1 = (Button) findViewById(R.id.shoulderFront1);
        chest = (Button) findViewById(R.id.chest);
        shoudlerFront2 = (Button) findViewById(R.id.shoulderFront2);
        upperBack = (Button) findViewById(R.id.upperBack);

        bicep1 = (Button) findViewById(R.id.bicep1);
        stomach = (Button) findViewById(R.id.stomach);
        bicep2 = (Button) findViewById(R.id.bicep2);

        tricep1 = (Button) findViewById(R.id.tricep1);
        lowerBack = (Button) findViewById(R.id.lowerBack);
        tricep2 = (Button) findViewById(R.id.tricep2);

        wrist1 = (Button) findViewById(R.id.wrist1);
        wrist2 = (Button) findViewById(R.id.wrist2);
        wrist3 = (Button) findViewById(R.id.wrist3);
        wrist4 = (Button) findViewById(R.id.wrist4);

        hips = (Button) findViewById(R.id.hips);

        gluts = (Button) findViewById(R.id.gluts);

        quad1 = (Button) findViewById(R.id.quad1);
        quad2 = (Button) findViewById(R.id.quad2);

        hamstring1 = (Button) findViewById(R.id.hamstring1);
        hamstring2 = (Button) findViewById(R.id.hamstring2);

        knee1 = (Button) findViewById(R.id.knee1);
        knee2 = (Button) findViewById(R.id.knee2);

        shin1 = (Button) findViewById(R.id.shin1);
        shin2 = (Button) findViewById(R.id.shin2);

        calve1= (Button) findViewById(R.id.calve1);
        calve2 = (Button) findViewById(R.id.calve2);

        foot1 = (Button) findViewById(R.id.foot1);
        foot2 = (Button) findViewById(R.id.foot2);
        foot3 = (Button) findViewById(R.id.foot3);
        foot4 = (Button) findViewById(R.id.foot4);

        bhome = (Button) findViewById(R.id.bHome);

        mes1 = (TextView) findViewById(R.id.mes1);
        mes2 = (TextView) findViewById(R.id.mes2);
        bodyPart = (TextView) findViewById(R.id.bodyPart);

        bShow.setOnClickListener(this);
        headFront.setOnClickListener(this);
        headBack.setOnClickListener(this);
        shoulderFront1.setOnClickListener(this);
        chest.setOnClickListener(this);
        shoudlerFront2.setOnClickListener(this);
        upperBack.setOnClickListener(this);
        bicep1.setOnClickListener(this);
        stomach.setOnClickListener(this);
        bicep2.setOnClickListener(this);
        tricep1.setOnClickListener(this);
        lowerBack.setOnClickListener(this);
        tricep2.setOnClickListener(this);
        wrist1.setOnClickListener(this);
        wrist2.setOnClickListener(this);
        wrist3.setOnClickListener(this);
        wrist4.setOnClickListener(this);
        hips.setOnClickListener(this);
        gluts.setOnClickListener(this);
        quad1.setOnClickListener(this);
        quad2.setOnClickListener(this);
        hamstring1.setOnClickListener(this);
        hamstring2.setOnClickListener(this);
        knee1.setOnClickListener(this);
        knee2.setOnClickListener(this);
        shin1.setOnClickListener(this);
        shin2.setOnClickListener(this);
        calve1.setOnClickListener(this);
        calve2.setOnClickListener(this);
        foot1.setOnClickListener(this);
        foot2.setOnClickListener(this);
        foot3.setOnClickListener(this);
        foot4.setOnClickListener(this);
        bhome.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.headFront:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("head");
                break;

            case R.id.headBack:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("head");
                break;

            case R.id.shoulderFront1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("shoulder");
                break;

            case R.id.chest:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("chest");
                break;

            case R.id.shoulderFront2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("shoulder");
                break;

            case R.id.upperBack:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("upper back");
                break;

            case R.id.bicep1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("bicep");
                break;

            case R.id.stomach:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("abdominal");
                break;

            case R.id.bicep2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("bicep");
                break;

            case R.id.lowerBack:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("lower back");
                break;

            case R.id.tricep1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("tricep");
                break;

            case R.id.tricep2:
                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("tricep");
                break;

            case R.id.wrist1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("wrist");
                break;

            case R.id.wrist2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("wrist");
                break;

            case R.id.wrist3:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("wrist");
                break;

            case R.id.wrist4:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("wrist");
                break;

            case R.id.gluts:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("gluts");
                break;

            case R.id.quad1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("quad");
                break;

            case R.id.quad2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("quad");
                break;

            case R.id.hamstring1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("hamstring");
                break;

            case R.id.hamstring2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("hamstring");
                break;

            case R.id.knee1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("knee");
                break;

            case R.id.knee2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("knee");
                break;

            case R.id.shin1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("shin");
                break;

            case R.id.shin2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("shin");
                break;

            case R.id.calve1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("calve");
                break;

            case R.id.calve2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("calve");
                break;

            case R.id.foot1:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("foot");
                break;

            case R.id.foot2:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("foot");
                break;

            case R.id.foot3:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("foot");
                break;

            case R.id.foot4:

                mes1.setVisibility(View.VISIBLE);
                mes2.setVisibility(View.VISIBLE);
                bodyPart.setText("foot");
                break;

            case R.id.bHome:

                startActivity(new Intent(this, Home.class));
                break;

            case R.id.bShow:
                ex.body_part = bodyPart.getText().toString();
                mRecoveryLocalStore.storeRecoveryEx(ex);
                startActivity(new Intent(this, PostRecovery.class));
                break;


        }

        ex.body_part = bodyPart.getText().toString();
        mRecoveryLocalStore.storeRecoveryEx(ex);

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int i = painBar.getProgress();
        painLevel.setText(i+"");
        seekBar.setMax(10);

        ex.discomfort = painLevel.getText().toString();
        mRecoveryLocalStore.storeRecoveryEx(ex);


        // TODO Auto-generated method stub
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub

    }

}


