package com.example.medonation.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.medonation.R;
import com.example.medonation.sModel;
import com.example.medonation.shortageAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class medicine_shortage extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("medicineshortage");
    private com.example.medonation.shortageAdapter shortageAdapter;
    private ArrayList<sModel> slist;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view=  inflater.inflate(R.layout.fragment_medicine_shortage, container, false);
        editText=view.findViewById(R.id.seachshortagedonor);
        //search
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

            private void filter(String text) {
                ArrayList<sModel> filteredlist=new ArrayList<>();
                for (sModel item : slist) {
                    if (item.getMedicineName().toLowerCase().contains(text.toLowerCase())) {
                        filteredlist.add(item);
                    }
                }

                shortageAdapter.filterList(filteredlist);
            }
        });
        //
        recyclerView = view.findViewById(R.id.donorshortagerecyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        slist = new ArrayList<>();
        shortageAdapter = new shortageAdapter(getContext() ,slist );

        recyclerView.setAdapter(shortageAdapter);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sModel smodel = dataSnapshot.getValue(sModel.class);
                    slist.add(smodel);

                }
                shortageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      return view;
    }
}