package q2.spacebook;

import java.util.ArrayList;

/**
 * Test class to test messaging functionality
 */
public class TestMessaging {
  
  User homer = new User("Homer","Simpson","h@s.com","secret");
  User barney = new User("Barney","Gumble","b@s.com","secret");
  int numberMessages = 14;
  Message[] messages = new Message[numberMessages];
  ArrayList<Message> messageList = new ArrayList<>();

  /**
   * Constructs a testMessaging object that will be used for testing.
   * Initialises and populates messageList with sample Message objects.
   */
  public TestMessaging() {

    messages[0] = new Message(Subject.PERSONAL, homer, barney,"hi");
    messages[1] = new Message(Subject.PERSONAL, homer, barney,"aloha");
    messages[2] = new Message(Subject.PERSONAL, homer, barney,"bonjour");
    messages[3]= new Message(Subject.PERSONAL,  homer, barney,"howdy");
    messages[4]= new Message(Subject.PERSONAL,  homer, barney,"so long");
    messages[5]= new Message(Subject.PERSONAL,  homer, barney,"adios");
    messages[6]= new Message(Subject.FAMILY,  homer, barney,"shalom");
    messages[7]= new Message(Subject.FAMILY,  homer, barney,"ciao");
    messages[8]= new Message(Subject.FAMILY,  homer, barney,"hola");
    messages[9]= new Message(Subject.WORK,  homer, barney,"tja");
    messages[10]= new Message(Subject.WORK,  homer, barney,"god dag");
    messages[11]= new Message(Subject.GENERAL,  homer, barney,"");
    messages[12]= new Message(Subject.GENERAL,  homer, barney,"xin chao");
    messages[13]= new Message(Subject.WORK,  homer, barney,"ni hao");

    for(int i = 0; i < messages.length; i += 1) {
      messageList.add(messages[i]);
    }
  }

  /** 
   * Calls testAll() to run all tests in class
   */
  public static void main(String[] args) {
    testAll();
  }

  /**
   * Creates a new TestMessaging object and calls all test methods in class.
   */
  public static void testAll() {
    TestMessaging test = new TestMessaging();
    test.displayMessageList();
    test.displayMessageList(Subject.WORK);
    test.sortArrayListMessages();
    test.sortArrayMessages();
  }
  
  /**
   * Tests message sorting on a primitive array of messages.
   * Prints list of unsorted messages, sorts them, and prints sorted list.
   */
  public void sortArrayMessages() {     
    System.out.println("\nMessages");
    System.out.println("-------------------------------------------");
    System.out.println("Message[] Unsorted");
    for(Message message : messages) {
      message.displayMessageContent();
    }

    MessageSort.selectionSort(messages);

    System.out.println("\nMessage[] Sorted");

    for(Message message : messages) {
      message.displayMessageContent();
    }
  }

  /**
   * Tests message sorting on an arraylist of messages.
   * Sends a range of messages, prints the arraylist of unsorted messages, 
   * sorts messages, and prints sorted list of messages.
   */
  public void sortArrayListMessages() {
    //First populate message in & out boxes
    for(Message message : messages) {
      homer.sendMessage(message);
    }
    //Unsorted
    System.out.println("ArrayList<Message> Unsorted");
    homer.displayOutbox();
    MessageSort.selectionSort(homer.outbox);
    //Sorted
    System.out.println("\nArrayList Sorted");
    homer.displayOutbox();  
  }

  /**
   * Test method that displays an arraylist of messages
   */
  public void displayMessageList() {
    homer.displayMessages(messageList);
  }

  /**
   * Test method that displays an arraylist of messages by subject
   * 
   * @param subject Subject to display messages by
   */
  public void displayMessageList(Subject subject) {
    homer.displayMessages(subject, messageList);
  }
  
}