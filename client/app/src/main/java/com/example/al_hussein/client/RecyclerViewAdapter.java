package com.example.al_hussein.client;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;

    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(aData.get(position).Author);
        holder.tv_phone.setText(aData.get(position).NameProject);
    }



    @Override
    public int getItemCount() {
        return aData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;

        public MyViewHolder(View itemView){
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.name_contact);
            tv_phone = (TextView) itemView.findViewById(R.id.phone_contact);
            img = (ImageView) itemView.findViewById(R.id.img_contact);
        }
    }


































}
