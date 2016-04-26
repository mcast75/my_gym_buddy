package android.bignerdranch.com.mygymbuddy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mike on 11/8/15.
 */
public class RecoveryLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences recoveryLocalDatabase;

    public RecoveryLocalStore(Context context){
        recoveryLocalDatabase = context.getSharedPreferences(SP_NAME,0);

    }

    public void storeRecoveryEx(RecoveryEx ex){
        SharedPreferences.Editor spEditor = recoveryLocalDatabase.edit();
        spEditor.putString("body_part", ex.body_part);
        spEditor.putString("discomfort", ex.discomfort);
        spEditor.putString("ins1", ex.ins1);
        spEditor.putString("ins2", ex.ins2);
        spEditor.putString("ins3", ex.ins3);
        spEditor.putString("further", ex.further);
        spEditor.putString("name", ex.name);
        spEditor.commit();
    }

    public RecoveryEx getRecoveryEx(){
        String body_part = recoveryLocalDatabase.getString("body_part", "");
        String discomfort = recoveryLocalDatabase.getString("discomfort", "");
        String ins1 = recoveryLocalDatabase.getString("ins1", "");
        String ins2 = recoveryLocalDatabase.getString("ins2", "");
        String ins3 = recoveryLocalDatabase.getString("ins3","");
        String further = recoveryLocalDatabase.getString("further","");
        String name = recoveryLocalDatabase.getString("name", "");

        RecoveryEx storedRecoveryEx = new RecoveryEx(name, body_part, discomfort, ins1, ins2, ins3,
                further);
        return storedRecoveryEx;

    }


}
