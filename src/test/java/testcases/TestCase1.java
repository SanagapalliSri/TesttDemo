package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import LauchApp.LaunchApplication;
import net.sourceforge.tess4j.TesseractException;
import reusableClasses.HomePage;

public class TestCase1 extends LaunchApplication {

	@Test
	public void testLogin() throws InterruptedException, IOException, TesseractException {

		HomePage loginPage = new HomePage(driver);
		loginPage.enterDetailsInHomePage();

	}
}
