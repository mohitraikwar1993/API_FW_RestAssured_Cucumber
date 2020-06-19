package stepDefinations;
import pojo.*;
import resourses.API_Resourses;
import resourses.TestDataBuild;
import resourses.Utils;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.*;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils {
	
	RequestSpecification reqs;
	static Response response;
	TestDataBuild data=new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException 
	{
		reqs=given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name, language, address));
	}

	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourse, String method) 
	{
		API_Resourses resapi=API_Resourses.valueOf(resourse); 
		
					if(method.equalsIgnoreCase("POST")) 
					{
					response=reqs.when()
								  .post(resapi.getResourse());
					}
					else if (method.equalsIgnoreCase("GET")) 
					{
					response=reqs.when()
								  .get(resapi.getResourse());
					}
	}

	
	
	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer i) 
	{
		response.then()
				.spec(responseSpecification())
				.extract().response();
				assertEquals(response.getStatusCode(), i.intValue());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String val) {
		String resp=response.asString();
		//System.out.println("response="+response);
		JsonPath js=new JsonPath(resp);
		assertEquals(getJsonPath(response, key), val );
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourse) throws IOException {
		
		place_id=getJsonPath(response, "place_id");
		reqs=given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_http_request(resourse, "GET");
		assertEquals(expectedName,getJsonPath(response,"name"));		
		
		
	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	   
		reqs=given().spec(requestSpecification())
					.body(data.deletePlacePayLoad(place_id));
		
	}

	

}
