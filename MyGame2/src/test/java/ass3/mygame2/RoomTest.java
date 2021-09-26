/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.mygame2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Tajneen Rahman Susan
 */
public class RoomTest {
    Room room, room2;
    Item item;
    
    public RoomTest()
    {
        room = new Room("Dining", "Dining room", true);
        room2 = new Room("Kitchen", "Kitchen room", false);
        room.setExit("south", room2);
    }
    
    /**
     * Test of getShortDescription method, of class Room.
     */
    @Test
    public void testGetShortDescription() {
        
        String expected = "Dining room";
        //String expected = "Dining rooms";     // failed test
        String actual = room.getShortDescription();
        
        assertEquals(expected, actual);
    }
    
    /**
     * Test of getLockedStatus method, of class Room.
     */
    @Test
    public void testGetLockedStatus() {
        
        boolean expected = true;
        //boolean expected = false;    // failed test
        boolean actual = room.getLockedStatus();
        
        assertEquals(expected, actual);
    }
    
    /**
     * Test of getName method, of class Room.
     */
    @Test
    public void testGetName() {
        
        String expected = "Dining";
        //String expected = "Dining rooms";     // failed test
        String actual = room.getName();
        
        assertEquals(expected, actual);
    }
        
    /**
     * Test of getExit method, of class Room.
     */
    @Test
    public void testgetExit() {
        
        Room expected = room2;
        //String expected = "Dining rooms";     // failed test
        Room actual = room.getExit("south");
        
        assertEquals(expected, actual);
    }
}
