package com.example.al_hussein.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import CommonClass.User;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void btnLogin(View view) {
    /*    Intent Myintent = new Intent(this,MainActivity.class);
        startActivity(Myintent);*/
        EditText userName = findViewById(R.id.editText);
        EditText password = findViewById(R.id.editText2);


        boolean ok = MainActivity.MyClient.Login(new User(userName.getText().toString(), password.getText().toString()));

        if (ok) {
            Log.i("CREATION", "Done Login");
            // go To Next GUI
            Toast.makeText(Main2Activity.this, "Done Login", Toast.LENGTH_SHORT).show();

            Intent Myintent = new Intent(this, Main3Activity.class);
            startActivity(Myintent);
        } else {
            Toast.makeText(Main2Activity.this, "username or password is wrong", Toast.LENGTH_SHORT).show();
        }

    }
}
