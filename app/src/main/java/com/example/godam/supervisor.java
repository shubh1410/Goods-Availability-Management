package com.example.godam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class supervisor extends AppCompatActivity
{
    Button cuser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);
        cuser = (Button)findViewById(R.id.newuser);

        cuser.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(supervisor.this, newUser.class);
                startActivity(intent);
                Toast.makeText(supervisor.this, "Create New User", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
