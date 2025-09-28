package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Random;

import org.testng.annotations.Test;

import api.endpoints.EndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints {

	// create user

	public static Response postUser(User data) {
		// Request payload creation

		/*
		 * Random random = new Random(); HashMap<String, Object> data = new HashMap<>();
		 * int randomValue = random.nextInt(); data.put("id", randomValue);
		 * data.put("username", "user" + randomValue); data.put("firstName",
		 * "Automatino"); data.put("lastName", "Testing"); data.put("email",
		 * "Automn.Test@gmail.com"); data.put("password", "Passwod343dfd");
		 * data.put("phone", "334333"); data.put("userStatus", 0);
		 */
		
		Random random = new Random();		
		int randomValue = random.nextInt();
		data.setId(randomValue);	
		
		

		Response res = given()
						.baseUri(EndPoints.BASE_URL)
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.body(data).
						when()
						.post(EndPoints.POST_ENDPOINT);

		return res;

	}

	public static Response updateUser(String userName, User data) {
		// Request payload creation

		/*
		 * Random random = new Random(); HashMap<String, Object> data = new HashMap();
		 * int randomValue = random.nextInt(); data.put("id", randomValue);
		 * data.put("username", "user" + randomValue); data.put("firstName",
		 * "Automatino"); data.put("lastName", "Testing"); data.put("email",
		 * "Automn.Test@gmail.com"); data.put("password", "Passwod343dfd");
		 * data.put("phone", "334333"); data.put("userStatus", 0);
		 */
		

		Random random = new Random();		
		int randomValue = random.nextInt();
		data.setId(randomValue);	

		Response res = given().baseUri(EndPoints.BASE_URL).pathParam("username", userName)
				.header("accept", "application/json").body(data).when()
				.get(EndPoints.UPDATE_ENDPOINT);

		return res;

	}

	public static Response getUser(String userName) {
		
		//System.out.println(EndPoints.GET_ENDPOINT + userName);

		Response res = given().baseUri(EndPoints.BASE_URL).pathParam("username", userName)
				.header("accept", "application/json").when().get(EndPoints.GET_ENDPOINT);
				//.header("accept", "application/json").when().get(EndPoints.GET_ENDPOINT + "/{username}");

		return res;

	}

	public static Response deleteUser(String userName) {

		Response res = given().baseUri(EndPoints.BASE_URL).pathParam("username", userName)
				.header("accept", "application/json").when().delete(EndPoints.DELTE_ENDPOINT);

		return res;

	}

}
