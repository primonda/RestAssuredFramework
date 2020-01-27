package test_cases.module_001.api_aaa.functional_validations;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import libraries.Setup;

public class SucessFunctionalValidations extends Setup {

	RequestSpecification requestSpec;
	RequestSpecBuilder requestBuilder;
	ResponseSpecification responseSpec;
	ResponseSpecBuilder responseBuilder;
	
	@BeforeClass
	public void beforeClassSetup() {
		requestBuilder = new RequestSpecBuilder();
		requestBuilder.addPathParam("param1", "param1_value");
		requestBuilder.addHeader("heyThere", "heyThere");
		requestSpec = requestBuilder.build();
	}

	@Test
	public void test() {
		createTest(new Throwable().getStackTrace()[0].getMethodName());
		Response response = given().
			get(uri);
		Assert.assertEquals(response.statusCode(), 200);
		logInfo("got suceess response");
		logInfo(response.asString());
		System.out.println(response.asString());
		System.out.println("********************"+response.body().jsonPath().get("userId").toString());
	}

	//@Test
	public void test1() throws Exception {
		requestSpec.log().all();
		Response response = given().spec(requestSpec).get(uri);
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println(response.body().jsonPath().get("userId").toString());
	}

	//@Test
	public void test2() {
		createTest(new Throwable().getStackTrace()[0].getMethodName());
		logInfo("failing method"+ System.getProperty("baseUri"));
		Assert.fail("method failed");
	}

}
