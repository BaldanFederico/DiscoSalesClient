/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

/**
 * La classe gestisce le iterazioni fra client e server con una serie di codici chiamati: Protocollo.
 * Il protocollo regola la sintassi delle comunicazioni
 * @author DiscoSales
 */
public class Protocollo {
        public String SignUp() {
        return "signUP";
    }

    public String log() {
        return "log";
    }

    public String secure() {
        return "secure";
    }

    public String create() {
        return "create";
    }
  public String Search(){
      return "search";
  }

}
