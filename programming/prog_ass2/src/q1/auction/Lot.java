package q1.auction;

/**
 * Class to create and manage an auction lot
 * 
 * @author jf
 * @version 07.3.2016
 *
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 *
 */
public class Lot {

  /**
   * Unique id number of Lot
   */
  final int lotId;

  /**
   * A description of the lot
   */
  String description;

  /**
   * Construct a Lot, initializing its number and description.
   * 
   * @param number The lot number.
   * @param description A description of this lot.
   */
  public Lot(int lotId, String description) {
    this.lotId = lotId;
    this.description = description;
  }

  /**
   * Constructs a string representation of this object
   * 
   * @return string representation of this object
   */
  public String toString() {
    return "Lot id: " + lotId + ": " + description;
  }   
}
