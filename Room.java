import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * Rooms can have items or people within them. People can be spoken to, 
 * and items can be picked up by a player. The room stores what items or
 * people it has.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, Item> items;        // stores items in this room
    private HashMap<String, Person> people;     // stores people in this room
    /**
     * Create a room described "description". Initially, it has
     * no exits or items. "description" is something like "a kitchen" or
     * "an open court yard".
     * Initializes items and peple hashmaps for the room. Initially the room has no items or people
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
        people = new HashMap<String, Person>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Add items for this room.
     * @param name The items in the room.
     * @param item The item object to be added
     */
    public void addItem(String name, Item item)
    {
        items.put(name, item);
    }
    
    /**
     * Adds person for this room.
     * @param name The person in the room.
     * @param person The person object to be added
     */
    public void addPerson(String name, Person person)
    {
        people.put(name, person);
    }
    
    /**
     * Remove items for this room.
     * @param name The item to be removed.
     */
    public void removeItem(String name)
    {
        items.remove(name);
    }

    /**
     * Remove people from this room.
     * @param name The person to be removed.
     */
    public void removePerson(String name)
    {
        name.toLowerCase();
        people.remove(name);
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Returns a formatted string for items in the room
     * @return The items in the room
     */
    public String getItemsString()
    {
      String itemString = "   Items: ";
      if(!items.isEmpty()){
        Set<String> mapKeys = items.keySet();
        itemString += setToString(mapKeys);
      }
      else itemString += "None";
      
      return itemString;
    }
    
    /**
     * SReturns a formatted string for the people in the room
     * @return  The people in the room
     */
    public String getPeopleString(){
      String peopleString = "   People: ";
      if(!people.isEmpty()){
        Set<String> mapKeys = people.keySet();
        peopleString += setToString(mapKeys);
      }  
      else peopleString += "None";
      
      return peopleString;
    }
    
    /**
     * Converts a set into a formatted string. Used for converting HashMap keys for items/people into a useable sring for printing sets
     * @param set  The set to create into a string
     * @return  The formatted string
     */
    private String setToString(Set<String> set)
    {
       String setFormat = "";
        int count = 0;
        for(String s : set){
          if(count == 0){
            setFormat = s;
            count++;
            }
          else setFormat = setFormat + ", " + s;
        }
       return setFormat;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getPeopleString() + "\n" + getItemsString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "   Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Gets an item object from the items HashMap
     * @param name  The name of the item
     * @return  The item object
     */
    public Item getItem(String name)
    {
        name = name.toLowerCase();
        return items.get(name);
    }
    
    /**
     * Gets a person object from the people HashMap
     * @param name  The name of the person
     * @return  The person object
     */
    public Person getPerson(String name)
    {
        name = name.toLowerCase();
        return people.get(name);
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

