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

import java.util.ArrayList;

public class disasterAdapter extends RecyclerView.Adapter<disasterAdapter.MyViewHolder> implements Filterable {
    ArrayList<dModel> dlist;
    Context context;
    public disasterAdapter(Context context,ArrayList<dModel> dlist){
        this.dlist=dlist;
        this.context=context;
    }

    @NonNull
    @Override
    public disasterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ditem , parent ,false);
        return new disasterAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull disasterAdapter.MyViewHolder holder, int position) {

        dModel dmodel=dlist.get(position);
        holder.suppermedname.setText(dmodel.getDsmedicineName());
        holder.smedname.setText(dmodel.getDsmedicineName());
        holder.squantity.setText(dmodel.getDsmedicineQuantity());
        holder.dtype.setText(dmodel.getDstype());
        holder.sarea.setText(dmodel.getDsArea());
        holder.spost.setText(dmodel.getDspostDate());
    }

    @Override
    public int getItemCount() {
        return dlist.size();
    }
    public void filterList(ArrayList<dModel> filteredList) {
        dlist = filteredList;
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
        TextView suppermedname,smedname,squantity,dtype,sarea,spost;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            suppermedname=itemView.findViewById(R.id.shortageuppermedname);
            smedname=itemView.findViewById(R.id.shortmedName);
            squantity=itemView.findViewById(R.id.shortquantity);
            sarea=itemView.findViewById(R.id.shortagearea);
            spost=itemView.findViewById(R.id.shortagepostdate);
            dtype=itemView.findViewById(R.id.disastertype);
        }
    }

}
