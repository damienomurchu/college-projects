package q1.auction;

import java.util.HashSet;
/**
 * Class to manage an audience
 * Has set of people and methods to register and authenticate new members
 * 
 * @author jf
 * @version 14.3.2015
 *
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 *
 */
public class Audience {
  /**
   * Set of people comprising the members of the audience. 
   * Set used to ensure duplicate registration does not occur
   */
  HashSet<Person> members;
  
  /**
   * Constructs an Audience object. Initializes the members field
   */
  public Audience() {
    members = new HashSet<Person>();
  }
  
  /**
   * Registers a person as a member of the audience
   * 
   * @param person an instance of Person register as member of members
   * @return returns true if the person successfully added to set else false
   */
  public boolean register(Person person) { 
    // TODO 1
    return members.add(person);
  }
  
  /**
   * Authenticates a person is a member of the audience
   * 
   * @param person The person being authenticated
   * @return Returns true if the person is a member of the audience otherwise returns false.
   */
  public boolean isRegistered(Person person) {
    // TODO 2
    return members.contains(person);
  }
  
  /**
   * Authenticates person is member of audience 
   * Information provided is email and pin
   * 
   * @param email The email address of person being authenticated
   * @param pin The pin number of person being authenticated
   * @return Returns true if the person is a member of the audience otherwise returns false.
   */
  public Person isRegistered(String email, int pin) {
    
    // check if person is in audience
    for (Person person : members) {
      // TODO 3
      if (person.email == email && person.getPin() == pin) {
    	  return person;
      }
    }
    
    // return null if not present
    return null;
  }
}
