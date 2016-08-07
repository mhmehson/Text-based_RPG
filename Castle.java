/**Castle.java
 * 3/20/2016
 * Author: Mohammad Ehson
 */
 
public class Castle
{
     // instance variables hold references to Room objects
     private Room entrance;
     private Room gallery;
     private Room greatHall;
     private Room kitchen;
     private Room diningHall;
     private Room library; 
     
     public Castle()
     {
		 // Room objects initilaized by passing description and probability of creating monster in that Room to constructor)
          entrance = new Room("You are in the Entrance Hall. Explore the castle!", 10); 
          gallery = new Room("You are in the Gallery. There's some impressive artwork in here.", 30);
          greatHall = new Room("You are in the Great Hall. No events going on today.", 30);
          kitchen = new Room("You are in the Kitchen: Help yourself.", 30);
          diningHall = new Room("You are in the Dining Hall: Don’t bother having a seat. No one’s around to serve you.", 30);
          library = new Room("You are in the Library. You may find a good read under all the dust.", 30);
          
          
          /* Each Room's exits are set by calling method setExit() and 
           * the description for the possible exits is created by calling setPossibleExits()
           */
          entrance.setExits(null, library, gallery, null);
          entrance.setPossibleExits();

          gallery.setExits(greatHall, entrance, null, null);
          gallery.setPossibleExits();
          
          greatHall.setExits(null, kitchen, null, gallery);
          greatHall.setPossibleExits();

          kitchen.setExits(null, diningHall, greatHall, null);
          kitchen.setPossibleExits();

          diningHall.setExits(null, null, kitchen, library);
          diningHall.setPossibleExits();

          library.setExits(diningHall, null, entrance, null);
          library.setPossibleExits();
          

     }
     
     // method returns initial Room where gameplay starts
     public Room getRoom0()
     {
          return entrance;
     }
     
}
 
