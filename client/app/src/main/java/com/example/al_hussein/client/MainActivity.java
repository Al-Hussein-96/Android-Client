package com.example.al_hussein.client;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    static MainClient MyClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //Toast.makeText(MainActivity.this,"Hello Wolrd",Toast.LENGTH_SHORT).show();
        Intent Myintent = new Intent(this,Main2Activity.class);
        startActivity(Myintent);
    }

}














