package com.example.al_hussein.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /*    if(!isValidUserName(userName.getText().toString())){
            Toast.makeText(Login.this, "invalid username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isValidPassword(password.getText().toString())){
            Toast.makeText(Login.this, "invalid password", Toast.LENGTH_SHORT).show();
            return;
        }*/

        boolean ok = Welcom.MyClient.Login(new User(userName.getText().toString(), password.getText().toString()));

        if (ok) {
            Log.i("CREATION", "Done Login");
            // go To Next GUI
            Toast.makeText(Login.this, "Done Login", Toast.LENGTH_SHORT).show();
            Intent Myintent = new Intent(this, MainPage.class);
            startActivity(Myintent);
            MainPage.user = new User(userName.getText().toString(), password.getText().toString());

        } else {
            Toast.makeText(Login.this, "username or password is wrong", Toast.LENGTH_SHORT).show();
        }

    }

    // check if UserName Valid :
    // Have at least 3 character
    // First one is char

    public boolean isValidUserName(String UserName){
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

    public boolean isValidPassword(String Password){
//        Pattern ptn = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Pattern ptn = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,}$");
        Matcher match = ptn.matcher(Password);
        return match.find();
    }
}
