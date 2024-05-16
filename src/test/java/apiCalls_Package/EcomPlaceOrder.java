package apiCalls_Package;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class EcomPlaceOrder extends BaseData{
	
	
	
	RequestSpecification requestSpecification;
	
	ResponseSpecification responseSpecification;
	
	
	
	@BeforeClass
	public void initRequestAndResponseSpecBuilder() {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		
		requestSpecBuilder.setBaseUri("https://rahulshettyacademy.com/");
		
		requestSpecBuilder.addHeader("Authorization", TOKEN);
		
		requestSpecBuilder.log(LogDetail.ALL);
		
		requestSpecification = requestSpecBuilder.build();
		
		
		
		ResponseSpecBuilder responseSpecBuilder = new  ResponseSpecBuilder();
		
		responseSpecBuilder.expectStatusCode(201);
		
		responseSpecBuilder.log(LogDetail.ALL);
		
		responseSpecification = responseSpecBuilder.build();
		
	}
	
	
	
	
	@Test (priority = 1)
	public void placeOrder() {
		
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com/";
	
			Response resp   =		given()
									
									.header("Content-Type", "application/json")
									
									.spec(requestSpecification)
									
									.body("{\r\n"
											+ "    \"orders\": [\r\n"
											+ "        {\r\n"
											+ "            \"country\": \"India\",\r\n"
											+ "            \"productOrderedId\": \""+PRODUCT_ID+"\"\r\n"
											+ "        }\r\n"
											+ "    ]\r\n"
											+ "}")
									
									
								
									
									.when()
									
									.post("api/ecom/order/create-order")
									
									.then()
									
									.log().all()
									
									.extract()
									
									.response();
			
			
					JsonPath jp =	 resp.jsonPath();
			
					ORDERS   =	jp.getString("orders[0]");
					
					System.out.println("ORDER ID id is : "+ ORDERS);
		
	}
	
	
	
	@Test(priority = 2)
	public void viewOrder() {
		
		
		
										given()
												
										//.header("Content-Type", "application/json")
										
										
												
										.spec(requestSpecification)
										
										
										.queryParam("id", ORDERS)
												
										.when()
												
										.get("api/ecom/order/get-orders-details")
												
										.then()
//												
//										.log().all()
//												
//										.extract()
										
//										.response();
												
										.spec(responseSpecification);
								
								

		
	}
	
	
	
}
