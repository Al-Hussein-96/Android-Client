package com.example.al_hussein.client;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import CommonClass.CommonProject;
import CommonClass.User;
import CommonCommand.Command;
import EventClass.Event_AddCommit;
import EventClass.Event_Class;

public class MainPage extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    public static User user;

    public void Refresh(){
        List<String> MyFollowProjects = Welcom.MyClient.getMyFollowProjects();
        for(String projectName:MyFollowProjects){
            MainPage.user.add_Follow(projectName);
        }

        List<Event_Class> event_classes = Welcom.MyClient.RefreshEvent(user);
        Fragment frg = null;
        frg = adapter.getFragment(0);
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ((fragmentNotifications)frg).setNotifications(event_classes);
        ft.attach(frg);


        List<CommonProject> commonProjects = Welcom.MyClient.getMyProject(user);
        frg = adapter.getFragment(1);
        ft.detach(frg);
        ((fragmentMyProject)frg).setMyProjectlist(commonProjects);
        ft.attach(frg);
 //       ft.commit();

        List<CommonProject> commonProjectsAll = Welcom.MyClient.getAllProject();
        frg = adapter.getFragment(2);
        ft.detach(frg);
        ((fragmentMyProject)frg).setMyProjectlist(commonProjectsAll);
        ft.attach(frg);
        ft.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagemain);

        // her Timer to refresh the data every 5 sec
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //do Refresh
                Refresh();
   //             Toast.makeText(MainPage.this, "looool", Toast.LENGTH_SHORT).show();
                handler.postDelayed( this, 5000 );
            }
        }, 5000);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
                swipeRefreshLayout.setRefreshing(false);
                Log.i("Refresh","Mohammad");
            }
        });


        List<String> MyFollowProjects = Welcom.MyClient.getMyFollowProjects();
        if(MyFollowProjects!=null)
        for(String projectName:MyFollowProjects){
            MainPage.user.add_Follow(projectName);
        }
        else Toast.makeText(MainPage.this, "Doooooo", Toast.LENGTH_SHORT).show();

        /// Notifications
        fragmentNotifications notifications = new fragmentNotifications();
        List<Event_Class> event_classes = Welcom.MyClient.RefreshEvent(user);
        notifications.setNotifications(event_classes);

        /// my project
        fragmentMyProject myProject = new fragmentMyProject();
        List<CommonProject> commonProjects = Welcom.MyClient.getMyProject(user);
        myProject.setMyProjectlist(commonProjects);

        /// all project
        fragmentMyProject allProject = new fragmentMyProject();
        List<CommonProject> commonProjectsAll = Welcom.MyClient.getAllProject();
        allProject.setMyProjectlist(commonProjectsAll);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(notifications, "Notifications");
        adapter.AddFragment(myProject, "My Project");
        adapter.AddFragment(allProject, "All Project");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }



/*    public void Refresh(View view) {

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


    }*/
}
