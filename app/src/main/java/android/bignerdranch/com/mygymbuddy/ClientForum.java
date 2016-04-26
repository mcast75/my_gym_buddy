package android.bignerdranch.com.mygymbuddy;

/**
 * This program is designed to manage all the threads returned from the database in an ArrayList.
 */

import java.util.ArrayList;

public class ClientForum {

    ArrayList<User> allClients;

    public ClientForum(){

        this.allClients = new ArrayList<User>();
    }

    public void addClient(User user){

        this.allClients.add(user);

    }

    public ArrayList<User> getAllClients(){
        return this.allClients;
    }
}
