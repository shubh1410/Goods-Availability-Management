package com.example.godam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.godam.Utils.Item_new;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class createNewItem extends AppCompatActivity
{

    Button create_item;
    EditText pname,pbrand,quantity,pdesc,mno;
    AutoCompleteTextView cat_autocomplete;
    Spinner category;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public List<String> categories= new ArrayList<String>();
    private static final String TAG = "createNewItem";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        databaseReference.keepSynced(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);

        cat_autocomplete = (AutoCompleteTextView) findViewById(R.id.cat_autocomplete);
        create_item = (Button)findViewById(R.id.createItem);
        //category = (Spinner)findViewById(R.id.category);
        pname = (EditText)findViewById(R.id.pname);
        pbrand = (EditText)findViewById(R.id.pbrand);
        quantity = (EditText)findViewById(R.id.quantity);
        pdesc = (EditText)findViewById(R.id.pdesc);
        mno = (EditText)findViewById(R.id.mno);
        cat_autocomplete.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.e("TAG","Done pressed");
                }
                return false;
            }
        });
        cat_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                }
            }

        });


        databaseReference.child("Product_categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categories.clear();
                categories.add("none");
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String temp = areaSnapshot.getKey();
                    categories.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, categories);
        cat_autocomplete.setThreshold(1);
        cat_autocomplete.setAdapter(adapter);


        create_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String prod_category = cat_autocomplete.getText().toString().toLowerCase();
                String prod_pname    = pname.getText().toString().toLowerCase();
                String prod_pbrand   = pbrand.getText().toString().toLowerCase();
                String prod_quantity = quantity.getText().toString();
                String prod_desc     = pdesc.getText().toString();
                String prod_modelno  = mno.getText().toString();
                Log.d(TAG, "onClick: prod_category"+prod_category);
                
                if (!prod_category.isEmpty() && !prod_pname.isEmpty()    &&
                    !prod_pbrand.isEmpty()   && !prod_quantity.isEmpty() &&
                    !prod_desc.isEmpty()     && !prod_modelno.isEmpty())
                {
                    String key = databaseReference.child("Product_info").child(prod_category).child(prod_pbrand).push().getKey();
                    Item_new item = new Item_new(prod_category,prod_pname,prod_pbrand,prod_modelno,prod_desc,prod_quantity,key);

                    databaseReference.child("Product_info").child(prod_category).child(key).setValue(item);
                    databaseReference.child("Product_categories").child(prod_category).setValue(prod_category);
                    databaseReference.child("Product_brand").child(prod_category).child(prod_pbrand).setValue(prod_pbrand);
                    Toast.makeText(createNewItem.this, "Item added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(createNewItem.this, inventorymanagerview.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    //finish();

                    startActivity(new Intent(createNewItem.this, inventorymanagerview.class));
                }
                else
                {
                    Toast.makeText(createNewItem.this,"No Field can be EMPTY!!!", Toast.LENGTH_SHORT).show();
                }
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
            Intent intent = new Intent(createNewItem.this, MainActivity.class);
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

