package android.bignerdranch.com.mygymbuddy;

/**
 * Created by Mike on 11/8/15.
 */
public class User {
    String name, username, password;
    int heightFt, heightIn, weight, benchMax, squatMax, deadMax, experience, goals, numWorkouts, trainer;

    public User(String name, String username, String password, int heightFt, int heightIn,
                int weight, int benchMax, int squatMax, int deadMax, int experience, int goal, int numWorkouts, int trainer){

        this.name = name;
        this.username = username;
        this.password = password;
        this.heightFt = heightFt;
        this.heightIn = heightIn;
        this.weight = weight;
        this.benchMax = benchMax;
        this.squatMax = squatMax;
        this.deadMax = deadMax;
        this.experience = experience;
        this.goals = goal;
        this.numWorkouts = numWorkouts;
        this.trainer = trainer;
    }

    public User(String name, String username, String password, int heightFt, int heightIn,
                int weight, int experience, int goals, int numWorkouts){

        this.name = name;
        this.username = username;
        this.password = password;
        this.heightFt = heightFt;
        this.heightIn = heightIn;
        this.weight = weight;
        this.benchMax = -1;
        this.squatMax = -1;
        this.deadMax = -1;
        this.experience = experience;
        this.goals = goals;
        this.numWorkouts = numWorkouts;
        this.trainer = 0;
    }

    public User(String username, String password){

        this.name = "";
        this.username = username;
        this.password = password;
        this.heightFt = -1;
        this.heightIn = -1;
        this.weight = -1;
        this.benchMax = -1;
        this.squatMax = -1;
        this.deadMax = -1;
        this.numWorkouts = 0;
        this.trainer = 0;
    }

    public User(){

        this.name = "";
        this.username = "";
        this.password = "";
        this.heightFt = -1;
        this.heightIn = -1;
        this.weight = -1;
        this.benchMax = -1;
        this.squatMax = -1;
        this.deadMax = -1;
        this.numWorkouts = 0;
        this.trainer = 0;
    }

    public User(String name){

        this.name = "";
        this.username = name;
        this.password = "";
        this.heightFt = -1;
        this.heightIn = -1;
        this.weight = -1;
        this.benchMax = -1;
        this.squatMax = -1;
        this.deadMax = -1;
        this.numWorkouts = 0;
        this.trainer = 0;
    }
}
