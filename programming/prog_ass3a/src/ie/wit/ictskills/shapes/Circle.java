package ie.wit.ictskills.shapes;

/**
 *  @file					Circle.java
 *  @description
 *		A circle that can be manipulated and that draws itself on a canvas.
 *	
 *  @author       Michael Kolling and David J. Barnes
 *  @since        30 March 2006
 *  @version      1.0
 *	
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class Circle extends Ellipse
{
  /**
   * Create a new circle at default position with default color.
   */
  public Circle()
  {
    super(30, 30, 20, 60, "blue");
  }

  /**
   * Creates a new circle object from the specified parameters
   * 
   * @param diameter diameter of the circle
   * @param xPosition x-position of circle on canvas
   * @param yPosition y-position of circle on canvas
   * @param color colour of the circle
   */
  public Circle(int diameter, int xPosition, int yPosition, String color)
  {
    super(diameter, diameter, xPosition, yPosition, color);
  }

  /**
   * Creates and draws a new circle
   */
  public static void main(String[] args)
  {
    Circle circle = new Circle();
    circle.makeVisible();
    circle.draw();
  }

  /**
   * Sets the radius of the circle
   * 
   * @param radius new radius of the circle
   */
  public void setState(int radius)
  {
    super.setState(radius, radius);
  }

}
