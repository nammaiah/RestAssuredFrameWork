package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.JSONPlaceHolderClient;
import api.endpoints.ObjectEndPoints;
import api.payload.Data;
import api.payload.Object1;
import api.utilities.CommonApiValidations;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class JSONPlaceHolderTests {
	
	Faker faker;
	
	
	public Logger logger;
	
	
	@BeforeClass
	public void setupData()
	{
		/*
		 * objPayload= new Object1(); faker=new Faker();
		 * 
		 * objPayload.setName(faker.name().username()); int
		 * year=faker.number().numberBetween(2000, 2020); double price=
		 * faker.number().numberBetween(10, 200); String
		 * cpuModel=faker.computer().brand(); String
		 * hardDiskSize=faker.computer().operatingSystem(); data=new Data();
		 * data.setYear(year); data.setPrice(price); data.setCpuModel(cpuModel);
		 * data.setHardDiskSize(hardDiskSize); objPayload.setData(data);
		 */
		//logs
		
		logger=LogManager.getLogger(this.getClass());		
						
	}
	
	/*
	 * @Test(priority=1, enabled=false) public void createObjectTest() throws
	 * InterruptedException { logger.info("******Object Creation test****");
	 * Response response= ObjectEndPoints.createObject(objPayload); int
	 * statusCode=response.getStatusCode(); Assert.assertEquals(statusCode, 200);
	 * JsonPath jsonPath= new JsonPath(response.asString()); String id=
	 * jsonPath.getString("id"); objPayload.setId(id);
	 * 
	 * logger.info("******Object Created****");
	 * 
	 * 
	 * }
	 */

	@Test(priority = 1, enabled = false)
	public void postToDOTest() throws InterruptedException {
		logger.info("******Post Todo test****");

		Response response = JSONPlaceHolderClient.postToDO();

		int statusCode = response.getStatusCode();	
		
		JsonPath jsonPath=new JsonPath(response.asString());	

		System.out.println(response.asPrettyString());

		
		Assert.assertEquals(statusCode, 201);
		//System.out.println("statusCode: "+statusCode+" userID: "+userID+"title: "+title);

	}

	
	
	@Test(priority = 1, enabled = false)
	public void getAllCommentsPostTest() throws InterruptedException {
		logger.info("******Get All Comments for a Post test****");

		Response response = JSONPlaceHolderClient.getAllCommentsPost();

		int statusCode = response.getStatusCode();	
		
		JsonPath jsonPath=new JsonPath(response.asString());
		
		int numOfComments = jsonPath.getList("$").size(); // total 
		
		boolean postIDFlag=true;

		for(int i=0;i<numOfComments;i++)
		{
		
			int postID= Integer.parseInt(jsonPath.getString("[" + i + "].postId"));		
			
			System.out.println(jsonPath.getString("[" + i + "].postId"));
			
			if(postID!=1)
			{
				postIDFlag=false;
			}
		}
		
		Assert.assertEquals(statusCode, 200);

		Assert.assertEquals(numOfComments, 5);
		

		Assert.assertEquals(postIDFlag, true);
		System.out.println(response.asPrettyString());
		
		System.out.println("statusCode: "+statusCode+" numOfComments: "+numOfComments+"PostID flag: "+postIDFlag);

	}
	
	@Test(priority = 1, enabled = true)
	public void getSinglePostTest() throws InterruptedException {
		logger.info("******Get Posts test****");

		Response response = JSONPlaceHolderClient.getPost();

		int statusCode = response.getStatusCode();	
		
		int userId = response.jsonPath().getInt("userId");

		String title = response.jsonPath().getString("title");
		
		
		Assert.assertEquals(statusCode, 200);

		Assert.assertEquals(userId, 1);
		Assert.assertTrue(!title.isEmpty());
		
		System.out.println(response.asPrettyString());
		
		System.out.println("statusCode: "+statusCode+" userId: "+userId+" title: "+title);
		
		//JSON Schema validation
		
		String jsonShemaFilePath=System.getProperty("user.dir")+"//src//test//resources//SinglePost_JSON_Schema.json";
		
		CommonApiValidations.validateJsonSchema(response, jsonShemaFilePath);

	}
		/*
		 * @Test(priority=3, enabled=false) public void updateObjectTest() throws
		 * InterruptedException { logger.info("******Update Object test****");
		 * 
		 * objPayload.setName(faker.name().username());
		 * 
		 * int year=faker.number().numberBetween(2000, 2020); double price=
		 * faker.number().numberBetween(10, 200); String
		 * cpuModel=faker.computer().brand(); String
		 * hardDiskSize=faker.computer().operatingSystem(); data=new Data();
		 * data.setYear(year); data.setPrice(price); data.setCpuModel(cpuModel);
		 * data.setHardDiskSize(hardDiskSize); objPayload.setData(data);
		 * 
		 * System.out.println(objPayload.toString());
		 * 
		 * Response response= ObjectEndPoints.updateObject(objPayload.getId(),
		 * objPayload); System.out.println(response.asPrettyString()); int
		 * statusCode=response.getStatusCode(); Assert.assertEquals(statusCode, 200); }
		 * 
		 * @Test(priority=4, enabled=false) public void deleteObjectTest() throws
		 * InterruptedException { logger.info("******Delete Object test****");
		 * 
		 * Response response= ObjectEndPoints.deleteObject(objPayload.getId()); int
		 * statusCode=response.getStatusCode(); Assert.assertEquals(statusCode, 200);
		 * 
		 * }
		 * 
		 * 
		 * @Test(priority=5, enabled=true) public void getALLObjectsTest() throws
		 * InterruptedException {
		 * 
		 * Response response= ObjectEndPoints.getALLObjects();
		 * 
		 * int statusCode=response.getStatusCode(); Assert.assertEquals(statusCode,
		 * 200); System.out.println(response.asPrettyString());
		 * 
		 * JsonPath jsonPath=new JsonPath(response.asString());
		 * 
		 * int size = jsonPath.getList("$").size(); // total elements for (int i = 0; i
		 * < size; i++) { String id = jsonPath.getString("[" + i + "].id"); String name
		 * = jsonPath.getString("[" + i + "].name"); String color =
		 * jsonPath.getString("[" + i + "].data.color"); String capacity =
		 * jsonPath.getString("[" + i + "].data.capacity");
		 * 
		 * System.out.println("ID: " + id + ", Name: " + name + ", Color: " + color +
		 * ", Capacity: " + capacity); }
		 * 
		 * 
		 * }
		 */	  
	 	
	

}
