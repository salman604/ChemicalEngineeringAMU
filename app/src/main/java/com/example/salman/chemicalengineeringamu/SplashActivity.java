package com.example.salman.chemicalengineeringamu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import org.json.JSONObject;

public class SplashActivity extends AwesomeSplash {
   // @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.splash );
//        if(getJsonData( getApplicationContext() ) == null){
//            getJsonFromServer();
//        }
//
//        new Handler().postDelayed( new Runnable() {
//
//            /*
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                // Start your app main activity
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(i);
//
//                // close this activity
//                finish();
//            }
//        }, 5000);
//    }

    @Override
    public void initSplash(ConfigSplash configSplash) {

        if(getJsonData( getApplicationContext() ) == null){
            getJsonFromServer();
        }


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        //background Animation
        configSplash.setBackgroundColor( R.color.colorPrimary );
        configSplash.setAnimCircularRevealDuration( 3000 );
        configSplash.setRevealFlagX( Flags.REVEAL_LEFT );
        configSplash.setRevealFlagX( Flags.REVEAL_BOTTOM );

        //logo
        configSplash.setLogoSplash( R.drawable.appicon13);
        configSplash.setAnimLogoSplashDuration( 2000 );
        configSplash.setAnimLogoSplashTechnique( Techniques.FadeIn );



        //Title
        configSplash.setTitleSplash( "Welcome To E-Chem ");
        configSplash.setTitleTextColor( R.color.colorText );
        configSplash.setTitleTextSize( 30f );
        configSplash.setAnimTitleDuration( 2000 );
        configSplash.setAnimTitleTechnique( Techniques.FadeIn);



    }

    @Override
    public void animationsFinished() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }


    private void getJsonFromServer() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue( this );
        String url = "http://chemicalaligs.mygamesonline.org/json.txt";
//        String url = "http://chemicalaligs.mygamesonline.org/email.php";
//        String url = "";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        saveJsonData( getApplicationContext(),response );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        // Add the request to the RequestQueue.
        queue.add( stringRequest );
    }

    public static void saveJsonData(Context context, String data) {
        try {
            JSONObject jsonObject = new JSONObject( data );

            SharedPreferences sharedpreferences = context.getSharedPreferences( "ChemicalAMU", Context.MODE_PRIVATE );
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString( "jsondata", data );
            editor.commit();
        } catch (Exception e) {

        }
    }

    public static String getJsonData(Context context) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences( "ChemicalAMU", Context.MODE_PRIVATE );
            String s = sharedpreferences.getString( "jsondata", null );
            return s;
        } catch (Exception e) {

        }
        return null;
    }
}