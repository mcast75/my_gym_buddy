package android.bignerdranch.com.mygymbuddy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mike on 11/8/15.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);

    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.putInt("heightFt", user.heightFt);
        spEditor.putInt("heightIn", user.heightIn);
        spEditor.putInt("weight", user.weight);
        spEditor.putInt("benchMax", user.benchMax);
        spEditor.putInt("squatMax", user.squatMax);
        spEditor.putInt("deadMax", user.deadMax);
        spEditor.putInt("experience", user.experience);
        spEditor.putInt("goals", user.goals);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username","");
        String password = userLocalDatabase.getString("password", "");
        int heightFt = userLocalDatabase.getInt("heightFt", -1);
        int heightIn = userLocalDatabase.getInt("heightIn",-1);
        int weight = userLocalDatabase.getInt("weight",-1);
        int benchMax = userLocalDatabase.getInt("benchMax", -1);
        int squatMax = userLocalDatabase.getInt("squatMax", -1);
        int deadMax = userLocalDatabase.getInt("deadMax", -1);
        int experience = userLocalDatabase.getInt("experience",-1);
        int goals = userLocalDatabase.getInt("goals", -1);

        User storedUser = new User(name, username, password, heightFt, heightIn,
        weight, benchMax, squatMax, deadMax, experience, goals);
        return storedUser;

    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();

    }


    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn",false) == true){
            return true;
        }else {
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
