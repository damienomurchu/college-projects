package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import utils.MessageDateComparator;
import utils.MessageFromComparator;
import utils.UserNameComparator;

import java.util.*;

import models.*;

/**
 *  @file					Home.java
 *  @description
 *		Home java controller for the associated Home html views in the 
 *    Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class Home extends Controller
{
  /**
   * Renders the default Home view for the logged in user, otherwise redirects to 
   * the Accounts.index view if user cannot be identified.
   */
  public static void index()
  {
    byDate();
  }

  /**
   * Drop a friend from the users list of friendships. 
   * Renders the Home.index view.
   * 
   * @param id id of the friend to drop
   */
  public static void drop(Long id)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));

    User friend = User.findById(id);

    user.unfriend(friend);
    Logger.info("Dropped " + friend.firstName + " as a friend.");

    index();
  }

  /**
   * Sorts the message view in Home by user that sent the message.
   * Renders sorted Home.byUser view (or Accounts.index if user can't be found).
   */
  public static void byUser()
  {
    try
    {
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      Collections.sort(user.inbox, new MessageFromComparator());
      
      render(user);
    }
    catch (NullPointerException e)
    {
      Accounts.index();
    }
    catch (NumberFormatException e)
    {
      Accounts.index();
    }
  }

  /**
   * Sorts the message view in Home by the date that the message was sent.
   * Renders sortedHome.byDate view(or Accounts.index if user can't be found).
   */
  public static void byDate()
  {
    try
    {
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      Collections.sort(user.inbox, new MessageDateComparator());
      Collections.reverse(user.inbox);
      
      render(user);
    }
    catch (NullPointerException e)
    {
      Accounts.index();
    }
    catch (NumberFormatException e)
    {
      Accounts.index();
    }
  }

  /**
   * Sorts the message view in Home by individual conversation with each user.
   * Renders sorted Home.byConv view (or Accounts.index if user can't be found).
   */
  public static void byConv()
  {
    try
    {
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      HashSet<User> messageUsers = new HashSet<User>();
      
      for (Message message : user.inbox)
      {
        messageUsers.add(message.from);
      }
      
      for (Message message : user.outbox)
      {
        messageUsers.add(message.to);
      }   
      
      ArrayList<ArrayList<Message>> conversations = new ArrayList<ArrayList<Message>>();

      for (User messageUser : messageUsers)
      {
        conversations.add(getConv(user, messageUser));
      }
      
      render(user, conversations);
    }
    catch (NullPointerException e)
    {
      Accounts.index();
    }
    catch (NumberFormatException e)
    {
      Accounts.index();
    }
  }

  /**
   * Helper method that retrieves all messages between two users, returning them
   * in an arraylist of that conversation.
   * Returned arraylist is sorted by date.
   * 
   * @param user first user in conversation
   * @param friend second user in conversation
   * @return an arralist of all messages between the specified users
   */
  private static ArrayList<Message> getConv(User user, User friend)
  {
    ArrayList<Message> ar = new ArrayList<Message>();

    for (Message message : user.inbox)
    {
      if (message.from == friend)
      {
        ar.add(message);
      }
    }

    for (Message message : user.outbox)
    {
      if (message.to == friend)
      {
        ar.add(message);
      }
    }

    Collections.sort(ar, new MessageDateComparator());

    return ar;
  }

}