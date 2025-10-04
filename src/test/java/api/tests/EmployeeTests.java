package api.tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.EmployeeEndPoints;
import api.payload.Employee;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class EmployeeTests {
	
	Faker faker;
	Employee empPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		empPayload= new Employee();
		faker=new Faker();
		
		empPayload.setName(faker.name().username());
		empPayload.setAge(faker.number().numberBetween(1, 100));		
		empPayload.setSalary(faker.number().numberBetween(1000, 50000));	
		logger=LogManager.getLogger(this.getClass());	
	}
	
	@Test(priority=1)
	public void createEmployeeTest() throws InterruptedException
	{
		{
		logger.info("******Employee Creation test****");
			
		Response response= EmployeeEndPoints.createEmployee(empPayload);
		int statusCode=response.getStatusCode();	
				
		int count=1;		
		while(count<=5)
		{
			if(statusCode != 429)
			{
				break;				
			}
			else
			{
				Thread.sleep(Duration.ofSeconds(2));
				response= EmployeeEndPoints.getEmployee(empPayload.getId());
				statusCode=response.getStatusCode();				
			}			
		}	
		
	
		Assert.assertEquals(statusCode, 200);
		
		JsonPath jsonPath= new JsonPath(response.asString());
		int id= jsonPath.getInt("data.id");
		empPayload.setId(id);
		
		//System.out.println(response.asPrettyString());
		
		}
	}
	
	@Test(priority=2, enabled=false)
	public void getEmployeeTest() throws InterruptedException
	{
		logger.info("******Employee Creation test****");
		
		System.out.println("ID: "+empPayload.getId());
		Response response= EmployeeEndPoints.getEmployee(empPayload.getId());		
		
		int statusCode=response.getStatusCode();		
		
		//Retry mechanism incase of failure..
		
		int count=1;		
		while(count<=5)
		{
			if(statusCode != 429)
			{
				break;				
			}
			else
			{
				Thread.sleep(Duration.ofSeconds(2));
				response= EmployeeEndPoints.getEmployee(empPayload.getId());
				statusCode=response.getStatusCode();
				System.out.println("Get Employee Failure counts: "+count);
			}
			count++;
		}		
		
		Assert.assertEquals(statusCode, 200);
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(priority=3, enabled=false)
	public void updateEmployeeTest() throws InterruptedException
	{
		empPayload.setName(faker.name().username());
		empPayload.setAge(faker.number().numberBetween(1, 100));		
		empPayload.setSalary(faker.number().numberBetween(1000, 50000));
		
		Response response= EmployeeEndPoints.updateEmployee(empPayload.getId(), empPayload);
		
		int statusCode=response.getStatusCode();	
		
		//Retry mechanism incase of failure..
		
			int count=1;		
			while(count<=5)
			{
				if(statusCode != 429)
				{
					break;				
				}
				else
				{
					Thread.sleep(Duration.ofSeconds(2));
					response= EmployeeEndPoints.updateEmployee(empPayload.getId(), empPayload);
					statusCode=response.getStatusCode();	
					System.out.println("Update Employee Failure counts: "+count);
				}	
				count++;
			}					
	
		Assert.assertEquals(statusCode, 200);			
		
	}
	
	@Test(priority=4, enabled=false)
	public void deleteUserTest() throws InterruptedException
	{
		Response response= EmployeeEndPoints.deleteEmployee(empPayload.getId());		
		int statusCode=response.getStatusCode();		
		
		//Retry mechanism incase of failure..
		
		int count=1;		
		while(count<=5)
		{
			if(statusCode != 429)
			{
				break;				
			}
			else
			{
				Thread.sleep(Duration.ofSeconds(2));
				response= EmployeeEndPoints.deleteEmployee(empPayload.getId());
				statusCode=response.getStatusCode();	
				System.out.println("Delete Employee Failure counts: "+count);
			}
			count++;
		}		
		
		Assert.assertEquals(statusCode, 200);	
		
	}
	
	
	

}
