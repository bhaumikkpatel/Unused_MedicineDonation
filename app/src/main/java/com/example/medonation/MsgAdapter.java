package com.example.medonation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MyViewHolder> {
    ArrayList<messagesModel> msglist;
    Context context;

    String check="donor";
    FirebaseDatabase db;
    DatabaseReference root;
    MsgAdapter msgadapter;
    private ValueEventListener mQueryListener;
    public MsgAdapter(Context context,ArrayList<messagesModel> msglist){
        this.msglist=msglist;
        this.context=context;
        //remove btn work
        //  db = FirebaseDatabase.getInstance();
        // root = db.getReference("registermedicine");
    }

    @NonNull
    @Override
    public MsgAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

           View v = LayoutInflater.from(context).inflate(R.layout.senderchatlayout, parent, false);
           return new MsgAdapter.MyViewHolder(v);

    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.MyViewHolder holder, int position) {

        messagesModel msgModel=msglist.get(position);
        holder.senderName.setText(msgModel.getSenderName());
        holder.chat.setText(msgModel.getChat());
        holder.Date.setText(msgModel.getDate());
        holder.receivername.setText((msgModel.getReceiver()));
        check.equals(msgModel.sender);
    }

    @Override
    public int getItemCount() {
        return msglist.size();
    }

 /*   //for search
    public void filterList(ArrayList<Model> filteredList) {
        mlist= filteredList;
        notifyDataSetChanged();
    }
    //search end*/
    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView senderName,chat,Date,receivername;
        RecyclerView recyclerView;
       String Status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            senderName=itemView.findViewById(R.id.sendername);
            chat=itemView.findViewById(R.id.sendermessage);
            Date=itemView.findViewById(R.id.timeofmessage);
            receivername=itemView.findViewById(R.id.receivername);
            recyclerView=itemView.findViewById(R.id.recyclerviewofDonor);

        }
    }
}
