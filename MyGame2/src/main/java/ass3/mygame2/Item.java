
package ass3.mygame2;
/**
 * This class holds information about Item like name, description and healing power.
 * The way this is used is: The class has two constructor where one is creating Item with destructive power 
 * and another is creating Item with healing power. When an object of this class is being created then
 * it needs three parameter such as name, description and power (destructive or healing) and then initializes Items of type Item. 
 * This class also returns the name, description and power using accessors.
 * @author Tajneen Rahman Susan
 * @version 2021.09.01
 */
public class Item
{
    private String description;
    private String name;
    private int destructivePower;
    private double healingPower;
    
    /**
     * Create a Item object. Name and description and destructivePower must be supplied
     * @param name The name of the item.
     * @param description The description of the item.
     * @param destructivePower The destructivePower of the item.
     */
    public Item(String name, String description, int destructivePower)
    {
        this.name = name;
        this.description = description;
        this.destructivePower = destructivePower;
    }
    
    /**
     * Create a Item object. Name and description and healingPower must be supplied
     * @param name The name of the item.
     * @param description The description of the item.
     * @param healingPower The healingPower of the item.
     */
    public Item(String name, String description, double healingPower)
    {
        this.name = name;
        this.description = description;
        this.healingPower = healingPower;
    }
    
    /**
     * @return The name of the item
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return The description of the item
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * @return The destructivePower of the item
     */
    public int getPower(){
        return destructivePower;
    }
    
    /**
     * @return The healingPower of the item
     */
    public double getHealingPower(){
        return healingPower;
    }
}
