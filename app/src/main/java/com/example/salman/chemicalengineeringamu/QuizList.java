package com.example.salman.chemicalengineeringamu;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class QuizList extends AppCompatActivity {
    // Array of strings...
    String[] mobileArray = {"Youth for Work","IndiaBix","ExamTimeQuiz","Engineering interview Question",
            "Basicversity", "Chemical Formulas Pdf","Chemical Engineering Calculation Pdf2"};
    String[] mobileArray1 = {"GATE 2018 Question Papers ","GATE 2017 Question Papers ","GATE 2016 Question Papers ","GATE 2015 Question Papers ","GATE 2014 Question Papers "};
    QuizList quizList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizlist);
        quizList =  this;
        int value = getIntent().getIntExtra( "list", 0 );
        if(value==0) {
            ArrayAdapter adapter = new ArrayAdapter<String>( this, R.layout.quizlistitem, R.id.name, mobileArray );

            ListView listView = (ListView) findViewById( R.id.mobile_list );
            listView.setAdapter( adapter );
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 5) {
                        openPdf( "https://www.aiche.org/sites/default/files/docs/overview-page/9865-14aichestudenthandbookfinalpdf.pdf",
                                "9865-14aichestudenthandbookfinalpdf.pdf" );
                    } else if (position == 6) {
                        String pdfURL = "http://www.pacificcrn.com/Upload/file/201612/12/20161212221600_64552.pdf";
                        String string = "20161212221600_64552.pdf";
                        openPdf( pdfURL, string );
                    } else {
                        Intent fixture = new Intent( QuizList.this, OpenUrl.class );
                        fixture.putExtra( "mobilearray", position );
                        fixture.putExtra( "caller", 1 );
                        startActivity( fixture );
                    }
                }
            } );
        }
        if(value==1) {
            ArrayAdapter adapter = new ArrayAdapter<String>( this, R.layout.quizlistitem, R.id.name, mobileArray1 );

            ListView listView = (ListView) findViewById( R.id.mobile_list );
            listView.setAdapter( adapter );
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        Intent fixture = new Intent( QuizList.this, OpenUrl.class );
                        fixture.putExtra( "mobilearray", 5 );
                        fixture.putExtra( "caller", 1 );
                        startActivity( fixture );

                    } else if (position == 1) {
                        String pdfURL = "http://www.gate.iitg.ac.in/2015questions/CH_GATE_2015.pdf";
                        String string = "CH_GATE_2015.pdf";
                        openPdf( pdfURL, string );
                    }
                    else if (position == 2) {
                        String pdfURL = "http://www.gate.iitg.ac.in/2016questions/S2_CH.pdf";
                        String string = "S2_CH.pdf";
                        openPdf( pdfURL, string );
                    }
                    else if (position == 3) {
                        String pdfURL = "http://www.gate.iitg.ac.in/2015questions/CH_GATE_2015.pdf";
                        String string = "CH_GATE_2015.pdf";
                        openPdf( pdfURL, string );
                    }else {
                        Intent fixture = new Intent( QuizList.this, OpenUrl.class );
                        fixture.putExtra( "mobilearray", position );
                        fixture.putExtra( "caller", 1 );
                        startActivity( fixture );
                    }
                }
            } );
        }
    }
    private void openPdf(String pdfURL,String string){
        try {
            new DownloadFile( quizList ).execute( pdfURL, string );
        }catch (Exception e){

        }
    }
}
