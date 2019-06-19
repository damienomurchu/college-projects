package q2.spacebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class to represent a Spacebook User.
 */
public class User {
  
  /**
   * Users first name
   */
  String firstName;
  
  /**
   * Users last name
   */
  String lastName;
  
  /**
   * Users age
   */
  int age;
  
  /**
   * Users nationality
   */
  String nationality;
  
  /**
   * Email of User
   */
  String email;
  
  /**
   * Users password
   */
  String password;

  /**
   * Users online status
   */
  Status status;

  /**
   * List of Users friendships
   */
  ArrayList<Friendship> friendships   = new ArrayList<>();
  
  /**
   * List of messages in Users inbox
   */
  ArrayList<Message> inbox            = new ArrayList<>();
  
  /**
   * List of messages in Users outbox
   */
  ArrayList<Message> outbox           = new ArrayList<>(); 
  
  /**
   * Map of Users Groups. Maps Group name with Group.
   */
  HashMap<String, Group> groups = new HashMap<>();

  /**
   * Constructs an object intended for testing only
   * The firstName determined by caller
   * Remaining fields used default data
   */
  public User(String firstName) {
    setState(firstName, "Simpson", firstName+"@simpson.com", "secret");
  }
  
  /**
   * Constructs User object. 
   * Initialises firstName, lastName, email and password. 
   * Sets status to ONLINE
   * 
   * @param firstName First name of user
   * @param lastName Last name of user
   * @param email Email of user
   * @param password Users password
   */
  public User(String firstName, String lastName, String email, String password) {
    setState(firstName, lastName, email, password);
  }  

  /**
   * Sets firstName, lastName, email and password to specified values.
   * Sets status to ONLINE
   * 
   * @param firstName
   * @param lastName
   * @param email
   * @param password
   */
  public void setState(String firstName, String lastName, String email, String password) {
    this.firstName  = firstName;
    this.lastName   = lastName;
    this.email      = email;
    this.password   = password;
    // TODO 2
    setStatus(Status.ONLINE);
  }  
  
  /**
   * Sets online status of user
   * 
   * @param status New user status
   */
  public void setStatus(Status status) {
    // TODO
    this.status = status;
  }

  /**
   * Spams all of users friends
   * 
   * @param subject 
   * @param messageText
   */
  public void broadcastMessage(Subject subject, String messageText) {
    for(Friendship f : friendships) {
      Message message = new Message(subject, this, f.targetUser, messageText);
      outbox.add(message);
      f.targetUser.inbox.add(message);
    }
  }

  /**
   * Sends message from one user to another
   * 
   * @param message message that is sent
   */
  public void sendMessage(Message message) {
    // TODO
    outbox.add(message);
    message.to.inbox.add(message);
  }

  /**
   * Sends message from one user to another
   * 
   * @param subject Subject of message
   * @param to Recipient of message
   * @param messageText Text of message
   */
  public void sendMessage(Subject subject, User to, String messageText) {
    Message message = new Message(subject,this, to, messageText);
    outbox.add(message);
    to.inbox.add(message);
  }

  /**
   * Display all messages in users outbox
   */
  public void displayOutbox() {
    for(Message msg : outbox) {
      msg.displayMessage();
    }
  }

  /**
   * Display all messages in users inbox
   */
  public void displayInbox() {
    for(Message msg : inbox) {
      msg.displayMessage();
    }
  }
  
  /**
   * Creates a new friendship between this user and another user.
   * 
   * @param friend User to create friendship with
   */
  public void befriend(User friend) {
    if(friend == this) {
      System.out.println("Opps! You seem to have made a mistake in attempting to befriend yourself");
    }
    else if(friendshipsContains(friend)) {
      System.out.println("You attempted to befriend " + friend.firstName +" who is already a friend");
    }
    else {
      Friendship friendship = new Friendship(this, friend);
      friendships.add(friendship);
    }
  }

  /**
   * Checks if user is already a friend
   * 
   * @param friend User to check whether they're a friend
   * @return True if user is already a friend, false if user is not
   */
  private boolean friendshipsContains(User friend) {
    // TODO
    for (Friendship friendship : friendships) {
      if (friendship.targetUser == friend) {
        return true;
      }
    }
    return false;
  }

  /**
   * Clears all users friendships
   */
  public void unfriendAll() {
    friendships.clear();
  }

  // TODO
  /**
   * Unfriends a specific user
   * 
   * @param friend User to defriend
   */
  public void unfriend(User friend) {
    for(Friendship friendship : friendships) {
      if(friendship.targetUser == friend) {
        friendships.remove(friendship);
        return;
      }
    }
  }

  /** 
   * Displays a list of all users friends
   */
  public void displayFriends() {
    if(friendships.isEmpty()) {
      System.out.println("Unfortunately you have no friends");
    }

    System.out.println("I'm " + this.firstName + " " + this.lastName + " and these are my \"friends\" hehe :-)");
    for(Friendship friendship : friendships) {
      System.out.println("My friend "+ friendship.targetUser.firstName + " is " + friendship.targetUser.status);
    }
  }

  /**
   * Displays a list of users friends that match a specified status
   * 
   * @param status Status of users which to display
   */
  public void displayFriends(Status status) {
    // TODO
    String friends = "Friends that are " + status +" :\n";
    for (Friendship friendship : friendships) {
      if (friendship.targetUser.status == status) {
        friends += "My friend "+ friendship.targetUser.firstName + " is " + friendship.targetUser.status + "\n";
      }
    }
    System.out.print(friends);
  }

  /**
   * Creates a new Group
   * 
   * @param groupName Name of the new Group
   */
  public void addGroup(String groupName) {
    groups.put(groupName, new Group(groupName));
  }

  /**
   * Adds a member to a specified Group
   * 
   * @param groupName Group which to add user to
   * @param user User which to add to Group
   */
  public void addGroupMember(String groupName, User user) {
    Group group = groups.get(groupName);
    group.addGroupMember(user);
  }

  /**
   * Broadcast a message to all members of a specific Group
   * 
   * @param groupName Name of Group which to message its users
   * @param message Message to send
   */
  public void broadcastMessage(String groupName, Message message) {
    groups.get(groupName).broadcastMessage(message);
  }

  /**
   * Displays all messages in list
   *  
   * @param messages List of messages to display
   */
  public void displayMessages(ArrayList<Message> messages) {
    // TODO 
    for (Message message : messages) {
      message.displayMessage();
    }
  }

  /**
   * Displays all messages in a list that match a specified subject
   * 
   * @param subject Subject of messages to display
   * @param messages List of messages which to display from
   */
  public void displayMessages(Subject subject, ArrayList<Message> messages) {
    // TODO 
    for (Message message : messages) {
      if (message.subject == subject) {
        message.displayMessage();
      }
    }
  }

  /**
   * Searches a list of messages to see if their content matches a specified content.
   * Returns any messages that match the searched for content string exactly.
   * 
   * @param content   the content sought
   * @param msg       the array Message objects to be searched
   * @return          returns the first Message containing content, else null
   */
  public Message search(String content, ArrayList<Message> msg) {
    int index = 0;
    while(index < msg.size()) {
      Message thisMsg = msg.get(index);
      if(content.equals(thisMsg.messageText))
      {
        return thisMsg;
      }
      index += 1;
    }
    return null;
  }

  /**
   * Searches a list of messages to see if their subject matches a specified subject.
   * Returns any messages that match the searched for subject string exactly. 
   * 
   * @param subject   seeking a Message object with Subject subject
   * @param msg       the array Message objects to be searched
   * @return          returns the first Message with subject matching param, else null
   */
  public Message search(Subject subject, ArrayList<Message> msg) {
    int index = 0;
    
    while(index < msg.size()) {
      Message thisMsg = msg.get(index);
      if(subject == thisMsg.subject) {
        return thisMsg;
      }
      index += 1;
    }
    return null;        
  }
}