package com.example.al_hussein.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import CommonClass.User;
import CommonCommand.Command;
import CommonCommand.GetLOGIN;
import CommonCommand.GetNewEvent;
import CommonRespone.Respone;
import CommonRespone.ResponeType;
import CommonRespone.SendNewEvent;
import EventClass.Event_AddBranch;
import EventClass.Event_AddCommit;
import EventClass.Event_AddContributor;
import EventClass.Event_Class;

import static com.example.al_hussein.client.MainClient.networkInput;
import static com.example.al_hussein.client.MainClient.networkOutput;

public class Main3Activity extends AppCompatActivity {

    User user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void Refresh(View view) {

        Respone respone = MainActivity.MyClient.RefreshEvent(user);

        if(respone ==null)return;

        if (respone.TypeRespone==ResponeType.DONE) {
            Log.i("REFRESH", "Done Refresh");
            Toast.makeText(Main3Activity.this, "Done Refresh", Toast.LENGTH_SHORT).show();
            LinearLayout L = findViewById(R.id.linearLayout);

            List<Event_Class> NewEvent = ((SendNewEvent)respone).NewEvent;

            TextView tv=new TextView(getApplicationContext());
            tv.setText("new new");

            L.addView(tv);
            Toast.makeText(Main3Activity.this, "new", Toast.LENGTH_SHORT).show();

            for(Event_Class e:NewEvent){
                if(e instanceof Event_AddContributor){
                    tv=new TextView(getApplicationContext());
                    tv.setText("new addCon");
                }
                else if(e instanceof Event_AddBranch){
                    tv=new TextView(getApplicationContext());
                    tv.setText("new addbra");
                }
                else if(e instanceof Event_AddCommit){
                    tv=new TextView(getApplicationContext());
                    tv.setText("new addCommit");
                }
                L.addView(tv);
            }

        } else {
            Toast.makeText(Main3Activity.this, "You Not Connect", Toast.LENGTH_SHORT).show();
        }
    }
}
