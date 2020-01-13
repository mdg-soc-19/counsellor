package com.example.counsellor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private Context mContext;
    private List<PostItems> postItemsList;


    public MyAdapter(Context mContext, List<PostItems> postItemsList) {
        this.mContext = mContext;
        this.postItemsList = postItemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);

        return new MyViewHolder(mView);


    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {


        holder.imageView.setImageResource(postItemsList.get(position).getItemImage());
        holder.mQuestion.setText(postItemsList.get(position).getItemQuestion());
        holder.btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, answer.class);
                intent.putExtra("question", postItemsList.get(holder.getAdapterPosition()).getItemQuestion());
                mContext.startActivity(intent);
            }
        });
        holder.btn_viewAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, answer.class);
                intent.putExtra("question", postItemsList.get(holder.getAdapterPosition()).getItemQuestion());
                mContext.startActivity(intent);
            }

        });



    }
    @Override
    public int getItemCount() {
        return postItemsList.size();
    }


}
class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mQuestion;
    Button btn_answer, btn_viewAns;
    CardView mCardView;


    public MyViewHolder( View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.user_img);
        mQuestion = itemView.findViewById(R.id.question);
        btn_answer = itemView.findViewById(R.id.answer);
        btn_viewAns = itemView.findViewById(R.id.view_answer);
        mCardView = itemView.findViewById((R.id.card_View));


    }
}