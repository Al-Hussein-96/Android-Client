package com.example.al_hussein.client;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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
import CommonRespone.Respone;
import CommonRespone.ResponeType;

/**
 * Created by Al-Hussein on 6/13/2018.
 */

public class MainClient extends Thread {
    MainActivity mainActivity;
    public static InetAddress host;
    public static final int PORT = 4321;
    public static Socket socket;
    public static ObjectInputStream networkInput;
    public static ObjectOutputStream networkOutput;

    public MainClient(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        try {
           // host = InetAddress.getLocalHost();
            socket = new Socket("192.168.1.103", PORT);
            networkOutput = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            networkInput = new ObjectInputStream(socket.getInputStream());

            /*
             * here We Do Every Thing , Send To Server And Get From Server
             *
             */

            User user = new User("m", "1");

            Command command = new GetLOGIN(user);
            networkOutput.writeObject(command);
            networkOutput.flush();
            Respone respone = (Respone) networkInput.readObject();



            if (respone.TypeRespone == ResponeType.DONE) {
               // Log.i("CREATION","Done Connect");

                /*
                 * Here I want To Display Message On Layout ,
                 * without below --> Exception
                 */
                mainActivity.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        Toast.makeText(mainActivity, "Done Login", Toast.LENGTH_LONG).show();
                    }
                });
            }



        } catch (UnknownHostException ex) {
            System.out.println("\nHost ID not foun!");
            System.exit(1);
        }catch (IOException e) {

            Log.i("CREATION",e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
