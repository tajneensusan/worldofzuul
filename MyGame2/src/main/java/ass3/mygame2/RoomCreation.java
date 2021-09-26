package ass3.mygame2;


import java.util.ArrayList;

/**
 * This class holds information about creation of room of type Room class.
 * The way this is used is: creates room of type Room by creating an object of Room class and adding the rooms into a list.
 * Also, this class is returning the list of Rooms available in the game. 
 * 
 * @author Tajneen Rahman Susan
 * @version 2021.09.01
 */

public class RoomCreation {

    private ArrayList<Room> allRoomInGame = new ArrayList();

    private ItemCreation itemCreation;

    public RoomCreation() {
        itemCreation = new ItemCreation();
        createRooms();
    }
    
    
    private void createRooms() {

        Room castle, kitchen, frontGate, hallRoom, gallery, store;

        castle = new Room("castle", "You are at the castle", false);
        kitchen = new Room("kitchen", "The kitchen door has a shape of a heart", false);
        frontGate = new Room("frontGate", "There is a giant ogre", true);
        hallRoom = new Room("hallRoom", "You are at the Hall room", false);
        gallery = new Room("gallery", "You are at the Gallery", true);
        store = new Room("store", "You are at the Store room", true);
        

        castle.setExit("east", kitchen);
        castle.setExit("south", frontGate);
        frontGate.setExit("north", castle);
        kitchen.setExit("south", hallRoom);
        hallRoom.setExit("north", kitchen);
        hallRoom.setExit("east", gallery);
        gallery.setExit("west", hallRoom);
        gallery.setExit("down", store);
        store.setExit("up", gallery);

        castle.addItemInRoom(itemCreation.getItem("excaliburSword"));
        castle.addItemInRoom(itemCreation.getItem("key"));
        kitchen.addItemInRoom(itemCreation.getItem("frontGateKey"));

        allRoomInGame.add(castle);
        allRoomInGame.add(frontGate);
        allRoomInGame.add(kitchen);
        allRoomInGame.add(hallRoom);
        allRoomInGame.add(gallery);
        allRoomInGame.add(store);

    }

    public Room getRoom(String stringRoom) {
        Room roomToReturn = null;
        for (Room room : allRoomInGame) {
            if (room.getName().contains(stringRoom)) {
                roomToReturn = room;
            }
        }
        return roomToReturn;
    }

}
