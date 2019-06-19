package controllers;

import java.util.ArrayList;
import java.util.Collections;

import models.Friendship;
import models.Message;
import models.User;

import utils.MessageDateComparator;
import utils.MessageFromComparator;
import utils.MessageToComparator;

import views.HomeView;

public class Home
{
  /**
   * Dispatches a list of messages in the user's inbox to HomeView for rendering
   * This method simulates the Play version of spaceboook Home page responding
   * to BY DATE button press Note that the method HomeView.index(User, ArrayList
   * <Message>) could have been condensed to HomeView.index(User) since the
   * reference to outbox is available from within the User object. The more
   * verbose version of the method has been chosen to preserve similarity with
   * the equivalent Play method. A similar approach has been adopted with other
   * view methods.
   * 
   * @param user simulates the logged-in user
   */
  public static void index(User user)
  {
    HomeView.index(user, user.inbox);
  }

  /**
   * Sort a copy of a specific user's inbox. Render sorted list of messages in
   * HomeView.
   * 
   * @param user the user whose message inbox will be displayed grouped by user.
   * 
   */
  public static void byUser(User user)
  {
    // TODO Complete implementation of method Home.byUser

    ArrayList<Message> messagesByUser = new ArrayList<>();

    for (Message message : user.inbox)
    {
      messagesByUser.add(message);
    }

    Collections.sort(messagesByUser, new MessageFromComparator());

    HomeView.byUser(user, messagesByUser);
  }

  /**
   * This method creates and renders a list of conversations between a user and
   * all its friends A single conversation between 2 users comprises a list of
   * messages ordered by date-time. A user has a list of friendships (friends).
   * Thus a conversation comprises a list of date-time-sorted messages. The
   * conversations, therefore, comprise a list of a list of messages. This can
   * represent a conversation: ArrayList<Message> This can represent a list of
   * conversations: ArrayList<ArrayList<Message>>
   * 
   * @param user the user that initiates the conversation
   */
  public static void byConversation(User user)
  {
    // TODO Complete implementation of method Home.byConversation

    ArrayList<ArrayList<Message>> conversations = new ArrayList<ArrayList<Message>>();

    for (Friendship friendship : user.friendships)
    {
      conversations.add(getConversation(user, friendship.targetUser));
    }

    HomeView.byConversation(user, conversations);
  }

  /**
   * Generates a conversation comprising a list of messages between two users.
   * The list is sorted by reference to date and time. Recall that a message
   * object contains an instance of Date object
   * 
   * @param user the user who initiates the conversation
   * @param friend the user with whom the initiator is having a conversation
   * 
   * @return the list of messages which makes up the conversation
   */
  static ArrayList<Message> getConversation(User user, User friend)
  {
    // TODO Complete implementation of method Home.getConversation

    ArrayList<Message> ar = new ArrayList<>();

    for (Message message : user.inbox)
    {
      if (message.from == friend)
      {
        ar.add(message);
      }
    }

    for (Message message : user.outbox)
    {
      if (message.to == friend)
      {
        ar.add(message);
      }
    }

    Collections.sort(ar, new MessageDateComparator());

    return ar;
  }
}
