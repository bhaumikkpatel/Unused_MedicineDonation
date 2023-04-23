package com.example.medonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class donor_register extends AppCompatActivity {
    TextInputLayout regfullname, regcnic, regemail, regphoneno, regpassword;
    Button buttonregnewdonor;
    Button buttongobacksin;

    FirebaseDatabase rootnode;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://medicines2-d061f-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_register);

        buttonregnewdonor = findViewById(R.id.registernewdonor);
        buttongobacksin = findViewById(R.id.donorgobacksignin);

        regfullname = findViewById(R.id.rdonorname);
        regcnic = findViewById(R.id.rdonorcnic);
        regemail = findViewById(R.id.rdonormail);
        regphoneno = findViewById(R.id.rdonorphone);
        regpassword = findViewById(R.id.rdonorpass);

        buttonregnewdonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode = FirebaseDatabase.getInstance();
                databaseReference = rootnode.getReference("users");

                //get all values from text fields
                String name = regfullname.getEditText().getText().toString();
                String cnic = regcnic.getEditText().getText().toString();
                String email = regemail.getEditText().getText().toString();
                String phno = regphoneno.getEditText().getText().toString();
                String password = regpassword.getEditText().getText().toString();

                //UserHelperClass userHelperClass = new UserHelperClass(name, cnic, email, phno, password);
                //below line to save all values in firebase

                if (!validatedonorname() | !validatedonorcnic() | !validatedonormail() |
                        !validatedonorphno() | !validatedonorcpass()) {
                    return;
                }
                UserHelperClass userHelperClass = new UserHelperClass(name, cnic, email, phno, password);
                //below line to save all values in firebase
                //to insert multiple values on single node use child()
               // reference.child(email).setValue(userHelperClass);
                databaseReference.child(cnic).setValue(userHelperClass);
            }
        });

        buttongobacksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(donor_register.this, DonorSignIn.class);
                startActivity(intent);
            }
        });
    }
//validation from here
    private Boolean validatedonorname() {
        String val = regfullname.getEditText().getText().toString();
        if (val.isEmpty()) {
            regfullname.setError("Field cannot be empty");
            return false;
        } else {
            regfullname.setError(null);
            regfullname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatedonorcnic() {
       // String noWhiteSpace = "\\A\\w{4,20}\\z";
        String val = regcnic.getEditText().getText().toString();
        if (val.isEmpty()) {
            regcnic.setError("Field cannot be empty");
            return false;
        } else if (val.length() != 13) {
            regcnic.setError("invalid cnic");
            return false;
        } //else if (!val.matches(noWhiteSpace)) {
           // regcnic.setError("White spaces are not allowed");
            //return false; }
            else {
            regcnic.setError(null);
            regcnic.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatedonormail() {
        String val = regemail.getEditText().getText().toString();
        if (val.isEmpty()) {
            regemail.setError("Field cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
            regemail.setError("please enter valid email");
            return false;
        } else {
            regemail.setError(null);
            regemail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatedonorphno() {
        String val = regphoneno.getEditText().getText().toString();
        if (val.isEmpty()) {
            regphoneno.setError("Field cannot be empty");
            return false;
        } else {
            regphoneno.setError(null);
            regphoneno.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatedonorcpass() {
        String val = regpassword.getEditText().getText().toString();
        if (val.isEmpty()) {
            regpassword.setError("Field cannot be empty");
            return false;
        } else if (val.length() < 5) {
            regpassword.setError("password is not valid");
            return false;
        } else {
            regpassword.setError(null);
            regpassword.setErrorEnabled(false);
            return true;
        }
    }

}