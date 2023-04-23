package com.example.medonation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class shortageAdapter extends RecyclerView.Adapter<shortageAdapter.MyViewHolder> implements Filterable {
    ArrayList<sModel> slist;
    Context context;
    public shortageAdapter(Context context,ArrayList<sModel> slist){
        this.slist=slist;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sitem , parent ,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        sModel smodel=slist.get(position);
        holder.suppermedname.setText(smodel.getMedicineName());
        holder.smedname.setText(smodel.getMedicineName());
        holder.squantity.setText(smodel.getMedicineQuantity());
        holder.sarea.setText(smodel.getShortageArea());
        holder.spost.setText(smodel.getPostDate());
    }

    @Override
    public int getItemCount() {
        return slist.size();
    }
    public void filterList(ArrayList<sModel> filteredList) {
        slist = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };

    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView suppermedname,smedname,squantity,sarea,spost;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            suppermedname=itemView.findViewById(R.id.shortageuppermedname);
            smedname=itemView.findViewById(R.id.shortmedName);
            squantity=itemView.findViewById(R.id.shortquantity);
            sarea=itemView.findViewById(R.id.shortagearea);
            spost=itemView.findViewById(R.id.shortagepostdate);
        }
    }

}

