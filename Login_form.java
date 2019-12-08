package com.example.counsellor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
    }

    public void onClickRegister(View view){
        Intent intent = new Intent(Login_form.this, Signup.class);
        startActivity(intent);
    }
}
