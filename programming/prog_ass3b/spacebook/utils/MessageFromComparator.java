package utils;

import java.util.Comparator;

import models.Message;

/**
 *  @file					MessageFromComparator.java
 *  @description
 *		A comparator class that facilitates comparison between the concatenated last
 *    and first names of the originator of 2 message objects Example: Message o1
 *    has a User from that has String lastName and String firstName The comparison
 *    is between lastName+firstName in both messages' originators.
 *
 *	@author       jfitzgerald
 *  @since        12 April 2016
 *	@version      1.0
 *
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class MessageFromComparator implements Comparator<Message>
{
  /**
   * Performs a lexicographic comparison the String name fields of the sending
   * user (that is User from) in 2 Message objects
   * 
   * @param o1 the first message object
   * @param o2 the second message object
   * 
   * @return 0 if the user name in o1 is equal to the name in o2 less than zero
   *         if name in o1 less than name in o2 greater than zero if name in o2
   *         greater than name in o2
   */
  @Override
  public int compare(Message o1, Message o2)
  {
    // TODO Complete implementation of method MessageFromComparator.compare.

    String o1Name = o1.from.lastName + o1.from.firstName;
    String o2Name = o2.from.lastName + o2.from.firstName;

    return o1Name.compareToIgnoreCase(o2Name);
  }

}
