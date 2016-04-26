package android.bignerdranch.com.mygymbuddy;

/**
 * This program contains the class definition for comments.
 */

public class Comment {

    int commentID, threadID;
    String user, text;


    public Comment(int commentID, String user, int threadID, String text){

        this.commentID = commentID;
        this.threadID = threadID;
        this.user = user;
        this.text = text;

    }


    public Comment(int threadID, String user, String text){

        this.commentID = 0;
        this.threadID = threadID;
        this.user = user;
        this.text = text;

    }




    public Comment(){
        this.commentID = 0;
        this.threadID = 0;
        this.user = "";
        this.text = "";

    }


}
