package com.example.godam;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update_Quantity extends AppCompatActivity {

    Button add_quantity_btn, sub_quantity_btn;
    EditText upqname, upqbrand, upquantity, upqdesc, upqmno, up_new_quantity;
    Spinner upqcategory;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static final String TAG = "Update_Quantity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        databaseReference.keepSynced(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__quantity);

        Intent intent = getIntent();
        final Item_new selected_data = (Item_new) getIntent().getParcelableExtra("parcel_data");

        add_quantity_btn = (Button) findViewById(R.id.Add_quantity_btn);
        sub_quantity_btn = (Button) findViewById(R.id.sub_quantity_btn);
        upqcategory = (Spinner) findViewById(R.id.upqcategory);
        upqname = (EditText) findViewById(R.id.upqname);
        upqbrand = (EditText) findViewById(R.id.upqbrand);
        upquantity = (EditText) findViewById(R.id.upquantity);
        upqdesc = (EditText) findViewById(R.id.upqdesc);
        upqmno = (EditText) findViewById(R.id.upqmno);
        up_new_quantity = (EditText) findViewById(R.id.up_new_quantity);


        upqname.setText(selected_data.getProduct_name());
        upqbrand.setText(selected_data.getProduct_brand());
        upquantity.setText(selected_data.getProduct_quantity());
        upqdesc.setText(selected_data.getProduct_desc());
        upqmno.setText(selected_data.getModel_number());

        upqname.setEnabled(false);
        upqbrand.setEnabled(false);
        upquantity.setEnabled(false);
        upqdesc.setEnabled(false);
        upqmno.setEnabled(false);
        upqcategory.setEnabled(false);

        add_quantity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String entered_quantit = up_new_quantity.getText().toString();
                if (!entered_quantit.isEmpty()) {
                    int new_quantity = (Integer.parseInt(up_new_quantity.getText().toString())) + (Integer.parseInt(selected_data.getProduct_quantity()));


                    selected_data.setProduct_quantity(Integer.toString(new_quantity));
                    String key = selected_data.getFb_key();
                    databaseReference.child("Product_info").child(selected_data.getProduct_category()).child(key).setValue(selected_data);
                    databaseReference.child("Product_categories").child(selected_data.getProduct_category()).setValue(selected_data.getProduct_category());
                    databaseReference.child("Product_brand").child(selected_data.getProduct_category()).child(selected_data.getProduct_brand()).setValue(selected_data.getProduct_brand());
                    Toast.makeText(Update_Quantity.this, "Quantity Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Update_Quantity.this, viewInventory.class);
                    intent.putExtra("user_type", "update_quantity");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    //finish();

                } else {
                    Toast.makeText(Update_Quantity.this, "Enter Valid Quantity", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sub_quantity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String entered_quantit = up_new_quantity.getText().toString();
                if (!entered_quantit.isEmpty()) {
                    int new_quantity = (Integer.parseInt(selected_data.getProduct_quantity())) - (Integer.parseInt(up_new_quantity.getText().toString()));

                    if (new_quantity < 0) {
                        Toast.makeText(Update_Quantity.this, "Enter Valid Quantity", Toast.LENGTH_SHORT).show();
                    } else {
                        selected_data.setProduct_quantity(Integer.toString(new_quantity));
                        String key = selected_data.getFb_key();
                        databaseReference.child("Product_info").child(selected_data.getProduct_category()).child(key).setValue(selected_data);
                        databaseReference.child("Product_categories").child(selected_data.getProduct_category()).setValue(selected_data.getProduct_category());
                        databaseReference.child("Product_brand").child(selected_data.getProduct_category()).child(selected_data.getProduct_brand()).setValue(selected_data.getProduct_brand());
                        Toast.makeText(Update_Quantity.this, "Quantity subtracted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Update_Quantity.this, viewInventory.class);
                        intent.putExtra("user_type", "update_quantity");
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        //finish();
                    }
                } else {
                    Toast.makeText(Update_Quantity.this, "Enter Valid Quantity", Toast.LENGTH_SHORT).show();
                }

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
            Intent intent = new Intent(Update_Quantity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
