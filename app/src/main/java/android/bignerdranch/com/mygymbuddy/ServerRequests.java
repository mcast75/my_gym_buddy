package android.bignerdranch.com.mygymbuddy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mike on 11/9/15.
 */
public class ServerRequests {

    ProgressDialog mProgressDialog;
    public static final int CONNECTION_TIMEOUT = 1000*15;
    public static final String SERVER_ADDRESS = "http://castmgb.esy.es/";

    //public static final String SERVER_ADDRESS = "http://10.0.2.2:80/webservice/";


    public ServerRequests(Context context){

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("Processing");
        mProgressDialog.setMessage("Please_wait");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback){
        mProgressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback){
        mProgressDialog.show();
        new fetchUserDataAsyncTask(user, callback).execute();

    }

    public void fetchWorkoutDataInBackground(WorkoutPlan workoutPlan, GetWorkoutCallback workoutCallback){
        mProgressDialog.show();
        new fetchWorkoutDataAsyncTask(workoutPlan, workoutCallback).execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{
        User mUser;
        GetUserCallback userCallback;
        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback){
            this.mUser = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", mUser.name));
            dataToSend.add(new BasicNameValuePair("username", mUser.username));
            dataToSend.add(new BasicNameValuePair("password", mUser.password));
            dataToSend.add(new BasicNameValuePair("heightft", mUser.heightFt+""));
            dataToSend.add(new BasicNameValuePair("heightin", mUser.heightIn+""));
            dataToSend.add(new BasicNameValuePair("weight", mUser.weight+""));
            dataToSend.add(new BasicNameValuePair("benchmax", mUser.benchMax+""));
            dataToSend.add(new BasicNameValuePair("squatmax", mUser.squatMax + ""));
            dataToSend.add(new BasicNameValuePair("deadmax", mUser.deadMax + ""));
            dataToSend.add(new BasicNameValuePair("experience", mUser.experience + ""));
            dataToSend.add(new BasicNameValuePair("goals", mUser.goals + ""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);


                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "Value: \n" + result);
                JSONObject jsonObject = new JSONObject(result);



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid){

            mProgressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }



    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User mUser;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.mUser = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", mUser.name));
            dataToSend.add(new BasicNameValuePair("username", mUser.username));
            dataToSend.add(new BasicNameValuePair("password", mUser.password));
            dataToSend.add(new BasicNameValuePair("heightft", mUser.heightFt+""));
            dataToSend.add(new BasicNameValuePair("heightin", mUser.heightIn+""));
            dataToSend.add(new BasicNameValuePair("weight", mUser.weight+""));
            dataToSend.add(new BasicNameValuePair("benchMax", mUser.benchMax+""));
            dataToSend.add(new BasicNameValuePair("squatMax", mUser.squatMax + ""));
            dataToSend.add(new BasicNameValuePair("deadMax", mUser.deadMax + ""));
            dataToSend.add(new BasicNameValuePair("experience", mUser.experience + ""));
            dataToSend.add(new BasicNameValuePair("goals", mUser.goals + ""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User returnedUser = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "Value: \n"+result);
                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.length() ==0){
                    returnedUser = null;
                }else{
                    String name = jsonObject.getString("name");
                    int heightFt = jsonObject.getInt("heightft");
                    int heightIn = jsonObject.getInt("heightin");
                    int weight = jsonObject.getInt("weight");
                    int benchMax = jsonObject.getInt("benchmax");
                    int squatMax = jsonObject.getInt("squatmax");
                    int deadMax = jsonObject.getInt("deadmax");
                    int experience = jsonObject.getInt("experience");
                    int goals = jsonObject.getInt("goals");


                    returnedUser = new User(name, mUser.username, mUser.password, heightFt,heightIn,
                            weight, benchMax, squatMax, deadMax, experience, goals );

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser){

            mProgressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }


    public class fetchWorkoutDataAsyncTask extends AsyncTask<Void, Void, WorkoutPlan> {
        WorkoutPlan mWorkoutPlan;
        GetWorkoutCallback mWorkoutCallback;

        public fetchWorkoutDataAsyncTask(WorkoutPlan mWorkoutPlan, GetWorkoutCallback mWorkoutCallback) {
            this.mWorkoutPlan = mWorkoutPlan;
            this.mWorkoutCallback = mWorkoutCallback;
        }

        @Override
        protected WorkoutPlan doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("planName", mWorkoutPlan.planName));
            dataToSend.add(new BasicNameValuePair("week", mWorkoutPlan.week+""));
            dataToSend.add(new BasicNameValuePair("day", mWorkoutPlan.day+""));

            dataToSend.add(new BasicNameValuePair("ex1", mWorkoutPlan.ex1));
            dataToSend.add(new BasicNameValuePair("numSets1", mWorkoutPlan.numSets1+""));
            dataToSend.add(new BasicNameValuePair("numReps1", mWorkoutPlan.numReps1+""));
            dataToSend.add(new BasicNameValuePair("weight1", mWorkoutPlan.weight1+""));

            dataToSend.add(new BasicNameValuePair("ex2", mWorkoutPlan.ex2));
            dataToSend.add(new BasicNameValuePair("numSets2", mWorkoutPlan.numSets2+""));
            dataToSend.add(new BasicNameValuePair("numReps2", mWorkoutPlan.numReps2+""));
            dataToSend.add(new BasicNameValuePair("weight2", mWorkoutPlan.weight2+""));

            dataToSend.add(new BasicNameValuePair("ex3", mWorkoutPlan.ex3));
            dataToSend.add(new BasicNameValuePair("numSets3", mWorkoutPlan.numSets3+""));
            dataToSend.add(new BasicNameValuePair("numReps3", mWorkoutPlan.numReps3+""));
            dataToSend.add(new BasicNameValuePair("weight3", mWorkoutPlan.weight3+""));

            dataToSend.add(new BasicNameValuePair("ex4", mWorkoutPlan.ex4));
            dataToSend.add(new BasicNameValuePair("numSets4", mWorkoutPlan.numSets4+""));
            dataToSend.add(new BasicNameValuePair("numReps4", mWorkoutPlan.numReps4+""));
            dataToSend.add(new BasicNameValuePair("weight4", mWorkoutPlan.weight4+""));

            dataToSend.add(new BasicNameValuePair("ex5", mWorkoutPlan.ex5));
            dataToSend.add(new BasicNameValuePair("numSets5", mWorkoutPlan.numSets5+""));
            dataToSend.add(new BasicNameValuePair("numReps5", mWorkoutPlan.numReps5+""));
            dataToSend.add(new BasicNameValuePair("weight5", mWorkoutPlan.weight5+""));

            dataToSend.add(new BasicNameValuePair("ex6", mWorkoutPlan.ex6));
            dataToSend.add(new BasicNameValuePair("numSets6", mWorkoutPlan.numSets6+""));
            dataToSend.add(new BasicNameValuePair("numReps6", mWorkoutPlan.numReps6+""));
            dataToSend.add(new BasicNameValuePair("weight6", mWorkoutPlan.weight6+""));

            dataToSend.add(new BasicNameValuePair("ex7", mWorkoutPlan.ex7));
            dataToSend.add(new BasicNameValuePair("numSets7", mWorkoutPlan.numSets7+""));
            dataToSend.add(new BasicNameValuePair("numReps7", mWorkoutPlan.numReps7+""));
            dataToSend.add(new BasicNameValuePair("weight7", mWorkoutPlan.weight7+""));

            dataToSend.add(new BasicNameValuePair("ex8", mWorkoutPlan.ex8));
            dataToSend.add(new BasicNameValuePair("numSets8", mWorkoutPlan.numSets8+""));
            dataToSend.add(new BasicNameValuePair("numReps8", mWorkoutPlan.numReps8+""));
            dataToSend.add(new BasicNameValuePair("weight8", mWorkoutPlan.weight8+""));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchWorkoutData.php");

            WorkoutPlan returnedWorkoutPlan = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "WORKOUT DETAILS: \n"+result);
                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.length() ==0){
                    returnedWorkoutPlan = null;
                }else{
                    String planName = jsonObject.getString("planName");
                    int week = jsonObject.getInt("week");
                    int day = jsonObject.getInt("day");

                    String ex1 = jsonObject.getString("ex1");
                    int numSets1 = jsonObject.getInt("numSets1");
                    int numReps1 = jsonObject.getInt("numReps1");
                    int weight1 = jsonObject.getInt("weight1");

                    String ex2 = jsonObject.getString("ex2");
                    int numSets2 = jsonObject.getInt("numSets2");
                    int numReps2 = jsonObject.getInt("numReps2");
                    int weight2 = jsonObject.getInt("weight2");

                    String ex3 = jsonObject.getString("ex3");
                    int numSets3 = jsonObject.getInt("numSets3");
                    int numReps3 = jsonObject.getInt("numReps3");
                    int weight3 = jsonObject.getInt("weight3");

                    String ex4 = jsonObject.getString("ex4");
                    int numSets4 = jsonObject.getInt("numSets4");
                    int numReps4 = jsonObject.getInt("numReps4");
                    int weight4 = jsonObject.getInt("weight4");

                    String ex5 = jsonObject.getString("ex5");
                    int numSets5 = jsonObject.getInt("numSets5");
                    int numReps5 = jsonObject.getInt("numReps5");
                    int weight5 = jsonObject.getInt("weight5");

                    String ex6 = jsonObject.getString("ex6");
                    int numSets6 = jsonObject.getInt("numSets6");
                    int numReps6 = jsonObject.getInt("numReps6");
                    int weight6 = jsonObject.getInt("weight6");

                    String ex7 = jsonObject.getString("ex7");
                    int numSets7 = jsonObject.getInt("numSets7");
                    int numReps7 = jsonObject.getInt("numReps7");
                    int weight7 = jsonObject.getInt("weight7");

                    String ex8 = jsonObject.getString("ex8");
                    int numSets8 = jsonObject.getInt("numSets8");
                    int numReps8 = jsonObject.getInt("numReps8");
                    int weight8 = jsonObject.getInt("weight8");



                    returnedWorkoutPlan = new WorkoutPlan(planName, week, day, ex1, numSets1, numReps1, weight1,
                            ex2, numSets2, numReps2, weight2, ex3, numSets3, numReps3, weight3, ex4, numSets4,
                            numReps4, weight4, ex5, numSets5, numReps5, weight5, ex6, numSets6, numReps6,
                            weight6, ex7, numSets7, numReps7, weight7, ex8, numSets8, numReps8, weight8);

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedWorkoutPlan;
        }

        @Override
        protected void onPostExecute(WorkoutPlan returnedWorkoutPlan){

            mProgressDialog.dismiss();
            mWorkoutCallback.done(returnedWorkoutPlan);
            super.onPostExecute(returnedWorkoutPlan);
        }
    }






}
