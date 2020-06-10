import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
public class CookieBasedAuthentication {
	
	@Test
	public void cookieAuthentication() {
		
		String userCridential="{\n" + 
				"    \"username\":\"user id\",\n" + 
				"    \"password\":\"password\"\n" + 
				"}";
		
		Response res=given()
				.header("Content-Type", "application/json")
				.body(userCridential)
		.when()
				.post("resources of the API")
		.then()
				.assertThat().statusCode(201)
		.extract().response();
		
		String s=res.asString();
		
		JsonPath js=new JsonPath(s);
		String sessionId=js.get("enter the json path here like session.value");
		System.out.println(sessionId);	
	}
}
