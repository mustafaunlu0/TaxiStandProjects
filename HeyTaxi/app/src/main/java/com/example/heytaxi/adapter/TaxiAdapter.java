package com.example.heytaxi.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heytaxi.databinding.RecyclerRowBinding;
import com.example.heytaxi.model.Taxi;
import com.example.heytaxi.view.MapsActivity;

import java.util.List;

public class TaxiAdapter extends RecyclerView.Adapter<TaxiAdapter.TaxiHolder> {

    List<Taxi> taxiList;

    public TaxiAdapter(List<Taxi> taxis) {
        this.taxiList=taxis;
    }

    @NonNull
    @Override
    public TaxiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TaxiHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaxiHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recyclerRowBinding.recyclerViewTextView.setText(taxiList.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), MapsActivity.class);
                intent.putExtra("info","old");
                intent.putExtra("taxi",taxiList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taxiList.size();
    }

    public class TaxiHolder extends RecyclerView.ViewHolder{

        RecyclerRowBinding recyclerRowBinding;
        public TaxiHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding=recyclerRowBinding;
        }
    }
}
