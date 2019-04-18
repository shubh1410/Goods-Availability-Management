package com.example.godam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.godam.Utils.NewUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class newUser extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "newUser";

    Button createUser;
    EditText fname, lname, pno, username, password, cpassword;
    Spinner userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        createUser = (Button) findViewById(R.id.createUser);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        cpassword = (EditText) findViewById(R.id.cpassword);
        pno = (EditText) findViewById(R.id.pno);
        userType = (Spinner) findViewById(R.id.userType);

        createUser.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              String fn = fname.getText().toString();
                                              String ln = lname.getText().toString();
                                              String uname = username.getText().toString();
                                              String pass = password.getText().toString();
                                              String cpass = cpassword.getText().toString();
                                              String no = pno.getText().toString();
                                              String user_type = userType.getSelectedItem().toString();
                                              boolean flag = true;

                                              if (!fn.isEmpty() && !ln.isEmpty() && !uname.isEmpty() && !pass.isEmpty() && !cpass.isEmpty() && !no.isEmpty() && !user_type.isEmpty()) {
                                                  if (no.length() != 10) {
                                                      //Toast.makeText(newUser.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                                                      pno.setError("Enter 10 digit phone number");
                                                      flag = false;
                                                  }
                                                  if (!pass.equals(cpass)) {
                                                      //Toast.makeText(newUser.this, "password and confirm does not match", Toast.LENGTH_SHORT).show();
                                                      cpassword.setError("password and confirm password do  not match");
                                                      flag = false;
                                                  }

                                                  if (!isEmailValid(uname)){
                                                      //Toast.makeText(newUser.this, "password and confirm does not match", Toast.LENGTH_SHORT).show();
                                                      username.setError("Email id invalid");
                                                      flag = false;
                                                  }

                                                  if (flag) {
                                                      final NewUser newuser = new NewUser(fn, ln, user_type, uname, pass, no);

                                                      //firebase authentication
                                                      mAuth.createUserWithEmailAndPassword(uname, pass)
                                                              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                  @Override
                                                                  public void onComplete(@NonNull Task<AuthResult> task) {
                                                                      if (task.isSuccessful()) {
                                                                          // Sign in success, update UI with the signed-in user's information
                                                                          Log.d(TAG, "createUserWithEmail:success");
                                                                          FirebaseUser user = mAuth.getCurrentUser();
                                                                          String temp_user=newuser.getUname().replaceAll("[.]","");
                                                                          databaseReference.child("user_info").child(temp_user).setValue(user);
                                                                          databaseReference.child(newuser.getType()).child(temp_user).setValue(newuser.getUname());
                                                                          Toast.makeText(newUser.this, "User added", Toast.LENGTH_SHORT).show();
                                                                          Intent intent = new Intent(newUser.this, supervisor.class);
                                                                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                          startActivity(intent);
                                                                          finish();
                                                                          //startActivity(new Intent(newUser.this, supervisor.class));
                                                                          //updateUI(user);
                                                                      } else {
                                                                          // If sign in fails, display a message to the user.
                                                                          Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                                                          Toast.makeText(newUser.this, "Authentication failed.",
                                                                                  Toast.LENGTH_SHORT).show();
                                                                          username.setError("Email already Exist");

                                                                         // updateUI(null);
                                                                      }

                                                                      // ...
                                                                  }
                                                              });
                                                      //*******************************

                                                  }
                                              }
                                              else {
                                                  Toast.makeText(newUser.this, "Enter all values", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                          });

    }
                /*For Menu Button in Action Bar*/
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.others_menu, menu);
            return true;
        }
        /*For Menu Button in Action Bar*/
        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            if (item.getItemId() == R.id.logOut) {
                Intent intent = new Intent(newUser.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
            } else {
                return super.onOptionsItemSelected(item);
            }
            return true;
        }
        public boolean isEmailValid(String email){

            Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
            if(matcher.find())
            {
                return true;
            }
            else
            {
                return false;
            }

        }

    }
