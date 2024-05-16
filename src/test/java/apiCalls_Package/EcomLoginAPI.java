package apiCalls_Package;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import pojo_Package.Ecom_Login_Request_Body;
import pojo_Package.Ecom_Login_Response_Body;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseData;



public class EcomLoginAPI extends BaseData{

	@Test
	public void loginToApp() {
		
		
		//Serialization
		Ecom_Login_Request_Body LoginRequestBody = new Ecom_Login_Request_Body();
		
		LoginRequestBody.setUserEmail("restassuredTesting@gmail.com");
		LoginRequestBody.setUserPassword("Thunderball@13");
		
		
		RestAssured.baseURI =  "https://rahulshettyacademy.com/";
		
		
		
				Response resp = given()
								
								.header("Content-Type", "application/json")
								
								.body(LoginRequestBody)
								
								.log().all()
								
								.when()
								
								.post("api/ecom/auth/login")
								
								.then()
								
								.log().all()
								
								.extract()
								
								.response();
								
								
								
			int statuscode	=	resp.statusCode();
			
			Assert.assertEquals(statuscode, 200);
			 
			
			//Deserialization	
			Ecom_Login_Response_Body  loginResponseObject = resp.as(Ecom_Login_Response_Body.class);
			
			
			TOKEN =	loginResponseObject.getToken();	
			System.out.println("Token is : " + TOKEN);
			
			USER_ID	= loginResponseObject.getUserId();
			System.out.println("UserId is : " + USER_ID);
		
	}
}
