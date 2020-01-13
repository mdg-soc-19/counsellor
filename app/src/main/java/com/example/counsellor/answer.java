package com.example.counsellor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class answer extends AppCompatActivity {

    TextView questionImported;
    DatabaseReference ref;
    private RecyclerView recyclerView;
    EditText enterAns;
    List<PostItems.ChildLevel2> answerItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        questionImported = (TextView) findViewById(R.id.txtQuestion);
        Intent intent = getIntent();
        String question = intent.getStringExtra("question");
        questionImported.setText(question);



        recyclerView = (RecyclerView) findViewById(R.id.answers);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(answer.this, 1);
        gridLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        answerItemList = new ArrayList<>();
        final DatabaseReference databaseReference;
        final AnswerAdapter myAdapter = new AnswerAdapter(answer.this,answerItemList);
        recyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Answers");

        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                answerItemList.clear();


                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    PostItems.ChildLevel2 answerItems = itemSnapshot.getValue(PostItems.ChildLevel2.class);
                    answerItemList.add(answerItems);

                }

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //do nothing
            }
        });
    }

    public void uploadAnswer(){
        enterAns = (EditText) findViewById(R.id.enterAns);
        String answer = enterAns.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Answer Uploading");
        progressDialog.show();
        PostItems.ChildLevel2 answerItem = new PostItems.ChildLevel2(
                enterAns.getText().toString()
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Answers")
                .child(myCurrentDateTime).setValue(answerItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(answer.this, "Answer Uploaded", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
                else{
                    Toast.makeText(answer.this, "Sorry! The answer could not be uploaded", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
            }
        });
    }





    public void onClickSubmit(View view) {
        uploadAnswer();
    }
}
