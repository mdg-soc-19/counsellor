package com.example.counsellor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
//import io.grpc.Compressor;
import id.zelory.compressor.Compressor;

import static id.zelory.compressor.Compressor.*;

public class AccountSetup extends AppCompatActivity {

    private static final String TAG = "AccountSetup";

    private Toolbar toolbarSetup;
    private ProgressBar progressBar;
    private CircleImageView userImg;
    private Uri main_uri = null;
    private Uri default_uri = null;
    private FirebaseAuth mAuth;
    private boolean isChanged = true;
    private FirebaseFirestore fireStore;
    private EditText Name;
    private FirebaseStorage firebaseStorage;
    private StorageReference mStorageRef;
    Button Submit;
    private String user_id;
    public Bitmap compressedImageBitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);

        toolbarSetup = findViewById(R.id.toolbarSetup);
        setSupportActionBar(toolbarSetup);
        getSupportActionBar().setTitle("Account Setup");

        Submit = findViewById(R.id.submit);
        progressBar = findViewById(R.id.AccountSettingsBar);
        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        fireStore = FirebaseFirestore.getInstance();
        Name = findViewById(R.id.name);
        userImg = findViewById(R.id.profile);
        default_uri = Uri.parse("R.mipmap.user");

        fireStore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        isChanged = false;
                        String name = task.getResult().getString("name");
                        String image = task.getResult().getString("image");
//                        Toast.makeText(AccountSetup.this,"DATA EXISTS",Toast.LENGTH_LONG).show();
                        Name.setText(name);

                        RequestOptions placeHolder = new RequestOptions();
                        placeHolder.placeholder(R.mipmap.user);

                        main_uri = Uri.parse(image);

                        Glide.with(AccountSetup.this).setDefaultRequestOptions( placeHolder.placeholder(R.mipmap.user)).load(image).into(userImg);

                    }
                    else{
                        main_uri = default_uri;
                        Toast.makeText(AccountSetup.this,"NO DATA EXISTS",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(AccountSetup.this,"Firestore Retrieve Error OUTSIDE",Toast.LENGTH_LONG).show();
                }
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String uName = Name.getText().toString();
                if (isChanged) {
                    if (!TextUtils.isEmpty(uName) && main_uri != null) {
                        progressBar.setVisibility(View.VISIBLE);
//                    String user_id = mAuth.getCurrentUser().getUid();

                        File imageFile = new File(main_uri.getPath());

                        if(imageFile != null) {
                            Log.d(TAG, "onClick: File imageFile created successfully");
                        }
                        
                        else{
                            Log.d(TAG, "onClick: imageFile path is E M P T Y!!!!!");
                        }
                        String path = imageFile.getAbsolutePath();

                        Log.d(TAG, "onClick: Absolute path received.");

                        compressedImageBitmap = BitmapFactory.decodeFile(path);

                        Log.d(TAG, "onClick: Value set in compressedImageBitmap");
                           /* try {
                                FileOutputStream outputStream = new FileOutputStream(imageFile);
                                compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }*/

                      //
                        //    saveBitmapToFile(imageFile);


                      /*  if(imageFile != null && imageFile.exists() && imageFile.canRead()) {
                            try {
                                String compressedFileName = "_" + imageFile.getName();

                                compressedImageBitmap = new Compressor(this)
                                        .setMaxWidth(100)
                                        .setMaxHeight(100)
                                        .setQuality(10)
                                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                        .compressToFile(imageFile, compressedFileName);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                             /*   .setMaxHeight(100)
                                .setMaxWidth(100)
                                .setQuality(10)
                                .compressToBitmap(imageFile);*/
                      /*  ByteArrayOutputStream out = new ByteArrayOutputStream();
                        compressedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);*/


                        //Uploading bitmap to firebase
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] thumbBitmap = baos.toByteArray();

                        UploadTask thumbImage = mStorageRef.child("/Profile_Photos/thumbs").child(user_id+".jpg").putBytes(thumbBitmap);
                        thumbImage.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    saveToFirestore(task, uName);
                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(AccountSetup.this, " Image Error" + error, Toast.LENGTH_LONG).show();
                                }
                                progressBar.setVisibility(View.INVISIBLE);

                            }
                        });
//
                    }
                    else {
                        Toast.makeText(AccountSetup.this, "No image", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
                else {
                    Toast.makeText(AccountSetup.this, "Image not changed", Toast.LENGTH_LONG).show();
                    saveToFirestore(null,Name.getText().toString());
                }
            }

        });

        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    Toast.makeText(AccountSetup.this, "ReadPerm", Toast.LENGTH_LONG).show();
                    if (ContextCompat.checkSelfPermission(AccountSetup.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AccountSetup.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//                        ActivityCompat.requestPermissions(AccountSetup.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

                    }
                    Toast.makeText(AccountSetup.this, "WritePerm", Toast.LENGTH_LONG).show();
                    if (ContextCompat.checkSelfPermission(AccountSetup.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(AccountSetup.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                        ActivityCompat.requestPermissions(AccountSetup.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

                    }
                }
                getPicture();
            }

            private void getPicture() {
                Toast.makeText(AccountSetup.this,"Successfully Saved",Toast.LENGTH_LONG).show();
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(AccountSetup.this);
            }


        });
    }

    //    private void saveToFirestore(@NonNull Task<UploadTask.TaskSnapshot> task,String uName) {
    private void saveToFirestore(Task<UploadTask.TaskSnapshot> task, final String uName) {

        final Uri[] downloadUri = new Uri[1];
        if(task != null) {
          //  downloadUri = task.getResult().getStorage().getDownloadUrl();

            Task<Uri> uriTask = task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return mStorageRef.getDownloadUrl();
                }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                         downloadUri[0] = task.getResult();
                    } else {

                    }
                }
            });
//
        }

        else {
            downloadUri[0] = main_uri;
        }
        Map<String,String> userMap= new HashMap<>();

        userMap.put("name",uName);
        userMap.put("image", downloadUri[0].toString());
        fireStore.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AccountSetup.this, "Settings Saved Succesfully", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(AccountSetup.this,MainActivity.class);
                    startActivity(main);
                }
                else {
                    String error = task.getException().getMessage();
                    Toast.makeText(AccountSetup.this, " FireStore Error" + error, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

   public File saveBitmapToFile(File file){
        try {

            FileOutputStream outputStream = new FileOutputStream(file);
            compressedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return file;

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                main_uri = result.getUri();
                userImg.setImageURI(main_uri);
                isChanged = true;
                String tct = main_uri.toString();
//                Toast.makeText(AccountSetup.this,tct,Toast.LENGTH_LONG).show();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

            }
        }
    }


}
