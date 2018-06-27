package com.example.al_hussein.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import CommonClass.User;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void btnLogin(View view) {
    /*    Intent Myintent = new Intent(this,Welcom.class);
        startActivity(Myintent);*/
        EditText userName = findViewById(R.id.editText);
        EditText password = findViewById(R.id.editText2);


        boolean ok = Welcom.MyClient.Login(new User(userName.getText().toString(), password.getText().toString()));

        if (ok) {
            Log.i("CREATION", "Done Login");
            // go To Next GUI
            Toast.makeText(Login.this, "Done Login", Toast.LENGTH_SHORT).show();

            Intent Myintent = new Intent(this, MainPage.class);
            startActivity(Myintent);
        } else {
            Toast.makeText(Login.this, "username or password is wrong", Toast.LENGTH_SHORT).show();
        }

    }
}
