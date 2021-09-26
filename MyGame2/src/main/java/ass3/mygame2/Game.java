 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.mygame2;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class holds information about the game itself. All the important classes such as Parser, Player, Room and RoomCreation has been 
 * initialized in here and called
 * The way this is used is: it starts the timer using the real time. The it starts the game by creating new Player and Room(s).
 * This class also receives user command and processes the command to check which command the user has type. If the command exists in game
 * command list then it is redirecting the user to that action otherwise letting know the user that the command is unknown.
 * This class also, moves user between room by following user command and printing the help messages to the user. 
 * 
 * @author Tajneen Rahman Susan
 * @version 2021.09.01
 */

public class Game {

    private Parser parser;
    private Player player;
    private Room currentRoom;
    private RoomCreation rooms;

    private HashMap<Item, Room> roomItem;

    private HashMap<Item, Room> roomKey;

    private int timeCounter; // to count the steps
    private int healthCount; // to count the health value
    private long timeStart;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        timeStart = System.currentTimeMillis(); // use the real time
        timeCounter = 50;
        healthCount = 0;
        parser = new Parser();
        player = new Player();
        rooms = new RoomCreation();
        currentRoom = rooms.getRoom("castle");  // start game outside
    }
    
    /**
     * Return the current room of type Room.
     * If the room is not found then returns null.
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    
    /**
     * Main play routine. Loops until end of play.
     * It checks timer if it is more than 15 minutes then the game is finished. Otherwise it processes the command and
     * redirects to the appropriate command method.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished) {
            long currentTime = System.currentTimeMillis();
            if((currentTime - timeStart) > 15)
            {
                System.out.println("Your time is up!!");
                finished = true;
                
                return;
            }
            Command command = parser.getCommand();
            
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of zuul adventure game!");
        System.out.println("your goal is to find the hidden object 'Diamond' to become the winner");
        System.out.println("you have to complete this challenge between 15 minutes");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if(healthCount < 0)
        {
            System.out.println("Sorry your health value is less than 0.");
            return true;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("inventory")) {
            printInventory(); 
        } else if (commandWord.equals("go") || commandWord.equals("up")|| commandWord.equals("down")) {
            goRoom(command);
        } else if (commandWord.equals("take")) {
            takeItem(command);
        } else if (commandWord.equals("drop")) {
            dropItem(command);
        } else if (commandWord.equals("use")) {
            useItem(command);
        } else if (commandWord.equals("inspect")) {
            lookItem(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else
        {            
            // else command not recognised.
            System.out.println("Sorry, what did you say??");
        }
        return wantToQuit;
    }

    
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println("");

        // implement random Hints -> massive bonus points 
        System.out.println("you can open the door using the use command");

        System.out.println("you need to clear the ogre before you can open the kitchen door");
        
        System.out.println("you must have to find the diamond to win the game.");

        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

     /**
     * Print out all item in the inventory. 
     */
    private void printInventory() {
        System.out.println(player.printAllInventory());
    }

    
    /**
     * Try to move the player in to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     * @param command The command to be processed.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            if (currentRoom.getLockedStatus() == true) { // the door is locked
                System.out.println("The door is locked, you need to find a way to open it");
                System.out.println(currentRoom.getLongDescription());
            } else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                
                // increment the timeCounter
                timeCounter ++;
            }
        }
    }

    /**
     * Try to check if the item is available in the room or not. 
     * If available then remove the item from room and add it to the player inventory
     * otherwise printing that the item is not available to take. 
     * @param command The command to be processed as item name.
     * @return true If the item 'Diamond' is found otherwise always return false.
     */
    private boolean takeItem(Command command) {
               
      boolean isGoalObectFound = false;
      try
      {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return isGoalObectFound;
        }
        
        String itemFromCommand = command.getSecondWord();
        Item currentItem = currentRoom.getRoomItem(itemFromCommand);

        if (currentItem == null) {
            System.out.println("You can't take nothing, no?");
        } else {
            // Do the transaction here
            currentRoom.removeItemInRoom(currentItem);
            player.addItemInventory(currentItem);
            
            if(currentItem.getName().equals("diamond"))
            {
                System.out.println("you won the game!!");
                isGoalObectFound = true;
            }
        }
      }
      catch(Exception ex){
            System.out.println("Sorry there was an issue processing your request.");
      }
        
        return isGoalObectFound;
    }

    /**
     * Try to check if the item is available in the players item list or not. 
     * If available then remove the item from players item list and add it to the current room
     * otherwise printing that the player does not have this item to drop. 
     * @param command The command to be processed as item name.
     */
    private void dropItem(Command command) {
      try
      {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        String itemFromCommand = command.getSecondWord();
        Item currentItem = player.getPlayerItem(itemFromCommand);

        if (currentItem == null) {
            System.out.println("You can't drop anything, no?");
        } else {
            // Do the transaction here
            player.removeItemInventory(currentItem);
            currentRoom.addItemInRoom(currentItem);
        }
      }
      catch(Exception ex){
            System.out.println("Sorry there was an issue processing your request.");
      }
    }
    
    /**
     * Try to check if the item is available in the players item list or not. 
     * If available then prints the description about the item otherwise printing that the player does not have this item. 
     * @param command The command to be processed as item name.
     */
    private void lookItem(Command command) 
    {
        try
        {
            if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
                System.out.println("Look what?");
                return;
            }
        
            String itemFromCommand = command.getSecondWord();
            Item currentItem = currentRoom.getRoomItem(itemFromCommand);
        
            if (currentItem == null) {
                System.out.println("You don't have this item");
            } 
            else {
                currentItem.getDescription();
            }
        }
        catch(Exception ex){
            System.out.println("Sorry there was an issue processing your request.");
      }
    }
    
    /**
     * Try to check if the item is available in the players item list or not. 
     * If available then prints the description about the item
     * otherwise printing that the player cannot use this item. 
     * @param command The command to be processed as item name.
     */
    private void useItem(Command command) // use key
    {
        try
        {
            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Use what?");
                return;
            }

            String itemFromCommand = command.getSecondWord();
            Item currentItem = currentRoom.getRoomItem(itemFromCommand);

            if (currentItem == null) {
                System.out.println("You can't use nothing, no?");
            } else {
                // you want make sure that the currentRoom is the room where you want to open the door (before the nextdoor).
                // you want to make sure the currentItem matches the key to open the next door.

                if(currentItem.getPower() > 0)
                {
                    healthCount = healthCount - currentItem.getPower();
                    System.out.println("You've just lost your health value : " + currentItem.getPower());
                }
                else
                {
                    healthCount = (int) (healthCount + currentItem.getHealingPower());
                }
                if(currentItem.getName().contains("key"))
                {
                    if(currentRoom.getName().equals("castle") && currentItem.getName().equals("key"))
                    {
                        currentRoom.setLockedStatus(false);
                    }
                    else
                    {
                        System.out.println("You cannot use this item here");
                        return;
                    }
                
                    if(currentRoom.getName().equals("store") && currentItem.getName().equals("storeKey"))
                    {
                        currentRoom.setLockedStatus(false);
                    }
                    else
                    {
                        System.out.println("You cannot use this item here");
                        return;
                    }
                
                    if(currentRoom.getName().equals("frontGate") && currentItem.getName().equals("frontGateKey"))
                    {
                        currentRoom.setLockedStatus(false);
                    }
                    else
                    {
                        System.out.println("You cannot use this item here");
                        return;
                    }
                }
                System.out.println("You just used the " + currentItem.getName());   
            }
        }
        catch(Exception ex){
            System.out.println("Sorry there was an issue processing your request.");
      }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

}

