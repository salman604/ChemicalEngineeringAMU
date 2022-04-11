package com.example.salman.chemicalengineeringamu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.help );


        Button contact = (Button) findViewById( R.id.Contact );
        contact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, OpenUrl.class );
//                fixture.putExtra("salman",0);
                fixture.putExtra("salman",2);
                startActivity( fixture );
            }
        } );

        Button team = (Button) findViewById( R.id.queries );
        team.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), " Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, Team.class );
                startActivity( fixture );
            }
        } );

        Button about = (Button) findViewById( R.id.about );
        about.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "about button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, OpenUrl.class );
                fixture.putExtra("salman",0);
                startActivity( fixture );
            }
        } );

        Button result = (Button) findViewById( R.id.Result );
        result.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Result button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, OpenUrl.class );
                fixture.putExtra("salman",1);
                startActivity( fixture );
            }
        } );
        Button attendence = (Button) findViewById( R.id.attendence );
        attendence.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, OpenUrl.class );
                fixture.putExtra("salman",4);
                startActivity( fixture );
            }
        } );
        Button spe = (Button) findViewById( R.id.spe );
        spe.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, OpenUrl.class );
                fixture.putExtra("salman",5);
                startActivity( fixture );
            }
        } );
        Button aiche = (Button) findViewById( R.id.aiche );
        aiche.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, OpenUrl.class );
                fixture.putExtra("salman",6);
                startActivity( fixture );
            }
        } );
        Button iiche = (Button) findViewById( R.id.iiche );
        iiche.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( Help.this, OpenUrl.class );
                fixture.putExtra("salman",7);
                startActivity( fixture );
            }
        } );
    }
}
