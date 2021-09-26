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
        
    /**
     * Create a list of item of Item type.
     */
    public Player()
    {
        playerItem = new ArrayList();
    }
    
   /**
    * Adds item to the Item list playerItem
     * @param item The item of the Item type.
    */    
    public void addItemInventory(Item item){
        playerItem.add(item);
        System.out.println(item.getDescription() + " was taken ");
    }

   /**
    * Removes item from the Item list playerItem
     * @param item The item of the Item type.
    */  
    public void removeItemInventory(Item item){
        playerItem.remove(item);
        System.out.println(item.getName() + " was removed from your inventory");
    }
    
   /**
    * Checks the item list if the item exists matching the item name
    * if the item exists then return the item of type Item otherwise return null
     * @param stringItem The name of the item.
     * @return found item of type Item
    */  
    public Item getPlayerItem(String stringItem){
        Item itemToReturn = null;
        for(Item item: playerItem){
            if(item.getName().contains(stringItem)){
                itemToReturn = item;
            }
        }
        return itemToReturn;
    }
        
   /**
    * Prints all of the item available into the item list
     * @return found name of items available into item list
    */  
    public String printAllInventory(){

        String returnString = "Items:";
        for(Item item : playerItem){
            returnString += " " + item.getName();           
        }
        return returnString;
    }

    
}
