package ie.wit.ictskills.shapes;

/**
 *  @file					Shapes.java
 *  @description
 *    An abstract class describing geometric shapes		
 *
 *	@author       jfitzgerald
 *  @since        12 April 2016
 *  @version      1.0
 *	
 *  @author				Damien Murphy
 *  @since				15 May 2016
 *  @version 			1.1
 */
public abstract class Shapes
{
  /**
   * x-coordinate position of shape on canvas
   */
  int xPosition;

  /**
   * y-coordinate position of shape on canvas
   */
  int yPosition;

  /**
   * colour of shape
   */
  String color;

  /**
   * visibility of shape
   */
  boolean isVisible;

  /**
   * Constructs a new Shape object using the specified parameters
   * 
   * @param xPosition
   * @param yPosition
   * @param color
   * @param isVisible
   */
  public Shapes(int xPosition, int yPosition, String color, boolean isVisible)
  {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.color = color;
    this.isVisible = isVisible;
  }

  /**
   * Draw method to be implemented by all subclasses
   */
  abstract void draw();

  /**
   * Changesize method to be implemented by all subclasses
   * 
   * @param scale 
   */
  abstract void changeSize(int scale);

  /**
   * Sets dimensions of the Shape
   * 
   * @param xPosition
   * @param yPosition
   * @param color
   */
  public void setState(int xPosition, int yPosition, String color)
  {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.color = color;
    isVisible = true;
  }

  /**
   * Changes the colour of the shape
   * 
   * @param color colour of the Shape
   */
  public void changeColor(String color)
  {
    this.color = color;
    draw();
  }

  /**
   * Makes shape visible on canvas
   */
  public void makeVisible()
  {
    isVisible = true;
    draw();
  }

  /**
   * Makes shape invisible on canvas
   */
  public void makeInvisible()
  {
    erase();
    isVisible = false;
  }

  /**
   * Moves the shape to the selected (x,y) co-ordinates on the canvas
   * 
   * @param x new xPosition
   * @param y new yPosition 
   */
  public void moveTo(int x, int y)
  {
    erase();
    this.xPosition = x;
    this.yPosition = y;
    draw();
  }

  /**
   * Erases the shape from the canvas
   */
  protected void erase()
  {
    if (isVisible)
    {
      Canvas canvas = Canvas.getCanvas();
      canvas.erase(this);
    }
  }

}
