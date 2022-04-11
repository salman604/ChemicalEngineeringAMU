package com.example.salman.chemicalengineeringamu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.dataViewHolder> {

    private ArrayList<String> data;
    public DataAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public dataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent,false);
        return new dataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dataViewHolder holder, int position) {
        String title = data.get(position);
        String[] split = title.split( "#" );
        holder.txtTitle.setText(split[0]);

        if(SubjectList.position_year == 0){
            holder.imgIcon.setImageResource( R.drawable.chemicn2 );
        }
        else if(SubjectList.position_year == 1){
            holder.imgIcon.setImageResource( R.drawable.chemamu );
        }
        else if(SubjectList.position_year == 2){
            holder.imgIcon.setImageResource( R.drawable.chemical1 );
        }
        else if(SubjectList.position_year == 3){
            holder.imgIcon.setImageResource( R.drawable.pdf1 );
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class dataViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView txtTitle;
        public dataViewHolder(View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtTitle = (TextView) itemView.findViewById(R.id.txttitle);
        }
    }
}
