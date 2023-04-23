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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medonation.Fragments.DonorProfile;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class register_medicine extends Fragment {
    View view;
    TextInputLayout xmedicinename, xmedquantity, full_name, ph_no;
    TextView textView1, textView2;
    Button btnregdone;
    RadioButton rbtnliquid, rbtnsollid, rbtnnew, rbtnused;
    String xmedtype, xmedcnd, xExpirydate, get_username, get_phno;

    //date picker
    private DatePickerDialog datePickerDialog;
    public Button dateButton;

    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register_medicine, container, false);
//
        reference = FirebaseDatabase.getInstance().getReference("users");
        xmedicinename = view.findViewById(R.id.medicinename);
        xmedquantity = view.findViewById(R.id.Quantity);
        btnregdone = view.findViewById(R.id.reg_done);

        textView1 = view.findViewById(R.id.tvtype);
        textView2 = view.findViewById(R.id.tvcondition);

        rbtnliquid = view.findViewById(R.id.typeliquid);
        rbtnsollid = view.findViewById(R.id.typesolid);
        rbtnnew = view.findViewById(R.id.newmed);
        rbtnused = view.findViewById(R.id.usedmed);
        //retrieve user data
        full_name = view.findViewById(R.id.donor_name);
        ph_no = view.findViewById(R.id.donor_phno);
        showAllDatau();
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

        btnregdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootnode = FirebaseDatabase.getInstance();
                reference = FirebaseDatabase.getInstance().getReference("registermedicine");
                xExpirydate = dateButton.getText().toString();//


                String medname = xmedicinename.getEditText().getText().toString();
                String medqty = xmedquantity.getEditText().getText().toString();
                String medexpiry = xExpirydate;
                String med_donor = full_name.getEditText().getText().toString();
                String md_phno = ph_no.getEditText().getText().toString();
                //for radio button value
                if (rbtnliquid.isChecked()) {
                    xmedtype = "liquid";
                } else if (rbtnsollid.isChecked()) {
                    xmedtype = "solid";
                } else {
                    textView1.setError("select any type");
                    Toast.makeText(getActivity().getApplicationContext(), "select medicine type", Toast.LENGTH_SHORT).show();
                    textView1.requestFocus();
                }

                if (rbtnnew.isChecked()) {
                    xmedcnd = "new";
                } else if (rbtnused.isChecked()) {
                    xmedcnd = "used";
                } else {
                    textView2.setError("select condition");
                    Toast.makeText(getActivity().getApplicationContext(), "select medicine condition", Toast.LENGTH_LONG).show();
                    textView2.requestFocus();
                }
                String medtype = xmedtype;
                String medcondition = xmedcnd;
                //for radio button value

                if (!validatemedicinename() | !validatemedicineQuantity() | !validatedonorname() | !validatephno()) {
                    return;
                }
                register_medicineHelperClass registerMedicineHelperClass = new register_medicineHelperClass(medname, medtype, medqty, medcondition, medexpiry, med_donor, md_phno);
                String key = reference.push().getKey();
                reference.child(key).setValue(registerMedicineHelperClass);
                //reference.child(medname).setValue(registerMedicineHelperClass);
                Toast.makeText(getActivity().getApplicationContext(), "Medicine registered succesfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //date picker
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
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

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + "/" + day + "/" + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }


    //getting user data
    private void showAllDatau() {
        Intent intent = getActivity().getIntent();
        get_username = intent.getStringExtra("name");
        get_phno = intent.getStringExtra("phno");

        full_name.getEditText().setText(get_username);
        ph_no.getEditText().setText(get_phno);

    }

    private Boolean validatemedicinename() {
        String val = xmedicinename.getEditText().getText().toString();
        if (val.isEmpty()) {
            xmedicinename.setError("Field cannot be empty");
            return false;
        } else {
            xmedicinename.setError(null);
            xmedicinename.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatemedicineQuantity() {
        String val = xmedquantity.getEditText().getText().toString();
        if (val.isEmpty()) {
            xmedquantity.setError("Field cannot be empty");
            return false;
        } else {
            xmedquantity.setError(null);
            xmedquantity.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatedonorname() {
        String val = full_name.getEditText().getText().toString();
        if (val.isEmpty()) {
            full_name.setError("Field cannot be empty");
            return false;
        } else {
            full_name.setError(null);
            full_name.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatephno() {
        String val = ph_no.getEditText().getText().toString();
        if (val.isEmpty()) {
            ph_no.setError("Field cannot be empty");
            return false;
        } else {
            ph_no.setError(null);
            ph_no.setErrorEnabled(false);
            return true;
        }
    }

}