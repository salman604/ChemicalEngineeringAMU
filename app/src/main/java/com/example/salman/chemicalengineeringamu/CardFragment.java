package com.example.salman.chemicalengineeringamu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardFragment extends Fragment {

    private CardView cardView;
    private TextView titletext;
    private TextView valuetext;
    private ImageView cardicon;


    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt( "position", position );
        f.setArguments( args );


        return f;
    }


    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.item_viewpager, container, false );


        cardView = (CardView) view.findViewById( R.id.cardView );
        cardView.setMaxCardElevation( cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR );

        valuetext = (TextView) view.findViewById( R.id.value );
        titletext = (TextView) view.findViewById( R.id.title );
        cardicon = (ImageView) view.findViewById( R.id.card_icon );
        Button button = (Button) view.findViewById( R.id.button );

        int position = getArguments().getInt( "position" );
        if(position==0)
        {
            valuetext.setText( R.string.value1 );
            titletext.setText( "Salman shafi" );
            cardicon.setImageResource( R.drawable.salman );

            button.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( getActivity(), "Button in salman profile Clicked!", Toast.LENGTH_SHORT ).show();{
                        try{
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"zonicmedia13@gmail.com"});
                            email.setType("message/rfc822");
                            startActivity(Intent.createChooser(email, "Choose an Email client :"));

                        }catch (Exception e){

                        }
                    }
                }
            } );
        }
        else if(position==1)
        {
            valuetext.setText(R.string.value2  );
            titletext.setText( "Saifuddin" );
            cardicon.setImageResource( R.drawable.saifuddin );
            button.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( getActivity(), "Button in Saifuddin profile Clicked!", Toast.LENGTH_SHORT ).show();{
                        try{
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"Rahnuma.sharib@gmail.com"});
                            email.setType("message/rfc822");
                            startActivity(Intent.createChooser(email, "Choose an Email client :"));

                        }catch (Exception e){

                        }
                    }
                }
            } );
        }
       else if(position==2)
        {
            valuetext.setText( R.string.value3 );
            titletext.setText( "Asif Bhai" );
            cardicon.setImageResource( R.drawable.asif );

            button.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( getActivity(), "Button in Tanda profile Clicked!", Toast.LENGTH_SHORT ).show();{
                        try{
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"zonicmedia13@gmail.com"});
                            email.setType("message/rfc822");
                            startActivity(Intent.createChooser(email, "Choose an Email client :"));

                        }catch (Exception e){

                        }
                    }
                }
            } );
        }




//        Button button = (Button) view.findViewById( R.id.button );
//
//
//
//        button.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText( getActivity(), "Button in Card " + getArguments().getInt( "position" )
//                        + "Clicked!", Toast.LENGTH_SHORT ).show();
//            }
//        } );


        return view;
    }


    public CardView getCardView() {
        return cardView;
    }
}


