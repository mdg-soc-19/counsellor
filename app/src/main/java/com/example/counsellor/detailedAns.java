package com.example.counsellor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detailedAns extends AppCompatActivity {

    TextView detailedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_ans);

        detailedAnswer = (TextView) findViewById(R.id.detailedAns);
        Intent intent = getIntent();
        String answer = intent.getStringExtra("answer");
        detailedAnswer.setText(answer);

    }
}
