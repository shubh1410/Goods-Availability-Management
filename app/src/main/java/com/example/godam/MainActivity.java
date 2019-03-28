package com.example.godam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login_button;
    EditText user,pass;
    TextView msg;

    private static final String TAG = "MainActivity";
    private final String uname="uname";
    private final String passtext="passtext";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button = (Button)findViewById(R.id.login);
        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        msg = (TextView)findViewById(R.id.errormsg);


        
        login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
                if(user.getText().toString().equals("admin") &&
                        user.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent intent=new  Intent(MainActivity.this,supervisor.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    //msg.setText("Wrong Credentials");
                    //msg.setVisibility(View.VISIBLE);


                }
            }

        });
        Log.d(TAG, "onCreate: out");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: in");
        outState.putString(uname,user.getText().toString());
        outState.putString(passtext,pass.getText().toString());
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: out");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: in");
        super.onRestoreInstanceState(savedInstanceState);
        user.setText(savedInstanceState.getString(uname));
        pass.setText(savedInstanceState.getString(passtext));
        Log.d(TAG, "onRestoreInstanceState: out");
    }
}
