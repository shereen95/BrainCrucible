package com.example.pc.newapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private Button signIn;
    private Button signUp;
    private DataBaseHelper db ;
    final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    String message = "please put head set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DataBaseHelper(this);
        // intiait editText
        name =(EditText) findViewById(R.id.name);
        pass =(EditText) findViewById(R.id.editTextpass);
        // intiait button
        signIn = (Button)findViewById(R.id.buttonLogin);
        signUp =(Button)findViewById(R.id.buttonSignUP);
       //signInAction
       signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // go to activity
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login(){
        String stu_name =name.getText().toString();
        String stu_pass =pass.getText().toString();

        if(db.getUser(stu_name,stu_pass)){
            Intent toy = new Intent(MainActivity.this,MoodActivity.class);
            toy.putExtra("name",stu_name);
            startActivity(toy);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Wrong Name/Password",Toast.LENGTH_SHORT).show();
        }
    }
}


