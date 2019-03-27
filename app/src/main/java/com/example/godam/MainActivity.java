package com.example.godam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login_button;
    EditText user,pass;
    TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }
}
