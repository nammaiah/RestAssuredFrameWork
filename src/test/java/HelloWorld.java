import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class HelloWorld {
	
	@Test(enabled=true)
	public void sampleTest()
	{
		System.out.println("Hello World");
		
		given()
		.header("x-api-key", "reqres-free-v1")
		.when()
		.get("https://reqres.in/api/users?page=5")
		.then().statusCode(200)
		.log().all();
		
		
		
	}

}
