import java.io.File;
import java.io.FileInputStream;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
public class ReqresDotIn {
	
	Properties prop=new Properties();
	@BeforeTest
	public void readPropertyFile() throws IOException {
		File f=new File("/Users/mdkashemhossain/eclipse-workspace/RestAssuredRahulShettyprojectWithDataDrivenTesting/file.properties");
		FileInputStream fp= new FileInputStream(f);
		prop.load(fp);
	}
	@Test
	public void getRequest() {
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response resp=given()			
				.param("page", 2)
		.when()
				.get(Resources.resourceData())
		.then()
		.assertThat().statusCode(200)
		.extract().response();
		
		String str=resp.asString();
		System.out.println(str);	
	}
	@Test
	public void postRequest(){
		RestAssured.baseURI=prop.getProperty("HOST");
	Response resp=given()
			.body(PayLoad.bodyData())
	.when()
			.post(Resources.resourceData())
	.then()
	.assertThat().statusCode(201)
	.extract().response();
	String s=resp.asString();
	System.out.println();
	System.out.println(s);
	
		JsonPath js=new JsonPath(s);
		String placeid=js.get("place_id");
		System.out.println("the place-id is ");
		System.out.println(placeid);
	}
}
