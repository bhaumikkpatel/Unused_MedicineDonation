package com.example.medonation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class medshortage_admin extends Fragment {
    View view;
    TextInputLayout shortagemedname,shortagequantity,shortagearea;
    Button btnpost,btnshortageview;
    String postdate;
    //date picker
    private DatePickerDialog datePickerDialog;
    public Button dateButton;

    FirebaseDatabase rootnode;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_medshortage_admin, container, false);
        reference = FirebaseDatabase.getInstance().getReference("medicineshortage");

        shortagemedname=view.findViewById(R.id.shortagemedicinename);
        shortagequantity=view.findViewById(R.id.quantityneed);
        shortagearea=view.findViewById(R.id.shortage_area);
        btnpost=view.findViewById(R.id.shortagebtn);
        btnshortageview=view.findViewById(R.id.viewshortage);

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
btnpost.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rootnode=FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("medicineshortage");

        postdate=dateButton.getText().toString();//
        String medicineName=shortagemedname.getEditText().getText().toString();
        String medicineQuantity=shortagequantity.getEditText().getText().toString();
        String shortageArea=shortagearea.getEditText().getText().toString();
        String postDate=postdate;

        if (!validateshortagemedicinename() | !validateshortagemedicineQuantity() | !validateshortagearea()) {
            return;
        }
        sModel smodel=new sModel(medicineName,medicineQuantity,shortageArea,postDate);
        String key=reference.push().getKey();
        reference.child(key).setValue(smodel);
        Toast.makeText(getActivity().getApplicationContext(), "Post Succesfully", Toast.LENGTH_SHORT).show();

    }

    private boolean validateshortagearea() {
        String val = shortagearea.getEditText().getText().toString();
        if (val.isEmpty()) {
            shortagearea.setError("Field cannot be empty");
            return false;
        } else {
            shortagearea.setError(null);
            shortagearea.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateshortagemedicineQuantity() {
        String val = shortagequantity.getEditText().getText().toString();
        if (val.isEmpty()) {
            shortagequantity.setError("Field cannot be empty");
            return false;
        } else {
            shortagequantity.setError(null);
            shortagequantity.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateshortagemedicinename() {
        String val = shortagemedname.getEditText().getText().toString();
        if (val.isEmpty()) {
            shortagemedname.setError("Field cannot be empty");
            return false;
        } else {
            shortagemedname.setError(null);
            shortagemedname.setErrorEnabled(false);
            return true;
        }
    }
});
//
        btnshortageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),adminshortageview.class);
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