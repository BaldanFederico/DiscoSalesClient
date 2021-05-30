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
 * La classe è il gestore del profilo client e di tutte le chatroom che sono collegate al profilo di ogni singolo utente.
 * Inoltre comunica con il server dopo che l'utente ha avuto accesso all'applicazione.
 * @author DiscoSales
 */
public class GestioneProfilo {

    Socket server;
    private Vector<Room> room = new Vector();
    private String userName = System.getProperty("user.name");
    private String owner;
    private String nomeRoom, RoomID;
    File f;
/**
 * Il costruttore istanzia l'oggetto GestioneProfilo che mette al suo interno la socket
 * @param server la socket che comunica con il server 
 */
    public GestioneProfilo(Socket server) {
        this.server = server;

    }
/**
 * Il metodo gestisce le comunicazioni con il server attraverso un protoccolo proprietario a seconda dei comandi inseriti nella console
 */
    public void gestisci() {

        utente u = new utente();
        Protocollo p = new Protocollo();
        //    Thread Gm = new Thread(new riceviMessaggi(server));
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
            scrittore.println(partecipante); //Manda il partecipante per ricevere tutte le caratteristiche 
            salva();

            do {

                //Parte di refresh 
                //Controllo arrivo di messaggi
                //Controllo di essere stato rimosso da una room
                //RiceviMessaggio();
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
                        System.out.println(risposta); //Questa room esiste o non
                        System.out.println("vuoi unirti(scrivi entr)");//Verrà automaticamente generat
                        risposta = sc.next();
                        if (risposta.equals("entr")) {
                            scrittore.println(risposta);//Manda la conferma
                            scrittore.println(partecipante);//Manda il nome utente
                            nomeRoom = ricevi.readLine();//Riceve il nome delle room di appartenenza
                            owner = ricevi.readLine();//Riceve il nome dell'owner
                            salvaSoloUtenti();
                            //  if (checkbox == true) {
                            writeRoom();

                        }
                        break;
                    case 3:
                        for (int i = 0; i < room.size(); i++) {
                            System.out.println(room.get(i).toString());
                        }
                        break;
                    case 4:
                        scrittore.println("remove"); //Manda il protocollo al server
                        scrittore.println(partecipante);
                        for (int i = 0; i < room.size(); i++) {
                            room.remove(i);
                        }

                        break;
                    case 5:

                        scrittore.println("chat");
                        System.out.println("inserisci il messaggio da inviare");//Verrà inserito al click della casella
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
/**
 *  Serve tenere traccia delle varie delle room
 * @throws IOException  Eccezione che viene gestita tramite ,appunto, il "throws IOException"
 * 
 */
    private void writeRoom() throws IOException { 

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
/**
 * Il metodo cancella i file delle chatroom
 */
    private void cancella() {
        f = new File("C:\\Users\\" + userName + "\\Desktop\\Room.txt");
        f.delete();
    }
/**
 * Il metodo salva i dati della chatroom.
 * Nel caso il server manda il comando "stop", il client non salverà i dati della chatroom.
 * @throws IOException Eccezione che viene gestita tramite ,appunto, il "throws IOException"
 */
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
/**
 * Il metodo salva i dati degli utenti scritti nella chatroom, qualora la chatroom non avesse membri o non è possibile accedere a quei dati, il client riceve il comando stop e non riceve alcun dato riguardo ai partecipanti.
 * @throws IOException Eccezione che viene gestita tramite ,appunto, il "throws IOException"
 */
    private void salvaSoloUtenti() throws IOException {
        String partecipante;
        BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));

        partecipante = ricevi.readLine(); //Riceve i partecipanti 

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
}
