package utils;

import java.util.Comparator;

import models.Message;

/**
 *  @file         MessageToComparator.java
 *  @description
 *    A comparator class that facilitates comparison between the concatenated last
 *    and first names of the originator of 2 message objects Example: Message o1
 *    has a User from that has String lastName and String firstName The comparison
 *    is between lastName+firstName in both messages' originators.
 *
 *  @author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 *
 *  @author       Damien Murphy
 *  @since        15 May 2016
 *  @version      1.1
 */
public class MessageToComparator implements Comparator<Message>
{
  /**
   * Compares two message objects by name (lastName + firstName)
   * 
   * @param o1 the first message object
   * @param o2 the second message comparison object
   * 
   * @return an int value of 0, <0, or >0 representing whether o1 is equal to, 
   * 					less than, or greater than o2
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Message o1, Message o2)
  {
    String o1Name = o1.to.lastName + o1.to.firstName;
    String o2Name = o2.to.lastName + o2.to.firstName;
    
    return o1Name.compareToIgnoreCase(o2Name);
  }

}
