package utils;

import java.util.Comparator;

import models.User;

/**
 *  @file					UserSocialComparator.java
 *  @description
 *		Comparator class that facilitates the comparison of user objects by the
 *    number of friendships they have
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
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
    Integer numFriendsB = new Integer(b.friendships.size());
    return numFriendsB.compareTo(a.friendships.size());    
  }
}
