package com.example.salman.chemicalengineeringamu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class SavedFileList extends AppCompatActivity {



    private ArrayList<String> itemName = null;
    public static int position_subject;
    private SavedFileList savedFileList;
    private String firstTimeFilePath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.second_year );
        SubjectList.position_year = getIntent().getIntExtra( "salman", 0 );
        savedFileList = this;
        itemName = getAllFiles();
        initViews();
    }
    private ArrayList<String> getAllFiles(){
        ArrayList<String> filesArr=new ArrayList<>( );
        try{
            String[] strings = null;
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, MainActivity.folderName);
            if(folder.exists()){
                strings = folder.list();
                if(strings != null && strings.length > 0){
                    for(int i = 0;i<strings.length;i++){
                        filesArr.add( strings[i] );
                    }
                }
                else{
                    return filesArr;
                }
            }else{
                return filesArr;
            }
        }catch (Exception e){

        }
        return filesArr;
    }
    private ArrayList<String> createList(ArrayList<String> arrayList){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int i = 0 ; i < arrayList.size() ; i ++){
            stringArrayList.add(arrayList.get(i));
        }
        return stringArrayList;
    }

    DataAdapter dataAdapter;
    private void initViews() {
        RecyclerView myrecycleview = (RecyclerView) findViewById( R.id.myrecycler_view );
        myrecycleview.setHasFixedSize( true );
        myrecycleview.setLayoutManager( new LinearLayoutManager( this ) );
        dataAdapter =  new DataAdapter( itemName );
        myrecycleview.setAdapter(dataAdapter);
        myrecycleview.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), myrecycleview, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                File folder = new File(extStorageDirectory, MainActivity.folderName);
                File pdfFile = new File(folder, itemName.get( position ));
                if(pdfFile.length() == 0) {
                    Toast.makeText( getApplicationContext(),"Some Error With File",Toast.LENGTH_LONG ).show();
                    pdfFile.delete();
                    itemName.remove( position );
                    dataAdapter.notifyDataSetChanged();
                }else {
                    Intent intent = new Intent(SavedFileList.this,PdfActivity.class);
                    intent.putExtra( "pdfFileName", pdfFile.getAbsolutePath() );
                    startActivity(intent);
                }



            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}











