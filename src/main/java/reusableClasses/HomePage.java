package reusableClasses;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.sourceforge.tess4j.TesseractException;

public class HomePage extends WebActions {
	private WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[contains(@class,'h_menu_drop_button')])[1]")
	private WebElement menuButton;

	@FindBy(xpath = "//button[text()='LOGIN']")
	private WebElement loginButton;

	@FindBy(xpath = "//input[@formcontrolname='userid']")
	private WebElement userName;

	@FindBy(xpath = "//input[@formcontrolname='password']")
	private WebElement password;

	@FindBy(xpath = "//button[text()='SIGN IN']")
	private WebElement signIn;

	@FindBy(xpath = "//img[@class='captcha-img']")
	private WebElement captchaElement;

	@FindBy(xpath = "//input[@formcontrolname='captcha']")
	private WebElement capchaTextbox;

	public void enterDetailsInHomePage() throws InterruptedException, IOException, TesseractException {
		click(menuButton);
		Thread.sleep(1000);
		click(loginButton);
		enterText(userName, "Sri5817389");
		enterText(password, "14JE1a0530_30");
		Thread.sleep(5000);
		File srcCaptcha = captchaElement.getScreenshotAs(OutputType.FILE);

		String captchaText = extractCaptchaText(srcCaptcha);
		System.out.println("Captcha Text is -" + captchaText);
		enterText(capchaTextbox, captchaText);
		Thread.sleep(3000);
		click(signIn);
		Thread.sleep(5000);

	}

}
