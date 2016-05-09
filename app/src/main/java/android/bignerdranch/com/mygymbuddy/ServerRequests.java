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
import org.json.JSONArray;
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

    public void updateUserDataInBackground(User user, GetUserCallback userCallback){
        mProgressDialog.show();
        new updateUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback){
        mProgressDialog.show();
        new fetchUserDataAsyncTask(user, callback).execute();

    }

    public void fetchProfileUserInBackground(User user, GetUserCallback callback){
        mProgressDialog.show();
        new fetchProfileUserAsyncTask(user, callback).execute();

    }

    public void fetchWorkoutDataInBackground(WorkoutPlan workoutPlan, GetWorkoutCallback workoutCallback){
        mProgressDialog.show();
        new fetchWorkoutDataAsyncTask(workoutPlan, workoutCallback).execute();
    }


    public void storeTrainerDataInBackground(Trainer trainer, GetTrainerCallback trainerCallback){
        mProgressDialog.show();
        new StoreTrainerDataAsyncTask(trainer, trainerCallback).execute();
    }

    public void fetchTrainerDataInBackgorund(Trainer trainer, GetTrainerCallback trainerCallback){
        mProgressDialog.show();
        new fetchTrainerDataAsyncTask(trainer, trainerCallback).execute();

    }

    public void fetchTrainerByID(Trainer trainer, GetTrainerCallback trainerCallback){
        mProgressDialog.show();
        new fetchTrainerByIDAsyncTask(trainer, trainerCallback).execute();

    }

    public void fetchTrainerByFocus(User user, GetUserCallback userCallback){
        mProgressDialog.show();
        new fetchTrainerByFocusAsyncTask(user, userCallback).execute();

    }


    public void storeThreadDataInBackground(Thread thread, GetThreadCallback threadCallBack){
        mProgressDialog.show();
        new StoreThreadDataAsyncTask(thread, threadCallBack).execute();

    }

    public void storeWallPostDataInBackground(WallPost post, GetWallPostCallback wallPostCallback){
        mProgressDialog.show();
        new StoreWallPostDataAsyncTask(post, wallPostCallback).execute();

    }

    public void storeThreadLikeDataInBackground(Thread thread, GetThreadCallback threadCallBack){
        //mProgressDialog.show();
        new StoreThreadLikeDataAsyncTask(thread, threadCallBack).execute();

    }

    public void storeCommentDataInBackground(Comment comment, GetCommentCallback commentCallback){
        mProgressDialog.show();
        new StoreCommentDataAsyncTask(comment, commentCallback).execute();

    }

    public void fetchForumInBackground(Forum forum, GetForumCallback callback){
        mProgressDialog.show();
        new fetchForumDataAsyncTask(forum, callback).execute();

    }

    public void fetchWallInBackground(User user, GetWallCallback callback){
        mProgressDialog.show();
        new fetchWallDataAsyncTask(user, callback).execute();

    }

    public void fetchClientListInBackground(Trainer trainer, GetClientListCallback callback){
        mProgressDialog.show();
        new fetchClientListAsyncTask(trainer, callback).execute();

    }

    public void fetchCommentForumInBackground(CommentForum commentForum, Thread thread, GetCommentForumCallback callback){
        mProgressDialog.show();
        new fetchCommentForumDataAsyncTask(commentForum, thread, callback).execute();

    }

    public void storeWorkoutDataInBackground(WorkoutPlan workoutPlan, GetWorkoutCallback workoutCallback){
        mProgressDialog.show();
        new StoreWorkoutDataAsyncTask(workoutPlan, workoutCallback).execute();
    }

    public void fetchRecoveryDataInBackground(RecoveryEx recoveryEx, GetRecoveryCallback recoveryCallback){
        mProgressDialog.show();
        new fetchRecoveryDataInBackground(recoveryEx, recoveryCallback).execute();
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
            dataToSend.add(new BasicNameValuePair("numWorkouts", mUser.numWorkouts + ""));
            dataToSend.add(new BasicNameValuePair("trainer", mUser.trainer + ""));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");

            try {
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


    public class updateUserDataAsyncTask extends AsyncTask<Void, Void, Void>{
        User mUser;
        GetUserCallback userCallback;
        public updateUserDataAsyncTask(User user, GetUserCallback userCallback){
            this.mUser = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", mUser.username + ""));
            dataToSend.add(new BasicNameValuePair("numWorkouts", mUser.numWorkouts + ""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "UpdateUser.php");

            try {
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
            dataToSend.add(new BasicNameValuePair("username", mUser.username));
            dataToSend.add(new BasicNameValuePair("password", mUser.password));


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
                    int numWorkouts = jsonObject.getInt("numWorkouts");
                    int trainer = jsonObject.getInt("trainer");


                    returnedUser = new User(name, mUser.username, mUser.password, heightFt,heightIn,
                            weight, benchMax, squatMax, deadMax, experience, goals, numWorkouts, trainer);

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

    public class fetchProfileUserAsyncTask extends AsyncTask<Void, Void, User> {
        User mUser;
        GetUserCallback userCallback;

        public fetchProfileUserAsyncTask(User user, GetUserCallback userCallback) {
            this.mUser = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", mUser.username));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchProfileUserData.php");

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
                    int numWorkouts = jsonObject.getInt("numWorkouts");
                    int trainer = jsonObject.getInt("trainer");


                    returnedUser = new User(name, mUser.username, mUser.password, heightFt,heightIn,
                            weight, benchMax, squatMax, deadMax, experience, goals, numWorkouts, trainer);

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
            dataToSend.add(new BasicNameValuePair("planID", mWorkoutPlan.planID));
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
                    String planID = jsonObject.getString("planID");
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



                    returnedWorkoutPlan = new WorkoutPlan(planID, planName, week, day, ex1, numSets1, numReps1, weight1,
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



    public class StoreTrainerDataAsyncTask extends AsyncTask<Void, Void, Void>{
        Trainer mTrainer;
        GetTrainerCallback trainerCallback;
        public StoreTrainerDataAsyncTask(Trainer trainer, GetTrainerCallback trainerCallback){
            this.mTrainer = trainer;
            this.trainerCallback = trainerCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", mTrainer.name));
            dataToSend.add(new BasicNameValuePair("email", mTrainer.email));
            dataToSend.add(new BasicNameValuePair("password", mTrainer.password));
            dataToSend.add(new BasicNameValuePair("age", mTrainer.age+""));
            dataToSend.add(new BasicNameValuePair("experience", mTrainer.experience+""));
            dataToSend.add(new BasicNameValuePair("focus", mTrainer.focus+""));
            dataToSend.add(new BasicNameValuePair("numClients", mTrainer.numClients+""));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "RegisterTrainer.php");

            try {
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
            trainerCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }


    public class fetchTrainerDataAsyncTask extends AsyncTask<Void, Void, Trainer> {
        Trainer mTrainer;
        GetTrainerCallback mTrainerCallback;

        public fetchTrainerDataAsyncTask(Trainer trainer, GetTrainerCallback trainerCallback) {
            this.mTrainer = trainer;
            this.mTrainerCallback = trainerCallback;
        }

        @Override
        protected Trainer doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", mTrainer.name));
            dataToSend.add(new BasicNameValuePair("email", mTrainer.email));
            dataToSend.add(new BasicNameValuePair("password", mTrainer.password));
            dataToSend.add(new BasicNameValuePair("age", mTrainer.age+""));
            dataToSend.add(new BasicNameValuePair("experience", mTrainer.experience+""));
            dataToSend.add(new BasicNameValuePair("focus", mTrainer.experience+""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchTrainerData.php");

            Trainer returnedTrainer = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "Value: \n"+result);
                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.length() ==0){
                    returnedTrainer = null;
                }else{
                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    String password = jsonObject.getString("password");
                    int age = jsonObject.getInt("age");
                    int experience = jsonObject.getInt("experience");
                    int focus = jsonObject.getInt("focus");
                    int numClients = jsonObject.getInt("numClients");
                    int trainerID = jsonObject.getInt("trainerID");



                    returnedTrainer = new Trainer(name, email, password, age,experience,
                            focus, numClients, trainerID);

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedTrainer;
        }

        @Override
        protected void onPostExecute(Trainer returnedTrainer){

            mProgressDialog.dismiss();
            mTrainerCallback.done(returnedTrainer);
            super.onPostExecute(returnedTrainer);
        }
    }

    public class fetchTrainerByIDAsyncTask extends AsyncTask<Void, Void, Trainer> {
        Trainer mTrainer;
        GetTrainerCallback mTrainerCallback;

        public fetchTrainerByIDAsyncTask(Trainer trainer, GetTrainerCallback trainerCallback) {
            this.mTrainer = trainer;
            this.mTrainerCallback = trainerCallback;
        }

        @Override
        protected Trainer doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("id", mTrainer.id+""));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchTrainerDataByID.php");

            Trainer returnedTrainer = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "Value: \n"+result);
                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.length() ==0){
                    returnedTrainer = null;
                }else{
                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");



                    returnedTrainer = new Trainer(mTrainer.id);
                    returnedTrainer.name = name;
                    returnedTrainer.email = email;

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedTrainer;
        }

        @Override
        protected void onPostExecute(Trainer returnedTrainer){

            mProgressDialog.dismiss();
            mTrainerCallback.done(returnedTrainer);
            super.onPostExecute(returnedTrainer);
        }
    }

    public class fetchTrainerByFocusAsyncTask extends AsyncTask<Void, Void, User> {
        User mUser;
        GetUserCallback mGetUserCallback;

        public fetchTrainerByFocusAsyncTask(User user, GetUserCallback userCallback) {
            this.mUser = user;
            this.mGetUserCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", mUser.username));
            dataToSend.add(new BasicNameValuePair("focus", mUser.goals+""));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchTrainerDataByFocus.php");

            User returnedUser = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "Value: \n"+result);
                JSONObject jsonObject = new JSONObject(result);


                    int trainerId = jsonObject.getInt("id");


                    returnedUser = mUser;
                    returnedUser.trainer = trainerId;


            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser){

            mProgressDialog.dismiss();
            mGetUserCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }


    public class StoreThreadDataAsyncTask extends AsyncTask<Void, Void, Void> {
        Thread mThread;
        GetThreadCallback threadCallback;
        public StoreThreadDataAsyncTask(Thread thread, GetThreadCallback threadCallback){
            this.mThread = thread;
            this.threadCallback = threadCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("user", mThread.user));
            dataToSend.add(new BasicNameValuePair("title", mThread.title));
            dataToSend.add(new BasicNameValuePair("text", mThread.text));
            dataToSend.add(new BasicNameValuePair("like", mThread.like + ""));
            dataToSend.add(new BasicNameValuePair("dislikes", mThread.dislikes + ""));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "NewThread.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));

                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



//                Log.d("ADebugTag", "Value: Thread!!!!!!!!!!!!!!!!!!!!!!! \n\n\n" + result);
                JSONObject jsonObject = new JSONObject(result);

//                Log.d("Register", "ValueJSON: \n\n\n\n\n" + result);



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid){

            mProgressDialog.dismiss();
            threadCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }


    public class StoreWallPostDataAsyncTask extends AsyncTask<Void, Void, Void> {
        WallPost mWallPost;
        GetWallPostCallback mWallPostCallback;
        public StoreWallPostDataAsyncTask(WallPost wallPost, GetWallPostCallback wallPostCallback){
            this.mWallPost = wallPost;
            this.mWallPostCallback = wallPostCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("userID", mWallPost.userID));
            dataToSend.add(new BasicNameValuePair("comment", mWallPost.comment));
            dataToSend.add(new BasicNameValuePair("poster", mWallPost.poster));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "NewWallPost.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));

                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



//                Log.d("ADebugTag", "Value: Thread!!!!!!!!!!!!!!!!!!!!!!! \n\n\n" + result);
                JSONObject jsonObject = new JSONObject(result);

//                Log.d("Register", "ValueJSON: \n\n\n\n\n" + result);



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid){

            mProgressDialog.dismiss();
            mWallPostCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }


    public class StoreThreadLikeDataAsyncTask extends AsyncTask<Void, Void, Void> {
        Thread mThread;
        GetThreadCallback threadCallback;
        public StoreThreadLikeDataAsyncTask(Thread thread, GetThreadCallback threadCallback){
            this.mThread = thread;
            this.threadCallback = threadCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("threadID", mThread.id+""));
            dataToSend.add(new BasicNameValuePair("user", mThread.user));
            dataToSend.add(new BasicNameValuePair("title", mThread.title));
            dataToSend.add(new BasicNameValuePair("text", mThread.text));
            dataToSend.add(new BasicNameValuePair("like", mThread.like + ""));
            dataToSend.add(new BasicNameValuePair("dislikes", mThread.dislikes+""));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "UpdateThread.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));

                HttpResponse httpResponse = client.execute(post);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid){

            mProgressDialog.dismiss();
            threadCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }



    public class StoreCommentDataAsyncTask extends AsyncTask<Void, Void, Void> {
        Comment mComment;
        GetCommentCallback mCommentCallback;
        public StoreCommentDataAsyncTask(Comment comment, GetCommentCallback commentCallback){
            this.mComment = comment;
            this.mCommentCallback = commentCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("threadID", mComment.threadID+""));
            dataToSend.add(new BasicNameValuePair("user", mComment.user));
            dataToSend.add(new BasicNameValuePair("text", mComment.text));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "NewComment.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));

                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



//                Log.d("ADebugTag", "Value: COMMENT!!!!!!!!!!!!!!!!!!!!!!! \n\n\n" + result);
                JSONObject jsonObject = new JSONObject(result);



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid){

            mProgressDialog.dismiss();
            mCommentCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }


    public class fetchForumDataAsyncTask extends AsyncTask<Void, Void, Forum> {
        Forum mForum;
        GetForumCallback mForumCallback;

        public fetchForumDataAsyncTask(Forum forum, GetForumCallback forumCallback) {
            this.mForum = forum;
            this.mForumCallback = forumCallback;
        }

        @Override
        protected Forum doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchForumData.php");

            Forum returnedForum = new Forum();
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



//                Log.d("ADebugTag", "JSON RETURN: \n" + result);
                JSONArray jsonArray = new JSONArray(result);

//                Log.d("ADebugTag", "JSON ARRAY!!!!!: \n" + jsonArray);


                if(jsonArray.length() ==0){
                    returnedForum = null;
                }else{
                    int i = 0;
                    for(i=0; i<jsonArray.length(); i++){
                        Thread tempThread = new Thread();


                        JSONObject object = jsonArray.getJSONObject(i);
                        tempThread.user = object.get("user").toString();
                        tempThread.title = object.get("title").toString();
                        tempThread.text = object.get("text").toString();
                        tempThread.like = object.getInt("num_like");
                        tempThread.id = object.getInt("threadID");
                        tempThread.dislikes = object.getInt("num_dislikes");




//                        Log.d("ADebugTag", "AHHHHHHHH!!!!!!!!!!!!!!: \n" + tempThread.title);
                        returnedForum.addThread(tempThread);


                        //

                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedForum;
        }

        @Override
        protected void onPostExecute(Forum returnedForum){

            mProgressDialog.dismiss();
            mForumCallback.done(returnedForum);
            super.onPostExecute(returnedForum);
        }
    }


    public class fetchWallDataAsyncTask extends AsyncTask<Void, Void, Wall> {
        User mUser;
        GetWallCallback mWallCallback;

        public fetchWallDataAsyncTask(User user, GetWallCallback wallCallback) {
            this.mUser = user;
            this.mWallCallback = wallCallback;
        }

        @Override
        protected Wall doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", mUser.username));
            Log.d("ADebugTag", "Going out: \n" + dataToSend.get(0));




            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchWallData.php");

            Wall returnedWall = new Wall();
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



               Log.d("ADebugTag", "JSON RETURN: \n" + result);
                JSONArray jsonArray = new JSONArray(result);

                Log.d("ADebugTag", "JSON ARRAY!!!!!: \n" + jsonArray);


                if(jsonArray.length() ==0){
                    returnedWall = null;
                }else{
                    int i = 0;
                    for(i=0; i<jsonArray.length(); i++){
                        WallPost tempPost = new WallPost();


                        JSONObject object = jsonArray.getJSONObject(i);
                        tempPost.postID = object.getInt("postID");
                        tempPost.userID = object.get("userID").toString();
                        tempPost.comment = object.get("comment").toString();
                        tempPost.poster = object.get("poster").toString();


//                        Log.d("ADebugTag", "AHHHHHHHH!!!!!!!!!!!!!!: \n" + tempThread.title);
                        returnedWall.addPost(tempPost);

                        //

                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedWall;
        }

        @Override
        protected void onPostExecute(Wall returnedWall){

            mProgressDialog.dismiss();
            mWallCallback.done(returnedWall);
            super.onPostExecute(returnedWall);
        }
    }


    public class fetchClientListAsyncTask extends AsyncTask<Void, Void, ClientForum> {
        Trainer mTrainer;
        GetClientListCallback mClientListCallback;

        public fetchClientListAsyncTask(Trainer trainer, GetClientListCallback clientListCallback) {
            this.mTrainer = trainer;
            this.mClientListCallback = clientListCallback;
        }

        @Override
        protected ClientForum doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("trainerID", mTrainer.id+""));
            Log.d("ADebugTag", "Sending Out: \n" + mTrainer.id);



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchClientList.php");

            ClientForum returnedForum = new ClientForum();
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "JSON RETURN: \n" + result);
                JSONArray jsonArray = new JSONArray(result);

                Log.d("ADebugTag", "JSON ARRAY!!!!!: \n" + jsonArray);


                if(jsonArray.length() ==0){
                    returnedForum = null;
                }else{
                    int i = 0;
                    for(i=0; i<jsonArray.length(); i++){
                        User temp = new User();


                        JSONObject object = jsonArray.getJSONObject(i);
                        temp.name = object.get("name").toString();
                        temp.benchMax = object.getInt("benchmax");
                        temp.squatMax = object.getInt("squatmax");
                        temp.deadMax = object.getInt("deadmax");


//                        Log.d("ADebugTag", "AHHHHHHHH!!!!!!!!!!!!!!: \n" + tempThread.title);
                        returnedForum.addClient(temp);


                        //

                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedForum;
        }

        @Override
        protected void onPostExecute(ClientForum returnedForum){

            mProgressDialog.dismiss();
            mClientListCallback.done(returnedForum);
            super.onPostExecute(returnedForum);
        }
    }

    public class fetchCommentForumDataAsyncTask extends AsyncTask<Void, Void, CommentForum> {
        CommentForum mCommentForum;
        GetCommentForumCallback mCommentForumCallback;
        Thread mThread;

        public fetchCommentForumDataAsyncTask(CommentForum commentForum, Thread thread, GetCommentForumCallback commentForumCallback) {
            this.mCommentForum = commentForum;
            this.mCommentForumCallback = commentForumCallback;
            this.mThread = thread;
        }

        @Override
        protected CommentForum doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("currentThreadID", mThread.id+""));
            Log.d("ADebugTag", "MTHREAD LOOK HERE ID!!!!!: \n" + mThread.id);



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchCommentForumData.php");

            CommentForum returnedCommentForum = new CommentForum();
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "JSON RETURN: \n" + result);
                JSONArray jsonArray = new JSONArray(result);

          //      Log.d("ADebugTag", "JSON ARRAY!!!!!: \n" + jsonArray);


                if(jsonArray.length() ==0){
                    returnedCommentForum = new CommentForum();
                }else{
                    int i = 0;
                    for(i=0; i<jsonArray.length(); i++){
                        Comment tempComment = new Comment();


                        JSONObject object = jsonArray.getJSONObject(i);
                        tempComment.user = object.get("user").toString();
                        tempComment.text = object.get("text").toString();
                        tempComment.threadID = object.getInt("threadID");
                        tempComment.commentID = object.getInt("commentID");




//                        Log.d("ADebugTag", "COMMENTS!!!!!!!!!!!!!!: \n" + tempComment.text);
                        returnedCommentForum.addComment(tempComment);


                        //

                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedCommentForum;
        }

        @Override
        protected void onPostExecute(CommentForum returnedCommentForum){

            mProgressDialog.dismiss();
            mCommentForumCallback.done(returnedCommentForum);
            super.onPostExecute(returnedCommentForum);
        }
    }


    public class StoreWorkoutDataAsyncTask extends AsyncTask<Void, Void, Void>{
        WorkoutPlan mWorkoutPlan;
        GetWorkoutCallback mWorkoutCallback;
        public StoreWorkoutDataAsyncTask(WorkoutPlan workoutPlan, GetWorkoutCallback getWorkoutCallback){
            this.mWorkoutPlan = workoutPlan;
            this.mWorkoutCallback = getWorkoutCallback;
        }

        @Override
        protected Void doInBackground(Void...params){

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("planID", mWorkoutPlan.planID));
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


            Log.d("ADebugTag", "Workout Value Value: \n" + dataToSend.toString());


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "StoreWorkout.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                Log.d("ADebugTag", "MADE IT TO HERE Value: \n" + result);

                JSONObject jsonObject = new JSONObject(result);






            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid){

            mProgressDialog.dismiss();
            mWorkoutCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class fetchRecoveryDataInBackground extends AsyncTask<Void, Void, RecoveryEx> {
        RecoveryEx mRecoveryEx;
        GetRecoveryCallback mRecoveryCallback;

        public fetchRecoveryDataInBackground(RecoveryEx recoveryEx,GetRecoveryCallback recoveryCallback) {
            this.mRecoveryEx = recoveryEx;
            this.mRecoveryCallback = recoveryCallback;
        }

        @Override
        protected RecoveryEx doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("body_part", mRecoveryEx.body_part));
            dataToSend.add(new BasicNameValuePair("discomfort", mRecoveryEx.discomfort));

            Log.d("ADebugTag", "body part LOOK HERE ID!!!!!: \n" + mRecoveryEx.body_part + "\n\ndiscomfort: " + mRecoveryEx.discomfort + "\n\n");



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchRecoveryEx.php");

            RecoveryEx returnedEx = new RecoveryEx();
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);



                Log.d("ADebugTag", "JSON RETURN: \n" + result);
                //      Log.d("ADebugTag", "JSON ARRAY!!!!!: \n" + jsonArray);

                JSONObject jsonObject = new JSONObject(result);

                String name = jsonObject.getString("name");
                String body_part = jsonObject.getString("body_part");
                String discomfort = jsonObject.getString("discomfort");
                String ins1 = jsonObject.getString("ins1");
                String ins2 = jsonObject.getString("ins2");
                String ins3 = jsonObject.getString("ins3");
                String further = jsonObject.getString("further");

                returnedEx = new RecoveryEx(name, body_part, discomfort, ins1, ins2, ins3, further);



            }catch (Exception e){
                e.printStackTrace();
            }
            return returnedEx;
        }

        @Override
        protected void onPostExecute(RecoveryEx recoveryEx){

            mProgressDialog.dismiss();
            mRecoveryCallback.done(recoveryEx);
            super.onPostExecute(recoveryEx);
        }
    }








}
