package com.example.counsellor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.counsellor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    EditText txtName, txtEmail, txtPassword, txtConfirm, txtBio;
    Button btn_register;
    ProgressBar pgbar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    txtName = (EditText) findViewById(R.id.txt_name);
    txtEmail = (EditText) findViewById(R.id.txt_email);
    txtPassword = (EditText) findViewById(R.id.txt_password);
    txtConfirm = (EditText) findViewById(R.id.txt_confirmPassword);
    txtBio = (EditText) findViewById(R.id.txt_bio);


    firebaseAuth = FirebaseAuth.getInstance();



    btn_register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String name = txtName.getText().toString();
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            String confirm = txtConfirm.getText().toString();




        if(TextUtils.isEmpty(name)){
            Toast.makeText(Signup.this, "Enter Your Name! What are we supposed to call you??", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(email)){
                Toast.makeText(Signup.this, "Enter a valid Email Address", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(Signup.this, "Enter the password", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(confirm)){
                Toast.makeText(Signup.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.length()<6){
                Toast.makeText(Signup.this, "Password is too short, enter a password greater than 6 characters", Toast.LENGTH_SHORT).show();
            }

        //    pgbar.setVisibility(View.VISIBLE);


if(password.equals(confirm)){

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

          //                      pgbar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Toast.makeText(Signup.this, "Authentication complete", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });




            }
        }
    });

    }
}
