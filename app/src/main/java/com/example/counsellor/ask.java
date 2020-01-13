package com.example.counsellor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class ask extends AppCompatActivity {

    RadioButton comp, tv, campus, litr, pni, pg, travel, fin, research;
    EditText txtAsk;
    Button btn_ask;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        DatabaseReference databaseReference;

        databaseReference = FirebaseDatabase.getInstance().getReference("Category");

        comp = (RadioButton) findViewById(R.id.rdCompCode);
        tv = (RadioButton) findViewById(R.id.rdTV);
        campus = (RadioButton) findViewById(R.id.rdCamp);
        litr = (RadioButton) findViewById(R.id.rdLitr);
        pni = (RadioButton) findViewById(R.id.rdPnI);
        pg = (RadioButton) findViewById(R.id.rdPG);
        travel = (RadioButton) findViewById(R.id.rdTravel);
        fin = (RadioButton) findViewById(R.id.rdFinance);
        research = (RadioButton) findViewById(R.id.rdResearch);
        txtAsk = (EditText) findViewById(R.id.txtAsk);
        btn_ask = (Button) findViewById(R.id.ask);


    }


    public void uploadQuestion() {

        String question = txtAsk.getText().toString();

        if (TextUtils.isEmpty(question)) {
            Toast.makeText(ask.this, "Enter a question mate!!", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Question Uploading");
            progressDialog.show();
            if (comp.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });
            } else if (tv.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else if (campus.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else if (litr.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else if (pni.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else if (pg.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else if (travel.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else if (fin.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else if (research.isChecked()) {
                PostItems postItems = new PostItems(
                        question, R.drawable.user2);


                String myCurrentDateTime = DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                PostItems postChild1 = new PostItems(question, R.drawable.user2);
                FirebaseDatabase.getInstance().getReference("Questions")
                        .child(myCurrentDateTime).setValue(postChild1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });

            } else {
                Toast.makeText(this, "Select a category!!", Toast.LENGTH_SHORT).show();
            }




        /*String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());


        FirebaseDatabase.getInstance().getReference("Questions")
                .child(myCurrentDateTime).setValue(postItems).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ask.this, "Question Uploaded", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                } else {
                    Toast.makeText(ask.this, "Question could not be uploaded", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
            }
        });*/
        }
    }


    public void onClickAsk2(View view) {
        uploadQuestion();
    }
}
