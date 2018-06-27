package com.example.al_hussein.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import CommonClass.CommonProject;
import CommonRespone.Respone;
import CommonRespone.SendMyProject;

public class MyProject extends AppCompatActivity {
    List<CommonProject> myProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        Respone respone = (Respone) getIntent().getSerializableExtra("respone");
        myProject = ((SendMyProject)respone).getMylist();

        Log.i("list",myProject.toString());
    }
}
