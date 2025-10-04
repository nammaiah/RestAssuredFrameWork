package api.endpoints;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

public class AdhocTest {
	
	
@Test(enabled=true)
public void responseTimeValidation()
{
	String url="https://dog.ceo/api/breeds/image/random";
	
	Response res=  given().
					when()
					.get(url);
	
	System.out.println(res.getStatusLine());
	System.out.println(res.getTimeIn(TimeUnit.MILLISECONDS));
	System.out.println(res.getTimeIn(TimeUnit.SECONDS));	
	System.out.println(res.getStatusLine().contains("OK"));	
	System.out.println(res.jsonPath().getString("status"));
}
	
}
