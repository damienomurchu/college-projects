package utils;

import java.util.Comparator;

import models.User;

/**
 *  @file         UserTalkativeComparator.java
 *  @description
 *    A comparator class that facilitates comparison between two users objects 
 *    on the basis of size of their message outboxes. Largest outbox wins.
 *
 *  @author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 *  
 *  @author       Damien Murphy
 *  @since        15 May 2016
 *  @version      1.1
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
    return Integer.compare(b.outbox.size(), a.outbox.size());
  }
}
