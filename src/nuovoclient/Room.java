/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

/**
 * La classe che gestisce i dati della chatroom con cui l'utente è iscritto.
 * @author DiscoSales
 */
public class Room {

    private String nomeRoom;
    private String owner;//Un id univoco per ogni utente
    private String roomID; //Un id univoco per la room
    private String partecipante;

    public Room(String nomeRoom, String owner, String RoomID, String partecipante) {
        this.nomeRoom = nomeRoom;
        this.owner = owner;
        this.roomID = RoomID;
        this.partecipante = partecipante;
    }

    public String getNomeRoom() {
        return nomeRoom;
    }

    public String getOwner() {
        return owner;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getPartecipante() {
        return partecipante;
    }
/**
 * Il metodo che ritorna i dati della chatroom
 * @return Una stringa che contiene i dati della relativa chatroom
 */
    @Override
    public String toString() {
        return "Room{" + "nomeRoom=" + nomeRoom + ", owner=" + owner + ", roomID=" + roomID + ", partecipante=" + partecipante + '}';
    }

}
