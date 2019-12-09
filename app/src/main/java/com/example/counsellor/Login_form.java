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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_form extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btn_signin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);


        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        btn_signin = (Button) findViewById(R.id.signIn);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login_form.this, "Enter a valid Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login_form.this, "Enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Login_form.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                } else {

                                    Toast.makeText(Login_form.this, "SignIn Unsuccessful", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });


    }

    public void onClickRegister(View view){
        Intent intent = new Intent(Login_form.this, Signup.class);
        startActivity(intent);
    }
}
