package com.example.medonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminshortageview extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("medicineshortage");
    private shortageAdapter shortageAdapter;
    private ArrayList<sModel> slist;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminshortageview);
        editText=findViewById(R.id.seachshortage);
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
        recyclerView = findViewById(R.id.shortagerecyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        slist = new ArrayList<>();
        shortageAdapter = new shortageAdapter(this ,slist );

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

        }
}