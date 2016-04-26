package android.bignerdranch.com.mygymbuddy;

/**
 * This program is designed to manage all the threads returned from the database in an ArrayList.
 */

import java.util.ArrayList;

public class Wall {

    ArrayList<WallPost> allPosts;

    public Wall(){

        this.allPosts = new ArrayList<WallPost>();
    }

    public void addPost(WallPost post){

        this.allPosts.add(post);

    }

    public ArrayList<WallPost> getAllPosts(){
        return this.allPosts;
    }
}
