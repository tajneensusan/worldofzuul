package ass3.mygame2;

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class holds information about current room such as name, description , whether it is locked or not, the exits of a room and items available in that room.
 * The way this is used is: This class initializes room. Also, this class sets exit for a room and returns description about the current room.
 * This class also returns description with long sentence to help the player to understand where he is now.
 * ALso, this class returns list of items available into current room and add/remove item to current room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private String name;
    private boolean isLocked;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> roomItem;
    private HashMap<Room, Item> roomHashMapItem;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param name The room's name.
     * @param description The room's description.
     * @param isLocked To define if the room is locked or not.
     */
    public Room(String name, String description, boolean isLocked) 
    {
        this.description = description;
        this.name = name;
        this.isLocked = isLocked;
        exits = new HashMap<>();
        roomItem = new ArrayList();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Returns description of the room which has been defined in the constructor
     * @return The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getAllItems();
    }

    /**
     * Return all of the item available into the item list
     * @return all items available into the item list
     */
    public String getAllItems(){

        return "You have some " + listOfItems();

    }

    /**
     * Loop through all of the item in the list of available item in the room and returns the name of the items.
     * @return name of the items available in the room item list
     */
    private String listOfItems(){

        String returnString = "items:";
        for(Item item : roomItem){
            returnString += " " + item.getName();           
        }
        return returnString;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Loop through all of the item in the list of available item in the room item list
     * and checks if the item name exists in the list or not. If available then return the item or return null
     *
     * @param stringItem taken from the command which was converted into a String
     * @return Item class according to the input String
     */
    public Item getRoomItem(String stringItem){
        Item itemToReturn = null;
        for(Item item: roomItem){
            if(item.getName().contains(stringItem)){
                itemToReturn = item;
            }
        }
        return itemToReturn;
    }

    /**
     * Add item to the list of items in the room
     * @param item of type Item class with Name, description and power (healing or destructive)
     */
    public void addItemInRoom(Item item){
        roomItem.add(item);
    }

    /**
     * Remove item from the list of items in the room
     * @param item of type Item class with Name, description and power (healing or destructive)
     */
    public void removeItemInRoom(Item item){
        if(roomItem.size() > 0){
            roomItem.remove(item);
        }
    }

    /**
     * Add room with it's item to a Hashmap
     * @param room of type Room class which will have the item given
     * @param item of type Item class which will be available to the given room
     */
    public void addHashMapItemInRoom(Room room, Item item){
        roomHashMapItem.put(room, item);
    }

    /**
     * Return lock status of the room
     *
     * @return The room lock status
     */
    public boolean getLockedStatus(){
        return isLocked;
    }
    
    /**
     * set lock status of the room
     *
     * @param newStatus of the room lock
     */
    public void setLockedStatus(boolean newStatus){
        isLocked = newStatus;
    }
    
    /**
     * 
     * @return the name of the Room
     */
    public String getName(){
        return name;
    }

    
}

