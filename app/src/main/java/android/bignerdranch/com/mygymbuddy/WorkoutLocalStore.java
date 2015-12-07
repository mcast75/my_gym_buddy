package android.bignerdranch.com.mygymbuddy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mike on 11/28/15.
 */
public class WorkoutLocalStore {

    public static final String SP_NAME = "workoutDetails";
    SharedPreferences workoutLocalDatabase;

    public WorkoutLocalStore(Context context){
        workoutLocalDatabase = context.getSharedPreferences(SP_NAME,1);

    }

    public void storeWorkoutData(WorkoutPlan workout){
        SharedPreferences.Editor spEditor = workoutLocalDatabase.edit();

        spEditor.putString("planName", workout.planName);
        spEditor.putInt("week", workout.week);
        spEditor.putInt("day", workout.day);

        spEditor.putString("ex1", workout.ex1);
        spEditor.putInt("numSets1", workout.numSets1);
        spEditor.putInt("numReps1", workout.numReps1);
        spEditor.putInt("weight1", workout.weight1);

        spEditor.putString("ex2", workout.ex2);
        spEditor.putInt("numSets2", workout.numSets2);
        spEditor.putInt("numReps2", workout.numReps2);
        spEditor.putInt("weight2", workout.weight2);

        spEditor.putString("ex3", workout.ex3);
        spEditor.putInt("numSets3", workout.numSets3);
        spEditor.putInt("numReps3", workout.numReps3);
        spEditor.putInt("weight3", workout.weight3);

        spEditor.putString("ex4", workout.ex4);
        spEditor.putInt("numSets4", workout.numSets4);
        spEditor.putInt("numReps4", workout.numReps4);
        spEditor.putInt("weight4", workout.weight4);

        spEditor.putString("ex5", workout.ex5);
        spEditor.putInt("numSets5", workout.numSets5);
        spEditor.putInt("numReps5", workout.numReps5);
        spEditor.putInt("weight5", workout.weight5);

        spEditor.putString("ex6", workout.ex6);
        spEditor.putInt("numSets6", workout.numSets6);
        spEditor.putInt("numReps6", workout.numReps6);
        spEditor.putInt("weight6", workout.weight6);

        spEditor.putString("ex7", workout.ex7);
        spEditor.putInt("numSets7", workout.numSets7);
        spEditor.putInt("numReps7", workout.numReps7);
        spEditor.putInt("weight7", workout.weight7);

        spEditor.putString("ex8", workout.ex8);
        spEditor.putInt("numSets8", workout.numSets8);
        spEditor.putInt("numReps8", workout.numReps8);
        spEditor.putInt("weight8", workout.weight8);



        spEditor.commit();
    }

    public WorkoutPlan getCurrentWorkout(){
        String planName = workoutLocalDatabase.getString("planName", "");
        int week = workoutLocalDatabase.getInt("week", -1);
        int day = workoutLocalDatabase.getInt("day", -1);

        String ex1 = workoutLocalDatabase.getString("ex1", "");
        int numSets1 = workoutLocalDatabase.getInt("numSets1", -1);
        int numReps1 = workoutLocalDatabase.getInt("numReps1", -1);
        int weight1 = workoutLocalDatabase.getInt("weight1", -1);

        String ex2 = workoutLocalDatabase.getString("ex2", "");
        int numSets2 = workoutLocalDatabase.getInt("numSets2", -1);
        int numReps2 = workoutLocalDatabase.getInt("numReps2", -1);
        int weight2 = workoutLocalDatabase.getInt("weight2", -1);

        String ex3 = workoutLocalDatabase.getString("ex3", "");
        int numSets3 = workoutLocalDatabase.getInt("numSets3", -1);
        int numReps3 = workoutLocalDatabase.getInt("numReps3", -1);
        int weight3 = workoutLocalDatabase.getInt("weight3", -1);

        String ex4 = workoutLocalDatabase.getString("ex4", "");
        int numSets4 = workoutLocalDatabase.getInt("numSets4", -1);
        int numReps4 = workoutLocalDatabase.getInt("numReps4", -1);
        int weight4 = workoutLocalDatabase.getInt("weight4", -1);

        String ex5 = workoutLocalDatabase.getString("ex5", "");
        int numSets5 = workoutLocalDatabase.getInt("numSets5", -1);
        int numReps5 = workoutLocalDatabase.getInt("numReps5", -1);
        int weight5 = workoutLocalDatabase.getInt("weight5", -1);

        String ex6 = workoutLocalDatabase.getString("ex6", "");
        int numSets6 = workoutLocalDatabase.getInt("numSets6", -1);
        int numReps6 = workoutLocalDatabase.getInt("numReps6", -1);
        int weight6 = workoutLocalDatabase.getInt("weight6", -1);

        String ex7 = workoutLocalDatabase.getString("ex7", "");
        int numSets7 = workoutLocalDatabase.getInt("numSets7", -1);
        int numReps7 = workoutLocalDatabase.getInt("numReps7", -1);
        int weight7 = workoutLocalDatabase.getInt("weight7", -1);

        String ex8 = workoutLocalDatabase.getString("ex8", "");
        int numSets8 = workoutLocalDatabase.getInt("numSets8", -1);
        int numReps8 = workoutLocalDatabase.getInt("numReps8", -1);
        int weight8 = workoutLocalDatabase.getInt("weight8", -1);

        WorkoutPlan storedWorkout = new WorkoutPlan(planName, week, day, ex1, numSets1, numReps1, weight1,
                ex2, numSets2, numReps2, weight2, ex3, numSets3, numReps3, weight3, ex4, numSets4,
                numReps4, weight4, ex5, numSets5, numReps5, weight5, ex6, numSets6, numReps6,
                weight6, ex7, numSets7, numReps7, weight7, ex8, numSets8, numReps8, weight8);
        return storedWorkout;


    }



    public void clearWorkoutData(){
        SharedPreferences.Editor spEditor = workoutLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
