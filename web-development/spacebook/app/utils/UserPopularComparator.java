package utils;

import java.util.Comparator;

import models.User;

/**
 *  @file					UserPopularComparator.java
 *  @description
 *		Comparator class that facilitates the comparison of user objects by the
 *    size of their inbox (how many messages they have received)
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class UserPopularComparator implements Comparator<User>
{

  /**
   * Compares two users by the size of their message inbox
   * 
   * @return 0 if the inbox size of o1 is equal to the inbox size of o2, 
   *          less than zero if the inbox size of o1 is less than the 
   *          inbox size of o2, and 
   *          greater than zero if the inbox size of o2 is greater than 
   *          the inbox size of o2.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(User a, User b)
  {
    Integer inboxSizeB = new Integer(b.inbox.size());
    return inboxSizeB.compareTo(a.inbox.size());
  }
}