package util;

import ie.wit.ictskills.shapes.Measurable;

import java.util.ArrayList;

/**
 *  @file					Util.java
 *  @description
 *		Utility class that houses several helper methods for classes in application
 *		
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.0
 */
public class Util
{
  /**
   * Measureable interface contains the method double perimeter().
   * The method maximum here evalutates the single value representing the largest
   * perimeter discovered in the list of Measurable objects.
   *
   * @param object The list of objects whose classes implement the interface Measurable
   * @return Returns the largest perimeter discovered among entire list objects.
   */
  static public double maximum(ArrayList<Measurable> object)
  {
    double max = 0;
    
    for (Measurable shape : object)
    {
      max = (shape.perimeter() > max) ? shape.perimeter() : max;
    }
    
    return max;
  }
}
