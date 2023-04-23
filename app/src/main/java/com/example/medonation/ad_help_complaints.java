package com.example.medonation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ad_help_complaints extends Fragment {
    EditText editTextmsg,editTextto;
    ImageButton buttonsend;
    String get_sendername;
    private String enteredMsg,sendto;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    //

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    //recycler
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("help_complaints_feedback");
    private MsgAdapter msgAdapter;
    private ArrayList<messagesModel> msglist;
    //recycler
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ad_help_complaints, container, false);
        editTextmsg = view.findViewById(R.id.getmessage);
        editTextto=view.findViewById(R.id.getName);
        buttonsend = view.findViewById(R.id.imageviewsendmessage);
        //recycler
        recyclerView = view.findViewById(R.id.recyclerviewofDonor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        msglist = new ArrayList<>();
        msgAdapter = new MsgAdapter(getContext() ,msglist );

        recyclerView.setAdapter(msgAdapter);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    messagesModel msgmodel = dataSnapshot.getValue(messagesModel.class);
                    msglist.add(msgmodel);

                }
                msgAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //recycler

        reference = FirebaseDatabase.getInstance().getReference("users");

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootnode = FirebaseDatabase.getInstance();
                reference = FirebaseDatabase.getInstance().getReference("help_complaints_feedback");
                enteredMsg = editTextmsg.getText().toString();
                sendto=editTextto.getText().toString();
                if (enteredMsg.isEmpty()) {
                    Toast.makeText(getContext(), "Enter message first", Toast.LENGTH_SHORT).show();
                } else {
                    get_sendername="Admin";
                    String senderName = get_sendername;
                    String chat = enteredMsg.toString();
                    String status= "Admin";
                    String receiver=sendto.toString();
                    //
                    calendar = Calendar.getInstance();
                    simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm");
                    String Date = simpleDateFormat.format(calendar.getTime());

                    messagesModel messagesmodel = new messagesModel(senderName, chat, Date,status,receiver);
                    String key = reference.push().getKey();
                    reference.child(key).setValue(messagesmodel);
                    Toast.makeText(getActivity().getApplicationContext(), "Your message has been sent...", Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;
    }
}