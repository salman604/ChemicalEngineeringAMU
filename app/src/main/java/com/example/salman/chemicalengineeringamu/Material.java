package com.example.salman.chemicalengineeringamu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class Material extends AppCompatActivity {
//    String[] pdfName = null;

    private Material material;
    public static int position_year;
    public static int position_subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.material );

        material = this;
        position_year = getIntent().getIntExtra( "position_year", 0 );
        position_subject = getIntent().getIntExtra( "subject_position", 0 );

//        if(SubjectList.position_year == 0){
//            pdfName = SubjectList.language[position_year].split( "#" );
//        }
//        else if(SubjectList.position_year == 1){
//            pdfName = SubjectList.language1[position_year].split( "#" );
//        }
//        else if(SubjectList.position_year == 2){
//            pdfName = SubjectList.language1[position_year].split( "#" );
//        }

        Button classNote = findViewById( R.id.button1 );
        classNote.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Material.this,PaperList.class );
                intent.putExtra( "position_year", position_year );
                intent.putExtra( "subject_position", position_subject );
                intent.putExtra( "button_position", 0 );
                startActivity( intent );

            }
        } );
        Button examPaper = findViewById( R.id.button2 );
        examPaper.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Material.this,PaperList.class );
                intent.putExtra( "position_year", position_year );
                intent.putExtra( "subject_position", position_subject );
                intent.putExtra( "button_position", 1 );
                startActivity( intent );
            }
        } );
        Button material = findViewById( R.id.button3 );
        material.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Material.this,PaperList.class );
                intent.putExtra( "position_year", position_year );
                intent.putExtra( "subject_position", position_subject );
                intent.putExtra( "button_position", 2 );
                startActivity( intent );
            }
        } );
    }



}
