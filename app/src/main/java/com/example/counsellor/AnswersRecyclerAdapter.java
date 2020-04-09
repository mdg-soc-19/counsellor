package com.example.counsellor;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AnswersRecyclerAdapter extends RecyclerView.Adapter<AnswersRecyclerAdapter.ViewHolder> {

    public List<AnswersModelClass> commentList;
    private Context context;
    private String user_id;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    String currentUser;

    public AnswersRecyclerAdapter(List<AnswersModelClass> commentList){
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        firebaseFirestore =FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser().getUid();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item,parent,false);
        context = parent.getContext();


        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        String CommentMessage = commentList.get(position).getMessage();
        String user_id_comment = commentList.get(position).getUser_id();
        holder.setCommentMessage(CommentMessage);

        firebaseFirestore.collection("Users").document(user_id_comment).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){

                        String userPhoto = task.getResult().getString("image");
                        String userName = task.getResult().getString("name");

                        holder.setNamePhoto(userPhoto,userName);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View mview;
        private ImageView profilePic;
        private TextView userName;
        private TextView commentMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
            profilePic = mview.findViewById(R.id.commentProfilePic);
            userName = mview.findViewById(R.id.commentUsername);
            commentMessage = mview.findViewById(R.id.commentTextMessage);

        }

        private void setCommentMessage(String message){
            commentMessage.setText(message);
        }

        private void setNamePhoto(String pic, String name){

            Glide.with(context).load(pic).into(profilePic);
            userName.setText(name);

        }


    }
}