package ie.wit.ictskills.shapes;

import java.awt.Polygon;

/**
 *  @file					Triangle.java
 *  @description
 *		A class that represents a triangle shape
 *
 *  @author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 * 	
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class Triangle extends Shapes implements Measurable
{
  /**
   * height of triangle
   */
  private int height;

  /**
   * width (base) of triangle
   */
  private int width;

  /**
   * Constructs a default triangle
   */
  public Triangle()
  {
    super(150, 65, "black", true);
    setState(50, 75);
  }

  /**
   * Constructs a triangle of the specified attributes
   * 
   * @param height height of triangle
   * @param width width (base) of triangle
   * @param xPosition x co-ordinate of triangle on canvas
   * @param yPosition y co-ordinate of triangle on canvas
   * @param color colour of triangle
   */
  public Triangle(int height, int width, int xPosition, int yPosition, String color)
  {
    super(xPosition, yPosition, color, true);
    setState(height, width);
  }

  /**
   * Creates and draws a new triangle object
   */
  public static void main(String[] args)
  {
    Triangle tr = new Triangle();
    tr.draw();
  }

  /**
   * Sets dimensions of triangle
   * 
   * @param height height of triangle
   * @param width width of triangle
   */
  public void setState(int height, int width)
  {
    this.height = height;
    this.width = width;
  }

  /**
   * Returns perimeter of triangle
   * 
   * @return  the perimeter of the triangle
   * @see ie.wit.ictskills.shapes.Measurable#perimeter()
   */
  @Override
  public double perimeter()
  {
    return 2 * Math.hypot(height, width / 2) + width;
  }

  /**
   * Draws triangle on canvas
   * 
   * @see ie.wit.ictskills.shapes.Shapes#draw()
   */
  @Override
  void draw()
  {
    if (isVisible)
    {
      Canvas canvas = Canvas.getCanvas();
      int[] xpoints = { xPosition, xPosition + (width / 2), xPosition - (width / 2) };
      int[] ypoints = { yPosition, yPosition + height, yPosition + height };
      canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
      canvas.wait(10);
    }
  }

  /**
   * Changes overall size of triangle by scale factor specified
   * 
   * @param scale scale with which to increase/ decrease triangle size
   * @see ie.wit.ictskills.shapes.Shapes#changeSize(int)
   */
  @Override
  void changeSize(int scale)
  {
    this.height *= scale;
    this.width *= scale;
  }

}
