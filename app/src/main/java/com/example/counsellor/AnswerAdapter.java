package com.example.counsellor;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnsViewHolder> {

    private Context context;
    private List<PostItems.ChildLevel2> answerItemList;

    public AnswerAdapter(Context context, List<PostItems.ChildLevel2> answerItemList){
        this.context = context;
        this.answerItemList = answerItemList;
    }

    @Override
    public AnsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item,parent,false);
        return new AnsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnsViewHolder holder, int position) {
        holder.txtAns.setText(answerItemList.get(position).getAnswer());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detailedAns.class);
                intent.putExtra("answer", answerItemList.get(holder.getAdapterPosition()).getAnswer());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return answerItemList.size();
    }
}

class  AnsViewHolder extends RecyclerView.ViewHolder{

    TextView txtAns, txtName;
    ImageView userImg;
    CardView mCardView;


    public AnsViewHolder(@NonNull View itemView) {
        super(itemView);

        txtAns = itemView.findViewById(R.id.userAnswer);
        txtName = itemView.findViewById(R.id.naam);
        userImg = itemView.findViewById(R.id.user_img);
        mCardView = itemView.findViewById(R.id.cardView);

    }
}
