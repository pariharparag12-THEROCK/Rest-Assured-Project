package apiCalls_Package;

import org.testng.annotations.Test;

import base.BaseData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class EcomAddProduct extends BaseData{

	@Test
	public void AddProduct() {
		
		
		String path =	System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshot 2024-04-25 150103.png";
		
		File file = new File(path);
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		 Response resp = given()
							
							.header("Authorization", TOKEN)
							.param("productName", "Tshirt")		
							.param("productAddedBy", USER_ID)
							.param("productCategory", "Fashion")
							.param("productSubCategory", "Shirt")
							.param("productPrice", "500")
							.param("productDescription", "Lee Cooper Tshirt")
							.param("productFor", "Men")
							.multiPart("productImage", file)
							
							
							.log().all()
							
							.when()
							
							.post("api/ecom/product/add-product")
							
							.then()
							
							.log().all()
							
							.extract()
							
							.response();
			
			
			
			
					JsonPath jp = resp.jsonPath();
					
					PRODUCT_ID = jp.getString("productId");
					
					System.out.println("Product ID is : " + PRODUCT_ID);
	}

	
		
}
