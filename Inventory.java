import java.util.HashMap;
import java.util.Set;

/**
 * Inventory class keeps track of items a user has picked up or dropped.
 * Items are added every time a player picks up an item, allowing them to
 * "have" them. Items are removed every time a player drops an item, removing
 * it from items they have. Stores items in a HashMap.
 * 
 * @author Alex C 
 * @version 1.0
 */
public class Inventory
{
    //Declare fields
    
    private HashMap<String, Item> inventory;
    /**
     * Creates new HashMap that stores items
     */
    public Inventory()
    {
        inventory = new HashMap<String, Item>();
    }

    /**
     * Adds an item to the inventory HashMap
     * 
     * @param  s  The key for the map (name of item)
     * @param  t  The item object being added 
     */
    public void addInv(String s, Item t)
    {
        inventory.put(s, t);
    }
    
    /**
     * Deletes an item from the inventory HashMap
     * 
     * @param  s  The key for the map (name of item)
     */
    public void delInv(String s)
    {
        inventory.remove(s);
    }
    
    
    /**
     * Get an item from the inventory
     * @param t  The name of the item
     * @return  The item object
     */
    public Item getItem(String t){
        return inventory.get(t);
    }
    
    /**
     * Checks if item exists in the inventory
     * @param t  The item name
     * @return  Boolean that tells if the item exists in the inventory
     */
    public boolean itemExists(String t)
    {
        boolean bool;
        if(inventory.containsKey(t)) //check if inventory contains the key (name of object) specificed
        {
            bool = true; //exists
        }
        else bool = false; //does not exist
        
        return bool;
    }
    
    /**
     * Gets the list of items in the inventory
     * 
     * @return  String formatted with all item names
     */
    public String getInv()
    {
        String invFormat = "";
        if(!inventory.isEmpty()){
            Set<String> itemKeys = inventory.keySet();
            int count = 0;
        for(String s : itemKeys){
            if(count == 0){
            invFormat = "Inventory: " + s;
            count++;
        }
        else    
            invFormat = invFormat + ", " + s;
        }
    }
    else{
        invFormat = "You have no items!";
    }
    return invFormat;
    }
}
