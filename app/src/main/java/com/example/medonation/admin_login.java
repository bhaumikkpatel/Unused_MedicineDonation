package com.example.medonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class admin_login extends AppCompatActivity {
    Button buttonadsignin, buttonadsignup,buttonfp;
    TextInputLayout cnic, password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth=FirebaseAuth.getInstance();

        setContentView(R.layout.activity_admin_login);

        buttonadsignup = findViewById(R.id.admingosignup);
        buttonadsignin = findViewById(R.id.adminsignin);
        buttonfp=findViewById(R.id.btnforgetpassword);
        cnic = findViewById(R.id.etn1);
        password = findViewById(R.id.pass1);

        buttonfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_login.this,forgetPassword.class);
                startActivity(intent);
            }
        });

        buttonadsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(admin_login.this, admin_register.class);
                startActivity(intent);
            }
        });

     
        buttonadsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = cnic.getEditText().getText().toString().trim();
                String userEnteredpass = password.getEditText().getText().toString().trim();
                if (!validateadmincnic() | !validateadminpass()) {
                    return;
                }
//authcancel                else {
// authcancel               isUser();
// authcancel               }
               // Intent intent = new Intent(admin_login.this, ADMINHOME.class);
                //startActivity(intent);
//auth
          if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
              cnic.setError("Enter valid email");
              cnic.requestFocus();
              return;
          }
//authentication
          auth.signInWithEmailAndPassword(email,userEnteredpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      Intent intent = new Intent(admin_login.this, ADMINHOME.class);
                      startActivity(intent);
 //                     isUser();

                  }
                  else {
                      Toast.makeText(admin_login.this,"failed to login",Toast.LENGTH_SHORT).show();

                  }
              }
          });
//auth
            }

    /*      private void isUser() {
                String userEnteredcnic = cnic.getEditText().getText().toString().trim();
              String userEnteredpass = password.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("admins");
                Query checkuser = reference.orderByChild("adcnic").equalTo(userEnteredcnic);

                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            cnic.setError(null);
                            cnic.setErrorEnabled(true);

                            String passwordFromDB = snapshot.child(userEnteredcnic).child("adpassword").getValue(String.class);
                            if (passwordFromDB.equals(userEnteredpass)){
                                password.setError(null);
                                password.setErrorEnabled(true);

                                String nameFromDB = snapshot.child(userEnteredcnic).child("adname").getValue(String.class);
                                String cnicFromDB = snapshot.child(userEnteredcnic).child("adcnic").getValue(String.class);
                                String emailFromDB = snapshot.child(userEnteredcnic).child("ademail").getValue(String.class);
                                String phnoFromDB = snapshot.child(userEnteredcnic).child("adphno").getValue(String.class);

                                Intent intent = new Intent(admin_login.this, ADMINHOME.class);
                                intent.putExtra("adname",nameFromDB);
                                intent.putExtra("adcnic", cnicFromDB);
                                intent.putExtra("ademail", emailFromDB);
                                intent.putExtra("adphno", phnoFromDB);
                                intent.putExtra("adpassword", passwordFromDB);
                                startActivity(intent);
                            }
                            else{
                                password.setError("Wrong password");
                                password.requestFocus();
                            }
                        }
                        else {
                            cnic.setError("No such user exist");
                            cnic.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }*/
        });

    }

    private Boolean validateadmincnic() {
        String val = cnic.getEditText().getText().toString();
        if (val.isEmpty()) {
            cnic.setError("Field cannot be empty");
            return false;
        } else {
            cnic.setError(null);
            cnic.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateadminpass() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

}