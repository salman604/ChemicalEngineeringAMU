package com.example.salman.chemicalengineeringamu;

import android.support.v7.widget.CardView;


public interface CardAdapter {


    public final int MAX_ELEVATION_FACTOR = 3;


    float getBaseElevation();


    CardView getCardViewAt(int position);


    int getCount();
}


