/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class GestioneProfilo implements Runnable {

    Socket server;
    String nomeU;

    public GestioneProfilo(Socket server, String nomeU) {
        this.server = server;
        this.nomeU = nomeU;
    }

    @Override
    public void run() {
        protocolli p = new protocolli();
        Scanner sc = new Scanner(System.in);
        String nomeRoom, owner, RoomID, partecipante;
        int controllo;
        String risposta;
        try {

            PrintWriter scrittore = new PrintWriter(server.getOutputStream(), true);
            BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));
            
            
            do {
               System.out.println("premi 1 per creare una nuova room");
                controllo=sc.nextInt();
                
                switch (controllo) {
                    case 1:
                        scrittore.println(p.create());
                        nomeRoom=sc.next();
                        owner = nomeU;
                        partecipante = owner;
                        scrittore.println(RoomID);
                        scrittore.println(owner);
                        scrittore.println(partecipante);
                        
                        break;

                }
            } while ();

        } catch (IOException ex) {
            System.out.println("hai rotto java");
        }
    }

}
