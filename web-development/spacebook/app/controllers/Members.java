package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 *  @file					Members.java
 *  @description
 *		Members java controller for the associated Members html views in 
 *    the Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class Members extends Controller
{
  /**
   * Retrieves a list of all site users except the current user, and renders the
   * Members.index view with this list of users.
   */
  public static void index()
  {
    try
    {
      List<User> users = User.findAll();

      // remove logged in user from member list that is rendered
      String userId = session.get("logged_in_userid");
      User thisUser = User.findById(Long.parseLong(userId));
      users.remove(thisUser);

      render(users);
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
   * Adds a user to the current users friend list.
   * Renders Home.index view after new friend is added.
   * 
   * @param id id of the user to be friended
   */
  public static void follow(Long id)
  {
    try
    {
      User friend = User.findById(id);
  
      String userId = session.get("logged_in_userid");
      User me = User.findById(Long.parseLong(userId));
  
      me.befriend(friend);
      Home.index();
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

}