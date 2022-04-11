package com.example.salman.chemicalengineeringamu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;

public class PdfActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    String TAG = "PdfActivity";
    int position = -1;
    private String pdfFilePath = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        System.out.println("Debug Live 1");
        pdfFilePath = getIntent().getStringExtra( "pdfFileName" );

        System.out.println("Debug Live pdfFilePath " + pdfFilePath);
        init();
    }

    private void init() {
        pdfView = (PDFView) findViewById(R.id.pdfView);
//        position_year = getIntent().getIntExtra(“position_year”, -1);
        displayFromSdcard();
    }

    private void displayFromSdcard() {
//        pdfFileName = MainActivity.fileList.get(position_year).getName();
        try {
            File file = new File(pdfFilePath);
            pdfFileName = file.getName();
            pdfView.fromFile(file)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onPageChange(this)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .load();
        }catch (Exception e){
            System.out.println("Debug Live Exception " + e);
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        System.out.println("Debug Live 1");
        try {
            String s = String.format(" % s % s / % s", pdfFileName, page + 1, pageCount);
            setTitle(s);
        }catch (Exception e){

        }
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), " -");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

//            Log.e(TAG, String.format(" % s % s, p % d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + " -");
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
