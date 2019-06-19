package controllers;

import play.mvc.Controller;

/**
 *  Home controller that represents functionality relating to the Home view
 *    
 *  @since        31 May 2016
 */
public class Home extends Controller
{
  /**
   * Renders the welcome view
   */
  public static void welcome()
  {
    render();
  }
  
  /**
   * Renders the landing view
   */
  public static void landing()
  {
    render();
  }
}
