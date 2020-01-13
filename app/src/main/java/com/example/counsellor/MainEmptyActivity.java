package com.example.counsellor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.util.Util;

public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent activityIntent;

        // go straight to main if a token is stored

            activityIntent = new Intent(this, Signup.class);


        startActivity(activityIntent);
        finish();
    }
}
