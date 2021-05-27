/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agostinelli.luca
 */
public class riceviMessaggi implements Runnable {

    Socket server;

    public riceviMessaggi(Socket server) {
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
            Logger.getLogger(riceviMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
