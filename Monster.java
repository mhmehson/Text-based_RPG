/**Monster.java
 * 04/15/2016
 * Mohammad Ehson
 */
 
public class Monster extends GameCharacter
{
     private int xP; // each monster has an experience value that player will get for defeating it.
     
     // constructor
     public Monster(String name, int health, int attackPower, int xP)
     {
          super(name, health, attackPower);
          
          this.xP = xP;
     }
     
     // method allows monster to strike player
     public void attack(Player thePlayer)
     {
         System.out.println("The monster strikes you back for " + this.getAttackPower() + " damage!"); // Monster attacks hero(player)
         int health;
         health = thePlayer.getHealth() - this.getAttackPower(); //new hero health equals old hero health minus monster attack power
         thePlayer.setHealth(health);
         System.out.println(); // empty line
     }
     
     // method allows mosnter to take it's turn during combat. Can only attack (calls attack method)
     public void takeTurn(Player thePlayer)
     {
          attack(thePlayer);
     }
     
     // method for getting experience points
     public int getXP()
     {
          return xP;
     }
     
     // toSring method
     @Override
     public String toString()
     {
          return String.format("%s%nXP: %d%n", super.toString(), xP);     
     }
     
}
