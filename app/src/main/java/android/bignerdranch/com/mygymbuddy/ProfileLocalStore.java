package android.bignerdranch.com.mygymbuddy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mike on 11/8/15.
 */
public class ProfileLocalStore {

    public static final String SP_NAME = "ProfileDetails";
    SharedPreferences profileLocalDatabase;
    boolean isTrainer;

    public ProfileLocalStore(Context context){
        profileLocalDatabase = context.getSharedPreferences(SP_NAME,0);

    }


    public void storeUser(User user){
        SharedPreferences.Editor spEditor = profileLocalDatabase.edit();
        spEditor.putString("username", user.username);
        spEditor.commit();
        isTrainer = false;
    }

    public void storeUser(Trainer trainer){
        SharedPreferences.Editor spEditor = profileLocalDatabase.edit();
        spEditor.putString("email", trainer.email);
        spEditor.commit();
        isTrainer = true;
    }

    public User getProfile(){

            return new User(profileLocalDatabase.getString("username", ""));

    }



    public boolean isTrainer(){
        return isTrainer;
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = profileLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
