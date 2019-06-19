package utils;

import java.util.Comparator;

import models.User;

/**
 *  @file         UserSocialComparator.java
 *  @description
 *    A comparator class that facilitates comparison between two users objects 
 *    on the basis of number of friendships they have. 
 *
 *  @author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 *  
 *  @author       Damien Murphy
 *  @since        15 May 2016
 *  @version      1.1
 */
public class UserSocialComparator implements Comparator<User>
{
  /**
   * Compares two users by the number of friendships they have
   * 
   * @return 0 if the number of friendships of o1 is equal to the number of 
   * 					friendships of o2, less than zero if the number of friendships of 
   * 					o1 is less than the number of friendships of o2, and greater than 
   * 					zero if the number of friendships of o2 is greater than the number 
   * 					of friendships of o2.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(User a, User b)
  {
    return Integer.compare(b.friendships.size(), a.friendships.size());
  }
}
