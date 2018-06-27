package com.example.al_hussein.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import CommonClass.User;
import CommonRespone.Respone;
import CommonRespone.ResponeType;
import CommonRespone.SendMyProject;
import CommonRespone.SendNewEvent;
import EventClass.Event_AddBranch;
import EventClass.Event_AddCommit;
import EventClass.Event_AddContributor;
import EventClass.Event_Class;

public class MainPage extends AppCompatActivity {

    User user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagemain);
    }

    public void Refresh(View view) {

        Respone respone = Welcom.MyClient.RefreshEvent(user);

        if(respone ==null)return;

        if (respone.TypeRespone==ResponeType.DONE) {
            Log.i("REFRESH", "Done Refresh");
            Toast.makeText(MainPage.this, "Done Refresh", Toast.LENGTH_SHORT).show();
            LinearLayout L = findViewById(R.id.linearLayout);

            List<Event_Class> NewEvent = ((SendNewEvent)respone).NewEvent;

            TextView tv=new TextView(getApplicationContext());
            tv.setText("new new");

            L.addView(tv);
            Toast.makeText(MainPage.this, "new", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(MainPage.this, "You Not Connect", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnMyproject(View view) {
        Respone respone = Welcom.MyClient.getMyProject(user);

        if(respone != null)
        {
            Log.i("list",String.valueOf(((SendMyProject)respone).getMylist().size()));
            Intent Myintent = new Intent(this,MyProject.class);
            Myintent.putExtra("respone",respone);
            startActivity(Myintent);
        }
        else
            Log.i("list","null");


    }
}
