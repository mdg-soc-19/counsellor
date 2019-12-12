package com.example.counsellor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*Intent intent = getIntent();
        String question1 = intent.getStringExtra("question asked");
        TextView textView = (TextView)findViewById(R.id.question);
        textView.setText(question1);*/


        String[] myDataset = { "question asked"};


        recyclerView = (RecyclerView) findViewById(R.id.the_wall);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ProgrammingAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);


        getSupportActionBar().setTitle("CounselloR");
        spinner = findViewById(R.id.spinner);
        List<String> categories = new ArrayList<>();
        categories.add(0, "CATEGORIES!!");
        categories.add("Competitive Coding");
        categories.add("TV Series");
        categories.add("Campus Groups");
        categories.add("Litreature");
        categories.add("Post Graduation");
        categories.add("Travelling");
        categories.add("Finance");
        categories.add("Research");
        categories.add("Placements/Internships");
        //Style the spinner

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("CATEGORIES!!")) {

                    //do nothing
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
                }


                if (parent.getItemAtPosition(position).equals("Competitive Coding")) {
                    Intent intent = new Intent(MainActivity.this, comp_coding.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("TV Series")) {
                    Intent intent = new Intent(MainActivity.this, tv_series.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Campus Groups")) {
                    Intent intent = new Intent(MainActivity.this, campus_groups.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Litreature")) {
                    Intent intent = new Intent(MainActivity.this, litr.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Post Graduation")) {
                    Intent intent = new Intent(MainActivity.this, pg_exams.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Travelling")) {
                    Intent intent = new Intent(MainActivity.this, travel.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Finance")) {
                    Intent intent = new Intent(MainActivity.this, finance.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Placements/Internships")) {
                    Intent intent = new Intent(MainActivity.this, PnI.class);
                    startActivity(intent);
                } else if (parent.getItemAtPosition(position).equals("Research")) {
                    Intent intent = new Intent(MainActivity.this, Research.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void onClickNotification(View view) {
        Intent intent = new Intent(this, notification.class);
        startActivity(intent);
    }

    public void onClickProfile(View view) {

        Intent intent = new Intent(this, Login_form.class);
        startActivity(intent);
    }

    public void onClickAsk(View view) {
        Intent intent = new Intent(this, ask.class);
        startActivity(intent);
    }


}
