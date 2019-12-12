package com.example.counsellor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ask extends AppCompatActivity {

    RadioButton comp, tv, campus, litr, pni, pg, travel, fin, research;
    EditText txtAsk;
    Button btn_ask;
    String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        comp = (RadioButton) findViewById(R.id.rdCompCode);
        tv = (RadioButton) findViewById(R.id.rdTV);
        campus = (RadioButton) findViewById(R.id.rdCamp);
        litr = (RadioButton) findViewById(R.id.rdLitr);
        pni = (RadioButton) findViewById(R.id.rdPnI);
        pg = (RadioButton) findViewById(R.id.rdPG);
        travel = (RadioButton) findViewById(R.id.rdTravel);
        fin = (RadioButton) findViewById(R.id.rdFinance);
        research = (RadioButton) findViewById(R.id.rdResearch);
        txtAsk = (EditText) findViewById(R.id.txtAsk);
        btn_ask = (Button) findViewById(R.id.ask);
        btn_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               question = txtAsk.getText().toString();
               Intent intent = new Intent(ask.this, MainActivity.class);
               intent.putExtra("question asked", question);
               startActivity(intent);

               if(comp.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent1 = new Intent(ask.this, comp_coding.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(tv.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent2 = new Intent(ask.this, tv_series.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(campus.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent3 = new Intent(ask.this, campus_groups.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(litr.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent4 = new Intent(ask.this, litr.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(pni.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent5 = new Intent(ask.this, PnI.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(pg.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent6 = new Intent(ask.this, pg_exams.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(travel.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent7 = new Intent(ask.this, travel.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(fin.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent8 = new Intent(ask.this, finance.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
               if(research.isChecked()){
                   question = txtAsk.getText().toString();
                   Intent intent9 = new Intent(ask.this, Research.class);
                   intent.putExtra("question asked", question);
                   startActivity(intent);
               }
            }
        });


    }

}
