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

    /**
     * Default constructor of the class 
     * Initializes an object of type ItemCreation
     * and create rooms
     */
    public RoomCreation() {
        itemCreation = new ItemCreation();
        createRooms();
    }
    
    /**
     * Creating room of type Room class
     * The room is created by providing room name, description and locked status
     * The exit of the room is also created to some direction like East, west, north, south  
     * then item is added to the room created
     * All the rooms with exit and items are added to the game to be available for the player.
     * 
     */
    private void createRooms() {

        Room castle, kitchen, frontGate, hallRoom, gallery, store;

        //creating rooms
        castle = new Room("castle", "You are at the castle", false);
        kitchen = new Room("kitchen", "The kitchen door has a shape of a heart", false);
        frontGate = new Room("frontGate", "There is a giant ogre", true);
        hallRoom = new Room("hallRoom", "You are at the Hall room", false);
        gallery = new Room("gallery", "You are at the Gallery", false);
        store = new Room("store", "You are at the Store room", true);
        

        //setting up exit for the rooms created
        castle.setExit("east", kitchen);
        castle.setExit("south", frontGate);
        frontGate.setExit("north", castle);
        kitchen.setExit("south", hallRoom);
        hallRoom.setExit("north", kitchen);
        hallRoom.setExit("east", gallery);
        gallery.setExit("west", hallRoom);
        gallery.setExit("down", store);
        store.setExit("up", gallery);

        //addiing item to the room
        castle.addItemInRoom(itemCreation.getItem("excaliburSword"));
        castle.addItemInRoom(itemCreation.getItem("key"));
        kitchen.addItemInRoom(itemCreation.getItem("frontGateKey"));
        kitchen.addItemInRoom(itemCreation.getItem("flower"));
        hallRoom.addItemInRoom(itemCreation.getItem("pairOfGlass"));
        gallery.addItemInRoom(itemCreation.getItem("storeKey"));
        store.addItemInRoom(itemCreation.getItem("diamond"));

        //adding the room to the game
        allRoomInGame.add(castle);
        allRoomInGame.add(frontGate);
        allRoomInGame.add(kitchen);
        allRoomInGame.add(hallRoom);
        allRoomInGame.add(gallery);
        allRoomInGame.add(store);

    }

    /**
     * Checks if given room name exists in the room list available to the game or not
     * if exists then return the Room with details as Room type or returns null
     * @param stringRoom name of the room
     * @return the Room with details as Room type or returns null
     */
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
