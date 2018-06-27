package com.example.al_hussein.client;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import CommonClass.User;
import CommonCommand.Command;
import CommonCommand.GetLOGIN;
import CommonCommand.GetMyProject;
import CommonCommand.GetNewEvent;
import CommonRespone.Respone;
import CommonRespone.ResponeType;


public class MainClient extends Thread {
    public static final int PORT = 4321;
    public static InetAddress host;
    public static Socket socket;
    public static ObjectInputStream networkInput;
    public static ObjectOutputStream networkOutput;


    @Override
    public void run() {
        try {
            // host = InetAddress.getLocalHost();

            socket = new Socket("192.168.1.6", PORT);
            networkOutput = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            networkInput = new ObjectInputStream(socket.getInputStream());
            Log.i("CREATION", "Connected");
        } catch (UnknownHostException ex) {
            System.out.println("\nHost ID not foun!");
            System.exit(1);
        } catch (IOException e) {

            Log.i("CREATION", e.getMessage());
            System.exit(1);
        }

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

    public Respone RefreshEvent(User user) {

        try {
            Command command = new GetNewEvent();
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();


            if (respone.TypeRespone == ResponeType.DONE) {
                return respone;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Respone getMyProject(User user) {
        try {
            Command command = new GetMyProject();
            networkOutput.writeObject(command);
            networkOutput.flush();
            final Respone respone = (Respone) networkInput.readObject();


            if (respone.TypeRespone == ResponeType.DONE) {
                return respone;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
