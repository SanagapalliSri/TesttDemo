package reusableClasses;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;

import LauchApp.LaunchApplication;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class WebActions extends LaunchApplication {

	private WebDriver driver;

	public WebActions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterText(WebElement element, String text) {

		element.clear();
		element.sendKeys(text);
	}

	public void click(WebElement element) {

		element.click();
	}

	public void selectRadioButton(By locator) {
		WebElement radioButton = driver.findElement(locator);
		if (!radioButton.isSelected()) {
			radioButton.click();
		}
	}

	public void takeScreenshot() throws IOException {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "/screenshots/" + "screenshot" + ".png";
			FileUtils.copyFile(src, new File(destination));
			System.out.println("Screenshot saved at: " + destination);
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}

	public static String extractCaptchaText(File captchaScreenshot) throws TesseractException {

		String projectDir = System.getProperty("user.dir");
		String path = projectDir + "\\captchaScreenshots" + "\\Captcha.png";
		try {

			FileHandler.copy(captchaScreenshot, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return extractTextFromImage(path);
	}

	private static String extractTextFromImage(String path) throws TesseractException {
		Tesseract image = new Tesseract();
		image.setDatapath("C:\\Users\\sanag\\Downloads\\ProjectDemo\\demo\\tessdata");
		image.setLanguage("eng");
		String captchaText = "";
		try {
			File imageFile = new File(path);
			if (!imageFile.exists()) {
				throw new IOException("File not found: " + path);
			}
			captchaText = image.doOCR(imageFile);
		} catch (TesseractException e) {
			System.err.println("Error during OCR: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("File error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		}
		return captchaText.replaceAll("\\s+", "");

	}

}
