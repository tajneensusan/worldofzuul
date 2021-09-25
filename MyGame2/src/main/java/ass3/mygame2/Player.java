package ass3.mygame2;


import java.util.ArrayList;


/**
 * This class holds information about player's item and inventory.
 * The way this is used is: it receives item then add/remove those item into/from the player inventory
 * Player class receives an item name and checks if that item exists in players item list or not. If exists then it returns the item.
 * This class also returns list of available player item into its inventory.
 * @author Tajneen Rahman Susan
 * @version 2021.09.01
 */
public class Player
{
    
    private ArrayList<Item> playerItem;
    
    public Player()
    {
        playerItem = new ArrayList();
    }
    
    /**
    * Write a description of class Player here.
    *
    * @param (your name)
    * @return (a version number or a date)
    * @exception (a version number or a date)
    * @see (a version number or a date)
    */
    
    public void addItemInventory(Item item){
        playerItem.add(item);
        System.out.println(item.getDescription() + " was taken ");
        //System.out.println(item.getDescription() + " was removed from the room"); // add extra information to inform user that the item has been taken
    }

    public void removeItemInventory(Item item){
        playerItem.remove(item);
        System.out.println(item.getName() + " was removed from your inventory");
    }
    
    public Item getPlayerItem(String stringItem){
        Item itemToReturn = null;
        for(Item item: playerItem){
            if(item.getName().contains(stringItem)){
                itemToReturn = item;
            }
        }
        return itemToReturn;
    }
    
    public String printAllInventory(){

        String returnString = "Items:";
        for(Item item : playerItem){
            returnString += " " + item.getName();           
        }
        return returnString;
    }

    
}
