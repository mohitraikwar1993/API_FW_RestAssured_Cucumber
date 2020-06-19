package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks 
{

	@Before("@deletePlace")
	public String beforeTest() throws IOException
	{
		
		StepDefination sd=new StepDefination();
		if(sd.place_id==null)
		{
		sd.add_Place_Payload_with("Shahpura", "Hindi", "Bhopal");
		sd.user_calls_with_http_request("addPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("Shahpura", "getPlaceAPI");
		return sd.place_id;
		}
		return sd.place_id;
		
	}
	
}
