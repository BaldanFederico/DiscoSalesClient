/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuovoclient;

/**
 *
 * @author dell
 */
public class Room {

    private String nomeRoom;
    private String owner;//un id univoco per ogni utente
    private String roomID; //un id univoco per la room
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

    @Override
    public String toString() {
        return "Room{" + "nomeRoom=" + nomeRoom + ", owner=" + owner + ", roomID=" + roomID + ", partecipante=" + partecipante + '}';
    }

}
