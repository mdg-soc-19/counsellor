package com.example.counsellor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class campus_groups extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<PostItems> postItemsList;
    PostItems mPostItems;
    ProgressDialog progressDialog;
    Toolbar myToolbar;
    MenuItem item;
    String question;
    DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("CounselloR - Campus Groups!!");





        recyclerView = (RecyclerView) findViewById(R.id.the_wall);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(campus_groups.this, 1);
        gridLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Building 'THE WALL' .....");


        postItemsList = new ArrayList<>();

        final DatabaseReference databaseReference;
        final MyAdapter myAdapter = new MyAdapter(campus_groups.this,postItemsList);
        recyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");

        progressDialog.show();
        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postItemsList.clear();


                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    PostItems postItems = itemSnapshot.getValue(PostItems.class);
                    postItemsList.add(postItems);
                    question = (String) itemSnapshot.child("itemQuestion").getValue();
                    //question = FirebaseDatabase.getInstance().getReference("Questions/itemQuestion").toString();

                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


        //postItemsList.clear();



    }



    public void onClickProfile(View view) {

        Intent intent = new Intent(this, Login_form.class);
        startActivity(intent);
    }

    public void onClickAsk(View view) {
        Intent intent = new Intent(this, ask.class);
        startActivity(intent);
    }

    /*public void onClickAnswer(View view){
        Intent intent = new Intent(this, answer.class);
        intent.putExtra("question", question);
        startActivity(intent);
    }

        public void onClickViewAnswer(View view){
        Intent intent = new Intent(this, answer.class);
        intent.putExtra("question", question);
        startActivity(intent);
    }*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.user_profile:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...

                Intent intent2 = new Intent(this, Login_form.class);
                startActivity(intent2);
                return true;

            case R.id.Competitive_Coding:
                Intent intent3 = new Intent(campus_groups.this, comp_coding.class);
                startActivity(intent3);
                return true;
            case R.id.tv:
                Intent intent4 = new Intent(campus_groups.this, tv_series.class);
                startActivity(intent4);
                return true;
            case R.id.CampGrp:
                Toast.makeText(this, "You are already on this category!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.litr:
                Intent intent6 = new Intent(campus_groups.this, litr.class);
                startActivity(intent6);
                return true;
            case R.id.pg:
                Intent intent7 = new Intent(campus_groups.this, pg_exams.class);
                startActivity(intent7);
                return true;
            case R.id.travel:
                Intent intent8 = new Intent(campus_groups.this, travel.class);
                startActivity(intent8);
                return true;
            case R.id.fin:
                Intent intent9 = new Intent(campus_groups.this, finance.class);
                startActivity(intent9);
                return true;
            case R.id.research:
                Intent intent10 = new Intent(campus_groups.this, Research.class);
                startActivity(intent10);
                return true;
            case R.id.pni:
                Intent intent11 = new Intent(campus_groups.this, PnI.class);
                startActivity(intent11);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this, "Not Valid Choice!", Toast.LENGTH_SHORT).show();
                return  true;

        }
        return false;
    }


}