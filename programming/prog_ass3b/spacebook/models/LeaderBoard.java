package models;
/**
* @file    LeaderBoard.java
* @brief   Class publishes users graded most socially and talkatively active
* @version 2015-4-22
* @author  
*/

import java.util.ArrayList;
import java.util.Collections;

import utils.UserLeastTalkativeComparator;
import utils.UserSocialComparator;
import utils.UserTalkativeComparator;

import views.LeaderBoardView;

/**
 *  @file					LeaderBoard.java
 *  @description
 *		Class publishes users graded most socially and talkatively active
 *
 *	@author       jfitzgerald
 *  @since        22 April 2015
 *  @version      1.0
 *	
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class LeaderBoard
{
  /**
   * Sorts an arraylist of users by the most social (size of their friend list).
   * Sorted list is rendered to LeaderBoardView.
   * 
   * @param users the arraylist of users to be sorted
   */
  public static void index(ArrayList<User> users)
  {
    Collections.sort(users, new UserSocialComparator());
    LeaderBoardView.index(users);
  }

  /**
   * Sorts an arraylist of users by the most talkative (the size of their outbox).
   * Sorted list is rendered to LeaderBoardView.
   * 
   * @param users the arraylist of users to be sorted
   */
  public static void talkative(ArrayList<User> users)
  {
    // TODO Complete implementation of method LeaderBoard.talkative

    Collections.sort(users, new UserTalkativeComparator());

    LeaderBoardView.talkative(users);
  }

  /**
   * Sorts an arraylist of users by the least talkative (the size of their outbox).
   * Sorted list is rendered to LeaderBoardView.
   * 
   * @param users the arraylist of users to be sorted
   */
  public static void leastTalkative(ArrayList<User> users)
  {
    // TODO Complete implementation of method LeaderBoard.leastTalkative

    Collections.sort(users, new UserLeastTalkativeComparator());

    LeaderBoardView.leastTalkative(users);
  }
}
