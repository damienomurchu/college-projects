package controllers;

import java.util.List;

import models.Donation;
import play.mvc.Controller;

public class DonationController extends Controller
{ 
  public static void index()
  {
    int percentDonated = getDonations();
    render(percentDonated);
  }
  
  public static void report()
  {
    List<Donation> donations = Donation.findAll();
    
    render(donations);
  }
  
  public static void donate(Donation donation)
  {
    donation.donor = Accounts.loggedInUser();
    
    if ((donation.amount != null) && (donation.method !=null) && (donation.donor != null))
    {
      donation.save();    
    }
    
    index();
  }
  
  public static int getDonations()
  {
    Long amountDonated = (long) 0;
    
    List<Donation> donations = Donation.findAll();
    
    for (Donation donation : donations) 
    {
      amountDonated += donation.amount;
    }
    
    return ((int) ((amountDonated * 100)/ 10000));
  }
}
