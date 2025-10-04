package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.JSONPLACEHOLDER;
import api.payload.Object1;
import io.restassured.response.Response;

public class JSONPlaceHolderClient {

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

	public static Response getPost() {
				
		Response res = given()
						.baseUri(EndPoints.JSONPH_BASE_URL)						
						//.header("accept", "application/json")
					  .when()
					  	.get(EndPoints.JP_GET_ENDPOINT);

		return res;

	}
	
	public static Response getAllCommentsPost() {
		
		Response res = given()
						.baseUri(EndPoints.JSONPH_BASE_URL)						
						//.header("accept", "application/json")
					  .when()
					  	.get(EndPoints.JP_GET_ALL_COMMENTS_ENDPOINT);

		return res;

	}
	
	public static Response postToDO() {
		
		/*
		 * String data="{\r\n" + "  \"userId\": 1,\r\n" +
		 * "  \"title\": \"Learn RestAssured\",\r\n" + "  \"completed\": false\r\n" +
		 * "}";
		 */		
		String data = "{"
			    + "\"userId\": 1,"
			    + "\"title\": \"Learn RestAssured\","
			    + "\"completed\": false"
			    + "}";
		
		JSONPLACEHOLDER dataPayload=new JSONPLACEHOLDER();
		dataPayload.setUserId(1);
		dataPayload.setTitle("test");
		dataPayload.setCompleted(false);
				
		
		Response res = given()
						.baseUri(EndPoints.JSONPH_BASE_URL)	
						.body(dataPayload)
						//.header("accept", "application/json")
					  .when()
					  	.post(EndPoints.JP_POST_TODO_ENDPOINT);

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
