package com.example.salman.chemicalengineeringamu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class MessageViewHolder extends RecyclerView.ViewHolder{
    public TextView authorView;
    public TextView timeView;
    public TextView bodyView;

    public MessageViewHolder(View itemView) {
        super(itemView);

        authorView = (TextView) itemView.findViewById(R.id.tv_author);
        timeView = (TextView) itemView.findViewById(R.id.tv_time);
        bodyView = (TextView) itemView.findViewById(R.id.tv_body);
    }
}

