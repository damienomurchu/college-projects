package ie.wit.ictskills.shapes;

// TODO Task 1: Refactor: derive from Shapes super class

/**
 *  @file					Rectangle.java
 *  @description
 *		A class that represents a rectangle shape
 *
 *	@author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 *	
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public class Rectangle extends Shapes implements Measurable
{
  /**
   * Length of rectangle
   */
  private int xSideLength;

  /**
   * Height of rectangle
   */
  private int ySideLength;

  /**
   * Constructs a default Rectangle
   */
  public Rectangle()
  {
    super(60, 50, "red", false);
    setState(60, 30);
  }

  /**
   * Constructs a Rectangle with the specified parameters
   * 
   * @param xSideLength length of rectangle
   * @param ySideLength height of rectangle
   * @param xPosition x position co-ordinate on canvas
   * @param yPosition y position co-ordinate on canvas
   * @param color colour of rectangle
   */
  public Rectangle(int xSideLength, int ySideLength, int xPosition, int yPosition, String color)
  {
    super(xPosition, yPosition, color, false);
    setState(xSideLength, ySideLength);
  }

  /**
   * Constructs a sample rectangle and makes it visible
   */
  public static void main(String[] args)
  {
    Rectangle r1 = new Rectangle(100, 50, 50, 100, "red");
    r1.makeVisible();
  }

  /**
   * Sets dimensions of rectangle
   * 
   * @param xSideLength length of rectangle
   * @param ySideLength height of rectangle
   */
  public void setState(int xSideLength, int ySideLength)
  {
    this.xSideLength = xSideLength;
    this.ySideLength = ySideLength;
  }

  /**
   * Changes size of rectangle to specified dimensions
   * 
   * @param xSideLength length of rectangle
   * @param ySideLength height of rectangle
   */
  public void changeSize(int xSideLength, int ySideLength)
  {
    if (xSideLength > 0 && ySideLength > 0)
    {
      erase();
      this.xSideLength = xSideLength;
      this.ySideLength = ySideLength;
      draw();
    }
    else
    {
      System.out.println("Enter positive dimensions");
    }
  }

  /**
   * Increases/ reduces size of rectangle by desired scale
   * 
   * @param scale scale of rectangle
   */
  public void changeSize(int scale)
  {
    if (xSideLength > 0 && ySideLength > 0)
    {
      erase();
      this.xSideLength *= scale;
      this.ySideLength *= scale;
      draw();
    }
    else
    {
      System.out.println("Enter positive dimensions");
    }
  }

  /**
   * Draws rectangle on canvas if rectangle is visible
   */
  void draw()
  {
    if (isVisible)
    {
      Canvas canvas = Canvas.getCanvas();
      canvas.draw(this, color, new java.awt.Rectangle(xPosition, yPosition, xSideLength, ySideLength));
      canvas.wait(10);
    }
  }

  /**
   * Returns perimeter of a rectangle
   * 
   * @return the perimeter of the rectangle
   */
  @Override
  public double perimeter()
  {
    return (xSideLength + ySideLength) * 2;
  }

}