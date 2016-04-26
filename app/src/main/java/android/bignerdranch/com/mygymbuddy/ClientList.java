package android.bignerdranch.com.mygymbuddy;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ClientList extends AppCompatActivity implements View.OnClickListener{


    TextView name1, bench1, squat1, dead1, name2, bench2, squat2, dead2, name3, bench3, squat3, dead3, name4, bench4, squat4, dead4
            , name5, bench5, squat5, dead5;
    LinearLayout client1, client2, client3, client4, client5;
    Forum forum;
    Context mContext;
    private View.OnClickListener mClickListener;
    ArrayList<User> temp;
    ArrayList<LinearLayout> clients = new ArrayList<>();
    ArrayList<TextView> benches = new ArrayList<>();
    ArrayList<TextView> squats = new ArrayList<>();
    ArrayList<TextView> deads = new ArrayList<>();
    ArrayList<TextView> names = new ArrayList<>();


    ThreadLocalStore mThreadLocalStore;

    Button bHome;
    TrainerLocalStore mTrainerLocalStore;
    Trainer mTrainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        name4 = (TextView) findViewById(R.id.name4);
        name5 = (TextView) findViewById(R.id.name5);

        dead1 = (TextView) findViewById(R.id.dead1);
        dead2 = (TextView) findViewById(R.id.dead2);
        dead3 = (TextView) findViewById(R.id.dead3);
        dead4 = (TextView) findViewById(R.id.dead4);
        dead5 = (TextView) findViewById(R.id.dead5);


        bench1 = (TextView) findViewById(R.id.bench1);
        bench2 = (TextView) findViewById(R.id.bench2);
        bench3 = (TextView) findViewById(R.id.bench3);
        bench4 = (TextView) findViewById(R.id.bench4);
        bench5 = (TextView) findViewById(R.id.bench5);

        squat1 = (TextView) findViewById(R.id.squat1);
        squat2 = (TextView) findViewById(R.id.squat2);
        squat3 = (TextView) findViewById(R.id.squat3);
        squat4 = (TextView) findViewById(R.id.squat4);
        squat5 = (TextView) findViewById(R.id.squat5);

        client1 = (LinearLayout) findViewById(R.id.client1);
        client2 = (LinearLayout) findViewById(R.id.client2);
        client3 = (LinearLayout) findViewById(R.id.client3);
        client4 = (LinearLayout) findViewById(R.id.client4);
        client5 = (LinearLayout) findViewById(R.id.client5);

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);

        benches.add(bench1);
        benches.add(bench2);
        benches.add(bench3);
        benches.add(bench4);
        benches.add(bench5);

        squats.add(squat1);
        squats.add(squat2);
        squats.add(squat3);
        squats.add(squat4);
        squats.add(squat5);

        deads.add(dead1);
        deads.add(dead2);
        deads.add(dead3);
        deads.add(dead4);
        deads.add(dead5);

        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);

        bHome = (Button) findViewById(R.id.bHome);
        bHome.setOnClickListener(this);

        mTrainerLocalStore = new TrainerLocalStore(this);

        mClickListener = this;

        mContext = this;
        mTrainer = mTrainerLocalStore.getLoggedInTrainer();


    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bHome:
                startActivity(new Intent(this, HomeTrainer.class));
                break;

        }
    }

    @Override
    public void onStart(){
        super.onStart();

        client1.setVisibility(View.INVISIBLE);
        client2.setVisibility(View.INVISIBLE);
        client3.setVisibility(View.INVISIBLE);
        client4.setVisibility(View.INVISIBLE);
        client5.setVisibility(View.INVISIBLE);

        getClientList(mTrainer);

    }


    private void getClientList(Trainer trainer){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.fetchClientListInBackground(trainer, new GetClientListCallback() {
            @Override
            public void done(ClientForum returnedForum) {

                int i = 0;
                temp = returnedForum.getAllClients();


                for (i = 0; i < temp.size(); i++) {
                    clients.get(i).setVisibility(View.VISIBLE);

                    names.get(i).setText(temp.get(i).name);
                    benches.get(i).setText(temp.get(i).benchMax+"");
                    squats.get(i).setText(temp.get(i).squatMax+"");
                    deads.get(i).setText(temp.get(i).deadMax+"");


                }
            }


        });
    }

}
