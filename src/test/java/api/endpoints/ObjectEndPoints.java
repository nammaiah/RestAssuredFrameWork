package api.endpoints;

import static io.restassured.RestAssured.given;
import api.payload.Object1;
import io.restassured.response.Response;

public class ObjectEndPoints {

	// create employee

	public static Response createObject(Object1 obj) {
		
		Response res = given()
						.baseUri(EndPoints.OBJ_BASE_URL)
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.body(obj).
						when()
						.post();
		return res;

	}

	public static Response updateObject(String Id, Object1 obj) {
		
		System.out.println(EndPoints.OBJ_UPDATE_ENDPOINT+Id);

		Response res = given()
						.baseUri(EndPoints.OBJ_BASE_URL)
						.pathParam("id", Id)
						.header("Content-Type", "application/json")
						.body(obj).
					  when()
					  	.put(EndPoints.OBJ_UPDATE_ENDPOINT);

		return res;

	}

	public static Response getObject(String Id) {
				
		Response res = given()
						.baseUri(EndPoints.OBJ_BASE_URL)
						.pathParam("id", Id)
						.header("accept", "application/json")
					  .when()
					  	.get(EndPoints.OBJ_GET_ENDPOINT);

		return res;

	}

	public static Response deleteObject(String Id) {

		Response res = given()
						.baseUri(EndPoints.OBJ_BASE_URL).pathParam("id", Id)
				.header("accept", "application/json").when().delete(EndPoints.OBJ_DELTE_ENDPOINT);

		return res;

	}
	
	public static Response getALLObjects() {
		
		Response res = given()
						.baseUri(EndPoints.OBJ_BASE_URL)					
						.header("accept", "application/json")
					  .when()
					  	.get();

		return res;

	}

}
