/**Game.java - This is a game in which the user creates a player, explores a castle, and while exploring the castle, battles monsters.  
 * 04/15/2016
 * Mohammad Ehson
 */
 
import java.util.Scanner; // program uses class Scanner
import java.security.SecureRandom; // program uses class SecureRandom 
import java.util.InputMismatchException;

public class Game
{
    // Objects declared
    private static Player thePlayer;
    private static Monster theMonster;
    private static Castle castle1;
    private static Room currentRoom;
    
    // variables hold values that what will be arguments to Monster constructor 
    private static String monsterName;
    private static int monsterHealth;
    private static int monsterAttackPwr; 
    private static int monsterExPoints;
    
    // variables hold valus that what will be arguments to Player constructor 
    private static String playerName; 
    private static int playerHealth = 0;
    private static int playerAttackPower = 0;
    private static int mana = 0;
         
    // gameplay loop control variable 
    private static boolean continuePlaying = true;
    
    // this variable allows for control of execution based on whether player's selection was valid or not
    private static boolean validSelection = true;

    // create Scanner object
    private static Scanner input = new Scanner(System.in); // create new scanner object
    
// **********************************************************************************************************
    public static void main(String[] args) // execute method main
    {
        
        // user creates/customizes a player character
        createHero();
          
        // castle object instantiated
        castle1 = new Castle();
          
        // get initial room by calling getRoom0() method from Castle
        currentRoom = castle1.getRoom0();
    
        // while loop control variable is true
		while(continuePlaying)
		{
			// print current room's description and available exits
			System.out.println(currentRoom); 
			System.out.println(currentRoom.getExits());
			System.out.println(); // empty line
		    
		    /* if player picked valid direction, than room is updated and a monster may be generated, 
		     * otherwise user promted to re-enter direction choice. 
		     */
			if (validSelection)
			{
			    
			    currentRoom.monsterGenerator(); // call method monsterGenerator(); monster may or not be generated
			    // call method isMonsterGenerated() to find out if monster was generated and store result in boolean generateMonster
			    boolean generateMonster = currentRoom.isMonsterGenerated(); 
			
         			if(generateMonster == true)
         			{
         			     createMonster();
         			     while(theMonster.getHealth() > 0 && thePlayer.getHealth()>0 )  
         			     {
         			         startCombat();
         			     }
         			     
         			     endCombat();
         			     if (thePlayer.getHealth() <= 0)
         			     {
         			          break;
         			     }
     
         			} 
			}
			
			// Prompt user for exit choice and read choice from keyboard. 
			System.out.printf("Your choice:%nn: go to the north%ne: go to the east%nw: go to the west%ns: go to the south%nq: Quit%n");
			String playerChoice = input.nextLine(); // player selection is read and stored
		    System.out.println(); // empty line
		 	updateRoom(playerChoice); // method handles playerChoise and updates currentRoom accordingly
			
		}	 
     
    }
// *********************************************************************************************************************************************************
    // declare method createHero which allows player to customize his/her character
    public static void createHero() 
    {

        System.out.println("Please enter a name: "); 
        playerName = input.nextLine();
        System.out.println(); // empty line
        int statPoints = 20; // player has 20 points avaiable to purchase health, attack power, or magic power. Must use all of it.
        
    while (statPoints >0) // User continues customizing character until all stat points used up
    {
		
	  System.out.println("Your stats are: "); // players initial stats (0,0,0) are listed
	  System.out.printf("Health: %d%nAttack: %d%nMagic: %d%n%n", playerHealth, playerAttackPower, mana);
	  boolean continueLoop = true; // boolean controls do-while loop
	  
          // do-while loop continues until valid input made at keyboard
             do
             {
                  System.out.printf("1) +10 Health%n2) +1 Attack%n3) +3 Magic%n%n"); // stat options available for purchase are listed
                  System.out.printf("You have %d stat points to spend. Your selection:%n", statPoints); // Stat points available are listed and player is asked to select an item to purchase
                  int playerSelection; // stores int after parsing String userInput
                  try
                  {
                       playerSelection = input.nextInt();
                       // if valid int inputed at keyboard
					   switch (playerSelection) // based on player's purchase selection, appropriate stats are updated and stat points are deducted
                       {
                         case 1:
                              playerHealth += 10;
                              statPoints -= 1;
                              continueLoop =false;
                              break;
                         case 2:
                              playerAttackPower += 1;
                              statPoints -= 1;
                              continueLoop = false;
                              break;
                         case 3:
                              mana += 3;
                              statPoints -= 1;
                              continueLoop = false;
                              break;
                         default:
							System.out.println("Invalid selection. Please try again.\n");
							continueLoop = true;
                         } // end switch
                  }
                  catch(InputMismatchException e) // catch inputMistmatchException
                  {
                       input.nextLine(); // discard input so user can try again
				       System.out.println("\nInvalid selection. Please try again.");
                  }
                   System.out.println(); // empty line
              }while(continueLoop);
              
     } // end while loop
     // When all stat points are used up, user is informed that no points are left and that the character has been createdand final stats listed
	System.out.println(); // empty line
	thePlayer = new Player(playerName, playerHealth, playerAttackPower, mana);
	System.out.println("You have no more stat points left! Your character has been created with stats: ");
	System.out.println(thePlayer);
	System.out.println(); // empty line
	
    } // end class method createHero
//*********************************************************************************************************************************************
// method generates one of four monsters
public static void createMonster() 
     {
     	// create new random-number generator object
     	SecureRandom randomNumber = new SecureRandom();
     	
     	// obtain random int value between 0 and 3 and store in variable whichMonster (int will represent 1 of 4 monsters) 
     	int whichMonster = randomNumber.nextInt(4); // value used to make selection from among 4 monsters
     	
     	// random number generated is used to select type of monster via multi-selection statement
     	/*
     	After selection (1, 2, 3, or 4) is made the following monster stats are set for each monster:
     	monster's name
     	monster's health (sum of a constant and randomly generated value)
        monster's attack power (sum of a constant and a randomly generated value)
     	monster's experience points (points players receives for defeating monster; a constant value)
     	*/
     	
     	switch (whichMonster)
     	{
     		case 0: 
     			monsterName = "Goblin";
     			monsterHealth = 60 + randomNumber.nextInt(20);
     			monsterAttackPwr = 5 + randomNumber.nextInt(5);
     			monsterExPoints = 2;
     			theMonster = new Monster(monsterName, monsterHealth, monsterAttackPwr, monsterExPoints);
     			System.out.println("You have encountered a " + monsterName + "!");
     			break;
			case 1: 
     			monsterName = "Orc";
     			monsterHealth = 80 + randomNumber.nextInt(20);
     			monsterAttackPwr = 10 + randomNumber.nextInt(5);
     			monsterExPoints = 4;
     		    theMonster = new Monster(monsterName, monsterHealth, monsterAttackPwr, monsterExPoints);
     			System.out.println("You have encountered an " + monsterName + "!");
     			break;
     		case 2: 
     			monsterName = "Troll";
     			monsterHealth = 100 + randomNumber.nextInt(20);
     			monsterAttackPwr = 15 + randomNumber.nextInt(5);
     			monsterExPoints = 6;
     			theMonster = new Monster(monsterName, monsterHealth, monsterAttackPwr, monsterExPoints);
     			System.out.println("You have encountered a " + monsterName + "!");
            	break;
     		case 3: 
     			monsterName = "Ogre";
     			monsterHealth = 120 + randomNumber.nextInt(20);
     			monsterAttackPwr = 20 + randomNumber.nextInt(5);
     			monsterExPoints = 8;
                theMonster = new Monster(monsterName, monsterHealth, monsterAttackPwr, monsterExPoints);
     			System.out.println("You have encountered an " + monsterName + "!");
                break;
     		default:
     		break;
     	}
     	
     } // end class method createMonster
// *******************************************************************************************************************************
// this method processes player's choice for direction or for quitting the game
	public static void updateRoom(String playerChoice)
	{
			if (playerChoice.equalsIgnoreCase("q"))
		 	{
		 		continuePlaying = false;
		 		System.out.println("You chose to quit. Quitting now...");
		 	}
		 	else if (playerChoice.equalsIgnoreCase("n") && (currentRoom.getNorth() != null))
		 	{
		 	    validSelection = true;
		 	    System.out.println("Going north...\n");
		 		currentRoom = currentRoom.getNorth();
		 	}
	    		else if (playerChoice.equalsIgnoreCase("e") && (currentRoom.getEast() != null))
		 	{
				
		 	    validSelection = true;
		 	    System.out.println("Going east...");
		 		currentRoom = currentRoom.getEast();
		 	}
		 	else if (playerChoice.equalsIgnoreCase("w") && (currentRoom.getWest() != null))
		 	{
		 	    validSelection = true;
		 	    System.out.println("Going west...\n");
		 		currentRoom = currentRoom.getWest();
		 	}
		     else if (playerChoice.equalsIgnoreCase("s") && (currentRoom.getSouth() != null))
		 	{
		 	    validSelection = true;
		 	    System.out.println("Going south...\n");
		 		currentRoom = currentRoom.getSouth();
		 	}
		 	else
		 	{
		 	    validSelection = false;
		 		System.out.println("Invalid selection!");
		 		System.out.println();
		 	}
	}
// ****************************************************************************************************************************************
    public static void startCombat() 
    {
        
 	    /*Report Combat Stats*/
        //Print the monster's info.
        //Print the player info. 
        System.out.println("Battle the " + monsterName + "!");       
        System.out.println(theMonster);           
        System.out.println(thePlayer);     
        
        thePlayer.takeTurn(theMonster);
        
        // if monster is alive after player takes turn, then monster takes turn
        if (theMonster.getHealth()>0)
	    {
	        theMonster.takeTurn(thePlayer); //
        }
        
    } // end class method runCombat
// **********************************************************************************************************************************************************************************
     public static void endCombat()
     {
          if (theMonster.getHealth() <= 0)
          {
               System.out.printf("You defeated the %s!%n%n", monsterName);
               System.out.printf("You powered up!%nExperience Points: + %d%nHealth: 100%nAttack Power: + 3%n%n", theMonster.getXP());
               thePlayer.setExperience(theMonster);
               thePlayer.setHealth(100); // reset health back to client provided value (i.e 100)
               thePlayer.setAttackPower(thePlayer.getAttackPower()+3); // attack power increases by client provided value (i.e. 3)
               System.out.printf("Final stats:%n%s%n%n", thePlayer);
               System.out.println(currentRoom);
               System.out.println(currentRoom.getExits());
          }
          else if (thePlayer.getHealth() <= 0) // if hero's health is less than or eqal to 0, the combat loop control variable is set to false and the player is informed that he/she was defeated by the monster.
          {
    	          continuePlaying = false; 
    	          System.out.printf("You were defeated by the %s!%n", monsterName);
    	          System.out.println("Game Over!\n");
          }
     }
   

} // end class
