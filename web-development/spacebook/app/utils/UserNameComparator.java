package utils;

import java.util.Comparator;

import models.User;

/**
 *  @file					UserNameComparator.java
 *  @description
 *		Comparator class that facilitates the comparison of user objects by name
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class UserNameComparator implements Comparator<User>
{

  /**
   * Performs a lexicographic comparison of the String name fields of 2 user objects
   * 
   * @param a the first user object
   * @param b the second user object
   * 
   * @return 0 if the user name in o1 is equal to the name in o2 less than zero
   *         if name in o1 less than name in o2 greater than zero if name in o2
   *         greater than name in o2
   */
  @Override
  public int compare(User a, User b)
  {
    String firstUser = a.lastName + a.firstName;
    String secondUser = b.lastName + b.firstName;

    return firstUser.compareToIgnoreCase(secondUser);
  }
}