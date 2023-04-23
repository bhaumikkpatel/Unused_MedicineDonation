package com.example.medonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DonorSignIn extends AppCompatActivity {
    Button buttonsignin, buttongosignup;
    TextInputLayout cnic, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donor_sign_in);

        buttonsignin = findViewById(R.id.donorsignin);
        buttongosignup = findViewById(R.id.donorgosignup);
        cnic = findViewById(R.id.etn1);
        password = findViewById(R.id.pass1);

        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatedonorcnic() | !validatedonorcpass()) {
                    return;
                } else {
                    isUser();
                }
             //   Intent intent = new Intent(DonorSignIn.this, DONORHOME.class);
             //   startActivity(intent);
            }

            private void isUser() {
                String userEnteredcnic = cnic.getEditText().getText().toString().trim();
                String userEnteredpass = password.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkuser = reference.orderByChild("cnic").equalTo(userEnteredcnic);

                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            cnic.setError(null);
                            cnic.setErrorEnabled(true);

                            String passwordFromDB = snapshot.child(userEnteredcnic).child("password").getValue(String.class);
                        if (passwordFromDB.equals(userEnteredpass)) {
                            password.setError(null);
                            password.setErrorEnabled(true);

                            String nameFromDB = snapshot.child(userEnteredcnic).child("name").getValue(String.class);
                            String cnicFromDB = snapshot.child(userEnteredcnic).child("cnic").getValue(String.class);
                            String emailFromDB = snapshot.child(userEnteredcnic).child("email").getValue(String.class);
                            String phnoFromDB = snapshot.child(userEnteredcnic).child("phno").getValue(String.class);

                            Intent intent = new Intent(DonorSignIn.this, DONORHOME.class);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("cnic", cnicFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("phno", phnoFromDB);
                            intent.putExtra("password", passwordFromDB);
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
            }
        });

        buttongosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonorSignIn.this, donor_register.class);
                startActivity(intent);
            }
        });


    }

    private Boolean validatedonorcnic() {
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

    private Boolean validatedonorcpass() {
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