package ie.wit.ictskills.shapes;

import java.util.ArrayList;

/**
 *  @file					TestPentagon.java
 *  @description
 *    Test class for Pentagon objects		
 *
 *	@author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 *  	
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class TestPentagon
{
  /**
   * Creates and prints an arraylist of pentagon shapes
   */
  public static void main(String[] args)
  {
    // TODO Task 3: Display 4 cascaded Pentagons differently colored shapes.
    ArrayList<Shapes> shapes = new ArrayList<>();

    shapes.add(new Pentagon(30, 60, 30, "red"));
    shapes.add(new Pentagon(40, 90, 50, "blue"));
    shapes.add(new Pentagon(50, 120, 70, "green"));
    shapes.add(new Pentagon(60, 150, 90, "black"));

    for (Shapes shape : shapes)
    {
      shape.makeVisible();
    }
  }

}
