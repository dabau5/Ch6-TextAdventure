/**
 * Item class makes items with a description that can be accessed easily or changed.
 * It has a print function which allows the description to be printed in a pre-formatted
 * output. Easy to use with HashMaps.
 * 
 * @author Alex Cumins
 * @version 1.0
 */
public class Item
{
    // private variables
    private String description;
    /**
     * Creates an item with a description "description".
     * @param desc  The item's description
     */
    public Item(String desc)
    {
        description = desc;
    }
    
    /**
     * Prints out a formatted description of the item
     * @param name  The name of the item
     */
    public void printLook(String name)
    {
      System.out.println("You pick up the " + name + ". It is " + description);
    }
    
    /**
     * Sets the description of the item
     * @param desc  The description
     */
    public void setDesc(String desc)
    {
        description = desc;
    }
        
    /**
     * Returns the description of the item
     * @return  The description
     */
    public String getDescription()
    {
        return description;
    }

}
