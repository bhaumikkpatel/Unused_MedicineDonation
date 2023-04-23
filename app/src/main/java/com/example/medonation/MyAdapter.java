package com.example.medonation;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Model> mlist;
    Context context;
    //remove btn work
    FirebaseDatabase db;
    DatabaseReference root;
    MyAdapter adapter;
    private ValueEventListener mQueryListener;
    public MyAdapter(Context context,ArrayList<Model> mlist){
        this.mlist=mlist;
        this.context=context;
        //remove btn work
      //  db = FirebaseDatabase.getInstance();
    // root = db.getReference("registermedicine");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item , parent ,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model=mlist.get(position);
        holder.uppermed.setText(model.getMedname());
        holder.medname.setText(model.getMedname());
        holder.medtype.setText(model.getMedtype());
        holder.medcondition.setText(model.getMedcondition());
        holder.medqty.setText(model.getMedqty());
        holder.medexpiry.setText(model.getMedexpiry());
        holder.med_donor.setText(model.getMed_donor());
        holder.md_phno.setText(model.getMd_phno());

        holder.btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance();
                root = db.getReference("registermedicine");
                root.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                //DataSnapshot dataSnapshot : snapshot.getChildren();
                                 if (dataSnapshot.exists()){
                                String key = dataSnapshot.getKey();
                                root = root.child(key);
                                root.removeValue();

                                     model.remove(position);
                                     notifyItemRemoved(position);
                                     notifyItemRangeChanged(position, model.size());

                                     /*int actualPosition = holder.getBindingAdapterPosition();
                                model.remove(actualPosition);
                                notifyItemRemoved(actualPosition);
                                notifyItemRangeChanged(actualPosition, model.size());*/

                                Toast.makeText(holder.medname.getContext(), "medicine has been removed " + key, Toast.LENGTH_SHORT).show();
                                return ;
                            }
                                 else
                                 {Toast.makeText(holder.medname.getContext(), "nothing to show " , Toast.LENGTH_SHORT).show();}

                        }
                      adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }

    //for search
    public void filterList(ArrayList<Model> filteredList) {
         mlist= filteredList;
        notifyDataSetChanged();
    }
//search end
    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView uppermed,medname,medtype,medcondition,medqty,medexpiry,med_donor,md_phno;
        RecyclerView recyclerView;
        Button btnremove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            uppermed=itemView.findViewById(R.id.uppermedname);
            medname=itemView.findViewById(R.id.medName);
            medtype=itemView.findViewById(R.id.tvmedtype);
            medcondition=itemView.findViewById(R.id.tvcondition);
            medqty=itemView.findViewById(R.id.tvquantity);
            medexpiry=itemView.findViewById(R.id.tvexpiry);
            med_donor=itemView.findViewById(R.id.tvdonorname);
            md_phno=itemView.findViewById(R.id.tvphno);
            btnremove=itemView.findViewById(R.id.btnremove);
            recyclerView=itemView.findViewById(R.id.recyclerviewid);

        }
    }
}
