package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 *  User model that represents all details relating to site users
 *    
 *  @since        31 May 2016
 */
@Entity
public class User extends Model
{
  public boolean citizenStatus;
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  
  /**
   * Find a user from their email address
   * 
   * @param email email to search by
   * @return the user that the email belongs to
   */
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }
  
}
