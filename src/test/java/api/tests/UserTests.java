package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	
	@BeforeClass
	public void setupData()
	{
		userPayload= new User();
		faker=new Faker();
		
		userPayload.setId(faker.idNumber().hashCode());
		/*
		 * String userName= faker.name().username(); userPayload.setUsername(userName);
		 * System.out.println(userName);
		 */
		
		userPayload.setUsername(faker.name().username());		
		userPayload.setFirstName(faker.name().firstName());		
		userPayload.setLastName(faker.name().lastName());	
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));	
		logger=LogManager.getLogger(this.getClass());	
	}
	
	@Test(priority=1)
	public void createUserTest()
	{
		logger.info("******Employee Creation test****");
		
		Response response= UserEndPoints.postUser(userPayload);
		int statusCode=response.getStatusCode();	
	
		Assert.assertEquals(statusCode, 200);
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(priority=2,enabled=false)
	public void getUserTest()
	{
		logger.info("******Employee Creation test****");
		
		//Response response= UserEndPoints.getUser("test");
		
		Response response= UserEndPoints.getUser(userPayload.getUsername());
		
		
		int statusCode=response.getStatusCode();	
	
		Assert.assertEquals(statusCode, 200);		
		
	}
	
	@Test(priority=3,enabled=false)
	public void updateUserTest()
	{
		
		userPayload.setUsername(faker.name().username());		
		userPayload.setFirstName(faker.name().firstName());		
		userPayload.setLastName(faker.name().lastName());	
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));	
		
		//Response response= UserEndPoints.updateUser("test", userPayload);
		Response response= UserEndPoints.updateUser(userPayload.getUsername(), userPayload);		
		
		
		int statusCode=response.getStatusCode();	
	
		Assert.assertEquals(statusCode, 200);		
		
	}
	

	@Test(priority=4,enabled=false)
	public void deleteUserTest()
	{
		//Response response= UserEndPoints.deleteUser("test");
		
		Response response= UserEndPoints.deleteUser(userPayload.getUsername());	
		
		int statusCode=response.getStatusCode();	
	
		Assert.assertEquals(statusCode, 200);		
		
	}
	
	
	

}
