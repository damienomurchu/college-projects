package q1.auction;

import java.util.ArrayList;

/**
 * Class that contains and manages catalogue of auction lots
 * 
 * @author jf
 * @version 07.3.2016
 *
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 * 
 */
public class Catalogue {
  
  /** 
   * List of all Lots in catalogue
   */
  ArrayList<Lot> lots;
  
  /**
   * Constructs a catalogue object
   * Initializes the Lot list field
   */
  public Catalogue() {
    lots = new ArrayList<Lot>();
  }
  
  /**
   * Adds a Lot object to list of lots field
   * 
   * @param lot The Lot object being added
   */
  public void addLot(Lot lot) {
    // TODO 1 
	  lots.add(lot);
  }
  
  /**
   * Constructs a string representation of this object
   * 
   * @return string representation of this object
   */
  public String toString() {    
    String details = "Lots in catalogue:\n";
    // TODO 2
    for (Lot lot : lots) {
    	details += lot.toString() + "\n";
    }
    return details;
  }
  
}
