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

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class PaperList extends AppCompatActivity {



    private ArrayList<String> itemName = null;
    public static int position_year;
    public static int position_subject;
    private PaperList paperList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.second_year );
        paperList = this;

        position_year = getIntent().getIntExtra( "position_year", 0 );
        position_subject = getIntent().getIntExtra( "subject_position", 0 );
        int button_position = getIntent().getIntExtra( "button_position", 0 );

//        if(position_year == 0){
            if(button_position == 0) {
                itemName = createList(MainActivity.mainOArrayList.get(position_subject).getNotes());
            }
            else  if(button_position == 1) {
                itemName = createList(MainActivity.mainOArrayList.get(position_subject).getExamPaper());
            }
            else  if(button_position == 2) {
                itemName = createList(MainActivity.mainOArrayList.get(position_subject).getMaterial());
            }
//        }

        initViews();
    }

    private ArrayList<String> createList(ArrayList<String> arrayList){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int i = 0 ; i < arrayList.size() ; i ++){
            stringArrayList.add(arrayList.get(i));
        }
        return stringArrayList;
    }


    private void initViews() {
        RecyclerView myrecycleview = (RecyclerView) findViewById( R.id.myrecycler_view );
        myrecycleview.setHasFixedSize( true );
        myrecycleview.setLayoutManager( new LinearLayoutManager( this ) );
        myrecycleview.setAdapter( new DataAdapter( itemName ) );
        myrecycleview.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), myrecycleview, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String string = "";
                try {
                    string = URLEncoder.encode( itemName.get(position) , "UTF-8" )+".pdf";
                }catch (Exception e){

                }
//                string = "5903.pdf";
                String pdfURL = MainActivity.getPdfURL( getApplicationContext() )+string;
//                pdfURL = "https://www.aiche.org/sites/default/files/docs/overview-page/9865-14aichestudenthandbookfinalpdf.pdf";
//                string = "9865-14aichestudenthandbookfinalpdf.pdf";
                MainActivity.printKr( "URL " + pdfURL );
                new DownloadFile(paperList).execute(pdfURL,string);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

//    private class DownloadFile extends AsyncTask<String, Void, Void> {
//        private ProgressDialog dialog;
//        public DownloadFile(Activity activity){
//            dialog = new ProgressDialog(activity);
//        }
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialog.setMessage("Loading, please wait.");
//            dialog.setCancelable(false);
//            dialog.show();
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            dialog.dismiss();
//            if(firstTimeFilePath.contains("pdf")) {
//                Intent intent = new Intent(paperList, PdfActivity.class);
//                intent.putExtra("pdfFileName", firstTimeFilePath);
//                startActivity(intent);
//            }
//        }
//
//        @Override
//        protected Void doInBackground(String... strings) {
//            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
//            String fileName = strings[1];  // -> maven.pdf
//            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//            File folder = new File(extStorageDirectory, MainActivity.folderName);
//            folder.mkdir();
//
//            File pdfFile = new File(folder, fileName);
//
//            try{
//                if(pdfFile.exists() == false || pdfFile.length() == 0) {
//                    pdfFile.createNewFile();
//                    FileDownloader.downloadFile(fileUrl, pdfFile);
//                    firstTimeFilePath = pdfFile.getAbsolutePath();
//                }else {
//                    Intent intent = new Intent(PaperList.this,PdfActivity.class);
//                    intent.putExtra( "pdfFileName", pdfFile.getAbsolutePath() );
//                    startActivity(intent);
//
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//    }
}











