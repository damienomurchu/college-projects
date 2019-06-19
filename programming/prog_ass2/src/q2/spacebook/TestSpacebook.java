package q2.spacebook;

/**
 * Test class to test Spacebook functionality
 * @
 */
public class TestSpacebook {
  
  /**
   * Number of Users to set size of User[] array
   */
  int nmrUsers    = 3;
  
  /**
   * Number of Messages to set size of Message[] array
   */
  int nmrMessages = 2;
  
  /**
   * User[] array for testing purposes
   */
  User[] users    = new User[nmrUsers];
  
  /**
   * Message[] array for testing purposes
   */
  Message[] msg 	= new Message[nmrMessages];

  /**
   * Creates a new TestSpacebook object.
   * Creates sample users and messages for testing purposes.
   */
  public TestSpacebook() {    
    users[0] = new User("Homer", "Simpson", "homer@simpson.com", "secret");
    users[1] = new User("Barney", "Gumble", "barney@gumble.com", "secret");
    users[2] = new User("Bart", "Simpson", "bart@simpson.com", "secret");    

    msg[0] = new Message(Subject.PERSONAL, users[0], users[1], "hi Barney");
    msg[1] = new Message(Subject.PERSONAL, users[0], users[2], "hi");
  }

  /**
   * Tests User.befriend() method.
   * Attempts to befriend several users, including ones self, and an already existing friend
   */
  public void befriend() {    
    System.out.println("Creating friends");
    System.out.println("===========================================");
    users[0].befriend(users[1]);
    users[0].befriend(users[2]);

    // Attempt to befriend oneself
    users[0].befriend(users[0]);

    // Attempt to befriend existing friend
    users[0].befriend(users[1]);	

    System.out.println("\nFriends list");
    System.out.println("===========================================");
    users[0].displayFriends();
  }

  /**
   * Tests Message.displayMessage() method.
   * Attempts to display several messages with displayMessage(). 
   */
  public void displayMessages() {   
    System.out.println("\nMessages");
    System.out.println("===========================================");
    for (int i = 0; i < msg.length; i++) {
      msg[i].displayMessage();
    }
  }

  /**
   * Tests User.addGroup() and User.addGroupMember() methods.
   * Attempts to create a new Group and add several members to it.
   */
  public void addGroups() {
    System.out.println("\nGroup name and members");
    System.out.println("===========================================");
    users[0].addGroup("Friends");
    users[0].addGroupMember("Friends", users[1]);
    users[0].addGroupMember("Friends", users[2]);
    System.out.println(users[0].groups.get("Friends"));

  }

  /**
   * Tests User.sendMessage() method.
   * Attempts to send a message, and checks recipients inbox and senders outbox 
   * to check if successful.
   */
  public void sendMessages() {
    System.out.println("\nTesting inbox and outbox");
    System.out.println("===========================================");
    users[0].sendMessage(msg[0]);
    System.out.println(users[0].firstName + "'s Outbox");
    users[0].displayOutbox();
    System.out.println("-------------------------------------------");
    System.out.println(users[1].firstName + "'s Inbox");
    users[1].displayInbox();
    System.out.println("-------------------------------------------");        
  }

  /**
   * Tests User.displayFriends() method.
   * Utilises befriend() and setStatus(), and calls displayFriends() with and without
   * status arguments to check if functioning as expected.
   */
  public void displayFriends() {
    System.out.println("Invoking befriend() to ensure Homer has some friends");
    this.befriend();

    System.out.println("\nFiltering friend list based on status");
    System.out.println("===========================================");

    System.out.println("Changing Barney's status to OFFLINE");
    users[1].setStatus(Status.OFFLINE);
    users[2].setStatus(Status.BUSY);
    System.out.println("\nList of all friends");
    System.out.println("-------------------------------------------");
    users[0].displayFriends();
    System.out.println("\nList of OFFLINE friends");
    users[0].displayFriends(Status.OFFLINE);
    System.out.println("-------------------------------------------");
  }

  /**
   * Creates new TestSpacebook object and calls all class test methods.
   */
  public static void testAll() {
    TestSpacebook test = new TestSpacebook();
    test.displayFriends();
    test.befriend();
    test.addGroups();
    test.displayMessages();
    test.sendMessages();
  }

  /**
   * Calls testAll() to test all class test methods
   */
  public static void main(String[] args) {
    testAll();
  }

}