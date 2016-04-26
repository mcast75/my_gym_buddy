package android.bignerdranch.com.mygymbuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    Button bProfHome;
    TextView tvProfName, tvProfHeightFt, tvProfHeightIn, tvProfWeight, tvProfBM, tvProfSM, tvProfDM, tvProfID, tvProfGoals;
    UserLocalStore mUserLocalStore;
    ProfileLocalStore mProfileLocalStore;
    TextView createWallPost;

    ArrayList<WallPost> temp;

    ImageView profPicture;
    Button bPhoto;
    Context mContext;
    User profUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileLocalStore = new ProfileLocalStore(this);

        createWallPost = (TextView) findViewById(R.id.createWallPost);
        createWallPost.setOnClickListener(this);

        tvProfID = (TextView) findViewById(R.id.profID);
        tvProfName = (TextView) findViewById(R.id.profName);
        tvProfHeightFt = (TextView) findViewById(R.id.profHeightFt);
        tvProfHeightIn = (TextView) findViewById(R.id.profHeightIn);
        tvProfWeight = (TextView) findViewById(R.id.profWeight);
        tvProfBM = (TextView) findViewById(R.id.profBM);
        tvProfSM = (TextView) findViewById(R.id.profSM);
        tvProfDM = (TextView) findViewById(R.id.profDM);

        profPicture = (ImageView) findViewById(R.id.profPic);
        bPhoto = (Button) findViewById(R.id.bPhoto);

        mUserLocalStore = new UserLocalStore(this);

        if((mProfileLocalStore.getProfile().username.equals(mUserLocalStore.getLoggedInUser().username))) {
            bPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectImage();
                }
            });
        }else{
            bPhoto.setVisibility(View.INVISIBLE);
        }



        bProfHome = (Button) findViewById(R.id.profHome);

        bProfHome.setOnClickListener(this);


        mContext = this;
    }


    @Override
    public void onStart() {
        super.onStart();

        getProfUser(mProfileLocalStore.getProfile());
        getWallPosts(mProfileLocalStore.getProfile());






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profHome:
                startActivity(new Intent(Profile.this, Home.class));

                break;

            case R.id.createWallPost:
                startActivity(new Intent(this, CreatePost.class));
                break;
        }

    }

    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        builder.setTitle("Change My Profile Pic");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    profPicture.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                //Log.d("path of image from gallery......******************.........", picturePath+"");
                profPicture.setImageBitmap(thumbnail);
            }
        }
    }

    private void getProfUser(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchProfileUserInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                tvProfID.setText(returnedUser.username);
                tvProfName.setText(returnedUser.name);
                tvProfHeightFt.setText("" + returnedUser.heightFt);
                tvProfHeightIn.setText("" + returnedUser.heightIn);
                tvProfWeight.setText("" + returnedUser.weight);

                if (returnedUser.benchMax == -1) {
                    tvProfBM.setText("Not Given");
                } else {
                    tvProfBM.setText("" + returnedUser.benchMax);
                }

                if (returnedUser.squatMax == -1) {
                    tvProfSM.setText("Not Given");
                } else {
                    tvProfSM.setText("" + returnedUser.squatMax);
                }

                if (returnedUser.deadMax == -1) {
                    tvProfDM.setText("Not Given");
                } else {
                    tvProfDM.setText("" + returnedUser.deadMax);
                }


            }
        });
    }

    private void getWallPosts(User user){
        ServerRequests serverRequests = new ServerRequests((this));
        serverRequests.fetchWallInBackground(user, new GetWallCallback() {
            @Override
            public void done(Wall returnedWall) {

                TableLayout table = (TableLayout) findViewById(R.id.publicTable);
                int i = 0;
                temp = returnedWall.getAllPosts();


                for (i = 0; i < temp.size(); i++) {

                    Log.d("PROFILE", "USER POST: \n" + mProfileLocalStore.getProfile().username);


                    if(temp.get(i).userID.equals(mProfileLocalStore.getProfile().username)) {

                        Log.d("PROFILE", "USER POST: \n" + i);


                        TableRow row = new TableRow(mContext);
                        table.addView(row);
                        LinearLayout ll = new LinearLayout(mContext);
                        row.addView(ll);


                        ll.setOrientation(LinearLayout.VERTICAL);
                        ll.setPadding(0, 0, 0, 70);

                        LinearLayout ll2 = new LinearLayout(mContext);
                        ll.setPadding(0, 0, 0, 40);
                        ll.addView(ll2);
                        TextView tvTitle = new TextView(mContext);
                        tvTitle.setText(temp.get(i).poster);

                        Point size2 = new Point();
                        getWindowManager().getDefaultDisplay().getSize(size2);
                        int width2 = size2.x;
                        tvTitle.setLayoutParams(new LinearLayout.LayoutParams(width2 - 10
                                , ViewGroup.LayoutParams.WRAP_CONTENT));

                        tvTitle.setTextColor(Color.WHITE);
                        tvTitle.setTextSize(24);
                        ll2.addView(tvTitle);
                        TextView line = new TextView(mContext);
                        line.setTextSize(2);
                        line.setTextColor(Color.LTGRAY);
                        line.setText("_____________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "_______________________________________________________________________" +
                                "______________________________________________________________________");


                        LinearLayout ll3 = new LinearLayout(mContext);
                        ll.addView(ll3);
                        TextView space = new TextView(mContext);
                        space.setText("      ");
                        ll3.addView(space);
                        TextView comments = new TextView(mContext);
                        comments.setTextSize(24);
                        comments.setText(temp.get(i).comment);
                        ll3.addView(comments);
                        ll.addView(line);
                    }


                }
            }


        });
    }

}



