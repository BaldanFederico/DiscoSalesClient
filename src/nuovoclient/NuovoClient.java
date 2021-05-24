/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.io.*;
import java.net.*;
import java.util.*;


import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author dell
 */
public class NuovoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Apertua connessione");
        Socket server = new Socket("127.0.0.1", 6666);
        Scanner sc = new Scanner(System.in);
        GestioneProfilo pf = new GestioneProfilo(server);
        int controllo;
        String nome, password, email, codice;
        utente2 u = new utente2();
        protocolli2 p = new protocolli2();
        PrintWriter scrittore = new PrintWriter(server.getOutputStream(), true);
        BufferedReader ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String risposta;
        do {
            System.out.println("1) registrati");
            System.out.println("2) accedi");
            System.out.println("3)verfica account");
            controllo = sc.nextInt();
            switch (controllo) {
                case 1:
                    scrittore.println(p.SignUp());
                    System.out.println("crea un nuovo nome utente");
                    nome = sc.next();
                    scrittore.println(nome);
                    System.out.println("crea una nuova password");
                    password = sc.next();
                    scrittore.println(password);
                    System.out.println("inserisci la tua mail");
                    email = sc.next();
                    u.setEmail(email);
                    scrittore.println(email);
                    risposta = ricevi.readLine();
                    System.out.println(risposta);
                    break;
                case 2:
                    scrittore.println(p.log());
                    System.out.println("inserisci il tuo nome utente");
                    nome = sc.next();
                    u.setNome(nome);
                    System.out.println("inserisci la password");
                    password = sc.next();
                    u.setPassword(password);
                    scrittore.println(nome);
                    scrittore.println(password);
                    risposta = ricevi.readLine();
                    System.out.println(risposta);
                    if (risposta.equals("enterAccount")) {
                        pf.gestisci();
                    }
                    break;
                case 3:
                    scrittore.println(p.secure());
                    System.out.println("inserisci il codice fornito via mail");
                    codice = sc.next();
                    scrittore.println(codice);
                    risposta = ricevi.readLine();
                    System.out.println(risposta);
            }
        } while (controllo != 5);
        
        scrittore.println("exit");
        System.out.println("chiusura connessione");
        scrittore.close();
        ricevi.close();
        server.close();
    }
}
