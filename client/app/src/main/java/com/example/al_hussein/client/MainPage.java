package com.example.al_hussein.client;

import android.app.Notification;
import android.app.NotificationManager;
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

import java.util.List;

import CommonClass.CommonProject;
import CommonClass.User;
import EventClass.Event_AddBranch;
import EventClass.Event_AddCommit;
import EventClass.Event_AddContributor;
import EventClass.Event_Class;

public class MainPage extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    public static User user;
    private List<Event_Class> MyList;
    private boolean isRun = false;

    private boolean haveEvent(Event_Class e) {
        for (Event_Class temp : MyList) {
            if (temp instanceof Event_AddContributor) {
                if (!(e instanceof Event_AddContributor)) {
                    continue;
                }
            } else if (temp instanceof Event_AddBranch) {
                if (!(e instanceof Event_AddBranch)) {
                    continue;
                }
            } else if (temp instanceof Event_AddCommit) {
                if (!(e instanceof Event_AddCommit)) {
                    continue;
                }
            }
            if (e.Author.equals(temp.Author) && e.ProjectName.equals(temp.ProjectName) && e.date.equals(temp.date))
                return true;
        }
        return false;
    }


    public void Refresh() {
        List<String> MyFollowProjects = Welcom.MyClient.getMyFollowProjects();
        for (String projectName : MyFollowProjects) {
            MainPage.user.add_Follow(projectName);
        }

        List<Event_Class> event_classes = Welcom.MyClient.RefreshEvent(user);

        // here for notification
        int id = 0;
        for (Event_Class u : event_classes) {
            if (haveEvent(u)) continue;
            String Type = "";

            if (u instanceof Event_AddContributor) {
                Type = "Add Contributor";
            } else if (u instanceof Event_AddBranch) {
                Type = "Add Branch";
            } else if (u instanceof Event_AddCommit) {
                Type = "Add Commit";
            }

            Notification n = new Notification.Builder(this)
                    .setContentTitle(Type)
                    .setContentText(u.Author)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true).build();
            n.defaults |= Notification.DEFAULT_SOUND;

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(id, n);
            id++;
        }

        Fragment frg = null;
        frg = adapter.getFragment(0);
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ((fragmentNotifications) frg).setNotifications(event_classes);
        ft.attach(frg);


        List<CommonProject> commonProjects = Welcom.MyClient.getMyProject(user);
        frg = adapter.getFragment(1);

        if (frg != null) {
            ft.detach(frg);
            ((fragmentMyProject) frg).setMyProjectlist(commonProjects);
            ft.attach(frg);
        }

        //       ft.commit();

        List<CommonProject> commonProjectsAll = Welcom.MyClient.getAllProject();
        frg = adapter.getFragment(2);
        if (frg != null) {
            ft.detach(frg);
            ((fragmentMyProject) frg).setMyProjectlist(commonProjectsAll);
            ft.attach(frg);
        }

        if (isRun) ft.commit();

        MyList = event_classes;

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
                handler.postDelayed(this, 5000);
            }
        }, 5000);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
                swipeRefreshLayout.setRefreshing(false);
                Log.i("Refresh", "Mohammad");
            }
        });


        List<String> MyFollowProjects = Welcom.MyClient.getMyFollowProjects();
        if (MyFollowProjects != null)
            for (String projectName : MyFollowProjects) {
                MainPage.user.add_Follow(projectName);
            }
        else Toast.makeText(MainPage.this, "Doooooo", Toast.LENGTH_SHORT).show();

        /// Notifications
        fragmentNotifications notifications = new fragmentNotifications();
        List<Event_Class> event_classes = Welcom.MyClient.RefreshEvent(user);
        notifications.setNotifications(event_classes);
        MyList = event_classes;

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

    @Override
    protected void onStop() {
        super.onStop();
        isRun = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRun=false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRun = true;
    }
}