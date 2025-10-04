package api.endpoints;

public class EndPoints {
	
	//Pet store API's
	
	public static String BASE_URL="https://petstore.swagger.io/v2";	
	public static String POST_ENDPOINT="/user";
	public static String UPDATE_ENDPOINT="/user/{username}";
	public static String DELTE_ENDPOINT="/user/{username}";
	public static String GET_ENDPOINT="/user/{username}";
	
	//Employee API's
	
	public static String EMP_BASE_URL="https://dummy.restapiexample.com/api/v1";	
	public static String EMP_POST_ENDPOINT="/create";
	public static String EMP_UPDATE_ENDPOINT="/update/{id}";
	public static String EMP_DELTE_ENDPOINT="/delete/{id}";
	public static String EMP_GET_ENDPOINT="/employee/{id}";
	public static String EMP_GET_ALL_ENDPOINT="/employee";	
	
	//Object API's
	
	public static String OBJ_BASE_URL="https://api.restful-api.dev/objects";	
	public static String OBJ_POST_ENDPOINT="";
	public static String OBJ_UPDATE_ENDPOINT="/{id}";
	public static String OBJ_DELTE_ENDPOINT="/{id}";
	public static String OBJ_GET_ENDPOINT="/{id}";
	public static String OBJ_GET_ALL_ENDPOINT="";	
	
	//JsonPlace holder API's (posts, comments...)	

	public static String JSONPH_BASE_URL="https://jsonplaceholder.typicode.com";
	public static String JP_GET_ENDPOINT="/posts/1";
	public static String JP_GET_ALL_COMMENTS_ENDPOINT="/comments?postId=1";
	public static String JP_POST_TODO_ENDPOINT="/todos";
	
	//ReqResponse API's
	
	public static String REQRES_BASE_URL="https://reqres.in";
	public static String REQRES_GET_ALL_USERS_ENDPOINT1="/api/users?page=2";
	public static String REQRES_POST_USER_ENDPOINT="/api/users";
	/*
	 * public static String JP_GET_ALL_COMMENTS_ENDPOINT="/comments?postId=1";
	 * public static String JP_POST_TODO_ENDPOINT="/todos";
	 */
	
	//Dogs
	
	public static String DOG_BASE_URL="https://dog.ceo/api/breeds/image/random";
	public static String REQRES_GET_ALL_USERS_ENDPOINT="/api/users?page=2";
	
	
	
	
	
	
	
	
	

}
