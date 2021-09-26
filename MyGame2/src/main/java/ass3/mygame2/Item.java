
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
    
    public Item(String name, String description, int destructivePower)
    {
        this.name = name;
        this.description = description;
        this.destructivePower = destructivePower;
    }
    
    public Item(String name, String description, double healingPower)
    {
        this.name = name;
        this.description = description;
        this.healingPower = healingPower;
    }
    
    //write accessors and mutators
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    /**
     * 
     *  
     */
    public int getPower(){
        return destructivePower;
    }
    
    public double getHealingPower(){
        return healingPower;
    }
}
