package com.example.medonation;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class adminprofile extends Fragment {
    View view;
    TextView upperfullname, uppermail;
    TextInputLayout fullname, cnic, mail, phno, password;

    Button btnlogout;
    //afterauth
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    String getting_username, getting_cnic, getting_mail, getting_phno, getting_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_adminprofile, container, false);
//afterauth
        user= FirebaseAuth.getInstance().getCurrentUser();
//afterauth
        reference = FirebaseDatabase.getInstance().getReference("admins");
//afterauth
        userID=user.getUid();
       // tools:context=".Fragments.DonorProfile"
        upperfullname = view.findViewById(R.id.upperfull_name);
        uppermail = view.findViewById(R.id.upper_mail);

        fullname = view.findViewById(R.id.fullname);
        cnic = view.findViewById(R.id.cnic);
        mail = view.findViewById(R.id.email);
        phno = view.findViewById(R.id.phno);
        password = view.findViewById(R.id.passwordprofile);
        //updbutton=view.findViewById(R.id.button_update);
        btnlogout=view.findViewById(R.id.button_logout);
//afterauth
        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AdminHelperClass userprofile=snapshot.getValue(AdminHelperClass.class);
 //show data
            if (userprofile != null){
                String getting_username, getting_cnic, getting_mail, getting_phno, getting_password;
                getting_username=userprofile.getAdname();
                getting_cnic=userprofile.getAdcnic();
                getting_mail=userprofile.getAdemail();
                getting_phno=userprofile.getAdphno();
                getting_password=userprofile.getAdpassword();

                upperfullname.setText(getting_username);
                uppermail.setText(getting_mail);

                fullname.getEditText().setText(getting_username);
                cnic.getEditText().setText(getting_cnic);
                mail.getEditText().setText(getting_mail);
                phno.getEditText().setText(getting_phno);
                password.getEditText().setText(getting_password);

            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        showAllData();

     /*   updbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (namechanged()  || phnochanged()||emailchanged()) {
                    Toast.makeText(getActivity().getApplicationContext(), "data has been updated", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(), "no data change", Toast.LENGTH_SHORT).show();

            }

            private boolean emailchanged() {
                if (!getting_mail.equals(mail.getEditText().getText().toString())) {
                    reference.child(userID).child("ademail").setValue(mail.getEditText().getText().toString());
                    getting_mail=mail.getEditText().getText().toString();
                    return true;
                } else {
                    return false;
                }
            }

            private boolean phnochanged() {
                if (!getting_phno.equals(phno.getEditText().getText().toString())) {
                    reference.child(userID).child("adphno").setValue(phno.getEditText().getText().toString());
                    getting_phno=phno.getEditText().getText().toString();
                    return true;
                } else {
                    return false;
                }
            }

            private boolean namechanged() {
                if (!getting_username.equals(fullname.getEditText().getText().toString())) {
                    reference.child(userID).child("adname").setValue(fullname.getEditText().getText().toString());
                    getting_username=fullname.getEditText().getText().toString();
                    return true;
                } else {
                    return false;
                }
            }
        });*/
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext(),admin_login.class));
            }
        });
        return view;
    }

    private void showAllData() {
        Intent intent = getActivity().getIntent();

        getting_username=intent.getStringExtra("adname");
         getting_cnic=intent.getStringExtra("adcnic");
         getting_mail=intent.getStringExtra("ademail");
         getting_phno=intent.getStringExtra("adphno");
         getting_password=intent.getStringExtra("adpassword");

        upperfullname.setText(getting_username);
        uppermail.setText(getting_mail);

        fullname.getEditText().setText(getting_username);
        cnic.getEditText().setText(getting_cnic);
        mail.getEditText().setText(getting_mail);
        phno.getEditText().setText(getting_phno);
        password.getEditText().setText(getting_password);

    }
}