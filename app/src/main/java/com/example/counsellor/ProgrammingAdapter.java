package com.example.counsellor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private String[] data;


    public ProgrammingAdapter(String[] myDataset) {

        data = myDataset;
    }


    @NonNull
    @Override
    public ProgrammingAdapter.ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {


        String title = data[position];
        holder.question.setText(data[position]);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        ImageView user_img;
        TextView question;
        Button view_answer;
        Button answer;

        public ProgrammingViewHolder(View itemView) {
            super(itemView);
            user_img = (ImageView) itemView.findViewById(R.id.user_img);
            question = (TextView) itemView.findViewById(R.id.question);
            view_answer = (Button) itemView.findViewById(R.id.view_answer);
            answer = (Button) itemView.findViewById(R.id.answer);
        }
    }
}
