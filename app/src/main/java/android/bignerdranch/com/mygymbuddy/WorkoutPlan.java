package android.bignerdranch.com.mygymbuddy;

/**
 * Created by Mike on 11/23/15.
 */
public class WorkoutPlan {


    String planID, planName, ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8;
    int week, day, numSets1, numReps1, weight1, numSets2, numReps2, weight2, numSets3,
            numReps3, weight3, numSets4, numReps4, weight4, numSets5, numReps5, weight5,
            numSets6, numReps6, weight6, numSets7, numReps7, weight7, numSets8, numReps8,
            weight8;


    public WorkoutPlan(String planID, String planName, int week, int day, String ex1, int numSets1, int numReps1,
                       int weight1, String ex2, int numSets2, int numReps2,
                       int weight2, String ex3, int numSets3, int numReps3,
                       int weight3, String ex4, int numSets4, int numReps4,
                       int weight4, String ex5, int numSets5, int numReps5,
                       int weight5, String ex6, int numSets6, int numReps6,
                       int weight6, String ex7, int numSets7, int numReps7,
                       int weight7, String ex8, int numSets8, int numReps8,
                       int weight8){

        this.planID = planID;
        this.planName = planName;
        this.week = week;
        this.day =day;

        this.ex1 = ex1;
        this.numSets1 = numSets1;
        this.numReps1 = numReps1;
        this.weight1 = weight1;

        this.ex2 = ex2;
        this.numSets2 = numSets2;
        this.numReps2 = numReps2;
        this.weight2 = weight2;

        this.ex3 = ex3;
        this.numSets3 = numSets3;
        this.numReps3 = numReps3;
        this.weight3 = weight3;

        this.ex4 = ex4;
        this.numSets4 = numSets4;
        this.numReps4 = numReps4;
        this.weight4 = weight4;

        this.ex5 = ex5;
        this.numSets5 = numSets5;
        this.numReps5 = numReps5;
        this.weight5 = weight5;

        this.ex6 = ex6;
        this.numSets6 = numSets6;
        this.numReps6 = numReps6;
        this.weight6 = weight6;

        this.ex7 = ex7;
        this.numSets7 = numSets7;
        this.numReps7 = numReps7;
        this.weight7 = weight7;

        this.ex8 = ex8;
        this.numSets8 = numSets8;
        this.numReps8 = numReps8;
        this.weight8 = weight8;
    }


    public WorkoutPlan(String planID){

        this.planID = planID;
        this.planName = "";
        this.week = -1;
        this.day = -1;

        this.ex1 = "";
        this.numSets1 = -1;
        this.numReps1 = -1;
        this.weight1 = -1;

        this.ex2 = "";
        this.numSets2 = -1;
        this.numReps2 = -1;
        this.weight2 = -1;

        this.ex3 = "";
        this.numSets3 = -1;
        this.numReps3 = -1;
        this.weight3 = -1;

        this.ex4 = "";
        this.numSets4 = -1;
        this.numReps4 = -1;
        this.weight4 = -1;

        this.ex5 = "";
        this.numSets5 = -1;
        this.numReps5 = -1;
        this.weight5 = -1;

        this.ex6 = "";
        this.numSets6 = -1;
        this.numReps6 = -1;
        this.weight6 = -1;

        this.ex7 = "";
        this.numSets7 = -1;
        this.numReps7 = -1;
        this.weight7 = -1;

        this.ex8 = "";
        this.numSets8 = -1;
        this.numReps8 = -1;
        this.weight8 = -1;
    }

    public void setPlanName(String name){
        this.planName = name;
    }

}
