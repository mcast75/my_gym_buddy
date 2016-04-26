package android.bignerdranch.com.mygymbuddy;

/*
 * This program makes a server request to load all the comments on a thread and then displays those threads.
 * This program also allows users to like or dislike threads and stores those values in the database.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Comments extends AppCompatActivity implements View.OnClickListener {

    Button bFour, bHome, bLogout, bRedd;
    ProfileLocalStore mProfileLocalStore;
    UserLocalStore mUserLocalStore;
    LinearLayout profButton;
    TrainerLocalStore mTrainerLocalStore;
    TextView threadTitle, comments, createComment, numLikes, numDislikes, viewProf;
    Context mContext;
    private View.OnClickListener mClickListener;
    ArrayList<Comment> temp;
    ThreadLocalStore mThreadLocalStore;
    Thread currentThread;
    CommentForum commentForum;
    CheckBox cbLike, cbDislike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        profButton = (LinearLayout) findViewById(R.id.profButton);


        bLogout = (Button) findViewById(R.id.bLogout2);
        bHome = (Button) findViewById(R.id.bHome2);
        //comments = (TextView) findViewById(R.id.comments);
        threadTitle = (TextView) findViewById(R.id.threadTitle);
        createComment = (TextView) findViewById(R.id.createComment);
        numLikes = (TextView) findViewById(R.id.numLikes);
        numDislikes = (TextView) findViewById(R.id.numDislikes);
        viewProf = (TextView) findViewById(R.id.viewProf);
        viewProf.setOnClickListener(this);

        cbLike = (CheckBox) findViewById(R.id.bLike);
        cbDislike = (CheckBox) findViewById(R.id.bDisLike);

        bHome.setOnClickListener(this);
        createComment.setOnClickListener(this);
        profButton.setOnClickListener(this);

        mThreadLocalStore = new ThreadLocalStore(this);
        mUserLocalStore = new UserLocalStore(this);
        mTrainerLocalStore = new TrainerLocalStore(this);

        temp = new ArrayList<Comment>();

        mClickListener = this;

        mContext = this;

        currentThread = mThreadLocalStore.getCurrentThread();
        mProfileLocalStore = new ProfileLocalStore(this);
        commentForum = null;

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart(){
        super.onStart();
        getCommentForum(commentForum, mThreadLocalStore.getCurrentThread());
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.bHome2:
                if(mUserLocalStore.getUserLoggedIn()==true)
                    startActivity(new Intent(this, Home.class));
                else if(mTrainerLocalStore.getUserLoggedIn() == true)
                    startActivity(new Intent(this, HomeTrainer.class));
                break;

            case R.id.createComment:
                startActivity(new Intent(this, AddComment.class));
                break;

            case R.id.viewProf:
                mProfileLocalStore.storeUser(new User(viewProf.getText().toString()));
                startActivity(new Intent(this, Profile.class));
                break;


        }

    }


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.bLike:
                if(checked) {
                    Thread temp = mThreadLocalStore.getCurrentThread();
                    Log.d("ADebugTag", "ThreadLikes!!!!!: \n" + temp.like);

                    temp.addLike();
                    numLikes.setText(temp.like + "");
                    mThreadLocalStore.clearThreadData();
                    mThreadLocalStore.storeThreadData(temp);
                    Log.d("ADebugTag", "ThreadLikes!!!!!: \n" + mThreadLocalStore.getCurrentThread().like);

                    cbLike.setClickable(false);
                    cbDislike.setClickable(false);
                    updateThread(mThreadLocalStore.getCurrentThread());
                }
                    //send to database
                break;

            case R.id.bDisLike:
                if(checked) {
                    Thread temp = mThreadLocalStore.getCurrentThread();
                    temp.addDislike();
                    Log.d("ADebugTag", "ThreadLikes!!!!!: \n" + temp.dislikes);
                    numDislikes.setText(temp.dislikes + "");
                    mThreadLocalStore.clearThreadData();
                    mThreadLocalStore.storeThreadData(temp);
                    cbLike.setClickable(false);
                    cbDislike.setClickable(false);
                    updateThread(mThreadLocalStore.getCurrentThread());

                }
                //send to database
                break;
        }
    }


    private void updateThread(Thread thread){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.storeThreadLikeDataInBackground(thread, new GetThreadCallback() {
            @Override
            public void done(Thread returnedUser) {

            }

        });
    }


    private void getCommentForum(CommentForum commentForum, final Thread thread){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.fetchCommentForumInBackground(commentForum, thread, new GetCommentForumCallback() {
            @Override
            public void done(CommentForum returnedCommentForum) {

                TableLayout table = (TableLayout) findViewById(R.id.table);
                int i = 0;
                temp = returnedCommentForum.getAllComments();

                threadTitle.setText(mThreadLocalStore.getCurrentThread().title);
                numLikes.setText(mThreadLocalStore.getCurrentThread().like + "");
                numDislikes.setText(mThreadLocalStore.getCurrentThread().dislikes + "");
                viewProf.setText(mThreadLocalStore.getCurrentThread().user);



                TableRow row = new TableRow(mContext);
                table.addView(row);
                row.setId(i);
                row.setClickable(true);
                LinearLayout ll = new LinearLayout(mContext);
                row.addView(ll);


                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setPadding(0, 0, 0, 70);

                LinearLayout ll2 = new LinearLayout(mContext);
                ll.setPadding(0, 0, 0, 40);
                ll.addView(ll2);
                TextView tvTitle = new TextView(mContext);
                Point size = new Point();
                getWindowManager().getDefaultDisplay().getSize(size);
                int width = size.x;
                tvTitle.setText(mThreadLocalStore.getCurrentThread().text);
                tvTitle.setLayoutParams(new LinearLayout.LayoutParams(width - 10
                        , ViewGroup.LayoutParams.WRAP_CONTENT));
                tvTitle.setTextColor(Color.WHITE);
                tvTitle.setTextSize(24);
                ll2.addView(tvTitle);
                TextView line = new TextView(mContext);
                line.setTextSize(2);
                line.setTextColor(Color.WHITE);
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
                comments.setText("Submitted by: " + mThreadLocalStore.getCurrentThread().user);
                comments.setTextSize(20);
                ll3.addView(comments);

                TextView tvNumLike = new TextView(mContext);
                tvNumLike.setText("    (Original Post) ");
                tvNumLike.setTextSize(20);
                ll3.addView(tvNumLike);
                ll.addView(line);



                for (i = 0; i < temp.size(); i++) {


                    row = new TableRow(mContext);
                    table.addView(row);
                    row.setId(i);
                    row.setClickable(true);
//                    Log.d("ADebugTag", "LOG ID!!!!!!!!!!!!!!: \n" + row.getId());
                    ll = new LinearLayout(mContext);
                    row.addView(ll);
                    row.setOnClickListener(Comments.this);


                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.setPadding(0, 0, 0, 70);

                    ll2 = new LinearLayout(mContext);
                    ll.setPadding(0, 0, 0, 40);
                    ll.addView(ll2);
                    tvTitle = new TextView(mContext);
                    tvTitle.setText(temp.get(i).text);
                    Point size2 = new Point();
                    getWindowManager().getDefaultDisplay().getSize(size2);
                    int width2 = size2.x;
                    tvTitle.setText(temp.get(i).text);
                    tvTitle.setLayoutParams(new LinearLayout.LayoutParams(width2 - 10
                            , ViewGroup.LayoutParams.WRAP_CONTENT));

                    tvTitle.setTextColor(Color.WHITE);
                    tvTitle.setTextSize(24);
                    ll2.addView(tvTitle);
                    line = new TextView(mContext);
                    line.setTextSize(2);
                    line.setTextColor(Color.WHITE);
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


                    ll3 = new LinearLayout(mContext);
                    ll.addView(ll3);
                    space = new TextView(mContext);
                    space.setText("      ");
                    ll3.addView(space);
                    comments = new TextView(mContext);
                    comments.setText("Submitted by: " + temp.get(i).user);
                    comments.setTextSize(20);
                    ll3.addView(comments);
                    ll.addView(line);



                }
            }


        });
    }
}
