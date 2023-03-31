package api.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import java.util.*;


public class HTTPRequest {

	int id;
	
	@Test (priority=1)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
    }
	
	@Test (priority=2)
	void createUser() {
		
		HashMap myMap = new HashMap();
		myMap.put("name","Sun");
		myMap.put("job","energySupply");
		
	    id=given()
			.contentType("application/json")
			.body(myMap)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			

	}


	@Test(priority=3, dependsOnMethods= {"createUser"})	
	void updateUser() {
		
		HashMap myMap = new HashMap();
		myMap.put("name","Moon");
		myMap.put("job","beautySupply");
	
		  given()
			.contentType("application/json")
			.body(myMap)
			
		  .when()
		  	.put("https://reqres.in/api/users/"+id)
		  	
		
	      .then()
	      	.statusCode(200);
	      	
}
	@Test(priority=4)
	void deleteUser() {
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
		    .statusCode(204)
      	    .log() .all();
	}
	
	
}
