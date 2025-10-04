package api.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.ObjectEndPoints;
import api.payload.Data;
import api.payload.Object1;
import api.utilities.DataProviders;
import api.utilities.XLUtility;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ObjectDDTests {
	
	Object1 objPayload;
	Data data;
	
	public Logger logger;
	
	
	@BeforeClass
	public void setupData()
	{
		objPayload= new Object1();
		logger=LogManager.getLogger(this.getClass());	
								
	}
	
	@Test(priority=1, enabled=false, dataProvider="Datas", dataProviderClass =DataProviders.class )
	public void createObjectTest(String name,String id, String year,String price, String cpuModel, String hardDiskSize) throws InterruptedException
	{
		
		objPayload.setName(name);			
		data=new Data();
		data.setYear(Integer.parseInt(year));		
		data.setPrice(Double.parseDouble(price));
		data.setCpuModel(cpuModel);
		data.setHardDiskSize(hardDiskSize);		
		objPayload.setData(data);		
		
		Response response= ObjectEndPoints.createObject(objPayload);
		int statusCode=response.getStatusCode();	
		Assert.assertEquals(statusCode, 200);		
		JsonPath jsonPath= new JsonPath(response.asString());
		String id1= jsonPath.getString("id");
		objPayload.setId(id1);
		
		System.out.println(response.asString());
				
	}
	
	@Test(priority=1, enabled=false)
	public void createObjectTest2() throws IOException
	{
		
		String filePath=System.getProperty("user.dir")+"//testData//ObjectData.xlsx";
		XLUtility xl=new XLUtility();
		
		int rowCount=xl.getRowCount(filePath, "Sheet1");
		int colCount=xl.getCellCount(filePath, "Sheet1",1);
		
		
		for(int rowNum=1; rowNum<=rowCount;rowNum++)
		{
			String name= xl.getCellData(filePath, "Sheet1", rowNum, 0);
			int year= Integer.parseInt(xl.getCellData(filePath, "Sheet1", rowNum, 2));
			double price= Double.parseDouble(xl.getCellData(filePath, "Sheet1", rowNum, 3));
			String cpuModel=xl.getCellData(filePath, "Sheet1", rowNum, 4);
			String hardDiskSize=xl.getCellData(filePath, "Sheet1", rowNum, 5);
			
			objPayload.setName(name);			
			data=new Data();
			data.setYear(year);	
			data.setPrice(price);
			data.setCpuModel(cpuModel);
			data.setHardDiskSize(hardDiskSize);		
			objPayload.setData(data);		
			
			Response response= ObjectEndPoints.createObject(objPayload);
			int statusCode=response.getStatusCode();	
			Assert.assertEquals(statusCode, 200);		
			JsonPath jsonPath= new JsonPath(response.asString());
			String id1= jsonPath.getString("id");
			objPayload.setId(id1);
			xl.setCellData(filePath, "Sheet1", rowNum, 1, id1);
		
		}
		
			
	}
	
	

	@Test(priority=2, enabled=false, dataProvider="ids", dataProviderClass =DataProviders.class )	
	public void getObjectTest(String id) throws InterruptedException
	{
		Response response= ObjectEndPoints.getObject(id);		
		
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println(response.asPrettyString());
		
	}
	
	
	@Test(priority=3, enabled=false, dataProvider="ids", dataProviderClass =DataProviders.class )	
	public void deleteObjectTest(String id) throws InterruptedException
	{
		Response response= ObjectEndPoints.deleteObject(id);		
		int statusCode=response.getStatusCode();		
		Assert.assertEquals(statusCode, 200);
		System.out.println(response.asPrettyString());
				
	}
	
	
	@Test(priority=5, enabled=false)
	public void getALLObjectsTest() throws InterruptedException
	{
		
		Response response= ObjectEndPoints.getALLObjects();		
		
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println(response.asPrettyString());
		
		JsonPath jsonPath=new JsonPath(response.asString());
		
		int size = jsonPath.getList("$").size(); // total elements
		for (int i = 0; i < size; i++) {
		    String id = jsonPath.getString("[" + i + "].id");
		    String name = jsonPath.getString("[" + i + "].name");
		    String color = jsonPath.getString("[" + i + "].data.color");
		    String capacity = jsonPath.getString("[" + i + "].data.capacity");

		    System.out.println("ID: " + id + ", Name: " + name + 
		        ", Color: " + color + ", Capacity: " + capacity);
		}

		
	}
	
	
	

}
