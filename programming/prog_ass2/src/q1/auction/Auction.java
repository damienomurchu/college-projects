package q1.auction;


import java.util.ArrayList;
import java.util.HashMap;
/**
 * Class to manage an auction
 * Accepts bids
 * Runs auction
 * Creates collection winning bids
 * Reports on status particular bid
 * 
 * @author jf
 * @version 07.3.2016
 *
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 *
 */
public class Auction {
  
  /**
   * Map of lists of bids.
   * A list of bids exists for each lot.
   * Map key is the lotId integer value in Integer wrapper.
   * Value is the arraylist of bids for a specific lot
   */
  private HashMap<Integer, ArrayList<Bid>> bids;

  /**
   * A list of winning bids
   */
  private ArrayList<Bid> winningBids;

  /**
   *  Flag that indicates if the auction is open for bidding or not
   */
  boolean biddingOpen;

  /**
   * Creates a new auction from the catalogue
   * 
   * @param catalogue the catalogue used to create the auction
   */
  public Auction(Catalogue catalogue) {
    // TODO 1
    bids = new HashMap<Integer, ArrayList<Bid>>();
    winningBids = new ArrayList<Bid>();
    biddingOpen = false;
  }

  /**
   * Allows bidding
   */
  public void openBidding() {
    // TODO 2
    biddingOpen = true;
  }

  /**
   * Closes bidding - no further bids accepted
   */
  public void closeBidding() {  
    // TODO 3
    biddingOpen = false;
  }

  /**
   * The auction comprises a check on all bids
   * Winning bid is second highest for each lot
   * Traverse map of bids
   * Sort each array list of bids (corresponding to each lot)
   * And sell to second highest bid
   */
  public void holdAuction() {
    for (Integer key : bids.keySet()) {
      // TODO 5, 6, 7, 8
      ArrayList<Bid> listBids = bids.get(key);
      //SelectSort.sort(listBids);
      MergeSort.sort(listBids);
      Bid winningBid = listBids.get(listBids.size()-2);
      winningBids.add(winningBid);
    }
  } 

  /**
   * Submit a bid
   * The method adds the bid to the map of bids
   * The map key is the number of the lot being bid for
   * The corresponding map value is the array list of bids associated with that key
   * 
   * @param bid The bid being submitted
   * @return true if the bid accepted else false
   */
  public boolean submitBid(Bid bid) {
    if (biddingOpen == false) {
      System.out.println("Bid not accepted: bidding closed");
      return false;
    }

    Integer key = bid.lot.lotId;
    ArrayList<Bid> listBids = bids.get(key);
    if (listBids == null) {
      listBids = new ArrayList<Bid>();
    }
    listBids.add(bid);
    bids.put(key, listBids);
    return true;
  }

  /**
   * Checks if a particular person won the auction for particular lot
   * Traverse all winning bids
   * Search for match of lotId and person in each element of winning bids list
   * 
   * @param lotId The id of the lot in question
   * @param person The person whose bid is being checked
   * @return True if the person's bid successful else return false
   */
  public boolean status(int lotId, Person person) {
    for (Bid bid : winningBids) {
      // TODO 9
      if ((lotId == bid.lot.lotId) && (bid.bidder == person)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Constructs string representation of object
   * @return string representation of this object
   */
  public String toString() {
    String details = "Auction";
    for (Integer key : bids.keySet()) {
      details += "\nLot key : " + key + "\n";
      ArrayList<Bid> listBids = bids.get(key);
      for (Bid bid : listBids) {
        details += bid;
      }
    }     
    details += "\n" + "Winning bids \n" + detailsWinningBids();
    return details;
  }

  /**
   * Helper method for toString method. Returns list detailing all winning bids
   * @return
   */
  private String detailsWinningBids() {
    String details = new String();
    for (Bid bid : winningBids) {
      details += bid;
    }
    return details;
  }
}
