package q1.auction;

/**
 * Class contains personal details of person
 * 
 * @author jf
 * @version 07.3.2016
 *
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 *
 */
public class Person {

  /**
   * First name of Person
   */
  private final String firstName;
  
  /**
   * Last name of Person
   */
  private final String lastName;
  
  /**
   * Email of Person
   */
  String email;

  /**
   * Pin number of person
   */
  private int pin;

  /**
   * Construct Person object.
   * Initialises: first name, last name, email, pin
   * 
   * @param firstName first name
   * @param lastName last name
   * @param email email
   * @param pin pin number
   */
  public Person(String firstName, String lastName, String email, int pin) {
    this.firstName = firstName;
    this.lastName = lastName;
    // TODO 1 
    this.email = email;
    this.pin = pin;
  }

  /**
   * Accessor for pin number
   * 
   * @return The pin number
   */
  public int getPin() {
    return pin;
  }

  /**
   * Generates a String representation of Person object
   * 
   * @return The string representation of Person object
   */
  public String toString() {
    return "Full name: " + firstName + " " + lastName + " : Email : " + email;
  }
}
