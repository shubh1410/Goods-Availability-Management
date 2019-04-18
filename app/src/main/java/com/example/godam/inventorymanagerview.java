package com.example.godam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Menu;

public class inventorymanagerview extends AppCompatActivity
{
    Button nitem,view_inventory_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventorymanagerview);
        nitem = (Button) findViewById(R.id.createNewItem);
        view_inventory_button=(Button) findViewById(R.id.viewInventory);

        nitem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, createNewItem.class);
                startActivity(intent);
                Toast.makeText(inventorymanagerview.this, "Create New Item", Toast.LENGTH_SHORT).show();
            }

        });

        view_inventory_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, viewInventory.class);
                intent.putExtra("user_type","InventoryManager");
                startActivity(intent);
                Toast.makeText(inventorymanagerview.this, "view Inventory", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(inventorymanagerview.this, MainActivity.class);
            startActivity(intent);
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}