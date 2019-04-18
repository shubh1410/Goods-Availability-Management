package com.example.godam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewInventory extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        final Spinner pCat = (Spinner) findViewById(R.id.pcategory);
        final Spinner pBrandSpinner = (Spinner) findViewById(R.id.pBrand);

        databaseReference.child("Product_categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                pBrandSpinner.setEnabled(false);
                List<String> categories = new ArrayList<String>();
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren())
                {
                    String temp = areaSnapshot.getKey();
                    categories.add(temp);
                }

                ArrayAdapter<String> pCatAdapter = new ArrayAdapter<String>(viewInventory.this, android.R.layout.simple_spinner_item, categories);
                pCatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                pCat.setAdapter(pCatAdapter);
                pCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        pBrandSpinner.setEnabled(true);
                        String user_type = pCat.getSelectedItem().toString();
                        databaseReference.child("Product_brand").child(user_type).addValueEventListener(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                List<String> pBrandArea = new ArrayList<String>();
                                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren())
                                {
                                    String temp = areaSnapshot.getKey();
                                    pBrandArea.add(temp);
                                }

                                ArrayAdapter<String> pBrandAdapter = new ArrayAdapter<String>(viewInventory.this, android.R.layout.simple_spinner_item, pBrandArea);
                                pBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                pBrandSpinner.setAdapter(pBrandAdapter);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
            Intent intent = new Intent(viewInventory.this, MainActivity.class);
            startActivity(intent);
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
