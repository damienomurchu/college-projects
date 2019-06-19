package q2.spacebook;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that contains methods to sort messages.
 * Methods to sort a primitive array or an arraylist of Message objects.
 * Messages sorted by message text in ascending order.
 */
public class MessageSort {

  /**
   * Sorts an arraylist of Message objects by their message text.
   * Sorts in ascending order.
   * 
   * @param m ArrayList of messages to be sorted
   */
  public static void selectionSort(ArrayList<Message> m) {
    // TODO
    for (int i = 0; i < m.size(); i++) {
      for (int j = i; j < m.size(); j++) {      
        if (m.get(j).messageText.compareToIgnoreCase(m.get(i).messageText) < 0) {
          Collections.swap(m, i, j);
        }
      }
    }
  }
  
  /**
   * Sorts an array of Message objects by their message text.
   * Sorts in ascending order.
   * 
   * @param m Array of messages to be sorted
   */
  public static void selectionSort(Message[] m) {
    // TODO
    for (int i = 0; i < m.length; i++) {
      for (int j = i; j < m.length; j++) {
        if (m[j].messageText.compareToIgnoreCase(m[i].messageText) < 0) {
          swap(m, i, j);
        }
      }
    }
  }

  /**
   * Swaps the position of two Message elements in a Message[] array
   * 
   * @param arMsg Message[] array containing the elements to be swapped
   * @param to Array element position to be swapped to
   * @param from Array element position to be swapped from
   */
  private static void swap(Message[] arMsg, int to, int from) {
    // TODO
    Message msg = arMsg[to];
    arMsg[to] = arMsg[from];
    arMsg[from] = msg;
  }
  
}


