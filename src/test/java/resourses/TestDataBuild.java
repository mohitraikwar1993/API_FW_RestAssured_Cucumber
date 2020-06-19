package resourses;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild 
{
	public AddPlace addPlacePayLoad(String name, String language, String address)
	{
		AddPlace ad=new AddPlace();
		ad.setAccuracy(50);
		ad.setAddress(address);
		ad.setLanguage(language);
		ad.setName(name);
		ad.setPhone_number("(+91) 983 893 3937");
		ad.setWebsite("http://google.com");
		Location lc=new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		ad.setLocation(lc);
		List<String> list=new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		ad.setTypes(list);
		return ad;
		
	}
	
	public String deletePlacePayLoad(String placeid)
	{
		
		return "{\r\n" + 
				"    \"place_id\":\""+placeid+"\"\r\n" + 
				"}\r\n" + 
				"";
		
	}
	
	
	
}
