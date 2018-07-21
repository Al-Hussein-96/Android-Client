package com.example.al_hussein.client;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import CommonClass.CommonProject;
import EventClass.Event_AddBranch;
import EventClass.Event_AddCommit;
import EventClass.Event_AddContributor;
import EventClass.Event_Class;

/**
 * Created by Al-Hussein on 7/4/2018.
 */

public class RecyclerViewAdapterNotif extends RecyclerView.Adapter<RecyclerViewAdapterNotif.MyViewHolder> {
    Context mContext;
    List<Event_Class> aData;
    Dialog myDialog;

    public RecyclerViewAdapterNotif(Context mContext, List<Event_Class> aData) {
        this.mContext = mContext;
        this.aData = aData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_notifications, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_notification);


//        Toast.makeText(mContext, , Toast.LENGTH_LONG);



        vHolder.item_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss");

                TextView ProjectName = (TextView) myDialog.findViewById(R.id.projectName);
                TextView date = (TextView) myDialog.findViewById(R.id.date);
                TextView Type = (TextView) myDialog.findViewById(R.id.Type);
                TextView textDetails = (TextView) myDialog.findViewById(R.id.textDetails);
                TextView Details = (TextView) myDialog.findViewById(R.id.details);

                Event_Class event = vHolder.MyEvent;
                if (event instanceof Event_AddCommit){
                    textDetails.setText("Details :");
                    Event_AddCommit e = (Event_AddCommit)event ;
                    Details.setText(e.NewCommit.Detail);
                }
                if (event instanceof Event_AddBranch){
                    textDetails.setText("Branch Name :");
                    Event_AddBranch e = (Event_AddBranch)event ;
                    Details.setText(e.NameBranch);
                }
                if (event instanceof Event_AddContributor){
                    textDetails.setText("Contributor Name :");
                    Event_AddContributor e = (Event_AddContributor)event ;
                    Details.setText(e.NameContributor);
                }
                ProjectName.setText(vHolder.ProjectName.getText());
                date.setText(ft.format(vHolder.date));
                Type.setText(vHolder.Author +" " + vHolder.Type.getText());
                myDialog.show();
                Toast.makeText(mContext, "Mohammad: " + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_LONG);
            }
        });
        return vHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Event_Class event = aData.get(position);

        String Type = "No Event";

        if (event instanceof Event_AddCommit) Type = "Add Commit";
        if (event instanceof Event_AddBranch) Type = "Add Branch";
        if (event instanceof Event_AddContributor) Type = "Add Contributor";

        holder.Type.setText(Type);
        holder.ProjectName.setText(event.ProjectName);
        holder.Author = event.Author;
        holder.date = event.date;
        holder.MyEvent = event;
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item_notification;
        private TextView Type;
        private TextView ProjectName;
        private String Author;
        private Date date;
        private ImageView img;
        private Event_Class MyEvent;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_notification = (LinearLayout) itemView.findViewById(R.id.notification_item);
            Type = (TextView) itemView.findViewById(R.id.name_contact);
            ProjectName = (TextView) itemView.findViewById(R.id.phone_contact);
            img = (ImageView) itemView.findViewById(R.id.img_contact);
        }
    }

}
