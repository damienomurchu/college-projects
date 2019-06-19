package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

/**
 *  Accounts controller that represents all user account functionality
 *		
 *  @since				31 May 2016
 */
public class Accounts extends Controller
{
  /**
   * Renders the signup view
   */
  public static void signup()
  {
    render();
  }

  /**
   *  Renders the signin view 
   */
  public static void signin()
  {
    render();
  }
  
  /**
   * Registers a user for the site and saves their details to the DB.
   * 
   * @param user user to be registered for site
   */
  public static void register(User user)
  {
    Logger.info("Registering new user. usaCitizen: " + user.citizenStatus + 
                "firstName: " + user.firstName + " .lastName: " + user.lastName + 
                " .email: " + user.email + ". password: " + user.password);
    
    user.save();
    
    Accounts.signin();
  }  

  /**
   * Verifies a user to ensure they are a valid member of the site
   * 
   * @param user user to be verified
   */
  public static void authenticate(User user)
  {
    Logger.info("Trying to authenticate user: " + user.email + ". Password: " + user.password);

    String password = user.password;    
    
    try
    {
      user = User.findByEmail(user.email);
      
      if ((user != null) && (user.password.equals(password)))
      {
        Logger.info("Authentication Successful");
        session.put("loggedInUserId", user.id);
        Home.landing();
      }
      
      else
      {
        Logger.info("Authentication Failed");
        Accounts.signup();
      } 
    }
    catch (NullPointerException e)
    {
      Logger.info("Caught NullPointer Exception, redirected to signup page");
      Accounts.signup();
    }
  }
  
  /**
   *  Logs a user out from the site 
   */
  public static void logout()
  {
    session.clear();
    Home.welcome();
  }
  
  public static User loggedInUser()
  {
    String userId = session.get("loggedInUserId");
    User user = User.findById(Long.parseLong(userId));
    return user;
  }
}