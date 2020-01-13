package com.example.counsellor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class comp_coding extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<PostItems> postItemsList;
    PostItems mPostItems;
    ProgressDialog progressDialog;
    Toolbar myToolbar;
    MenuItem item;
    String question, type;
    TextView heading, testing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("CounselloR - Competitive Coding!!");


        heading = (TextView) findViewById(R.id.h1);
        testing = (TextView) findViewById(R.id.testing);
        recyclerView = (RecyclerView) findViewById(R.id.the_wall);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(comp_coding.this, 1);
        gridLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Building 'THE WALL' .....");


        postItemsList = new ArrayList<>();

        final DatabaseReference databaseReference;
        final MyAdapter myAdapter = new MyAdapter(comp_coding.this, postItemsList);
        recyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");


       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    PostItems postItems = itemSnapshot.getValue(PostItems.class);
                  //  type = (String) itemSnapshot.child("category").getValue();
                 //   testing.setText(type);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/

       // if (type.equals("comp")) {
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
        //}


        /*else{
            Toast.makeText(this, "did not work!", Toast.LENGTH_SHORT).show();
        }*/

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
                Toast.makeText(this, "You are already on this category!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv:
                Intent intent4 = new Intent(comp_coding.this, tv_series.class);
                startActivity(intent4);
                return true;
            case R.id.CampGrp:
                Intent intent5 = new Intent(comp_coding.this, campus_groups.class);
                startActivity(intent5);
                return true;
            case R.id.litr:
                Intent intent6 = new Intent(comp_coding.this, litr.class);
                startActivity(intent6);
                return true;
            case R.id.pg:
                Intent intent7 = new Intent(comp_coding.this, pg_exams.class);
                startActivity(intent7);
                return true;
            case R.id.travel:
                Intent intent8 = new Intent(comp_coding.this, travel.class);
                startActivity(intent8);
                return true;
            case R.id.fin:
                Intent intent9 = new Intent(comp_coding.this, finance.class);
                startActivity(intent9);
                return true;
            case R.id.research:
                Intent intent10 = new Intent(comp_coding.this, Research.class);
                startActivity(intent10);
                return true;
            case R.id.pni:
                Intent intent11 = new Intent(comp_coding.this, PnI.class);
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