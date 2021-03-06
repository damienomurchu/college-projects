package models;

public class Friendship
{
  User sourceUser;
  public User targetUser;

  /**
   * Creates a friendship between two users
   * 
   * @param sourceUser initiator of the friendship
   * @param targetUser user that is the friendship target
   */
  public Friendship(User sourceUser, User targetUser)
  {
    this.sourceUser = sourceUser;
    this.targetUser = targetUser;
  }

  @Override
  public String toString()
  {
    return targetUser.firstName + ",s friend is " + targetUser.lastName;
  }
}
