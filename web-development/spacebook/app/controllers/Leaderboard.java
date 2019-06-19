package controllers;

import play.*;
import play.mvc.*;
import utils.UserNameComparator;
import utils.UserPopularComparator;
import utils.UserSocialComparator;
import utils.UserTalkativeComparator;

import java.util.*;

import models.*;

/**
 *  @file					Leaderboard.java
 *  @description
 *		Leaderboard java controller for the associated Leaderboard html views in 
 *    the Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class Leaderboard extends Controller
{
  /**
   * Renders the Leaderboard.index view, with all site users sorted by name
   */
  public static void index()
  {
    try
    {
      // to check if a logged in user
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      List<User> users = User.findAll();
      Collections.sort(users, new UserNameComparator());
      
      render(users);
    }
    catch(NullPointerException e)
    {
      Accounts.index();
    }
    catch (NumberFormatException e)
    {
      Accounts.index();
    }    
  }

  /**
   * Sorts a list of all site users by the size of their inbox.
   * Leaderboard.popular view is rendered with the sorted list of users.
   */
  public static void popular()
  {
    try
    {
      // to check if a logged in user
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      List<User> users = User.findAll();
      Collections.sort(users, new UserPopularComparator());
      
      render(users);
    }
    catch(NullPointerException e)
    {
      Accounts.index();
    }
    catch(NumberFormatException e)
    {
      Accounts.index();
    }
  }

  /**
   * Sorts a list of all site users by the size of their outbox.
   * Leaderboard.talkative view is rendered with the sorted list of users.
   */
  public static void talkative()
  {
    try
    {
      // to check if a logged in user
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      List<User> users = User.findAll();
      Collections.sort(users, new UserTalkativeComparator());
      
      render(users);
    }
    catch(NullPointerException e)
    {
      Accounts.index();
    }  
    catch(NumberFormatException e)
    {
      Accounts.index();
    }  
  }

  /**
   * Sorts a list of all site users by the size of their friend list.
   * Leaderboard.social view is rendered with the sorted list of users.
   */
  public static void social()
  {
    try
    {
      // to check if a logged in user
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      List<User> users = User.findAll();
      Collections.sort(users, new UserSocialComparator());
      render(users);
    }
    catch(NullPointerException e)
    {
      Accounts.index();
    }
    catch(NumberFormatException e)
    {
      Accounts.index();
    }  
  }

}