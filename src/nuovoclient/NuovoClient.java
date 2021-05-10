/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;

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
        int controllo;
        String nome, password, Email, codice;
        utente u = new utente();
        protocolli p = new protocolli();
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
                    Email = sc.next();

                    scrittore.println(Email);
                    risposta = ricevi.readLine();
                    System.out.println(risposta);
                    break;
                case 2:
                    scrittore.println(p.log());
                    System.out.println("inserisci il tuo nome utente");
                    nome = sc.next();
                    System.out.println("inserisci la password");
                    password = sc.next();
                    scrittore.println(nome);
                    scrittore.println(password);
                    risposta = ricevi.readLine();
                    System.out.println(risposta);
                    if (risposta.equals("enterAccount")) {
                        Thread profiloClient = new Thread(new GestioneProfilo(server, nome));
                        profiloClient.start();
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
