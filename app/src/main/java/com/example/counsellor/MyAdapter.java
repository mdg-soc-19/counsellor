package com.example.counsellor;


import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.counsellor.BlogPost;
import com.example.counsellor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


    public List<BlogPost> blogList;
    private Context context;
    private String user_id;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    String blogPostId, currentUser;


    public MyAdapter(List<BlogPost> blog_list){
        this.blogList = blog_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {


            firebaseFirestore = FirebaseFirestore.getInstance();
            mAuth = FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser().getUid();
        }


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_item,parent,false);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final String blogPostId = blogList.get(position).BlogPostId;

//        holder.setIsRecyclable(false);


        String desc_text = blogList.get(position).getDesc();
        holder.setDescText(desc_text);

        String download_uri = blogList.get(position).getImage_url();
        String thumb_uri = blogList.get(position).getImage_thumb();
        holder.setImage(download_uri,thumb_uri);




        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            user_id = blogList.get(position).getUser_id();
            firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            String name = task.getResult().getString("name");
                            String image = task.getResult().getString("image");


                            holder.setUserImage(image);
                            holder.setUserName(name);

                        }
                    }
                }
            });

        }

        long milliseconds = blogList.get(position).getTimeStamp().getTime();
        //Using this for setting the date.

        String pattern = "E, dd MMM yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateString1 = simpleDateFormat.format(new Date(milliseconds));
        pattern = "HH:mm a";
        simpleDateFormat = new SimpleDateFormat(pattern);
        String dateString2 = simpleDateFormat.format(new Date(milliseconds));

        holder.setDate(dateString1+" at "+dateString2);




        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    try {
                        if (!documentSnapshots.isEmpty()) {
                            int count = documentSnapshots.size();
                            holder.setLikes(count);
                        } else {
                            holder.setLikes(0);
                        }
                    }
                    catch (NullPointerException E){

                    }
                }
            });
        }

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseFirestore.collection("Posts/" + blogPostId + "/Answers").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    try {
                        if (!documentSnapshots.isEmpty()) {
                            int countComments = documentSnapshots.size();
                            holder.setComments(countComments);
                        } else {
                            holder.setComments(0);
                        }
                    }
                    catch (NullPointerException E){

                    }
                }
            });
        }


        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUser).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                    try {
                        if (documentSnapshot.exists()) {
                            holder.BlogLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.image_like_red));
                        } else {
                            holder.BlogLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.image_like_gray));
                        }
                    }
                    catch (NullPointerException E){

                    }
                }
            });
        }


        holder.BlogLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                    firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (!task.getResult().exists()) {
                                Map<String, Object> likeMap = new HashMap<>();
                                likeMap.put("timeStamp", FieldValue.serverTimestamp());
                                firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUser).set(likeMap);
                            } else {

                                firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUser).delete();
                            }

                        }
                    });


                }
            }
        });

        holder.BlogCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent commentIntent = new Intent(context,Answers.class);
                commentIntent.putExtra("BlogPostId",blogPostId);
                context.startActivity(commentIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView descView;
        private TextView userName;
        private TextView postDate;
        private ImageView postImage;
        private CircleImageView userImage;
        private View mview;
        private TextView blogLikeCount;
        private TextView blogCommentCount;
        private ImageView BlogLikeBtn;
        private ImageView BlogCommentBtn;



        public ViewHolder(View itemView) {
            super(itemView);
            mview = itemView;

            BlogLikeBtn = mview.findViewById(R.id.blog_like);
            blogCommentCount = mview.findViewById(R.id.blog_comment_count);
            BlogCommentBtn = mview.findViewById(R.id.blog_comment);
            blogLikeCount = mview.findViewById(R.id.blog_like_count);
            userName = mview.findViewById(R.id.commentUsername);
            postDate = mview.findViewById(R.id.blogDate);
            postImage = mview.findViewById(R.id.blogImage);
            userImage = mview.findViewById(R.id.commentProfilePic);

        }

        public void setDescText(String descText){

            descView = mview.findViewById(R.id.blogDescription);
            descView.setText(descText);

        }

        public void setUserName(String userNameText){


            userName.setText(userNameText);

        }

        public void setDate(String dateOfPost){


            postDate.setText(dateOfPost);

        }

        public void setImage(String downloadURL, String thumb_url){


            Glide.with(context).load(downloadURL).thumbnail(Glide.with(context).load(thumb_url)).into(postImage);
        }

        public void setUserImage(String userImage_URL){


            Glide.with(context).load(userImage_URL).into(userImage);

        }

        private void setLikes(int count){
            String text = count + " Likes";

            blogLikeCount.setText(text);
        }

        private void setComments(int countC){
            String textC = countC + " Answers";

            blogCommentCount.setText(textC);
        }
    }

}
