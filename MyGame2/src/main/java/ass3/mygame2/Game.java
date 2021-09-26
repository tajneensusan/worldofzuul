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
        //System.out.println(createRoom.getcurrentRoom().getName());
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }

    
    /**
     * Main play routine. Loops until end of play.
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
            // count the delta (currentTome - startTime)            
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
            printInventory(); // printVeggies
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
        // else command not recognised.
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

    private void printInventory() {
        System.out.println(player.printAllInventory());
    }

    
    /**
     * Try to in to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
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

    private boolean takeItem(Command command) {
        
        boolean isGoalObectFound = false;
        
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return isGoalObectFound;
        }
        
        
        String itemFromCommand = command.getSecondWord();
        Item currentItem = currentRoom.getRoomItem(itemFromCommand);
        //getPlayerItem(itemFromCommand);

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

            //roomItem.remove(currentItem);
            //addItemInventory(currentItem);
            //System.out.println(currentRoom.getLongDescription());
        }
        
        return isGoalObectFound;
    }

    private void dropItem(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        String itemFromCommand = command.getSecondWord();
        Item currentItem = player.getPlayerItem(itemFromCommand);
        //getPlayerItem(itemFromCommand);

        if (currentItem == null) {
            System.out.println("You can't drop anything, no?");
        } else {
            // Do the transaction here
            player.removeItemInventory(currentItem);
            currentRoom.addItemInRoom(currentItem);

            //removeItemInventory(currentItem);
            //roomItem.put(currentItem, currentRoom);
            //System.out.println(currentRoom.getLongDescription());
        }
    }
    
    private void lookItem(Command command) 
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
        } else {
            currentItem.getDescription();
        }
    }

    private void useItem(Command command) // use key
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

            //if(currentRoom.getName().equals("frontYard") && currentItem.getName().equals("item1")){
            //frontGate.setLockedStatus(false);
            // if(currentRoom.getName().equals("castle")){
            // //currentRoom.checkRoom("castle");
            // roomKey.get(currentItem).setLockedStatus(false);
            // }
            

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

