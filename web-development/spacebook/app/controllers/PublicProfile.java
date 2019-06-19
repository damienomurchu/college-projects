package controllers;

import play.*;
import play.mvc.*;
import utils.MessageDateComparator;

import java.util.*;

import models.*;

/**
 *  @file					PublicProfile.java
 *  @description
 *		PublicProfile java controller for the associated PublicProfile html views 
 *    in the Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class PublicProfile extends Controller
{
  /**
   * Renders the PublicProfile.visit view for the specified user.
   * Redirects to the Profile.index view for the current user, if the current 
   * user and the user specified to visit are the same.
   * 
   * @param id id of the user to visit
   */
  public static void visit(Long id)
  {
    try
    {
      Long loggedInUserId = Long.parseLong(session.get("logged_in_userid"));
      
      if (id == loggedInUserId)
      {
        Logger.info("Tried to visit the public profile of logged in user. "
            + "Redirected to logged in user's private profile instead");
        Profile.index();
      }
      else
      {
        User user = User.findById(id);
        Logger.info("Visiting the public profile for : " + user.firstName + " " + user.lastName);
        
        Collections.sort(user.inbox, new MessageDateComparator());
        Collections.reverse(user.inbox);
        
        render(user);        
      }
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
   * Sends a message from the current/ logged in user to the specified user.
   *  
   * @param id id of the user to message
   * @param subject subject of the message
   * @param messageText message text of the message
   */
  public static void sendMessage(Long id, String subject, String messageText)
  {
    String userId = session.get("logged_in_userid");
    User fromUser = User.findById(Long.parseLong(userId));
    User toUser = User.findById(id);

    Logger.info(
        "Message from user " + fromUser.firstName + " to " + toUser.firstName + " : <" + subject + "> " + messageText);

    fromUser.sendMessage(toUser, subject, messageText);
    visit(id);
  }

}