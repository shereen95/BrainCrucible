package com.example.pc.newapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MoodActivity extends AppCompatActivity {

    private EditText num ;
    private Button ok ;
    private String name_Pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        ok =(Button) findViewById(R.id.buttonok);
        num =(EditText) findViewById(R.id.editTextNumber);


        Bundle extras = getIntent().getExtras();
        name_Pass =  extras.getString("name");

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent toy1= new Intent(MoodActivity.this,RecomandActivity.class);
                String number=number =num.getText().toString();
                toy1.putExtra("number",number);
                toy1.putExtra("name",name_Pass);
                startActivity(toy1);
                finish();

            }
        });








    }
}
