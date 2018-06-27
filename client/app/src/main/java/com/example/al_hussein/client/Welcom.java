package com.example.al_hussein.client;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Welcom extends AppCompatActivity {
    static MainClient MyClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom);

        /*because I can't use Network inside thread main*/
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // here start thread for Socket and Connected to Server
        MyClient = new MainClient();
        MyClient.start();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void btnClick(View view) {
        //Toast.makeText(Welcom.this,"Hello Wolrd",Toast.LENGTH_SHORT).show();
        Intent Myintent = new Intent(this,Login.class);
        startActivity(Myintent);
    }

}














