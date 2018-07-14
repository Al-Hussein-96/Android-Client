package com.example.al_hussein.client;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import CommonClass.CommonProject;

/**
 * Created by Al-Hussein on 7/4/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<CommonProject> aData;

    public RecyclerViewAdapter(Context mContext, List<CommonProject> aData) {
        this.mContext = mContext;
        this.aData = aData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_project,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        Log.i("REFRESH", "Done Click");

        vHolder.Follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Mohammad: " + String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_LONG);
                if(vHolder.followIsDone){
                    // send to server to un follow this project
                    String NameProject = vHolder.tv_phone.getText().toString();
                    boolean DoneUnFollow = Welcom.MyClient.sentFollow(NameProject,false);
                    if(DoneUnFollow) {
                        vHolder.Follow.setText("FOLLOW");
                        vHolder.followIsDone = false;
                        MainPage.user.delete_Follow(NameProject);
                    }
                    else {
                        Toast.makeText(mContext, "error in unfollow", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    // send to server to follow this project
                    String NameProject = vHolder.tv_phone.getText().toString();
                    boolean DoneFollow = Welcom.MyClient.sentFollow(NameProject,true);
                    if(DoneFollow) {
                        vHolder.Follow.setText("UN FOLLOW");
                        vHolder.followIsDone=true;
                        MainPage.user.add_Follow(NameProject);
                    }
                    else {
                        Toast.makeText(mContext, "error in follow", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return vHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_name.setText(aData.get(position).Author);
        holder.tv_phone.setText(aData.get(position).NameProject);

        String MyProject = aData.get(position).NameProject;

        List<String> Follow = MainPage.user.getMyFollow();
        boolean findProjectInFollow = false;



        for(String x:Follow){
            if(x.equals(MyProject)){
                findProjectInFollow=true;
                break;
            }
        }

        holder.followIsDone=findProjectInFollow;
        if(findProjectInFollow){
            holder.Follow.setText("UN FOLLOW");
        }
        else{
            holder.Follow.setText("FOLLOW");
        }

    }

    @Override
    public int getItemCount() {
        return aData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        private Button Follow;
        private boolean followIsDone=true;

        public MyViewHolder(android.view.View itemView ){
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.name_contact);
            tv_phone = (TextView) itemView.findViewById(R.id.phone_contact);
            img = (ImageView) itemView.findViewById(R.id.img_contact);
            Follow = (Button) itemView.findViewById(R.id.Follow);
        }
    }


































}
