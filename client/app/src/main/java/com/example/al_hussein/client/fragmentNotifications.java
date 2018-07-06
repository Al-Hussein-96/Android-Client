package com.example.al_hussein.client;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import EventClass.Event_Class;

/**
 * Created by Al-Hussein on 7/3/2018.
 */

public class fragmentNotifications extends Fragment {
    List<Event_Class> event_classes;
    View v;
    private RecyclerView myrecyclerview;

    public fragmentNotifications() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notifications_fragment, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.notification_recyclerview);
        RecyclerViewAdapterNotif recyclerViewAdapter = new RecyclerViewAdapterNotif(getContext(), event_classes);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);
        return v;
    }

    public void setNotifications(List<Event_Class> event_classes) {
        this.event_classes = event_classes;
    }
}
