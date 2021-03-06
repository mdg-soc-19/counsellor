package com.example.counsellor;


import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Answers extends AppCompatActivity {

    private Toolbar toolbarNewPost;
    private String BlogPostId;
    private EditText commentsText;
    private ImageView commentSubmit;
    private RecyclerView comment_list_view;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;
    public List<AnswersModelClass> commentList;
    private AnswersRecyclerAdapter commentsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        BlogPostId = getIntent().getStringExtra("BlogPostId");


        toolbarNewPost = findViewById(R.id.toolbarAnswers);
        setSupportActionBar(toolbarNewPost);
        getSupportActionBar().setTitle("Answers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        commentsText = findViewById(R.id.comment_text);
        commentSubmit = findViewById(R.id.commentSubmit);

        commentsText.requestFocus();



        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        comment_list_view = findViewById(R.id.recycler_view_comments);
        commentList = new ArrayList<>();
        commentsRecyclerAdapter = new AnswersRecyclerAdapter(commentList);

        comment_list_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        comment_list_view.setAdapter(commentsRecyclerAdapter);

        Query mainQuery = firebaseFirestore.collection("Posts/" + BlogPostId + "/Answers")
                .orderBy("timeStamp", Query.Direction.ASCENDING);

        mainQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {


                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        AnswersModelClass commentData = doc.getDocument().toObject(AnswersModelClass.class);
                        commentList.add(commentData);
                        commentsRecyclerAdapter.notifyDataSetChanged();
                    }
                }


            }
        });




        commentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String comment = commentsText.getText().toString();

                if(!comment.isEmpty()){

                    Map<String,Object> commentMap = new HashMap<>();
                    commentMap.put("Message",comment);
                    commentMap.put("user_id",mAuth.getCurrentUser().getUid());
                    commentMap.put("timeStamp", FieldValue.serverTimestamp());

                    firebaseFirestore.collection("Posts/" + BlogPostId + "/Answers").add(commentMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if(task.isSuccessful()){
                                commentsText.setText(null);
                            }

                        }
                    });

                }
            }
        });

    }
}