package api.user;


import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Random;

import api.endpoints.EndPoints;
import io.restassured.response.Response;

public class User {
	
	public static Response postUser()
	{
		//Request payload creation
		
		Random random= new Random();
		HashMap<String, Object> data=new HashMap();
		int randomValue= random.nextInt();
		data.put("id", randomValue);
		data.put("username", "user"+randomValue);
		data.put("firstName", "Automatino");
		data.put("lastName", "Testing");
		data.put("email", "Automn.Test@gmail.com");
		data.put("password", "Passwod343dfd");
		data.put("phone", "334333");
		data.put("userStatus", 0);	
		
				
		Response res= given()
						.baseUri(EndPoints.BASE_URL)
						.header("x-api-key", "reqres-free-v1")
						.body(data)
					  .when()
						.post(EndPoints.POST_ENDPOINT);
						
		return res;
		
		
		
	}
	
	public static Response updateUser()
	{
		//Request payload creation
		
		Random random= new Random();
		HashMap<String, Object> data=new HashMap();
		int randomValue= random.nextInt();
		data.put("id", randomValue);
		data.put("username", "user"+randomValue);
		data.put("firstName", "Automatino");
		data.put("lastName", "Testing");
		data.put("email", "Automn.Test@gmail.com");
		data.put("password", "Passwod343dfd");
		data.put("phone", "334333");
		data.put("userStatus", 0);	
		
				
		Response res= given()
						.header("x-api-key", "reqres-free-v1")
						.body(data)
					  .when()
						.put(EndPoints.POST_ENDPOINT);
						
		return res;
		
	}
	
	
	
	public static Response getUser()
	{
			
				
		Response res= given()
						.baseUri(EndPoints.BASE_URL)
						.baseUri(EndPoints.BASE_URL)
						.header("x-api-key", "reqres-free-v1")
					  .when()
					  	.get(EndPoints.GET_ENDPOINT);
		
		return res;
		
	}

	public static Response deleteUser()
	{
			
				
		Response res= given()
						.baseUri(EndPoints.BASE_URL)
						.header("x-api-key", "reqres-free-v1")
					  .when()
					  	.delete(EndPoints.DELTE_ENDPOINT);
				
		return res;
		
	}

	

}
