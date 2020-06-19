package resourses;

public enum API_Resourses 
{
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resourse;
	API_Resourses(String resourse)
	{
		this.resourse=resourse;
	}
	
	
	public String getResourse()
	{
		return resourse;
		
	}
	
}
	
	

