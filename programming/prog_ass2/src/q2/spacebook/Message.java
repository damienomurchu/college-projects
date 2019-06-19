package q2.spacebook;

/**
 * Class to represent a Message object.
 */
public class Message {
  
  /**
   * Subject of the Message
   */
  Subject   subject;
  
  /**
   * Text of the Message
   */
  String    messageText;
  
  /**
   * Sender of the Message
   */
  User      from;
  
  /**
   * Recipient of the Message
   */
  User      to;
  
  /**
   * Constructs a Message object
   * 
   * @param subject Subject of the message
   * @param from Sender of the message
   * @param to Recipient of the message
   * @param messageText Text of the message
   */
  public Message(Subject subject, User from, User to, String messageText) {
    // TODO
    this.from           = from;
    this.to             = to;
    this.messageText    = messageText;
    this.subject = subject;
  } 

  /**
   * Prints the details of a Message object
   */
  public void displayMessage() {
    String nameFrom = from.firstName;
    String nameTo   = to.firstName;
    // TODO
    System.out.print("Message Subject: " + subject + " " + nameFrom + " says \"" + messageText + "\" to " + nameTo + "\n");
  }

  /**
   * Prints the messageText of a Message object
   */
  public void displayMessageContent() {
    System.out.println(messageText);
  }
}