package com.example.al_hussein.client;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void btnClick(View view) {


        EditText IP = findViewById(R.id.editText_ip);
        String IP_Server = IP.getText().toString();
        if (!isValidIP(IP_Server)) {
            Toast.makeText(Welcom.this, "inValid IP", Toast.LENGTH_SHORT).show();
            return;
        }
        // here start thread for Socket and Connected to Server

            MyClient = new MainClient();
            MyClient.setIP_Server(IP_Server);
            MyClient.execute();

        //     Toast.makeText(Welcom.this,"Hello Wolrd", Toast.LENGTH_SHORT).show();
        Intent Myintent = new Intent(this, Login.class);
        startActivity(Myintent);
    }

    public boolean isValidIP(String ipAddr) {
        Pattern ptn = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher mtch = ptn.matcher(ipAddr);
        return mtch.find();
    }

    // check if UserName Valid :
    // Have at least 3 character
    // First one is char

    public boolean isValidUserName(String UserName) {
        Pattern ptn = Pattern.compile("^[a-zA-Z][a-zA-Z0-9\\-._]{2,}$");
        Matcher match = ptn.matcher(UserName);
        return match.find();
    }


    //    (?=.*[0-9]) a digit must occur at least once
    //    (?=.*[a-z]) a lower case letter must occur at least once
    //    (?=.*[A-Z]) an upper case letter must occur at least once
    //    (?=.*[@#$%^&+=]) a special character must occur at least once
    //    (?=\\S+$) no whitespace allowed in the entire string
    //    .{8,} at least 8 characters

    public boolean isValidPassword(String Password) {
        Pattern ptn = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher match = ptn.matcher(Password);
        return match.find();
    }


}














