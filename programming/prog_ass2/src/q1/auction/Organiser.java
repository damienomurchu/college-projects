package q1.auction;

import java.util.ArrayList;

/**
 * @brief Class to organise auction
 * @author jf
 * @version 07.3.2016
 *
 */
public class Organiser {
  static final int MIN_BID_AMOUNT = 100;
  static final int MAX_BID_AMOUNT = 1000;

  /**
   * An Audience of Persons
   */
  Audience audience;
  
  /**
   * A Catalogue of Lots
   */
  Catalogue catalogue;
  
  /**
   * An Auction of Lots and Bids
   */
  Auction auction;
  
  /**
   * Create arrays list of Person type
   */
  ArrayList<Person> people = new ArrayList<Person>();

  /** 
   * Constructs an Organiser object and initialises its audience, catalogue and auction
   */
  public Organiser() {
    audience = new Audience();
    catalogue = new Catalogue();
    auction = new Auction(catalogue);
  }

  /**
   * Creates a new Organiser object and calls all test methods
   */
  public static void main(String[] args) {
    Organiser organise = new Organiser();
    organise.buildCatalogue();
    organise.createAudience();
    organise.auction.openBidding();
    organise.submitBids();
    organise.auction.closeBidding();
    organise.holdAuction();
    organise.display();
    organise.checkBidStatus();
    organise.printCatalogue();
  }
  
  /** 
   * Creates sample People objects and registers them in audience 
   */
  void createAudience() {
    people.add(new Person("homer", "simpson", "homer@simpson.com", Util.generatePin()));
    people.add(new Person("marge", "simpson", "marge@simpson.com", Util.generatePin()));
    people.add(new Person("bart", "simpson", "bart@simpson.com", Util.generatePin()));
    people.add(new Person("lisa", "simpson", "lisa@simpson.com", Util.generatePin()));
    people.add(new Person("maggie", "simpson", "maggie@simpson.com", Util.generatePin()));
    people.add(new Person("barney", "gumble", "barney@simpson.com", Util.generatePin()));
    people.add(new Person("edna", "krabappel", "edna@simpson.com", Util.generatePin()));
    people.add(new Person("ned", "flanders", "ned@simpson.com", Util.generatePin()));
    people.add(new Person("moe", "szyslak", "moe@simpson.com", Util.generatePin()));

    // traverse list of people and register each person as member of audience
    for (Person person : people) {
      if (!this.audience.register(person)) {
        System.out.println("Failed to register " + person.email);
      }
    }

  }

  /**
   * Create a catalogue of Lot items
   */
  void buildCatalogue() {
    ArrayList<Lot> lots = new ArrayList<Lot>();
    int lotId = 1;
    lots.add(new Lot(lotId++, "Hamal Carpet"));
    lots.add(new Lot(lotId++, "Rosewood Recamier"));
    lots.add(new Lot(lotId++, "Tea Storage Jar"));
    lots.add(new Lot(lotId++, "Glass Chandelier"));

    // traverse list of lots and add to catalogue
    for (Lot lot : lots) {
      this.catalogue.addLot(lot);
    }
  }

  /**
   * Prints catalogue
   */
  public void printCatalogue() {
    System.out.println(catalogue);   
  }

  /**
   * Registered audience members submit bids
   */
  boolean submitBids() {
    boolean isSubmittedOk = true;
    // traverse audience
    // for each member audience submit bid for each item in catalogue
    for (Person person : audience.members) {
      for (Lot lot : catalogue.lots) {
        int amountBid = Util.randomIntInRange(MIN_BID_AMOUNT, MAX_BID_AMOUNT);
        Bid bid = new Bid(lot, person, amountBid);
        if (audience.isRegistered(person)) {
          auction.submitBid(bid);
        } else {
          System.out.println(person.email + " is not a registered member of audience and so cannot submit a bid");
          isSubmittedOk = false;
        }
      }
    }
    return isSubmittedOk;
  }

  /**
   * Auction opens, bids accepted, bids analysed. 
   * Sold to the man wearing mask and dark glasses.
   */
  void holdAuction() {
    auction.holdAuction();
  }

  /**
   * Prints out Auction object
   */
  public void display() {
    System.out.println(auction);
  }

  /**
   * Check bid status for a particular lot
   * 
   * @param lotId The id of the particular lot for which status requested
   * @param email The email of the applicant
   * @param pin The pin number of the applicant
   * @return The status of the bid including amount, ranking among all submitted bids
   */
  public String checkBidStatus(int lotId, String email, int pin) {
    Person person = audience.isRegistered(email, pin);
    if (person != null) {
      return auction.status(lotId, person) ? " successful" : "unsuccessful";
    }
    return "Failed to authenticate: unable to provide information requested";
  }

  /**
   * Test : checkBidStatus for particular bidder
   */
  public void checkBidStatus() {
    System.out.println("Complete bid status list");
    for (int lotId = 1; lotId <=4; lotId += 1) {
      for (Person person : people) {
        //int arrayIndex = 1;
        //Person person = people.get(arrayIndex);
        int pin = person.getPin();
        String email = person.email;
        System.out.println("Lot id " + lotId + " " + person.email + " " + checkBidStatus(lotId, email, pin));
      }
      System.out.println();
    }
  }
  
}
