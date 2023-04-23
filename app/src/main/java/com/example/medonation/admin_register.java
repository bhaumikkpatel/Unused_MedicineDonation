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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_register extends AppCompatActivity {
    TextInputLayout regadname,regadcnic,regademail,regadphno,regadpasswod;
    Button buttonadminbacksignin,buttonadminregister;

    FirebaseDatabase rootnode;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://medicines2-d061f-default-rtdb.firebaseio.com/");
    //auth
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        //auth
        auth=FirebaseAuth.getInstance();
        //auth

        buttonadminbacksignin=findViewById(R.id.admingobacksignin);
        buttonadminregister=findViewById(R.id.registernewadmin);

        regadname=findViewById(R.id.radminetname);
        regadcnic=findViewById(R.id.radminetcnic);
        regademail=findViewById(R.id.radminemail1);
        regadphno=findViewById(R.id.radminph1);
        regadpasswod=findViewById(R.id.radminpass1);

        buttonadminregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//authcancel                rootnode=FirebaseDatabase.getInstance();
//authcancel                 adminreference=rootnode.getReference("admins");

                String adname=regadname.getEditText().getText().toString();
                String adcnic=regadcnic.getEditText().getText().toString();
                String ademail=regademail.getEditText().getText().toString();
                String adphno=regadphno.getEditText().getText().toString();
                String adpassword=regadpasswod.getEditText().getText().toString();

//authcancel                 AdminHelperClass adminHelperClass=new AdminHelperClass(adname,adcnic,ademail,adphno,adpassword);
                if (!validateadname() | !validateadcnic() | !validateadmail() |
                        !validateadphno() | !validateadcpass()) {
                    return;
                }
                //firebase data store node
 //authcancel               adminreference.child(adcnic).setValue(adminHelperClass);
//auth
               auth.createUserWithEmailAndPassword(ademail,adpassword)
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                      AdminHelperClass adminHelperClass=new AdminHelperClass(adname,adcnic,ademail,adphno,adpassword);
                    FirebaseDatabase.getInstance().getReference("admins").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(adminHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(admin_register.this,"user has been registered successfully",Toast.LENGTH_LONG).show();

                            }
                            else {
                                Toast.makeText(admin_register.this,"user has not been registered successfully",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                       }
                       else {
                           Toast.makeText(admin_register.this,"failed to register",Toast.LENGTH_LONG).show();

                       }
                   }
               });
//auth
            }
        });

        buttonadminbacksignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_register.this,admin_login.class);
                startActivity(intent);
            }
        });
    }
    private Boolean validateadname() {
        String val = regadname.getEditText().getText().toString();
        if (val.isEmpty()) {
            regadname.setError("Field cannot be empty");
            return false;
        } else {
            regadname.setError(null);
            regadname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateadcnic() {
        // String noWhiteSpace = "\\A\\w{4,20}\\z";
        String val = regadcnic.getEditText().getText().toString();
        if (val.isEmpty()) {
            regadcnic.setError("Field cannot be empty");
            return false;
        } else if (val.length() != 13) {
            regadcnic.setError("invalid cnic");
            return false;
        } //else if (!val.matches(noWhiteSpace)) {
        // regcnic.setError("White spaces are not allowed");
        //return false; }
        else {
            regadcnic.setError(null);
            regadcnic.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateadmail() {
        String val = regademail.getEditText().getText().toString();
        if (val.isEmpty()) {
            regademail.setError("Field cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
            regademail.setError("please enter valid email");
            return false;
        } else {
            regademail.setError(null);
            regademail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateadphno() {
        String val = regadphno.getEditText().getText().toString();
        if (val.isEmpty()) {
            regadphno.setError("Field cannot be empty");
            return false;
        } else {
            regadphno.setError(null);
            regadphno.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateadcpass() {
        String val = regadpasswod.getEditText().getText().toString();
        if (val.isEmpty()) {
            regadpasswod.setError("Field cannot be empty");
            return false;
        } else if (val.length() < 5) {
            regadpasswod.setError("password is not valid");
            return false;
        } else {
            regadpasswod.setError(null);
            regadpasswod.setErrorEnabled(false);
            return true;
        }
    }
}