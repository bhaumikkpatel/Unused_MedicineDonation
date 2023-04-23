package com.example.medonation;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;

import java.util.Calendar;

public class disasteralert_admin extends Fragment {
    View view;
    TextInputLayout d_medname,d_quantity,d_affectedarea,D_disastertype;
    Button d_btnpost,d_btnview;
    String postdate;
    //date picker
    private DatePickerDialog datePickerDialog;
    public Button dateButton;

    FirebaseDatabase rootnode;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://medicines2-d061f-default-rtdb.firebaseio.com/");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_disasteralert_admin, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("disaster_alert");

        d_medname=view.findViewById(R.id.d_medicinename);
        d_quantity=view.findViewById(R.id.d_quantityneed);
        d_affectedarea=view.findViewById(R.id.d_affected_area);
        D_disastertype=view.findViewById(R.id.d_disastertype);
        d_btnpost=view.findViewById(R.id.d_btn);
        d_btnview=view.findViewById(R.id.viewdisaster);

        //date picker
        initDatePicker();
        dateButton = view.findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }

        });
        d_btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode=FirebaseDatabase.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference("disaster_alert");

                postdate=dateButton.getText().toString();//
                String dsmedicineName=d_medname.getEditText().getText().toString();
                String dsmedicineQuantity=d_quantity.getEditText().getText().toString();
                String dsArea=d_affectedarea.getEditText().getText().toString();
                String dstype=D_disastertype.getEditText().getText().toString();
                String dspostDate=postdate;

                if (!validateshortagemedicinename() | !validateshortagemedicineQuantity() | !validateshortagearea() | !validatedisastertype()) {
                    return;
                }
                dModel dmodel=new dModel(dsmedicineName,dsmedicineQuantity,dsArea,dspostDate,dstype);
                String key=databaseReference.push().getKey();
                databaseReference.child(key).setValue(dmodel);
                Toast.makeText(getActivity().getApplicationContext(), "Post Succesfully", Toast.LENGTH_SHORT).show();
            }

            private boolean validateshortagearea() {
                String val = d_affectedarea.getEditText().getText().toString();
                if (val.isEmpty()) {
                    d_affectedarea.setError("Field cannot be empty");
                    return false;
                } else {
                    d_affectedarea.setError(null);
                    d_affectedarea.setErrorEnabled(false);
                    return true;
                }
            }

            private boolean validateshortagemedicineQuantity() {
                String val = d_quantity.getEditText().getText().toString();
                if (val.isEmpty()) {
                    d_quantity.setError("Field cannot be empty");
                    return false;
                } else {
                    d_quantity.setError(null);
                    d_quantity.setErrorEnabled(false);
                    return true;
                }
            }

            private boolean validateshortagemedicinename() {
                String val = d_medname.getEditText().getText().toString();
                if (val.isEmpty()) {
                    d_medname.setError("Field cannot be empty");
                    return false;
                } else {
                    d_medname.setError(null);
                    d_medname.setErrorEnabled(false);
                    return true;
                }
            }
            private boolean validatedisastertype() {
                String val = D_disastertype.getEditText().getText().toString();
                if (val.isEmpty()) {
                    D_disastertype.setError("Field cannot be empty");
                    return false;
                } else {
                    D_disastertype.setError(null);
                    D_disastertype.setErrorEnabled(false);
                    return true;
                }
            }
        });
//
        d_btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),disasterview.class);
                startActivity(intent);
            }
        });

        return view;
    }
    //date picker
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this.getContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + "/" + day + "/" + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }
}