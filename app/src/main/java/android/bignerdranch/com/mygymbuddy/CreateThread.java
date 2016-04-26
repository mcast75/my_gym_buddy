package android.bignerdranch.com.mygymbuddy;

/*
 * This program is used to create new threads. This program creates comments based on user input and then sends those comments into
 * the database using ServerRequests.java.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateThread extends AppCompatActivity implements View.OnClickListener {

    EditText threadPost, threadTitle;
    TextView makeThread, cancelThread;
    UserLocalStore mUserLocalStore;
    TrainerLocalStore mTrainerLocalStore;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_thread);

        logout = (Button) findViewById(R.id.bLogout2);

        threadPost = (EditText) findViewById(R.id.threadPost);
        threadTitle = (EditText) findViewById(R.id.threadTitle);

        makeThread = (TextView) findViewById(R.id.makeThread);
        cancelThread = (TextView) findViewById(R.id.cancelThread);

        makeThread.setOnClickListener(this);
        cancelThread.setOnClickListener(this);
        logout.setOnClickListener(this);

        mUserLocalStore = new UserLocalStore(this);
        mTrainerLocalStore = new TrainerLocalStore(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.makeThread:


                String name = mUserLocalStore.getLoggedInUser().username;
                String title =  threadTitle.getText().toString();
                String post = threadPost.getText().toString();

                Thread thread = new Thread(name,title,post);

                Log.d("Register", "ValueTHREAD: \n\n\n\n\n name" + thread.user + "   Title" + thread.title + "    Post  " + threadPost.getText().toString() + "    Like  " + thread.like);



                makeThread(thread);


                break;

            case R.id.cancelThread:

                if(mUserLocalStore.getUserLoggedIn()==true)
                    startActivity(new Intent(this, Home.class));
                else if(mTrainerLocalStore.getUserLoggedIn() == true)
                    startActivity(new Intent(this, HomeTrainer.class));
                break;


            case R.id.bLogout2:
                startActivity(new Intent(this, GymActivity.class));
                break;

        }


    }


    private void makeThread(Thread thread){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.storeThreadDataInBackground(thread, new GetThreadCallback() {
            @Override
            public void done(Thread returnedUser) {
                startActivity(new Intent(CreateThread.this, Home.class));
            }

        });
    }


}




