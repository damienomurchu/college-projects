package models;

import java.util.ArrayList;
import java.util.Iterator;

public class User
{
  public String firstName;
  public String lastName;
  int age;
  String nationality;
  String email;
  String password;

  /**
   * A list of the users friendships
   */
  public ArrayList<Friendship> friendships = new ArrayList<>();
  
  /**
   * A list of the users inbox messages
   */
  public ArrayList<Message> inbox = new ArrayList<>();

  /**
   * A list of the users outbox messages
   */
  public ArrayList<Message> outbox = new ArrayList<>();

  /**
   * Creates a user from the specified parameters
   * 
   * @param firstName first name of user
   * @param lastName last name of user
   * @param age age of user
   * @param nationality nationality of user
   * @param email email address of user
   * @param password password of user
   */
  public User(String firstName, String lastName, int age, String nationality, String email, String password)
  {
    setState(firstName, lastName, age, nationality, email, password);
  }

  /**
   * Creates a user from the specified parameters
   * 
   * @param firstName first name of user
   * @param lastName last name of user
   * @param email email address of user
   * @param password password of user
   */
  public User(String firstName, String lastName, String email, String password)
  {
    setState(firstName, lastName, 100, "USA", email, password);
  }

  /**
   * Setter method to set the attributes of a user
   * 
   * @param firstName first name of user
   * @param lastName last name of user
   * @param age age of user
   * @param nationality nationality of user
   * @param email email address of user
   * @param password password of user
   */
  private void setState(String firstName, String lastName, int age, String nationality, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.nationality = nationality;
    this.email = email;
    this.password = password;
  }

  /**
   * Sends a message to all a users friends
   * 
   * @param messageText text of the message
   */
  public void broadcastMessage(String messageText)
  {
    for (Friendship f : friendships)
    {
      Message message = new Message(this, f.targetUser, messageText);
      outbox.add(message);
      f.targetUser.inbox.add(message);
    }
  }

  /**
   * Sends a message to a user
   *  
   * @param to intended recipient of the message
   * @param messageText text of the message
   */
  public void sendMessage(User to, String messageText)
  {
    Message message = new Message(this, to, messageText);
    outbox.add(message);
    to.inbox.add(message);
  }

  /**
   * Displays all the messages in a users outbox
   */
  public void displayOutbox()
  {
    for (Message msg : outbox)
    {
      msg.displayMessage();
    }
  }

  /**
   * Displays all the messages in a users inbox
   */
  public void displayInbox()
  {
    for (Message msg : inbox)
    {
      msg.displayMessage();
    }
  }

  /**
   * Adds a user as a friend.
   * Checks if target friendship target is a valid one.
   * 
   * @param friend
   */
  public void befriend(User friend)
  {
    if (!(friend == this))
    {
      Friendship friendship = new Friendship(this, friend);
      friendships.add(friendship);
    }
    else
    {
      System.out.println("Opps! You seem to have made a mistake in attempting to befriend yourself");
    }
  }

  /**
   * Deletes all a users friendships
   */
  public void unfriendAll()
  {
    Iterator<Friendship> it = friendships.iterator();
    while (it.hasNext())
    {
      it.next();
      it.remove();
    }
  }

  /**
   * Deletes a specific friendship
   * 
   * @param friend user to be removed as a friend
   */
  public void unfriend(User friend)
  {
    for (Friendship friendship : friendships)
    {
      if (friendship.targetUser == friend)
      {
        friendships.remove(friendship);
        return;
      }
    }
  }

  /**
   * Displays all a users friends to screen
   */
  public void displayFriends()
  {
    if (friendships.isEmpty())
    {
      System.out.println("Unfortunately you have no friends");
    }
    for (Friendship friendship : friendships)
    {
      System.out.println("My friend is " + friendship.targetUser.firstName);
    }
  }

}