package com.example.counsellor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class UserInfo extends AppCompatActivity {


    TextView name, email, text1, text2;
    ImageView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        name = (TextView) findViewById(R.id.UserName);
        email = (TextView) findViewById(R.id.Email);
        text1 = (TextView) findViewById(R.id.randomText);
        user = (ImageView) findViewById(R.id.anotherImage);
        text2 = (TextView) findViewById(R.id.welcome);
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
    }
}
