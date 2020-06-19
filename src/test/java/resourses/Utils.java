 package resourses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils
{
	static RequestSpecification reqspec;
	static ResponseSpecification respspec;
	public RequestSpecification requestSpecification() throws IOException
	{
			if(reqspec==null)
			{		
				PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
				reqspec=new RequestSpecBuilder().setBaseUri(getGlobal("BaseURI"))
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
				return reqspec;
			}
			return reqspec;
	}
	
	public ResponseSpecification responseSpecification()
	{
		
		respspec=new ResponseSpecBuilder().expectStatusCode(200)
				.build();
		return respspec;
		
	}
	
	public String getGlobal(String key) throws FileNotFoundException, IOException
	{
		Properties pro=new Properties();
		pro.load(new FileInputStream("./src/test/java/resourses/global.properties"));
		return pro.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		JsonPath js=new JsonPath(response.asString());
		return js.get(key).toString();
	}
	
	
	
	
}
