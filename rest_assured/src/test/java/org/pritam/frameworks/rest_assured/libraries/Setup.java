package libraries;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.yaml.snakeyaml.Yaml;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.response.ValidatableResponse;

import org.testng.annotations.Parameters;

public class Setup {
	
	private ExtentReports extentReport;
	private ExtentTest testReport;
	public String uri;
	
	@BeforeTest
	@Parameters({"module_name", "api_name"})
	public void setUp(String module_name, String api_name) throws FileNotFoundException {
		ExtentReportLibrary extentReportLib = ExtentReportLibrary.ExtentReportLibraryObject();
		extentReport = extentReportLib.extentReport();
		uri = return_complete_url(module_name,api_name);
	}
	
	protected void createTest(String methodName) {
		testReport = extentReport.createTest(methodName);
	}
	
	
	protected void logInfo(String message) {
		testReport.log(Status.INFO, message);
	}
    

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			testReport.log(Status.FAIL, "Test Case Failed: " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			testReport.log(Status.PASS, "Test Case Passed: " + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			testReport.log(Status.SKIP, "Test Case Skipped: " + result.getName());
		}
	}

	@AfterSuite
	public void afterClass() {
		extentReport.flush();
	}

	public String return_complete_url(String module_name, String api_name) throws FileNotFoundException {
		@SuppressWarnings("unchecked")
		Map<String, Object> env = (Map<String, Object>)loadYaml(System.getProperty("user.dir") + "/project_config.yaml").get(System.getProperty("env"));
		String baseUri = env.get("base_uri").toString();
		String basePath = loadYaml(System.getProperty("user.dir") +"\\src\\test\\java\\org\\pritam\\frameworks\\rest_assured\\test_cases\\"+module_name+"\\"+module_name+"_config.yaml").get("base_path").toString();
		String uri = loadYaml(System.getProperty("user.dir")+"\\src\\test\\java\\org\\pritam\\frameworks\\rest_assured\\test_cases\\"+module_name+"\\"+api_name+"\\"+api_name+"_config.yaml").get("uri").toString();
		System.out.println(baseUri + "/"+ basePath + "/" + uri);
		return baseUri + "/"+ basePath + "/" + uri;
	}
	
	public Map<String,Object> loadYaml(String filePath) throws FileNotFoundException {
		Yaml yaml = new Yaml();
		InputStream inputStream = new FileInputStream(filePath);
		Map<String,Object> yamlData = yaml.load(inputStream);
		return yamlData;
	}
	
}
