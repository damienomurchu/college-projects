package ie.wit.ictskills.shapes;

import java.awt.geom.*;

/**
 *  @file					Ellipse.java
 *  @description
 *		A class that represents an ellipse shape
 *		
 *  @author				jfitzgerald
 *  @since				12 April 2016
 *  @version 			1.0
 *  
 *  @author       Damien Murphy
 *  @since        15 May 2016
 *  @version      1.1
 */
public class Ellipse extends Shapes implements Measurable
{
  /**
   * x-side diameter of ellipse
   */
  protected int xdiameter;

  /**
   * y-side diameter of ellipse
   */
  protected int ydiameter;

  /**
   * Constructs a default ellipse
   */
  public Ellipse()
  {
    super(20, 60, "blue", false);
    setState(30, 30);
  }

  /**
   * Constructs an ellipse from the supplied parameters
   * 
   * @param xdiameter x-side diameter of ellipse
   * @param ydiameter y-side diameter of ellipse
   * @param xPosition x-position co-ordinate on canvas
   * @param yPosition y-position co-ordinate on canvas
   * @param color colour of ellipse
   */
  public Ellipse(int xdiameter, int ydiameter, int xPosition, int yPosition, String color)
  {
    super(xPosition, yPosition, color, false);
    setState(xdiameter, ydiameter);
  }

  /**
   * Creates and draws a new ellipse
   */
  public static void main(String[] args)
  {
    Ellipse ellipse = new Ellipse(90, 60, 20, 60, "red");
    ellipse.makeVisible();
    ellipse.draw();
  }

  /**
   * Sets dimensions of the ellipse
   * 
   * @param xdiameter x-side diameter of ellipse
   * @param ydiameter y-side diameter of ellipse
   */
  public void setState(int xdiameter, int ydiameter)
  {
    this.xdiameter = xdiameter;
    this.ydiameter = ydiameter;
  }

  /**
   * Draw the circle with current specifications on screen.
   */
  @Override
  void draw()
  {
    if (isVisible)
    {
      Canvas canvas = Canvas.getCanvas();
      canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition, xdiameter, ydiameter));
      canvas.wait(10);
    }
  }

  /**
   * Change the overall size of the circle by the scale factor specified
   * 
   * @param scale scale by which to increase/ decrease circle
   */
  @Override
  void changeSize(int scale)
  {
    erase();
    xdiameter *= scale;
    ydiameter *= scale;
    draw();
  }

  /**
   * Returns perimeter of an ellipse
   * 
   * @return the perimeter of the ellipse
   */
  @Override
  public double perimeter()
  {
    double a = xdiameter / 2.0;
    double b = ydiameter / 2.0;
    double h = Math.pow(a - b, 2) / Math.pow(a + b, 2);
    return Math.PI * (a + b) * (1 + h / 4 + Math.pow(h, 2) / 64 + Math.pow(h, 3) / 256 + Math.pow(h, 4) / 16384);
  }

}
