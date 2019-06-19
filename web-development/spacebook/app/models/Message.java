package models;

import java.util.Date;

import javax.persistence.*;
import play.db.jpa.*;

/**
 *  @file					Message.java
 *  @description
 *		Message model class to represent messages between users in the Spacebook
 *    application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
@Entity
public class Message extends Model
{
  public String subject;
  public String messageText;
  public Date postedAt;

  @ManyToOne
  public User from;

  @ManyToOne
  public User to;

  /**
   * Constructs a new message from the specified parameters
   * 
   * @param from sender of the message
   * @param to target of the message
   * @param subject subject of the message 
   * @param messageText message text of the message
   */
  public Message(User from, User to, String subject, String messageText)
  {
    this.from = from;
    this.to = to;
    this.subject = subject;
    this.messageText = messageText;
    this.postedAt = new Date();
  }
  
  /**
   * Returns the time of a message in the form XX:XX (added zeroes added as 
   * necessary for padding purposes)
   * 
   * @return formatted time string representing the message time
   */
  @SuppressWarnings("deprecation")
  public String getTime()
  {
    StringBuilder sb = new StringBuilder();
    
    if (postedAt.getHours() < 10)
      sb.append("0");
    sb.append(postedAt.getHours());
    
    sb.append(":");
    
    if (postedAt.getMinutes() < 10)
      sb.append("0");
    sb.append(postedAt.getMinutes());
    
    return sb.toString();
  }

}
