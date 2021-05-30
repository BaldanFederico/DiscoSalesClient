/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.*;


/**
 *La classe "NuovoClient" gestisce la connettività delle comunicazioni client e server.
 * Inoltre instanzia le classi che gestiscono le interfacce ed i gestori: Utente e Chatroom.
 * @author DiscoSales
 */
public class NuovoClient extends Application {
    private Login login;
    private Activation activation;
    private SaveAccount saveAccount;
    private Upload upload;
    private Friends main;
/**
 * Il metodo inizializza le interfacce grafiche salvate su apposite classi.
 * Le interfacce sono sincronizzate tra loro a seconda dei comandi inseriti nelle classi.
 * @param stage Il parametro è una classe "stage" che serve a mostrare a video le interfacce.
 * @throws Exception Eccezione che viene gestita tramite ,appunto, il "throws Exception"
 */    
    
    @Override
    public void start(Stage stage) throws Exception {
        login = new Login(stage);
        activation = new Activation(stage);
        saveAccount = new SaveAccount(stage);
        main = new Friends(stage);
        upload = new Upload(stage);
        
        login.setScenes(activation.getScene(), saveAccount.getScene(), upload.getScene(), main.getScene());
        activation.setScenes(login.getScene(),upload.getScene(), main.getScene());
        saveAccount.setScene(login.getScene());
        
        stage.setTitle("Login");
        stage.setScene(login.getScene());
        stage.show();  
    }
    

    /**
     * Il main è il metodo principale che viene eseguito per primo e gestisce la connessione fra client e server.
     * La connessione è volta a conoscere se l'utente, che vuole collegarsi al server, sia un iscritto ad un profilo o un nuovo utente.
     * @param args Gli argomenti delle linee di comando
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //launch();
        System.out.println("Apertura connessione");
        Socket server = new Socket("127.0.0.1", 6666);
        Scanner sc = new Scanner(System.in);
        GestioneProfilo pf = new GestioneProfilo(server);
        int controllo;
        String nome, password, email, codice;
        utente u = new utente();
        Protocollo p = new Protocollo();
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
