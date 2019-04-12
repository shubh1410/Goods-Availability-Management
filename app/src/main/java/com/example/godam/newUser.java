package com.example.godam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.godam.Utils.NewUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                                                  /*Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(uname);
                                                  if (matcher.find()) {}else {
                                                      //Toast.makeText(newUser.this, "password and confirm does not match", Toast.LENGTH_SHORT).show();
                                                      username.setError("Email id invalid");
                                                      flag = false;
                                                  }
*/
                                                  if (flag) {
                                                      NewUser user = new NewUser(fn, ln, user_type, uname, pass, no);
                                                      databaseReference.child("user").child(user.getUname()).setValue(user);
                                                      Toast.makeText(newUser.this, "User added", Toast.LENGTH_SHORT).show();
                                                      startActivity(new Intent(newUser.this, supervisor.class));
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
                startActivity(intent);
//            Toast.makeText(this,"logOut Clicked",Toast.LENGTH_SHORT).show();
            } else {
                return super.onOptionsItemSelected(item);
            }
            return true;
        }
  /* private static boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

*/
    }
