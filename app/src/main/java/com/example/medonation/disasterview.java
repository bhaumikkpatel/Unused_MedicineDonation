package com.example.medonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class disasterview extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("disaster_alert");
    private disasterAdapter disasterAdapter;
    private ArrayList<dModel> dlist;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disasterview);
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
                    ArrayList<dModel> filteredlist=new ArrayList<>();
                    for (dModel item : dlist) {
                        if (item.getDsmedicineName().toLowerCase().contains(text.toLowerCase())) {
                            filteredlist.add(item);
                        }
                    }

                    disasterAdapter.filterList(filteredlist);
                }
            });
            recyclerView = findViewById(R.id.shortagerecyclerviewid);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            dlist = new ArrayList<>();
            disasterAdapter = new disasterAdapter(this ,dlist );

            recyclerView.setAdapter(disasterAdapter);
            root.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        dModel dmodel = dataSnapshot.getValue(dModel.class);
                        dlist.add(dmodel);

                    }
                    disasterAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
