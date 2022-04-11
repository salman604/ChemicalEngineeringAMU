package com.example.salman.chemicalengineeringamu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout mylay;
    public static ArrayList<MainOptionData> mainOArrayList;
    private static String jsonString = "";
    public static final String folderName = "ChemicalAMU";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


//        jsonString = readfilefromAsset();
//        String string = readfilefromAssetTest();
        if (SplashActivity.getJsonData( getApplicationContext() ) == null) {
            jsonString = readfilefromAsset();
            printKr( "readfilefromAsset" );

        } else {
            jsonString = SplashActivity.getJsonData( getApplicationContext() );
            printKr( "readfilefrom serverrrrr" );

        }
//        try {
//            JSONObject jsonObject = new JSONObject( string );
//            String value = jsonObject.getString( "key" );
//            JSONArray jsonArray = new JSONArray( value );
//
//            for(int i = 0 ; i < jsonArray.length() ; i++){
//                JSONObject jsonObject1 = jsonArray.getJSONObject( i );
//                printKr( "JSON TEST " +jsonObject1 );
//                String value1 = jsonObject1.getString( "sub" );
//                printKr( "JSON TESTvalue1 " +value1 );
//            }
////            String value1 = jsonObject.getString( "Key2" );
//            printKr( "JSON TEST " + jsonArray );
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        Button first = (Button) findViewById( R.id.button1 );
        first.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson( jsonString, "_2ndYear" );
                Toast.makeText( getApplicationContext(), "Button1 Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, SubjectList.class );
                fixture.putExtra( "salman", 0 );
                startActivity( fixture );
            }
        } );

        Button second = (Button) findViewById( R.id.button2 );
        second.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson( jsonString, "_3ndYear" );
                Toast.makeText( getApplicationContext(), "Button2 Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, SubjectList.class );
                fixture.putExtra( "salman", 1 );
                startActivity( fixture );
            }
        } );

        Button third = (Button) findViewById( R.id.button3 );
        third.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson( jsonString, "_4ndYear" );
                Toast.makeText( getApplicationContext(), "Button3 Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, SubjectList.class );
                fixture.putExtra( "salman", 2 );
                startActivity( fixture );
            }
        } );
        Button savedFiles = (Button) findViewById( R.id.button4 );
        savedFiles.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson( jsonString, "_4ndYear" );
                Toast.makeText( getApplicationContext(), "Button4 Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, SavedFileList.class );
                fixture.putExtra( "salman", 3 );
                startActivity( fixture );
            }
        } );
        Button help = (Button) findViewById( R.id.button5 );
        help.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Button5 Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, Help.class );
                startActivity( fixture );
            }
        } );
        Button nptel = (Button) findViewById( R.id.button6 );
        nptel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Button6 Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, OpenUrl.class );
                fixture.putExtra( "salman", 3 );
                startActivity( fixture );
            }
        } );
        Button quiz = (Button) findViewById( R.id.button7 );
        quiz.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, QuizList.class );
                fixture.putExtra( "List", 0 );
                startActivity( fixture );
            }
        } );
        Button gate = (Button) findViewById( R.id.button8 );
        gate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, QuizList.class );
                fixture.putExtra( "list", 1 );
                startActivity( fixture );
            }
        } );
        Button share = (Button) findViewById( R.id.button9 );
        share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share();

            }
        } );

        mylay = (RelativeLayout) findViewById( R.id.myLayout );

        Timer timer = new Timer();
        MyTimer mt = new MyTimer();

        timer.schedule( mt, 2000, 2000 );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
//            super.onBackPressed();
            exitDialog();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText( this,"clicked",Toast.LENGTH_LONG ).show();
            Intent intent = new Intent( MainActivity.this,MainActivity.class ) ;
            startActivity( intent );


            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Toast.makeText( this,"clicked",Toast.LENGTH_LONG ).show();
            Intent intent = new Intent( MainActivity.this,Team.class ) ;
            startActivity( intent );

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText( this,"clicked",Toast.LENGTH_LONG ).show();
            Intent fixture = new Intent( MainActivity.this, OpenUrl.class );
            fixture.putExtra("salman",8);
            startActivity( fixture );


        } else if (id == R.id.nav_manage) {
            Toast.makeText( this, "clicked", Toast.LENGTH_LONG ).show();
            Intent fixture = new Intent( MainActivity.this, OpenUrl.class );
            fixture.putExtra( "salman", 9 );
            startActivity( fixture );

        } else if (id == R.id.Group_msg) {
                Toast.makeText( this,"clicked",Toast.LENGTH_LONG ).show();
                Intent fixture = new Intent( MainActivity.this, ChatActivity.class );
                startActivity( fixture );


        } else if (id == R.id.nav_share) {
            String text ="download will start automatically by clicking on the given link " +
                    "  http://www.droidbin.com/p1ck1g02691d0l1hm54cp5qm1ha13";
            Intent sendIntent = new Intent();
            sendIntent.setAction( Intent.ACTION_SEND );
            sendIntent.putExtra( Intent.EXTRA_TEXT, text );
            sendIntent.setType( "text/plain" );
            startActivity( sendIntent );


        } else if (id == R.id.nav_send) {
            Toast.makeText( this, "Button in salman profile Clicked!", Toast.LENGTH_SHORT ).show();{
                try{
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"zonicmedia13@gmail.com"});
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));

                }catch (Exception e){

                }
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }



class MyTimer extends TimerTask {

    public void run() {

        runOnUiThread( new Runnable() {
            Random rand = new Random();

            public void run() {

                int Images[] = {R.drawable.aligarh1, R.drawable.amustudents, R.drawable.chemicalicn, R.drawable.chemicn2, R.drawable.chemicals1, R.drawable.aligarh2, R.drawable.aligarh3, R.drawable.amam, R.drawable.amuamu, R.drawable.amucrowd, R.drawable.kennedy_hall, R.drawable.aamu};
                mylay.setBackgroundResource( Images[getRandomNumber()] );
            }

            private int getRandomNumber() {
                // TODO Auto-generated method stub
                return new Random().nextInt( 12 );
            }
        } );
    }
}
    private void parseJson(String str,String key){
        mainOArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(str);

            String pdfURL = jsonObject.getString( "url" );
            printKr( "URL Fom json " + pdfURL );
            savePdfURL( getApplicationContext(),pdfURL );

            JSONArray yearArray = jsonObject.getJSONArray(key);
            int size = yearArray.length();
            printKr( ""+size );
            for (int i = 0 ; i < size ; i++){
                MainOptionData mainOptionData = new MainOptionData();
                JSONObject jsonObject1 = yearArray.getJSONObject(i);
                String subject = jsonObject1.getString("Sub");
                printKr( " subject "+subject );
                mainOptionData.setSubjectName(subject);

                JSONArray notesArray = jsonObject1.getJSONArray("Note");
                printKr( " notesArray " + notesArray );
                for (int j = 0 ; j < notesArray.length() ; j++){
                    mainOptionData.setNotes(notesArray.getString(j));
                }
                printKr( " 2 " );

                JSONArray examPaperArray = jsonObject1.getJSONArray("Exem");
                for (int j = 0 ; j < examPaperArray.length() ; j++){
                    mainOptionData.setExamPaper(examPaperArray.getString(j));
                }
                printKr( " 3 " );

                JSONArray materialArray = jsonObject1.getJSONArray("material");
                for (int j = 0 ; j < materialArray.length() ; j++){
                    mainOptionData.setMaterial(materialArray.getString(j));
                }
                printKr( " 4 " );


                mainOArrayList.add(mainOptionData);
            }
        }catch (Exception e){
            printKr( " Exception "+e );
            }
        }
    public static void printKr(String s){
        System.out.println( "DebugSys " + s );
    }


    private String readfilefromAsset(){
        String text = "";
        try{
            InputStream inputStream = getAssets().open("json.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            text = new String(buffer);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String readfilefromAssetTest(){
        String text = "";
        try{
            InputStream inputStream = getAssets().open("testjson.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            text = new String(buffer);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void savePdfURL(Context context, String data) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences( "ChemicalAMU", Context.MODE_PRIVATE );
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString( "savePdfURL", data );
            editor.commit();
        } catch (Exception e) {

        }
    }

    public static String getPdfURL(Context context) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences( "ChemicalAMU", Context.MODE_PRIVATE );
            String s = sharedpreferences.getString( "savePdfURL", null );
            return s;
        } catch (Exception e) {

        }
        return null;
    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        exitDialog();
//    }
//
    private void exitDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle(getString( R.string.app_name ));

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to exit.");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.appicon);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                // Write your code here to invoke YES event
//                Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
//                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        alertDialog.show();
    }
    public void Share() {
        String text ="download will start automatically by clicking on the given link " +
                "  http://www.droidbin.com/p1ck1g02691d0l1hm54cp5qm1ha13";
        Intent sendIntent = new Intent();
        sendIntent.setAction( Intent.ACTION_SEND );
        sendIntent.putExtra( Intent.EXTRA_TEXT, text );
        sendIntent.setType( "text/plain" );
        startActivity( sendIntent );

    }
}
