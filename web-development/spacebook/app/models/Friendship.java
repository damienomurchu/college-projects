package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 *  @file					Friendship.java
 *  @description
 *		Friendship model class to represent user friendships in the Spacebook
 *    application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
@Entity
public class Friendship extends Model
{
  @ManyToOne
  public User sourceUser;

  @ManyToOne
  public User targetUser;

  /**
   * Constructs a friendship between the source and target users
   * 
   * @param source user initiating the friendship
   * @param target user that is the friendship target
   */
  public Friendship(User source, User target)
  {
    sourceUser = source;
    targetUser = target;
  }

}
