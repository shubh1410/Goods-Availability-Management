package com.example.godam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class supervisor extends AppCompatActivity
{
    Button cuser;
    private FirebaseAuth mAuth;

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
    /*For Menu Button in Action Bar*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.others_menu,menu);
        return true;
    }
    /*For Menu Button in Action Bar*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logOut)
        {
//            mAuth.signOut();
            Intent intent = new Intent(supervisor.this, MainActivity.class);
            startActivity(intent);
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
