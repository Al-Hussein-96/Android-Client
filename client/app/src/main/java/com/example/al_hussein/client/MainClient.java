package com.example.al_hussein.client;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import CommonClass.CommonProject;
import CommonClass.User;
import CommonCommand.Command;
import CommonCommand.FollowProject;
import CommonCommand.GetAllProject;
import CommonCommand.GetLOGIN;
import CommonCommand.GetMyFollowProject;
import CommonCommand.GetMyProject;
import CommonCommand.GetNewEvent;
import CommonRespone.Respone;
import CommonRespone.ResponeType;
import CommonRespone.SendAllProject;
import CommonRespone.SendMyFollowProjects;
import CommonRespone.SendMyProject;
import CommonRespone.SendNewEvent;
import EventClass.Event_Class;


public class MainClient extends AsyncTask<Void,Void,Void> {
    public static final int PORT = 4321;
    public static InetAddress host;
    public static Socket socket;
    public static ObjectInputStream networkInput;
    public static ObjectOutputStream networkOutput;
    public static String IP_Server = "192.168.1.111";
/*    @Override
    public void run() {
        try {
            socket = new Socket(IP_Server, PORT);
            networkOutput = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            networkInput = new ObjectInputStream(socket.getInputStream());
            Log.i("CREATION", "Connected");
        } catch (UnknownHostException ex) {
            System.out.println("\nHost ID not found!");
            System.exit(1);
        } catch (IOException e) {

            Log.i("CREATION", e.getMessage());
            System.exit(1);
        }

    }*/

    public void setIP_Server(String IP_Server){
        this.IP_Server = IP_Server;
    }

    public boolean Login(User user) {

        try {
            Command command = new GetLOGIN(user);
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();


            if (respone.TypeRespone == ResponeType.DONE) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Event_Class> RefreshEvent(User user) {

        try {
            Command command = new GetNewEvent();
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();


            if (respone.TypeRespone == ResponeType.DONE) {
                return ((SendNewEvent)respone).NewEvent;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<CommonProject> getMyProject(User user) {
        try {
            Command command = new GetMyProject();
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();


            if (respone.TypeRespone == ResponeType.DONE) {
                return ((SendMyProject)respone).getMylist();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CommonProject> getAllProject() {
        try {
            Command command = new GetAllProject();
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();
            if (respone.TypeRespone == ResponeType.DONE) {
                return ((SendAllProject)respone).getMylist();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean sentFollow(String project,boolean FollowUp){
        try {
            Command command = new FollowProject(project,FollowUp);
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();


            if (respone.TypeRespone == ResponeType.DONE) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getMyFollowProjects(){
        try {
            Command command = new GetMyFollowProject();
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();


            if (respone.TypeRespone == ResponeType.DONE) {
                return ((SendMyFollowProjects)respone).getMyFollowProjects();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            socket = new Socket(IP_Server, PORT);
            networkOutput = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            networkInput = new ObjectInputStream(socket.getInputStream());
            Log.i("CREATION", "Connected");
        } catch (UnknownHostException ex) {
            System.out.println("\nHost ID not found!");
            System.exit(1);
        } catch (IOException e) {

            Log.i("CREATION", e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
