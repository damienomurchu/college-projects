package ie.wit.ictskills.shapes;

// TODO Task 2: Complete Pentagon class

import java.awt.Polygon;

/**
 *  @file					Pentagon.java
 *  @description
 *		 This class describes a pentagon and has behaviour to display, resize
 *     and move objects
 *
 *	@author       jfitzgerald
 *  @since        23 May 2014
 *  @version      1.0	
 *
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class Pentagon extends Shapes implements Measurable
{
  /**
   * radius of circumscribing circle
   */
  private int radius;

  /**
   * Constructs a default pentagon
   */
  public Pentagon()
  {
    super(150, 150, "red", false);
    this.radius = 50;
  }

  /**
   * Constructs a pentagon of the specified parameters
   * 
   * @param radius radius of the pentagons circumscribing circle
   * @param xPosition x-coordinate position of shape on canvas
   * @param yPosition y-coordinate position of shape on canvas
   * @param color colour of the pentagon
   */
  public Pentagon(int radius, int xPosition, int yPosition, String color)
  {
    super(xPosition, yPosition, color, false);
    setState(radius);
  }

  /**
   * Sets the dimensions of the pentagon
   * 
   * @param radius radius of the pentagons circumscribing circle
   */
  private void setState(int radius)
  {
    this.radius = radius;
  }

  public static void main(String[] args)
  {
    Pentagon p1 = new Pentagon();
    p1.makeVisible();
    p1.draw();
    p1.moveTo(0, 0);
  }

  /**
   * Changes the size of the pentagon according to the specified scale
   * 
   * @see ie.wit.ictskills.shapes.Shapes#changeSize(int)
   */
  @Override
  public void changeSize(int scale)
  {
    erase();
    radius *= scale;
    draw();
  }

  /**
   * Draws the pentagon on the canvas
   * 
   * @see ie.wit.ictskills.shapes.Shapes#draw()
   */
  @Override
  void draw()
  {
    if (isVisible)
    {
      // Ref: http://mathworld.wolfram.com/Pentagon.html
      double dc1 = 0.25 * (Math.sqrt(5) - 1);
      double dc2 = 0.25 * (Math.sqrt(5) + 1);
      double ds1 = 0.25 * (Math.sqrt(10 + 2 * Math.sqrt(5)));
      double ds2 = 0.25 * (Math.sqrt(10 - 2 * Math.sqrt(5)));// length of
      // pentagon
      // side is
      // 2*ds2
      int c1 = -(int) (radius * dc1);// radius of circle that
      // circumscribes pentagon
      int c2 = -(int) (radius * dc2);
      int s1 = (int) (radius * ds1);
      int s2 = (int) (radius * ds2);

      Canvas canvas = Canvas.getCanvas();
      int[] xpoints = { xPosition, xPosition + s1, xPosition + s2, xPosition - s2, xPosition - s1 };
      int[] ypoints = { yPosition - radius, yPosition + c1, yPosition - c2, yPosition - c2, yPosition + c1 };
      canvas.draw(this, color, new Polygon(xpoints, ypoints, 5));
      canvas.wait(10);
    }
  }

  /**
   * Returns perimeter of a pentagon
   * 
   * @return the perimeter of the pentagon
   */
  @Override
  public double perimeter()
  {
    return 10 * radius * Math.sin(Math.PI / 5);
  }
  
}
