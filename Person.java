
/**
 * Person class creates a person that has a message they output when talked to, as well
 * as specifying if that person is a professor. Professor check is used for formatting 
 * throughout the game. Easy to use with HashMaps
 * 
 * @author Alex C 
 * @version 1.0
 */
public class Person
{
    //private fields
    private String message;
    private boolean professor;
    private boolean removeOnTalk;
    
    /**
     * Contructor that specifies a message for the person to say
     * @param message The message for the person
     */
    public Person(String message){
        this.message = message;
    }

    /**
     * Changes message for the person to say
     * @param change New message for person to say
     */
    public void changeMessage(String change){
        message = change;
    }

    /**
     * Sets if the person is a professor
     * @param bool  True/false if person is a professor
     */
    public void setProf(boolean bool)
    {
        professor = bool;
    }
    
    /**
     * Sets if the should be removed after being spoken to
     * @param bool  True/false if person should be removed
     */
    public void setRemove(boolean bool)
    {
        removeOnTalk = bool;
    }
    
    /**
     * Checks if the person is a professor
     * @return  Boolean if the person is a professor
     */
    public boolean isProf()
    {
        return professor;
    }
    
    /**
     * Checks if the person should be removed after being spoken to
     * @return  Boolean if the person should be removed
     */
    public boolean isRemoved()
    {
        return removeOnTalk;
    }

    /**
     * Returns the message for the person to say
     * @return The person's message
     */
    public String getMessage()
    {
        return message;
    }
}
