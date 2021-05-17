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
    private String nomeRoom;
    File f;

    public GestioneProfilo(Socket server) {
        this.server = server;

    }

    public void gestisci() {
        Utente2 u = new Utente2();
        Protocolli2 p = new Protocolli2();

        Scanner sc = new Scanner(System.in);
        String RoomID, partecipante = u.getNome();
        int controllo;
        String risposta;
        try {

            PrintWriter scrittore = new PrintWriter(server.getOutputStream(), true);
            BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));
            System.out.println("sei nella gestione profilo");
            scrittore.println(partecipante);
            // salva(nomeRoom, owner, partecipante);
            do {
                System.out.println("premi 1 per creare una nuova room");
                System.out.println("premi 2 per cercare una room");
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
                        System.out.println("vuoi unirti(scrivi entr)");//verrà automaticamente generata
                        risposta = sc.next();
                        if (risposta.equals("entr")) {
                            scrittore.println(risposta);//manda la conferma
                            scrittore.println(partecipante);
                            salva(partecipante);
                        }
                        break;

                }
            } while (controllo != 3);
            cancella();
        } catch (IOException ex) {
            System.out.println("hai rotto java");
        }
    }

    private void writeRoom() throws IOException {  //serve tenere traccia dei vari delle room

        f = new File("C:\\Users\\" + userName + "\\Desktop\\Room.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        f.createNewFile();
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

    private void salva(String partecipante) throws IOException {

        BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));

        nomeRoom = ricevi.readLine();
        owner = ricevi.readLine();
        partecipante = ricevi.readLine();

        while (!partecipante.equals(null)) {

            if (!partecipante.equals("members")) {
                room.add(new Room(nomeRoom, owner, null, partecipante));

                //  if (checkbox == true) {
                writeRoom();
                //  }
            }
            partecipante = ricevi.readLine();
        }
    }
}
