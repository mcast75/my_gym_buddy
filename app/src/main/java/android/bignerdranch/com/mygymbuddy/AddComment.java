package android.bignerdranch.com.mygymbuddy;

/*
 * This program is used to add comments to threads. This program creates comments based on user input and then sends those comments into
 * the database using ServerRequests.java.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddComment extends AppCompatActivity implements View.OnClickListener {

    EditText commentPost;
    TextView makeComment, cancelComment, threadTitle;
    UserLocalStore mUserLocalStore;
    ThreadLocalStore mThreadLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        commentPost = (EditText) findViewById(R.id.commentPost);
        threadTitle = (TextView) findViewById(R.id.threadTitle);

        makeComment = (TextView) findViewById(R.id.makeComment);
        cancelComment = (TextView) findViewById(R.id.cancelComment);

        makeComment.setOnClickListener(this);
        cancelComment.setOnClickListener(this);

        mUserLocalStore = new UserLocalStore(this);
        mThreadLocalStore = new ThreadLocalStore(this);

        threadTitle.setText(mThreadLocalStore.getCurrentThread().title);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.makeComment:


                String user = mUserLocalStore.getLoggedInUser().name;
                String text =  commentPost.getText().toString();
                int threadID = mThreadLocalStore.getCurrentThread().id;

                Comment temp = new Comment(threadID, user, text);
                Log.d("Register", "ValueTHREAD: name " + temp.user + "   post " + temp.text + "    ID  " + threadID);
                makeComment(temp);


                break;

            case R.id.cancelThread:

                startActivity(new Intent(this, Home.class));
                break;

            case R.id.bLogout2:
                startActivity(new Intent(this, GymActivity.class));
                break;

            case R.id.cancelComment:
                startActivity(new Intent(this, Comments.class));
                break;

        }


    }


    private void makeComment(Comment comment){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.storeCommentDataInBackground(comment, new GetCommentCallback() {
            @Override
            public void done(Comment returnedComment) {
                startActivity(new Intent(AddComment.this, Comments.class));
            }

        });
    }


}


