package models;

import java.util.ArrayList;
import java.util.Date;

public class Message
{
  /**
   * Text of the message
   */
  public String messageText;
  
  /**
   * Sender of the message
   */
  public User from;
  
  /**
   * Target recipient for the message
   */
  public User to;
  
  /**
   * Date of the message
   */
  public Date postedAt;

  /**
   * Creates a message from one user to another
   * 
   * @param from sender of the message 
   * @param to recipient of the message
   * @param messageText text of the message
   */
  public Message(User from, User to, String messageText)
  {
    this.from = from;
    this.to = to;
    this.messageText = messageText;
    postedAt = new Date();
    pause();
  }

  /*
   * Pause briefly to ensure the Date postedAt values are sufficiently different
   * to allow sorting. Here 10 milliseconds are used with effect but this figure
   * may vary on other systems.
   */
  private void pause()
  {
    try
    {
      Thread.sleep(10);
    }
    catch (InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public String toString()
  {
    return this.from.firstName + " says... " + this.messageText;
  }

  /**
   * Prints the message to screen
   */
  public void displayMessage()
  {
    System.out.println(toString());
  }

  /**
   * Prints all messages contained in list of messages to screen
   * 
   * @param messages list of messages to be displayed
   */
  public static void displayMessage(ArrayList<Message> messages)
  {
    for (Message message : messages)
    {
      message.displayMessage();
    }
  }
  
}