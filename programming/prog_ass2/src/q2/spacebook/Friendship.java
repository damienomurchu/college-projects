package q2.spacebook;

/**
 * Class to represent a Friendship object
 */
public class Friendship {
  
  /**
   * User initiating the friendship
   */
  User sourceUser;
  
  /**
   * User being befriended
   */
  User targetUser;

  /**
   * Constructs a Friendship
   * 
   * @param sourceUser The user initiating the friendship
   * @param targetUser The user being befriended
   */
  public Friendship(User sourceUser, User targetUser) {
    this.sourceUser = sourceUser;
    this.targetUser = targetUser;
  }   
}
