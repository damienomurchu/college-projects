package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

/**
 *  @file					Accounts.java
 *  @description
 *		Accounts java controller for the associated Accounts html views in the 
 *    Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class Accounts extends Controller
{
  /**
   * Renders the Accounts.signup view
   */
  public static void signup()
  {
    render();
  }

  /**
   * Renders the Accounts.login view
   */
  public static void login()
  {
    render();
  }

  /**
   * Clears the sessions and renders the Accounts.index view
   */
  public static void logout()
  {
    session.clear();
    index();
  }

  /**
   * Renders the Accounts.index view
   */
  public static void index()
  {
    render();
  }

  /**
   * Registers a user for the spacebook site
   * 
   * @param firstName first name of user
   * @param lastName last name of user
   * @param age age of user
   * @param nationality nationality of user
   * @param email email address of user
   * @param password password of user
   */
  public static void register(String firstName, String lastName, String age, 
                              String nationality, String email, String password)
  {
    Logger.info(firstName + " " + lastName + " " + age + " " + nationality + " " + email + " " + password);

    User user = new User(firstName, lastName, age, nationality, email, password);
    user.save();

    index();
  }
  
  /**
   * Edits a users details with the details submitted.
   * Details only changed if different from exisiting details, and new details 
   * fields are not blank
   * 
   * @param firstName new first name of user
   * @param lastName new last name of user
   * @param age new age of user
   * @param nationality new nationality of user
   * @param email new email address of user
   * @param password new password of user
   */
  public static void edit(String firstName, String lastName, String age, 
                          String nationality, String email, String password)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    
    if (!user.firstName.equals(firstName) && !firstName.isEmpty())
      user.firstName = firstName;
    
    if (!user.lastName.equals(lastName) && !lastName.isEmpty())
      user.lastName = lastName;
    
    if (!user.age.equals(age) && !age.isEmpty())
      user.age = age;
    
    if (!user.nationality.equals(nationality) && !nationality.isEmpty())
      user.nationality = nationality;
    
    if (!user.email.equals(email) && !email.isEmpty())
      user.email = email;
    
    if (!user.password.equals(password) && !password.isEmpty())
      user.password = password;
    
    user.save();
    
    Profile.index();
  }

  /**
   * Verifies a user is valid. Checks their email and password, and creates a 
   * session and renders the Home.index view if they are a valid user.
   * 
   * @param email email address of user
   * @param password password of user
   */
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    User user = User.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Authentication successful");
      session.put("logged_in_userid", user.id);
      Home.index();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }
}
