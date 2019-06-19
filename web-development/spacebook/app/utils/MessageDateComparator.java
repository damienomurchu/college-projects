package utils;

import java.util.Comparator;

import models.Message;

/**
 *  @file					MessageDateComparator.java
 *  @description
 *    A comparator class that facilitates comparison between two message objects 
 *    on the basis of the date at which they were posted. 
 *
 *	@author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 *	
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class MessageDateComparator implements Comparator<Message>
{
  /**
   * Compares two message objects by the date they were posted
   * 
   * @param a the first message object
   * @param b the second message comparison object
   * 
   * @return an int value of 0, <0, or >0 representing whether the date o1 was 
   * 					posted at is equal to, less than, or greater than o2
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Message a, Message b)
  {
    return a.postedAt.compareTo(b.postedAt);
  }

}
