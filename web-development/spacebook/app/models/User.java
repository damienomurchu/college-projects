package models;

import javax.persistence.Entity;

import play.Logger;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *  @file					User.java
 *  @description
 *		User model class to represent site users in the Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
@Entity
//@Table(name="`User`") //This is necessary because User is a reserved word in PostGreSQL
public class User extends Model
{
  public String firstName;
  public String lastName;
  public String age;
  public String nationality;
  public String statusText;
  public String email;
  public String password;
  public Blob profilePicture;

  @OneToMany(mappedBy = "sourceUser")
  public List<Friendship> friendships = new ArrayList<Friendship>();

  @OneToMany(mappedBy = "to")
  public List<Message> inbox = new ArrayList<Message>();

  @OneToMany(mappedBy = "from")
  public List<Message> outbox = new ArrayList<Message>();

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
  public User(String firstName, String lastName, String age, String nationality, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.nationality = nationality;
    this.email = email;
    this.password = password;
  }

  /**
   * Finds a user from the db by email. 
   * Returns first user found.
   * 
   * @param email email of user being searched for
   * @return the user found in the db
   */
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  /**
   * Checks if specified password matches this users password
   * 
   * @param password password being checked 
   * @return true if passwords match; else false
   */
  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }

  /**
   * Creates a new friendship between this user and specified friend target.
   * Checks if friendship target is valid before any friendship is created.
   * 
   * @param friend friendship target
   */
  public void befriend(User friend)
  {
    // check if friendship target valid
    if (!validFriend(friend))
    {
      Logger.info("Invalid friendship target!");
      return;
    }
    else
    {
      Friendship friendship = new Friendship(this, friend);
      friendships.add(friendship);
      
      Logger.info("Added a new friendship between " + this.firstName + " and " + friend.firstName);
      
      friendship.save();
      save();
    }
  }

  /**
   * Helper method that checks if friend target is a valid one.
   * Checks that target friend is not already an existing friend, and also that 
   * the friendship target and the current user are not one and the same.
   * 
   * @param friend friendship target
   * @return false if not a valid friend target; otherwise true 
   */
  private boolean validFriend(User friend)
  {
    for (Friendship friendship : friendships)
    {
      if ((friendship.targetUser == friend) || (this == friend))
      {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if friend is contained in list of users friendships, and if so, 
   * removes them from it.   * 
   * 
   * @param friend friend to be removed
   */
  public void unfriend(User friend)
  {
    Friendship thisFriendship = null;

    for (Friendship friendship : friendships)
    {
      if (friendship.targetUser == friend)
      {
        thisFriendship = friendship;
      }
    }

    friendships.remove(thisFriendship);
    thisFriendship.delete();
    
    save();
  }

  /**
   * Creates and sends a message to the target user
   * 
   * @param to user to send the message to 
   * @param subject subject of the message
   * @param messageText message text of the message
   */
  public void sendMessage(User to, String subject, String messageText)
  {
    Message message = new Message(this, to, subject, messageText);
    
    outbox.add(message);
    to.inbox.add(message);
    
    message.save();
  }

}
