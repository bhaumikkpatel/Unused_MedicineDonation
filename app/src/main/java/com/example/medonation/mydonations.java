package com.example.medonation;

import android.content.Intent;
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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mydonations extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
  private DatabaseReference root = db.getReference().child("registermedicine");
  //  DatabaseReference root ;

    private MyAdapter adapter;
    private ArrayList<Model> list;
    private ValueEventListener mQueryListener;
    TextView textView2;
    String get_username;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mydonations, container, false);
        textView2=view.findViewById(R.id.tv2);
       // root = db.getReference().child("registermedicine");

        recyclerView = view.findViewById(R.id.recyclerviewidmydonations);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        editText=view.findViewById(R.id.searchmydonation);
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
                ArrayList<Model> filteredlist=new ArrayList<>();
                for (Model item : adapter.mlist) {
                    if (item.getMedname().toLowerCase().contains(text.toLowerCase())) {
                        filteredlist.add(item);
                    }
                }

                adapter.filterList(filteredlist);
            }
        });


        showAllData();
        list = new ArrayList<>();
        adapter = new MyAdapter(getContext() ,list );

        recyclerView.setAdapter(adapter);

        mQueryListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        Query query=root.orderByChild("med_donor").equalTo(get_username);
        query.addValueEventListener(mQueryListener);
/*
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
  */

        return view;
    }

    private void showAllData() {
        Intent intent = getActivity().getIntent();
        get_username = intent.getStringExtra("name");
        textView2.setText(get_username);
    }
}