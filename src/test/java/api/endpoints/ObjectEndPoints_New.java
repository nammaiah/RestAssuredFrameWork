package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Object1;
import io.restassured.response.Response;

public class ObjectEndPoints_New {

	public static ResourceBundle get_URL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("EndPoints");
		return routes;
		
	}
	
	// create employee

	public static Response createObject(Object1 obj) {
		
		String objBaseURL=get_URL().getString("OBJ_BASE_URL");
		
		
		Response res = given()
						//.baseUri(EndPoints.OBJ_BASE_URL)
						.baseUri(objBaseURL)
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.body(obj).
						when()
						.post();
		return res;

	}

	public static Response updateObject(String Id, Object1 obj) {
		
		String objBaseURL=get_URL().getString("OBJ_BASE_URL");
		String objUpdateEndpoint=get_URL().getString("OBJ_UPDATE_ENDPOINT");
		
		System.out.println(EndPoints.OBJ_UPDATE_ENDPOINT+Id);

		Response res = given()
						//.baseUri(EndPoints.OBJ_BASE_URL)
						.baseUri(objBaseURL)
						.pathParam("id", Id)
						.header("Content-Type", "application/json")
						.body(obj).
					  when()
					  	.put(objUpdateEndpoint);

		return res;

	}

	public static Response getObject(String Id) {
		
		String objBaseURL=get_URL().getString("OBJ_BASE_URL");
		String objGetEndpoint=get_URL().getString("OBJ_GET_ENDPOINT");
				
		Response res = given()
						.baseUri(objBaseURL)
						.pathParam("id", Id)
						.header("accept", "application/json")
					  .when()
					  	.get(objGetEndpoint);

		return res;

	}

	public static Response deleteObject(String Id) {
		
		String objBaseURL=get_URL().getString("OBJ_BASE_URL");
		String objDeleteEndpoint=get_URL().getString("OBJ_DELTE_ENDPOINT");
				

		Response res = given()
						.baseUri(objBaseURL).pathParam("id", Id)
				.header("accept", "application/json").when().delete(objDeleteEndpoint);

		return res;

	}
	
	public static Response getALLObjects() {
		
		String objBaseURL=get_URL().getString("OBJ_BASE_URL");
		
		Response res = given()
						.baseUri(objBaseURL)					
						.header("accept", "application/json")
					  .when()
					  	.get();

		return res;

	}

}

