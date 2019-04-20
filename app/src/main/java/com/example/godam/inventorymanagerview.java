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
    Button nitem,view_inventory_button,update_quantity_button,delete_product_button,update_item_info_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventorymanagerview);
        nitem = (Button) findViewById(R.id.createNewItem);
        view_inventory_button=(Button) findViewById(R.id.viewInventory);
        update_quantity_button=(Button) findViewById(R.id.update_quantity_button);
        delete_product_button=(Button) findViewById(R.id.delete_product_button);
        update_item_info_btn=(Button)findViewById(R.id.update_item_info_btn);

        nitem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, createNewItem.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                Toast.makeText(inventorymanagerview.this, "Create New Item", Toast.LENGTH_SHORT).show();
                //finish();
            }

        });

        update_item_info_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, viewInventory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user_type","update_item_info_btn");
                startActivity(intent);
                Toast.makeText(inventorymanagerview.this, "update_item_info_btn", Toast.LENGTH_SHORT).show();
                //finish();
            }

        });

        update_quantity_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, viewInventory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user_type","update_quantity");
                startActivity(intent);
                Toast.makeText(inventorymanagerview.this, "update_quantity", Toast.LENGTH_SHORT).show();
                //finish();
            }

        });

        delete_product_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, viewInventory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user_type","delete_product");
                startActivity(intent);
                Toast.makeText(inventorymanagerview.this, "delete_product", Toast.LENGTH_SHORT).show();
                //finish();
            }

        });

        view_inventory_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventorymanagerview.this, viewInventory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user_type","view_inventory_button");
                startActivity(intent);
                Toast.makeText(inventorymanagerview.this, "view Inventory", Toast.LENGTH_SHORT).show();
                //finish();
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}