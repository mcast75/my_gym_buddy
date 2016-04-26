package android.bignerdranch.com.mygymbuddy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mike on 3/30/16.
 */
public class TrainerLocalStore {


    public static final String SP_NAME = "trainerDetails";
    SharedPreferences trainerLocalDatabase;

    public TrainerLocalStore(Context context){
        trainerLocalDatabase = context.getSharedPreferences(SP_NAME,0);

    }

    public void storeTrainerData(Trainer trainer){
        SharedPreferences.Editor spEditor = trainerLocalDatabase.edit();
        spEditor.putString("name", trainer.name);
        spEditor.putString("email", trainer.email);
        spEditor.putString("password", trainer.password);
        spEditor.putInt("age", trainer.age);
        spEditor.putInt("experience", trainer.experience);
        spEditor.putInt("focus", trainer.focus);
        spEditor.putInt("id", trainer.id);

        spEditor.commit();
    }

    public Trainer getLoggedInTrainer(){
        String name = trainerLocalDatabase.getString("name", "");
        String email = trainerLocalDatabase.getString("email", "");
        String password = trainerLocalDatabase.getString("password", "");
        int age = trainerLocalDatabase.getInt("experience", -1);
        int experience = trainerLocalDatabase.getInt("experience",-1);
        int focus = trainerLocalDatabase.getInt("focus",-1);
        int id = trainerLocalDatabase.getInt("id", -1);

        Trainer storedTrainer = new Trainer(name, email, password, age, experience,
                focus, id);
        return storedTrainer;

    }

    public void setTrainerLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = trainerLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();

    }


    public boolean getUserLoggedIn(){
        if(trainerLocalDatabase.getBoolean("loggedIn",false) == true){
            return true;
        }else {
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = trainerLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
