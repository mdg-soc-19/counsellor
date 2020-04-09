package com.example.counsellor;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.counsellor.MainActivity;
import com.example.counsellor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText loginEmailText,loginPassText;
    private Button loginButton;
    private TextView loginRegButton;
    private FirebaseAuth mAuth;
    private ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loginEmailText = findViewById(R.id.passReg);
        loginPassText = findViewById(R.id.passConReg);
        loginButton = findViewById(R.id.regB);
        loginRegButton=findViewById(R.id.createNewB);
        loginProgressBar = findViewById(R.id.progressLogin);

        loginRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(Login.this,register.class);
                startActivity(reg);
                finish();
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On clicking LOGIN login the user
                String email = loginEmailText.getText().toString().trim();
                String pasword= loginPassText.getText().toString().trim();
                if(!TextUtils.isEmpty(email)  && !TextUtils.isEmpty(pasword)){
                    loginProgressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email,pasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this,"Welcome",Toast.LENGTH_LONG).show();
                                sendToMain();
                            }
                            else{
                                String error = task.getException().getMessage();
                                Toast.makeText(Login.this,error,Toast.LENGTH_LONG).show();
                            }
                            loginProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                else{
                    String errorF = "Please fill all details";
                    Toast.makeText(getApplicationContext(),errorF,Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser curUser = mAuth.getCurrentUser();
        if(curUser != null){
            sendToMain();
        }

    }

    private void sendToMain() {
        Intent main = new Intent(Login.this, MainActivity.class);
        startActivity(main);
    }
}