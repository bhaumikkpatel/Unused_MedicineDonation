package com.example.medonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class findpaths extends AppCompatActivity {
    EditText etSource, etDestination;
    Button btntrack;
    Spinner spinner1, spinner2;
    String ngo,ngos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpaths);
        etSource = findViewById(R.id.et_source);
      //  etDestination = findViewById(R.id.et_destination);
        btntrack = findViewById(R.id.bt_track);
        spinner1 = findViewById(R.id.selectngocity);
        spinner2 = findViewById(R.id.selectngo);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Select Area");
        arrayList.add("Islamabad");
        arrayList.add("Rawalpindi");
        arrayList.add("Lahore");
        arrayList.add("Multan");
        arrayList.add("Fisalabad");
        arrayList.add("Peshawar");
        arrayList.add("karachi");

        ArrayAdapter<String> objAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner1.setAdapter(objAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=arrayList.get(position);
                if (city.equals("Islamabad")){
                    ArrayList<String> arrayList=new ArrayList<>();
                    arrayList.add("Select NGO");
                    arrayList.add("Aas Welfare Association");
                    arrayList.add("Shifa International Hospital Ltd");
                    arrayList.add("Quaid-e-Azam International Hospital");
                    arrayList.add("Kulsum International Hospital");
                    arrayList.add("Akbar Niazi Hospital");
                    arrayList.add("PIMS Hospital");
                    arrayList.add("Al-Noor Welfare Foundation");
                    ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner2.setAdapter(objAdapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ngos=arrayList.get(position);
                            Toast.makeText(getApplicationContext(), "Selected NGO " +ngos, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
                else if (city.equals("Rawalpindi")){
                    ArrayList<String> arrayList=new ArrayList<>();
                    arrayList.add("Farz NGO");
                    arrayList.add("Message Welfare Society (NGO) rawalpindi");
                    arrayList.add("The NGO World rawalpindi");
                    arrayList.add("RAHMA Islamic Relief Pakistan");
                    arrayList.add("Hasna Welfare rwp");
                    arrayList.add("Student Welfare Society");
                    arrayList.add("Shifa Foundation rawalpindi");
                    ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner2.setAdapter(objAdapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ngos=arrayList.get(position);
                            Toast.makeText(getApplicationContext(), "Selected NGO " +ngos, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (city.equals("Lahore")){
                    ArrayList<String> arrayList=new ArrayList<>();
                    arrayList.add("FALAH FOUNDATION (NGO) lahore");
                    arrayList.add("Transparent Hands Trust");
                    arrayList.add("Nabi Pura Welfare Society Lahore (NGO)");
                    arrayList.add("Esperance NGO lahore");
                    arrayList.add("Lahore Welfare Foundation");
                    arrayList.add("Idara Aaghosh");
                    arrayList.add("Green Circle Organization");
                    ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner2.setAdapter(objAdapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ngos=arrayList.get(position);
                            Toast.makeText(getApplicationContext(), "Selected NGO " +ngos, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (city.equals("Multan")){
                    ArrayList<String> arrayList=new ArrayList<>();
                    arrayList.add("Roshni Welfare Organization multan");
                    arrayList.add("Courage Development Foundation multan");
                    arrayList.add("Hasnat Human Care multan");
                    arrayList.add("Social Development Organization (SDO) Multan Pakistan");
                    arrayList.add("Bunyad Multan");
                    ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner2.setAdapter(objAdapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ngos=arrayList.get(position);
                            Toast.makeText(getApplicationContext(), "Selected NGO " +ngos, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (city.equals("Fisalabad")){
                    ArrayList<String> arrayList=new ArrayList<>();
                    arrayList.add("Farrukh Foundation Office faisalabad");
                    arrayList.add("AWAM Pakistan Office faisalabad");
                    arrayList.add("Hasnat Human Care multan");
                    arrayList.add("End Poverty Welfare Society- Faisalabad");
                    arrayList.add("Saylani Trust faisalabad");
                    ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner2.setAdapter(objAdapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ngos=arrayList.get(position);
                            Toast.makeText(getApplicationContext(), "Selected NGO " +ngos, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (city.equals("Peshawar")){
                    ArrayList<String> arrayList=new ArrayList<>();
                    arrayList.add("EHSAR FOUNDATION peshawar");
                    arrayList.add("FAO Peshawar");
                    arrayList.add("TARS Foundation peshawar");
                    arrayList.add("Intercooperation Peshawar Pakistan");
                    arrayList.add("Relief international Peshawar");
                    ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner2.setAdapter(objAdapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ngos=arrayList.get(position);
                            Toast.makeText(getApplicationContext(), "Selected NGO " +ngos, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (city.equals("karachi")){
                    ArrayList<String> arrayList=new ArrayList<>();
                    arrayList.add("AZAD Foundation Pakistan karachi");
                    arrayList.add("HOPE karachi");
                    arrayList.add("Pegham NGOs karachi");
                    arrayList.add("Jaag Karachi");
                    arrayList.add("Indus Earth Trust karachi");
                    ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
                    spinner2.setAdapter(objAdapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ngos=arrayList.get(position);
                            Toast.makeText(getApplicationContext(), "Selected NGO " +ngos, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
            }
//
String[] pindiNGOs={"Farz NGO", "Bedari Islamabad","Message Welfare Society (NGO)","The NGO World","RAHMA Islamic Relief Pakistan","Hasna Welfare","Student Welfare Society","Shifa Foundation rawalpindi"};
            String[] lahorengos={"FALAH FOUNDATION (NGO) lahore","Transparent Hands Trust","Nabi Pura Welfare Society Lahore (NGO)","Esperance NGO lahore","Lahore Welfare Foundation","Idara Aaghosh","Green Circle Organization"};
            String[] multanngos={"Roshni Welfare Organization multan","Courage Development Foundation","Hasnat Human Care","Social Development Organization (SDO) Multan Pakistan","Bunyad Multan"};
            String[] faisalabad={"Farrukh Foundation Office","AWAM Pakistan Office","End Poverty Welfare Society- Faisalabad","Saylani Trust faisalabad","Saylani House Faisalabad"};
            String[] peshawarngos={"EHSAR FOUNDATION peshawar","FAO Peshawar","TARS Foundation peshawar","Intercooperation Peshawar Pakistan","Relief international Peshawar"};
            String[] karachingos={"AZAD Foundation Pakistan karachi","HOPE karachi","Pegham NGOs","Jaag Karachi","Indus Earth Trust"};

            //

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btntrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSource = etSource.getText().toString().trim();
     //           String sDestination = etDestination.getText().toString().trim();

                if (sSource.equals("") ) {
                    Toast.makeText(getApplicationContext(), "Enter Your locations", Toast.LENGTH_SHORT).show();
                } else {
                    DisplayTrack(sSource);

                }
            }

            private void DisplayTrack(String sSource ) {
                try {
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/" + sSource + "/" +
                            ngos);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    return;
                }
            }
        });
    }
}