/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hangmanServer;

/**
 *
 * @author Ahad
 */

import java.io.*;
import java.net.*;

public class MyServer {     
   

    public void HangServer(){

        try{

            ServerSocket ss = new ServerSocket(5001);

            while(true)
            {
                System.out.println("Server is running...");
                Socket sc = ss.accept();

                ThreadHandler th = new ThreadHandler(sc);
                th.start();
            }
            //server.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}

