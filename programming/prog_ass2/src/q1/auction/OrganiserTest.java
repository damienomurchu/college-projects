package q1.auction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Organiser. 
 * 
 * Tests: 
 * submitBids()
 */
public class OrganiserTest {
	
  Organiser org = new Organiser();  

	@Test
	public void testSubmitBids() {
	  
	  /* build catalogue, create audience, and open bidding */
	  org.buildCatalogue();
	  org.createAudience();
	  org.auction.openBidding();
	  
		assertEquals(true, org.submitBids());		
	}

}
