package reusableClasses;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import LauchApp.LaunchApplication;


public class TestNGListener implements ITestListener {

    WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture screenshot on test failure
        Object testInstance = result.getInstance();
        driver = LaunchApplication.driver;
        WebActions actions = new WebActions(driver);
        try {
			actions.takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
