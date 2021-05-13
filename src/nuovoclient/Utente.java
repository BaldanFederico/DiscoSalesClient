/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

import java.util.Scanner;

/**
 *
 * @author dell
 */
public class Utente {
   private String nome;
   private String password;
   private String Email;
 
    
   public String getNome() {
        return nome;
    }

    public String getPassword() {
       
        return password;
    }

    public String getEmail() {
        return Email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
   



}
