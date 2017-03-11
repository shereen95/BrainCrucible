package com.example.pc.newapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private EditText sport;
    private EditText arts;
    private EditText music;
    private EditText read;
    private Button signIn ;
    private DataBaseHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DataBaseHelper(this);
        //editText
        name =(EditText)findViewById(R.id.editTextName);
        pass =(EditText)findViewById(R.id.editTextpass);
        sport =(EditText)findViewById(R.id.editTextSports);
        arts =(EditText)findViewById(R.id.editTextArts);
        music =(EditText)findViewById(R.id.editTextMusic);
        read =(EditText)findViewById(R.id.editTextRead);
        //button
        signIn =(Button) findViewById(R.id.buttonSignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go to activity
                if(db.checkUser_Name(name.getText().toString())){
                    displayeToast("The UserName is already exit");
                }else {
                    register();
                    Intent toy = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(toy);
                    finish();
                    }
            }
        });
    }

    private void  register (){
        String stu_name =name.getText().toString();
        String stu_pass =pass.getText().toString();
        String stu_sport =sport.getText().toString();
        String stu_arts =arts.getText().toString();
        String stu_music =music.getText().toString();
        String stu_read =read.getText().toString();

        if(stu_name.isEmpty() && stu_pass.isEmpty()){
            displayeToast("UserName/Password is empty");
        }else{
            db.addUser(stu_name,stu_pass,stu_sport,stu_arts,stu_music,stu_read);
            displayeToast("successfully Login");
        }
    }

    private void displayeToast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
