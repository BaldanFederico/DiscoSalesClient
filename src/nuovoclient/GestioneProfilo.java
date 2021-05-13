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
 
    
    public GestioneProfilo(Socket server) {
        this.server = server;
       
    }

    @Override
    public void run() {
        Utente u=new Utente();
        Protocolli p = new Protocolli();
        Scanner sc = new Scanner(System.in);
        String nomeRoom, RoomID, owner, partecipante=u.getNome();
        int controllo;
        String risposta;
        try {

            PrintWriter scrittore = new PrintWriter(server.getOutputStream(), true);
            BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));

            do {
                System.out.println("premi 1 per creare una nuova room");
                
                controllo = sc.nextInt();

                switch (controllo) {
                    case 1:
                        scrittore.println(p.create());
                        System.out.println("inserisci il nome della room");
                        nomeRoom = sc.next();
                        owner = partecipante;
                        partecipante = owner;
                        scrittore.println(owner);
                        scrittore.println(partecipante);

                        break;
                    case 2:
                        scrittore.println(p.Search());
                        System.out.println("inserisci l'id della room");
                        RoomID = sc.next();
                        scrittore.println(RoomID);
                        risposta = ricevi.readLine();
                        System.out.println(risposta);
                        System.out.println("vuoi unirti(scrivi entr)");
                        risposta = sc.next();
                        scrittore.println(risposta);//manda la conferma
                        while(risposta!=null){
                        if(!risposta.equals("members")){
                            
                        }
                      } 
                        break;

                }
            } while (controllo != 3);

        } catch (IOException ex) {
            System.out.println("hai rotto java");
        }
    }

}
