package com.example.salman.chemicalengineeringamu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DownloadFile extends AsyncTask<String, Void, Void> {
    private ProgressDialog dialog;
    private String firstTimeFilePath = "";
    private Activity activity;
    public DownloadFile(Activity activity){
        this.activity = activity;
        dialog = new ProgressDialog(activity);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Downloading Pdf, please wait.");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if(firstTimeFilePath.contains("pdf")) {
            Intent intent = new Intent(activity, PdfActivity.class);
            intent.putExtra("pdfFileName", firstTimeFilePath);
            activity.startActivity(intent);
        }
    }

    @Override
    protected Void doInBackground(String... strings) {
        String fileUrl = strings[0];
        MainActivity.printKr( "fileUrl " + fileUrl );
        // -> http://maven.apache.org/maven-1.x/maven.pdf
        String fileName = strings[1];
        MainActivity.printKr( "fileUrl " + fileName );
        // -> maven.pdf
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, MainActivity.folderName);
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try{
            if(pdfFile.exists() == false || pdfFile.length() == 0) {
                pdfFile.createNewFile();
                FileDownloader.downloadFile(fileUrl, pdfFile);
                firstTimeFilePath = pdfFile.getAbsolutePath();
            }else {
                Intent intent = new Intent(activity,PdfActivity.class);
                intent.putExtra( "pdfFileName", pdfFile.getAbsolutePath() );
                activity.startActivity(intent);

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
