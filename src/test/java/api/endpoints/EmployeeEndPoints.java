package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.Random;

import api.payload.Employee;
import api.payload.User;
import io.restassured.response.Response;

public class EmployeeEndPoints {

	// create employee

	public static Response createEmployee(Employee data) {
		
		Response res = given()
						.baseUri(EndPoints.EMP_BASE_URL)
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.body(data).
						when()
						.post(EndPoints.EMP_POST_ENDPOINT);
		return res;

	}

	public static Response updateEmployee(int Id, Employee data) {

		Response res = given()
						.baseUri(EndPoints.EMP_BASE_URL)
						.pathParam("id", Id)
						.header("accept", "application/json")
						.body(data).
					  when()
					  	.put(EndPoints.EMP_UPDATE_ENDPOINT);

		return res;

	}

	public static Response getEmployee(int Id) {
				
		Response res = given()
						.baseUri(EndPoints.EMP_BASE_URL)
						.pathParam("id", Id)
						.header("accept", "application/json")
					  .when()
					  	.get(EndPoints.EMP_GET_ENDPOINT);

		return res;

	}

	public static Response deleteEmployee(int Id) {

		Response res = given()
						.baseUri(EndPoints.EMP_BASE_URL).pathParam("id", Id)
				.header("accept", "application/json").when().delete(EndPoints.EMP_DELTE_ENDPOINT);

		return res;

	}

}
