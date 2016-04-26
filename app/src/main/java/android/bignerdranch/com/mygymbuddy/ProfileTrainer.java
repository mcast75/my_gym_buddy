package android.bignerdranch.com.mygymbuddy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ProfileTrainer extends AppCompatActivity implements View.OnClickListener {

    Button bProfHome, bContact;
    TextView tvProfName, tvAge, tvExperience, tvSpecialty, tvEmail, tvProfID;
    TrainerLocalStore mTrainerLocalStore;

    ImageView profPicture;
    Button bPhoto;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_trainer);

        tvProfID = (TextView) findViewById(R.id.profID);
        tvProfName = (TextView) findViewById(R.id.profName);
        tvAge = (TextView) findViewById(R.id.profAge);
        tvExperience = (TextView) findViewById(R.id.profExp);
        tvSpecialty = (TextView) findViewById(R.id.profFocus);
        tvEmail = (TextView) findViewById(R.id.profEmail);


        profPicture = (ImageView) findViewById(R.id.profPic);
        bPhoto = (Button) findViewById(R.id.bPhoto);

        bPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        bContact = (Button) findViewById(R.id.bContact);
        bContact.setOnClickListener(this);


        bProfHome = (Button) findViewById(R.id.profHome);

        bProfHome.setOnClickListener(this);

        mTrainerLocalStore = new TrainerLocalStore(this);

        context = this;
    }


    @Override
    public void onStart() {
        super.onStart();

        Trainer trainer = mTrainerLocalStore.getLoggedInTrainer();

        tvProfID.setText(trainer.name + " (Trainer)");
        tvProfName.setText(trainer.name);
        tvAge.setText("" + trainer.age);
        tvExperience.setText("" + trainer.experience);
        if (trainer.focus == 1){
            tvSpecialty.setText("Beginners");
        }else if(trainer.focus == 2){
            tvSpecialty.setText("General Fitness");
        }else if(trainer.focus == 3) {
            tvSpecialty.setText("Strength Loss");
        }else if(trainer.focus == 4) {
            tvSpecialty.setText("Weight Loss");
        }

        tvEmail.setText(trainer.email);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profHome:
                startActivity(new Intent(ProfileTrainer.this, HomeTrainer.class));


                break;

            case R.id.bContact:


                 /* Create the Intent */
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

/* Fill it with Data */
                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mTrainerLocalStore.getLoggedInTrainer().email});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Gym Buddy Community");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi "+mTrainerLocalStore.getLoggedInTrainer().name+"!\n\n");

/* Send it off to the Activity-Chooser */
                context.startActivity(Intent.createChooser(emailIntent, "Contact "+mTrainerLocalStore.getLoggedInTrainer().name+"..."));


                break;
        }

    }

    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileTrainer.this);
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

}



