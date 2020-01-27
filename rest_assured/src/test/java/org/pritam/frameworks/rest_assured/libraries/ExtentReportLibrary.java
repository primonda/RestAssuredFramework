package libraries;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportLibrary{
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	static ExtentReportLibrary returnClassObj;
	
	private ExtentReportLibrary(){
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//extentReport.html");
		System.out.println(System.getProperty("user.dir")+"//extentReport.html");
	}
	
	public ExtentReports extentReport() {
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("environment", "QA");
		return extent;
	}
	
	public static ExtentReportLibrary ExtentReportLibraryObject() {
		if(returnClassObj == null) {
			returnClassObj = new ExtentReportLibrary();
		}
		return returnClassObj;
	}
 
}