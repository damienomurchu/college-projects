package app.donation.api;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.donation.model.Candidate;
import app.donation.model.User;
import app.donation.model.Donation;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DonationServiceAPI 
{
	DonationService service;
	
	public DonationServiceAPI(String url)
	{
		String service_url = url;
		
		Gson gson = new GsonBuilder().create();
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(service_url)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		service = retrofit.create(DonationService.class);
	}
	
	public List<User> getUsers() throws Exception
  {
    Call<List<User>> call = (Call<List<User>>) service.getAllUsers();
    Response<List<User>> users = call.execute();
    return users.body();
  }

  public List<Candidate> getAllCandidates() throws Exception
  {
    Call<List<Candidate>> call = (Call<List<Candidate>>) service.getAllCandidates();
    Response<List<Candidate>> candidates = call.execute();
    return candidates.body();
  }

  public List<Donation> getAllDonations() throws Exception
  {
    Call<List<Donation>> call = (Call<List<Donation>>) service.getAllDonations();
    Response<List<Donation>> donations = call.execute();
    return donations.body();
  }

  public User createUser(User newUser) throws Exception
  {
    Call<User> call = (Call<User>) service.createUser(newUser);
    Response<User> returnedUser = call.execute();
    return returnedUser.body();
  }

  public Donation createDonation(Candidate candidate, Donation newDonation) throws Exception
  {

    Call<Donation> call = (Call<Donation>) service.createDonation(candidate._id, newDonation);
    Response<Donation> returnedDonation = call.execute();
    return returnedDonation.body();
  }
}