package android.bignerdranch.com.mygymbuddy;

/**
 * Creates new thread object
 */

/**
 * Created by Mike on 11/24/15.
 */
public class WallPost {
    int postID;
    String userID, comment, poster;


    public WallPost(int postID, String userID, String comment, String poster){

        this.postID = postID;
        this.userID = userID;
        this.comment = comment;
        this.poster = poster;
    }

    public WallPost(String userID, String comment, String poster){

        this.postID = -1;
        this.userID = userID;
        this.comment = comment;
        this.poster = poster;
    }

    public WallPost(){

        this.postID = -1;
        this.userID = "";
        this.comment = "";
        this.poster = "";
    }



}