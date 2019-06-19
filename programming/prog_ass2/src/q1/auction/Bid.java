package q1.auction;

/**
 * Class to manage bids
 * A bid comprises:
 * an item or lot being bid for
 * a bidder
 * an amount being bid or offered for the lot
 * 
 * @author jf
 * @version 07.3.2016
 *
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 *
 */
public class Bid {

  Lot lot;
  Person bidder;
  final int amountBid;

  /**
   * Constructs a Bid object
   * 
   * @param lot the subject of the bid
   * @param bidder person bidding
   * @param amountBid amount bid for lot
   */
  public Bid(Lot lot, Person bidder, int amountBid) {
    // TODO 1
    this.lot = lot;
    this.bidder = bidder;
    this.amountBid = amountBid;
  }

  /**
   * Accessor for amountBid field
   * @return The amount of this bid
   */
  public int getAmountBid() {
    return amountBid;
  }

  /**
   * Constructs a string representation of this object
   * @return string representation of this object
   */    
  public String toString() {
    // TODO 2
    return "Lot: " + lot + ". Bidder: " + bidder + ". Amount of Bid: " + amountBid + "\n";

  }
}
