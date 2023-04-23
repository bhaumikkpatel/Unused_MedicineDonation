package com.example.medonation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class disasteralert_donor extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("disaster_alert");
    private disasterAdapter disasterAdapter;
    private ArrayList<dModel> dlist;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_disasteralert_donor, container, false);
        editText=view.findViewById(R.id.seachshortage);
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
        recyclerView = view.findViewById(R.id.shortagerecyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dlist = new ArrayList<>();
        disasterAdapter = new disasterAdapter(getContext() ,dlist );

        recyclerView.setAdapter(disasterAdapter);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //notification();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    dModel dmodel = dataSnapshot.getValue(dModel.class);
                    dlist.add(dmodel);
                }
                disasterAdapter.notifyDataSetChanged();
                notification();
            }

           private void notification() {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    NotificationChannel channel=new NotificationChannel("n","n",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager= (NotificationManager)getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                    manager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext(),"n")
                        .setContentText("code sphere")
                        .setSmallIcon(R.drawable.ic_disaster)
                        .setAutoCancel(true)
                        .setContentText("here is some medicine needed in case of disaster disaster");
                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(getContext());
                managerCompat.notify(999,builder.build());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}