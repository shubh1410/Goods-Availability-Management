package com.example.godam;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.godam.Utils.Item_new;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class delete_item extends AppCompatActivity {

    Button delete_item_btn, cancel_del_btn;
    EditText delname, delbrand, delquantity, deldesc, delmno;
    Spinner delcategory;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
     Item_new selected_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        databaseReference.keepSynced(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);


        Intent intent = getIntent();
        selected_data = (Item_new) getIntent().getParcelableExtra("parcel_data");

        delete_item_btn = (Button) findViewById(R.id.delete_item_btn);
        cancel_del_btn = (Button) findViewById(R.id.cancel_del_btn);
        delcategory = (Spinner) findViewById(R.id.delcategory);
        delname = (EditText) findViewById(R.id.delname);
        delbrand = (EditText) findViewById(R.id.delbrand);
        delquantity = (EditText) findViewById(R.id.delquantity);
        deldesc = (EditText) findViewById(R.id.deldesc);
        delmno = (EditText) findViewById(R.id.delmno);


        delname.setText(selected_data.getProduct_name());
        delbrand.setText(selected_data.getProduct_brand());
        delquantity.setText(selected_data.getProduct_quantity());
        deldesc.setText(selected_data.getProduct_desc());
        delmno.setText(selected_data.getModel_number());

        delname.setEnabled(false);
        delbrand.setEnabled(false);
        delquantity.setEnabled(false);
        deldesc.setEnabled(false);
        delmno.setEnabled(false);


        delete_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(delete_item.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want Delete Item?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                delete_from_firebase();
                                Toast.makeText(delete_item.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(delete_item.this, viewInventory.class);
                                intent.putExtra("user_type", "delete_product");

                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        cancel_del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(delete_item.this, "Cancel", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(delete_item.this, viewInventory.class);
                intent.putExtra("user_type", "delete_product");

                startActivity(intent);

            }
        });
    }

    /*For Menu Button in Action Bar*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.others_menu, menu);
        return true;
    }

    /*For Menu Button in Action Bar*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logOut) {
//            mAuth.signOut();
            Intent intent = new Intent(delete_item.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }



    public void delete_from_firebase()
    {
        String key = selected_data.getFb_key();
        databaseReference.child("Product_info").child(selected_data.getProduct_category()).child(key).removeValue();

        databaseReference.child("Product_info").child(selected_data.getProduct_category()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean flag_brand =true;
                boolean flag_cat =true;
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    Item_new temp=areaSnapshot.getValue(Item_new.class);
                    if(temp.getProduct_brand().equals(selected_data.getProduct_brand()))
                    {
                        flag_brand=false;

                    }
                    if(temp.getProduct_category().equals(selected_data.getProduct_category()))
                    {
                        flag_cat=false;
                    }
                }

                if(flag_brand)
                {
                    databaseReference.child("Product_brand").child(selected_data.getProduct_category()).child(selected_data.getProduct_brand()).removeValue();
                }
                if(flag_cat){
                    databaseReference.child("Product_categories").child(selected_data.getProduct_category()).removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
