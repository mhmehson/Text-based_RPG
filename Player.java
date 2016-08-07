/**Player.java
 * 04/15/2016
 * Author: Mohammad Ehson
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class Player extends GameCharacter
{
     private int mana; // player has magic power as an attribute
     private int experience = 0; // player gains experience after defeating monsters
     
     // constructor
     public Player(String name, int health, int attackPower, int mana)
     {
          super(name, health, attackPower);
          
          this.mana = mana;
     }
     
     // method for attacking monster
     public void attack(Monster theMonster)
     {
         int health = theMonster.getHealth() - this.getAttackPower();
         
         theMonster.setHealth(health);
     }
     
     // method for casting spell on monster
     public void castSpell(Monster theMonster)
     {
          if (mana >=3) // 3 magic power points are need to cast spell
          {
               int health = theMonster.getHealth()/2;
               theMonster.setHealth(health);
               mana = mana - 3;
               System.out.println(); //empty line
          }
          else
          {
               System.out.println("You don't have enough mana."); // if player doesn't have atleast 3 magic power points, player is informed.
               System.out.println(); //empty line
          } 
     }
     
     // method for charging mana
     public void chargeMana()
     {
          mana++; // magic power is incremented by 1
          System.out.println("You focus and charge your magic power.");
          System.out.println();
     }
     
     // method allows player to choose next move during battle
     public void takeTurn(Monster theMonster)
     {
		  //Create a new Scanner object.
          Scanner input = new Scanner(System.in);
          boolean continueLoop = true;
          do
	      {
               try
               {
				    System.out.println("\nCombat options:");
					System.out.println("  1.) Sword Attack");
					System.out.println("  2.) Cast Spell");
					System.out.println("  3.) Charge Mana");
					System.out.print("Your choice:  ");
					System.out.println();// empty line
					//Declare variable for user's input (as an int) and store int acquired from scanner into variable.
                    int playerSelection = input.nextInt(); // read player input
                    // use multi-selection statement to carry out hero's next move based on player's selection from given options
                    switch (playerSelection)
                    {
             	    case 1:
             	         System.out.println("You strike the monster with your sword!");
                  		 attack(theMonster); // call method for sword striking
                  		 continueLoop = false; // input successful, end looping
                  		 break;
                    case 2:
                         System.out.println("You cast the weaken spell on the monster!");
                      	castSpell(theMonster); // call method for casting spell
                      	continueLoop = false; // input successful, end looping
                      	break;
                    case 3: 
                         System.out.println("You focus and charge your magic power!");
                         chargeMana(); // call method for charging magic power
                         continueLoop =false; // input successful, end looping
                         break;
                    default:
                 	     System.out.println(); // empty line
                 	     System.out.println("Invalid selection. Please try again.");
                 	     continueLoop = true; // input correct type, but not correct value, continue looping
                    }
               }
               catch (InputMismatchException e)
               {
				   input.nextLine(); // discard input so user can try again
				   System.out.println(); // empty line
				   System.out.println("Invalid selection. Please try again.");
               }
          }while (continueLoop);
     }
     
     // method for getting mana value
     public int getMana()
     {
          return mana;
     }
     
     // methods for updating and getting player's exeperience points
     public void setExperience(Monster theMonster)
     {
          experience = experience + theMonster.getXP();
     }
     public int getExperience()
     {
          return experience;
     }
     
     // toString method
     @Override
     public String toString()
     {
          if (experience > 0)
          {
               return String.format("%s%n%s%d%n%s%d%n", super.toString(), "Mana: ", mana, "Experience: ", experience);
          }
          else
          {
			  return String.format("%s%n%s%d%n", super.toString(), "Mana: ", mana);
		  }
     }
}
