package com.example.salman.chemicalengineeringamu;

import android.app.MediaRouteButton;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class OpenUrl extends AppCompatActivity {

    WebView webview;
    ProgressBar pbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.openurl);
        pbar = (ProgressBar) findViewById( R.id.progressBar1 );

        int caller = getIntent().getIntExtra( "caller", 0 );
        if (caller == 1)
        {

            int value = getIntent().getIntExtra( "mobilearray", 0 );
            if(value==0) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://www.prep.youth4work.com/Practice-_Test_s/Chemical-Engineering-Test" );
            }
            if(value==1) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://www.indiabix.com/online-test/chemical-engineering-test/" );
            }
            if(value==2) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "http://examtimequiz.com/multiple-choice-questions-chemical-engineering/" );
            }
            if(value==3) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "http://engineeringinterviewquestions.com/chemical-engineering-online-tests-quiz/" );
            }
            if(value==4) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://basicversity.com/quiz/chemical-engineering" );
            }
            if(value==5) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://drive.google.com/file/d/1uMfSxnV2QdaHg-CkjcFeTr1TaTtH5Zvl/view" );
            }


//            if(value==5) {
//                WebView webview = findViewById( R.id.openurl );
//                pbar.setVisibility( View.VISIBLE );
//                webview.setWebViewClient( new WebViewClient() );
//                webview.getSettings().setJavaScriptEnabled( true );
//                webview.loadUrl( "https://www.aiche.org/sites/default/files/docs/overview-page/9865-14aichestudenthandbookfinalpdf.pdf" );
//            }

        }


        else {
            int position = getIntent().getIntExtra( "salman", 0 );
            if (position == 0) {

                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://www.amu.ac.in/departmentpage.jsp?did=29" );

            } else if (position == 1) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "http://ctengg.amu.ac.in/web/index.php" );
            } else if (position == 2) {
                TextView textview = (TextView) findViewById( R.id.textview1 );
                textview.setText( R.string.textview1 );
            } else if (position == 3) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://www.youtube.com/results?search_query=nptel+videos+for+chemical+engineering" );
            } else if (position == 4) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "http://ctengg.amu.ac.in/web/attendance.php?p=be" );
            } else if (position == 5) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://www.spe.org/en/" );
            } else if (position == 6) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://www.aiche.org/" );
            } else if (position == 7) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "http://www.iiche.org.in/" );
            }
            else if (position == 8) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "https://www.amu.ac.in/index.jsp" );
            }
            else if (position == 9) {
                WebView webview = findViewById( R.id.openurl );
                pbar.setVisibility( View.VISIBLE );
                webview.setWebViewClient( new WebViewClient() );
                webview.getSettings().setJavaScriptEnabled( true );
                webview.loadUrl( "http://amunotes.com/exam-papers/" );
            }
        }


    }

    class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String value, Bitmap favicon) {

            // TODO Auto-generated method stub
            super.onPageStarted( view, value, favicon );
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String value) {

            // TODO Auto-generated method stub
            view.loadUrl( value );
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String value) {

            // TODO Auto-generated method stub

            super.onPageFinished( view, value );
            pbar.setVisibility( View.GONE );
//
//        }

        }
    }
}



