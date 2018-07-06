package com.example.al_hussein.client;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import CommonClass.CommonProject;




public class fragmentMyProject extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<CommonProject> listProject;
    public fragmentMyProject() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.myproject_fragment,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listProject);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

     /*   listProject = new ArrayList<>();
        CommonProject x = new CommonProject();
        x.Author = "Mohammad";
        x.NameProject = "Java";
        listProject.add(x);*/
    }

    public void setMyProjectlist(List<CommonProject> commonProjects) {
        this.listProject = commonProjects;
    }
}





















