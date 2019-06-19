package utils;

import java.util.Comparator;

import models.User;

/**
 *  @file					UserTalkativeComparator.java
 *  @description
 *		Comparator class that facilitates the comparison of user objects by the 
 *    size of their outbox (how many messages they have sent)
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
public class UserTalkativeComparator implements Comparator<User>
{
  /**
   * Compares two users by the size of their message outbox
   * 
   * @return 0 if the outbox size of o1 is equal to the outbox size of o2, 
   * 					less than zero if the outbox size of o1 is greater than the outbox size of o2, 
   * 					and greater than zero if the outbox size of o2 is less than the 
   * 					outbox size of o2.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(User a, User b)
  {
    Integer outboxSizeB = new Integer(b.outbox.size());
    return outboxSizeB.compareTo(a.outbox.size());
  }
}
