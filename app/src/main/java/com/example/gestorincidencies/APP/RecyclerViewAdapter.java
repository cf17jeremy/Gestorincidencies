package com.example.gestorincidencies.APP;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestorincidencies.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<incidencia> array_incidencies;
    private ListIncidencia context;

    public RecyclerViewAdapter(ListIncidencia con, ArrayList<incidencia> arrI){
        array_incidencies = arrI;
        context = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.etiquetaNom.setText(array_incidencies.get(position).getNom());
        holder.prioritat.setText(array_incidencies.get(position).getPrioritat());
    }

    @Override
    public int getItemCount() {
        return array_incidencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView etiquetaNom,prioritat;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaNom = itemView.findViewById(R.id.itemListIncidencia);
            layout = itemView.findViewById(R.id.layout);
            prioritat = itemView.findViewById(R.id.itemListPrioritat);
        }
    }
}
