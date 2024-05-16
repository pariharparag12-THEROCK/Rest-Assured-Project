package apiCalls_Package;

import org.testng.annotations.Test;

import base.BaseData;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class EcomDeleteProduct extends BaseData{

	
	@Test
	public void deleteproduct() {
		
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		
							given()
							
							.header("Authorization", TOKEN)
							
							.pathParam("keyOfPathParam", PRODUCT_ID)
							
							.log().all()
							
							.when()
							
							.delete("api/ecom/product/delete-product/{keyOfPathParam}")
							
							.then()
							
							.log().all()
							
							.extract()
							
							.response();
		
	}
}
