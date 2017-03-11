package com.example.pc.newapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecomandActivity extends AppCompatActivity {

    ArrayList<String> options ;
    String [] convert;
    private String mood ;
    private DataBaseHelper  db ;
    private TextView textMood ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomand);
        textMood =(TextView) findViewById(R.id.textViewMood);
        // pass name
        Bundle extras = getIntent().getExtras();
        String name_Passed = extras.getString("name");
        mood =extras.getString("number");
        // return from database
        db= new DataBaseHelper(this);
        String []s =db.getUser_Name(name_Passed);
        convert =new String [4];
        for(int i=0 ; i< s.length ;i++){
            convert[i] =s[i];
        }
        // determined activities
        options =new ArrayList<String>();
        if(mood.equals("1")){// stessed or inability to focus
            textMood.setText("You are Stressed");
            if(convert[2] != null && !convert[2].isEmpty()) {//musical hobby
                options.add(convert[2].toString());
            }else{
                options.add("Play Soft Music To Relax");
            }
            if(convert[3] != null && !convert[3].isEmpty()){// reading hobby
                options.add(convert[3].toString());
            }
            options.add("Break 5 Min minutes");
            options.add("Listen to Motivated Video");
        }else if(mood.equals("2")){//Conscious focus
            textMood.setText("You are Focued");
            options.add("Study your Subjects");
            options.add("Do your Assigments");
            options.add("Develop your Carear");
            options.add("Learn New Technology");
        }else if (mood.equals("3")){//depression
            textMood.setText("You are Depression ");
            if(convert[0] != null && !convert[0].isEmpty()){//sports hobby
                options.add(convert[0].toString());
            }
            options.add("Go to Space or picnic");
            options.add("Chat With Firends");
        }else if (mood.equals("4")){//relaxation
            textMood.setText("You are Relaxed");
            options.add("Go To Sleep");
        }
        // show on listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, options);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
