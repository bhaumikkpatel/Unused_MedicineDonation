package com.example.medonation;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class donationpoints extends Fragment {
View view;
Spinner spinner,spinner1;
ListView listView;
Button buttonpath,buttonmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_donationpoints, container, false);
listView=view.findViewById(R.id.ngolist);
String[] NGOs={"Aas Welfare Association", "Shifa International Hospital Ltd", "Quaid-e-Azam International Hospital", "Kulsum International Hospital", "Akbar Niazi Hospital", "PIMS Hospital", "Al-Noor Welfare Foundation"};
String[] pindiNGOs={"Farz NGO", "Bedari Islamabad","Message Welfare Society (NGO)","The NGO World","RAHMA Islamic Relief Pakistan","Hasna Welfare","Student Welfare Society","Shifa Foundation rawalpindi"};
String[] lahorengos={"FALAH FOUNDATION (NGO) lahore","Transparent Hands Trust","Nabi Pura Welfare Society Lahore (NGO)","Esperance NGO lahore","Lahore Welfare Foundation","Idara Aaghosh","Green Circle Organization"};
String[] multanngos={"Roshni Welfare Organization multan","Courage Development Foundation","Hasnat Human Care","Social Development Organization (SDO) Multan Pakistan","Bunyad Multan"};
String[] faisalabad={"Farrukh Foundation Office","AWAM Pakistan Office","End Poverty Welfare Society- Faisalabad","Saylani Trust faisalabad","Saylani House Faisalabad"};
String[] peshawarngos={"EHSAR FOUNDATION peshawar","FAO Peshawar","TARS Foundation peshawar","Intercooperation Peshawar Pakistan","Relief international Peshawar"};
String[] karachingos={"AZAD Foundation Pakistan karachi","HOPE karachi","Pegham NGOs","Jaag Karachi","Indus Earth Trust"};
//list view
//ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,NGOs);
//listView.setAdapter(adapter);

buttonpath=view.findViewById(R.id.btnpath);
buttonmap=view.findViewById(R.id.onmap);
buttonpath.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity().getApplicationContext(),findpaths.class);
        startActivity(intent);
    }
});

spinner=view.findViewById(R.id.selectcity);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Select Area");
        arrayList.add("Ahmedabad");
        arrayList.add("Himatnagar");
        arrayList.add("Surat");
        arrayList.add("Rajkot");
        arrayList.add("Mehsana");
        arrayList.add("Gandhinagar");
        arrayList.add("Jamnagar");
        ArrayAdapter<String> objAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
        spinner.setAdapter(objAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=arrayList.get(position);
                
                if (city.equals("Select Area")){
                    Toast.makeText(getContext(), "Select Area" , Toast.LENGTH_SHORT).show();
                }
                else if (city.equals("Ahmedabad")){
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,NGOs);
                    Toast.makeText(getContext(), "Selected Area "+city , Toast.LENGTH_SHORT).show();
                    listView.setAdapter(adapter);
                buttonmap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("https://www.google.com/maps/search/ngos+in+islamabad/@33.6693711,72.9578062,11z");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.setPackage("com.google.android.apps.maps");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                }
                else if (city.equals("Himatnagar")){
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,pindiNGOs);
                    listView.setAdapter(adapter);
                    buttonmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse("https://www.google.com/maps/search/ngos+in+rawalpindi/@33.5985135,73.0051168,12z/data=!3m1!4b1");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });}
                else if (city.equals("Surat")){
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,lahorengos);
                    listView.setAdapter(adapter);
                    buttonmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse("https://www.google.com/maps/search/ngos+in+lahore/@31.5282153,74.2594327,12z/data=!3m1!4b1");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });}
                else if (city.equals("Rajkot")){
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,multanngos);
                    listView.setAdapter(adapter);
                    buttonmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse("https://www.google.com/maps/search/ngos+in+multan/@30.2096952,71.4118355,12z/data=!3m1!4b1");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });}
                else if (city.equals("Mehsana")){
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,faisalabad);
                    listView.setAdapter(adapter);
                    buttonmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse("https://www.google.com/maps/search/ngos+in+faisalabad/@31.4238863,73.0411426,12z/data=!3m1!4b1");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });}
                else if (city.equals("Gandhinagar")){
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,peshawarngos);
                    listView.setAdapter(adapter);
                    buttonmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse("https://www.google.com/maps/search/ngos+in+peshawar/@34.007791,71.4438532,12z/data=!3m1!4b1");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });}
                else if (city.equals("Jamnagar")){
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,karachingos);
                    listView.setAdapter(adapter);
                    buttonmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse("https://www.google.com/maps/search/ngos+in+karachi/@24.9032024,66.9643653,11z/data=!3m1!4b1");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        return view;

    }

}