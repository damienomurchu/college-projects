package controllers;

import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 *  @file					Profile.java
 *  @description
 *		Profile java controller for the associated Profile html views in 
 *    the Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class Profile extends Controller
{
  /**
   * Renders the Profile.index view with the current user. 
   * Accounts.index view rendered if no current user.
   */
  public static void index()
  {    
    try
    {
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
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
   * Renders the Profile.editProfile view with the current user. 
   * Accounts.index view rendered if no current user.
   */
  public static void editProfile()
  {
    try
    {
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
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
   * Updates the users status with the new profile text submitted.
   * Profile.index view rendered upon status change.
   * 
   * @param profiletext the new status text
   */
  public static void changeStatus(String profiletext)
  {
    try
    {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    
    user.statusText = profiletext;
    user.save();
    
    Logger.info("Status update: " + profiletext);
    
    index();
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
   * Retrieves and set the users profile picture
   * 
   * @param id id of the user in question
   */
  public static void getPicture(Long id)
  {
    try
    {
      User user = User.findById(id);
      
      Blob picture = user.profilePicture;
      if (picture.exists())
      {
        response.setContentTypeIfNotSet(picture.type());
        renderBinary(picture.get());
      }
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
   * Sets and saves a new picture as the users profile picture.
   * 
   * @param id id of the user
   * @param picture the new picture to update the users current picture with
   */
  public static void uploadPicture(Long id, Blob picture)
  {
    try
    {
      User user = User.findById(id);
      
      user.profilePicture = picture;
      user.save();
      
      index();
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