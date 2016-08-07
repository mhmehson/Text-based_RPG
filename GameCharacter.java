/**GameCharacter.java
 * 04/15/2016
 * Author: Mohammad Ehson
 */
 
public abstract class GameCharacter
{
     private String name; // character's name
     private int health; // character's health
     private int attackPower; // character's attack power
     
     // constructor
     
     public GameCharacter(String name, int health, int attackPower)
     {
          this.name = name;
          this.health = health;
          this.attackPower = attackPower;
     }
     
     public void takeDamage(int damage)
     {
          health = health - damage;
     }
     
     // get methods
     
     public String getName()
     {
          return name;
     }
     
     // methods for setting and getting attackPower and health
     
     public void setAttackPower(int attackPower)
     {
		 this.attackPower = attackPower;
	 }
     public int getAttackPower()
     {
          return attackPower;
     }
     
     public void setHealth(int health)
     {
          this.health = health;
     }
     public int getHealth()
     {
          return health;
     }
     
     // toString method, returns string representation of object
     public String toString()
     {
          return String.format("Name: %s%nHealth: %d%nAttack Power: %d", name, health, attackPower);
     }
}
