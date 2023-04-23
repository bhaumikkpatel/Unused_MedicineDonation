package com.example.medonation.Fragments;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medonation.DonorSignIn;
import com.example.medonation.R;
import com.example.medonation.admin_login;
import com.example.medonation.usercategory;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorProfile extends Fragment {
    View view;
    TextView upperfullname, uppermail;
    TextInputLayout fullname, cnic, mail, phno, password;
    Button updbutton,btnlogout;
    DatabaseReference reference;

    String getting_username, getting_cnic, getting_mail, getting_phno, getting_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_donor_profile, container, false);

        reference = FirebaseDatabase.getInstance().getReference("users");

        upperfullname = view.findViewById(R.id.upperfull_name);
        uppermail = view.findViewById(R.id.upper_mail);

        fullname = view.findViewById(R.id.fullname);
        cnic = view.findViewById(R.id.cnic);
        mail = view.findViewById(R.id.email);
        phno = view.findViewById(R.id.phno);
        password = view.findViewById(R.id.passwordprofile);
        updbutton=view.findViewById(R.id.button_update);
        btnlogout=view.findViewById(R.id.button_logout);
        //data retrive
        showAllData();
        // Inflate the layout for this fragment*/

     updbutton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (namechanged() || passwordchanged() || phnochanged()||emailchanged()) {
                 Toast.makeText(getActivity().getApplicationContext(), "data has been updated", Toast.LENGTH_SHORT).show();
             }
             else
                 Toast.makeText(getActivity().getApplicationContext(), "no data change", Toast.LENGTH_SHORT).show();

         }

         private boolean emailchanged() {
             if (!getting_mail.equals(mail.getEditText().getText().toString())) {
                 reference.child(getting_cnic).child("email").setValue(mail.getEditText().getText().toString());
                 getting_mail=mail.getEditText().getText().toString();
                 return true;
             } else {
                 return false;
             }
         }

         private boolean phnochanged() {
             if (!getting_phno.equals(phno.getEditText().getText().toString())) {
                 reference.child(getting_cnic).child("phno").setValue(phno.getEditText().getText().toString());
                 getting_phno=phno.getEditText().getText().toString();
                 return true;
             } else {
                 return false;
             }
         }

         private boolean passwordchanged() {
             if (!getting_password.equals(password.getEditText().getText().toString())) {
                 reference.child(getting_cnic).child("password").setValue(password.getEditText().getText().toString());
                 getting_password=password.getEditText().getText().toString();
                 return true;
             } else {
                 return false;
             }
         }

         private boolean namechanged() {
             if (!getting_username.equals(fullname.getEditText().getText().toString())) {
                   reference.child(getting_cnic).child("name").setValue(fullname.getEditText().getText().toString());
                  getting_username=fullname.getEditText().getText().toString();
                 return true;
             } else {
                 return false;
             }
         }
     });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext(), usercategory.class));
            }
        });
        return view;
    }

    private void showAllData() {
        Intent intent = getActivity().getIntent();
        getting_username = intent.getStringExtra("name");
        getting_cnic = intent.getStringExtra("cnic");
        getting_mail = intent.getStringExtra("email");
        getting_phno = intent.getStringExtra("phno");
        getting_password = intent.getStringExtra("password");

        upperfullname.setText(getting_username);
        uppermail.setText(getting_mail);

        fullname.getEditText().setText(getting_username);
        cnic.getEditText().setText(getting_cnic);
        mail.getEditText().setText(getting_mail);
        phno.getEditText().setText(getting_phno);
        password.getEditText().setText(getting_password);

    }


}


