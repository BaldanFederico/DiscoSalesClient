/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;

/**
 *
 * @author agostinelli.luca
 */
public class RiceviMessaggi implements Runnable {
    Socket server;

    public RiceviMessaggi(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        String messaggio;
        BufferedReader ricevi;
        try {
            ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));

            while (true) {
                messaggio = ricevi.readLine();
                System.out.println(messaggio);
            }

        } catch (IOException ex) {
            Logger.getLogger(RiceviMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
