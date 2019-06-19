package q2.spacebook;

/**
 * Admin class that calls test methods
 */
public class Admin {
  
  /**
   * Calls testAll() to test Spacebook and Spacebook messaging functionality
   */
  public static void main(String[] args) {
    testAll();
  }
  
  /**
   * Calls testAll() in TestSpacebook and TestMessaging classes
   */
  public static void testAll() {
    TestSpacebook.testAll();
    TestMessaging.testAll();
  }
}
