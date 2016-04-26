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

public class Home extends AppCompatActivity implements View.OnClickListener{

    ProfileLocalStore mProfileLocalStore;
    TextView createThread, comments, banner;
    Forum forum;
    Context mContext;
    private View.OnClickListener mClickListener;
    ArrayList<Thread> temp;
    ThreadLocalStore mThreadLocalStore;

    Button bProfile, bWorkout, bHowIFeel, bLogout, trainer;
    UserLocalStore mUserLocalStore;
    TextView name, dayNum;
    static int day;
    String trainer_email, trainer_name;
    Trainer mTrainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mProfileLocalStore = new ProfileLocalStore(this);

        banner = (TextView) findViewById(R.id.banner);

        bProfile = (Button) findViewById(R.id.bProfile);
        bWorkout = (Button) findViewById(R.id.bWorkout);
        bHowIFeel = (Button) findViewById(R.id.bHowIFeel);
        bLogout = (Button) findViewById(R.id.bLogout);
        bWorkout = (Button) findViewById(R.id.bWorkout);
        name = (TextView) findViewById(R.id.name);
        createThread = (TextView) findViewById(R.id.createThread);
        trainer = (Button) findViewById(R.id.trainer);

        bProfile.setOnClickListener(this);
        bLogout.setOnClickListener(this);
        bWorkout.setOnClickListener(this);
        bHowIFeel.setOnClickListener(this);
        trainer.setOnClickListener(this);

        mUserLocalStore = new UserLocalStore(this);

        createThread = (TextView) findViewById(R.id.createThread);
        //comments = (TextView) findViewById(R.id.comments);

        createThread.setOnClickListener(this);

        mThreadLocalStore = new ThreadLocalStore(this);

        temp = new ArrayList<Thread>();

        mClickListener = this;

        mUserLocalStore = new UserLocalStore(this);

        mContext = this;

        forum = null;
        mTrainer = new Trainer(mUserLocalStore.getLoggedInUser().trainer);


    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bProfile:
               mProfileLocalStore.storeUser(new User(mUserLocalStore.getLoggedInUser().username));
                startActivity(new Intent(this, Profile.class));

                break;

            case R.id.bWorkout:
                startActivity(new Intent(this, Workout.class));
                break;

            case R.id.bLogout:

                mUserLocalStore.clearUserData();
                mUserLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, GymActivity.class));
                break;

            case R.id.bHowIFeel:
                startActivity(new Intent(this, HowIFeel.class));
                break;

            case R.id.createThread:
                startActivity(new Intent(this, CreateThread.class));
                break;

            case R.id.trainer:
                if(mUserLocalStore.getLoggedInUser().trainer == 0){
                    setTrainer(mUserLocalStore.getLoggedInUser());
                    startActivity(new Intent(this, Home.class));
                }else{
                    getTrainer(mTrainer);

                }
                break;

            default:
                int id = v.getId();

                mThreadLocalStore.storeThreadData(temp.get(id));

                Log.d("ADebugTag", "CURRENT THREAD!!!!!!!!!!!!!!: \n" + mThreadLocalStore.getCurrentThread().title);


                startActivity(new Intent(this, Comments.class));
                break;

        }
    }

    @Override
    public void onStart(){
        super.onStart();

        name.setText(mUserLocalStore.getLoggedInUser().name);
        getForum(forum);

        if(mUserLocalStore.getLoggedInUser().trainer == 0){
            trainer.setText("Get Me an MGB Trainer!");
            banner.setText("  Looking For a More Personalized Experience?");
        }else{
            trainer.setText("Contact My Trainer!");
            banner.setText("  Keep In Touch With Your Trainer!");
        }

    }


    private void setTrainer(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchTrainerByFocus(mUserLocalStore.getLoggedInUser(), new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Log.d("ADebugTag", "Value: \nNew ID: " + returnedUser.trainer);
                mUserLocalStore.updateTrainer(returnedUser.trainer);
                startActivity(new Intent(mContext, Home.class));

            }
        });
    }

    private void getTrainer(Trainer trainer){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchTrainerByID(trainer, new GetTrainerCallback() {
            @Override
            public void done(Trainer returnedTrainer) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                Log.d("ADebugTag", "Value: \nTrainer Email: " + returnedTrainer.email + "  Trainer Name" + returnedTrainer.name);

                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{returnedTrainer.email});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Gym Buddy Community");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi " + returnedTrainer.name + "!\n\n");
                mContext.startActivity(Intent.createChooser(emailIntent, "Contact " + returnedTrainer.name + "..."));



            }
        });
    }


    private void getForum(Forum forum){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.fetchForumInBackground(forum, new GetForumCallback() {
            @Override
            public void done(Forum returnedForum) {

                TableLayout table = (TableLayout) findViewById(R.id.publicTable);
                int i = 0;
                temp = returnedForum.getAllThreads();



                for(i =0; i < temp.size(); i++){



                    TableRow row = new TableRow(mContext);
                    table.addView(row);
                    row.setId(i);
                    row.setClickable(true);
                    LinearLayout ll = new LinearLayout(mContext);
                    row.addView(ll);
                    row.setOnClickListener(Home.this);


                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.setPadding(0, 0, 0, 70);

                    LinearLayout ll2 = new LinearLayout(mContext);
                    ll.setPadding(0, 0, 0, 40);
                    ll.addView(ll2);
                    TextView tvTitle = new TextView(mContext);
                    tvTitle.setText(temp.get(i).title);

                    Point size2 = new Point();
                    getWindowManager().getDefaultDisplay().getSize(size2);
                    int width2 = size2.x;
                    tvTitle.setLayoutParams(new LinearLayout.LayoutParams(width2 - 10
                            , ViewGroup.LayoutParams.WRAP_CONTENT));

                    tvTitle.setTextColor(Color.WHITE);
                    tvTitle.setTextSize(24);
                    ll2.addView(tvTitle);
                    TextView line = new TextView(mContext);
                    line.setTextSize(2);
                    line.setTextColor(Color.LTGRAY);
                    line.setText("_____________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "_______________________________________________________________________" +
                            "______________________________________________________________________");




                    LinearLayout ll3 = new LinearLayout(mContext);
                    ll.addView(ll3);
                    TextView space = new TextView(mContext);
                    space.setText("      ");
                    ll3.addView(space);
                    TextView comments = new TextView(mContext);
                    comments.setText("Comments");
                    comments.setLinksClickable(true);
                    comments.setTag("comments");

                    comments.setTextSize(20);
                    comments.setTextColor(Color.LTGRAY);
                    comments.setPaintFlags(comments.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    ll3.addView(comments);

                    TextView space2 = new TextView(mContext);
                    space2.setText("                                           ");
                    ll3.addView(space2);
                    ImageView thumbsup = new ImageView(mContext);
                    ll3.addView(thumbsup);
                    android.view.ViewGroup.LayoutParams layoutParams = thumbsup.getLayoutParams();
                    layoutParams.width = 30;
                    layoutParams.height = 30;
                    thumbsup.setLayoutParams(layoutParams);
                    thumbsup.setImageResource(R.drawable.thumbsup);


                    TextView tvNumLike = new TextView(mContext);
                    tvNumLike.setText("Likes(" + temp.get(i).like + ")      ");
                    tvNumLike.setTextSize(20);
                    ll3.addView(tvNumLike);

                    ImageView thumbsdown = new ImageView(mContext);
                    ll3.addView(thumbsdown);
                    android.view.ViewGroup.LayoutParams layoutParams2 = thumbsdown.getLayoutParams();
                    layoutParams2.width = 30;
                    layoutParams2.height = 30;
                    thumbsdown.setLayoutParams(layoutParams2);
                    thumbsdown.setImageResource(R.drawable.thumbsdown);
                    TextView tvNumDislikes = new TextView(mContext);
                    tvNumDislikes.setText("Dislikes(" + temp.get(i).dislikes + ")");
                    tvNumDislikes.setTextSize(20);
                    ll3.addView(tvNumDislikes);

                    ll.addView(line);






                }
            }


        });
    }

}
