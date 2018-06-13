package com.example.al_hussein.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainClient MyClient = new MainClient(this);
        MyClient.start();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
