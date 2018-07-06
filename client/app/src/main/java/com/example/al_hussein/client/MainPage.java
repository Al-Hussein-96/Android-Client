package com.example.al_hussein.client;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import CommonClass.CommonProject;
import CommonClass.User;

public class MainPage extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    User user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagemain);

        fragmentMyProject myProject = new fragmentMyProject();

       List<CommonProject> commonProjects = Welcom.MyClient.getMyProject(user);
        myProject.setMyProjectlist(commonProjects);


        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new fragmentCall(),"Notifications");
        adapter.AddFragment(myProject,"My Project");
        adapter.AddFragment(new fragmentFav(),"All Project");

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
