package com.example.al_hussein.client;

/**
 * Created by Al-Hussein on 7/4/2018.
 */

public class ProjectView {
    String NameProject;
    String Owner;

    public ProjectView(String nameProject, String owner) {
        NameProject = nameProject;
        Owner = owner;
    }

    public String getNameProject() {
        return NameProject;
    }

    public void setNameProject(String nameProject) {
        NameProject = nameProject;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }
}
