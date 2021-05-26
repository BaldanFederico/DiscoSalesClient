/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author agostinelli.luca
 */
public class GestioneProfilo {

    Socket server;
    private Vector<Room> room = new Vector();
    private String userName = System.getProperty("user.name");
    private String owner;
    private String nomeRoom, RoomID;
    File f;

    public GestioneProfilo(Socket server) {
        this.server = server;

    }

    public void gestisci() {

        utente2 u = new utente2();
        protocolli2 p = new protocolli2();

        Scanner sc = new Scanner(System.in);
        String partecipante;
        partecipante = u.getNome();
        int controllo;
        String risposta;
        try {

            PrintWriter scrittore = new PrintWriter(server.getOutputStream(), true);
            BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));
            System.out.println("sei nella gestione profilo");
            scrittore.println("chatData");
            scrittore.println(partecipante); //manda il partecipante per ricevere tutte le caratteristiche 

            salva();

            do {
                //parte di refresh 
                //controllo arrivo di messaggi
                //controllo di essere stato rimosso da una room
                //  riceviMessaggio();
                System.out.println("premi 1 per creare una nuova room");
                System.out.println("premi 2 per cercare una room");
                System.out.println("premi 3 visualizza la room  con i partecipanti");
                System.out.println("premi 4 per abbandonare la room");
                System.out.println("premi 5  scrivere un messaggio nella room");
                System.out.println("premi 6  per aggiornare lo stato dell'account e dei messaggi");
                controllo = sc.nextInt();

                switch (controllo) {
                    case 1:
                        scrittore.println(p.create());
                        System.out.println("inserisci il nome della room");
                        nomeRoom = sc.next();
                        owner = partecipante;
                        scrittore.println(owner);
                        scrittore.println(partecipante);
                        scrittore.println(nomeRoom);
                        RoomID = ricevi.readLine();
                        System.out.println("prova");
                        room.add(new Room(nomeRoom, owner, RoomID, partecipante));
                        writeRoom();
                        break;
                    case 2:
                        scrittore.println(p.Search());
                        System.out.println("inserisci l'id della room");
                        RoomID = sc.next();
                        scrittore.println(RoomID);
                        risposta = ricevi.readLine();
                        System.out.println(risposta); //questa room esiste o non
                        System.out.println("vuoi unirti(scrivi entr)");//verrà automaticamente generat
                        risposta = sc.next();
                        if (risposta.equals("entr")) {
                            scrittore.println(risposta);//manda la conferma
                            scrittore.println(partecipante);//manda il nome utente
                            nomeRoom = ricevi.readLine();//riceve il nome delle room di appartenenza
                            owner = ricevi.readLine();//riceve il nome dell'owner
                            salvaSoloUtenti();
                            //  if (checkbox == true) {
                            writeRoom();
                            //  }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < room.size(); i++) {
                            System.out.println(room.get(i).toString());
                        }
                        break;
                    case 4:
                        //manda il protocollo al server
                        //si fa rimuovere 
                        for (int i = 0; i < room.size(); i++) {
                            room.remove(i);
                        }

                        break;
                    case 5:

                        scrittore.println("chat");
                        System.out.println("inserisci il messaggio da inviare");//verra inserito al click della casella
                        risposta = sc.next();
                        scrittore.println(RoomID = room.get(0).getRoomID());
                        scrittore.println(risposta);
                        scrittore.println(partecipante);
                        risposta = ricevi.readLine();
                        System.out.println(risposta);
                }
            } while (controllo != 6);
            cancella();

        } catch (IOException ex) {
            System.out.println("hai rotto java");
        }
    }

    private void writeRoom() throws IOException {  //serve tenere traccia dei vari delle room

        f = new File("C:\\Users\\" + userName + "\\Desktop\\Room.txt");
        f.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < room.size(); i++) {
            bw.write(room.get(i).getNomeRoom() + ";");
            bw.write(room.get(i).getOwner() + ";");
            bw.write(room.get(i).getRoomID() + ";");
            bw.write(room.get(i).getPartecipante() + ";");
            bw.newLine();
            bw.flush();

        }
        bw.close();
    }

    private void cancella() {
        f = new File("C:\\Users\\" + userName + "\\Desktop\\Room.txt");
        f.delete();
    }

    private void salva() throws IOException {
        BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String risposta = ricevi.readLine();

        System.out.println("roomID" + RoomID);
        if (!risposta.equals("stop")) {
            RoomID = risposta;
            owner = ricevi.readLine();
            System.out.println("owner" + owner);
            nomeRoom = ricevi.readLine();
            System.out.println(nomeRoom);
            salvaSoloUtenti();

            //   System.out.println("RoomID"+RoomID);
            //  }
        }
    }

    private void salvaSoloUtenti() throws IOException {
        String partecipante;
        BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));

        partecipante = ricevi.readLine(); //riceve i partecipanti 

        while (!partecipante.equals("stop")) {
            System.out.println(partecipante);

            room.add(new Room(nomeRoom, owner, RoomID, partecipante));

            partecipante = ricevi.readLine();

        }
        System.out.println("esci dal ciclo partecipanti");
    }

//    private void rimuoviUtente() throws IOException {
//        PrintWriter scrittore = new PrintWriter(server.getOutputStream(), true);
//        for (int i = 0; i < room.size(); i++) {
//            System.out.println(room.get(i).toString());
//        }
//        System.out.println("seleziona l'utente da rimuovere");
//
//    }
    private void riceviMessaggio() throws IOException {
        String messaggio;

        BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));

        messaggio = ricevi.readLine();
        if (!"stop".equals(messaggio)) {
            System.out.println(messaggio);
        }

    }

    private void rimuoviRoom() {

    }
}
