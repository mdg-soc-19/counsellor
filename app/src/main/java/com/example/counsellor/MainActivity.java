package com.example.counsellor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<PostItems> postItemsList;
    PostItems mPostItems;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = (RecyclerView) findViewById(R.id.the_wall);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Building 'THE WALL' .....");


        postItemsList = new ArrayList<>();

        final DatabaseReference databaseReference;
        ValueEventListener eventListener;
        final MyAdapter myAdapter = new MyAdapter(MainActivity.this,postItemsList);
        recyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");

        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postItemsList.clear();


                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    PostItems postItems = itemSnapshot.getValue(PostItems.class);

                    postItemsList.add(postItems);

                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
            }
        });

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

    public void onClickAnswer(View view){
        Intent intent = new Intent(this, answer.class);
        startActivity(intent);
    }

    public void onClickViewAnswer(View view){
        Intent intent = new Intent(this, answer.class);
        startActivity(intent);
    }


}
