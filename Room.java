/**Room.java
 * 3/20/2016
 * Author: Mohammad Ehson
 */
 
import java.security.SecureRandom;

public class Room
{
     // instance variables 
     private String possibleExits; // wString that will hold possible exits for each room.
     private String description;
     private Room north;
     private Room east;
     private Room west;
     private Room south;
     private int probability; // probability that a monster appears in room
     private boolean monsterGenerated; // boolean true if monster generated and fals eif not generated.

     // constructor 
     public Room(String description, int probability) // constructor, sets room's description and number of monsters in each room.
     {
          this.description = description;
          this.probability = probability;
          
     }
     
     // methods to set exits individually 
     public void setNorth(Room north)
     {
          this.north = north;
     }
     public void setEast(Room east)
     {
          this.east = east;
     }
     public void setWest(Room west)
     {
          this.west = west;
     }
     public void setSouth(Room south)
     {
          this.south = south;
     }
     
     // method for setting all exits at once
     public void setExits(Room n, Room e, Room w, Room s)
     {
          this.north = n;
          this.east = e;
          this.west = w;
          this.south = s;
     }
     
     // method creates String that describes each room's available exits
     public void setPossibleExits()
     {
          if (north != null && east != null)
               possibleExits = "Exits are north and east";
          if (north != null && west != null)
               possibleExits = "Exits are north and west.";
          if (north != null && south != null)
               possibleExits = "Exits are north and south.";
          if (east != null && west != null)
               possibleExits = "Exits are east and west.";
          if (east != null && south != null)
               possibleExits = "Exits are east and south.";
          if (west != null && south != null)
               possibleExits = "Exits are west and south.";
     }
     
     // method to decide if monster is generated or not
     public void monsterGenerator()
     {
          SecureRandom randomNumber = new SecureRandom(); // Create SecrureRandom number generator
          int number = randomNumber.nextInt(probability/10);
          if (number == 1)
          {
               monsterGenerated = true;
          }
          else 
          {
               monsterGenerated = false;
          }
          
     }
     
     // method returns boolean indicating whether monster should be generated or not
     public boolean isMonsterGenerated()
     {
          return monsterGenerated; 
     }
     
     // get methods
     
     public Room getNorth()
     {
          return north;
     }
     
     public Room getEast()
     {
          return east;
     }
     
     public Room getWest()
     {
          return west;
     }
     
     public Room getSouth()
     {
          return south;
     }
     
     public String getDescription()
     {
          return description;
     }
     public String getExits()
     {
          return possibleExits;
     }
     public String toString()
     {
          return description;
     }
     
} // end class Roomsjava 
