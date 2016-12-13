/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Inventory inventory;
    private int progress;
    private Item id = new Item("your school ID."); //create new ID and schedule items
    private Item schedule = new Item("your course sechedule.\nIt reads:\n****SCHEDULE****\n1st Object Oriented Programming\n2nd Fundamentals of Game Design\n3rd Systems Analysis and Design\n****************");
    /**
     * Create the game and initialise its internal map, people, items, and player inventory.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        inventory = new Inventory();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, campus, westf1, westf2, westf3, library, colCenter, finaid, hunHall, hun1, hun2, comp1, comp2, comp3, comp4, enrollment;
      
        // create the rooms
        outside = new Room("outside the main entrance of RVCC");
        campus = new Room("on the main campus");
        westf1 = new Room("in West Building, Floor 1");
        westf2 = new Room("in West Building, Floor 2");
        westf3 = new Room("in West Building, Floor 3");
        library = new Room("in the Library");
        colCenter = new Room("in the College Center");
        finaid = new Room("in the Financial Aid office");
        hunHall = new Room("in Hunterdon Hall");
        hun1 = new Room("in room H123");
        hun2 = new Room("in room H110");
        comp1 = new Room("in room W110");
        comp2 = new Room("in room W210");
        comp3 = new Room("in room W213");
        comp4 = new Room("in room W306");
        enrollment = new Room("in the enrollment office");
        
        // initialise room exits
        outside.setExit("north", campus);
        outside.setExit("east", library);
        outside.setExit("west", westf1);
        
        library.setExit("west", outside);
        library.setExit("north", campus);

        westf1.setExit("east", outside);
        westf1.setExit("north", campus);
        westf1.setExit("west", westf2);
        westf1.setExit("south", comp1);
        
        comp1.setExit("north", westf1);
        
        westf2.setExit("east", westf1);
        westf2.setExit("west", westf3);
        westf2.setExit("north", comp2);
        westf2.setExit("south", comp3);
        
        comp2.setExit("south", westf2);
        comp3.setExit("north", westf2);
        
        westf3.setExit("east", westf2);
        westf3.setExit("north", comp4);
        
        comp4.setExit("south", westf3);

        campus.setExit("west", westf1);
        campus.setExit("east", library);
        campus.setExit("north", hunHall);
        campus.setExit("south", outside);

        hunHall.setExit("west", colCenter);
        hunHall.setExit("south", campus);
        hunHall.setExit("east", hun2);
        hunHall.setExit("north", hun1);
        
        hun1.setExit("south", hunHall);
        hun2.setExit("west", hunHall);

        colCenter.setExit("west", finaid);
        colCenter.setExit("north", enrollment);
        colCenter.setExit("south", campus);
        colCenter.setExit("east", hunHall);
        
        enrollment.setExit("south", colCenter);
        
        finaid.setExit("east", colCenter);
        
        //initialize items
        Item book = new Item("a psychology textbook left behind.");
        Item phone = new Item("an iPhone 4. It has a passcode lock.");
        Item pencil = new Item("a regular pencil.");
        Item gummy = new Item("a singular squished gummy bear.");
        Item paper = new Item("someone's old schedule. The first class is English 1.");
        Item stopwatch = new Item("a stopwatch. The timer was stopped at 80 minutes.");
        
        //add items to room
        library.addItem("book", book);
        colCenter.addItem("phone", phone);
        campus.addItem("pencil", pencil);
        hunHall.addItem("gummy", gummy);
        hunHall.addItem("paper", paper);
        comp4.addItem("stopwatch", stopwatch);
        
        
        //initialize people
        Person npc1 = new Person("Hey! First day is usually a challenge.\nGood luck finding your first class!");
        Person npc2 = new Person("What's your schedule like?\nI'm trying to find my first class...");
        Person npc3 = new Person("I took this class last semester but I have to retake it...");
        Person npc4 = new Person("Concepts & Programming is a great class!\nIt's fun once you know what you're doing!");
        Person npc5 = new Person("Parking here is terrible. I circled the lot\nfor 20 minutes to find the furthest spot possible from class!");
        Person npc6 = new Person("First day of class and I already can't wait for summer!");
        Person npc7 = new Person("I'm taking Fundamentals of Game Design with Professor Crosbie.\nI've heard it's a great class!");
        Person npc8 = new Person("I'm in a rush! I'm going to be late!");
        Person profNpc1 = new Person("This is English 1. We do lame stuff here\nlike reading poetry and writing essays.");
        Person profNpc2 = new Person("We are the photography class and we're\ntaking pictures! \n*SNAP*\nThanks for visiting!");
        Person profNpc3 = new Person("This is Computer Concepts and Programming.\nYou'll learn computer basics and some Java here.");
        Person profNpc4 = new Person("Welcome to Systems Analysis and Design!\nThis is a boring class but we try to make it fun!");
        Person profNpc5 = new Person("Welcome to Advanced Java. If you don't know\nany Java, you're in for a bad time.");
        Person librarianNpc = new Person("Hi! If you want to check out books \nyou're going to need your school ID.");
        Person enrollNpc = new Person("Hi there! What can I help you with?");
        Person finaidNpc = new Person("Hi! If you have questions about your financial aid you need to sign in with your G Number.");
        Person crosbieNpc = new Person("This is Object Oriented Programming! Here you'll learn how to make\nall sorts of stuff like silly text adventure games!");
        crosbieNpc.setProf(true);
        profNpc1.setProf(true);
        profNpc2.setProf(true);
        profNpc3.setProf(true);
        profNpc4.setProf(true);
        profNpc5.setProf(true);
        npc8.setRemove(true);
        
        
        //add people to room
        outside.addPerson("steve", npc1);
        campus.addPerson("thomas", npc2);
        hun1.addPerson("billy", npc3);
        hun1.addPerson("essa", profNpc1);
        hun2.addPerson("pix", profNpc2);
        hun2.addPerson("jack", npc5);
        comp1.addPerson("jamie", npc4);
        comp1.addPerson("basic", profNpc3);
        comp2.addPerson("jess", npc6);
        comp2.addPerson("rood", profNpc5);
        comp3.addPerson("alex", npc7);
        comp3.addPerson("crosbie", crosbieNpc);
        comp4.addPerson("anna", profNpc4);
        campus.addPerson("biff", npc8);
        library.addPerson("reed", librarianNpc);
        finaid.addPerson("penny", finaidNpc);
        enrollment.addPerson("stacy", enrollNpc);
        
        currentRoom = outside;  // start game outside
        progress = 0; //progress throughout the game initialized to 0
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Raritan Valley Community College!");
        System.out.println("You are here for your first day!\nWelcome!\nPlease make your way to class!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                lookItem(command);
                break;
                
            case TAKE:
                modInv(command);
                break;
                
            case DROP:
                modInv(command);
                break;
                
            case TALK:
                personTalk(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at RVCC.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Modify the player's inventory using the command the user gave. Either adds or removes item from inventory.
     * @param command  The command the user gave. Checked to see if command was take or drop
     */  
    private void modInv(Command command)
    {
        String com = command.getCommandWord().toString();
        if(checkValid(command)){ //check if command is valid with second word
            String item = command.getSecondWord();
            item = item.toLowerCase(); //make lowercase for hashmap purposes
            if(com.equalsIgnoreCase("take")){ //if command was "take"
              Item theItem = currentRoom.getItem(item);
                if(theItem != null){ //if the item exists in room
                 inventory.addInv(item, theItem);//add the item to the inventory
                 currentRoom.removeItem(item); //remove the item from the current room
                 System.out.println("You took the " + item + " and put it on your bag.");
                }
                else //the item doesn't exist in room
                System.out.println("That item isn't here!");
            }
            if(com.equalsIgnoreCase("drop")){ //if command was "drop"
                Item theItem = inventory.getItem(item);
                if(theItem != null){ //if the item exists in inventory
                inventory.delInv(item); //remove item from inventory
                currentRoom.addItem(item, theItem); //add the item to the current room
                System.out.println("You dropped the " + item + " from your bag.");
                }
                else //item doesn't exist in inventory
                System.out.println("You don't have that item!");
            }
        }
        else{ //no second word, don't know what to drop
            if(com.equalsIgnoreCase("take")) System.out.println("Take what?");
            if(com.equalsIgnoreCase("drop")) System.out.println("Drop what?");
        }
    }
    
    
    /** 
     * Try to look at the item's description. If the item exists, show the description.
     * If the item doesn't exist or no second word is provided, print error message.
     * @param command  The command the user gave
     */
    private void lookItem(Command command) 
    {
        if(checkValid(command)) {//check if command is valid with second word
        String item = command.getSecondWord();
        item = item.toLowerCase();
        if(item.equalsIgnoreCase("room")){//check if trying to look at room
        System.out.println(currentRoom.getLongDescription());
        }
        
        else if(item.equalsIgnoreCase("inventory"))
        {
            System.out.println(inventory.getInv());
        }
        // Try to look at item

        else {
            Item theItem = currentRoom.getItem(item);
            if (theItem == null) {
            System.out.println("That item isn't here!");
            }
            else {
            theItem.printLook(item);
            }
        }
     }
     else
        System.out.println("Look at what?"); //don't know what to look at if no second word
    }
    
    /**
     * Print out the message a person is supposed to say when talked to.
     * @param command  The command the user gave
     */
    private void personTalk(Command command)
    {
        if(checkValid(command)) //check if valid command with second word
        {
            String personName = command.getSecondWord(); //get the second word they said
            String name = personName; //store for reference
            Person personObj = currentRoom.getPerson(personName);
            if (personObj == null) //if person doesn't exist
            {
              System.out.println("That person isn't here!");
            }
            else //person exists
            {
             if(!checkProgress(name, personObj)){ //check if talking to this person does not add progress to the game
                 if (personObj.isProf()) personName = "prof. " + personName; //if the person is a professor, add PROF. prefix
                 System.out.println(personName.toUpperCase() + ": " + personObj.getMessage());
             }
             else checkProgress(name, personObj); //talking to this person adds progress, call checkProgress function which will add progress
             if(personObj.isRemoved()) currentRoom.removePerson(name); //Remove if the person should be removed after being spoken to
            }
        }
        else System.out.println("Talk to who?"); //don't know who to talk to if no second word
    }
    
    /**
     * Called each time a person is spoken to. Tracks player progress in game. Checks if talking to them will allow player to continue in game. If yes, add 1 to progress.
     * @param name  The name of the person
     * @param person  The person object
     * @return Boolean that tells if there is progress made
     */
    private boolean checkProgress(String name, Person person)
    {
        boolean prog = false;
        
        if((progress == 0) && (name.equalsIgnoreCase("steve")))
        {
          System.out.println(name.toUpperCase() + ": " + person.getMessage());
          progress++;
          prog = true;
        }
        
        if((progress == 1) && (name.equalsIgnoreCase("reed")))
        {
          System.out.println(name.toUpperCase() + ": " + person.getMessage());
          progress++;
          prog = true;
        }
        
        if((progress == 2) && (name.equalsIgnoreCase("pix")))
        {
            progress++;
            System.out.println("PROF. " + name.toUpperCase() + ": " + person.getMessage());
            person.changeMessage("Thanks again for letting us take your picture!");
            prog = true;
        }
        
        if((progress == 3) && (name.equalsIgnoreCase("reed")))
        {
            progress++;
            person.changeMessage("Here's your ID. If you need anything else just ask!");
            System.out.println(name.toUpperCase() + ": " + person.getMessage());
            System.out.println("** id now in the room **");
            currentRoom.addItem("id", id);
            person.changeMessage("If you need books just ask!");
            prog = true;
        }
        
        if((progress == 4) && (name.equalsIgnoreCase("stacy") && (inventory.getItem("id") != null)))
        {
            progress++;
            person.changeMessage("Here's your schedule. Get moving, if you wait you'll be late!");
            System.out.println(name.toUpperCase() + ": " + person.getMessage());
            currentRoom.addItem("schedule", schedule);
            System.out.println("** schedule now in the room **");
            person.changeMessage("Get to your class! You're going to be late!");
            prog = true;
        }
        
        if((progress == 5) && (name.equalsIgnoreCase("crosbie") && (inventory.getItem("schedule") != null)))
        {
            progress++;
            person.changeMessage("Ah you finally figured out where you're supposed to be!\n15 minutes late... don't make it a habit. :)");
            System.out.println("PROF. " + name.toUpperCase() + ": " + person.getMessage());
            prog = true;
            System.out.println("Congratulations! You finished the game! To leave RVCC, type 'quit'");
        }
        
        return prog;
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(checkValid(command)){ //check if command is valid with second word

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
         }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
         }
        }
        else //don't know where to go
        System.out.println("Go where?");
    }

    /**
      * Checks if command had a second word
      * @return Boolean that tells if it has a second word.
      */
    private boolean checkValid(Command command)
    {
        if(!command.hasSecondWord())
            return false;
        else
            return true;
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
