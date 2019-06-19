package q2.spacebook;

//Question 4 Task 3
import java.util.ArrayList;

/**
 * Class to represent a Group of Users
 */
public class Group {
  
  /**
   * Name of the Group
   */
  String groupName;
  
  /**
   * List of the Groups members
   */
  ArrayList<User> members;

  /**
   * Constructs a Group object. 
   * Initialises list of members.
   * 
   * @param groupName Name of the Group
   */
  public Group(String groupName) {
    // TODO
    this.groupName = groupName;
    members = new ArrayList<User>();
  }

  /**
   * Adds a User to the Group as a member
   * 
   * @param user User to be added to the group
   */
  public void addGroupMember(User user) {
    // TODO
    if (user != null && user instanceof User) {
      members.add((User) user);
    }
  }
  
  /**
   * Send a message to the inbox of each group member
   * 
   * @param message Message to be broadcast
   */
  public void broadcastMessage(Message message) {
    // TODO
    for (User user : members) {
      user.inbox.add(message);
    }
  }

  // TODO
  /**
   * Returns a String representation of the Group
   */
  @Override
  public String toString() {
    return "GroupName=" + groupName + "\n" + groupList();
  } 
  
  /**
   * Returns a list of members in the Group
   * 
   * @return all the group names in a single string
   */
  private String groupList() {
    // TODO
    String names = "";
    for (User member : members) {
      names += member.firstName + " " + member.lastName + "\n";
    }
    return names;
  }
  
}
