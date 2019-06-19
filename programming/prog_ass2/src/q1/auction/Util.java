package q1.auction;

import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * A utility class containing static methods as follows:
 * selection sort
 * pin number generators
 * validation methods
 * 
 * @author jf
 * @version 07.3.2016
 */
public class Util {
  private final static int NUMBER_DIGITS = 6;
  
  /**
   * Randomly generate a pin number
   * 
   * @param numberDigits The number of digits in pin
   * @return The randomly generated pin
   */
  public static int generatePin(int numberDigits) {
    String pin = new String();

    for (int i = 0; i < numberDigits; i += 1) {
      pin += Byte.toString((byte) (Math.random() * 9 + 1));
    }
    return Integer.parseInt(pin);
  }

  /**
   * Randomly generate a pin number with NUMBER_DIGITS digits
   * 
   * @return The randomly generated pin length NUMBER_DIGITS
   */
  public static int generatePin() {
    return generatePin(NUMBER_DIGITS);
  }

  /**
   * Generates a random number in range [min max]
   * 
   * @param min The lowest possible value inclusive of the random number generated
   * @param max The highest possible value inclusive of the random number generated
   * @return The random number in range [min max]
   */
  public static int randomIntInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt((max - min) + 1) + min;
  }

  /**
   * Checks if an email address correctly formatted
   * 
   * @param email The email address to be validated
   * @return Returns true if the address format valid else false
   *                Note only formate validated, not existence of address
   */
  public static boolean validEmail(String email) {
    boolean isValid = false;
    InternetAddress address;
    try {
      address = new InternetAddress(email);
      address.validate();
      isValid = true;
    }
    catch (AddressException e) {
      System.out.println("Please supply email address in valid format");
    }
    return isValid;
  }
  
  /**
   * Validate pin: Check it is 
   * a positive number and 
   * does not contain zero and
   * has at least 4 digits
   * 
   * @param pin
   * @return
   */
  public static boolean validPin(int pin) {
    if (pin < 1000  || String.valueOf(pin).contains("0")) {
      return false;
    }
    return true;    
  }
}
