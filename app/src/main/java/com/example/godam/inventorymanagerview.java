package com.example.godam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class inventorymanagerview extends AppCompatActivity
{
    Button nitem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventorymanagerview);
        nitem = (Button) findViewById(R.id.createNewItem);

        nitem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, createNewItem.class);
                startActivity(intent);
                Toast.makeText(inventorymanagerview.this, "Create New Item", Toast.LENGTH_SHORT).show();
            }

        });

    }
}