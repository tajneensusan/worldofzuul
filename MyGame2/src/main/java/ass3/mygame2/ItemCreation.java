package ass3.mygame2;

import java.util.ArrayList;


/**
 * This class contains information about Item list of user.
 * The way this is used is: creates Item of type Item by creating an object of Item class and adding the items into a list.
 * Also, this class is returning the list of Items.
 * @author Tajneen Rahman Susan
 * @version 2021.09.01
 */
public class ItemCreation
{
    
    private ArrayList<Item> allItemsInGame;
    
    public ItemCreation()
    {       
        allItemsInGame = new ArrayList();
        createItems();
    }
    
    public void createItems(){
        
        Item item1, item2, excaliburSword, key, frontGateKey, flower, pairOfGlass, storeKey, diamond;
        
        
        excaliburSword = new Item("excaliburSword", "The legendary Excalibur", 100);
        key = new Item("key", "It has a shape of a heart", 100);
        frontGateKey = new Item("frontGateKey", "To open the front gate door", 100);
        flower = new Item("flower", "The nice red flower", 100);
        pairOfGlass = new Item("pairOfGlass", "A pair of glass which helps to see in the dark", 10.0);
        storeKey = new Item("storeKey", "To open the store door", 10.0);
        diamond = new Item("diamond", "A nice big diamond", 10.0);
        
        allItemsInGame.add(excaliburSword);
        allItemsInGame.add(key);
        allItemsInGame.add(frontGateKey);
        allItemsInGame.add(flower);
        allItemsInGame.add(pairOfGlass);
        allItemsInGame.add(storeKey);
        allItemsInGame.add(diamond);
    }
   
    public Item getItem(String stringItem){
        Item itemToReturn = null;
        for(Item item: allItemsInGame){
            if(item.getName().contains(stringItem)){
                itemToReturn = item;
            }
        }
        return itemToReturn;
    }
  
    
}
